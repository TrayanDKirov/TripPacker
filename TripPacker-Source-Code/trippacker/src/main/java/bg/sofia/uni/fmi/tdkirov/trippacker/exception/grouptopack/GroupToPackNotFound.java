package bg.sofia.uni.fmi.tdkirov.trippacker.exception.grouptopack;

public class GroupToPackNotFound extends GroupToPackException {
    public GroupToPackNotFound(Long id) {
        super("Group to pack with id " + id + " was not found. ");
    }

    public GroupToPackNotFound(String message) {
        super(message);
    }

    public GroupToPackNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
