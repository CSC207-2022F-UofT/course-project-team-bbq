package FlashcardCreator.FcCScreens;

import FlashcardCreator.FcCController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FcCSuccessScreen extends JFrame implements ActionListener {
    FcCController controller;
    public FcCSuccessScreen(FcCController controller, String term, String definition){
        this.controller =controller;
        JPanel p = new JPanel();
        JLabel message_label = new JLabel("Card created:");
        JLabel card_text = new JLabel("<html>" + term + "<br/>" + definition + "<html>");
        JButton newCard = new JButton("new card");
        JButton returnToPage = new JButton("return to Flashcard set.");
        newCard.addActionListener(this);
        returnToPage.addActionListener(e -> {
            dispose();
        });
        p.add(message_label);
        p.add(card_text);
        p.add(newCard);
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
