package studyMode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudySessionScreen extends JFrame implements ActionListener {
    private StudySessionController controller;
    private JButton flip;

    private int numFlashcards;

    private JLabel cardLabel;

    public StudySessionScreen(StudySessionController controller,
                              StudySettingsResponseModel response) {
        super(response.getTitle());
        this.numFlashcards = response.getNumFlashcards();
        this.controller = controller;

        cardLabel = new JLabel("1 / " + this.numFlashcards);
        this.flip = new JButton(response.getOutputText());//creating instance of JButton
        JButton next = new JButton("Next");
        JButton prev = new JButton("Previous");
        JButton quit = new JButton("Quit");

        // set action commands so action listener knows what to do
        flip.setActionCommand("flip");
        next.setActionCommand("next");
        prev.setActionCommand("prev");
        quit.setActionCommand("quit");
        flip.addActionListener(this);
        next.addActionListener(this);
        prev.addActionListener(this);
        quit.addActionListener(this);


        int size = 700;
        int cardWidth = 250;
        int cardHeight = 200;
        int x = (size - cardWidth) / 2;
        int y = (size - cardWidth) / 2;
        int bWidth = 100;
        int bHeight = 50;
        // set the button bounds on the screen
        flip.setBounds(x,y,cardWidth, cardHeight);
        next.setBounds(x+cardWidth+(x-bWidth)/2, (size - bHeight)/2, bWidth, bHeight);
        prev.setBounds((x-bWidth)/2, (size -bHeight)/2, bWidth, bHeight);
        quit.setBounds((size-bWidth)/2, y + cardHeight + (y-bHeight)/2, bWidth, bHeight);
        cardLabel.setBounds((size-bWidth)/2, (y-bHeight)/2, bWidth, bHeight);
        this.add(flip);//adding button in JFrame
        this.add(next);
        this.add(prev);
        this.add(quit);
        this.add(cardLabel);


        this.setSize(size,size);
        this.setLayout(null);//using no layout managers
        this.setVisible(true);//making the frame visible

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StudySessionRequestModel request = new StudySessionRequestModel();
        String command = e.getActionCommand();
        if (command.equals("quit")){
            setVisible(false);
            dispose();
        }
        else{
            request.setCommand(command);
            StudySessionResponseModel response = this.controller.study(request);
            this.flip.setText(response.getOutputText());
            this.cardLabel.setText(response.getCardNumber() + " / "  + this.numFlashcards);
        }
    }
}
