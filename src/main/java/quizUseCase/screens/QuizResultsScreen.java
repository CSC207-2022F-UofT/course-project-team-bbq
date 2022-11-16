package quizUseCase.screens;

import quizUseCase.*;

import java.awt.event.ActionEvent;

/**
 * Frameworks & Drivers
 */
public class QuizResultsScreen extends Screen {
    QuizController controller;

    public QuizResultsScreen(QuizController controller, QuizResponseModel response) {
        super("Quiz Results", false);

        this.controller = controller;

        this.setupScreen();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
