package login_and_signup_use_case.login_and_signup_use_case_screens;

import dataAccess.*;
import entities.CommonUserFactory;
import entities.UserFactory;
import login_and_signup_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * Create a Swing user interface for the Welcom Screen
 *<p>
 * Frameworks and Drivers
 * @author Aryan Chablani (with inspiration from Professor Paul Gries)
 */

public class WelcomeScreen extends JFrame implements ActionListener {

    /**
     * A window with a title and a JButton.
     */

    DBGateway gateway;
    public WelcomeScreen() throws IOException {
        IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess(DBGateway.getFlashcardPath());
        IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
        IUserDataAccess userGateway = new CommonUserDataAccess(DBGateway.getUserPath());

        gateway = new DBGateway(flashcardGateway, flashcardSetGateway, userGateway);


        JLabel title = new JLabel("Welcome Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton logIn = new JButton("Log in");
        JButton signUp = new JButton("Sign up");

        JPanel buttons = new JPanel();
        buttons.add(logIn);
        buttons.add(signUp);

        logIn.addActionListener(this);
        signUp.addActionListener(this);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(title);
        main.add(buttons);
        this.setContentPane(main);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {

        System.out.println("Click " + evt.getActionCommand());

        if(evt.getActionCommand().equals("Log in")){

            UserLoginOutputBoundary presenter = new UserLoginPresenter();
            UserLoginInputBoundary interactor = new UserLoginInteractor(
                    gateway, presenter);
            UserLoginController userLoginController = new UserLoginController(interactor);
            setVisible(false);
            dispose();
            new LoginScreen(userLoginController).setVisible(true);

        } else if(evt.getActionCommand().equals("Sign up")){
           // create register controller
            UserRegisterOutputBoundary presenter = new UserRegisterPresenter();
            UserFactory userFactory = new CommonUserFactory();
            UserRegisterInputBoundary interactor = new UserRegisterInteractor(
                    gateway, presenter, userFactory);
            UserRegisterController userRegisterController = new UserRegisterController(interactor);

            // create login controller
            UserLoginOutputBoundary loginPresenter = new UserLoginPresenter();
            UserLoginInputBoundary loginInteractor = new UserLoginInteractor(
                    gateway, loginPresenter);
            UserLoginController userLoginController = new UserLoginController(loginInteractor);
            setVisible(false);
            dispose();
            new RegisterScreen(userRegisterController, userLoginController).setVisible(true);
        }

    }

    public static void main(String[] args) throws IOException {
        new WelcomeScreen();
    }
}