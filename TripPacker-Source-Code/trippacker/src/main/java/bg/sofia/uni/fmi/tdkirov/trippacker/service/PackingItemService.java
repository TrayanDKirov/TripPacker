package bg.sofia.uni.fmi.tdkirov.trippacker.service;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packingitem.PackingItemCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packingitem.PackingItemNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packingitem.PackingItemNotOwnedByYou;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.PackingItem;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;

public interface PackingItemService {
    /**
     * Assets if the item exits and if it is owned be the current user.
     *
     * @param id the unique identifier of the item, must not be null.
     * @param user the current user, must not be null.
     *
     * @throws PackingItemNotFound if packing item with this id was not found.
     * @throws PackingItemNotOwnedByYou if packing item is not owned by the current user.
     */
    void assertItemExists(Long id, User user);

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
