package bg.sofia.uni.fmi.tdkirov.trippacker.exception.tripluggage;

import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;

public class TripLuggageNotFound extends TripLuggageException {
    public TripLuggageNotFound(Long id) {
        super("TripLuggage with id " + id + " was not found. ");
    }

    public TripLuggageNotFound(String message) {
        super(message);
    }

    public TripLuggageNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
