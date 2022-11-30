package frameworks_and_drivers.screens;

import edit_flashcard_use_case.EditFlashcardInputBoundary;
import edit_flashcard_use_case.EditFlashcardInteractor;
import edit_flashcard_use_case.EditFlashcardOutputBoundary;
import interface_adapters.controllers.EditFlashcardController;
import interface_adapters.presenters.EditFlashcardPresenter;
import frameworks_and_drivers.components.EditFlashcardPanel;
import data_access_use_case.entity_request_models.FlashcardDsRequestModel;
import frameworks_and_drivers.database.DBGateway;

public class EditFlashcardScreen extends Screen {
    /**
     * Creates a new FlashcardEditorMain object. This constructor creates the presenter, interactor and controller and
     * uses dependency inversion to follow clean architecture. This constructor makes the flashcard edit page visible.
     * @param dbGateway The database gateway.
     * @param flashcard The flashcard to edit.
     */
    public EditFlashcardScreen(DBGateway dbGateway, FlashcardDsRequestModel flashcard){
        EditFlashcardOutputBoundary presenter = new EditFlashcardPresenter();

        EditFlashcardInputBoundary interactor = new EditFlashcardInteractor(dbGateway, presenter);
        EditFlashcardController controller = new EditFlashcardController(interactor);


        this.setTitle("Edit Flashcard \"" + flashcard.getTerm() + "\"");
        EditFlashcardPanel editScreen = new EditFlashcardPanel(controller, flashcard, this);
        this.add(editScreen);
        this.setSize(500, 200);
        this.setVisible(true);
    }
}