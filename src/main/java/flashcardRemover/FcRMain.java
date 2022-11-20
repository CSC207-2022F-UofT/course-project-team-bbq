package flashcardRemover;

import dataAccess.*;

import javax.swing.*;
import java.io.IOException;

public class FcRMain extends JFrame {
    public FcRMain(int flashcardSetId, int flashcardId){
        DBGateway gateway;
        try{
            IFlashcardSetDataAccess flashcardSetDataAccess = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
            IFlashcardDataAccess flashcardDataAccess = new FlashcardDataAccess(DBGateway.getFlashcardPath());
            gateway = new DBGateway(flashcardDataAccess, flashcardSetDataAccess, null);
        }
        catch (IOException e){
            throw new RuntimeException("Could not access files.");
        }
        FcRPresenter presenter = new FcRResponseFormatter();
        FcRInputBoundary interactor = new FcRInterator(gateway,presenter);
        FcRController controller = new FcRController(interactor, flashcardSetId);
        this.setVisible(false);
        try {
            FcRResponseModel responseModel = controller.delete(flashcardSetId, flashcardId);
            JOptionPane.showMessageDialog(this,
                    responseModel.getTerm() + " deleted from " + responseModel.getCardSetName() +
                    " at " + responseModel.getDeleteDate());
            this.dispose();
        }catch (RuntimeException error){
            JOptionPane.showMessageDialog(this, "Deletion failed:\n" + error);
            this.dispose();
        }
    }
    public static void main(String[] args) {
        new FcRMain(0, 4);
    }

}
