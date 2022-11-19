package EditorMainPage;


import Editor.FlashcardSet.FCSetEditorMain;
import dataAccess.DBGateway;
import entityRequestModels.FlashcardDsRequestModel;
import entityRequestModels.FlashcardSetDsRequestModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class ListOfFlashcardsDataPanel extends JPanel implements ActionListener, WindowListener {
    private final DBGateway dbGateway;
    private final FlashcardSetDsRequestModel flashcardSet;
    JFrame frame;

    public ListOfFlashcardsDataPanel(DBGateway dbGateway, List<FlashcardDsRequestModel> flashcardData, FlashcardSetDsRequestModel flashcardSet, JFrame frame) {
        this.dbGateway = dbGateway;
        this.frame = frame;
        this.flashcardSet = flashcardSet;

        JPanel buttons = new JPanel();
        JButton addFlashcard = new JButton("Add Flashcard");
        buttons.add(addFlashcard);
        JButton edit = new JButton("Edit Flashcard Set");
        buttons.add(edit);
        JButton refresh = new JButton("Refresh");
        buttons.add(refresh);

        addFlashcard.addActionListener(this);
        edit.addActionListener(this);
        refresh.addActionListener(this);

        this.add(buttons);

        for (FlashcardDsRequestModel flashcard : flashcardData) {
            this.add(new FlashcardDataPanel(dbGateway, flashcard, flashcardSet.getFlashcardSetId(),frame));
        }
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("Refresh")){
            int flashcardSetId = flashcardSet.getFlashcardSetId();
            new EditorMainPage(dbGateway, flashcardSetId);
            frame.dispose();
        } else if(event.getActionCommand().equals("Add Flashcard")){
            // Soon to add line below. Once add flashcard use case is done
            //FcCMain(flashcardSetId);
        } else if(event.getActionCommand().equals("Edit Flashcard Set")) {
            JFrame fcSetEditPage = new FCSetEditorMain(dbGateway, flashcardSet);
            fcSetEditPage.addWindowListener(this);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {
        frame.dispose();
        new EditorMainPage(dbGateway, flashcardSet.getFlashcardSetId());
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
