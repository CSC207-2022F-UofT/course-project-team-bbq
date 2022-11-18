package MainPage;

import dataAccess.DBGateway;
import quizUseCase.*;
import quizUseCase.screens.QuizSettingsScreen;
import studyMode.*;
import studyMode.screens.StudySettingsScreen;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class FlashcardSetDataPanel extends JPanel {

    boolean editable;
    public FlashcardSetDataPanel(String title, String description,
                                 int flashcardSetId, DBGateway gateway, boolean editable) {
        Border border = BorderFactory.createTitledBorder(title);


        JLabel descriptionLabel = new JLabel(description);
        this.add(descriptionLabel);

        JPanel buttons = new JPanel();

        JButton study = new JButton("Study");
        buttons.add(study);
        JButton test = new JButton("Test");
        buttons.add(test);
        JButton edit = new JButton("Edit");
        JButton delete = new JButton("Delete");

        if (editable) {
            buttons.add(edit);
            buttons.add(delete);
        }

        study.addActionListener(e -> {
            StudySessionOutputBoundary presenter = new StudySessionPresenter();
            StudySessionInputBoundary interactor = new StudySessionInteractor(gateway, presenter);
            StudySessionController controller = new StudySessionController(interactor);
            new StudySettingsScreen(controller, flashcardSetId);
        });
        test.addActionListener(e -> {
            QuizOutputBoundary presenter = new QuizPresenter();
            QuizInputBoundary interactor = new QuizInteractor(gateway, presenter);
            QuizController controller = new QuizController(interactor);

            new QuizSettingsScreen(controller, flashcardSetId);
        });

        this.add(buttons);
        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }


}
