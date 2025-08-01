package bg.sofia.uni.fmi.tdkirov.trippacker.service.implementation;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack.ItemToPackCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.service.ItemToPackService;

public class ItemToPackServiceImpl implements ItemToPackService {
    @Override
    public Long createItem(ItemToPackCreateDto itemToPackDto, String currentUser) {
        return 1L;
    }

    @Override
    public void deleteItemById(Long id, String currentUser) {

    }
}
