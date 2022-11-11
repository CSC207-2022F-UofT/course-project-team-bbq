package studyMode;

public class StudySessionRequestModel {
    private boolean flip = false;
    private boolean next = false;
    private boolean prev = false;


    public boolean wantsFlip() {
        return flip;
    }

    public void setFlip() {
        this.flip = true;
    }

    public boolean wantsNext() {
        return next;
    }

    public void setNext() {
        this.next = true;
    }

    public boolean wantsPrev() {
        return prev;
    }

    public void setPrev() {
        this.prev = true;
    }
}
