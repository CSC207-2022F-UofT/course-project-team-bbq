package studyMode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudySettingsScreen extends JFrame implements ActionListener {

    private final int flashcardSetId;

    private final StudySessionController controller;

    private String sortingOrder;

    private boolean termIsDefault=true;

    private boolean isReverse=false;


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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StudySettingsRequestModel request;

        if (sortingOrder.equals(StudySessionController.shuffleSort)) {
            request = new StudySettingsRequestModel(flashcardSetId, sortingOrder,
                    termIsDefault);
        }
        else {
            request = new StudySettingsRequestModel(flashcardSetId, sortingOrder,
                    termIsDefault, isReverse);
        }


        StudySettingsResponseModel response = this.controller.getSetToStudy(request);



        setVisible(false);
        dispose();
        if (response.hasFailed()){
            new StudySettingsFailureScreen();
        }
        else {
            new StudySessionScreen(this.controller, response);
        }
    }
}
