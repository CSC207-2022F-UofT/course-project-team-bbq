package quizUseCase.screens;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Frameworks & Drivers
 */
public abstract class Screen extends JFrame implements ActionListener {
    public Screen(String title) {
        super(title);
    }

    public void setupScreen() {
        // ICON
        File file = new File("src/images/spungebub.png");
        try {
            this.setIconImage(ImageIO.read(file));
        } catch (Exception e) {
            System.out.println(e);
        }

        // SCREEN SETUP
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.setSize(screenSize.width, screenSize.height); // max size
//        this.pack(); // pack it into a compact form
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
