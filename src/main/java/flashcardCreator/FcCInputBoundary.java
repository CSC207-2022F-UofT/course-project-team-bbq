package flashcardCreator;

/**
 * Input boundary for flashcard creator.
 * Application business rules.
 * @author Junyu Chen
 */

public interface FcCInputBoundary {
    FcCResponseModel create(FcCRequestModel requestModel);

}
