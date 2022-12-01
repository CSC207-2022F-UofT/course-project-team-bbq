package data_access_use_case.entity_request_models;

import java.util.List;
/**
 * Flashcard Set Request Model.
 * Application Business Rules
 * @author Justin Li
 */

public class FlashcardSetDsRequestModel {
    private String title;
    private String description;
    private final boolean isPrivate;

    private final List<Integer> flashcardIds;
    private final String ownerUsername;
    private int flashcardSetId;

    /**
     * Creates a flashcard response model based on the following parameters.
     * @param title the flashcard set title
     * @param description the flashcard set description.
     * @param isPrivate true if the flashcard set is private.
     * @param flashcardSetId the flashcard set id
     * @param ownerUsername the id of the user that created the flashcard set.
     */
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

    public void setFlashcardSetId(int flashcardSetId) {
        this.flashcardSetId = flashcardSetId;
    }
}
