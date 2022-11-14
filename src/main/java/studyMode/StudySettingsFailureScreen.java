package studyMode;

import javax.swing.*;

public class StudySettingsFailureScreen extends JFrame {

    private static String failMessage = "<html>This flashcard set is empty! Please add flashcards if you wish to study.</html>";
    public StudySettingsFailureScreen() {
        super("Error: Empty Flashcard Set");
        JLabel label = new JLabel(StudySettingsFailureScreen.failMessage);
        this.add(label);

        this.setSize(350,200);
        this.setVisible(true);//making the frame visible

        // quit if you close the tab
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new StudySettingsFailureScreen();
    }
}
