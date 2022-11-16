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
    private int w;
    private int h;

    private final boolean pack;

    public Screen(String title, boolean pack) {
        super(title);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        this.w = (int) (screenSize.width * (0.9));
        this.h = (int) (screenSize.height * (0.9));
        this.pack = pack;
        this.setSize(this.w, this.h);
    }

    public Screen(String title, int w, int h, boolean pack) {
        super(title);
        this.w = w;
        this.h = h;
        this.pack = pack;
        this.setSize(this.w, this.h);
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
        if (pack) {
            this.pack();
        }
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /** GETTERS AND SETTERS **/
    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
