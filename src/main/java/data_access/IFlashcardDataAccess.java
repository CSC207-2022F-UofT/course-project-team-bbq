package data_access;

import data_access.entity_request_models.FlashcardDsRequestModel;

// use case layer

public interface    IFlashcardDataAccess {

    FlashcardDsRequestModel getFlashcard(Integer flashcardID);

    int saveFlashcard(FlashcardDsRequestModel flashcard);

    void editFlashcard(FlashcardDsRequestModel flashcard);

    void deleteFlashcard(Integer flashcardID);

}
