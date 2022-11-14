package dataAccess;

import entityRequestModels.FlashcardDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;
import entityRequestModels.UserDsRequestModel;

public class DBGateway {
    private static String flashcardPath = "src/data/Flashcards.csv";
    private static String flashcardSetPath = "src/data/FlashcardSets.csv";
    private static String userPath = "src/data/Users.csv";

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
        return DBGateway.flashcardPath;
    }

    public static String getFlashcardSetPath() {
        return DBGateway.flashcardSetPath;
    }

    public FlashcardDsRequestModel getFlashcard(int flashcardId){
        return this.flashcardGateway.getFlashcard(flashcardId);
    }

    public FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId){
        return this.flashcardSetGateway.getFlashcardSet(flashcardSetId);
    }


}
