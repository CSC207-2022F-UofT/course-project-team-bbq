package dataAccess;

import entityRequestModels.FlashcardDsRequestModel;

// use case layer

public interface IFlashcardDataAccess {

    FlashcardDsRequestModel getFlashcard(Integer flashcardID);

    void saveFlashcard(FlashcardDsRequestModel flashcard);

    void editFlashcard(FlashcardDsRequestModel flashcard);

    void deleteFlashcard(Integer flashcardID);

}
