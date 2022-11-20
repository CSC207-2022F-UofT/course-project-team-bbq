package loginAndSignupUseCase.loginAndSignupUseCaseScreens;

import dataAccess.*;
import entities.CommonUserFactory;
import entities.UserFactory;
import loginAndSignupUseCase.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

// Frameworks/Drivers layer

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

        } else if(evt.getActionCommand().equals("Sign up")){
            IUserDataAccess userGateway;
            try {
                userGateway = new CommonUserDataAccess(DBGateway.getUserPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            UserRegisterOutputBoundary presenter = new UserRegisterPresenter();
            UserFactory userFactory = new CommonUserFactory();
            UserRegisterInputBoundary interactor = new UserRegisterInteractor(
                    userGateway, presenter, userFactory);
            UserRegisterController userRegisterController = new UserRegisterController(interactor);
            setVisible(false);
            dispose();
            new RegisterScreen(userRegisterController).setVisible(true);
        }

    }

    public static void main(String[] args) throws IOException {
        new WelcomeScreen();
    }
}