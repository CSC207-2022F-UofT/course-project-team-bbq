package create_flashcard_set_use_case;

// Use case (Red) layer

/**
 * The request model required for creating a flashcard set.
 *
 * @author Edward Ishii
 */
public class CreateFlashcardSetRequestModel {
    private String title;
    private String description;
    private boolean isPrivate;
    private String username;

    /**
     * Constructs the request model.
     *
     * @param title       the title of the flashcard set.
     * @param description the description of the flashcard set.
     * @param isPrivate   the privacy of the flashcard set.
     * @param username    the owner's username of the flashcard set.
     */
    public CreateFlashcardSetRequestModel(String title, String description, boolean isPrivate, String username) {
        this.title = title;
        this.description = description;
        this.isPrivate = isPrivate;
        this.username = username;
    }

    /**
     * Getter for the flashcard set title.
     *
     * @return the title of this flashcard set.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the flashcard set title.
     *
     * @param title the new title for this flashcard set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for the description of the flashcard set.
     *
     * @return the description of this flashcard set.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for the description of the flashcard set.
     *
     * @param description the new description for this flashcard set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for the privacy of the flashcard set.
     *
     * @return the privacy of this flashcard set.
     */
    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * Setter for the privacy of the flashcard set.
     *
     * @param aPrivate the new privacy status for this flashcard set.
     */
    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    /**
     * Getter for the owner's username of the flashcard set.
     *
     * @return the owner's username of this flashcard set.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the owner's username of the flashcard set.
     *
     * @param username the new owner's username of this flashcard set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
