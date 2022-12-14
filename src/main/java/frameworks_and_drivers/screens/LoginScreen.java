package frameworks_and_drivers.screens;

import frameworks_and_drivers.components.LabelTextPanel;
import frameworks_and_drivers.database.CommonUserDataAccess;
import frameworks_and_drivers.database.DBGateway;
import frameworks_and_drivers.database.FlashcardDataAccess;
import frameworks_and_drivers.database.FlashcardSetDataAccess;
import interface_adapters.controllers.UserLoginController;
import login_and_signup_use_case.UserLoginResponseModel;
import data_access_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Create a swing user interface for the Login Screen
 *<p>
 * Frameworks and Drivers
 * @author Aryan Chablani (with inspiration from Professor Paul Gries)
 */

public class LoginScreen extends Screen implements ActionListener {
    /**
     * The username belonging to the user
     */
    final JTextField username = new JTextField(15);
    /**
     * The password
     */
    final JPasswordField password = new JPasswordField(15);

    /**
     * The controller
     */
    final UserLoginController userLoginController;

    /**
     * A window with a title and a JButton.
     */
    public LoginScreen(UserLoginController controller) {

        this.userLoginController = controller;

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        frameworks_and_drivers.components.LabelTextPanel usernameInfo = new frameworks_and_drivers.components.LabelTextPanel(
                new JLabel("Username"), username);
        frameworks_and_drivers.components.LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), password);

        JButton logIn = new JButton("Log in");
        JButton cancel = new JButton("Return");

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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * React to a button click that results in evt, usually login.
     */
    public void actionPerformed(ActionEvent evt) {

        System.out.println("Click " + evt.getActionCommand());
        if (evt.getActionCommand().equals("Return")) {
            this.dispose();
            try {
                new WelcomeScreen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                UserLoginResponseModel user = userLoginController.create(username.getText(),
                        String.valueOf(password.getPassword()));
                this.dispose();
                IFlashcardSetDataAccess flashcardSetDataAccess = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
                IFlashcardDataAccess flashcardDataAccess = new FlashcardDataAccess(DBGateway.getFlashcardPath());
                IUserDataAccess userDataAccess = new CommonUserDataAccess(DBGateway.getUserPath());
                DBGateway gateway = new DBGateway(flashcardDataAccess,
                        flashcardSetDataAccess, userDataAccess);
                new HomeScreen(user, gateway);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
}
