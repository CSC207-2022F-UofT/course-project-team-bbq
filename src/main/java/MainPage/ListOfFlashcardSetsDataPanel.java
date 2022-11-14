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

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(1000, 1000);

        Map<Integer, String[]> map = new HashMap<>();
        for (int i = 1; i < 4; i++) {
            map.put(i, new String[]{"test set " + i, "test description " + i});
        }

        f.add(new ListOfFlashcardSetsDataPanel(map));
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
