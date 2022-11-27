package quiz_use_case.screens;

import quiz_use_case.*;
import quiz_use_case.GUI.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * GUI screen for the quiz settings.
 * Frameworks & Drivers
 * @author Anthony
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
    private final Slider numQuestionsSlider;
    private final Slider timerSlider;

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
        super("Quiz Settings", false);
        this.controller = controller;
        this.flashcardSetID = flashcardSetID;

        // making a database request for the number of flashcards
        QuizSettingsRequestModel request = new QuizSettingsRequestModel(this.flashcardSetID);
        QuizSettingsResponseModel response = this.controller.getNumFlashcards(request);
        int numFlashcards = response.getNumFlashcards();

        // LISTENER
        // listener
        ChangeListener listener = new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider) e.getSource();
                if (s.equals(numQuestionsSlider.slider)) {
                    numQuestionsSlider.updateValue();
                } else if (s.equals(timerSlider.slider)) {
                    timerSlider.updateValue();
                }
            }
        };

        // GUI COMPONENTS
        this.numQuestionsSlider = new Slider(1, numFlashcards, 4, 1, "Number of Questions:");
        this.numQuestionsSlider.addChangeListener(listener);

        this.timerButton = new JButton("Timer OFF");
        this.timerButton.setActionCommand(Actions.TIMER.name());
        this.timerButton.addActionListener(this);
        this.timerSlider = new Slider(0, 60, 30, 10, "Timer Duration:");
        this.timerSlider.setVisible(false);
        this.timerSlider.addChangeListener(listener);

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

        // ADDING COMPONENTS IN A LAYOUT
        JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.LINE_AXIS));
        timerPanel.add(timerButton);
        timerPanel.add(timerSlider);

        JPanel typesPanel = new JPanel();
        typesPanel.setLayout(new BoxLayout(typesPanel, BoxLayout.LINE_AXIS));
        typesPanel.setBorder(BorderFactory.createEmptyBorder(10,10, 10, 10));
        typesPanel.add(multipleChoiceButton);
        typesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        typesPanel.add(textEntryButton);
        typesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        typesPanel.add(trueFalseButton);

        JPanel submitPanel = new JPanel();
        submitPanel.add(submit);

        // adding everything
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.add(typesPanel);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.add(numQuestionsSlider);
        centerPanel.add(timerPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(submitPanel);

        Container contentPane = this.getContentPane();
        contentPane.add(leftPanel, BorderLayout.LINE_START);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.PAGE_END);
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
                this.timerSlider.setVisible(true);
            } else {
                this.timerButton.setText("Timer OFF");
                this.timerSlider.setVisible(false);
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
            int timerDuration = this.timerSlider.getValue();
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
                new QuizScreen(this.controller, response, this.flashcardSetID);
            }
        }
    }
}
