package bg.sofia.uni.fmi.tdkirov.trippacker.exception.packinggroup;

public class PackingGroupException extends RuntimeException {
    public PackingGroupException(String message) {
        super(message);
    }

    public PackingGroupException(String message, Throwable cause) {
        super(message, cause);
    }
}
