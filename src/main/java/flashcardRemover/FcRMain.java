package flashcardRemover;

import dataAccess.*;
import flashcardRemover.fcRScreens.FcRFailureScreen;
import flashcardRemover.fcRScreens.FcRSuccessScreen;
import quizUseCase.screens.Screen;

import java.io.IOException;

public class FcRMain{
    public FcRMain(int flashcardSetId, int flashcardId){
        DBGateway gateway;
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

        try {
            FcRResponseModel responseModel = controller.delete(flashcardSetId, flashcardId);
            new FcRSuccessScreen(responseModel);
        }catch (RuntimeException error){
            new FcRFailureScreen(error.toString());
        }
    }

}
