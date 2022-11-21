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
            fcGateway = new FlashcardDataAccess(DBGateway.getFlashcardPath());
            fcSetGateway = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
            userGateway = new CommonUserDataAccess(DBGateway.getUserPath());
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
        ListOfFlashcardsDataPanel listOfFlashcardPanel = new ListOfFlashcardsDataPanel(dbGateway ,flashcardData, flashcardSet, this);
        JScrollPane scrollPane = new JScrollPane(listOfFlashcardPanel);
        //scrollPane.setPreferredSize(new Dimension(100, 100));
        this.add(scrollPane);


        this.setTitle("Edit Set \"" + flashcardSet.getTitle() + "\"");
        this.setSize(1000, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
