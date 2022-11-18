package FlashcardCreator.FcCScreens;

import FlashcardCreator.FcCController;
import FlashcardCreator.FcCFailure;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FcCFailureScreen extends JFrame implements ActionListener {
    FcCController controller;
    public FcCFailureScreen(FcCController controller, String error){
        this.controller =controller;
        JPanel p = new JPanel();
        JLabel error_label = new JLabel(error);
        JButton reedit = new JButton("re-edit");
        JButton returnToPage = new JButton("Return to Flashcard set.");
        reedit.addActionListener(this);
        returnToPage.addActionListener(e -> {dispose();});
        p.add(error_label);
        p.add(reedit);
        p.add(returnToPage);
        this.add(p);
        this.setSize(1000,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new FcCScreen(controller);
        dispose();
    }
}
