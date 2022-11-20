package dataAccess;

import entityRequestModels.CommonUserDsRequestModel;
import entityRequestModels.FlashcardDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;
import entityRequestModels.UserDsRequestModel;

import java.io.IOException;
import java.util.ArrayList;

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

    public static void main(String[] args) throws IOException {
        IUserDataAccess iUserDataAccess = new CommonUserDataAccess(getUserPath());
        DBGateway dbGateway = new DBGateway(null, null, iUserDataAccess);
        dbGateway.userGateway.saveUser(new CommonUserDsRequestModel("lucas","r6tygh",false, new ArrayList<Integer>()));
    }
}
