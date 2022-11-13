package dataAccess;

public interface IGeneralDataAccess {

    IFlashcardDataAccess getFlashcardAccess();

    IFlashcardSetDataAccess getFlashcardSetAccess();

    IUserDataAccess getUserAccess();
}
