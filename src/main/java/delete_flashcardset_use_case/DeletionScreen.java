package delete_flashcardset_use_case;


import login_and_signup_use_case.UserLoginResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

// Frameworks/Drivers (Blue) layer

public class DeletionScreen extends JFrame implements ActionListener {
    /**
     * The id of the flashcard set to be deleted
     */
    int flashcardSetID;

    UserLoginResponseModel user;

    /**
     * The controller
     */
    DelFlashcardSetController controller;

//    /**
//     * The application
//     */
//    JFrame application;  // need this variable to only close the application and not the whole program

    /**
     * A window with a title and a JButton.
     */
    public DeletionScreen(int flashcardSetID, DelFlashcardSetController controller, UserLoginResponseModel user) {
        this.user=user;
        this.flashcardSetID = flashcardSetID;
        this.controller = controller;

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

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        this.add(name);
//        this.add(id);
        this.add(confirmationButtons);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1000, 800);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
//        System.out.println("Click " + evt.getActionCommand());

        // Exit deletion screen if user cancels deletion
        if (Objects.equals(evt.getActionCommand(), "Cancel")) {
            this.dispose();
        }

        else {  // Delete was pressed
//            try {
//                int id = Integer.parseInt(flashcardSetID.getText());  // check input is an integer

            // Ask for confirmation
            String title = user.getFlashcardSets().get(flashcardSetID)[0];

            int confirmation = JOptionPane.showConfirmDialog(this,
                    "Delete " + title + "?",
                    "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirmation == 0) {
                try {
                    controller.delete(flashcardSetID);
                    JOptionPane.showMessageDialog(this, title + " has been deleted.");
                    this.dispose();  // exit deletion screen
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