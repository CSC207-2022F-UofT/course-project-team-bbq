package flashcardCreator;

import flashcardCreator.fcCScreens.FcCScreen;
import dataAccess.*;

import java.io.IOException;

public class FcCMain{
    public static void FcCMain(int flashcardSetId){
        DBGateway gateway;
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
