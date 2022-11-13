package dataAccess;

import entityRequestModels.FlashcardDsRequestModel;

// use case layer

public interface IFlashcardDataAccess {

    FlashcardDsRequestModel getFlashcard(String flashcardID);

    void saveFlashcard(FlashcardDsRequestModel flashcard);

    void editFlashcard(FlashcardDsRequestModel flashcard);

    void deleteFlashcard(String flashcardID);
}
