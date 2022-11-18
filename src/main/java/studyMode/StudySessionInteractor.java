package studyMode;


import dataAccess.DBGateway;
import entities.Flashcard;
import entities.FlashcardStudier;
import entities.comparators.FlashcardAlphComparator;
import entities.comparators.FlashcardByDateComparator;

import java.util.Comparator;

public class StudySessionInteractor implements StudySessionInputBoundary {

    private FlashcardStudier studier;
    private final StudySessionOutputBoundary presenter;

    private final FlashcardStudierBuilder builder;

    public StudySessionInteractor(DBGateway gateway,
                                  StudySessionOutputBoundary presenter){
        this.builder = new FlashcardStudierBuilder(gateway);
        this.presenter = presenter;

    }

    @Override
    public StudySessionResponseModel study(StudySessionRequestModel userInput) {
        String outputText=null;
        String command = userInput.getCommand();

        switch (command) {
            case StudySessionInputBoundary.flip:
                outputText = studier.flipCard();
                break;
            case StudySessionInputBoundary.next:
                outputText = studier.getNextCard();
                break;
            case StudySessionInputBoundary.prev:
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
            if (sortingOrder.equals(StudySessionInteractor.shuffleSort)) {
                studier.shuffle();
            } else {
                Comparator<Flashcard> comparator;
                if (StudySessionInteractor.alphSort.equals(sortingOrder)) {
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

            return presenter.prepareStudyView(studier.getOutputText(), studier.getTitle(),
                    studier.getNumFlashcards(), studier.getFlashcardSetId());
        }
        catch (IndexOutOfBoundsException exception) {
            return presenter.prepareFailedStudyView();
        }
    }
}
