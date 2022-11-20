package MainPage;


import dataAccess.DBGateway;
import dataAccess.FlashcardDataAccess;
import dataAccess.FlashcardSetDataAccess;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ListOfFlashcardSetsDataPanel extends JPanel {

    public ListOfFlashcardSetsDataPanel(Map<Integer, String[]> idsToFlashcardSetData,
                                        DBGateway gateway, boolean editable) {
        Set<Integer> flashcardSetIds = idsToFlashcardSetData.keySet();

        for (int flashcardSetId : flashcardSetIds) {
           String[] data = idsToFlashcardSetData.get(flashcardSetId);
           String title = data[0];
           String description = data[1];

           this.add(new FlashcardSetDataPanel(title, description, flashcardSetId, gateway, editable));
        }

        this.setLayout(new FlowLayout());
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setPreferredSize(new Dimension(500, 1000));
    }

    public static void main(String[] args) throws IOException {
        JFrame f = new JFrame();

        Map<Integer, String[]> map = new HashMap<>();
        map.put(0, new String[] {"test set", "for testing study use case"});
        map.put(1, new String[] {"empty test set", "for testing study use case with empty set"});
        DBGateway gateway = new DBGateway(new FlashcardDataAccess(DBGateway.getFlashcardPath()),
                new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath()),
                null);

        f.add(new ListOfFlashcardSetsDataPanel(map, gateway, true));
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
