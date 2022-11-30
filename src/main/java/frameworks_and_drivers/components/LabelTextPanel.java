package frameworks_and_drivers.components;

import javax.swing.*;

/**
 * Creating the ability to have a text panel to take have that label and text-field
 * Frameworks & Drivers Layer
 * @author Edward Ishii
 * @author Aryan Chablani (with inspiration from Professor Paul Gries)
 *  * Frameworks & Drivers Layer
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

