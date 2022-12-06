package data_access_use_case.entity_request_models;

import java.time.LocalDateTime;
/**
 * Flashcard Request Model.
 * Application Business Rules
 * @author Justin Li
 */

public class FlashcardDsRequestModel {
    private String term;
    private String definition;
    private final LocalDateTime creationDate;
    private int flashcardId;
    private final int belongsToId;

    /**
     * Creates a flashcard response model based on the following parameters.
     * @param term the flashcard term
     * @param definition the flashcard definition for the term
     * @param creationDate the time the flashcard is created (time formatted in LocalDateTime)
     * @param flashcardId the flashcard id
     * @param belongsToId the id of the flashcard set that the flashcard is in.
     */
    public FlashcardDsRequestModel(String term, String definition, LocalDateTime creationDate,
                                   int flashcardId, int belongsToId){
        this.term = term;
        this.definition = definition;
        this.creationDate = creationDate;
        this.flashcardId = flashcardId;
        this.belongsToId = belongsToId;
    }
    /**
     * Gets the flashcard term.
     * @return the flashcard term
     */
    public String getTerm() {
        return term;
    }
    /**
     * Gets the flashcard definition.
     * @return the flashcard definition.
     */
    public String getDefinition() {
        return definition;
    }
    /**
     * Gets the flashcard creation date.
     * @return the flashcard creation date in LocalDateTime formatting.
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    /**
     * Gets the flashcard id.
     * @return the flashcard id.
     */
    public int getFlashcardId(){
        return flashcardId;
    }
    /**
     * Gets the id of the flashcard set that the flashcard belongs to.
     * @return the id of the flashcard set that the flashcard belongs to.
     */
    public int getBelongsToId(){
        return belongsToId;
    }
    /**
     * Sets the term.
     */
    public void setTerm(String term) {this.term = term;}
    /**
     * Sets the definition.
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }
    /**
     * Sets the flashcard id.
     */
    public void setFlashcardId(int flashcardId) {
        this.flashcardId = flashcardId;
    }
}
