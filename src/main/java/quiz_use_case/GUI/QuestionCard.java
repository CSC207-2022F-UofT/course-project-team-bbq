package quiz_use_case.GUI;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Abstract class that represents a GUI panel that displays a quiz question.
 * Frameworks & Drivers
 * @author Anthony
 */
public abstract class QuestionCard extends JPanel implements ActionListener {
    private String userAnswer = null;

    protected final ImageIcon yes = new ImageIcon("src/images/yes.png");
    protected final ImageIcon no = new ImageIcon("src/images/no.png");

    /**
     * Returns a JLabel that displays the status of the question.
     * @param isCorrect true if user answer is correct
     * @return JLabel that displays the status
     */
    public JLabel generateStatus(boolean isCorrect) {
        String statusMessage;
        if (isCorrect) {
            statusMessage = "CORRECT!";
        } else {
            statusMessage = "INCORRECT!";
        }
        return new JLabel(statusMessage);
    }

    /** GETTERS AND SETTERS **/
    public String getUserAnswer() {
        return this.userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
