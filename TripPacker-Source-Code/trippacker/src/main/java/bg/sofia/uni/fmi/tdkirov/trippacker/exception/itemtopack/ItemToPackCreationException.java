package bg.sofia.uni.fmi.tdkirov.trippacker.exception.itemtopack;

public class ItemToPackCreationException extends ItemToPackException {
    public ItemToPackCreationException() { // TODO Check this constructor
        super("ItemToPack creation data transfer object has no trip id or group id. ");
    }

    public ItemToPackCreationException(String message) {
        super(message);
    }

    public ItemToPackCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
