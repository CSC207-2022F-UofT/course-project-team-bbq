package search_use_case;

import dataAccess.DBGateway;
import loginAndSignupUseCase.UserLoginResponseModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The search screen where the user can select
 * tags and type in search input to find relevant
 * FlashcardSets to study and test from
 * <p>
 * Frameworks & Drivers
 * @author Winston Chieng
 */
public class SearchScreen extends JFrame implements ActionListener{

    /**
     * Creates a SearchScreen
     * @param search_controller the controller to handle search operations
     * @param gateway to handle study and quiz controllers in the results
     * @param curr_user the current user
     */
    public SearchScreen(SearchController search_controller, DBGateway gateway, UserLoginResponseModel curr_user){
        super("Search Flashcards");

        ArrayList<String> selected_tags = new ArrayList<>();

        // search input text field
        JTextField search_input = new JTextField("Search Input Here");
        search_input.setBounds(80, 100, 200, 40);

        // search tag options
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
            // navigate to results screen if results are found
            try {
                SearchRequestModel requestModel = new SearchRequestModel(s1, selected_tags, curr_user);
                new ResultsScreen(search_controller.create(requestModel),
                        gateway, curr_user);
            }
            catch (Exception x){
                JOptionPane.showMessageDialog(this, x.getMessage());
            }

        });

        // search all flashcards option
        JButton search_all = new JButton("Search All");
        search_all.setBounds(130, 400, 100, 40);
        search_all.addActionListener( e -> {
            try {
                SearchRequestModel requestModel = new SearchRequestModel("GET_ALL",
                        selected_tags, curr_user);
                new ResultsScreen(search_controller.create(requestModel),
                        gateway, curr_user);
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
    // action methods defined within body
        public void actionPerformed(ActionEvent e){
        }
}
