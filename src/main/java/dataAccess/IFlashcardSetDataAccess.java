package dataAccess;

import entityRequestModels.FlashcardDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;

// use case layer
public interface IFlashcardSetDataAccess {




    FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId);

    String[] getTitleAndDescription(int flashcardSetId);

    void editTitleAndDescription(FlashcardSetDsRequestModel flashcardSet);

    void saveFlashcardID(int flashcardSetId, int flashcardId);

    void removeFlashcardId(int flashcardSetId, int flashcardId);

    void deleteFlashcardSet(int flashcardSetID);

    int saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet);

}
