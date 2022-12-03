//package create_flashcard_set_use_case;
//
//import dataAccess.*;
//import entities.FlashcardSetFactory;
//
//import javax.swing.*;
//import java.awt.*;
//import java.io.IOException;
//
//public class MainCreateFlashcardSet {
//
//    public static void main(String[] args) {
//
//        // Build the main program window
//        JFrame application = new JFrame("Create a Flashcard Set");
//        application.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        CardLayout cardLayout = new CardLayout();
//        JPanel screens = new JPanel(cardLayout);
//        application.add(screens);
//
//        // Create the parts to plug into the Use Case+Entities engine
//        DBGateway dbGateway = dbGatewaySetup();
//
//        FlashcardSetOutputBoundary outputBoundary = new FlashcardSetPresenter();
//        FlashcardSetFactory flashcardSetFactory = new FlashcardSetFactory();
//        FlashcardSetInputBoundary interactor = new FlashcardSetInteractor(dbGateway, outputBoundary,
//                flashcardSetFactory);
//        FlashcardSetController flashcardSetController = new FlashcardSetController(
//                interactor
//        );
//
//        // Build the GUI, plugging in the parts
//        CreationScreen creationScreen = new CreationScreen(flashcardSetController);
//        screens.add(creationScreen, "welcome");
//        cardLayout.show(screens, "create flashcard set");
//        application.pack();
//        application.setVisible(true);
//    }
//
//    public static DBGateway dbGatewaySetup() {
//        IFlashcardSetDataAccess flashcardSetRepo;
//        IFlashcardDataAccess flashcardRepo;
//        IUserDataAccess userRepo;
//        try {
//            flashcardSetRepo = new FlashcardSetDataAccess("src/data/FlashcardSets.csv");
//            flashcardRepo = new FlashcardDataAccess("src/data/Flashcards.csv");
//            userRepo = new CommonUserDataAccess("src/data/Users.csv");
//        } catch (IOException e) {
//            throw new RuntimeException("Could not create file.");
//        }
//
//        return new DBGateway(flashcardRepo, flashcardSetRepo, userRepo);
//    }
//}
