package studyMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MockSettingsView {

    StudySessionController controller;

    BufferedReader reader;

    public MockSettingsView(StudySessionController controller){
        this.controller = controller;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public int eventHandler(int flashcardSetId) throws IOException {
        StudySettingsRequestModel request;
        System.out.println("Set default view: term, def?");
        boolean termIsDefault = reader.readLine().equals("term");

        System.out.println("Sort by: alphabetical (alph), creation time (time), shuffle?");
        String sortingOrder = reader.readLine();

        if (sortingOrder.equals("shuffle")) {
            request = new StudySettingsRequestModel(flashcardSetId, sortingOrder,
                    termIsDefault);
        }
        else {
            System.out.println("Reverse order (y/n)?");
            boolean isReverse = reader.readLine().equals("y");

            request = new StudySettingsRequestModel(flashcardSetId, sortingOrder,
                    termIsDefault, isReverse);
        }
        StudySettingsResponseModel response = this.controller.getSetToStudy(request);
        System.out.println("You are studying " + response.getTitle());
        System.out.println("Card " + response.getCardNumber() + " of " + response.getNumFlashcards());
        System.out.println(response.getOutputText());
        return response.getNumFlashcards();
    }
}
