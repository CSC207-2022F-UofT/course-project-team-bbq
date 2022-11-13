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
                        int flashcardSetId, String ownerUsername, List<Flashcard> flashcards){
        this.title = title;
        this.description = description;
        this.isPrivate = isPrivate;
        this.flashcardSetId = flashcardSetId;
        this.ownerUsername = ownerUsername;
        this.flashcards = flashcards;
    }

    @Override
    public String toString() {
        String privacy = "Private";
        if (!this.isPrivate) {
            privacy = "Public";
        }
        String line0 = "==========================" + "\n";
        String line1 = "(" + privacy + ") FlashcardSet #" + this.flashcardSetId + "\n";
        String line2 = "--------------------------" + "\n";
        String line3 = "Title: " + this.title + "\n";
        String line4 = "Owner: " + this.ownerUsername + "\n";
        String line5 = "Number of Flashcards: " + this.flashcards.size() + "\n";
        return line0 + line1 + line2 + line3 + line4 + line5 + line0;
    };

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

    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }
}
