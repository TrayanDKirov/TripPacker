package bg.sofia.uni.fmi.tdkirov.trippacker.service.implementation;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageUpdateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.tripluggage.TripLuggageNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.tripluggage.TripLuggageNotOwnedByYou;
import bg.sofia.uni.fmi.tdkirov.trippacker.mapper.TripLuggageMapper;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.TripLuggage;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.TripLuggageRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.UserRepository;
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
    public Set<TripLuggageResponseDto> getAllTripLuggage(String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        Set<TripLuggage> tripLuggageSet = tripLuggageRepository.findByCreatedById(user.getId());
        Set<TripLuggageResponseDto> result = new LinkedHashSet<>();
        for (TripLuggage curr : tripLuggageSet) {
            result.add(tripLuggageMapper.toResponseDto(curr));
        }

        return result;
    }

    @Override
    public void updateTripLuggage(Long id, TripLuggageUpdateDto tripLuggageDto, String currentUser) {

    }

    @Override
    public void deleteTripLuggageById(Long id, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        assertNotFound(id);
        assertNotOwnedByUser(id, user);

        tripLuggageRepository.deleteById(id);
    }
}
