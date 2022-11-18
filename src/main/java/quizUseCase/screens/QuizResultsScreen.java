package quizUseCase.screens;

import quizUseCase.*;
import quizUseCase.GUI.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * GUI screen for quiz results.
 * Frameworks & Drivers
 * @author Anthony
 */
public class QuizResultsScreen extends Screen {
    private final QuizController controller;

    private final int flashcardSetID;

    private final ArrayList<QuestionCard> questionCards;

    // actions
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
        this.questionCards = new ArrayList<>();

        ArrayList<String> types = response.getTypes();
        ArrayList<ArrayList<String>> outputText = response.getOutputText();
        ArrayList<String> userAnswers = response.getUserAnswers();
        ArrayList<String> actualAnswers = response.getActualAnswers();

        // NORTH PANEL
        int correct = response.getScore();
        int total = response.getNumQuestions();
        int incorrect = total - correct;
        double percentage = (((double) correct) / total) * 100;

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
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < types.size(); i++) {
            String type = types.get(i);
            QuestionCard q;
            if (type.equals("MC")) {
                q = new MultipleChoiceQuestionCard(outputText.get(i), userAnswers.get(i), actualAnswers.get(i));
                this.questionCards.add(q);
                centerPanel.add(q);
            } else if (type.equals("TE")) {
                q = new TextEntryQuestionCard(outputText.get(i), userAnswers.get(i), actualAnswers.get(i));
                this.questionCards.add(q);
                centerPanel.add(q);
            } else if (type.equals("TF")) {
                q = new TrueFalseQuestionCard(outputText.get(i), userAnswers.get(i), actualAnswers.get(i));
                this.questionCards.add(q);
                centerPanel.add(q);
            }
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
