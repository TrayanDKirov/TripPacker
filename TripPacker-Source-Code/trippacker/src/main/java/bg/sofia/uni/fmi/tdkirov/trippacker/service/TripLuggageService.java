package bg.sofia.uni.fmi.tdkirov.trippacker.service;

import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageCreateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggagePreviewDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageResponseDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.dto.tripluggage.TripLuggageUpdateDto;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.tripluggage.TripLuggageNotFound;
import bg.sofia.uni.fmi.tdkirov.trippacker.exception.tripluggage.TripLuggageNotOwnedByYou;

import java.util.Set;

public interface TripLuggageService {
    /**
     * Creates a new TripLuggage entity and save it into the database using
     * a creation data transfer object. And initializes all PackingGroups and PackingItems and
     * saves them into the database. And returns it's id.
     *
     * @param tripLuggageDto the creation data transfer object, must not be null.
     * @param currentUser the username of the current user, must not be null.
     *
     * @return the id of the trip luggage in the database.
     */
    Long createTripLuggage(TripLuggageCreateDto tripLuggageDto, String currentUser);

    /**
     * Retrieves the TripLuggage entity associated with this id and returns it
     * as a response data transfer object.
     *
     * @param id a unique identifier of the trip luggage, must not be null.
     * @param currentUser the username of the current user, must not be null.
     *
     * @return the response data transfer object associated with this id.
     *
     * @throws TripLuggageNotFound if TripLuggage with this id is not found.
     * @throws TripLuggageNotOwnedByYou if TripLuggage associated with this id is not owned by the current user.
     */
    TripLuggageResponseDto getTripLuggageById(Long id, String currentUser);

    /**
     * Retrieves all TripLuggage entities of the current user and returns them as
     * an unmodifiable set of response data transfer objects.
     *
     * @param currentUser the username of the current user, must not be null.
     *
     * @return an unmodifiable set of preview data transfer objects.
     */
    Set<TripLuggagePreviewDto> getAllTripLuggage(String currentUser);

    /**
     * Updates an existing TripLuggage entity associated with this id and saves it into the database,
     * using an update data transfer object.
     *
     * @param id a unique identifier of the trip luggage, must not be null.
     * @param tripLuggageDto the update data transfer object, must not be null.
     * @param currentUser the username of the current user, must not be null.
     *
     * @throws TripLuggageNotFound if TripLuggage with this id is not found.
     * @throws TripLuggageNotOwnedByYou if TripLuggage associated with this id is not owned by the current user.
     */
    void updateTripLuggage(Long id, TripLuggageUpdateDto tripLuggageDto, String currentUser);

    /**
     * Deletes TripLuggage entity associated with this id from the database.
     *
     * @param id a unique identifier of the trip luggage, must not be null.
     *
     * @throws TripLuggageNotFound if TripLuggage with this id is not found.
     * @throws TripLuggageNotOwnedByYou if TripLuggage associated with this id is not owned by the current user.
     */
    void deleteTripLuggageById(Long id, String currentUser);
}
