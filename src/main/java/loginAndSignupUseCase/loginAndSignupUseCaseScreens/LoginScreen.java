package loginAndSignupUseCase.loginAndSignupUseCaseScreens;

import MainPage.HomePage;
import loginAndSignupUseCase.UserLoginResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

// Frameworks/Drivers layer

public class LoginScreen extends JFrame implements ActionListener {
    /**
     * The username chosen by the user
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
     * React to a button click that results in evt.
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
//                JOptionPane.showMessageDialog(this, String.format(username.getText()));
                new HomePage(user);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
}
