package author;

import entity.Author;
import methods.AuthorMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import utils.PropertiesReader;

public class CreateAuthorTest {

    private final AuthorService authorService = new AuthorService();


    @Test(description = "Test of positive creating author")
    private void testPositiveScenario(){
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);
        Assert.assertTrue(baseResponse.getBody().equals(author));
    }

    @Test(description = "Test of Author with such id already exists")
    private void testCreateAuthorWithExistsId(){
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);

        baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 409);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_USER_ALREADY_EXIST"));
    }

    @Test(description = "Test of Author without body")
    private void testCreateAuthorWithoutBody(){
        BaseResponse<Author> baseResponse = authorService.createAuthor(null);
        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_BODY_IS_MISSING"));
    }
}
