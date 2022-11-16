package quizUseCase.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MultipleChoiceQuestionCard extends QuestionCard {
    private final ArrayList<JRadioButton> choices;

    public MultipleChoiceQuestionCard(ArrayList<String> outputText) {
        JLabel question = new JLabel(outputText.get(0));
        this.choices = new ArrayList<>();
        ButtonGroup group = new ButtonGroup();

        for (int i = 1; i < outputText.size(); i++) {
            JRadioButton choice = new JRadioButton(outputText.get(i));
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

    public void addActionListener(ActionListener actionListener) {
        for (JRadioButton choice : this.choices) {
            choice.addActionListener(actionListener);
        }
    }
}
