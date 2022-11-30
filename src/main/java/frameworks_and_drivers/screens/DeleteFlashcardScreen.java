package frameworks_and_drivers.screens;

import data_access_use_case.*;
import delete_flashcard_use_case.DeleteFlashcardInputBoundary;
import delete_flashcard_use_case.DeleteFlashcardInteractor;
import delete_flashcard_use_case.DeleteFlashcardOutputBoundary;
import delete_flashcard_use_case.DeleteFlashcardResponseModel;
import interface_adapters.controllers.DeleteFlashcardController;
import interface_adapters.presenters.DeleteFlashcardPresenter;
import frameworks_and_drivers.database.DBGateway;
import frameworks_and_drivers.database.FlashcardDataAccess;
import frameworks_and_drivers.database.FlashcardSetDataAccess;

import javax.swing.*;
import java.io.IOException;
/**
 * Main frame for flashcard removal.
 * @author Junyu Chen
 */
public class DeleteFlashcardScreen extends Screen {
    public DeleteFlashcardScreen(int flashcardSetId, int flashcardId){
        DBGateway gateway;
        try{
            IFlashcardSetDataAccess flashcardSetDataAccess = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
            IFlashcardDataAccess flashcardDataAccess = new FlashcardDataAccess(DBGateway.getFlashcardPath());
            gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);
        }
        catch (IOException e){
            throw new RuntimeException("Could not access files.");
        }
        DeleteFlashcardOutputBoundary presenter = new DeleteFlashcardPresenter();
        DeleteFlashcardInputBoundary interactor = new DeleteFlashcardInteractor(gateway,presenter);
        DeleteFlashcardController controller = new DeleteFlashcardController(interactor);
        this.setVisible(false);
        try {
            DeleteFlashcardResponseModel responseModel = controller.delete(flashcardSetId, flashcardId);
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
        new DeleteFlashcardScreen(0, 4);
    }

}
