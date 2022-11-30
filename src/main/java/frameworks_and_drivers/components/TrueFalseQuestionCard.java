package frameworks_and_drivers.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * GUI panel that displays a true false question.
 * Frameworks & Drivers
 * @author Anthony
 */
public class TrueFalseQuestionCard extends QuestionCard implements ActionListener {
    private final JButton trueButton;
    private final JButton falseButton;

    private enum Actions {
        TRUE, FALSE
    }

    /**
     * Constructs a true false question card that is ready to receive user input.
     * @param num the question number
     * @param outputText the output text to be displayed
     */
    public TrueFalseQuestionCard(int num, List<String> outputText) {
        super(num);
        JLabel term = new JLabel(outputText.get(0));
        JLabel potentialDefinition = new JLabel(outputText.get(1));
        this.trueButton = new JButton("TRUE");
        trueButton.addActionListener(this);
        this.falseButton = new JButton("FALSE");
        falseButton.addActionListener(this);

        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        this.setLayout(grid);
        this.add(number, c);
        c.gridy += 1;
        c.gridwidth = 1;
        this.add(term, c);
        c.gridx += 1;
        this.add(potentialDefinition, c);
        c.gridx = 0;
        c.gridy += 1;
        this.add(trueButton, c);
        c.gridx += 1;
        this.add(falseButton, c);
    }

    /**
     * Constructs a true false question card that already has user answers inputted. For display purposes only.
     * @param num the question number
     * @param outputText the output text to be displayed
     * @param userAnswer the user answer
     * @param actualAnswer the actual answer
     */
    public TrueFalseQuestionCard(int num, List<String> outputText, String userAnswer, String actualAnswer) {
        super(num);
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

        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        this.setLayout(grid);
        this.add(number, c);
        c.gridy += 1;
        this.add(status, c);
        c.gridy += 1;
        c.gridwidth = 1;
        this.add(term, c);
        c.gridx += 1;
        this.add(potentialDefinition, c);
        c.gridx = 0;
        c.gridy += 1;
        this.add(trueButton, c);
        c.gridx += 1;
        this.add(falseButton, c);
    }

    /**
     * If TRUE is toggled, toggle the true button. If FALSE is toggled, toggle the false button.
     * @param e the action event
     */
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
