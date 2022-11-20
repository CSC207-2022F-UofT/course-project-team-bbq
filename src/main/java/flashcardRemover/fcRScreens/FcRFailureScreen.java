package flashcardRemover.fcRScreens;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FcRFailureScreen extends JFrame implements ActionListener {
    public FcRFailureScreen(String error){
        JPanel p = new JPanel();
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JLabel message = new JLabel(error);
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
