package bg.sofia.uni.fmi.tdkirov.trippacker.service.implementation;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packingitem.PackingItemNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packingitem.PackingItemNotOwnedByYou;
import bg.sofia.uni.fmi.tdkirov.trippacker.mapper.PackingGroupMapper;
import bg.sofia.uni.fmi.tdkirov.trippacker.mapper.PackingItemMapper;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.ItemToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingGroup;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingItem;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.PackingItemRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.UserRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.PackingItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PackingItemServiceImpl implements PackingItemService {
    private PackingItemMapper packingItemMapper;

    private PackingItemRepository packingItemRepository;
    private UserRepository userRepository;

    private void assertNotFound(Long id) {
        if (!packingItemRepository.existsById(id)) {
            throw new PackingItemNotFound(id);
        }
    }

    private void assertNotOwnedByUser(Long id, User currentUser) {
        if (!packingItemRepository.existsByIdAndCreatedById(id, currentUser.getId())) {
            throw new PackingItemNotOwnedByYou(id, currentUser);
        }
    }

    @Override
    public void assertItemExists(Long id, User user) {
        assertNotFound(id);
        assertNotOwnedByUser(id, user);
    }

    @Override
    public Long createItem(PackingItemCreateDto packingItemDto, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        PackingItem packingItem = packingItemMapper.toEntity(packingItemDto, user);

        return packingItemRepository.save(packingItem).getId();
    }

    @Override
    public PackingItem createItem(Long itemToPackId, Long packingGroupId, Long userId) {
        return new PackingItem(new ItemToPack(itemToPackId), null,
            new PackingGroup(packingGroupId), new User(userId));
    }

    @Override
    public void deleteItemById(Long id, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        assertItemExists(id, user);

        packingItemRepository.deleteById(id);
    }
}
