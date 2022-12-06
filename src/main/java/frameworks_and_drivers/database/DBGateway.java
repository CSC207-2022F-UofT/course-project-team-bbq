package frameworks_and_drivers.database;

import data_access_use_case.IFlashcardDataAccess;
import data_access_use_case.IFlashcardSetDataAccess;
import data_access_use_case.IUserDataAccess;
import data_access_use_case.entity_request_models.CommonUserDsRequestModel;
import data_access_use_case.entity_request_models.FlashcardDsRequestModel;
import data_access_use_case.entity_request_models.FlashcardSetDsRequestModel;

public class DBGateway {
    private static final String flashcardPath = "src/data/Flashcards.csv";
    private static final String flashcardSetPath = "src/data/FlashcardSets.csv";
    private static final String userPath = "src/data/Users.csv";
    /**
     * The gateway object to access flashcard data
     */
    final IFlashcardDataAccess flashcardGateway;
    /**
     * The gateway object to access flashcard set data
     */
    final IFlashcardSetDataAccess flashcardSetGateway;
    /**
     * The gateway object to access user data
     */
    final IUserDataAccess userGateway;

    /**
     * Creates a DBGateway object which contain gateways to every csv file in the database. This acts as a facade class
     * for the other gateways.
     * @param flashcardGateway the flashcard gateway object.
     * @param flashcardSetGateway the flashcard set gateway object.
     * @param userGateway the user gateway object.
     */
    public DBGateway(IFlashcardDataAccess flashcardGateway,
                     IFlashcardSetDataAccess flashcardSetGateway,
                     IUserDataAccess userGateway){
        this.flashcardGateway = flashcardGateway;
        this.flashcardSetGateway = flashcardSetGateway;
        this.userGateway = userGateway;
    }

    /**
     * Gets the user object with a given username.
     * @param username the user's username.
     * @return the user object with the given username.
     */
    public CommonUserDsRequestModel getUser(String username) { return this.userGateway.getUser(username); }
    /**
     * Returns the value for if the user contains the given username.
     * @param username the user's username.
     * @return true if the user contains the given username.
     */
    public boolean existsByName(String username){
        return this.userGateway.existsByName(username);
    }

    /**
     * Saves the user.
     * @param user the common user request model that will be saved.
     */
    public void saveUser(CommonUserDsRequestModel user) {
        this.userGateway.saveUser(user);
    }

    /**
     * Saves the flashcard set id into the list of flashcard set ids of the user that created the flashcard set.
     * @param flashcardSet the flashcard set request model that will be saved.
     * @return the id of the flashcard set
     */
    public int saveFlashcardSet(FlashcardSetDsRequestModel flashcardSet) {
        int id = this.flashcardSetGateway.saveFlashcardSet(flashcardSet);
        this.userGateway.saveFlashcardSetID(flashcardSet.getOwnerUsername(), flashcardSet.getFlashcardSetId());
        return id;
    }
    /**
     * Edits the title and description of a flashcard set and saves the given edits.
     * @param flashcardSet the flashcard set request model that will be edited.
     */
    public void editFlashcardSet(FlashcardSetDsRequestModel flashcardSet){
        this.flashcardSetGateway.editTitleAndDescription(flashcardSet);
    }

    /**
     * Deletes the flashcard set id from the owner.
     * @param ownerUsername the username of user that created the flashcard set.
     * @param flashcardSetID the id of the flashcard set that will be deleted.
     */
    public void deleteFlashcardSet(String ownerUsername, int flashcardSetID) {
        this.userGateway.deleteFlashcardSetID(ownerUsername, flashcardSetID);
        this.flashcardSetGateway.deleteFlashcardSet(flashcardSetID);
    }

    /**
     * Saves the flashcard id to the list of flashcard ids contained in the flashcard set.
     * @param flashcard the flashcard request model that will be saved.
     * @return the id of the flashcard that is saved.
     */
    public int saveFlashcard(FlashcardDsRequestModel flashcard) {
        int id = this.flashcardGateway.saveFlashcard(flashcard);
        this.flashcardSetGateway.saveFlashcardID(flashcard.getBelongsToId(), id);
        return id;
    }

    /**
     * Edits the flashcard and saves the given edits.
     * @param flashcard the flashcard request model that will be edited
     */
    public void editFlashcard(FlashcardDsRequestModel flashcard){
        this.flashcardGateway.editFlashcard(flashcard);
    }

    /**
     * Deletes a flashcard id from a flashcard set and deletes the flashcard from the database.
     * @param flashcardSetId the id of the flashcard set.
     * @param flashcardId the id of the flashcard that will be deleted.
     */
    public void deleteFlashcard(int flashcardSetId, int flashcardId) {
        this.flashcardSetGateway.removeFlashcardId(flashcardSetId, flashcardId);
        this.flashcardGateway.deleteFlashcard(flashcardId);
    }

    /**
     * Gets the flashcard path to the csv containing all the flashcards.
     * @return the flashcard path to the csv containing all the flashcards.
     */
    public static String getFlashcardPath() {
        return flashcardPath;
    }
    /**
     * Gets the flashcard set path to the csv containing all the flashcard sets.
     * @return the flashcard set path to the csv containing all the flashcard sets.
     */
    public static String getFlashcardSetPath() {
        return flashcardSetPath;
    }
    /**
     * Gets the user path to the csv containing all the users.
     * @return the user path to the csv containing all the users.
     */
    public static String getUserPath() {
        return userPath;
    }

    /**
     * Gets the flashcard containing a given flashcard id.
     * @param flashcardId the flashcard id.
     * @return the flashcard request model containing a given flashcard id.
     */
    public FlashcardDsRequestModel getFlashcard(int flashcardId){
        return this.flashcardGateway.getFlashcard(flashcardId);
    }
    /**
     * Gets the flashcard set containing a given flashcard set id.
     * @param flashcardSetId the flashcard set id.
     * @return the flashcard set request model containing a given flashcard set id.
     */
    public FlashcardSetDsRequestModel getFlashcardSet(int flashcardSetId){
        return this.flashcardSetGateway.getFlashcardSet(flashcardSetId);
    }
    /**
     * Gets the user containing a given username.
     * @param username the user's username.
     * @return the common user request model containing a given username.
     */
    public CommonUserDsRequestModel getCommonUser(String username){
        return this.userGateway.getUser(username);
    }

    /**
     * Gets the flashcard gateway.
     * @return the flashcard gateway.
     */
    public IFlashcardDataAccess getFlashcardGateway() {
        return flashcardGateway;
    }
    /**
     * Gets the flashcard set gateway.
     * @return the flashcard set gateway.
     */
    public IFlashcardSetDataAccess getFlashcardSetGateway() {
        return flashcardSetGateway;
    }
    /**
     * Gets the user gateway.
     * @return the user gateway.
     */
    public IUserDataAccess getUserGateway() {
        return userGateway;
    }

    /**
     * Gets the title and description of a flashcard set containing the given flashcard set id.
     * @param flashcardSetId the flashcard set id.
     * @return the title and description of a flashcard set containing the given flashcard set id.
     */
    public String[] getTitleAndDescription(int flashcardSetId) {
        return this.flashcardSetGateway.getTitleAndDescription(flashcardSetId);
    }
}
