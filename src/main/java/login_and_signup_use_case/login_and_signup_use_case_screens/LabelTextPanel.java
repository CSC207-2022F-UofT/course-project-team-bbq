package login_and_signup_use_case.login_and_signup_use_case_screens;

import javax.swing.*;

/**
 * Creating the ability to have a text panel to take have that label and textfield
 *<p>
 * Frameworks & Drivers Layer
 * @author Aryan Chablani (with inspiration from Professor Paul Gries)
 */

public class LabelTextPanel extends JPanel {
    public LabelTextPanel(JLabel label, JTextField textField) {
        this.add(label);
        this.add(textField);
    }
}

