package dataAccess;

import entityRequestModels.CommonUserDsRequestModel;
import entityRequestModels.FlashcardDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;
import entityRequestModels.UserDsRequestModel;

public class DBGateway {
    private static final String flashcardPath = "src/data/Flashcards.csv";
    private static final String flashcardSetPath = "src/data/FlashcardSets.csv";
    private static final String userPath = "src/data/Users.csv";

    IFlashcardDataAccess flashcardGateway;
    IFlashcardSetDataAccess flashcardSetGateway;
    IUserDataAccess userGateway;

    public DBGateway(IFlashcardDataAccess flashcardGateway,
                     IFlashcardSetDataAccess flashcardSetGateway,
                     IUserDataAccess userGateway){
        this.flashcardGateway = flashcardGateway;
        this.flashcardSetGateway = flashcardSetGateway;
        this.userGateway = userGateway;
    }

    public static String getFlashcardPath() {
        return flashcardPath;
    }

    public static String getFlashcardSetPath() {
        return flashcardSetPath;
    }

    public static String getUserPath() {
        return userPath;
    }

    public FlashcardDsRequestModel getFlashcard(int flashcardId){
        return this.flashcardGateway.getFlashcard(flashcardId);
    }

    public FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId){
        return this.flashcardSetGateway.getFlashcardSet(flashcardSetId);
    }

    public CommonUserDsRequestModel getCommonUser(String username){
        return (CommonUserDsRequestModel)this.userGateway.getUser(username);
    }

    public IFlashcardDataAccess getFlashcardGateway() {
        return flashcardGateway;
    }

    public IFlashcardSetDataAccess getFlashcardSetGateway() {
        return flashcardSetGateway;
    }

    public IUserDataAccess getUserGateway() {
        return userGateway;
    }

    public int saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet) {
        this.userGateway.saveFlashcardSetID(flashcardSet.getOwnerUsername(), flashcardSet.getFlashcardSetId());
        return this.flashcardSetGateway.saveFlashcardSet(flashcardSet);
    }

    public void deleteFlashcardSet(int flashcardSetID) {
        // need to delete ID from user database too!!!
        this.flashcardSetGateway.deleteFlashcardSet(flashcardSetID);
    }

    public int saveFlashcard(FlashcardDsRequestModel flashcard) {
        this.flashcardSetGateway.saveFlashcardID(flashcard.getBelongsToId(), flashcard.getFlashcardId());
        return this.flashcardGateway.saveFlashcard(flashcard);
    }

    public void deleteFlashcard(Integer flashcardID) {
        // need to delete from flashcard set database as well
        this.flashcardGateway.deleteFlashcard(flashcardID);
    }
}
