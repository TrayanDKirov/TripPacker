package bg.sofia.uni.fmi.tdkirov.trippacker.service.implementation;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack.ItemToPackCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack.ItemToPackResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.itemtopack.ItemToPackNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.itemtopack.ItemToPackNotOwnedByYou;
import bg.sofia.uni.fmi.tdkirov.trippacker.mapper.ItemToPackMapper;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.ItemToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.ItemToPackRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.UserRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.GroupToPackService;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.ItemToPackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class ItemToPackServiceImpl implements ItemToPackService {
    private ItemToPackMapper itemToPackMapper;

    private ItemToPackRepository itemToPackRepository;
    private UserRepository userRepository;

    private GroupToPackService groupService;

    @Override
    public Long createItem(ItemToPackCreateDto itemToPackDto, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        groupService.assertGroupExists(itemToPackDto.getGroupId(), user);
        ItemToPack itemToPack = itemToPackMapper.toEntity(itemToPackDto, user);

        return itemToPackRepository.save(itemToPack).getId();
    }

    @Override
    public Set<ItemToPackResponseDto> getItemsByGroupId(Long groupId, String currentUser) {
        GroupToPackResponseDto group = groupService.getGroupToPackById(groupId, currentUser);

        return Set.copyOf(group.getItems());
    }

    private void assertNotFound(Long id) {
        if (!itemToPackRepository.existsById(id)) {
            throw new ItemToPackNotFound(id);
        }
    }

    private void assertNotOwnedByUser(Long id, User currentUser) {
        if (!itemToPackRepository.existsByIdAndCreatedById(id, currentUser.getId())) {
            throw new ItemToPackNotOwnedByYou(id, currentUser);
        }
    }

    @Override
    public void deleteItemById(Long id, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        assertNotFound(id);
        assertNotOwnedByUser(id, user);

        itemToPackRepository.deleteById(id);
    }
}
