package MainPage;

import create_flashcardset_use_case.*;
import dataAccess.*;
import entities.FlashcardSetFactory;
import loginAndSignupUseCase.UserLoginInputBoundary;
import loginAndSignupUseCase.UserLoginInteractor;
import loginAndSignupUseCase.UserLoginOutputBoundary;
import loginAndSignupUseCase.UserLoginResponseModel;
import loginAndSignupUseCase.loginAndSignupUseCaseScreens.UserLoginController;
import loginAndSignupUseCase.loginAndSignupUseCaseScreens.UserLoginPresenter;
import loginAndSignupUseCase.loginAndSignupUseCaseScreens.WelcomeScreen;
import search_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Map;

public class HomePage extends JFrame implements WindowListener {

    UserLoginResponseModel user;
    DBGateway gateway;

    public HomePage(UserLoginResponseModel user) throws IOException {
        super(user.getSignedInUsername() + "'s home page");

        this.user = user;
        // home page layout
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        // initialize DBGateway
        IFlashcardSetDataAccess flashcardSetDataAccess = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
        IFlashcardDataAccess flashcardDataAccess = new FlashcardDataAccess(DBGateway.getFlashcardPath());
        IUserDataAccess userDataAccess = new CommonUserDataAccess(DBGateway.getUserPath());
        DBGateway gateway = new DBGateway(flashcardDataAccess,
                flashcardSetDataAccess, userDataAccess);
        this.gateway = gateway;
        // top bar
        JPanel topBar = new JPanel();
        topBar.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton searchButton = new JButton("Search");
        JButton addFlashcardSetButton = new JButton("Add Flashcard Set");
        JButton logOff = new JButton("Log Off");

        searchButton.addActionListener(e -> {
            SearchOutputBoundary presenter = new SearchPresenter();
            SearchInteractor interactor = new SearchInteractor(presenter, gateway);
            SearchController controller = new SearchController(interactor);
            new SearchScreen(controller, gateway, user);
        });

        addFlashcardSetButton.addActionListener(e -> {
            FlashcardSetOutputBoundary presenter = new FlashcardSetPresenter();
            FlashcardSetFactory setFactory = new FlashcardSetFactory();
            FlashcardSetInteractor interactor = new FlashcardSetInteractor(gateway, presenter,
                    setFactory);
            FlashcardSetController controller = new FlashcardSetController(interactor);
            JFrame creationScreen = new CreationScreen(controller, user);
            creationScreen.addWindowListener(this);
        });


        logOff.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
            try {
                new WelcomeScreen();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        topBar.add(searchButton);
        topBar.add(addFlashcardSetButton);
        topBar.add(logOff);
        topBar.setSize(1000,20);
        this.add(topBar);

        Map<Integer, String[]> idsToFlashcardSetData = user.getFlashcardSets();

        int numSets = idsToFlashcardSetData.size();
        System.out.println(numSets);
        if (numSets==0){
            JLabel label = new JLabel("You have no Flashcard Sets!");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.TOP);
            JPanel labelPanel = new JPanel();
            labelPanel.add(label);
            this.add(labelPanel);
        }
        else {
            this.add(new ListOfFlashcardSetsDataPanel(idsToFlashcardSetData, gateway, user));
        }
        this.setSize(1000, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void refresh() {
        try {
            UserLoginOutputBoundary presenter = new UserLoginPresenter();
            UserLoginInputBoundary interactor = new UserLoginInteractor(
                    gateway, presenter);
            UserLoginController userLoginController = new UserLoginController(interactor);
            setVisible(false);
            dispose();

            user = userLoginController.create(user.getSignedInUsername(),
                    user.getPassword());
            this.dispose();
            new HomePage(user);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        refresh();
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
