package bg.sofia.uni.fmi.tdkirov.trippacker.exception.packingitem;

public class PackingItemCreationException extends PackingItemException {
    public PackingItemCreationException() {
        super("PackingItem needs to have tripId or groupId. Only one of them needs " + System.lineSeparator() +
            "to be set and the other one to be null. Both must not be null. ");
    }

    public PackingItemCreationException(String message) {
        super(message);
    }

    public PackingItemCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
