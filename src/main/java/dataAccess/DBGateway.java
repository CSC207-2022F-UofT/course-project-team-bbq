package dataAccess;

import entityRequestModels.FlashcardDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;
import entityRequestModels.UserDsRequestModel;

public class DBGateway implements IFlashcardDataAccess, IFlashcardSetDataAccess, IUserDataAccess {
    private static String flashcardPath = "src/data/Flashcards.csv";
    private static String flashcardSetPath = "src/data/FlashCardSets.csv";
    private static String userPath = "src/data/Users.csv";


    @Override
    public FlashcardDsRequestModel getFlashcard(String flashcardID) {
        return null;
    }

    @Override
    public void saveFlashcard(FlashcardDsRequestModel flashcard) {

    }

    @Override
    public void editFlashcard(FlashcardDsRequestModel flashcard) {

    }

    @Override
    public void deleteFlashcard(String flashcardID) {

    }

    @Override
    public FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId) {
        return null;
    }

    @Override
    public String[] getTitleAndDescription(int flashcardSetId) {
        return new String[0];
    }

    @Override
    public void editTitleAndDescription(int flashcardSetId, String title, String description) {

    }

    @Override
    public void deleteFlashcardSet(int flashcardSetID) {

    }

    @Override
    public void saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet) {

    }

    @Override
    public UserDsRequestModel getUser(String username) {
        return null;
    }

    @Override
    public boolean existsByName(String username) {
        return false;
    }

    @Override
    public void saveUser(UserDsRequestModel user) {

    }
}
