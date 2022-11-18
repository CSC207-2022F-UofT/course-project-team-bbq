package entities;

import entities.FlashcardStudier;

public class FlashcardStudierFactory {

    public FlashcardStudier create(String title, String description, boolean isPrivate,
                                   int flashcardSetId, String ownerUsername, boolean defaultDisplay){
        return new FlashcardStudier(title, description, isPrivate, flashcardSetId,
                ownerUsername, defaultDisplay);
    }
}
