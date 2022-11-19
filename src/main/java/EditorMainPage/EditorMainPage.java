package EditorMainPage;

import dataAccess.*;
import entityRequestModels.FlashcardDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditorMainPage extends JFrame {
    public EditorMainPage(int flashcardSetId){
        IFlashcardSetDataAccess fcSetGateway;
        IFlashcardDataAccess fcGateway;
        IUserDataAccess userGateway;
        try {
            fcSetGateway = new FlashcardSetDataAccess("src/data/FlashcardSets.csv");
            fcGateway = new FlashcardDataAccess("src/data/Flashcards.csv");
            userGateway = new CommonUserDataAccess("src/data/Users.csv");
        }
        catch(IOException error){
            throw new RuntimeException("error: file not found");
        }

        DBGateway dbGateway = new DBGateway(fcGateway, fcSetGateway, userGateway);

        FlashcardSetDsRequestModel flashcardSet = fcSetGateway.getFlashcardSet(flashcardSetId);

        List<FlashcardDsRequestModel> flashcardData = new ArrayList<>();
        for(int flashcardId: flashcardSet.getFlashcardIds()){
            FlashcardDsRequestModel flashcard = fcGateway.getFlashcard(flashcardId);
            flashcardData.add(flashcard);
        }

        this.add(new ListOfFlashcardsDataPanel(dbGateway ,flashcardData, flashcardSet, this));


        this.setTitle("Edit Set \"" + flashcardSet.getTitle() + "\"");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        new EditorMainPage(2);
    }
}
