package Editor.FlashcardSet;

import Editor.FlashcardSet.screens.FCSetEditorController;
import Editor.FlashcardSet.screens.FCSetEditorPresenter;
import Editor.FlashcardSet.screens.FCSetEditorScreen;
import dataAccess.DBGateway;
import dataAccess.FlashcardSetDataAccess;
import dataAccess.FlashcardDataAccess;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FCSetEditorMain {
    public static void main(String[] args) {
        FCSetEditorOutputBoundary presenter = new FCSetEditorPresenter();
        DBGateway dataBaseGateway;

        FlashcardDataAccess fcDataAccess;
        FlashcardSetDataAccess fcSDataAccess;
        //CommonUserDataAccess userDataAccess;

        try {
            fcDataAccess = new FlashcardDataAccess("src/data/Flashcards.csv");
            fcSDataAccess = new FlashcardSetDataAccess("src/data/FlashcardSets.csv");
            //userDataAccess = new CommonUserDataAccess("src/data/Users.csv");
        } catch (IOException e) {
            throw new RuntimeException("could not find file");
        }

        dataBaseGateway = new DBGateway(fcDataAccess, fcSDataAccess, null);

        FCSetEditorInputBoundary interactor = new FCSetEditorInteractor(dataBaseGateway, presenter);
        FCSetEditorController controller = new FCSetEditorController(interactor);


        JFrame application = new JFrame("Edit Example");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        FCSetEditorScreen editScreen = new FCSetEditorScreen(controller);
        screens.add(editScreen, "welcome");
        cardLayout.show(screens, "Edit");
        application.pack();
        application.setVisible(true);
    }
}