package search_use_case;

import entityRequestModels.CommonUserDsRequestModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ResultsScreen extends JFrame implements ActionListener {
    private final SearchResponseModel responseModel;

    public ResultsScreen(SearchResponseModel responseModel){
        super("Search Results");

        this.responseModel = responseModel;
        int num_results = responseModel.getResult_set().size();

        for (int x=0; x<num_results; x++){
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 25));
            JLabel title = new JLabel(responseModel.getResult_set().get(x).getTitle());
            JLabel description = new JLabel(responseModel.getResult_set().get(x).getDescription());
            JLabel owner = new JLabel(responseModel.getResult_set().get(x).getOwnerUsername());
            JLabel id = new JLabel(String.valueOf(responseModel.getResult_set().get(x).getFlashcardSetId()));
            JButton study = new JButton("Study");
            study.addActionListener( e -> {
                // to be implemented
            });
            title.setBounds(0, 0, 200, 40);

            panel.add(title);
            panel.add(description);
            panel.add(owner);
            panel.add(id);
            panel.add(study);
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
