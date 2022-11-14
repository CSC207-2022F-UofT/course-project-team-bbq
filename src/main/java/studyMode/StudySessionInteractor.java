package studyMode;


import dataAccess.DBGateway;
import entities.Flashcard;

import java.util.Comparator;

public class StudySessionInteractor implements StudySessionInputBoundary {

    private FlashcardStudier studier;
    private StudySessionOutputBoundary presenter;

    private DBGateway gateway;

    private FlashcardStudierBuilder builder;

    public StudySessionInteractor(DBGateway gateway,
                                  StudySessionOutputBoundary presenter){
        this.gateway = gateway;
        this.builder = new FlashcardStudierBuilder(gateway);
        this.presenter = presenter;

    }

    @Override
    public StudySessionResponseModel study(StudySessionRequestModel userInput) {
        String outputText=null;
        String command = userInput.getCommand();

        switch (command) {
            case "flip":
                outputText = studier.flipCard();
                break;
            case "next":
                outputText = studier.getNextCard();
                break;
            case "prev":
                outputText = studier.getPrevCard();
                break;
        }
        int cardNumber = studier.getCounter() + 1;

        return presenter.prepareCardView(outputText, cardNumber);

    }

    @Override
    public StudySettingsResponseModel getSetToStudy(StudySettingsRequestModel request) throws IndexOutOfBoundsException {
        this.studier = builder.buildStudier(request.getFlashcardSetId(), request.isTermDefault());

        try {
            String sortingOrder = request.getSortingOrder();
            if (sortingOrder.equals("shuffle")) {
                studier.shuffle();
            } else {
                Comparator<Flashcard> comparator;
                if ("alph".equals(sortingOrder)) {
                    comparator = new FlashcardAlphComparator();
                } else {
                    comparator = new FlashcardByDateComparator();
                }
                if (request.isReverse()) {
                    studier.reverse(comparator);
                } else {
                    studier.sort(comparator);
                }
            }

            return presenter.prepareStudyView(studier.getOutputText(), studier.getTitle(), studier.getNumFlashcards());
        }
        catch (IndexOutOfBoundsException exception) {
            return presenter.prepareFailedStudyView();
        }
    }
}
