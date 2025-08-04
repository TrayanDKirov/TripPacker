package bg.sofia.uni.fmi.tdkirov.trippacker.exception.itemtopack;

public class ItemToPackCreationException extends ItemToPackException {
    public ItemToPackCreationException() {
        super("ItemToPack needs to have tripId or groupId. Only one of them needs " + System.lineSeparator() +
            "to be set and the other one to be null. Both must not be null. ");
    }

    public ItemToPackCreationException(String message) {
        super(message);
    }

    public ItemToPackCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
