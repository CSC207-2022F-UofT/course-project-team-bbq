package login_and_sign_up_use_case_test;

import entities.CommonUser;
import entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UserUnitTest {

    /*
     * Test rudimentary entity class of creating new users.
     */

    /**
     * Test creating a new user with an invalid password.
     */
    @Test
    void given123Password_whenPasswordIsNotValid_thenIsFalse() {
        User user = new CommonUser("Lucas", "123", false);

        Assertions.assertFalse(user.passwordIsValid());
    }

    /**
     * Test creating a new user with a valid password.
     */
    @Test
    void given12345Password_whenPasswordIsValid_thenIsTrue() {
        User user = new CommonUser("Justin", "123456", false);

        Assertions.assertTrue(user.passwordIsValid());
    }

    /**
     * Test creating a new user and retrieving that they are NOT an admin user.
     */
    @Test
    void givenAdminUserTrue_whenIsntAdmin_thenIsFalse() {
        User user = new CommonUser("Thomas", "12345", false);

        Assertions.assertFalse(user.getIsAdmin());
    }

    /**
     * Test creating a new user and retrieving that they are an admin user.
     */
    @Test
    void getAdminLevel() {
        User user = new CommonUser("Anthony", "12345", true);

        Assertions.assertTrue(user.getIsAdmin());
    }

    /**
     * Test creating a new user and having them have an empty list of flashcard set ids instantiated.
     */
    @Test
    void getListOfFlashcardSetIds() {
        User user = new CommonUser("Edward", "12345", false);
        ArrayList<Integer> check = new ArrayList<>();
        Assertions.assertEquals(user.getFlashcardSetIds(), check);
    }

    /**
     * Test creating a new user and checking whether they as a user have the ability to reference the admin key
     * if necessary.
     */
    @Test
    void adminKeyValidChecker() {
        User user = new CommonUser("Junyu", "12345", false);
        Assertions.assertTrue(user.adminKeyValid("BuiltDifferent"));
    }

    /**
     * Test creating a new user and checking with an invalid admin key.
     */
    @Test
    void adminKeyValidChecker2() {
        User user = new CommonUser("Winston", "12345", false);
        Assertions.assertFalse(user.adminKeyValid(" "));
    }

}
