package editor_main_page;

import edit_flashcard_use_case.FlashcardEditorMain;
import data_access.DBGateway;
import data_access.entity_request_models.FlashcardDsRequestModel;
import delete_flashcard_use_case.FcRMain;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FlashcardDataPanel extends JPanel implements ActionListener, WindowListener {
    private final DBGateway dbGateway;
    private final FlashcardDsRequestModel flashcard;
    private final int flashcardSetId;
    JFrame frame;
    public FlashcardDataPanel(DBGateway dbGateway, FlashcardDsRequestModel flashcard, int flashcardSetId, JFrame frame){
        this.dbGateway = dbGateway;
        this.frame = frame;
        this.flashcard = flashcard;
        this.flashcardSetId = flashcardSetId;


        Border border = BorderFactory.createTitledBorder(flashcard.getTerm());

        JLabel descriptionLabel = new JLabel(flashcard.getDefinition());

        this.add(descriptionLabel);

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


    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("Edit Flashcard")){
            JFrame fcEditPage = new FlashcardEditorMain(dbGateway, flashcard);
            fcEditPage.addWindowListener(this);
        }
        else if(event.getActionCommand().equals("Delete Flashcard")){
            JFrame fcRemovePage = new FcRMain(flashcardSetId, flashcard.getFlashcardId());
            fcRemovePage.addWindowListener(this);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {
        frame.dispose();
        new EditorMainPage(flashcardSetId);
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
