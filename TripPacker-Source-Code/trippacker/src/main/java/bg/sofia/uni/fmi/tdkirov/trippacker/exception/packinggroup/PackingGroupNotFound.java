package bg.sofia.uni.fmi.tdkirov.trippacker.exception.packinggroup;

public class PackingGroupNotFound extends PackingGroupException {
    public PackingGroupNotFound(Long id) {
        super("Packing group with id " + id + " was not found. ");
    }

    public PackingGroupNotFound(String message) {
        super(message);
    }

    public PackingGroupNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
