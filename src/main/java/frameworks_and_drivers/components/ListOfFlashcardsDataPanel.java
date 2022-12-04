package frameworks_and_drivers.components;


import frameworks_and_drivers.screens.EditFlashcardSetScreen;
import frameworks_and_drivers.database.DBGateway;
import data_access_use_case.entity_request_models.FlashcardDsRequestModel;
import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;
import frameworks_and_drivers.screens.CreateFlashcardScreen;
import frameworks_and_drivers.screens.EditorMainScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

/**
 * A panel that includes buttons for flashcard set editing and another JPanel that includes all the
 * FlashcardDataPanel objects.
 */
public class ListOfFlashcardsDataPanel extends JPanel implements ActionListener, WindowListener {
    private final DBGateway dbGateway;
    private final FlashcardSetDsRequestModel flashcardSet;
    final JFrame frame;

    final JPanel flashcardPanels;

    /**
     * Constructs a ListOfFlashcardsDataPanel object. Includes buttons for adding flashcards, editing flashcards and
     * refreshing the page.
     * @param dbGateway The database gateway to access the database information
     * @param flashcardData All the flashcard information that was retrieved from the database
     * @param flashcardSet The flashcard set information of this flashcard set
     * @param frame The EditorMainPage in which this panel lies
     */
    public ListOfFlashcardsDataPanel(DBGateway dbGateway, List<FlashcardDsRequestModel> flashcardData,
                                     FlashcardSetDsRequestModel flashcardSet, JFrame frame) {
        this.dbGateway = dbGateway;
        this.frame = frame;
        this.flashcardSet = flashcardSet;
        flashcardPanels = new JPanel();

        //Buttons for adding flashcard, editing flashcard set, and refresh.
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

        //If there are no flashcards we show a label that states that there are no flashcards.
        //If there are flashcards we show the flashcard information with a FlashcardDataPanel object.
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
        //flashcardPanels customization
        flashcardPanels.setLayout(new FlowLayout());
        flashcardPanels.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        flashcardPanels.setPreferredSize(new Dimension(1000,500));

        //This panel customization
        this.add(flashcardPanels);
        this.setLayout(new FlowLayout());
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setPreferredSize(new Dimension(500, 1000));
    }

    /**
     * Performs action when an event is listened to. When 'Refresh is clicked we close this frame and open a new
     * EditorMainPage. 'When 'Add flashcard' is clicked we create a FcCMain object, which we open the adding flashcard
     * page. When 'Edit Flashcard Set' is clicked we create a FCSetEditorMain object, which we open the edit flashcard
     * set page.
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("Refresh")){
            int flashcardSetId = flashcardSet.getFlashcardSetId();
            new EditorMainScreen(flashcardSetId);
            frame.dispose();
        } else if(event.getActionCommand().equals("Add Flashcard")){
            JFrame fcCreatePage = new CreateFlashcardScreen(flashcardSet.getFlashcardSetId());
            fcCreatePage.addWindowListener(this);
        } else if(event.getActionCommand().equals("Edit Flashcard Set")) {
            JFrame fcSetEditPage = new EditFlashcardSetScreen(dbGateway, flashcardSet);
            fcSetEditPage.addWindowListener(this);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {
        //When observing a window close we refresh the page
        frame.dispose();
        new EditorMainScreen(flashcardSet.getFlashcardSetId());
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
