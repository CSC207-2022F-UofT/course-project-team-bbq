package dataAccess;

import entityRequestModels.FlashcardDsRequestModel;

// use case layer

public interface IFlashcardDataAccess {

    FlashcardDsRequestModel getFlashcard(Integer flashcardID);

    int saveFlashcard(FlashcardDsRequestModel flashcard);

    void editFlashcard(FlashcardDsRequestModel flashcard);

    void deleteFlashcard(Integer flashcardID);
}
