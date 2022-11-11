package dataAccess;

import entityRequestModels.FlashcardSetDsRequestModel;

// use case layer
public interface IFlashcardSetDataAccess {




    FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId);

    String[] getTitleAndDescription(int flashcardSetId);

    void editTitleAndDescription(int flashcardSetId, String title, String description);

    void deleteFlashcardSet(int flashcardSetID);

    void saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet);

}
