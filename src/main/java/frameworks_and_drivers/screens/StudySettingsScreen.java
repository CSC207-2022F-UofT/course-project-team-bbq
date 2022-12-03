package frameworks_and_drivers.screens;

import interface_adapters.presenters.exceptions.StudySettingsFailed;
import interface_adapters.controllers.StudySessionController;
import study_mode_use_case.StudySettingsRequestModel;
import study_mode_use_case.StudySettingsResponseModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A screen where a user can set the study settings for their study session.
 * <p>
 * Frameworks & Drivers
 * @author Lucas Prates
 */
public class StudySettingsScreen extends Screen implements ActionListener {

    /**
     * The id of the flashcard set to study
     */
    private final int flashcardSetId;

    /**
     * The study mode controller
     */
    private final StudySessionController controller;

    /**
     * A string specifying the sorting order of the flashcard set
     */
    private String sortingOrder;

    /**
     * A boolean which specifies if the term or definition is displayed
     * by default
     */
    private boolean termIsDefault=true;

    /**
     * A boolean which specifies if the flashcard set is sorted in order or in reverse
     */
    private boolean isReverse=false;


    /**
     * Creates a StudySettingsScreen
     * @param controller handles the study mode use case
     * @param flashcardSetId the ID of the flashcard set the user wants to study
     */
    public StudySettingsScreen(StudySessionController controller, int flashcardSetId){
        super("Study Settings");

        this.controller = controller;
        this.flashcardSetId = flashcardSetId;

        // create drop down menu for setting the sorting order
        JPanel dropDownPanel = new JPanel();

        JLabel dropDownLabel = new JLabel("Sort by");
        String[] choices = {"Creation Date", "Alphabetical", "Random Shuffle"};

        // create button for reversing the order
        JLabel setReverseLabel = new JLabel("in");
        JButton setReverse = new JButton("order");
        setReverse.addActionListener( e -> {
            if (this.isReverse){
                setReverse.setText("order");
            }
            else {
                setReverse.setText("reverse");
            }
            this.isReverse = !this.isReverse;
        });

        JComboBox<String> dropDown = new JComboBox<>(choices);
        this.sortingOrder = StudySessionController.timeSort;
        dropDown.addActionListener(e -> {
            String choice = (String) dropDown.getSelectedItem();
            assert choice != null;
            if (choice.equals(choices[0])){
                this.sortingOrder = StudySessionController.timeSort;
                setReverseLabel.setVisible(true);
                setReverse.setVisible(true);
            }
            else if (choice.equals(choices[1])) {
                this.sortingOrder = StudySessionController.alphSort;
                setReverseLabel.setVisible(true);
                setReverse.setVisible(true);
            }
            else {
                this.sortingOrder = StudySessionController.shuffleSort;
                setReverseLabel.setVisible(false);
                setReverse.setVisible(false);
            }
        });
        dropDownPanel.add(dropDownLabel);
        dropDownPanel.add(dropDown);
        dropDownPanel.add(setReverseLabel);
        dropDownPanel.add(setReverse);


        // create panel for setting the default view
        JPanel setDefaultPanel = new JPanel();

        JLabel setDefaultLabel1 = new JLabel("Dislpay ");
        JLabel setDefaultLabel2 = new JLabel(" by default");
        JButton setDefault = new JButton("Term");

        setDefault.addActionListener(e -> {
            if (this.termIsDefault) {
                setDefault.setText("Definition");
            }
            else {
                setDefault.setText("Term");
            }
            this.termIsDefault = !this.termIsDefault;
        });

        setDefaultPanel.add(setDefaultLabel1);
        setDefaultPanel.add(setDefault);
        setDefaultPanel.add(setDefaultLabel2);

        // create panel for study button
        JPanel studyPanel = new JPanel();
        JLabel studyLabel = new JLabel("Click to begin:");
        JButton study = new JButton("Study");
        study.setText("Study");

        study.addActionListener(this);

        studyPanel.add(studyLabel);
        studyPanel.add(study);

        this.add(dropDownPanel);
        this.add(setDefaultPanel);
        this.add(studyPanel);

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(350,200);
        this.setVisible(true);//making the frame visible

        // quit if you close the tab
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Calls the controller's getSetToStudy method with a StudySettingsRequestModel
     * compiled based on the data the user input into this screen. If the flashcard set
     * is not empty, a study session screen is created. Otherwise, a StudySettingsFailure
     * screen is created
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        StudySettingsRequestModel request;

        // create the appropriate request model
        if (sortingOrder.equals(StudySessionController.shuffleSort)) {
            request = new StudySettingsRequestModel(flashcardSetId, sortingOrder,
                    termIsDefault);
        }
        else {
            request = new StudySettingsRequestModel(flashcardSetId, sortingOrder,
                    termIsDefault, isReverse);
        }


        // close this screen
        setVisible(false);
        dispose();

        // get the response model
        try {
            StudySettingsResponseModel response = this.controller.getSetToStudy(request);
            new StudySessionScreen(this.controller, response);
        }
        catch (StudySettingsFailed err) {
            JOptionPane.showMessageDialog(this, err.getMessage());
        }

    }
}
