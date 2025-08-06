package bg.sofia.uni.fmi.tdkirov.trippacker.exception.packinggroup;

import bg.sofia.uni.fmi.tdkirov.trippacker.exception.TripPackerException;

public class PackingGroupException extends TripPackerException {
    public PackingGroupException(String message) {
        super(message);
    }

    public PackingGroupException(String message, Throwable cause) {
        super(message, cause);
    }
}
