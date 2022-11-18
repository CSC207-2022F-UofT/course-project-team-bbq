package MainPage;

import dataAccess.CommonUserDataAccess;
import dataAccess.DBGateway;
import dataAccess.FlashcardDataAccess;
import dataAccess.FlashcardSetDataAccess;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HomePage extends JFrame {
//
//    UserResponseModel user;
//
//    public HomePage(UserResponseModel user) throws IOException {
//        // initialize DBGateway
//        DBGateway gateway = new DBGateway(new FlashcardDataAccess(DBGateway.getFlashcardPath()),
//                new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath()),
//                new CommonUserDataAccess(DBGateway.getUserPath()));
//
//
//        user.getUsername();
//        Map<Integer, String[]> idsToFlashcardSetData = user.getFlashcardSets();
//
//        int numSets = idsToFlashcardSetData.size();
//        if (numSets==0){
//            new JLabel("You have no new FlashcardSets!");
//        }
//        else {
//            this.add(new ListOfFlashcardSetsDataPanel(idsToFlashcardSetData, gateway, true));
//        }
//        this.setSize(1000, 1000);
//        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//
//    public static void main(String[] args) {
//        Map<Integer, String[]> map = new HashMap<>();
//        for (int i = 1; i < 4; i++) {
//            map.put(i, new String[]{"test set " + i, "test description " + i});
//        }
//
////        new HomePage(map);
//    }
}
