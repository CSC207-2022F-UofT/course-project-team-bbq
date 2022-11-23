package edit_flashcard;

import edit_flashcard.screens.FlashcardEditorController;
import edit_flashcard.screens.FlashcardEditorPresenter;
import edit_flashcard.screens.FlashcardEditorScreen;
import dataAccess.*;
import entityRequestModels.FlashcardDsRequestModel;

import javax.swing.*;
import java.io.IOException;

public class FlashcardEditorMain extends JFrame{

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

    public static void main(String[] args) {
        IFlashcardDataAccess fcDataAccess;
        try{
            fcDataAccess = new FlashcardDataAccess("src/data/Flashcards.csv");
        }
        catch(IOException e){
            throw new RuntimeException("no file found");
        }
        DBGateway dbGateway = new DBGateway(fcDataAccess, null, null);
        FlashcardDsRequestModel fc = new FlashcardDsRequestModel("Term", "the first test card", null, 0, 0);
        new FlashcardEditorMain(dbGateway, fc);
    }
}