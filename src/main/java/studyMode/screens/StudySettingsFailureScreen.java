package studyMode.screens;

import javax.swing.*;
import java.awt.*;

/**
 * A screen which warns the user they are trying to study
 * an empty flashcard set.
 * <p>
 * Frameworks & Drivers
 * @author Lucas Prates
 */
public class StudySettingsFailureScreen extends JFrame {

    private static final String failMessage = "<html>This flashcard set is empty! Please add flashcards if you wish to study.</html>";

    /**
     * Creates a StudySettingsFailureScreen
     */
    public StudySettingsFailureScreen() {
        super("Error: Empty Flashcard Set");
        JLabel label = new JLabel(StudySettingsFailureScreen.failMessage);
        this.add(label, BorderLayout.CENTER);

        this.setSize(350,200);
        this.setVisible(true);//making the frame visible

        // quit if you close the tab
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
