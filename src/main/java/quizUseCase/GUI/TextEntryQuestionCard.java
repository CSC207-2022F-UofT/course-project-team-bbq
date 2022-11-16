package quizUseCase.GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TextEntryQuestionCard extends QuestionCard {
    public TextEntryQuestionCard(ArrayList<String> outputText) {
        if (outputText.get(0) == null) { // missing term
            JTextField term = new JTextField();
            JLabel definition = new JLabel(outputText.get(1));

            // adding components
            this.setLayout(new FlowLayout());
            this.add(term);
            this.add(definition);
        } else { // missing definition
            JLabel term = new JLabel(outputText.get(0));
            JTextField definition = new JTextField();

            // adding components
            this.setLayout(new FlowLayout());
            this.add(term);
            this.add(definition);
        }
    }
}
