package frameworks_and_drivers.components;


import frameworks_and_drivers.database.DBGateway;
import frameworks_and_drivers.screens.HomeScreen;
import login_and_signup_use_case.UserLoginResponseModel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Set;

/**
 * Abstract class that represents a panel for all flashcard set panels for a user.
 * This panel includes all FlashcardSetDataPanel objects and their buttons.
 * Frameworks & Drivers
 * @author Justin Li
 */
public class ListOfFlashcardSetsDataPanel extends JPanel {
    /**
     *Creates a ListOfFlashcardSetDataPanel object that includes the idsToFlashcardSetData,
     * gateway, user, and home.
     * @param idsToFlashcardSetData the ids of the flashcard sets.
     * @param gateway the gateway to reach all the flashcard sets.
     * @param user the user that contains all the flashcard sets.
     * @param home the home page that the list of flashcard set panels will be displayed on.
     */
    public ListOfFlashcardSetsDataPanel(Map<Integer, String[]> idsToFlashcardSetData,
                                        DBGateway gateway, UserLoginResponseModel user, HomeScreen home) {
        Set<Integer> flashcardSetIds = idsToFlashcardSetData.keySet();
        JPanel flashcardSets = new JPanel();
        for (int flashcardSetId : flashcardSetIds) {
           String[] data = idsToFlashcardSetData.get(flashcardSetId);
           String title = data[0];
           String description = data[1];

           flashcardSets.add(new FlashcardSetDataPanel(title, description, flashcardSetId, gateway, user, home));
        }
        int size = flashcardSetIds.size();
        int rows;
        if (size % 2 == 0){
            rows = size/2;
        }
        else{
            rows = size/2 + 1;
        }
        flashcardSets.setLayout(new GridLayout(rows, 2));
        Dimension flashcardSetPanelSize = flashcardSets.getPreferredSize();
        flashcardSets.setPreferredSize(new Dimension(flashcardSetPanelSize.width, flashcardSetPanelSize.height + 100));

        this.add(flashcardSets);
        this.setLayout (new FlowLayout());
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }
}
