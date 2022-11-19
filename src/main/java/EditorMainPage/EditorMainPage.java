package EditorMainPage;

import dataAccess.*;
import entityRequestModels.FlashcardDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditorMainPage extends JFrame {
    public EditorMainPage(DBGateway dbGateway, int flashcardSetId){
        IFlashcardDataAccess fcGateway = dbGateway.getFlashcardGateway();
        IFlashcardSetDataAccess fcSetGateway = dbGateway.getFlashcardSetGateway();

        FlashcardSetDsRequestModel flashcardSet = fcSetGateway.getFlashcardSet(flashcardSetId);

        List<FlashcardDsRequestModel> flashcardData = new ArrayList<>();
        for(int flashcardId: flashcardSet.getFlashcardIds()){
            FlashcardDsRequestModel flashcard = fcGateway.getFlashcard(flashcardId);
            flashcardData.add(flashcard);
        }

        int numCards = flashcardData.size();
        if (numCards == 0){
            new JLabel("You have no Flashcards in this FlashcardSet.");
        }
        else {
            this.add(new ListOfFlashcardsDataPanel(dbGateway ,flashcardData, flashcardSet, this));
        }
        this.setTitle("Edit Set \"" + flashcardSet.getTitle() + "\"");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        FlashcardSetDataAccess fcSetGateway;
        FlashcardDataAccess fcGateway;
        try {
            fcSetGateway = new FlashcardSetDataAccess("src/data/FlashcardSets.csv");
            fcGateway = new FlashcardDataAccess("src/data/Flashcards.csv");
        }
        catch(IOException error){
            throw new RuntimeException("error: file not found");
        }

        DBGateway dbGateway = new DBGateway(fcGateway, fcSetGateway, null);
        new EditorMainPage(dbGateway,0);
    }
}
