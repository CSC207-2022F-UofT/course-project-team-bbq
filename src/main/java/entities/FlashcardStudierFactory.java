package entities;

/**
 * A class which calls the Flashcard Studier constructor
 * <p>
 * Enterprise business rules
 * @author Lucas Prates
 */
public class FlashcardStudierFactory {

    /**
     * Creates a FlashcardStudier
     * @param title the title of the flashcard set
     * @param description the description of the flashcard set
     * @param isPrivate true if the flashcard set is private, false otherwise
     * @param flashcardSetId the flashcard set ID
     * @param ownerUsername the username of the user who created this flashcard set
     * @param defaultDisplay true if the user wishes to display flashcard 'terms' by default,
     *                      false if the user wishes to display flashcard 'definitions' by default
     * @return the FlashcardStudier
     */
    public FlashcardStudier create(String title, String description, boolean isPrivate,
                                   int flashcardSetId, String ownerUsername, boolean defaultDisplay){
        return new FlashcardStudier(title, description, isPrivate, flashcardSetId,
                ownerUsername, defaultDisplay);
    }
}
