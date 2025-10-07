package bg.sofia.uni.fmi.tdkirov.trippacker.service;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackPreviewDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.grouptopack.GroupToPackUpdateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.grouptopack.GroupToPackNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.grouptopack.GroupToPackNotOwnedByYou;
import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;

import java.util.Set;

public interface GroupToPackService {
    /**
     * Assets if the group exits and if it is owned be the current user.
     *
     * @param id the unique identifier of the group, must not be null.
     * @param user the current user, must not be null.
     *
     * @throws GroupToPackNotFound if packing group with this id was not found.
     * @throws GroupToPackNotOwnedByYou if packing group is not owned by the current user.
     */
    void assertGroupExists(Long id, User user);

    /**
     * Creates a new GroupToPack entity and save it into the database using
     * a creation data transfer object. And returns it's id.
     *
     * @param groupToPackDto the creation data transfer object, must not be null.
     * @param currentUser the username of the current user, must not be null.
     *
     * @return the id of the packing group in the database.
     */
    Long createPackingGroup(GroupToPackCreateDto groupToPackDto, String currentUser);

    /**
     * Retrieves the GroupToPack entity associated with this id and returns it
     * as a response data transfer object.
     *
     * @param id a unique identifier of the packing group, must not be null.
     * @param currentUser the username of the current user, must not be null.
     *
     * @return the response data transfer object of the packing group associated with this id.
     *
     * @throws GroupToPackNotFound if packing group with this id was not found.
     * @throws GroupToPackNotOwnedByYou if packing group is not owned by the current user.
     */
    GroupToPackResponseDto getPackingGroupById(Long id, String currentUser);

    /**
     * Retrieves all GroupToPack entities of the current user and returns them as
     * an unmodifiable set of preview response data transfer objects.
     *
     * @param currentUser the username of the current user, must not be null.
     *
     * @return an unmodifiable set of response data transfer objects.
     */
    Set<GroupToPackPreviewDto> getAllPackingGroupPreviews(String currentUser);

    /**
     * Updates an existing GroupToPack entity associated with this id and saves it into the database,
     * using an update data transfer object.
     *
     * @param id a unique identifier of the packing group, must not be null.
     * @param groupToPackDto the update data transfer object, must not be null.
     * @param currentUser the username of the current user, must not be null.
     *
     * @throws GroupToPackNotFound if packing group with this id was not found.
     * @throws GroupToPackNotOwnedByYou if packing group is not owned by the current user.
     */
    void updatePackingGroup(Long id, GroupToPackUpdateDto groupToPackDto, String currentUser);

    /**
     * Deletes GroupToPack entity associated with this id from the database.
     *
     * @param id a unique identifier of the packing group, must not be null.
     *
     * @throws GroupToPackNotFound if packing group with this id was not found.
     * @throws GroupToPackNotOwnedByYou if packing group is not owned by the current user.
     */
    void deletePackingGroupById(Long id, String currentUser);
}
