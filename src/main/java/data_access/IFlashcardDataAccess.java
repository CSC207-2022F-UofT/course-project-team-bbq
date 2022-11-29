package data_access;

import data_access.entity_request_models.FlashcardDsRequestModel;

/**
 * User Data Access Interface.
 * Application Business Rules
 * @author Justin Li
 */

public interface IFlashcardDataAccess {
    /**
     * @param flashcardId the id of the flashcard.
     * @return the flashcard object with the given id.
     */
    FlashcardDsRequestModel getFlashcard(Integer flashcardId);
    /**
     * @param flashcard the flashcard object that will be created.
     */
    int saveFlashcard(FlashcardDsRequestModel flashcard);
    /**
     * @param flashcard the flashcard object that will be edited.
     */
    void editFlashcard(FlashcardDsRequestModel flashcard);
    /**
     * @param flashcardId the id of the flashcard that will be deleted.
     */
    void deleteFlashcard(Integer flashcardId);

}
