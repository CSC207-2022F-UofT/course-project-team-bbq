package create_flashcardset_use_case;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

// Frameworks/Drivers (Blue) layer

public class CreationScreen extends JPanel implements ActionListener {
    /**
     * The title of the flashcard set chosen by the user
     */
    JTextField title = new JTextField(15);
    /**
     * The description of the flashcard set
     */
    JTextField description = new JTextField(15);
    /**
     * The owner's username of the flashcard set
     */
    JTextField username = new JTextField(15);

    /**
     * The controller
     */
    FlashcardSetController flashcardSetController;

    /**
     * A window with a title and a JButton.
     */
    boolean privateSelected;  // for public or private status of flashcard set
    public CreationScreen(FlashcardSetController controller) {

        this.flashcardSetController = controller;

        JLabel name = new JLabel("Flashcard Set Creation Screen");
        name.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel flashcardSetTitle = new LabelTextPanel(
                new JLabel("Title:"), title);
        LabelTextPanel flashcardSetDescription = new LabelTextPanel(
                new JLabel("Description:"), description);
        LabelTextPanel flashcardSetOwner = new LabelTextPanel(
                new JLabel("Owner (username):"), username);

        JButton signUp = new JButton("Create");
        JButton cancel = new JButton("Cancel");

        JPanel confirmationButtons = new JPanel();
        confirmationButtons.add(signUp);
        confirmationButtons.add(cancel);

        signUp.addActionListener(this);
        cancel.addActionListener(this);

        JCheckBox privacy = new JCheckBox("Set as Private");
        JPanel privacyButton = new JPanel();
        privacyButton.add(privacy);
        privacy.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(name);
        this.add(flashcardSetTitle);
        this.add(flashcardSetDescription);
        this.add(flashcardSetOwner);
        this.add(privacy);
        this.add(confirmationButtons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
//        System.out.println("Click " + evt.getActionCommand());

        // Exit program if user cancels creation
        if (Objects.equals(evt.getActionCommand(), "Cancel")) {
            System.exit(0);
        } else if (Objects.equals(evt.getActionCommand(), "Set as Private")) {
            privateSelected = !privateSelected;  // toggle every time clicked
        } else {
            try {
                flashcardSetController.create(title.getText(), description.getText(),
                        privateSelected, username.getText(), evt.getActionCommand());
                JOptionPane.showMessageDialog(this,
                        "Flashcard Set: [" + title.getText() + "] created.");
                System.exit(0);
            } catch (FlashcardSetCreationFailed e) {
                JOptionPane.showMessageDialog(this, e.getMessage());

            }
        }
    }
}