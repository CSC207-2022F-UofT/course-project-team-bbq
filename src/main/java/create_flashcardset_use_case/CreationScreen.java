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
     * The application
     */
    JFrame application;  // need this variable to only close the application and not the whole program

    private boolean privateSelected;  // for public or private status of flashcard set

    /**
     * A window with a title and a JButton.
     */
    public CreationScreen(FlashcardSetController controller, JFrame application) {

        this.flashcardSetController = controller;
        this.application = application;

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

        // Exit the creation screen if user cancels creation
        if (Objects.equals(evt.getActionCommand(), "Cancel")) {
            application.dispose();
        } else if (Objects.equals(evt.getActionCommand(), "Set as Private")) {
            privateSelected = !privateSelected;  // toggle every time clicked
        } else {
            try {
                flashcardSetController.create(title.getText(), description.getText(),
                        privateSelected, username.getText());
                JOptionPane.showMessageDialog(this,
                        String.format("Flashcard Set: [%s] created.", title.getText()));
                application.dispose();  // exit creation screen
            } catch (FlashcardSetCreationFailed e) {
                JOptionPane.showMessageDialog(this, e.getMessage());

            }
        }
    }
}