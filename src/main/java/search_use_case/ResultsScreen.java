package search_use_case;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultsScreen extends JFrame implements ActionListener {
    private final SearchResponseModel responseModel;

    public ResultsScreen(SearchResponseModel responseModel){
        super("Search Results");

        this.responseModel = responseModel;
        int num_results = responseModel.getResult_set().size();

//        JTextField result1 = new JTextField(responseModel.getResult_set().get(0).getTitle());
//        result1.setBounds(80, 600, 200, 40);


        for (int x=0; x<num_results; x++){
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 25));
            JLabel title = new JLabel(responseModel.getResult_set().get(x).getTitle());
            JLabel description = new JLabel(responseModel.getResult_set().get(x).getDescription());
            JLabel owner = new JLabel(responseModel.getResult_set().get(x).getOwnerUsername());
            JLabel id = new JLabel(String.valueOf(responseModel.getResult_set().get(x).getFlashcardSetId()));
            title.setBounds(0, 0, 200, 40);
            panel.add(title);
            panel.add(description);
            panel.add(owner);
            panel.add(id);
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
