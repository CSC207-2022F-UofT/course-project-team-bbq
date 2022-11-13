package entityRequestModels;

import java.util.List;

public class FlashcardSetDsRequestModel {
    private String title;
    private String description;
    private boolean isPrivate;

    private List<Integer> flashcardIds;
    private String ownerUsername;
    private int flashcardSetId;

    public FlashcardSetDsRequestModel(String title, String description, boolean isPrivate,
                                      int flashcardSetId, String ownerUsername,
                                      List<Integer> flashcards){
        this.title = title;
        this.description = description;
        this.isPrivate = isPrivate;
        this.flashcardSetId = flashcardSetId;
        this.ownerUsername = ownerUsername;
        this.flashcardIds = flashcards;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public List<Integer> getFlashcardIds() {
        return flashcardIds;
    }

    public void setFlashcardIds(List<Integer> flashcardIds) {
        this.flashcardIds = flashcardIds;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public int getFlashcardSetId() {
        return flashcardSetId;
    }

    public void setFlashcardSetId(int flashcardSetId) {
        this.flashcardSetId = flashcardSetId;
    }
}


