package main_page;

import editor_main_page.EditorMainPage;
import data_access.DBGateway;
import delete_flashcard_set_use_case.*;
import login_and_signup_use_case.UserLoginResponseModel;
import quiz_use_case.*;
import quiz_use_case.screens.QuizSettingsScreen;
import study_mode_use_case.*;
import study_mode_use_case.screens.StudySettingsScreen;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FlashcardSetDataPanel extends JPanel implements WindowListener {
    HomePage home;
    public FlashcardSetDataPanel(String title, String description,
                                 int flashcardSetId, DBGateway gateway,
                                 UserLoginResponseModel user, HomePage home) {
        Border border = BorderFactory.createTitledBorder(title);


        JLabel descriptionLabel = new JLabel(description);
        this.add(descriptionLabel);
        this.home = home;
        JPanel buttons = new JPanel();

        JButton study = new JButton("Study");
        buttons.add(study);
        JButton test = new JButton("Test");
        buttons.add(test);
        JButton edit = new JButton("Edit");
        JButton delete = new JButton("Delete");


        buttons.add(edit);
        buttons.add(delete);

        edit.addActionListener((e) -> {
            EditorMainPage editor = new EditorMainPage(flashcardSetId);
            editor.addWindowListener(this);
        });

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
            DeletionScreen deleter = new DeletionScreen(flashcardSetId, controller, user, gateway);
            deleter.addWindowListener(this);
        });

        this.add(buttons);
        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        home.refresh();

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
