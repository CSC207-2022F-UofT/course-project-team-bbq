package flashcardCreator.fcCScreens;

import flashcardCreator.FcCController;
import flashcardCreator.FcCResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FcCSuccessScreen extends JFrame implements ActionListener {
    FcCController controller;
    public FcCSuccessScreen(FcCController controller, FcCResponseModel responseModel){
        this.controller =controller;
        JPanel p = new JPanel();
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JLabel message_label = new JLabel("Card created at("+responseModel.getCreationDate() +"):");
        JLabel card_text = new JLabel("<html>" + responseModel.getTerm() + "<br/>" +
                responseModel.getDefinition() + "<html>");
        JButton newCard = new JButton("new card");
        JButton returnToPage = new JButton("return to Flashcard set");
        newCard.addActionListener(this);
        returnToPage.addActionListener(e -> {
            dispose();
        });
        p.add(message_label);
        p.add(card_text);
        buttonPanel.add(newCard);
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
