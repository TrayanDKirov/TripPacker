package bg.sofia.uni.fmi.tdkirov.trippacker.exception.tripluggage;

import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;

public class TripLuggageNotOwnedByYou extends TripLuggageException {
    public TripLuggageNotOwnedByYou(Long id, User currentUser) {
        super(String.format("ItemToPack with id %d is not owned by you (%d, %s). ",
            id, currentUser.getId(), currentUser.getUsername()));
    }

    public TripLuggageNotOwnedByYou(String message) {
        super(message);
    }

    public TripLuggageNotOwnedByYou(String message, Throwable cause) {
        super(message, cause);
    }
}
