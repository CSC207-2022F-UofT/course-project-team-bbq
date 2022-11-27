package editor_main_page;


import edit_flashcard_set_use_case.FCSetEditorMain;
import data_access.DBGateway;
import data_access.entity_request_models.FlashcardDsRequestModel;
import data_access.entity_request_models.FlashcardSetDsRequestModel;
import create_flashcard_use_case.FcCMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class ListOfFlashcardsDataPanel extends JPanel implements ActionListener, WindowListener {
    private final DBGateway dbGateway;
    private final FlashcardSetDsRequestModel flashcardSet;
    JFrame frame;

    JPanel flashcardPanels;

    public ListOfFlashcardsDataPanel(DBGateway dbGateway, List<FlashcardDsRequestModel> flashcardData,
                                     FlashcardSetDsRequestModel flashcardSet, JFrame frame) {
        this.dbGateway = dbGateway;
        this.frame = frame;
        this.flashcardSet = flashcardSet;

        flashcardPanels = new JPanel();

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

        int numCards = flashcardData.size();
        if (numCards == 0){
            JLabel label = new JLabel("You have no Flashcards in this FlashcardSet.");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.TOP);
            JPanel labelPanel = new JPanel();
            labelPanel.add(label);
            flashcardPanels.add(labelPanel);
        }
        else{
            for (FlashcardDsRequestModel flashcard : flashcardData) {
                flashcardPanels.add(new FlashcardDataPanel(dbGateway, flashcard, flashcardSet.getFlashcardSetId(),frame));
            }
        }
        flashcardPanels.setLayout(new FlowLayout());
        flashcardPanels.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        flashcardPanels.setPreferredSize(new Dimension(1000,500));
        this.add(flashcardPanels);
        this.setLayout(new FlowLayout());
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setPreferredSize(new Dimension(500, 1000));
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("Refresh")){
            int flashcardSetId = flashcardSet.getFlashcardSetId();
            new EditorMainPage(flashcardSetId);
            frame.dispose();
        } else if(event.getActionCommand().equals("Add Flashcard")){
            JFrame fcCreatePage = new FcCMain(flashcardSet.getFlashcardSetId());
            fcCreatePage.addWindowListener(this);
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
        new EditorMainPage(flashcardSet.getFlashcardSetId());
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
