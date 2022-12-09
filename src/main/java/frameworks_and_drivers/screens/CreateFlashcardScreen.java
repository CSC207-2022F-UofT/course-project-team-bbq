package frameworks_and_drivers.screens;

import create_flashcard_use_case.CreateFlashcardInputBoundary;
import create_flashcard_use_case.CreateFlashcardInteractor;
import create_flashcard_use_case.CreateFlashcardOutputBoundary;
import frameworks_and_drivers.database.DBGateway;
import frameworks_and_drivers.database.FlashcardDataAccess;
import frameworks_and_drivers.database.FlashcardSetDataAccess;
import interface_adapters.controllers.CreateFlashcardController;
import interface_adapters.presenters.CreateFlashcardPresenter;
import frameworks_and_drivers.components.CreateFlashcardPanel;
import data_access_use_case.*;
import java.io.IOException;

/**
 * Main frame for flashcard Creation.
 * @author Junyu Chen
 */
public class CreateFlashcardScreen extends Screen {
    /**
     * Create main frame for the flashcard creator
     * @param flashcardSetId id of the flashcard set which the flashcard will be stored in
     */
    public CreateFlashcardScreen(int flashcardSetId){
        DBGateway gateway;
        try{
            IFlashcardSetDataAccess flashcardSetDataAccess = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
            IFlashcardDataAccess flashcardDataAccess = new FlashcardDataAccess(DBGateway.getFlashcardPath());
            gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);
        }
        catch (IOException e){
            throw new RuntimeException("Could not access files.");
        }
        CreateFlashcardOutputBoundary presenter = new CreateFlashcardPresenter();
        CreateFlashcardInputBoundary interactor = new CreateFlashcardInteractor(gateway,presenter);
        CreateFlashcardController controller = new CreateFlashcardController(interactor, flashcardSetId);
        CreateFlashcardPanel createFlashcardPanel = new CreateFlashcardPanel(controller, this);
        this.setTitle("Create Flashcard");
        this.add(createFlashcardPanel);
        setLocationRelativeTo(null);
        this.setSize(1000, 500);
        this.setVisible(true);
    }
    //To run flashcard deletion screen.
    public static void main(String[] args) {
        int flashcardSetId = 0;
        new CreateFlashcardScreen(flashcardSetId);
    }

}
