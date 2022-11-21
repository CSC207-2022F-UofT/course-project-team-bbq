package loginAndSignupUseCase.loginAndSignupUseCaseScreens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Create a swing user interface for the Login Screen
 *<p>
 * Frameworks and Drivers
 * @author Aryan Chablani (with inspiration from Professor Paul Gries)
 */

public class LoginScreen extends JFrame implements ActionListener {
    /**
     * The username belonging to the user
     */
    JTextField username = new JTextField(15);
    /**
     * The password
     */
    JPasswordField password = new JPasswordField(15);

    /**
     * The controller
     */
    UserLoginController userLoginController;

    /**
     * A window with a title and a JButton.
     */
    public LoginScreen(UserLoginController controller) {

        this.userLoginController = controller;

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), username);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), password);

        JButton logIn = new JButton("Log in");
        JButton cancel = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(logIn);
        buttons.add(cancel);

        logIn.addActionListener(this);
        cancel.addActionListener(this);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(title);
        main.add(usernameInfo);
        main.add(passwordInfo);
        main.add(buttons);
        this.setContentPane(main);

        this.pack();
    }

    /**
     * React to a button click that results in evt, usually login.
     */
    public void actionPerformed(ActionEvent evt) {

        System.out.println("Click " + evt.getActionCommand());

        try {
            userLoginController.create(username.getText(),
                    String.valueOf(password.getPassword()));
            JOptionPane.showMessageDialog(this, "%s Logged In.".format(username.getText()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
