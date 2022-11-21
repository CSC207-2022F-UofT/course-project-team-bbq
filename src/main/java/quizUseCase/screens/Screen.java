package quizUseCase.screens;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Abstract class representing a GUI screen.
 * Frameworks & Drivers
 * @author Anthony
 */
public abstract class Screen extends JFrame implements ActionListener {
    private final boolean pack;

    /**
     * Constructs a large screen.
     * @param title the screen title
     * @param pack true if I want to pack it
     */
    public Screen(String title, boolean pack) {
        super(title);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int w = (int) (screenSize.width * (0.9));
        int h = (int) (screenSize.height * (0.9));
        this.pack = pack;
        this.setSize(w, h);
    }

    /**
     * Sets up the screen at the end. Also adds an icon.
     */
    public void setupScreen() {
        // ICON
        File file = new File("src/images/spungebub.png");
        try {
            this.setIconImage(ImageIO.read(file));
        } catch (Exception e) {
            System.out.println(e);
        }

        // SCREEN SETUP
        if (pack) {
            this.pack();
        }
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
