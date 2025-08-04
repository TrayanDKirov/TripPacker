package bg.sofia.uni.fmi.tdkirov.trippacker.exception.packingitem;

public class PackingItemNotFound extends PackingItemException {
    public PackingItemNotFound(Long id) {
        super("Packing item with id " + id + " was not found. ");
    }

    public PackingItemNotFound(String message) {
        super(message);
    }

    public PackingItemNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
