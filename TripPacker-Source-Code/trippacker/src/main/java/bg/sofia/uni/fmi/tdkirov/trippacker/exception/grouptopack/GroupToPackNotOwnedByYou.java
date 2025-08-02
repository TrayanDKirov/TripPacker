package bg.sofia.uni.fmi.tdkirov.trippacker.exception.grouptopack;

import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;

public class GroupToPackNotOwnedByYou extends GroupToPackException {
    public GroupToPackNotOwnedByYou(Long id, User currentUser) {
        super(String.format("Group to pack with id %d is not owned by you (%d, %s). "
            , id, currentUser.getId(), currentUser.getUsername()));
    }

    public GroupToPackNotOwnedByYou(String message) {
        super(message);
    }

    public GroupToPackNotOwnedByYou(String message, Throwable cause) {
        super(message, cause);
    }
}
