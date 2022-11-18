package studyMode;


import dataAccess.DBGateway;
import entities.Flashcard;
import entities.FlashcardStudier;
import entities.comparators.FlashcardAlphComparator;
import entities.comparators.FlashcardByDateComparator;

import java.util.Comparator;

/**
 * The study session interactor
 * <p>
 * Application Business Rules.
 * @author Lucas Prates
 */
public class StudySessionInteractor implements StudySessionInputBoundary {

    private FlashcardStudier studier;
    private final StudySessionOutputBoundary presenter;

    private final FlashcardStudierBuilder builder;

    /**
     * Creates a StudySessionInteractor
     * @param gateway a gateway which gives this class database access
     * @param presenter an object capable of updating the view data
     */
    public StudySessionInteractor(DBGateway gateway,
                                  StudySessionOutputBoundary presenter){
        this.builder = new FlashcardStudierBuilder(gateway);
        this.presenter = presenter;

    }

    @Override
    public StudySessionResponseModel study(StudySessionRequestModel request) {
        String outputText=null;
        String command = request.getCommand();

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

        return presenter.updateStudyView(outputText, cardNumber);

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

            return presenter.prepareSuccessStudyView(studier.getOutputText(), studier.getTitle(),
                    studier.getNumFlashcards(), studier.getFlashcardSetId());
        }
        catch (IndexOutOfBoundsException exception) {
            return presenter.prepareFailedStudyView();
        }
    }
}
