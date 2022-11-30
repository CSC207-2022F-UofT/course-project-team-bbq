package frameworks_and_drivers.screens;

import frameworks_and_drivers.components.FlatLabel;
import frameworks_and_drivers.components.QuestionCard;
import frameworks_and_drivers.components.QuestionCardFactory;
import frameworks_and_drivers.components.TextEntryQuestionCard;
import interface_adapters.controllers.QuizController;
import quiz_use_case.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * GUI screen for the quiz.
 * Frameworks & Drivers
 * @author Anthony
 */
public class QuizScreen extends QuizUseCaseScreen {
    private final QuizController controller;
    private final int flashcardSetID;
    private final List<QuestionCard> questionCards;

    // timer components
    private Timer timer;
    private long startTime = -1;
    private long duration;
    private FlatLabel timerLabel;
    private final SimpleDateFormat df = new SimpleDateFormat("mm:ss:SSS");

    private enum Actions {
        RESTART, SUBMIT, UPDATE_TIMER
    }

    /**
     * Constructs a quiz screen.
     * @param controller the quiz controller
     * @param response the quiz settings response model
     * @param flashcardSetID the flashcard set ID
     */
    public QuizScreen(QuizController controller, QuizSettingsResponseModel response, int flashcardSetID) {
        super("Quiz", false);

        this.controller = controller;
        this.flashcardSetID = flashcardSetID;
        this.questionCards = new ArrayList<>();
        QuestionCardFactory questionCardFactory = new QuestionCardFactory();

        List<String> types = response.getTypes();
        List<List<String>> outputText = response.getOutputText();

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
            QuestionCard q = questionCardFactory.createQuestionCard(types.get(i), i+1, outputText.get(i));
            this.questionCards.add(q);
            centerPanel.add(q, c);
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

        // TIMER
        if (response.isTimerOn()) {
            JPanel timerPanel = new JPanel();
            this.duration = response.getTimerDuration() * 60000L;
            this.timer = new Timer(250, this);
            this.timer.setActionCommand(Actions.UPDATE_TIMER.name());
            this.timer.setInitialDelay(1000); // delay between timer start and first event detection
            this.timerLabel = new FlatLabel(df.format(duration), "h0");
            timerPanel.add(timerLabel);
            contentPane.add(timerPanel, BorderLayout.PAGE_START);
            this.timer.start();
        }
        this.setupScreen();
    }

    /**
     * RESTART          the program returns to the quiz settings screen.
     * SUBMIT           the program collects all user answers and proceeds to the quiz results screen.
     * UPDATE_TIMER     the program updates the timer. If the timer runs out of time, then the program automatically
     *                  proceeds to the quiz results screen without any user input.
     * @param e         the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(Actions.RESTART.name())) {
            if (timer != null) {
                timer.stop();
            }
            this.setVisible(false);
            this.dispose();
            new QuizSettingsScreen(this.controller, this.flashcardSetID);
        } else if (command.equals(Actions.SUBMIT.name())) {
            QuizResponseModel response = collectUserAnswers();

            if (response.isFailed()) {
                String error = response.getMessage();
                JOptionPane.showMessageDialog(this, error);
            } else if (response.needToConfirm()) {
                String message = response.getMessage();
                int input = JOptionPane.showConfirmDialog(null, message,
                        "WARNING", JOptionPane.YES_NO_OPTION);
                if (input == 0) { // continue onwards
                    if (timer != null) {
                        timer.stop();
                    }

                    // transition into next screen
                    this.setVisible(false);
                    this.dispose();
                    new QuizResultsScreen(this.controller, response, this.flashcardSetID);
                }
            } else {
                if (timer != null) {
                    timer.stop();
                }

                // transition into next screen
                this.setVisible(false);
                this.dispose();
                new QuizResultsScreen(this.controller, response, this.flashcardSetID);
            }
        } else if (command.equals(Actions.UPDATE_TIMER.name())) {
            if (startTime < 0) {
                startTime = System.currentTimeMillis();
            }
            long now = System.currentTimeMillis();
            long clockTime = now - startTime;
            if (clockTime >= duration) {
                clockTime = duration;
                timer.stop();

                QuizResponseModel response = collectUserAnswers();

                // transition into next screen
                this.setVisible(false);
                this.dispose();
                new QuizResultsScreen(this.controller, response, this.flashcardSetID);
            }
            timerLabel.setText(df.format(duration - clockTime));
        }
    }

    /**
     * Private helper method that collects all the user answers and submits a quiz request in order to obtain a
     * corresponding quiz response model.
     * @return the quiz response model
     */
    private QuizResponseModel collectUserAnswers() {
        List<String> userAnswers = new ArrayList<>();
        for (QuestionCard q : this.questionCards) {
            if (q instanceof TextEntryQuestionCard) {
                ((TextEntryQuestionCard)q).updateUserAnswer();
            }
            userAnswers.add(q.getUserAnswer());
        }

        QuizRequestModel request = new QuizRequestModel(userAnswers);
        return this.controller.getResults(request);
    }
}
