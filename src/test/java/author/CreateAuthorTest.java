package author;

import entity.Author.Author;
import methods.AuthorMethods;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import utils.PropertiesReader;

public class CreateAuthorTest {

    private final AuthorService authorService = new AuthorService();
    private Author author;
    private BaseResponse<Author> baseResponse;

    @BeforeMethod
    public void setup(){
        author = AuthorMethods.generateAuthor();
        baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);
    }

    @Test(description = "Test of positive creating author")
    private void testPositiveScenario(){
        Assert.assertEquals(baseResponse.getBody(), author);
    }

    @Test(description = "Test of Author with such id already exists")
    private void testCreateAuthorWithExistsId(){
        baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 409);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_USER_ALREADY_EXIST"));
    }

    @Test(description = "Test of Author without body")
    private void testCreateAuthorWithoutBody(){
        baseResponse = authorService.createAuthor(null);
        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_BODY_IS_MISSING"));
    }
}
