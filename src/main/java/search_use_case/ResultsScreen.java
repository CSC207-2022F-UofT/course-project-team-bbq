package search_use_case;

import dataAccess.DBGateway;
import quizUseCase.*;
import quizUseCase.screens.QuizSettingsScreen;
import studyMode.*;
import studyMode.screens.StudySettingsScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The results screen where the user can
 * see the results from their search and
 * select FlashcardSets to study or test from
 * <p>
 * Frameworks & Drivers
 * @author Winston Chieng
 */
public class ResultsScreen extends JFrame implements ActionListener {

    /**
     *
     * @param responseModel contains results from search
     * @param gateway to access information for study and quiz options
     */
    public ResultsScreen(SearchResponseModel responseModel, DBGateway gateway){
        super("Search Results");

        // store results in a Box layout
        JPanel result_panel = new JPanel();
        result_panel.setLayout(new BoxLayout(result_panel, BoxLayout.Y_AXIS));

        // scrollbar if results dont fit on screen
        JScrollPane scrPane = new JScrollPane(result_panel);
        scrPane.setPreferredSize(new Dimension(350, 500));
        scrPane.getVerticalScrollBar().setUnitIncrement(16);

        // Loop through every result in the result set
        for (int x=0; x<responseModel.getResult_set().size(); x++){

            // store all elements of this result in a GridLayout
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(6, 1, 20, 20));
            JLabel title = new JLabel("   " + responseModel.getResult_set().get(x).getTitle());
            JLabel description = new JLabel("   Description: " +
                    responseModel.getResult_set().get(x).getDescription());
            JLabel owner = new JLabel("   Creator: " + responseModel.getResult_set().get(x).getOwnerUsername());
            int tempX = x;

            // if user decides to study
            JButton study = new JButton("Study");
            study.addActionListener(e -> {
                try{
                    StudySessionOutputBoundary study_presenter = new StudySessionPresenter();
                    StudySessionInputBoundary study_interactor = new StudySessionInteractor(gateway, study_presenter);
                    StudySessionController study_controller = new StudySessionController(study_interactor);
                    new StudySettingsScreen(study_controller, responseModel.getResult_set().get(tempX).getFlashcardSetId());
                }
                catch (Exception s){
                    JOptionPane.showMessageDialog(this, s.getMessage());
                }
            });
            // if user decides to test
            JButton test = new JButton("Test");
            test.addActionListener(e -> {
                try{
                    QuizOutputBoundary quiz_presenter = new QuizPresenter();
                    QuizInputBoundary quiz_interactor = new QuizInteractor(gateway, quiz_presenter);
                    QuizController quiz_controller = new QuizController(quiz_interactor);
                    new QuizSettingsScreen(quiz_controller, responseModel.getResult_set().get(tempX).getFlashcardSetId());
                }
                catch (Exception s){
                    JOptionPane.showMessageDialog(this, s.getMessage());
                }
            });

            // add elements to the GridLayout
            panel.add(title);
            panel.add(description);
            panel.add(owner);
            panel.add(study);
            panel.add(test);
            result_panel.add(panel);
        }
        // add the panel containing all the results
        add(scrPane);
        setSize(400, 500);
        setLayout(new FlowLayout());
        setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}