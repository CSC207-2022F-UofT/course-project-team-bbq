package studyMode.screens;

import studyMode.StudySessionController;
import studyMode.StudySessionRequestModel;
import studyMode.StudySessionResponseModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MockStudyView {
    StudySessionController controller;
    BufferedReader reader;

    public MockStudyView(StudySessionController controller){
        this.controller = controller;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }


    public void eventHandler(int numFlashcards) throws IOException {

        StudySessionRequestModel request = new StudySessionRequestModel();
        System.out.println("prev, flip, next, quit?");

        // Reading data using readLine
        String command = reader.readLine();

        if (command.equals("quit")){
            System.out.println("Goodbye...");
        }
        else{
            request.setCommand(command);
            StudySessionResponseModel response = this.controller.study(request);
            System.out.println("Card " + response.getCardNumber() + " of " + numFlashcards);
            System.out.println(response.getOutputText());

            eventHandler(numFlashcards);
        }
    }
}