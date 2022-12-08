package frameworks_and_drivers.components;
import delete_flashcard_use_case.DeleteFlashcardResponseModel;
import interface_adapters.controllers.DeleteFlashcardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

/**
 * Panel for deleting flashcard.
 * @author Junyu Chen
 */
public class DeleteFlashcardPanel extends JPanel implements ActionListener {
    DeleteFlashcardController controller;
    JFrame deleteScreen;
    int flashcardId, flashcardSetId;
    String flashcardTerm, flashcardSetTitle;

    public DeleteFlashcardPanel(DeleteFlashcardController controller, JFrame DeleteScreen, int flashcardId,
                                int flashcardSetId, String flashcardTerm, String flashcardSetTitle) {
        //Initiating all the sub panels.
        this.deleteScreen = DeleteScreen;
        this.controller = controller;
        this.flashcardId = flashcardId;
        this.flashcardSetId = flashcardSetId;
        this.flashcardTerm = flashcardTerm;
        this.flashcardSetTitle = flashcardSetTitle;
        this.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());


        //Creating the button panel.
        JButton confirm = new JButton("Confirm");
        JButton cancel = new JButton("Cancel");
        confirm.addActionListener(this);
        cancel.addActionListener(e -> this.deleteScreen.dispose());
        buttonPanel.add(confirm);
        buttonPanel.add(cancel);
        JLabel message = new JLabel("<html>Do you wish to delete the flashcard ["+ flashcardTerm +"] from ["
                + flashcardSetTitle +"]</html>");


        //Creating the showing the sub panel.
        this.add(message);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(1000, 500);
        this.setVisible(true);


    }

    /**
     * Response based on creation success or failure.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //Success view.
            DeleteFlashcardResponseModel responseModel = controller.delete(flashcardSetId, flashcardId);
            JOptionPane.showMessageDialog(this, "Flashcard [" + responseModel.getTerm() +
                    "] deleted at [" + responseModel.getDeleteDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "].");
            deleteScreen.dispose();
        } catch (RuntimeException error) {
            //Failure view.
            JOptionPane.showMessageDialog(this, error.getMessage());
            deleteScreen.dispose();
            }
        }
}
