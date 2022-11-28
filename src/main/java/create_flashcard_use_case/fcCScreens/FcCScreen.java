package create_flashcard_use_case.fcCScreens;

import create_flashcard_use_case.FcCResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel for flashcard creator.
 * @author Junyu Chen
 */
public class FcCScreen extends JPanel implements ActionListener {
    FcCController controller;
    JTextArea term_text;
    JTextArea definition_text;
    JFrame fcCMain;
    public FcCScreen(FcCController controller, JFrame fcCMain){
        //Initiating all the sub panels.
        this.fcCMain = fcCMain;
        this.controller = controller;
        this.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel labelPanel = new JPanel(new GridLayout(1,2));
        JPanel textPanel = new JPanel(new GridLayout(1,2));

        //Creating the button panel.
        JButton confirm = new JButton("confirm");
        JButton cancel = new JButton("cancel");
        confirm.addActionListener(this);
        cancel.addActionListener(e -> this.fcCMain.dispose());
        buttonPanel.add(confirm);
        buttonPanel.add(cancel);

        JLabel term_label = new JLabel("term");
        JLabel definition_label = new JLabel("definition");
        labelPanel.add(term_label);
        labelPanel.add(definition_label);

        //Creating the text panel.
        term_text = new JTextArea();
        definition_text = new JTextArea();
        term_text.setLineWrap(true);
        definition_text.setLineWrap(true);
        textPanel.add(term_text);
        textPanel.add(definition_text);

        //Creating the label panel.
        this.add(labelPanel, BorderLayout.NORTH);
        this.add(textPanel);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(1000,500);
        this.setVisible(true);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            //Success view.
            FcCResponseModel responseModel = controller.create(term_text.getText(), definition_text.getText());
            int action = JOptionPane.showConfirmDialog(this,
                    "Card created:\n"+responseModel.getTerm()+ "\n" + responseModel.getDefinition()
                            +"\ncreate another card?");
            if(action == JOptionPane.YES_OPTION){
                term_text.setText("");
                definition_text.setText("");
            }else {
                fcCMain.dispose();
            }
        }catch (RuntimeException error){
            //Failure view.
            int action = JOptionPane.showConfirmDialog(this,
                    error + "\nRecreate?");
            if(action == JOptionPane.YES_OPTION){
                term_text.setText("");
            }else {
                fcCMain.dispose();
            }
        }
    }
}
