package bg.sofia.uni.fmi.tdkirov.trippacker.exception.grouptopack;

public class GroupToPackException extends RuntimeException {
    public GroupToPackException(String message) {
        super(message);
    }

    public GroupToPackException(String message, Throwable cause) {
        super(message, cause);
    }
}
