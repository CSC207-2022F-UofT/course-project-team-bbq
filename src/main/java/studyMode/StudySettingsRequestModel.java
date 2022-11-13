package studyMode;

public class StudySettingsRequestModel {
    private int flashcardSetId;
    private String sortingOrder;

    private boolean isReverse = false;
    private boolean termIsDefault;

    public StudySettingsRequestModel(int flashcardSetId, String sortingOrder,
                                     boolean termIsDefault) {
        this.flashcardSetId = flashcardSetId;
        this.sortingOrder = sortingOrder;
        this.termIsDefault = termIsDefault;
    }

    public StudySettingsRequestModel(int flashcardSetId, String sortingOrder,
                                     boolean termIsDefault, boolean isReverse) {
        this.flashcardSetId = flashcardSetId;
        this.sortingOrder = sortingOrder;
        this.termIsDefault = termIsDefault;
        this.isReverse = isReverse;
    }

    public int getFlashcardSetId() {
        return flashcardSetId;
    }

    public String getSortingOrder() {
        return sortingOrder;
    }

    public boolean isTermDefault() {
        return termIsDefault;
    }

    public boolean isReverse() {
        return isReverse;
    }

    public void setReverse() {
        isReverse = !isReverse;
    }
}
