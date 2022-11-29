package quiz_use_case.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * GUI panel that displays a multiple choice question.
 * Frameworks & Drivers
 * @author Anthony
 */
public class MultipleChoiceQuestionCard extends QuestionCard implements ActionListener {
    private final List<JRadioButton> choices;

    /**
     * Constructs a multiple choice question card that is ready to receive user input.
     * @param num the question number
     * @param outputText the output text to display within the card
     */
    public MultipleChoiceQuestionCard(int num, List<String> outputText) {
        super(num);
        JLabel question = new JLabel(outputText.get(0));
        this.choices = new ArrayList<>();
        ButtonGroup group = new ButtonGroup();

        for (int i = 1; i < outputText.size(); i++) {
            JRadioButton choice = new JRadioButton(outputText.get(i));
            choice.addActionListener(this);
            choices.add(choice);
            group.add(choice);
        }

        // ADDING COMPONENTS
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(grid);
        c.gridx = 0;
        c.gridy = 0;
        this.add(number, c);
        c.gridy += 1;
        this.add(question, c);
        c.anchor = GridBagConstraints.LINE_START;
        for (JRadioButton choice : choices) {
            c.gridy += 1;
            this.add(choice, c);
        }
    }

    /**
     * Constructs a multiple choice question card that already has user answers inputted. For display purposes only.
     * @param num the question number
     * @param outputText the output text to be displayed
     * @param userAnswer the user answer
     * @param actualAnswer the actual answer
     */
    public MultipleChoiceQuestionCard(int num, List<String> outputText, String userAnswer, String actualAnswer) {
        super(num);
        JLabel question = new JLabel(outputText.get(0));
        this.choices = new ArrayList<>();
        ButtonGroup group = new ButtonGroup();

        boolean isCorrect = actualAnswer.equalsIgnoreCase(userAnswer);
        JLabel status = generateStatus(isCorrect);

        for (int i = 1; i < outputText.size(); i++) {
            String choiceText = outputText.get(i);
            JRadioButton choice = new JRadioButton(choiceText);
            choice.setEnabled(false); // disabled

            if (choiceText.equalsIgnoreCase(actualAnswer)) {
                choice.setIcon(yes);
            }
            if (choiceText.equalsIgnoreCase(userAnswer)) {
                choice.setSelected(true);
                if (!isCorrect) {
                    choice.setIcon(no);
                }
            }
            choices.add(choice);
            group.add(choice);
        }

        // ADDING COMPONENTS
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        this.setLayout(grid);
        c.gridx = 0;
        c.gridy = 0;
        this.add(number, c);
        c.gridy += 1;
        this.add(status, c);
        c.gridy += 1;
        this.add(question, c);
        c.anchor = GridBagConstraints.LINE_START;
        for (JRadioButton choice : choices) {
            c.gridy += 1;
            this.add(choice, c);
        }
    }

    /**
     * When a multiple choice button is pressed, this method sets the user answer according to its source button.
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton source = (JRadioButton) e.getSource();
        this.setUserAnswer(source.getText());
    }
}
