package quizUseCase.GUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class QuestionCard extends JPanel implements ActionListener {
    private String userAnswer = null;

    public String getUserAnswer() {
        return this.userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
