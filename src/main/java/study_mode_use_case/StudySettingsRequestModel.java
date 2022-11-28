package study_mode_use_case;

/**
 * A data structure which specifies the ID of the flashcard set
 * the user wants to study, as well as how the user wishes to
 * sort the flashcard set.
 * <p>
 * Application Business Rules.
 * @author Lucas Prates
 */
public class StudySettingsRequestModel {
    private final int flashcardSetId;
    private final String sortingOrder;

    private boolean isReverse = false;
    private final boolean termIsDefault;

    /**
     * Creates a StudySettingsRequestModel
     * @param flashcardSetId the ID of the desired flashcard set
     * @param sortingOrder a string specifying the sorting order
     * @param termIsDefault a boolean specifying whether the default flashcard view is 'term'
     *                      or 'definition'
     */
    public StudySettingsRequestModel(int flashcardSetId, String sortingOrder,
                                     boolean termIsDefault) {
        this.flashcardSetId = flashcardSetId;
        this.sortingOrder = sortingOrder;
        this.termIsDefault = termIsDefault;
    }

    /**
     * Creates a StudySettingsRequestModel
     * @param flashcardSetId the ID of the desired flashcard set
     * @param sortingOrder a string specifying the sorting order
     * @param termIsDefault a boolean specifying whether the default flashcard view is 'term'
     *                      or 'definition'
     * @param isReverse a boolean which specifies whether the flashcard set will be sorted in
     *                  order or in reverse
     */
    public StudySettingsRequestModel(int flashcardSetId, String sortingOrder,
                                     boolean termIsDefault, boolean isReverse) {
        this.flashcardSetId = flashcardSetId;
        this.sortingOrder = sortingOrder;
        this.termIsDefault = termIsDefault;
        this.isReverse = isReverse;
    }

    /**
     * @return the flashcard set ID
     */
    public int getFlashcardSetId() {
        return flashcardSetId;
    }

    /**
     * @return the sorting order string
     */
    public String getSortingOrder() {
        return sortingOrder;
    }

    /**
     * @return true if the default flashcard view is 'term' and
     *         false otherwise
     */
    public boolean isTermDefault() {
        return termIsDefault;
    }

    /**
     * @return true if the flashcard set is sorted in reverse and
     *         false otherwise
     */
    public boolean isReverse() {
        return isReverse;
    }

}
