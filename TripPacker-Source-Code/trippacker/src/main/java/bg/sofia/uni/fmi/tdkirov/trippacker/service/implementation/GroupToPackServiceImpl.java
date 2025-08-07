package bg.sofia.uni.fmi.tdkirov.trippacker.service.implementation;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackPreviewDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackUpdateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.grouptopack.GroupToPackNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.grouptopack.GroupToPackNotOwnedByYou;
import bg.sofia.uni.fmi.tdkirov.trippacker.mapper.GroupToPackMapper;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.GroupToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.GroupToPackRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.UserRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.GroupToPackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class GroupToPackServiceImpl implements GroupToPackService {
    private UserRepository userRepository;
    private GroupToPackRepository packingGroupRepository;

    private GroupToPackMapper groupToPackMapper;

    @Override
    public Long createPackingGroup(GroupToPackCreateDto packingGroupDto, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        GroupToPack groupToPack = groupToPackMapper.toEntity(packingGroupDto, user);

        return packingGroupRepository.save(groupToPack).getId();
    }

    private void assertNotFound(Long id) {
        if (!packingGroupRepository.existsById(id)) {
            throw new GroupToPackNotFound(id);
        }
    }

    private void assertNotOwnedByUser(Long packingGroupID, User currentUser) {
        if (!packingGroupRepository.existsByIdAndCreatedById(packingGroupID, currentUser.getId())) {
            throw new GroupToPackNotOwnedByYou(packingGroupID, currentUser);
        }
    }

    @Override
    public GroupToPackResponseDto getPackingGroupById(Long id, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        assertNotFound(id);
        assertNotOwnedByUser(id, user);

        GroupToPack groupToPack = packingGroupRepository.findById(id).get();

        return groupToPackMapper.toResponseDto(groupToPack);
    }

    @Override
    public Set<GroupToPackPreviewDto> getAllPackingGroupPreviews(String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        Set<GroupToPack> groupToPacks = packingGroupRepository.findByCreatedById(user.getId());

        Set<GroupToPackPreviewDto> result = new LinkedHashSet<>();
        for (GroupToPack curr : groupToPacks) {
            result.add(groupToPackMapper.toPreviewDto(curr));
        }

        return Set.copyOf(result);
    }

    @Override
    public void updatePackingGroup(Long id, GroupToPackUpdateDto packingGroupDto, String currentUser) {

    }

    @Override
    public void deletePackingGroupById(Long id, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        assertNotFound(id);
        assertNotOwnedByUser(id, user);

        packingGroupRepository.deleteById(id);
    }
}
