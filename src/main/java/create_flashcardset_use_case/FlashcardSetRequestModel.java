package create_flashcardset_use_case;

// Use case (Red) layer

public class FlashcardSetRequestModel {
    private String title;
    private String description;
    private boolean isPrivate;
    private String username;
//    private String action;  // e.g., when user clicks on "discard" button, action sends "discard" string

    public FlashcardSetRequestModel(String title, String description, boolean isPrivate, String username) {
        this.title = title;
        this.description = description;
        this.isPrivate = isPrivate;
        this.username = username;
//        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username; }

//    public String getAction() {
//        return action;
//    }
//
//    public void setAction(String action) {
//        this.action = action;
//    }
}
