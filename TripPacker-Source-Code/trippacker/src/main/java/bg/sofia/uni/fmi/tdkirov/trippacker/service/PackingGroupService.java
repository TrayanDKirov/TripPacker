package bg.sofia.uni.fmi.tdkirov.trippacker.service;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.packinggroup.PackingGroupUpdateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packinggroup.PackingGroupNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.packinggroup.PackingGroupNotOwnedByYou;

import java.util.Set;

public interface PackingGroupService {
    /**
     * Creates a new PackingGroup entity and save it into the database using
     * a creation data transfer object. And returns it's id.
     *
     * @param packingGroupDto the creation data transfer object, must not be null.
     * @param currentUser the username of the current user, must not be null.
     *
     * @return the id of the packing group in the database.
     */
    Long createPackingGroup(PackingGroupCreateDto packingGroupDto, String currentUser);

    /**
     * Retrieves the PackingGroup entity associated with this id and returns it
     * as a response data transfer object.
     *
     * @param id a unique identifier of the packing group, must not be null.
     * @param currentUser the username of the current user, must not be null.
     *
     * @return the response data transfer object of the packing group associated with this id.
     *
     * @throws PackingGroupNotFound if packing group with this id was not found.
     * @throws PackingGroupNotOwnedByYou if packing group is not owned by the current user.
     */
    PackingGroupResponseDto getPackingGroupById(Long id, String currentUser);

    /**
     * Retrieves all PackingGroup entities of the current user and returns them as
     * an unmodifiable set of response data transfer objects.
     *
     * @param currentUser the username of the current user, must not be null.
     *
     * @return an unmodifiable set of response data transfer objects.
     */
    Set<PackingGroupResponseDto> getAllPackingGroups(String currentUser);

    /**
     * Updates an existing PackingGroup entity associated with this id and saves it into the database,
     * using an update data transfer object.
     *
     * @param id a unique identifier of the packing group, must not be null.
     * @param packingGroupDto the update data transfer object, must not be null.
     * @param currentUser the username of the current user, must not be null.
     *
     * @throws PackingGroupNotFound if packing group with this id was not found.
     * @throws PackingGroupNotOwnedByYou if packing group is not owned by the current user.
     */
    void updatePackingGroup(Long id, PackingGroupUpdateDto packingGroupDto, String currentUser);

    /**
     * Deletes PackingGroup entity associated with this id from the database.
     *
     * @param id a unique identifier of the packing group, must not be null.
     *
     * @throws PackingGroupNotFound if packing group with this id was not found.
     * @throws PackingGroupNotOwnedByYou if packing group is not owned by the current user.
     */
    void deletePackingGroupById(Long id, String currentUser);
}
