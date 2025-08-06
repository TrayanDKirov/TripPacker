package bg.sofia.uni.fmi.tdkirov.trippacker.service;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packinggroup.PackingGroupNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packinggroup.PackingGroupNotOwnedByYou;

public interface PackingGroupService {
    /**
     * Creates a PackingGroup entity using a creation data transfer object and saves it in the database.
     *
     * @param packingGroupDto the creation data transfer object, must not be null.
     * @param currentUser the username of the current user, must not be null.
     * @return the id of the PackingGroup entity in the database.
     */
    Long createPackingGroup(PackingGroupCreateDto packingGroupDto, String currentUser);

    /**
     * Finds and deletes the PackingGroup entity associated with this id.
     *
     * @param id an unique identifier of the entity, must not be null.
     * @param currentUser the username of the current user, must not be null.
     *
     * @throws PackingGroupNotFound if the packing group is not found.
     * @throws PackingGroupNotOwnedByYou if the packing group is not owned by you.
     */
    void deletePackingGroupById(Long id, String currentUser);
}
