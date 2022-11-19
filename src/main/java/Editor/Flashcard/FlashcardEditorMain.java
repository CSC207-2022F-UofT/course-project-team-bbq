package Editor.Flashcard;

import Editor.Flashcard.screens.FlashcardEditorController;
import Editor.Flashcard.screens.FlashcardEditorPresenter;
import Editor.Flashcard.screens.FlashcardEditorScreen;
import dataAccess.*;
import entityRequestModels.FlashcardDsRequestModel;

import javax.swing.*;

public class FlashcardEditorMain extends JFrame{

    public FlashcardEditorMain(DBGateway dbGateway, FlashcardDsRequestModel flashcard){
        FlashcardEditorOutputBoundary presenter = new FlashcardEditorPresenter();

        FlashcardEditorInputBoundary interactor = new FlashcardEditorInteractor(dbGateway, presenter);
        FlashcardEditorController controller = new FlashcardEditorController(interactor);


        this.setTitle("Edit Flashcard \"" + flashcard.getTerm() + "\"");
        FlashcardEditorScreen editScreen = new FlashcardEditorScreen(controller, flashcard, this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(editScreen);
        this.setSize(500, 200);
        this.setVisible(true);
    }
}