package study_mode_use_case;


import frameworks_and_drivers.database.DBGateway;
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

    /**
     * a Flashcard Set which can handle study mode operations (flipping, going to next flashcard,
     * going to previous flashcard)
     */
    private FlashcardStudier studier;

    /**
     * an object which can prepare data for the view
     */
    private final StudySessionOutputBoundary presenter;

    /**
     * an object that knows how to build a flashcard studier
     */
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
    public StudySettingsResponseModel getSetToStudy(StudySettingsRequestModel request) {
        this.studier = builder.buildStudier(request.getFlashcardSetId(), request.isTermDefault());

        if (this.studier.getNumFlashcards() == 0) {
            String error = "<html>This flashcard set is empty. Please add some flashcards to begin studying!</html>";
            return presenter.prepareFailedStudyView(error);
        }
        else {
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
    }
}
