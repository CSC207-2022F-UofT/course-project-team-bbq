package search_use_case;

import data_access.*;
import login_and_signup_use_case.UserLoginResponseModel;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchUseCaseTest {
    SearchController controller;
    UserLoginResponseModel common_user = new UserLoginResponseModel("common_user",
            false, new HashMap<>());
    UserLoginResponseModel admin = new UserLoginResponseModel("admin",
            true, new HashMap<>());

    SearchRequestModel setup(String search_input, ArrayList<String> tags,
                              UserLoginResponseModel user) throws IOException {
        IFlashcardDataAccess flashcardGateway = new FlashcardDataAccess(
                "src/test/java/search_use_case/test_data/Flashcards.csv");
        IFlashcardSetDataAccess flashcardSetGateway = new FlashcardSetDataAccess(
                "src/test/java/search_use_case/test_data/FlashcardSets.csv");
        IUserDataAccess userGateway = new CommonUserDataAccess(
                "src/test/java/search_use_case/test_data/Users.csv");
        DBGateway gateway = new DBGateway(flashcardGateway, flashcardSetGateway, userGateway);

        SearchOutputBoundary search_presenter = new SearchPresenter();
        SearchInputBoundary search_interactor = new SearchInteractor(search_presenter, gateway);
        this.controller = new SearchController(search_interactor);

        return new SearchRequestModel(search_input, tags, user);
    }

    @Test
    void testSearchAll() throws IOException {
        SearchRequestModel searchRequestModel = this.setup("GET_ALL", new ArrayList<>(), common_user);
        SearchResponseModel responseModel = controller.create(searchRequestModel);

        assertEquals(4, responseModel.getResult_set().size());
        assertEquals("test4", responseModel.getResult_set().get(0).getTitle());
        assertEquals("test1", responseModel.getResult_set().get(3).getTitle());

    }
    // Test Search results on all 3 tags

    @Test
    void testSearchTitle() throws IOException{
        ArrayList<String> tags = new ArrayList<>();
        tags.add("Title");

        SearchRequestModel searchRequestModel = this.setup("test1", tags, common_user);
        SearchResponseModel responseModel = controller.create(searchRequestModel);

        assertEquals(1, responseModel.getResult_set().size());
        assertEquals("test1", responseModel.getResult_set().get(0).getTitle());
    }

    @Test
    void testSearchDescription() throws IOException{
        ArrayList<String> tags = new ArrayList<>();
        tags.add("Description");

        SearchRequestModel searchRequestModel = this.setup("d2", tags, common_user);
        SearchResponseModel responseModel = controller.create(searchRequestModel);

        assertEquals(1, responseModel.getResult_set().size());
        assertEquals("d2", responseModel.getResult_set().get(0).getDescription());
    }

    @Test
    void testSearchOwner() throws IOException{
        ArrayList<String> tags = new ArrayList<>();
        tags.add("Owner");

        SearchRequestModel searchRequestModel = this.setup("testUser3", tags, common_user);
        SearchResponseModel responseModel = controller.create(searchRequestModel);

        assertEquals(1, responseModel.getResult_set().size());
        assertEquals("testUser3", responseModel.getResult_set().get(0).getOwnerUsername());
    }


    // Test Admin search ignores privates
    @Test
    void testAdminSearch() throws IOException{
        SearchRequestModel searchRequestModel = this.setup("GET_ALL", new ArrayList<>(), admin);
        SearchResponseModel responseModel = controller.create(searchRequestModel);

        assertEquals(6, responseModel.getResult_set().size());
        assertEquals("test6", responseModel.getResult_set().get(2).getTitle());
        assertEquals("testUser1", responseModel.getResult_set().get(5).getOwnerUsername());
    }
}
