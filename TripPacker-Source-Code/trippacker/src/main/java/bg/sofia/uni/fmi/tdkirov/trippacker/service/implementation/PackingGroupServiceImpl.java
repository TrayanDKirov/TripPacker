package bg.sofia.uni.fmi.tdkirov.trippacker.service.implementation;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.mapper.PackingGroupMapper;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.GroupToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.ItemToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingGroup;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingItem;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.TripLuggage;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.GroupToPackRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.PackingGroupRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.GroupToPackService;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.PackingGroupService;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.PackingItemService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class PackingGroupServiceImpl implements PackingGroupService {
    private GroupToPackService groupToPackService;
    private PackingItemService packingItemService;

    private PackingGroupMapper packingGroupMapper;

    private PackingGroupRepository packingGroupRepository;
    private GroupToPackRepository groupToPackRepository;

    @Override
    @Transactional
    public PackingGroup createPackingGroup(PackingGroupCreateDto packingGroupDto, TripLuggage tripLuggage,
                                           User currentUser) {
        groupToPackService.assertGroupExists(packingGroupDto.getGroupToPackId(), currentUser);

        PackingGroup packingGroup = packingGroupMapper
            .toEntity(packingGroupDto, tripLuggage, currentUser);

        PackingGroup createdPackingGroup = packingGroupRepository.save(packingGroup);
        GroupToPack groupToPack = groupToPackRepository.findById(createdPackingGroup.getGroup().getId()).get();

        Set<PackingItem> createdItems = new LinkedHashSet<>();
        for (ItemToPack curr : groupToPack.getItems()) {
            PackingItem item = packingItemService
                .createItem(curr.getId(), createdPackingGroup.getId(), currentUser.getId());

            createdItems.add(item);
        }
        createdPackingGroup.getItems().addAll(createdItems);

        return packingGroupRepository.save(createdPackingGroup);
    }

    @Override
    public void deletePackingGroupById(Long id, User currentUser) {

    }
}
