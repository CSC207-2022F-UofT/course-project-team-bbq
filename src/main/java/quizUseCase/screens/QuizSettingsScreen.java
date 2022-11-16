package quizUseCase.screens;

import quizUseCase.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Frameworks & Drivers
 */
public class QuizSettingsScreen extends Screen {
    private final QuizController controller;

    // default values (later on default settings should be pulled directly from the database)
    private boolean timerOn = false;
    private boolean multipleChoiceOn = true;
    private boolean textEntryOn = true;
    private boolean trueFalseOn = true;

    private final int flashcardSetID;

    // GUI components
    private final JSlider numQuestionsSlider;
    private final JSlider timerDurationSlider;

    private final JLabel timerDurationLabel;

    private final JButton timerButton;
    private final JButton multipleChoiceButton;
    private final JButton textEntryButton;
    private final JButton trueFalseButton;

    // actions
    private enum Actions {
        TIMER, MULTIPLE_CHOICE, TEXT_ENTRY, TRUE_FALSE, SUBMIT
    }

    /**
     * Initializes the quiz settings screen.
     * Precondition: The flashcard set belonging to flashcardSetID contains at least 4 flashcards.
     * @param controller the quiz settings controller
     * @param flashcardSetID the flashcard set ID
     */
    public QuizSettingsScreen(QuizController controller, int flashcardSetID) {
        super("Quiz Settings");

        this.controller = controller;
        this.flashcardSetID = flashcardSetID;

        // making a database request for the number of flashcards
        QuizSettingsRequestModel request = new QuizSettingsRequestModel(this.flashcardSetID);
        QuizSettingsResponseModel response = this.controller.getNumFlashcards(request);
        int numFlashcards = response.getNumFlashcards();

        // USER INPUT
        JLabel numQuestionsLabel = new JLabel("Number of Questions");
        this.numQuestionsSlider = new JSlider(1, numFlashcards, 4);
        this.numQuestionsSlider.setPaintTicks(true);
        this.numQuestionsSlider.setPaintLabels(true);
        this.numQuestionsSlider.setMinorTickSpacing(1);
        this.numQuestionsSlider.setMajorTickSpacing(1);

        this.timerButton = new JButton("Timer OFF");
        this.timerButton.setActionCommand(Actions.TIMER.name());
        this.timerButton.addActionListener(this);

        this.timerDurationLabel = new JLabel("Timer Duration");
        this.timerDurationSlider = new JSlider(0, 60, 30);
        this.timerDurationSlider.setPaintTicks(true);
        this.timerDurationSlider.setPaintLabels(true);
        this.timerDurationSlider.setMinorTickSpacing(1);
        this.timerDurationSlider.setMajorTickSpacing(10);
        this.timerDurationLabel.setVisible(false);
        this.timerDurationSlider.setVisible(false);

        this.multipleChoiceButton = new JButton("Multiple Choice ON");
        this.multipleChoiceButton.setActionCommand(Actions.MULTIPLE_CHOICE.name());
        this.multipleChoiceButton.addActionListener(this);

        this.textEntryButton = new JButton("Text Entry ON");
        this.textEntryButton.setActionCommand(Actions.TEXT_ENTRY.name());
        this.textEntryButton.addActionListener(this);

        this.trueFalseButton = new JButton("True False ON");
        this.trueFalseButton.setActionCommand(Actions.TRUE_FALSE.name());
        this.trueFalseButton.addActionListener(this);

        JButton submit = new JButton("Submit");
        submit.setActionCommand(Actions.SUBMIT.name());
        submit.addActionListener(this);

        // ADDING BUTTONS
        this.getContentPane().add(numQuestionsLabel);
        this.getContentPane().add(numQuestionsSlider);

        this.getContentPane().add(timerButton);

        this.getContentPane().add(timerDurationLabel);
        this.getContentPane().add(timerDurationSlider);

        this.getContentPane().add(multipleChoiceButton);
        this.getContentPane().add(textEntryButton);
        this.getContentPane().add(trueFalseButton);

        this.getContentPane().add(submit);

        this.setupScreen();
    }

    /**
     * Performs an action according to the button command.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals(Actions.TIMER.name())) {
            this.timerOn = !(this.timerOn);
            if (this.timerOn) {
                this.timerButton.setText("Timer ON");
                this.timerDurationLabel.setVisible(true);
                this.timerDurationSlider.setVisible(true);
            } else {
                this.timerButton.setText("Timer OFF");
                this.timerDurationLabel.setVisible(false);
                this.timerDurationSlider.setVisible(false);
            }
        } else if (command.equals(Actions.MULTIPLE_CHOICE.name())) {
            this.multipleChoiceOn = !(this.multipleChoiceOn);
            if (this.multipleChoiceOn) {
                this.multipleChoiceButton.setText("Multiple Choice ON");
            } else {
                this.multipleChoiceButton.setText("Multiple Choice OFF");
            }
        } else if (command.equals(Actions.TEXT_ENTRY.name())) {
            this.textEntryOn = !(this.textEntryOn);
            if (this.textEntryOn) {
                this.textEntryButton.setText("Text Entry ON");
            } else {
                this.textEntryButton.setText("Text Entry OFF");
            }
        } else if (command.equals(Actions.TRUE_FALSE.name())) {
            this.trueFalseOn = !(this.trueFalseOn);
            if (this.trueFalseOn) {
                this.trueFalseButton.setText("True False ON");
            } else {
                this.trueFalseButton.setText("True False OFF");
            }
        } else if (command.equals(Actions.SUBMIT.name())) {
            int numQuestions = this.numQuestionsSlider.getValue();
            int timerDuration = this.timerDurationSlider.getValue();
            QuizSettingsRequestModel request = new QuizSettingsRequestModel(numQuestions, this.timerOn,
                    timerDuration, this.multipleChoiceOn, this.textEntryOn, this.trueFalseOn, this.flashcardSetID);
            QuizSettingsResponseModel response = this.controller.startQuiz(request);

            if (response.isFailed()) {
                String error = response.getError();
                JOptionPane.showMessageDialog(this, error);
            } else {
                // transition to next screen
                this.setVisible(false);
                this.dispose();
                System.out.println("QUIZ TIME!!! AW YEA");
                new QuizScreen(this.controller, response, this.flashcardSetID);
            }
        }
    }
}
