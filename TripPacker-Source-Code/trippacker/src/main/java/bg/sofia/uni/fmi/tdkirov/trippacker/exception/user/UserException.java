package bg.sofia.uni.fmi.tdkirov.trippacker.exception.user;

import bg.sofia.uni.fmi.tdkirov.trippacker.exception.TripPackerException;

public class UserException extends TripPackerException {
    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
