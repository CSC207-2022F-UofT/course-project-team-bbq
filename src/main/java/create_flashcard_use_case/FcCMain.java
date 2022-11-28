package create_flashcard_use_case;

import create_flashcard_use_case.fcCScreens.FcCController;
import create_flashcard_use_case.fcCScreens.FcCResponsePresenter;
import create_flashcard_use_case.fcCScreens.FcCScreen;
import data_access.*;
import javax.swing.*;
import java.io.IOException;

/**
 * Main frame for flashcard Creation.
 * @author Junyu Chen
 */
public class FcCMain extends JFrame {
    /**
     * Create main frame for the flashcard creator.
     * @param flashcardSetId id of the flashcard set which flashcard will be stored in.
     */
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
        FcCOutputBoundary presenter = new FcCResponsePresenter();
        FcCInputBoundary interactor = new FcCInteractor(gateway,presenter);
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
