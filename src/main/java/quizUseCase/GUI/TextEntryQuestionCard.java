package quizUseCase.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * GUI panel that displays a text entry question.
 * Frameworks & Drivers
 * @author Anthony
 */
public class TextEntryQuestionCard extends QuestionCard {
    private boolean missingTerm;
    private JLabel label;
    private JTextField textField;

    /**
     * Constructs a text entry question card that is ready to receive user input.
     * @param outputText the output text to be displayed
     */
    public TextEntryQuestionCard(ArrayList<String> outputText) {
        this.missingTerm = outputText.get(0) == null;

        this.textField = new JTextField();
        if (missingTerm) { // missing term
            this.label = new JLabel(outputText.get(1));

            // adding components in a specific order
            this.setLayout(new FlowLayout());
            this.add(textField);
            this.add(label);
        } else { // missing definition
            this.label = new JLabel(outputText.get(0));

            // adding components in a specific order
            this.setLayout(new FlowLayout());
            this.add(label);
            this.add(textField);
        }
    }

    /**
     * Constructs a text entry question card that already has user answers inputted. For display purposes only.
     * @param outputText the output text to be displayed
     * @param userAnswer the user answer
     * @param actualAnswer the actual answer
     */
    public TextEntryQuestionCard(ArrayList<String> outputText, String userAnswer, String actualAnswer) {
        this.missingTerm = outputText.get(0) == null;

        boolean isCorrect = actualAnswer.equals(userAnswer);
        JLabel status = generateStatus(isCorrect);

        this.textField = new JTextField();
        this.textField.setText(userAnswer);
        this.textField.setEnabled(false); // disabled
        if (missingTerm) { // missing term
            this.label = new JLabel(outputText.get(1));

            // adding components in a specific order
            this.setLayout(new FlowLayout());
            this.add(status);
            this.add(textField);
            this.add(label);
        } else { // missing definition
            this.label = new JLabel(outputText.get(0));

            // adding components in a specific order
            this.setLayout(new FlowLayout());
            this.add(status);
            this.add(label);
            this.add(textField);
        }
        if (!isCorrect) {
            JLabel actualAnswerLabel = new JLabel("Actual Answer: " + actualAnswer);
            this.add(actualAnswerLabel);
        }
    }

    /**
     * Updates the user answer based on the text field.
     */
    public void updateUserAnswer() {
        this.setUserAnswer(this.textField.getText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /** GETTERS AND SETTERS **/
    public boolean isMissingTerm() {
        return missingTerm;
    }

    public void setMissingTerm(boolean missingTerm) {
        this.missingTerm = missingTerm;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }
}
