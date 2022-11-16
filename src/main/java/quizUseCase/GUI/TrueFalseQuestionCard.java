package quizUseCase.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TrueFalseQuestionCard extends QuestionCard implements ActionListener {
    private boolean value;

    private enum Actions {
        TRUE, FALSE
    }

    public TrueFalseQuestionCard(ArrayList<String> outputText) {
        JLabel term = new JLabel(outputText.get(0));
        JLabel potentialDefinition = new JLabel(outputText.get(1));
        JButton trueButton = new JButton("TRUE");
        JButton falseButton = new JButton("FALSE");

        this.setLayout(new FlowLayout());
        this.add(term);
        this.add(potentialDefinition);
        this.add(trueButton);
        this.add(falseButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        if (c.equals(Actions.TRUE.name())) {

        }
    }
}
