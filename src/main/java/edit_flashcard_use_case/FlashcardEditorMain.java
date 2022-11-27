package edit_flashcard_use_case;

import edit_flashcard_use_case.screens.FlashcardEditorController;
import edit_flashcard_use_case.screens.FlashcardEditorPresenter;
import edit_flashcard_use_case.screens.FlashcardEditorScreen;
import data_access.*;
import data_access.entity_request_models.FlashcardDsRequestModel;

import javax.swing.*;

public class FlashcardEditorMain extends JFrame{
    /**
     * Creates a new FlashcardEditorMain object. This constructor creates the presenter, interactor and controller and
     * uses dependency inversion to follow clean architecture. This constructor makes the flashcard edit page visible.
     * @param dbGateway The database gateway.
     * @param flashcard The flashcard to edit.
     */
    public FlashcardEditorMain(DBGateway dbGateway, FlashcardDsRequestModel flashcard){
        FlashcardEditorOutputBoundary presenter = new FlashcardEditorPresenter();

        FlashcardEditorInputBoundary interactor = new FlashcardEditorInteractor(dbGateway, presenter);
        FlashcardEditorController controller = new FlashcardEditorController(interactor);


        this.setTitle("Edit Flashcard \"" + flashcard.getTerm() + "\"");
        FlashcardEditorScreen editScreen = new FlashcardEditorScreen(controller, flashcard, this);
        this.add(editScreen);
        this.setSize(500, 200);
        this.setVisible(true);
    }
}