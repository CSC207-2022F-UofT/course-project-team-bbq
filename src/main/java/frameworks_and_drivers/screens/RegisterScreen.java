package frameworks_and_drivers.screens;

import frameworks_and_drivers.components.LabelTextPanel;
import frameworks_and_drivers.database.CommonUserDataAccess;
import frameworks_and_drivers.database.DBGateway;
import frameworks_and_drivers.database.FlashcardDataAccess;
import frameworks_and_drivers.database.FlashcardSetDataAccess;
import interface_adapters.controllers.UserLoginController;
import interface_adapters.controllers.UserRegisterController;
import interface_adapters.presenters.exceptions.UserRegistrationFailed;
import login_and_signup_use_case.*;
import data_access_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Create a swing user interface for the Sign-Up Screen
 *<p>
 * Frameworks and Drivers
 * @author Aryan Chablani (with inspiration from Professor Paul Gries)
 */

public class RegisterScreen extends JFrame implements ActionListener {

    private final UserLoginController userLoginController;
    /**
     * The username chosen by the user
     */
    final JTextField username = new JTextField(15);
    /**
     * The password
     */
    final JPasswordField password = new JPasswordField(15);
    /**
     * The second password to make sure the user understands
     */
    final JPasswordField repeatPassword = new JPasswordField(15);

    /**
     * The Admin Key to ensure to make sure whether the user is an admin user or not
     */
    final JPasswordField adminChecker = new JPasswordField(15);

    /**
     * The controller
     */
    final UserRegisterController userRegisterController;

    /**
     * A window with a title and a JButton.
     */
    public RegisterScreen(UserRegisterController registerController, UserLoginController loginController) {

        this.userRegisterController = registerController;
        this.userLoginController = loginController;

        JLabel title = new JLabel("Register Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        frameworks_and_drivers.components.LabelTextPanel usernameInfo = new frameworks_and_drivers.components.LabelTextPanel(
                new JLabel("Choose Username"), username);
        frameworks_and_drivers.components.LabelTextPanel passwordInfo = new frameworks_and_drivers.components.LabelTextPanel(
                new JLabel("Choose Password"), password);
        frameworks_and_drivers.components.LabelTextPanel repeatPasswordInfo = new frameworks_and_drivers.components.LabelTextPanel(
                new JLabel("Enter Password Again"), repeatPassword);
        frameworks_and_drivers.components.LabelTextPanel adminCheckerInfo = new LabelTextPanel(
                new JLabel("Enter Admin Key (Optional)"), adminChecker);

        JButton signUp = new JButton("Sign up");
        JButton cancel = new JButton("Return");

        JPanel buttons = new JPanel();
        buttons.add(signUp);
        buttons.add(cancel);

        signUp.addActionListener(this);
        cancel.addActionListener(this);
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(title);
        main.add(usernameInfo);
        main.add(passwordInfo);
        main.add(repeatPasswordInfo);
        main.add(adminCheckerInfo);
        main.add(buttons);

        this.setContentPane(main);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        if (evt.getActionCommand().equals("Return")) {
            this.dispose();
            try {
                new WelcomeScreen();
            }
            catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        else {

            try {
                UserRegisterResponseModel newUser = userRegisterController.create(username.getText(),
                        String.valueOf(password.getPassword()),
                        String.valueOf(repeatPassword.getPassword()), String.valueOf(adminChecker.getPassword()));


                UserLoginResponseModel user = userLoginController.create(newUser.getSignedUpUsername(),
                        newUser.getSignedUpPassword());
                try {
                    IFlashcardSetDataAccess flashcardSetDataAccess = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
                    IFlashcardDataAccess flashcardDataAccess = new FlashcardDataAccess(DBGateway.getFlashcardPath());
                    IUserDataAccess userDataAccess = new CommonUserDataAccess(DBGateway.getUserPath());
                    DBGateway gateway = new DBGateway(flashcardDataAccess,
                            flashcardSetDataAccess, userDataAccess);
                    new HomeScreen(user, gateway);
                    dispose();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            catch (UserRegistrationFailed e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }

        }
    }
}
