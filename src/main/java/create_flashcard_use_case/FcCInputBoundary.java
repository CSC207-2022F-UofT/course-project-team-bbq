package create_flashcard_use_case;

/**
 * Input boundary for flashcard creation.
 * Application business rules.
 * @author Junyu Chen
 */

public interface FcCInputBoundary {
    FcCResponseModel create(FcCRequestModel requestModel);

}
