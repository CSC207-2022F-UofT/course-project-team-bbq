package frameworks_and_drivers.screens;

import frameworks_and_drivers.components.FlatLabel;
import frameworks_and_drivers.components.QuestionCard;
import frameworks_and_drivers.components.QuestionCardFactory;
import interface_adapters.controllers.QuizController;
import quiz_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * GUI screen for quiz results.
 * Frameworks & Drivers
 * @author Anthony
 */
public class QuizResultsScreen extends QuizUseCaseScreen {
    private final QuizController controller;
    private final int flashcardSetID;

    private enum Actions {
        RESTART
    }

    /**
     * Constructs a quiz results screen based on the following parameters.
     * @param controller the quiz controller
     * @param response the quiz response model
     * @param flashcardSetID the flashcard set ID
     */
    public QuizResultsScreen(QuizController controller, QuizResponseModel response, int flashcardSetID) {
        super("Quiz Results", false);

        this.controller = controller;
        this.flashcardSetID = flashcardSetID;
        QuestionCardFactory questionCardFactory = new QuestionCardFactory();

        List<String> types = response.getTypes();
        List<List<String>> outputText = response.getOutputText();
        List<String> userAnswers = response.getUserAnswers();
        List<String> actualAnswers = response.getActualAnswers();

        // NORTH PANEL
        int correct = response.getScore();
        int total = response.getNumQuestions();
        int incorrect = total - correct;
        int percentage = (int) Math.round((((double) correct) / total) * 100);

        FlatLabel correctLabel = new FlatLabel((correct) + " Correct", "h00");
        FlatLabel incorrectLabel = new FlatLabel((incorrect) + " Incorrect", "h00");
        FlatLabel totalLabel = new FlatLabel((total) + " Total", "h00");
        FlatLabel percentageLabel = new FlatLabel((percentage) + "%", "h00");

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
        northPanel.add(correctLabel);
        northPanel.add(incorrectLabel);
        northPanel.add(totalLabel);
        northPanel.add(percentageLabel);

        // CENTER PANEL
        JPanel centerPanel = new JPanel();
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        centerPanel.setLayout(grid);
        c.gridx = 0;
        c.insets = new Insets(25, 25, 25, 25);
        c.ipadx = 25;
        c.ipady = 25;
        c.fill = GridBagConstraints.BOTH;

        for (int i = 0; i < types.size(); i++) {
            c.gridy = i;
            QuestionCard q = questionCardFactory.createAnsweredQuestionCard(types.get(i), i+1, outputText.get(i),
                    userAnswers.get(i), actualAnswers.get(i));
            centerPanel.add(q, c);
        }

        // SCROLL BAR
        JScrollPane scroll = new JScrollPane(centerPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // SOUTH PANEL
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());
        JButton restartButton = new JButton("Restart");
        restartButton.setActionCommand(Actions.RESTART.name());
        restartButton.addActionListener(this);
        southPanel.add(restartButton);

        // CONTENT PANE
        Container contentPane = this.getContentPane();
        contentPane.add(northPanel, BorderLayout.NORTH);
        contentPane.add(scroll, BorderLayout.CENTER);
        contentPane.add(southPanel, BorderLayout.SOUTH);
        this.setupScreen();
    }

    /**
     * Restarts back to quiz settings screen on command.
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(Actions.RESTART.name())) {
            this.setVisible(false);
            this.dispose();
            new QuizSettingsScreen(this.controller, this.flashcardSetID);
        }
    }
}
