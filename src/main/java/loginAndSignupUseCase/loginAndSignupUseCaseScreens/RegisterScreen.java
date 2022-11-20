package loginAndSignupUseCase.loginAndSignupUseCaseScreens;

import dataAccess.*;
import loginAndSignupUseCase.UserLoginInputBoundary;
import loginAndSignupUseCase.UserLoginInteractor;
import loginAndSignupUseCase.UserLoginOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Frameworks/Drivers layer

public class RegisterScreen extends JFrame implements ActionListener {

    /**
     * The username chosen by the user
     */
    JTextField username = new JTextField(15);
    /**
     * The password
     */
    JPasswordField password = new JPasswordField(15);
    /**
     * The second password to make sure the user understands
     */
    JPasswordField repeatPassword = new JPasswordField(15);

    /**
     * The Admin Key to ensure to make sure whether the user is an admin user or not
     */
    JPasswordField adminChecker = new JPasswordField(15);

    /**
     * The controller
     */
    UserRegisterController userRegisterController;

    /**
     * A window with a title and a JButton.
     */
    public RegisterScreen(UserRegisterController controller) {

        this.userRegisterController = controller;

        JLabel title = new JLabel("Register Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Choose Username"), username);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Choose Password"), password);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel("Enter Password Again"), repeatPassword);
        LabelTextPanel adminCheckerInfo = new LabelTextPanel(
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
            new WelcomeScreen();
        } else {
            try {
                userRegisterController.create(username.getText(),
                        String.valueOf(password.getPassword()),
                        String.valueOf(repeatPassword.getPassword()), String.valueOf(adminChecker.getPassword()));
                JOptionPane.showMessageDialog(this, String.format(username.getText()));
                IFlashcardSetDataAccess flashcardSetGateway;
                try {
                    flashcardSetGateway = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                IUserDataAccess userGateway;
                try {
                    userGateway = new CommonUserDataAccess(DBGateway.getUserPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                UserLoginOutputBoundary presenter = new UserLoginPresenter();
                UserLoginInputBoundary interactor = new UserLoginInteractor(
                        userGateway, flashcardSetGateway, presenter);
                UserLoginController userLoginController = new UserLoginController(interactor);
                setVisible(false);
                dispose();
                new LoginScreen(userLoginController).setVisible(true);
                new LoginScreen(userLoginController).setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
}
