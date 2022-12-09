package frameworks_and_drivers.screens;

import data_access_use_case.*;
import delete_flashcard_use_case.DeleteFlashcardInputBoundary;
import delete_flashcard_use_case.DeleteFlashcardInteractor;
import delete_flashcard_use_case.DeleteFlashcardOutputBoundary;
import frameworks_and_drivers.components.DeleteFlashcardPanel;
import interface_adapters.controllers.DeleteFlashcardController;
import interface_adapters.presenters.DeleteFlashcardPresenter;
import frameworks_and_drivers.database.DBGateway;
import frameworks_and_drivers.database.FlashcardDataAccess;
import frameworks_and_drivers.database.FlashcardSetDataAccess;
import java.io.IOException;
/**
 * Main frame for flashcard removal.
 * @author Junyu Chen
 */
public class DeleteFlashcardScreen extends Screen {
    /**
     * Creating main frame for deleting flashcard.
     * @param flashcardSetId id of flashcard set containing the flashcard
     * @param flashcardId id of the flashcard to delete
     */
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
        this.setTitle("Delete Flashcard");
        this.add(new DeleteFlashcardPanel(controller,this, flashcardId, flashcardSetId,
                gateway.getFlashcard(flashcardId).getTerm(), gateway.getFlashcardSet(flashcardSetId).getTitle()));
        this.setSize(500, 200);
        this.setVisible(true);
    }

    //To run flashcard creation screen.
    public static void main(String[] args) {
        int flashcardSetId = 0;
        int flashcardId = 38;
        new DeleteFlashcardScreen(flashcardSetId, flashcardId);
    }

}
