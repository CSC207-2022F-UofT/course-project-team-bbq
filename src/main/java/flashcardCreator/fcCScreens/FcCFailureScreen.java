package flashcardCreator.fcCScreens;

import flashcardCreator.FcCController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FcCFailureScreen extends JFrame implements ActionListener {
    FcCController controller;
    public FcCFailureScreen(FcCController controller, String error){
        this.controller =controller;
        JPanel p = new JPanel();
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JLabel error_label = new JLabel(error);
        JButton reedit = new JButton("re-edit");
        JButton returnToPage = new JButton("return to flashcard set");
        reedit.addActionListener(this);
        returnToPage.addActionListener(e -> {dispose();});

        p.add(error_label);
        buttonPanel.add(reedit);
        buttonPanel.add(returnToPage);
        this.add(p);
        this.add(buttonPanel, BorderLayout.SOUTH);
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
