package frameworks_and_drivers.components;

import frameworks_and_drivers.screens.EditFlashcardScreen;
import frameworks_and_drivers.database.DBGateway;
import data_access_use_case.entity_request_models.FlashcardDsRequestModel;
import frameworks_and_drivers.screens.DeleteFlashcardScreen;
import frameworks_and_drivers.screens.EditorMainScreen;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * The panel for an individual flashcard. Includes buttons for deleting and editing flashcard for this flashcard.
 */
public class FlashcardDataPanel extends JPanel implements ActionListener, WindowListener {
    private final DBGateway dbGateway;
    private final FlashcardDsRequestModel flashcard;
    private final int flashcardSetId;
    JFrame frame;
    /**
     * Constructs a FlashcardDataPanel object that includes flashcard information and editing buttons.
     */
    public FlashcardDataPanel(DBGateway dbGateway, FlashcardDsRequestModel flashcard, int flashcardSetId, JFrame frame){
        this.dbGateway = dbGateway;
        this.frame = frame;
        this.flashcard = flashcard;
        this.flashcardSetId = flashcardSetId;

        //Includes flashcard information.
        Border border = BorderFactory.createTitledBorder(flashcard.getTerm());
        JLabel descriptionLabel = new JLabel(flashcard.getDefinition());
        this.add(descriptionLabel);

        //Buttons for this flashcard panel.
        JPanel buttons = new JPanel();
        JButton edit = new JButton("Edit Flashcard");
        buttons.add(edit);
        JButton delete = new JButton("Delete Flashcard");
        buttons.add(delete);
        edit.addActionListener(this);
        delete.addActionListener(this);

        this.add(buttons);
        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Performs action when an event is listened to. When 'Edit flashcard' is clicked we create a FlashcardEditorMain
     * object, which we open the flashcard editing page. When delete flashcard is clicked we create a FcRMain object,
     * which we open the delete flashcard page.
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("Edit Flashcard")){
            JFrame fcEditPage = new EditFlashcardScreen(dbGateway, flashcard);
            fcEditPage.addWindowListener(this);
        }
        else if(event.getActionCommand().equals("Delete Flashcard")){
            JFrame fcRemovePage = new DeleteFlashcardScreen(flashcardSetId, flashcard.getFlashcardId());
            fcRemovePage.addWindowListener(this);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {
        //When observing a window close we refresh the page.
        frame.dispose();
        WindowListener wl = frame.getWindowListeners()[0];
        EditorMainScreen editor = new EditorMainScreen(flashcardSetId);
        editor.addWindowListener(wl);
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
