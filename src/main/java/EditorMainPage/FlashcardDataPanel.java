package EditorMainPage;

import Editor.Flashcard.FlashcardEditorMain;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlashcardDataPanel extends JPanel implements ActionListener {
    FlashcardInfo flashcard;
    JFrame frame;
    public FlashcardDataPanel(FlashcardInfo flashcard, JFrame frame){
        this.frame = frame;
        this.flashcard = flashcard;


        Border border = BorderFactory.createTitledBorder(flashcard.getTerm());

        JLabel descriptionLabel = new JLabel(flashcard.getDefinition());

        this.add(descriptionLabel);

        JPanel buttons = new JPanel();

        JButton edit = new JButton("Edit Flashcard");
        buttons.add(edit);
        JButton delete = new JButton("Delete Flashcard");
        buttons.add(delete);

        edit.addActionListener(this);

        this.add(buttons);
        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("Clicked " + event.getActionCommand());

        if(event.getActionCommand().equals("Edit Flashcard")){
            FlashcardEditorMain.main(flashcard);
        }
    }
}
