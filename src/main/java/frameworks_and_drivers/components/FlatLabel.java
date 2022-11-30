package frameworks_and_drivers.components;

import javax.swing.*;

/**
 * JLabel that can be further customized using FlatLaf.
 * Frameworks & Drivers
 * @author Anthony
 */
public class FlatLabel extends JLabel {
    /**
     * Creates a FlatLaf styled label.
     * @param text the display text
     * @param type the style type. Refer to FlatLaf documentation for a list of style types.
     */
    public FlatLabel(String text, String type) {
        super(text);
        this.putClientProperty("FlatLaf.styleClass", type);
    }
}
