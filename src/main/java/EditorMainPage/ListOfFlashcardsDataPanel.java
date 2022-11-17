package EditorMainPage;


import javax.swing.*;
import java.util.Map;

public class ListOfFlashcardsDataPanel extends JPanel {

    JFrame frame;
    public ListOfFlashcardsDataPanel(Map<Integer, String[]> idsToFlashcardData, JFrame frame) {
        this.frame = frame;

        JPanel buttons = new JPanel();
        JButton addFlashcard = new JButton("Add Flashcard");
        buttons.add(addFlashcard);
        JButton edit = new JButton("Edit Flashcard Set");
        buttons.add(edit);

        this.add(buttons);

        for (int flashcardId : idsToFlashcardData.keySet()) {
            String[] data = idsToFlashcardData.get(flashcardId);
            String title = data[0];
            String description = data[1];

            FlashcardInfo flashcard = new FlashcardInfo(flashcardId, title, description);

            this.add(new FlashcardDataPanel(flashcard, frame));
        }

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
