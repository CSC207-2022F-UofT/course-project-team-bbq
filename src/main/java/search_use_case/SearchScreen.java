package search_use_case;

import entityRequestModels.CommonUserDsRequestModel;
import quizUseCase.QuizController;
import studyMode.StudySessionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class SearchScreen extends JFrame implements ActionListener{

    public SearchScreen(SearchController search_controller, StudySessionController study_controller,
                        QuizController quiz_controller, CommonUserDsRequestModel curr_user){
        super("Search Flashcards");

        ArrayList<String> selected_tags = new ArrayList<>();

        // search input text field
        JTextField search_input = new JTextField("Search Input Here");
        search_input.setBounds(80, 100, 200, 40);

        JCheckBox title_box = new JCheckBox("Title");
        title_box.setBounds(100,200, 50,50);

        JCheckBox description_box = new JCheckBox("Description");
        description_box.setBounds(100,250, 100,50);

        JCheckBox owner_box = new JCheckBox("Owner");
        owner_box.setBounds(100,300, 100,50);

        // search button
        JButton search = new JButton("Search");
        search.setBounds(130, 350, 100, 40);
        search.addActionListener( e -> {
            // nav to next screen
            String s1 = search_input.getText();

            // define the tags selected
            if (title_box.isSelected()){
                selected_tags.add("Title");
            }
            if (description_box.isSelected()){
                selected_tags.add("Description");
            }
            if (owner_box.isSelected()){
                selected_tags.add("Owner");
            }
            try {
                new ResultsScreen(search_controller.create(s1, selected_tags, curr_user),
                        study_controller, quiz_controller);
            }
            catch (Exception x){
                JOptionPane.showMessageDialog(this, x.getMessage());
            }

        });

        JButton search_all = new JButton("Search All");
        search_all.setBounds(130, 400, 100, 40);
        search_all.addActionListener( e -> {
            try {
                new ResultsScreen(search_controller.create("GET_ALL", selected_tags, curr_user),
                        study_controller, quiz_controller);
            }
            catch (Exception x){
                JOptionPane.showMessageDialog(this, x.getMessage());
            }
        });

        // add buttons
        add(search_input);
        add(title_box);
        add(description_box);
        add(owner_box);
        add(search);
        add(search_all);

        setSize(400, 500);
        setLayout(null);
        setVisible(true);

    }

        public void actionPerformed(ActionEvent e){
        }
}
