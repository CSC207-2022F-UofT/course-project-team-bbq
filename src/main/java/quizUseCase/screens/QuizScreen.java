package quizUseCase.screens;

import quizUseCase.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Frameworks & Drivers
 */
public class QuizScreen extends Screen {
    private QuizController controller;

    // user input values
    private ArrayList<String> userAnswers;

    private final int flashcardSetID;

    // GUI components

    // actions
    private enum Actions {
        RESTART, SUBMIT
    }

    public QuizScreen(QuizController controller, QuizSettingsResponseModel response, int flashcardSetID) {
        super("QUIZ TIME :)");

        this.controller = controller;
        this.flashcardSetID = flashcardSetID;

        this.userAnswers = new ArrayList<>();

        // I want to get information to display
        // I need question type

        // scrollbar
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);

        this.setLayout(null);
        this.setupScreen();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(Actions.RESTART.name())) {
            this.setVisible(false);
            this.dispose();
            new QuizSettingsScreen(this.controller, this.flashcardSetID);
        } else if (command.equals(Actions.SUBMIT.name())) {
            QuizRequestModel request = new QuizRequestModel(userAnswers);
            QuizResponseModel response = this.controller.getResults(request);

            if (response.isFailed()) {
                String error = response.getError();
                JOptionPane.showMessageDialog(this, error);
            } else {
                // transition into next screen
                this.setVisible(false);
                this.dispose();
                System.out.println("RESULTS TIME!!! AW YEA");
                new QuizResultsScreen(this.controller, response);
            }
        }
    }
}
