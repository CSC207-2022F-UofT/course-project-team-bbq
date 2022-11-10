package dataAccess;

import entityRequestModels.FlashcardDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;
import entityRequestModels.UserDsRequestModel;

public class DBGateway implements IGeneralDataAccess {
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
    @Override
    public IFlashcardDataAccess getFlashcardAccess() {
        return this.flashcardGateway;
    }

    @Override
    public IFlashcardSetDataAccess getFlashcardSetAccess() {
        return this.flashcardSetGateway;
    }

    @Override
    public IUserDataAccess getUserAccess() {
        return this.userGateway;
    }
}
