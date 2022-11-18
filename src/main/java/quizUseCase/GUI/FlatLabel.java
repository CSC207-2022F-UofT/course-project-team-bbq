package quizUseCase.GUI;

import javax.swing.*;

/**
 * JLabel that can be further customized using FlatLaf.
 * Frameworks & Drivers
 * @author Anthony
 */
public class FlatLabel extends JLabel {
    public FlatLabel(String text, String type) {
        super(text);
        this.putClientProperty("FlatLaf.styleClass", type);
    }
}
