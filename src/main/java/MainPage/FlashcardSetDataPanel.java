package MainPage;

import editor_main_page.EditorMainPage;
import dataAccess.DBGateway;
import delete_flashcardset_use_case.*;
import login_and_signup_use_case.UserLoginResponseModel;
import quizUseCase.*;
import quizUseCase.screens.QuizSettingsScreen;
import studyMode.*;
import studyMode.screens.StudySettingsScreen;

import javax.swing.*;
import javax.swing.border.Border;

public class FlashcardSetDataPanel extends JPanel {

    public FlashcardSetDataPanel(String title, String description,
                                 int flashcardSetId, DBGateway gateway,
                                 UserLoginResponseModel user) {
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


        buttons.add(edit);
        buttons.add(delete);

        edit.addActionListener((e) -> new EditorMainPage(flashcardSetId));
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
        delete.addActionListener(e -> {
            DelFlashcardSetOutputBoundary presenter = new DelFlashcardSetPresenter();
            DelFlashcardSetInputBoundary interactor = new DelFlashcardSetInteractor(gateway, presenter);
            DelFlashcardSetController controller = new DelFlashcardSetController(interactor);
            new DeletionScreen(flashcardSetId, controller, user);
        });

        this.add(buttons);
        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }


}
