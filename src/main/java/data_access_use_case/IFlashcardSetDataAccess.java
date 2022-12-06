package data_access_use_case;

import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;

/**
 * User Data Access Interface.
 * Application Business Rules
 * @author Justin Li
 */

public interface IFlashcardSetDataAccess {

    /**
     * @param flashcardSetId the id of the flashcard set.
     * @return the flashcardSet object by the id given.
     */
    FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId);
    /**
     * @param flashcardSetId the id of the flashcard set.
     * @return the title and description of the flashcard set.
     */
    String[] getTitleAndDescription(int flashcardSetId);
    /**
     * @param flashcardSet the flashcard set object.
     */
    void editTitleAndDescription(FlashcardSetDsRequestModel flashcardSet);
    /**
     * @param flashcardSetId the id of the flashcard set.
     * @param flashcardId the id of the flashcard that will be added.
     */
    void saveFlashcardID(int flashcardSetId, int flashcardId);
    /**
     * @param flashcardSetId the id of the flashcard set.
     * @param flashcardId the id of the flashcard that will be removed.
     */
    void removeFlashcardId(int flashcardSetId, int flashcardId);
    /**
     * @param flashcardSetId the id of the flashcard set that will be deleted.
     */
    void deleteFlashcardSet(int flashcardSetId);
    /**
     * @param flashcardSet the flashcard set object.
     */
    int saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet);


}
