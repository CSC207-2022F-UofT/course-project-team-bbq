package quizUseCase.screens;

import quizUseCase.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Frameworks & Drivers
 */
public class QuizResultsScreen extends Screen {
    QuizController controller;

    public QuizResultsScreen(QuizController controller, QuizResponseModel response) {
        super("Quiz Results", false);

        this.controller = controller;

        int score = response.getScore();
        int numQuestions = response.getNumQuestions();
        double percentage = (((double) score) / numQuestions) * 100;

        JLabel scoreLabel = new JLabel(Integer.toString(score));
        JLabel numQuestionsLabel = new JLabel(Integer.toString(numQuestions));
        JLabel percentageLabel = new JLabel(Double.toString(percentage) + "%");

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        centerPanel.add(scoreLabel);
        centerPanel.add(numQuestionsLabel);
        centerPanel.add(percentageLabel);

        // CONTENT PANE
        Container contentPane = this.getContentPane();
        contentPane.add(centerPanel, BorderLayout.CENTER);
        this.setupScreen();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
