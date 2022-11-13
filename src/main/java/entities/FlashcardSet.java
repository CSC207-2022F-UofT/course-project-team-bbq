package entities;
import java.util.*;

public class FlashcardSet {
    private String title;
    private String description;
    private boolean isPrivate;
    private List<Flashcard> flashcards;

    private String ownerUsername;
    private int flashcardSetId;

    public FlashcardSet(String title, String description, boolean isPrivate,
                        int flashcardSetId, String ownerUsername){
        this.title = title;
        this.description = description;
        this.isPrivate = isPrivate;
        this.flashcardSetId = flashcardSetId;
        this.ownerUsername = ownerUsername;
        this.flashcards = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return this.description;
    }

    public int getFlashcardSetId() {
        return flashcardSetId;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    public boolean getIsPrivate(){
        return isPrivate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void addFlashcard(Flashcard flashcard) {
        this.flashcards.add(flashcard);
    }
}
