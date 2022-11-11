package studyMode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MockView {
    StudySessionController controller;
    BufferedReader reader;

    public MockView(StudySessionController controller){
        this.controller = controller;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }


    public boolean eventHandler() throws IOException {
        StudySessionRequestModel request = new StudySessionRequestModel();
        System.out.println("prev, flip, next, quit?");

        // Reading data using readLine
        String command = reader.readLine();

        if (command.equals("quit")){
            System.out.println("Goodbye...");
            return true;
        }
        else{
            request.setCommand(command);
            StudySessionResponseModel response = this.controller.study(request);
            System.out.println("Card " + response.getCardNumber());
            System.out.println(response.getOutputText());
            return false;
        }
    }
}
