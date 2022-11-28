package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;

public abstract class Screen extends JFrame {
    public Screen() {
        loadIcon();
    }

    public Screen(String title) {
        super(title);
        loadIcon();
    }

    public void loadIcon() {
        File file = new File("src/images/bbq.png");
        try {
            this.setIconImage(ImageIO.read(file));
        } catch (Exception e) {
            System.out.println("Unable to load file.");
        }
    }
}
