package quizUseCase.screens;

import quizUseCase.*;
import quizUseCase.GUI.*;

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
    private JPanel panel;
    private Card card;

    // actions
    private enum Actions {
        RESTART, SUBMIT
    }

    public QuizScreen(QuizController controller, QuizSettingsResponseModel response, int flashcardSetID) {
        super("QUIZ TIME :)");

        this.controller = controller;
        this.flashcardSetID = flashcardSetID;

        this.userAnswers = new ArrayList<>();

        ArrayList<String> types = response.getTypes();
        ArrayList<ArrayList<String>> outputText = response.getOutputText();

        // GUI components
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBackground(Color.DARK_GRAY);

        JButton button = new JButton("HELLO");
        this.panel.add(button);

        this.add(this.panel);
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
