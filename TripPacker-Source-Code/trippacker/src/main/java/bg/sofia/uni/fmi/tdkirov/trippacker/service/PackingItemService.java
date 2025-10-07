package bg.sofia.uni.fmi.tdkirov.trippacker.service;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemUpdateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packingitem.PackingItemNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packingitem.PackingItemNotOwnedByYou;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingItem;

public interface PackingItemService {
    /**
     * Creates an item using a creation data transfer object, associates it with a specific trip or packingGroup
     * and saves it into the database.
     *
     * @param packingItemDto the creation data transfer object, must not be null.
     * @param currentUser the username of the current user, must not be null.
     *
     * @return the id of the item in the database.
     */
    Long createItem(PackingItemCreateDto packingItemDto, String currentUser);

    /**
     * Creates a PackingItem entity, saves it in the database and returns it.
     *
     * @param itemToPackId the unique identifier of the ItemToPack entity, must not be null.
     * @param packingGroupId the unique identifier of the PackingGroup entity, must not be null.
     * @param userId the unique identifier of the User entity, must not be null.
     *
     * @return the PackingItem entity returned which has already been saved in the database.
     */
    PackingItem createItem(Long itemToPackId, Long packingGroupId, Long userId);

    /**
     * Updates the PackingItem entity associated with this id and saves it into the database.
     *
     * @param id the unique identifier of the entity, must not be null.
     * @param packingItemDto the update data transfer object, must not be null.
     * @param currentUser the username of the current user, must not be null.
     *
     * @throws PackingItemNotFound if the item with this id was not found.
     * @throws PackingItemNotOwnedByYou if the item is not owned by the current user.
     */
    void updatePackingItem(Long id, PackingItemUpdateDto packingItemDto, String currentUser);

    /**
     * Deletes PackingItem entity associated with this id from the database.
     *
     * @param id an unique identifier of the item.
     * @param currentUser the username of the current user, must not be null.
     *
     * @throws PackingItemNotFound if the item with this id was not found.
     * @throws PackingItemNotOwnedByYou if the item is not owned by the current user.
     */
    void deleteItemById(Long id, String currentUser);
}
