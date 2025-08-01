package bg.sofia.uni.fmi.tdkirov.trippacker.exception.itemtopack;

public class ItemToPackCreateDtoException extends ItemToPackException {
    public ItemToPackCreateDtoException() { // TODO Check this constructor
        super("ItemToPack creation data transfer object has no trip id or group id. ");
    }

    public ItemToPackCreateDtoException(String message) {
        super(message);
    }

    public ItemToPackCreateDtoException(String message, Throwable cause) {
        super(message, cause);
    }
}
