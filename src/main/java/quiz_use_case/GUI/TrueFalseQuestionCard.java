package quiz_use_case.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * GUI panel that displays a true false question.
 * Frameworks & Drivers
 * @author Anthony
 */
public class TrueFalseQuestionCard extends QuestionCard {
    private final JButton trueButton;
    private final JButton falseButton;

    private enum Actions {
        TRUE, FALSE
    }

    /**
     * Constructs a true false question card that is ready to receive user input.
     * @param outputText the output text to be displayed
     */
    public TrueFalseQuestionCard(ArrayList<String> outputText) {
        JLabel term = new JLabel(outputText.get(0));
        JLabel potentialDefinition = new JLabel(outputText.get(1));
        this.trueButton = new JButton("TRUE");
        trueButton.addActionListener(this);
        this.falseButton = new JButton("FALSE");
        falseButton.addActionListener(this);

        this.setLayout(new FlowLayout());
        this.add(term);
        this.add(potentialDefinition);
        this.add(trueButton);
        this.add(falseButton);
    }

    /**
     * Constructs a true false question card that already has user answers inputted. For display purposes only.
     * @param outputText the output text to be displayed
     * @param userAnswer the user answer
     * @param actualAnswer the actual answer
     */
    public TrueFalseQuestionCard(ArrayList<String> outputText, String userAnswer, String actualAnswer) {
        JLabel term = new JLabel(outputText.get(0));
        JLabel potentialDefinition = new JLabel(outputText.get(1));

        boolean isCorrect = actualAnswer.equalsIgnoreCase(userAnswer);
        JLabel status = generateStatus(isCorrect);

        this.trueButton = new JButton("TRUE");
        trueButton.setEnabled(false);
        this.falseButton = new JButton("FALSE");
        falseButton.setEnabled(false);

        if (userAnswer != null) {
            if (userAnswer.equalsIgnoreCase("true")) {
                if (isCorrect) {
                    trueButton.setIcon(yes);
                } else {
                    trueButton.setIcon(no);
                }
            } else {
                if (isCorrect) {
                    falseButton.setIcon(yes);
                } else {
                    falseButton.setIcon(no);
                }
            }
        }

        this.setLayout(new FlowLayout());
        this.add(status);
        this.add(term);
        this.add(potentialDefinition);
        this.add(trueButton);
        this.add(falseButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        if (c.equals(Actions.TRUE.name())) {
            this.setUserAnswer("true");
            this.trueButton.setText("TRUE SELECTED");
            this.falseButton.setText("FALSE");
        } else if (c.equals(Actions.FALSE.name())) {
            this.setUserAnswer("false");
            this.trueButton.setText("TRUE");
            this.falseButton.setText("FALSE SELECTED");
        }
    }
}
