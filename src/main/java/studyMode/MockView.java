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


    public void eventHandler() throws IOException {
        StudySessionRequestModel request = new StudySessionRequestModel();
        System.out.println("prev, flip, next, quit?");

        // Reading data using readLine
        String command = reader.readLine();

        if (command.equals("flip")){
            request.setFlip();
            this.controller.study(request);
        }
        else if (command.equals("prev")){
            request.setPrev();
            this.controller.study(request);
        }
        else if (command.equals("next")){
            request.setNext();
            this.controller.study(request);
        }
        else if (command.equals("quit")){
            request.setQuit();
            this.controller.study(request);
        }
        else {
            this.eventHandler();
        }
    }
}
