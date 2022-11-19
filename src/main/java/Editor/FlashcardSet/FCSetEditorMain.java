package Editor.FlashcardSet;

import Editor.FlashcardSet.screens.FCSetEditorController;
import Editor.FlashcardSet.screens.FCSetEditorPresenter;
import Editor.FlashcardSet.screens.FCSetEditorScreen;
import dataAccess.DBGateway;
import entityRequestModels.FlashcardSetDsRequestModel;

import javax.swing.*;

public class FCSetEditorMain extends JFrame{
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