package entities;

public class FlashcardSetFactory {
    /**
     * Creates a new flashcard set
     * @param title the title of the flashcard set
     * @param description the description of the flashcard set
     * @param isPrivate true if the flashcard set is not publicly accessible
     * @param flashcardSetId the flashcard set's unique id
     * @param ownerUsername the username of the user who owns this flashcard set
     * @return the new flashcard set
     */
    public FlashcardSet create(String title, String description, boolean isPrivate,
                        int flashcardSetId, String ownerUsername) {
        return new FlashcardSet(title, description, isPrivate, flashcardSetId, ownerUsername);
    }
}
