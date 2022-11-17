package EditorMainPage;

import dataAccess.FlashcardDataAccess;
import dataAccess.FlashcardSetDataAccess;
import entityRequestModels.FlashcardDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditorMainPage extends JFrame {
    public EditorMainPage(int flashcardSetId){
        FlashcardSetDataAccess fcSetGateway;
        FlashcardDataAccess fcGateway;
        try {
            fcSetGateway = new FlashcardSetDataAccess("src/data/FlashcardSets.csv");
            fcGateway = new FlashcardDataAccess("src/data/Flashcards.csv");
        }
        catch(IOException error){
            throw new RuntimeException("error: file not found");
        }
        FlashcardSetDsRequestModel flashcardSet = fcSetGateway.getFlashcardSet(flashcardSetId);
        Map<Integer, String[]> idsToFlashcardData = new HashMap<>();

        for(int flashcardId: flashcardSet.getFlashcardIds()){
            FlashcardDsRequestModel flashcard = fcGateway.getFlashcard(flashcardId);

            idsToFlashcardData.put(flashcardId, new String[] {flashcard.getTerm(), flashcard.getDefinition()});
        }

        int numCards = idsToFlashcardData.size();
        if (numCards == 0){
            new JLabel("You have no Flashcards in this FlashcardSet.");
        }
        else {
            this.add(new ListOfFlashcardsDataPanel(idsToFlashcardData, this));
        }
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        new EditorMainPage(0);
    }
}
