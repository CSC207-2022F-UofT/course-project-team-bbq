package entities;
import java.util.*;

public class FlashcardSet {
    private static int count;
    private String title;
    private String description;
    private boolean isPrivate;
    private List<Flashcard> flashcards;

    private String ownerId;
    private int flashcardSetId;

    public FlashcardSet(String title, String description, boolean isPrivate){
        this.title = title;
        this.description = description;
        this.isPrivate = isPrivate;
        this.flashcards = new ArrayList<>();
        this.flashcardSetId = ++count;
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

    public String getOwnerId() {
        return ownerId;
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

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setFlashcards(ArrayList<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }
}
