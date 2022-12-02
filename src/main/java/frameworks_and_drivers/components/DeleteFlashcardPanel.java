package frameworks_and_drivers.components;
import interface_adapters.controllers.DeleteFlashcardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteFlashcardPanel extends JPanel implements ActionListener {
    DeleteFlashcardController controller;
    JFrame deleteScreen;
    int flashcardId, flashcardSetId;

    public DeleteFlashcardPanel(DeleteFlashcardController controller, JFrame DeleteScreen, int flashcardId, int flashcardSetId) {
        //Initiating all the sub panels.
        this.deleteScreen = DeleteScreen;
        this.controller = controller;
        this.flashcardId = flashcardId;
        this.flashcardSetId = flashcardSetId;
        this.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());


        //Creating the button panel.
        JButton confirm = new JButton("confirm");
        JButton cancel = new JButton("cancel");
        confirm.addActionListener(this);
        cancel.addActionListener(e -> this.deleteScreen.dispose());
        buttonPanel.add(confirm);
        buttonPanel.add(cancel);
        JLabel message = new JLabel("Do you wish to delete " +  flashcardId);


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
            controller.delete(flashcardSetId, flashcardId);
            JOptionPane.showMessageDialog(this, "Flashcard deleted.");
            deleteScreen.dispose();
        } catch (RuntimeException error) {
            //Failure view.
            JOptionPane.showMessageDialog(this, error.getMessage());
            deleteScreen.dispose();
            }
        }
}
