package bg.sofia.uni.fmi.tdkirov.trippacker.exception.itemtopack;

import bg.sofia.uni.fmi.tdkirov.trippacker.exception.TripPackerException;

public class ItemToPackException extends TripPackerException {
    public ItemToPackException(String message) {
        super(message);
    }

    public ItemToPackException(String message, Throwable cause) {
        super(message, cause);
    }
}
