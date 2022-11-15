package MainPage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListOfFlashcardSetsDataPanel extends JPanel {

    public ListOfFlashcardSetsDataPanel(Map<Integer, String[]> idsToFlashcardSetData) {
        Set<Integer> flashcardSetIds = idsToFlashcardSetData.keySet();

        for (int flashcardSetId : flashcardSetIds) {
           String[] data = idsToFlashcardSetData.get(flashcardSetId);
           String title = data[0];
           String description = data[1];

           this.add(new FlashcardSetDataPanel(title, description, flashcardSetId));
        }

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
