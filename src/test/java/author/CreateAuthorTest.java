package author;

import entity.Author;
import methods.AuthorMethods;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.VerifyService;
import utils.PropertiesReader;

public class CreateAuthorTest {

    private final AuthorService authorService = new AuthorService();
    private final VerifyService verifyService = new VerifyService();
    private Author author;
    private BaseResponse<Author> baseResponse;

    @BeforeMethod
    public void setup(){
        author = AuthorMethods.generateAuthor();
        baseResponse = authorService.createAuthor(author);
    }

    @Test(description = "Test of positive creating author")
    private void testPositiveScenario(){
        verifyService.verifyCreatedSuccess(baseResponse, author);
    }

    @Test(description = "Test of Author with such id already exists")
    private void testCreateAuthorWithExistsId(){
        baseResponse = authorService.createAuthor(author);
        verifyService.verifyEntityAlreadyExist(baseResponse, PropertiesReader.getProperty("ENTITY_AUTHOR"));
    }

    @Test(description = "Test of Author without body")
    private void testCreateAuthorWithoutBody(){
        baseResponse = authorService.createAuthor(null);
        verifyService.verifyRequestWithoutBody(baseResponse);
    }
}
