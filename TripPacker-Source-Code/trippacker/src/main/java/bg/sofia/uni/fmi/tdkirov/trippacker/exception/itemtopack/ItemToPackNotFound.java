package bg.sofia.uni.fmi.tdkirov.trippacker.exception.itemtopack;

public class ItemToPackNotFound extends ItemToPackException {
    public ItemToPackNotFound(Long id) {
        super("ItemToPack with this id " + id + " was not found. ");
    }

    public ItemToPackNotFound(String message) {
        super(message);
    }

    public ItemToPackNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
