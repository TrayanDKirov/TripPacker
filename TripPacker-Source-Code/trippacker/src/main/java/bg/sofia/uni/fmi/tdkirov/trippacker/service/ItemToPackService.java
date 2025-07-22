package bg.sofia.uni.fmi.tdkirov.trippacker.service;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.itemtopack.ItemToPackCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.itemtopack.ItemToPackNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.itemtopack.ItemToPackNotOwnedByYou;

public interface ItemToPackService {
    /**
     * Creates an item using a creation data transfer object, associates it with a specific trip or packingGroup
     * and saves it into the database.
     *
     * @param itemToPackDto the creation data transfer object, must not be null.
     * @param currentUser the username of the current user, must not be null.
     *
     * @return the id of the item in the database.
     */
    Long createItem(ItemToPackCreateDto itemToPackDto, String currentUser);

    /**
     * Deletes ItemToPack entity associated with this id from the database.
     *
     * @param id an unique identifier of the item.
     * @param currentUser the username of the current user, must not be null.
     *
     * @throws ItemToPackNotFound if the item with this id was not found.
     * @throws ItemToPackNotOwnedByYou if the item is not owned by the current user.
     */
    void deleteItemById(Long id, String currentUser);
}
