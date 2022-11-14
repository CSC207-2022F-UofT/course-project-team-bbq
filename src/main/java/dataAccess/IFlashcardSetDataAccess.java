package dataAccess;

import entityRequestModels.FlashcardDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;

// use case layer
public interface IFlashcardSetDataAccess {




    FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId);

    String[] getTitleAndDescription(int flashcardSetId);

    void editTitleAndDescription(FlashcardSetDsRequestModel flashcardSet);

    void saveFlashcardID(FlashcardSetDsRequestModel flashcardSet);

    void deleteFlashcardSet(int flashcardSetID);

    void saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet);

}
