package frameworks_and_drivers.screens;

import interface_adapters.controllers.StudySessionController;
import study_mode_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A screen where a user can flip the flashcard they are currently
 * looking at or go to the next/prev flashcard
 * <p>
 * Frameworks & Drivers
 * @author Lucas Prates
 */

public class StudySessionScreen extends Screen implements ActionListener {
    /**
     * the study session controller
     */
    private final StudySessionController controller;

    /**
     * a JButton which handles flipping the flashcard
     */
    private final JButton flip;

    /**
     * stores the length of the flashcard set
     */
    private final int numFlashcards;

    /**
     * stores the text to be written to the on-screen flashcard
     */
    private final JLabel cardLabel;

    /**
     * the id of the flashcard set being studied
     */
    private final int flashcardSetId;

    /**
     * Create a StudySessionScreen
     * @param controller handles the study mode use case
     * @param response contains the data required to prepare the view
     */
    public StudySessionScreen(StudySessionController controller,
                              StudySettingsResponseModel response) {
        super(response.getTitle());
        this.flashcardSetId = response.getFlashcardSetId();
        this.numFlashcards = response.getNumFlashcards();
        this.controller = controller;

        JPanel cLabelPanel = new JPanel();
        cardLabel = new JLabel("1 / " + this.numFlashcards);
        cLabelPanel.add(cardLabel, BorderLayout.SOUTH);

        this.flip = new JButton(response.getOutputText());//creating instance of JButton
        JButton next = new JButton("Next");
        JButton prev = new JButton("Previous");
        JButton quit = new JButton("Quit");
        JButton restart = new JButton("Restart");

        // set action commands so action listener knows what to do
        flip.setActionCommand(StudySessionController.flip);
        next.setActionCommand(StudySessionController.next);
        prev.setActionCommand(StudySessionController.prev);
        quit.setActionCommand("quit");
        restart.setActionCommand("restart");
        flip.addActionListener(this);
        next.addActionListener(this);
        prev.addActionListener(this);
        quit.addActionListener(this);
        restart.addActionListener(this);


        int width = 550;
        int height = 400;
        int cardWidth = 250;
        int cardHeight = 200;
        int x = (width - cardWidth) / 2;
        int y = (height - cardWidth) / 2;
        int bWidth = 100;
        int bHeight = 50;
        // set the button bounds on the screen
        flip.setBounds(x,y,cardWidth, cardHeight);
        next.setBounds(x+cardWidth+(x-bWidth)/2, (height)/2 - bHeight, bWidth, bHeight);
        prev.setBounds((x-bWidth)/2, height/2 - bHeight, bWidth, bHeight);
        quit.setBounds((width-2 * bWidth)/3, y + cardHeight + (y-bHeight)/2, bWidth, bHeight);
        restart.setBounds(2* (width-2 * bWidth)/3 + bWidth, y + cardHeight + (y-bHeight)/2, bWidth, bHeight);
        cLabelPanel.setBounds((width-bWidth)/2, (y-bHeight)/2, bWidth, bHeight);
        this.add(flip);//adding button in JFrame
        this.add(next);
        this.add(prev);
        this.add(quit);
        this.add(restart);
        this.add(cLabelPanel);


        this.setSize(width,height);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible
//        setLocationRelativeTo(null);

        // quit if you close the tab
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     *  If the user chose to quit, close this screen. If the user chose to restart,
     * close this screen and reopen a StudySettingsScreen. Otherwise, calls the
     * controller's study method with a StudySessionRequestModel compiled based on
     * the data the user input into this screen. The response data is used to update
     * this StudySessionScreen.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        StudySessionRequestModel request = new StudySessionRequestModel();
        String command = e.getActionCommand();
        if (command.equals("quit")){
            setVisible(false);
            dispose();
        }
        else if (command.equals("restart")) {
            setVisible(false);
            dispose();
            new StudySettingsScreen(controller, this.flashcardSetId);
        }
        else{
            request.setCommand(command);
            StudySessionResponseModel response = this.controller.study(request);
            this.flip.setText("<html>" + response.getOutputText() + "</html>");
            this.cardLabel.setText(response.getCardNumber() + " / "  + this.numFlashcards);
        }
    }
}
