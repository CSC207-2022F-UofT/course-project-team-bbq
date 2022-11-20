package delete_flashcardset_use_case;

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
    int flashcardSetID;

    /**
     * The controller
     */
    DelFlashcardSetController controller;

    /**
     * The application
     */
    JFrame application;  // need this variable to only close the application and not the whole program

    /**
     * A window with a title and a JButton.
     */
    public DeletionScreen(int flashcardSetID, DelFlashcardSetController controller, JFrame application) {
        this.flashcardSetID = flashcardSetID;
        this.controller = controller;
        this.application = application;

        JLabel name = new JLabel("Flashcard Set Deletion Screen");
        name.setAlignmentX(Component.CENTER_ALIGNMENT);

//        LabelTextPanel id = new LabelTextPanel(
//                new JLabel("Enter the flashcard set's ID:"), flashcardSetID);

        JButton delete = new JButton("Delete");
        JButton cancel = new JButton("Cancel");

        JPanel confirmationButtons = new JPanel();
        confirmationButtons.add(delete);
        confirmationButtons.add(cancel);

        delete.addActionListener(this);
        cancel.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(name);
//        this.add(id);
        this.add(confirmationButtons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
//        System.out.println("Click " + evt.getActionCommand());

        // Exit deletion screen if user cancels deletion
        if (Objects.equals(evt.getActionCommand(), "Cancel")) {
            application.dispose();
        }

        else {  // Delete was pressed
//            try {
//                int id = Integer.parseInt(flashcardSetID.getText());  // check input is an integer

            // Ask for confirmation
            int confirmation = JOptionPane.showConfirmDialog(this,
                    "Delete Flashcard set #" + flashcardSetID + "?",
                    "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirmation == 0) {
                try {
                    controller.delete(flashcardSetID);
                    JOptionPane.showMessageDialog(this, "Flashcard Set #"
                            + flashcardSetID + " has been deleted.");
                    application.dispose();  // exit deletion screen
                } catch (FlashcardSetNotFound e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
//            } catch (NumberFormatException e) {
//                JOptionPane.showMessageDialog(this, "Please provide an integer as input.");
//
//            }
        }


    }
}