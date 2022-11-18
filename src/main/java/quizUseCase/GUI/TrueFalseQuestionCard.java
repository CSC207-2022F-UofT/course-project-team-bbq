package quizUseCase.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class TrueFalseQuestionCard extends QuestionCard {
    private final JButton trueButton;
    private final JButton falseButton;

    private enum Actions {
        TRUE, FALSE
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String c = e.getActionCommand();
        if (c.equals(Actions.TRUE.name())) {
            this.setUserAnswer("true");
            this.trueButton.setText("TRUE SELECTED!!!!");
            this.falseButton.setText("FALSE");
        } else if (c.equals(Actions.FALSE.name())) {
            this.setUserAnswer("false");
            this.trueButton.setText("TRUE");
            this.falseButton.setText("FALSE SELECTED!!!!");
        }
    }
}
