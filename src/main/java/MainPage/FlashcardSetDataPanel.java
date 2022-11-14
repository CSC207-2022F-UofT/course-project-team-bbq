package MainPage;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FlashcardSetDataPanel extends JPanel {

    public FlashcardSetDataPanel(String title, String description, int flashcardSetId) {
        Border border = BorderFactory.createTitledBorder(title);


        JLabel descriptionLabel = new JLabel(description);
        this.add(descriptionLabel);

        JPanel buttons = new JPanel();

        JButton study = new JButton("Study");
        buttons.add(study);
        JButton test = new JButton("Test");
        buttons.add(test);
        JButton edit = new JButton("Edit");
        buttons.add(edit);
        JButton delete = new JButton("Delete");
        buttons.add(delete);


        this.add(buttons);
        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setSize(1000, 1000);
        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        f.add(new FlashcardSetDataPanel("Test Set", "Test Description", 0));

        f.add(new FlashcardSetDataPanel("Test Set 2", "Test Description", 0));
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
