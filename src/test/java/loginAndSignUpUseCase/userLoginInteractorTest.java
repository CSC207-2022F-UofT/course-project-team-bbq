//package loginAndSignUpUseCase;
//
//import dataAccess.CommonUserDataAccess;
//import dataAccess.DBGateway;
//import dataAccess.IUserDataAccess;
//import entities.CommonUserFactory;
//import entities.UserFactory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import loginAndSignupUseCase.*;
//import org.junit.jupiter.api.Test;
//import org.testing.annotations.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//import java.io.IOException;
//
//import static org.junit.Assert.*;
//import static org.testng.AssertJUnit.*;
//
//class UserRegisterInteractorTest {
//      IUserDataAccess userRepository = new UserRegisterTestHelper();
//        UserRegisterOutputBoundary presenter = new UserRegisterOutputBoundary() {
//
//        UserFactory userFactory = new CommonUserFactory();
//        UserRegisterInputBoundary interactor = new UserRegisterInteractor(
//                userRepository, presenter, userFactory);
//
//        UserRegisterRequestModel inputData = new UserRegisterRequestModel(
//                "Richard", "Virgin123", "Virgin123", "BuiltDifferent");
//
//         UserRegisterRequestModel inputData2 = new UserRegisterRequestModel(
//                "Branson", "Virgin123", "Virgin123", "");
//
//        interactor.create(inputData);
//        interactor.create(inputData2);

//        IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess(DBGateway.getFlashcardPath());
//        IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess(DBGateway.getFlashcardSetPath());
//        IUserDataAccess userGateway = new CommonUserDataAccess(DBGateway.getUserPath());
//
//        DBGateway gateway = new DBGateway(flashcardGateway, flashcardSetGateway, userGateway);
//
//    @Test
//    void login() throws IOException {
//
//        UserLoginOutputBoundary presenter = new UserLoginOutputBoundary() {
//            @Override
//            public UserLoginResponseModel prepareSuccessView(UserLoginResponseModel user) {
//                assertEquals("Richard", user.getSignedInUsername());
//                assertEquals(true, user.getIsAdmin());
//                assertEquals({}, user.getFlashcardSet());
//                return null;
//            }
//
//            @Override
//            public UserLoginResponseModel prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//                return null;
//            }
//        };
//
//        UserLoginInputBoundary interactor = new UserLoginInteractor(
//                userRepository, presenter, userFactory);
//
//    }
//
//    @Test
//    void create2() throws IOException {
//
//
//    }
//
//}