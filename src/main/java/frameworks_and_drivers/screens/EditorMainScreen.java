package frameworks_and_drivers.screens;

import data_access_use_case.*;
import data_access_use_case.entity_request_models.FlashcardDsRequestModel;
import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;
import frameworks_and_drivers.components.ListOfFlashcardsDataPanel;
import frameworks_and_drivers.database.CommonUserDataAccess;
import frameworks_and_drivers.database.DBGateway;
import frameworks_and_drivers.database.FlashcardDataAccess;
import frameworks_and_drivers.database.FlashcardSetDataAccess;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The main page to edit a flashcard set. Includes the view of all flashcards inside the flashcard set. Including
 * features of editing title and description of flashcard set, editing independent flashcards, deleting flashcards, and
 * add flashcards.
 */
public class EditorMainScreen extends Screen {
    /**
     * Constructs an EditorMainPage object which is a JFrame and constructor opens a new window and makes the frame
     * visible.
     * @param flashcardSetId the flashcard set id of the flashcard to edit
     */
    public EditorMainScreen(int flashcardSetId){
        //Declare and initialize the database gateway, DBGateway.
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

        //Get all the flashcards of the flashcard set.
        FlashcardSetDsRequestModel flashcardSet = fcSetGateway.getFlashcardSet(flashcardSetId);
        List<FlashcardDsRequestModel> flashcardData = new ArrayList<>();
        for(int flashcardId: flashcardSet.getFlashcardIds()){
            FlashcardDsRequestModel flashcard = fcGateway.getFlashcard(flashcardId);
            flashcardData.add(flashcard);
        }

        //Create a panel that includes all the flashcards in the flashcard set and a panel with editing buttons.
        ListOfFlashcardsDataPanel listOfFlashcardPanel = new ListOfFlashcardsDataPanel(dbGateway ,flashcardData, flashcardSet, this);
        JScrollPane scrollPane = new JScrollPane(listOfFlashcardPanel);
        this.add(scrollPane);

        //Customize this frame with the title of the flashcard, size of the  panel, and setting frame to be visible.
        this.setTitle("Edit Set \"" + flashcardSet.getTitle() + "\"");
        this.setSize(1000, 800);
        this.setVisible(true);
    }
}
