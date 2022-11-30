package delete_flashcard_use_case;

import data_access.*;
import delete_flashcard_use_case.FcRScreens.FcRController;
import delete_flashcard_use_case.FcRScreens.FcRResponsePresenter;
import frameworks_and_drivers.Screen;

import javax.swing.*;
import java.io.IOException;
/**
 * Main frame for flashcard removal.
 * @author Junyu Chen
 */
public class FcRMain extends Screen {
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
        FcROutputBoundary presenter = new FcRResponsePresenter();
        FcRInputBoundary interactor = new FcRInteractor(gateway,presenter);
        FcRController controller = new FcRController(interactor);
        this.setVisible(false);
        try {
            FcRResponseModel responseModel = controller.delete(flashcardSetId, flashcardId);
            JOptionPane.showMessageDialog(this,
                    responseModel.getTerm() + " deleted from " + responseModel.getCardSetName() +
                    " at " + responseModel.getDeleteDate());
            this.dispose();
        }catch (RuntimeException error){
            JOptionPane.showMessageDialog(this, "Deletion failed:\n" + error);
            this.dispose();
        }
    }
    public static void main(String[] args) {
        new FcRMain(0, 4);
    }

}
