package bg.sofia.uni.fmi.tdkirov.trippacker.service.implementation;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggagePreviewDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageUpdateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.tripluggage.TripLuggageNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.tripluggage.TripLuggageNotOwnedByYou;
import bg.sofia.uni.fmi.tdkirov.trippacker.mapper.PackingGroupMapper;
import bg.sofia.uni.fmi.tdkirov.trippacker.mapper.TripLuggageMapper;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingGroup;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.TripLuggage;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.TripLuggageRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.UserRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.PackingGroupService;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.TripLuggageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class TripLuggageServiceImpl implements TripLuggageService {
    private UserRepository userRepository;
    private TripLuggageRepository tripLuggageRepository;

    private TripLuggageMapper tripLuggageMapper;
    private PackingGroupMapper packingGroupMapper;

    private PackingGroupService packingGroupService;

    @Override
    public Long createTripLuggage(TripLuggageCreateDto tripLuggageDto, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        TripLuggage tripLuggage = tripLuggageMapper.toEntity(tripLuggageDto, user);

        return tripLuggageRepository.save(tripLuggage).getId();
    }

    private void assertNotFound(Long id) {
        if (!tripLuggageRepository.existsById(id)) {
            throw new TripLuggageNotFound(id);
        }
    }

    private void assertNotOwnedByUser(Long tripLuggageId, User currentUser) {
        if (!tripLuggageRepository.existsByIdAndCreatedById(tripLuggageId, currentUser.getId())) {
            throw new TripLuggageNotOwnedByYou(tripLuggageId, currentUser);
        }
    }

    @Override
    public TripLuggageResponseDto getTripLuggageById(Long id, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        assertNotFound(id);
        assertNotOwnedByUser(id, user);

        TripLuggage tripLuggage = tripLuggageRepository.findById(id).get();

        return tripLuggageMapper.toResponseDto(tripLuggage);
    }

    @Override
    public Set<TripLuggagePreviewDto> getAllTripLuggage(String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        Set<TripLuggage> tripLuggageSet = tripLuggageRepository.findByCreatedById(user.getId());
        Set<TripLuggagePreviewDto> result = new LinkedHashSet<>();
        for (TripLuggage curr : tripLuggageSet) {
            result.add(tripLuggageMapper.toPreviewDto(curr));
        }

        return result;
    }

    private void updateNewGroup(TripLuggage tripLuggage, PackingGroupCreateDto packingGroupDto, User user) {
        PackingGroup packingGroup = packingGroupService
            .createPackingGroup(packingGroupDto, tripLuggage, user);

        tripLuggage.addPackingGroup(packingGroup);
    }

    private void deletePackingGroups(TripLuggage tripLuggage, User user) {
        for (PackingGroup curr : tripLuggage.getPackingGroups()) {
            packingGroupService.deletePackingGroupById(curr.getId(), user);
        }

        tripLuggage.setPackingGroups(new LinkedHashSet<>());
    }

    private void updateNewGroups(TripLuggage tripLuggage, Set<PackingGroupCreateDto> packingGroupDtos, User user) {
        tripLuggage.getPackingGroups().clear();

        Set<PackingGroup> newGroups = new LinkedHashSet<>();
        for (PackingGroupCreateDto curr : packingGroupDtos) {
            PackingGroup packingGroup = packingGroupService
                .createPackingGroup(curr, tripLuggage, user);

            newGroups.add(packingGroup);
        }

        tripLuggage.getPackingGroups().addAll(newGroups);
    }

    @Override
    public void updateTripLuggage(Long id, TripLuggageUpdateDto tripLuggageDto, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        assertNotFound(id);
        assertNotOwnedByUser(id, user);

        TripLuggage tripLuggage = tripLuggageRepository.findById(id).get();

        if (tripLuggageDto.getNewName() != null) {
            tripLuggage.setName(tripLuggageDto.getNewName());
        }
        if (tripLuggageDto.getNewGroup() != null) {
            updateNewGroup(tripLuggage, tripLuggageDto.getNewGroup(), user);
        }
        if (tripLuggageDto.getNewGroups() != null) {
            updateNewGroups(tripLuggage, tripLuggageDto.getNewGroups(), user);
        }

        tripLuggageRepository.save(tripLuggage);
    }

    @Override
    public void deleteTripLuggageById(Long id, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        assertNotFound(id);
        assertNotOwnedByUser(id, user);

        tripLuggageRepository.deleteById(id);
    }
}
