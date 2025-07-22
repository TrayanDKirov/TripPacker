 package bg.sofia.uni.fmi.tdkirov.trippacker.exception.itemtopack;

import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;

public class ItemToPackNotOwnedByYou extends ItemToPackException {
    public ItemToPackNotOwnedByYou(Long id, User currentUser) {
        super(String.format("ItemToPack with id %d is not owned by you (%d, %s). ",
            id, currentUser.getId(), currentUser.getUsername()));
    }

    public ItemToPackNotOwnedByYou(String message) {
        super(message);
    }

    public ItemToPackNotOwnedByYou(String message, Throwable cause) {
        super(message);
    }
}
