package entities;

import java.util.List;

public class FlashcardSetFactory {
    public FlashcardSet create(String title, String description, boolean isPrivate,
                        int flashcardSetId, String ownerUsername) {
        return new FlashcardSet(title, description, isPrivate, flashcardSetId, ownerUsername);
    }
}
