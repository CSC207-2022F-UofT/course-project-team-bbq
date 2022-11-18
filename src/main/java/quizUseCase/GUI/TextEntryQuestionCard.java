package quizUseCase.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TextEntryQuestionCard extends QuestionCard {
    private boolean missingTerm;
    private JLabel label;
    private JTextField textField;

    public TextEntryQuestionCard(ArrayList<String> outputText) {
        if (outputText.get(0) == null) {
            this.missingTerm = true;
        } else {
            this.missingTerm = false;
        }

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
