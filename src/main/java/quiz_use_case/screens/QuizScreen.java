package quiz_use_case.screens;

import quiz_use_case.*;
import quiz_use_case.GUI.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * GUI screen for the quiz.
 * Frameworks & Drivers
 * @author Anthony
 */
public class QuizScreen extends Screen {
    private final QuizController controller;

    private final int flashcardSetID;

    private final ArrayList<QuestionCard> questionCards;

    // actions
    private enum Actions {
        RESTART, SUBMIT
    }

    public QuizScreen(QuizController controller, QuizSettingsResponseModel response, int flashcardSetID) {
        super("Quiz", false);

        this.controller = controller;
        this.flashcardSetID = flashcardSetID;

        this.questionCards = new ArrayList<>();

        ArrayList<String> types = response.getTypes();
        ArrayList<ArrayList<String>> outputText = response.getOutputText();

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
            String type = types.get(i);
            QuestionCard q;
            c.gridy = i;
            if (type.equals("MC")) {
                q = new MultipleChoiceQuestionCard(i+1, outputText.get(i));
                this.questionCards.add(q);
                centerPanel.add(q, c);
            } else if (type.equals("TE")) {
                q = new TextEntryQuestionCard(i+1, outputText.get(i));
                this.questionCards.add(q);
                centerPanel.add(q, c);
            } else if (type.equals("TF")) {
                q = new TrueFalseQuestionCard(i+1, outputText.get(i));
                this.questionCards.add(q);
                centerPanel.add(q, c);
            }
        }

        // SCROLL BAR
        JScrollPane scroll = new JScrollPane(centerPanel);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // BOTTOM PANEL
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        JButton restart = new JButton("Restart");
        restart.setActionCommand(Actions.RESTART.name());
        restart.addActionListener(this);
        bottomPanel.add(restart);
        JButton submit = new JButton("Submit");
        submit.setActionCommand(Actions.SUBMIT.name());
        submit.addActionListener(this);
        bottomPanel.add(submit);

        // CONTENT PANE
        Container contentPane = this.getContentPane();
        contentPane.add(scroll, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.PAGE_END);
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
            // collect all the user answers
            ArrayList<String> userAnswers = new ArrayList<>();
            for (QuestionCard q : this.questionCards) {
                if (q instanceof TextEntryQuestionCard) {
                    ((TextEntryQuestionCard)q).updateUserAnswer();
                }
                userAnswers.add(q.getUserAnswer());
            }

            // make a request based on user answers
            QuizRequestModel request = new QuizRequestModel(userAnswers);
            QuizResponseModel response = this.controller.getResults(request);

            if (response.isFailed()) {
                String error = response.getMessage();
                JOptionPane.showMessageDialog(this, error);
            } else if (response.needToConfirm()) {
                String message = response.getMessage();
                int input = JOptionPane.showConfirmDialog(null, message,
                        "WARNING", JOptionPane.YES_NO_OPTION);
                if (input == 0) { // continue onwards
                    this.setVisible(false);
                    this.dispose();
                    new QuizResultsScreen(this.controller, response, this.flashcardSetID);
                }
            } else {
                // transition into next screen
                this.setVisible(false);
                this.dispose();
                new QuizResultsScreen(this.controller, response, this.flashcardSetID);
            }
        }
    }
}
