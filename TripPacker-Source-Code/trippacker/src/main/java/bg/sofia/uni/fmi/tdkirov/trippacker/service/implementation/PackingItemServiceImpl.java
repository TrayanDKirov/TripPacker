package bg.sofia.uni.fmi.tdkirov.trippacker.service.implementation;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packingitem.PackingItemNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packingitem.PackingItemNotOwnedByYou;
import bg.sofia.uni.fmi.tdkirov.trippacker.mapper.PackingItemMapper;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingItem;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.PackingItemRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.UserRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.PackingItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PackingItemServiceImpl implements PackingItemService {
    private UserRepository userRepository;
    private PackingItemRepository packingItemRepository;
    private PackingItemMapper packingItemMapper;

    @Override
    public Long createItem(PackingItemCreateDto packingItemDto, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        PackingItem packingItem = packingItemMapper.toEntity(packingItemDto, user);

        return packingItemRepository.save(packingItem).getId();
    }

    private void assertNotFound(Long id) {
        if (!packingItemRepository.existsById(id)) {
            throw new PackingItemNotFound(id);
        }
    }

    private void assertNotOwnedByYou(Long id, User currentUser) {
        if (!packingItemRepository.existsByIdAndCreatedById(id, currentUser.getId())) {
            throw new PackingItemNotOwnedByYou(id, currentUser);
        }
    }

    @Override
    public void deleteItemById(Long id, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        assertNotFound(id);
        assertNotOwnedByYou(id, user);

        packingItemRepository.deleteById(id);
    }
}
