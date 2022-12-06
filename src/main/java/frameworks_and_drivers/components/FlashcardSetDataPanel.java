package frameworks_and_drivers.components;

import frameworks_and_drivers.screens.EditorMainScreen;
import frameworks_and_drivers.database.DBGateway;
import delete_flashcard_set_use_case.*;
import frameworks_and_drivers.screens.DeleteFlashcardSetScreen;
import frameworks_and_drivers.screens.HomeScreen;
import interface_adapters.controllers.DeleteFlashcardSetController;
import interface_adapters.controllers.QuizController;
import interface_adapters.controllers.StudySessionController;
import interface_adapters.presenters.DeleteFlashcardSetPresenter;
import interface_adapters.presenters.QuizPresenter;
import interface_adapters.presenters.StudySessionPresenter;
import login_and_signup_use_case.UserLoginResponseModel;
import quiz_use_case.*;
import frameworks_and_drivers.screens.QuizSettingsScreen;
import study_mode_use_case.*;
import frameworks_and_drivers.screens.StudySettingsScreen;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Abstract class that represents a panel for an individual flashcard set.
 * This panel includes buttons for study, edit, test, and delete.
 * Frameworks & Drivers
 * @author Justin Li
 */
public class FlashcardSetDataPanel extends JPanel implements WindowListener {
    /**
     * The main JFrame that the flashcard set data panel will lie in.
     */
    final HomeScreen home;

    /**
     * Creates a FlashcardSetDataPanel object that includes the title, description, flashcardSetId, gateway,
     * user, and home.
     * @param title the title of the flashcard set.
     * @param description the description of the flashcard set.
     * @param flashcardSetId the flashcard set id.
     * @param gateway the gateway to reach the flashcard set.
     * @param user the user that owns that flashcard set.
     * @param home the home screen containing the flashcard set panel.
     */
    public FlashcardSetDataPanel(String title, String description,
                                 int flashcardSetId, DBGateway gateway,
                                 UserLoginResponseModel user, HomeScreen home) {
        this.home = home;

        // Panel Construction
        Border border = BorderFactory.createTitledBorder("<html>"+title+"<html>");

        JLabel descriptionLabel = new JLabel("<html>"+description+"<html>");
        JPanel descriptionPanel = new JPanel();
        descriptionLabel.setPreferredSize(new Dimension(300,200));
        descriptionPanel.add(descriptionLabel);

        // Button Construction
        JPanel buttons = new JPanel();

        JButton study = new JButton("Study");
        buttons.add(study);

        JButton test = new JButton("Test");
        buttons.add(test);

        JButton edit = new JButton("Edit");
        JButton delete = new JButton("Delete");
        buttons.add(edit);
        buttons.add(delete);

        // Action listeners for edit, study, test, and delete buttons.
        edit.addActionListener((e) -> {
            home.setVisible(false);
            EditorMainScreen editor = new EditorMainScreen(flashcardSetId);
            editor.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent event) {
                    home.refresh();
                }
            });
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
            DeleteFlashcardSetOutputBoundary presenter = new DeleteFlashcardSetPresenter();
            DeleteFlashcardSetInputBoundary interactor = new DeleteFlashcardSetInteractor(gateway, presenter);
            DeleteFlashcardSetController controller = new DeleteFlashcardSetController(interactor);
            DeleteFlashcardSetScreen deleter = new DeleteFlashcardSetScreen(flashcardSetId, controller, user, gateway);
            deleter.addWindowListener(this);
        });
        this.add(descriptionPanel);
        this.add(buttons);
        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {
        // When a window is closed the page will be refreshed.
        home.refresh();
    }
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}
