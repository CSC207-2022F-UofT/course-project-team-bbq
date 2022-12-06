package frameworks_and_drivers.screens;

import edit_flashcard_set_use_case.EditFlashcardSetInputBoundary;
import edit_flashcard_set_use_case.EditFlashcardSetInteractor;
import edit_flashcard_set_use_case.EditFlashcardSetOutputBoundary;
import frameworks_and_drivers.components.EditFlashcardSetPanel;
import interface_adapters.controllers.EditFlashcardsSetController;
import interface_adapters.presenters.EditFlashcardSetPresenter;
import frameworks_and_drivers.database.DBGateway;
import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;
import frameworks_and_drivers.screens.Screen;

public class EditFlashcardSetScreen extends Screen {
    /**
     * Creates a new FCSetEditorMain object. This constructor creates the presenter, interactor and controller and
     * uses dependency inversion to follow clean architecture. This constructor makes the flashcard set edit page
     * visible.
     * @param dbGateway The database gateway.
     * @param flashcardSet The flashcard set to edit.
     */
    public EditFlashcardSetScreen(DBGateway dbGateway, FlashcardSetDsRequestModel flashcardSet){
        EditFlashcardSetOutputBoundary presenter = new EditFlashcardSetPresenter();

        EditFlashcardSetInputBoundary interactor = new EditFlashcardSetInteractor(dbGateway, presenter);
        EditFlashcardsSetController controller = new EditFlashcardsSetController(interactor);


        this.setTitle("Edit Flashcard Set \"" + flashcardSet.getTitle() + "\"");

        EditFlashcardSetPanel editScreen = new EditFlashcardSetPanel(controller, flashcardSet, this);
        this.add(editScreen);
        this.setSize(500, 200);
        this.setVisible(true);
    }
}