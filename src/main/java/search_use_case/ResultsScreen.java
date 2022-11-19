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
    private final SearchResponseModel responseModel;
    private final StudySessionController study_controller;
    private final QuizController quiz_controller;

    public ResultsScreen(SearchResponseModel responseModel, StudySessionController study_controller,
                         QuizController quiz_controller){
        super("Search Results");
        this.study_controller = study_controller;
        this.quiz_controller = quiz_controller;
        this.responseModel = responseModel;
        int num_results = responseModel.getResult_set().size();

        for (int x=0; x<num_results; x++){
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 25));
            JLabel title = new JLabel(responseModel.getResult_set().get(x).getTitle());
//            JLabel description = new JLabel(responseModel.getResult_set().get(x).getDescription());
            JLabel owner = new JLabel(responseModel.getResult_set().get(x).getOwnerUsername());
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

            title.setBounds(0, 0, 200, 40);

            panel.add(title);
//            panel.add(description);
            panel.add(owner);
            panel.add(study);
            panel.add(test);
            add(panel);
        }


    setSize(400, 500);
    setLayout(new FlowLayout());
    setVisible(true);


    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
