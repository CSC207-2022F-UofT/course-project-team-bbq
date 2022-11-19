package quizUseCase.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * GUI panel that displays a multiple choice question.
 * Frameworks & Drivers
 * @author Anthony
 */
public class MultipleChoiceQuestionCard extends QuestionCard {
    private final ArrayList<JRadioButton> choices;

    /**
     * Constructs a multiple choice question card that is ready to receive user input.
     * @param outputText the output text to display within the card
     */
    public MultipleChoiceQuestionCard(ArrayList<String> outputText) {
        JLabel question = new JLabel(outputText.get(0));
        this.choices = new ArrayList<>();
        ButtonGroup group = new ButtonGroup();

        for (int i = 1; i < outputText.size(); i++) {
            JRadioButton choice = new JRadioButton(outputText.get(i));
            choice.addActionListener(this);
            choices.add(choice);
            group.add(choice);
        }

        // adding components
        this.setLayout(new FlowLayout());
        this.add(question);
        for (JRadioButton choice : choices) {
            this.add(choice);
        }
    }

    /**
     * Constructs a multiple choice question card that already has user answers inputted. For display purposes only.
     * @param outputText the output text to be displayed
     * @param userAnswer the user answer
     * @param actualAnswer the actual answer
     */
    public MultipleChoiceQuestionCard(ArrayList<String> outputText, String userAnswer, String actualAnswer) {
        JLabel question = new JLabel(outputText.get(0));
        this.choices = new ArrayList<>();
        ButtonGroup group = new ButtonGroup();

        boolean isCorrect = actualAnswer.equals(userAnswer);
        JLabel status = generateStatus(isCorrect);

        for (int i = 1; i < outputText.size(); i++) {
            String choiceText = outputText.get(i);
            JRadioButton choice = new JRadioButton(choiceText);
            choice.setEnabled(false); // disabled

            if (choiceText.equals(actualAnswer)) {
                choice.setIcon(yes);
            }
            if (choiceText.equals(userAnswer)) {
                choice.setSelected(true);
                if (!isCorrect) {
                    choice.setIcon(no);
                }
            }
            choices.add(choice);
            group.add(choice);
        }

        // adding components
        this.setLayout(new FlowLayout());
        this.add(status);
        this.add(question);
        for (JRadioButton choice : choices) {
            this.add(choice);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton source = (JRadioButton) e.getSource();
        this.setUserAnswer(source.getText());
    }
}
