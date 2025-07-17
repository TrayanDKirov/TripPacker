package bg.sofia.uni.fmi.tdkirov.trippacker.service.implementation;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupUpdateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packinggroup.PackingGroupNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packinggroup.PackingGroupNotOwnedByYou;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingGroup;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.PackingGroupRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.UserRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.PackingGroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class PackingGroupServiceImpl implements PackingGroupService {
    private UserRepository userRepository;
    private PackingGroupRepository packingGroupRepository;

    @Override
    public Long createPackingGroup(PackingGroupCreateDto packingGroupDto, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        PackingGroup packingGroup = PackingGroup.fromDtoAndUser(packingGroupDto, user);

        return packingGroupRepository.save(packingGroup).getId();
    }

    private void assertNotFound(Long id) {
        if (!packingGroupRepository.existsById(id)) {
            throw new PackingGroupNotFound(id);
        }
    }

    private void assertNotOwnedByUser(Long packingGroupID, User currentUser) {
        if (!packingGroupRepository.existsByIdAndCreatedById(packingGroupID, currentUser.getId())) {
            throw new PackingGroupNotOwnedByYou(packingGroupID, currentUser);
        }
    }

    @Override
    public PackingGroupResponseDto getPackingGroupById(Long id, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        assertNotFound(id);
        assertNotOwnedByUser(id, user);

        PackingGroup packingGroup = packingGroupRepository.findById(id).get();

        return packingGroup.toResponseDto();
    }

    @Override
    public Set<PackingGroupResponseDto> getAllPackingGroups(String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        Set<PackingGroup> packingGroups = packingGroupRepository.findByCreatedById(user.getId());

        Set<PackingGroupResponseDto> result = new LinkedHashSet<>();
        for (PackingGroup curr : packingGroups) {
            result.add(curr.toResponseDto());
        }

        return Set.copyOf(result);
    }

    @Override
    public void updatePackingGroup(Long id, PackingGroupUpdateDto packingGroupDto, String currentUser) {

    }

    @Override
    public void deletePackingGroupById(Long id, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        assertNotFound(id);
        assertNotOwnedByUser(id, user);

        packingGroupRepository.deleteById(id);
    }
}
