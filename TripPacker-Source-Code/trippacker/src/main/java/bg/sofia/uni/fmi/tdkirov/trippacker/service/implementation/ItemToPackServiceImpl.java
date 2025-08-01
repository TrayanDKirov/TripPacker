package bg.sofia.uni.fmi.tdkirov.trippacker.service.implementation;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack.ItemToPackCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.itemtopack.ItemToPackNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.itemtopack.ItemToPackNotOwnedByYou;
import bg.sofia.uni.fmi.tdkirov.trippacker.mapper.ItemToPackMapper;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.ItemToPack;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.ItemToPackRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.repository.UserRepository;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.ItemToPackService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ItemToPackServiceImpl implements ItemToPackService {
    private ItemToPackMapper itemToPackMapper;

    private ItemToPackRepository itemToPackRepository;
    private UserRepository userRepository;

    @Override
    public Long createItem(ItemToPackCreateDto itemToPackDto, String currentUser) {
        User user = userRepository.findByUsername(currentUser).get();

        ItemToPack itemToPack = itemToPackMapper.toEntity(itemToPackDto, user);

        return itemToPackRepository.save(itemToPack).getId();
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
