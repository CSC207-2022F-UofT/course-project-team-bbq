package FlashcardCreator;

import FlashcardCreator.FcCScreens.FcCScreen;
import dataAccess.*;

import java.io.IOException;

public class FcCMain{
    public static void main(String[] args){
        DBGateway gateway;
        //for test purposes.
        int flashcardSetId = 0;
        try{
            IFlashcardSetDataAccess flashcardSetDataAccess = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
            IFlashcardDataAccess flashcardDataAccess = new FlashcardDataAccess(DBGateway.getFlashcardPath());
            gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);
        }
        catch (IOException e){
            throw new RuntimeException("Could not access files.");
        }
        FcCPresenter presenter = new FcCResponseFormatter();
        FcCInputBoundary interactor = new FcCInterator(gateway,presenter);
        FcCController controller = new FcCController(interactor, flashcardSetId);
        new FcCScreen(controller);

    }

}
