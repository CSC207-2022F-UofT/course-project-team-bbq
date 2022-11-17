package dataAccess;

import entityRequestModels.FlashcardSetDsRequestModel;

// use case layer
public interface IFlashcardSetDataAccess {

    FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId);

    String[] getTitleAndDescription(int flashcardSetId);

    void editTitleAndDescription(FlashcardSetDsRequestModel flashcardSet);

    void saveFlashcardID(int flashcardSetId, int flashcardId);

    void deleteFlashcardSet(int flashcardSetID);

    void saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet);


}
