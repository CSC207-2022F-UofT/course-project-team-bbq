package flashcardRemover.fcRScreens;
import flashcardRemover.FcRResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FcRSuccessScreen extends JFrame implements ActionListener{
    public FcRSuccessScreen(FcRResponseModel responseModel){
        JPanel p = new JPanel();
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JLabel message = new JLabel(responseModel.getTerm() + " deleted from " + responseModel.getCardSetName() +
                " at " + responseModel.getDeleteDate());
        JButton return_to_previous = new JButton("OK");
        return_to_previous.addActionListener(this);

        p.add(message);
        buttonPanel.add(return_to_previous);
        this.add(p);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setSize(1000,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }
}
