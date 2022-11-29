package main_page;

import create_flashcard_set_use_case.*;
import data_access.*;
import entities.FlashcardSetFactory;
import login_and_signup_use_case.UserLoginInputBoundary;
import login_and_signup_use_case.UserLoginInteractor;
import login_and_signup_use_case.UserLoginOutputBoundary;
import login_and_signup_use_case.UserLoginResponseModel;
import login_and_signup_use_case.login_and_signup_use_case_screens.UserLoginController;
import login_and_signup_use_case.login_and_signup_use_case_screens.UserLoginPresenter;
import login_and_signup_use_case.login_and_signup_use_case_screens.WelcomeScreen;
import search_use_case.*;
import view.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Map;

public class HomePage extends Screen implements WindowListener {

    UserLoginResponseModel user;
    DBGateway gateway;

    public HomePage(UserLoginResponseModel user, DBGateway gateway) throws IOException {
        super(user.getSignedInUsername() + "'s home page");
        this.gateway = gateway;
        this.user = user;
        // home page layout
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

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
            CreationScreen creation = new CreationScreen(controller, user);
            creation.addWindowListener(this);
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
            this.add(new ListOfFlashcardSetsDataPanel(idsToFlashcardSetData, gateway, user, this));
        }
        this.setSize(1000, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void refresh() {
        try {
            IFlashcardSetDataAccess flashcardSetDataAccess = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
            IFlashcardDataAccess flashcardDataAccess = new FlashcardDataAccess(DBGateway.getFlashcardPath());
            IUserDataAccess userDataAccess = new CommonUserDataAccess(DBGateway.getUserPath());
            DBGateway gateway = new DBGateway(flashcardDataAccess,
                    flashcardSetDataAccess, userDataAccess);
            UserLoginOutputBoundary presenter = new UserLoginPresenter();
            UserLoginInputBoundary interactor = new UserLoginInteractor(
                    gateway, presenter);
            UserLoginController userLoginController = new UserLoginController(interactor);
            setVisible(false);

            user = userLoginController.create(user.getSignedInUsername(),
                    user.getPassword());
            this.dispose();
            new HomePage(user, gateway);
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
