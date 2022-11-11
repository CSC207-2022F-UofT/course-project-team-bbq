package studyMode;

public class StudySettingsRequestModel {
    private int flashcardSetId;
    private String sortingOrder;
    private boolean termIsDefault;

    public StudySettingsRequestModel(int flashcardSetId, String sortingOrder, boolean termIsDefault) {
        this.flashcardSetId = flashcardSetId;
        this.sortingOrder = sortingOrder;
        this.termIsDefault = termIsDefault;
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
}
