package flashcardCreator;

import flashcardCreator.fcCScreens.FcCScreen;
import dataAccess.*;

import javax.swing.*;
import java.io.IOException;

public class FcCMain extends JFrame {
    public FcCMain(int flashcardSetId){
        DBGateway gateway;
        try{
            IFlashcardSetDataAccess flashcardSetDataAccess = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
            IFlashcardDataAccess flashcardDataAccess = new FlashcardDataAccess(DBGateway.getFlashcardPath());
            gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);
        }
        catch (IOException e){
            throw new RuntimeException("Could not access files.");
        }
        FcCPresenter presenter = new FcCResponseFormatter();
        FcCInputBoundary interactor = new FcCInterator(gateway,presenter);
        FcCController controller = new FcCController(interactor, flashcardSetId);
        FcCScreen fcCScreen= new FcCScreen(controller, this);
        this.add(fcCScreen);
        this.setSize(1000, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new FcCMain(0);
    }

}
