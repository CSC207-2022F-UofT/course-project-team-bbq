package delete_flashcard_use_case;
/**
 * Input boundary for flashcard remover.
 * Application business rules.
 * @author Junyu Chen
 */
public interface FcRInputBoundary {
    FcRResponseModel delete(FcRRequestModel requestModel);
}
