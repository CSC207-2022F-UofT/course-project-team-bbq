package entities;

import java.util.Map;

public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String username, String password, boolean isAdmin, Map<Integer, String[]> flashcardSets) {
        return null;
    }
}
