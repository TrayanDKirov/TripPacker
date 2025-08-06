package bg.sofia.uni.fmi.tdkirov.trippacker.exception.packinggroup;

import bg.sofia.uni.fmi.tdkirov.trippacker.model.User;

public class PackingGroupNotOwnedByYou extends PackingGroupException {
    public PackingGroupNotOwnedByYou(Long id, User currentUser) {
        super(String.format("PackingGroup with id %d is not owned by you (%d, %s). ",
            id, currentUser.getId(), currentUser.getUsername()));
    }

    public PackingGroupNotOwnedByYou(String message) {
        super(message);
    }

    public PackingGroupNotOwnedByYou(String message, Throwable cause) {
        super(message, cause);
    }
}
