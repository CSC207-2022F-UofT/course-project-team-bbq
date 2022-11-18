package delete_flashcardset_use_case;

import create_flashcardset_use_case.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

// Frameworks/Drivers (Blue) layer

public class DeletionScreen extends JPanel implements ActionListener {
    /**
     * The id of the flashcard set to be deleted
     */
    JTextField flashcardSetID = new JTextField(15);

    /**
     * The controller
     */
    DelFlashcardSetController controller;

    /**
     * A window with a title and a JButton.
     */

    private boolean keepFrame = true;  // indicates when to quit the application in MainCreateFlashcardSet

    public DeletionScreen(DelFlashcardSetController controller) {

        this.controller = controller;

        JLabel name = new JLabel("Flashcard Set Deletion Screen");
        name.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel id = new LabelTextPanel(
                new JLabel("Enter the flashcard set's ID:"), flashcardSetID);

        JButton delete = new JButton("Delete");
        JButton cancel = new JButton("Cancel");

        JPanel confirmationButtons = new JPanel();
        confirmationButtons.add(delete);
        confirmationButtons.add(cancel);

        delete.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(name);
        this.add(id);
        this.add(confirmationButtons);
    }

    public boolean isKeepFrame() {
        return keepFrame;
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
//        System.out.println("Click " + evt.getActionCommand());

        // Exit deletion screen if user cancels deletion
        if (Objects.equals(evt.getActionCommand(), "Cancel")) {
            keepFrame = false;
        }

        else {  // Delete was pressed
            try {
                int id = Integer.parseInt(flashcardSetID.getText());  // check input is an integer

                // Ask for confirmation
                int confirmation = JOptionPane.showConfirmDialog(this,
                        "Delete Flashcard set #" + flashcardSetID.getText() + "?",
                        "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (confirmation == 0) {
                    try {
                        controller.delete(id);
                        JOptionPane.showMessageDialog(this, "Flashcard Set #"
                                + flashcardSetID.getText() + " has been deleted.");
                        keepFrame = false;  // exit deletion screen
                    } catch (FlashcardSetNotFound e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please provide an integer as input.");

            }
        }


    }
}