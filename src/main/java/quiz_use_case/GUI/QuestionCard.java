package quiz_use_case.GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Abstract class that represents a GUI panel that displays a quiz question.
 * Frameworks & Drivers
 * @author Anthony
 */
public abstract class QuestionCard extends JPanel {
    private String userAnswer = null;

    protected FlatLabel number;
    protected final ImageIcon yes = new ImageIcon("src/images/yes.png");
    protected final ImageIcon no = new ImageIcon("src/images/no.png");

    protected final Color DARK_GRAY = new Color(41,41,41);
    protected final Color GREEN = new Color(24, 134, 0);
    protected final Color RED = new Color(134, 0, 0);

    /**
     * Constructs a question card given the question number.
     * @param num the numbering for this question card
     */
    public QuestionCard(int num) {
        this.setBackground(DARK_GRAY);
        this.number = new FlatLabel("Question #" + num, "h4");
    }

    /**
     * Returns a JLabel that displays the status of the question. Also updates the background.
     * @param isCorrect true if user answer is correct
     * @return JLabel that displays the status
     */
    public JLabel generateStatus(boolean isCorrect) {
        String statusMessage;
        if (isCorrect) {
            statusMessage = "CORRECT!";
            this.setBackground(GREEN);
        } else {
            statusMessage = "INCORRECT!";
            this.setBackground(RED);
        }
        return new JLabel(statusMessage);
    }

    /**
     * Gets the user answer.
     * @return the user answer
     */
    public String getUserAnswer() {
        return this.userAnswer;
    }

    /**
     * Sets the user answer.
     * @param userAnswer the user answer
     */
    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
