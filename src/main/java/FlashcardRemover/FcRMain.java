package FlashcardRemover;

import FlashcardCreator.FcCScreens.FcCScreen;
import dataAccess.*;

import java.io.IOException;

public class FcRMain{
    public static void main(String[] args){
        DBGateway gateway;
        //for test purposes.
        int flashcardSetId = 0;
        int flashcardId = 4;
        try{
            IFlashcardSetDataAccess flashcardSetDataAccess = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
            IFlashcardDataAccess flashcardDataAccess = new FlashcardDataAccess(DBGateway.getFlashcardPath());
            gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);
        }
        catch (IOException e){
            throw new RuntimeException("Could not access files.");
        }
        FcRPresenter presenter = new FcRResponseFormatter();
        FcRInputBoundary interactor = new FcRInterator(gateway,presenter);
        FcRController controller = new FcRController(interactor, flashcardSetId);

        FcRResponseModel response = controller.delete(flashcardSetId, flashcardId);
        System.out.println(response.getTerm()+" deleted at " + response.getDeleteDate());
    }

}
