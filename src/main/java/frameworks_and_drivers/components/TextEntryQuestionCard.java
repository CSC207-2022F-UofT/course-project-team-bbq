package frameworks_and_drivers.components;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * GUI panel that displays a text entry question.
 * Frameworks & Drivers
 * @author Anthony
 */
public class TextEntryQuestionCard extends QuestionCard {
    private final boolean missingTerm;
    private final JLabel label;
    private final JTextArea textArea;

    /**
     * Constructs a text entry question card that is ready to receive user input.
     * @param num the question number
     * @param outputText the output text to be displayed
     */
    public TextEntryQuestionCard(int num, List<String> outputText) {
        super(num);
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        this.setLayout(grid);
        this.add(number, c);
        c.insets = new Insets(50, 50, 50, 50);
        this.missingTerm = outputText.get(0) == null;
        this.textArea = new JTextArea();
        c.gridwidth = 1;
        c.gridy += 1;
        if (missingTerm) { // missing term
            this.label = new JLabel(outputText.get(1));
            c.fill = GridBagConstraints.BOTH;
            this.add(textArea, c);
            c.fill = GridBagConstraints.NONE;
            c.gridx += 1;
            this.add(label, c);
        } else { // missing definition
            this.label = new JLabel(outputText.get(0));
            this.add(label, c);
            c.gridx += 1;
            c.fill = GridBagConstraints.BOTH;
            this.add(textArea, c);
            c.fill = GridBagConstraints.NONE;
        }
    }

    /**
     * Constructs a text entry question card that already has user answers inputted. For display purposes only.
     * @param num the question number
     * @param outputText the output text to be displayed
     * @param userAnswer the user answer
     * @param actualAnswer the actual answer
     */
    public TextEntryQuestionCard(int num, List<String> outputText, String userAnswer, String actualAnswer) {
        super(num);
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        this.setLayout(grid);
        this.add(number, c);
        this.missingTerm = outputText.get(0) == null;
        boolean isCorrect = actualAnswer.equalsIgnoreCase(userAnswer);
        JLabel status = generateStatus(isCorrect);
        c.gridy += 1;
        this.add(status, c);
        c.insets = new Insets(50, 50, 50, 50);
        this.textArea = new JTextArea(userAnswer);
        this.textArea.setEnabled(false); // disabled
        c.gridwidth = 1;
        c.gridy += 1;
        if (missingTerm) { // missing term
            this.label = new JLabel(outputText.get(1));
            c.fill = GridBagConstraints.BOTH;
            this.add(textArea, c);
            c.fill = GridBagConstraints.NONE;
            c.gridx += 1;
            this.add(label, c);
        } else { // missing definition
            this.label = new JLabel(outputText.get(0));
            this.add(label, c);
            c.gridx += 1;
            c.fill = GridBagConstraints.BOTH;
            this.add(textArea, c);
            c.fill = GridBagConstraints.NONE;
        }
        c.insets = new Insets(0, 0, 0, 0);
        if (!isCorrect) {
            JLabel actualAnswerLabel = new JLabel("Actual Answer: " + actualAnswer);
            c.gridx = 0;
            c.gridy += 1;
            c.gridwidth = 2;
            this.add(actualAnswerLabel, c);
        }
    }

    /**
     * Updates the user answer based on the text field.
     */
    public void updateUserAnswer() {
        this.setUserAnswer(this.textArea.getText());
    }
}
