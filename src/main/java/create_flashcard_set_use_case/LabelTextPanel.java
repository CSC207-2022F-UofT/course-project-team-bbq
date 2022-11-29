package create_flashcard_set_use_case;

import javax.swing.*;

// Frameworks/Drivers (Blue) layer

/**
 * A panel with a label and text field.
 *
 * @author Edward Ishii
 */
public class LabelTextPanel extends JPanel {

    /**
     * Constructs a new LabelTextPanel.
     *
     * @param label     the label of the panel.
     * @param textField the text of the panel.
     */
    public LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }
}
