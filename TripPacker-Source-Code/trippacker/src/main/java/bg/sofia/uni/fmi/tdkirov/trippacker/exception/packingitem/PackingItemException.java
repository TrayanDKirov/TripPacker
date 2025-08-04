package bg.sofia.uni.fmi.tdkirov.trippacker.exception.packingitem;

import bg.sofia.uni.fmi.tdkirov.trippacker.exception.TripPackerException;

public class PackingItemException extends TripPackerException {
    public PackingItemException(String message) {
        super(message);
    }

    public PackingItemException(String message, Throwable cause) {
        super(message, cause);
    }
}
