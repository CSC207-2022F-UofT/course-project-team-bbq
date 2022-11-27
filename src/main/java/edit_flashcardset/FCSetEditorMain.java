package edit_flashcardset;

import edit_flashcardset.screens.FCSetEditorController;
import edit_flashcardset.screens.FCSetEditorPresenter;
import edit_flashcardset.screens.FCSetEditorScreen;
import dataAccess.DBGateway;
import entityRequestModels.FlashcardSetDsRequestModel;

import javax.swing.*;

public class FCSetEditorMain extends JFrame{
    /**
     * Creates a new FCSetEditorMain object. This constructor creates the presenter, interactor and controller and
     * uses dependency inversion to follow clean architecture. This constructor makes the flashcard set edit page
     * visible.
     * @param dbGateway The database gateway.
     * @param flashcardSet The flashcard set to edit.
     */
    public FCSetEditorMain(DBGateway dbGateway, FlashcardSetDsRequestModel flashcardSet){
        FCSetEditorOutputBoundary presenter = new FCSetEditorPresenter();

        FCSetEditorInputBoundary interactor = new FCSetEditorInteractor(dbGateway, presenter);
        FCSetEditorController controller = new FCSetEditorController(interactor);


        this.setTitle("Edit Flashcard Set \"" + flashcardSet.getTitle() + "\"");

        FCSetEditorScreen editScreen = new FCSetEditorScreen(controller, flashcardSet, this);
        this.add(editScreen);
        this.setSize(500, 200);
        this.setVisible(true);
    }
}