package frameworks_and_drivers.screens;

import create_flashcard_set_use_case.*;
import data_access_use_case.*;
import entities.FlashcardSetFactory;
import frameworks_and_drivers.database.CommonUserDataAccess;
import frameworks_and_drivers.database.DBGateway;
import frameworks_and_drivers.database.FlashcardDataAccess;
import frameworks_and_drivers.database.FlashcardSetDataAccess;
import interface_adapters.controllers.CreateFlashcardSetController;
import interface_adapters.controllers.SearchController;
import interface_adapters.presenters.CreateFlashcardSetPresenter;
import interface_adapters.presenters.SearchPresenter;
import login_and_signup_use_case.UserLoginInputBoundary;
import login_and_signup_use_case.UserLoginInteractor;
import login_and_signup_use_case.UserLoginOutputBoundary;
import login_and_signup_use_case.UserLoginResponseModel;
import interface_adapters.controllers.UserLoginController;
import interface_adapters.presenters.UserLoginPresenter;
import frameworks_and_drivers.components.ListOfFlashcardSetsDataPanel;
import search_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Map;

/**
 * The home page of a user. Includes the list of all their flashcard sets panels including their buttons.
 * Also include a top bar with a search, add flashcard set, and log off button.
 * Frameworks & Drivers.
 * @author Justin Li
 */

public class HomeScreen extends Screen implements WindowListener {
    /**
     * The logged-in user
     */
    UserLoginResponseModel user;
    /**
     * The gateway object to access the entire database.
     */
    final DBGateway gateway;

    /**
     * Creates the home screen.
     * @param user the logged-in user.
     * @param gateway the gateway object to access the entire database.
     */
    public HomeScreen(UserLoginResponseModel user, DBGateway gateway) throws IOException {
        super(user.getSignedInUsername() + "'s home page");
        this.gateway = gateway;
        this.user = user;
        // home page layout
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        // top bar
        JPanel topBar = new JPanel();
        topBar.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // button creation
        JButton searchButton = new JButton("Search");
        JButton addFlashcardSetButton = new JButton("Add Flashcard Set");
        JButton logOff = new JButton("Log Off");

        // action listeners for search, add flashcard set, and log off buttons.
        searchButton.addActionListener(e -> {
            SearchOutputBoundary presenter = new SearchPresenter();
            SearchInteractor interactor = new SearchInteractor(presenter, gateway);
            SearchController controller = new SearchController(interactor);
            new SearchScreen(controller, gateway, user);
        });

        addFlashcardSetButton.addActionListener(e -> {
            CreateFlashcardSetOutputBoundary presenter = new CreateFlashcardSetPresenter();
            FlashcardSetFactory setFactory = new FlashcardSetFactory();
            CreateFlashcardSetInteractor interactor = new CreateFlashcardSetInteractor(gateway, presenter,
                    setFactory);
            CreateFlashcardSetController controller = new CreateFlashcardSetController(interactor);
            CreateFlashcardSetScreen creation = new CreateFlashcardSetScreen(controller, user);
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

        // adding list of flashcard set data panels to the home screen
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
            ListOfFlashcardSetsDataPanel listOfFCSet = new ListOfFlashcardSetsDataPanel(idsToFlashcardSetData, gateway, user, this);
            this.add(new JScrollPane(listOfFCSet));
        }
        this.setSize(1000, 800);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * A function used to refresh the home page by calling the user login again.
     * The current home page is dispose and a new home page is created.
     */
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
            new HomeScreen(user, gateway);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {}
    @Override
    public void windowClosed(WindowEvent e) {
        // When a window is closed the page will be refreshed.
        refresh();
    }
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}
