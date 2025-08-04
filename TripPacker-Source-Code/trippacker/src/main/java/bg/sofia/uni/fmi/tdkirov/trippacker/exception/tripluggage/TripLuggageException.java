package bg.sofia.uni.fmi.tdkirov.trippacker.exception.tripluggage;

import bg.sofia.uni.fmi.tdkirov.trippacker.exception.TripPackerException;

public class TripLuggageException extends TripPackerException {
    public TripLuggageException(String message) {
        super(message);
    }

    public TripLuggageException(String message, Throwable cause) {
        super(message, cause);
    }
}
