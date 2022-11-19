package flashcardCreator.fcCScreens;

import flashcardCreator.FcCController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FcCScreen extends JFrame implements ActionListener {
    FcCController controller;
    JTextArea term_text;
    JTextArea definition_text;
    public FcCScreen(FcCController controller){
        this.controller = controller;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
        JPanel textPanel = new JPanel(new FlowLayout());

        JButton confirm = new JButton("confirm");
        JButton cancel = new JButton("cancel");
        confirm.addActionListener(this);
        cancel.addActionListener(e -> {dispose();});
        buttonPanel.add(confirm);
        buttonPanel.add(cancel);

        JLabel term_label = new JLabel("term");
        JLabel definition_label = new JLabel("definition");
        labelPanel.add(term_label);
        labelPanel.add(definition_label);

        term_text = new JTextArea();
        definition_text = new JTextArea();
        term_text.setLineWrap(true);
        definition_text.setLineWrap(true);
        textPanel.add(term_text);
        textPanel.add(definition_text);

        this.add(labelPanel, BorderLayout.NORTH);
        this.add(textPanel);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(1000,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            controller.create(term_text.getText(), definition_text.getText());
            new FcCSuccessScreen(controller, term_text.getText(), definition_text.getText());
            dispose();
        }catch (RuntimeException error){
            new FcCFailureScreen(controller,error.toString());
            dispose();
        }

    }
}
