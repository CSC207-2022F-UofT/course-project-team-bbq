package dataAccess;

import entityRequestModels.CommonUserDsRequestModel;
import entityRequestModels.FlashcardDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;

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
        return this.userGateway.getUser(username);
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

    public String[] getTitleAndDescription(int flashcardSetId) {
        return this.flashcardSetGateway.getTitleAndDescription(flashcardSetId);
    }

    public boolean existsByName(String username){
        return this.userGateway.existsByName(username);
    }

    public void saveUser(CommonUserDsRequestModel user) {
        this.userGateway.saveUser(user);
    }


    public int saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet) {
        this.userGateway.saveFlashcardSetID(flashcardSet.getOwnerUsername(), flashcardSet.getFlashcardSetId());
        return this.flashcardSetGateway.saveFlashcardSet(flashcardSet);
    }

    public void deleteFlashcardSet(String ownerUsername, int flashcardSetID) {
        this.userGateway.deleteFlashcardSetID(ownerUsername, flashcardSetID);
        this.flashcardSetGateway.deleteFlashcardSet(flashcardSetID);
    }

    public int saveFlashcard(FlashcardDsRequestModel flashcard) {
        this.flashcardSetGateway.saveFlashcardID(flashcard.getBelongsToId(), flashcard.getFlashcardId());
        return this.flashcardGateway.saveFlashcard(flashcard);
    }

    public void deleteFlashcard(int flashcardSetId, int flashcardId) {
        this.flashcardSetGateway.removeFlashcardId(flashcardSetId, flashcardId);
        this.flashcardGateway.deleteFlashcard(flashcardId);
    }
}
