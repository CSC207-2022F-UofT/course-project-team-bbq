package Editor.Flashcard;

import Editor.Flashcard.screens.FlashcardEditorController;
import Editor.Flashcard.screens.FlashcardEditorPresenter;
import Editor.Flashcard.screens.FlashcardEditorScreen;
import dataAccess.CommonUserDataAccess;
import dataAccess.DBGateway;
import dataAccess.FlashcardDataAccess;
import dataAccess.FlashcardSetDataAccess;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FlashcardEditorMain {

    public static void main(String[] args) {
        FlashcardEditorOutputBoundary presenter = new FlashcardEditorPresenter();
        DBGateway dataBaseGateway;

        FlashcardDataAccess fcDataAccess;
        FlashcardSetDataAccess fcSDataAccess;
        CommonUserDataAccess userDataAccess;

        try{
            fcDataAccess = new FlashcardDataAccess("src/data/Flashcards.csv");
            fcSDataAccess = new FlashcardSetDataAccess("src/data/FlashcardSets.csv");
            //userDataAccess = new CommonUserDataAccess("src/data/Users.csv");
        }
        catch(IOException e){
            throw new RuntimeException("could not find file");
        }

        dataBaseGateway = new DBGateway(fcDataAccess, fcSDataAccess, null);

        FlashcardEditorInputBoundary interactor = new FlashcardEditorInteractor(dataBaseGateway, presenter);
        FlashcardEditorController controller = new FlashcardEditorController(interactor);


        JFrame application = new JFrame("Edit Example");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        FlashcardEditorScreen editScreen = new FlashcardEditorScreen(controller);
        screens.add(editScreen, "welcome");
        cardLayout.show(screens, "Edit");
        application.pack();
        application.setVisible(true);




        //Second use case

    }
}
