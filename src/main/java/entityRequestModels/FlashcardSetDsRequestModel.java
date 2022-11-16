package entityRequestModels;

import java.time.LocalDateTime;
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
                                      List<Integer> flashcardIds){
        this.title = title;
        this.description = description;
        this.isPrivate = isPrivate;
        this.flashcardSetId = flashcardSetId;
        this.ownerUsername = ownerUsername;
        this.flashcardIds = flashcardIds;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public int getFlashcardSetId(){
        return flashcardSetId;
    }

    public String getOwnerUsername(){
        return ownerUsername;
    }

    public List<Integer> getFlashcardIds(){
        return flashcardIds;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrivacy(Boolean privacy) {
        this.isPrivate = privacy;
    }

}