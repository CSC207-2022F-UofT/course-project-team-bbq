package entities;
import java.util.*;

public class FlashcardSet {
    private String title;
    private String description;
    private boolean isPrivate;
    private final List<Flashcard> flashcards;

    private final String ownerUsername;
    private final int flashcardSetId;

    /**
     * Creates a new flashcard set
     * @param title the title of the flashcard set
     * @param description the description of the flashcard set
     * @param isPrivate true if the flashcard set is not publicly accessible
     * @param flashcardSetId the flashcard set's unique id
     * @param ownerUsername the username of the user who owns this flashcard set
     */
    public FlashcardSet(String title, String description, boolean isPrivate,
                        int flashcardSetId, String ownerUsername){
        this.title = title;
        this.description = description;
        this.isPrivate = isPrivate;
        this.flashcardSetId = flashcardSetId;
        this.ownerUsername = ownerUsername;
        this.flashcards = new ArrayList<>();
    }

    /**
     * @return the flashcard set's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the flashcard set's description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return the flashcard set's unique id
     */
    public int getFlashcardSetId() {
        return flashcardSetId;
    }

    /**
     * @return the username of the user who owns this flashcard set
     */
    public String getOwnerUsername() {
        return ownerUsername;
    }

    /**
     * @return the list of flashcard's in this flashcard set
     */
    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    /**
     * @return true if the flashcard set is private, false otherwise
     */
    public boolean getIsPrivate(){
        return isPrivate;
    }

    /**
     * changes the flashcard set's title
     * @param title the new title of the flashcard set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * changes the flashcard set's description
     * @param description the new description of the flashcard set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * change the privacy of the flashcard set
     * @param aPrivate the new privacy setting for the flashcard set
     */
    public void setIsPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    /**
     * adds a flashcard to the flashcard set
     * @param flashcard the new flashcard to be added to the flashcard set
     */
    public void addFlashcard(Flashcard flashcard) {
        this.flashcards.add(flashcard);
    }
}