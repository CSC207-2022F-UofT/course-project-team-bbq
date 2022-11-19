package search_use_case;

import quizUseCase.QuizController;
import quizUseCase.screens.QuizSettingsScreen;
import studyMode.StudySessionController;
import studyMode.screens.StudySettingsScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultsScreen extends JFrame implements ActionListener {

    public ResultsScreen(SearchResponseModel responseModel, StudySessionController study_controller,
                         QuizController quiz_controller){
        super("Search Results");
        int num_results = responseModel.getResult_set().size();

        JPanel result_panel = new JPanel();
        result_panel.setLayout(new BoxLayout(result_panel, BoxLayout.Y_AXIS));

        JScrollPane scrPane = new JScrollPane(result_panel);
        scrPane.setPreferredSize(new Dimension(350, 500));
        scrPane.getVerticalScrollBar().setUnitIncrement(16);

        for (int x=0; x<num_results; x++){
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(6, 1, 20, 20));
            JLabel title = new JLabel("   " + responseModel.getResult_set().get(x).getTitle());
            JLabel description = new JLabel("   Description: " +
                    responseModel.getResult_set().get(x).getDescription());
            JLabel owner = new JLabel("   Creator: " + responseModel.getResult_set().get(x).getOwnerUsername());
            JButton study = new JButton("Study");
            int tempX = x;
            study.addActionListener(e -> {
                try{
                    new StudySettingsScreen(study_controller, responseModel.getResult_set().get(tempX).getFlashcardSetId());
                }
                catch (Exception s){
                    JOptionPane.showMessageDialog(this, s.getMessage());
                }
            });
            JButton test = new JButton("Test");
            test.addActionListener(e -> {
                try{
                    new QuizSettingsScreen(quiz_controller, responseModel.getResult_set().get(tempX).getFlashcardSetId());
                }
                catch (Exception s){
                    JOptionPane.showMessageDialog(this, s.getMessage());
                }
            });

            panel.add(title);
            panel.add(description);
            panel.add(owner);
            panel.add(study);
            panel.add(test);
            result_panel.add(panel);
        }
        add(scrPane);


        setSize(400, 500);
        setLayout(new FlowLayout());
        setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
