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
    /**
     * Gets the flashcard set title.
     * @return the flashcard set title.
     */
    public String getTitle() {
        return title;
    }
    /**
     * Gets the flashcard set description.
     * @return the flashcard set description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Gets the flashcard privacy.
     * @return true if the flashcard set is private.
     */
    public Boolean getIsPrivate() {
        return isPrivate;
    }
    /**
     * Gets the flashcard set id.
     * @return the flashcard set id.
     */
    public int getFlashcardSetId(){
        return flashcardSetId;
    }
    /**
     * Gets the id of the user who created the flashcard set.
     * @return the if of the user who created the flashcard set.
     */
    public String getOwnerUsername(){
        return ownerUsername;
    }
    /**
     * Gets a list of all the flashcards in the flashcard set.
     * @return a list of all the flashcards in the flashcard set.
     */
    public List<Integer> getFlashcardIds(){
        return flashcardIds;
    }
    /**
     * Sets the flashcard set title.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Sets the flashcard set description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Sets the flashcard set id.
     */
    public void setFlashcardSetId(int flashcardSetId) {
        this.flashcardSetId = flashcardSetId;
    }
}
