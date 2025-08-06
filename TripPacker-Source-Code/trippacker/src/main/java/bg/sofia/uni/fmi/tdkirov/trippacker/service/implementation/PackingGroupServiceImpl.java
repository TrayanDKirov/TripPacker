package bg.sofia.uni.fmi.tdkirov.trippacker.service.implementation;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packinggroup.PackingGroupNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packinggroup.PackingGroupNotOwnedByYou;
import bg.sofia.uni.fmi.tdkirov.trippacker.mapper.PackingGroupMapper;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingGroup;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.PackingGroupRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.UserRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.PackingGroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PackingGroupServiceImpl implements PackingGroupService {
    private UserRepository userRepository;
    private PackingGroupRepository packingGroupRepository;
    private PackingGroupMapper packingGroupMapper;

    @Override
    public Long createPackingGroup(PackingGroupCreateDto packingGroupDto, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        PackingGroup packingGroup = packingGroupMapper.toEntity(packingGroupDto, user);

        return packingGroupRepository.save(packingGroup).getId();
    }

    private void assertNotFound(Long id) {
        if (!packingGroupRepository.existsById(id)) {
            throw new PackingGroupNotFound(id);
        }
    }

    private void assertNotOwnedByYou(Long id, User currentUser) {
        if (!packingGroupRepository.existsByIdAndCreatedById(id, currentUser.getId())) {
            throw new PackingGroupNotOwnedByYou(id, currentUser);
        }
    }

    @Override
    public void deletePackingGroupById(Long id, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        assertNotFound(id);
        assertNotOwnedByYou(id, user);

        packingGroupRepository.deleteById(id);
    }
}
