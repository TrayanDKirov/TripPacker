package bg.sofia.uni.fmi.tdkirov.trippacker.exception.packingitem;

import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;

public class PackingItemNotOwnedByYou extends PackingItemException {
    public PackingItemNotOwnedByYou(Long id, User currentUser) {
        super(String.format("PackingItem with id %d is not owned by you (%d, %s). ",
            id, currentUser.getId(), currentUser.getUsername()));
    }

    public PackingItemNotOwnedByYou(String message) {
        super(message);
    }

    public PackingItemNotOwnedByYou(String message, Throwable cause) {
        super(message, cause);
    }
}
