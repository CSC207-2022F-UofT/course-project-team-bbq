package MainPage;

import create_flashcardset_use_case.*;
import dataAccess.*;
import entities.FlashcardSetFactory;
import loginAndSignupUseCase.UserLoginResponseModel;
import loginAndSignupUseCase.loginAndSignupUseCaseScreens.WelcomeScreen;
import search_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HomePage extends JFrame {

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
            new CreationScreen(controller, user);

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
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void refresh() {
        try {
//            UserLoginOutputBoundary presenter = new UserLoginPresenter();
//            UserLoginInputBoundary interactor = new UserLoginInteractor(
//                    gateway, presenter);
//            UserLoginController userLoginController = new UserLoginController(interactor);
//            setVisible(false);
//            dispose();
//            new LoginScreen(userLoginController).setVisible(true);
//
//            UserLoginResponseModel user = userLoginController.create(user.getSignedInUsername(),
//                    user.getPassword());
//            this.dispose();
            new HomePage(user);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    public static void main(String[] args) throws IOException {
        Map<Integer, String[]> map = new HashMap<>();
        for (int i = 1; i < 4; i++) {
            map.put(i, new String[]{"test set " + i, "test description " + i});
        }
        UserLoginResponseModel user = new UserLoginResponseModel("Lucas", false, map);
        new HomePage(user);
    }
}
