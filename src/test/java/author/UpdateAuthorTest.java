package author;

import entity.Author;
import methods.AuthorMethods;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import utils.PropertiesReader;

public class UpdateAuthorTest {
    private final AuthorService authorService = new AuthorService();

    @Test(description = "Test of positive update author")
    private void testPositiveUpdateAuthor(){
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Object> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(201, baseResponse.getStatusCode());
        author.setNationality(PropertiesReader.getProperty("ANOTHER_NATIONALITY"));
        baseResponse = authorService.updateAuthor(author);
        Assert.assertEquals(200, baseResponse.getStatusCode());
    }

    @Test(description = "Test of update author that not found")
    private void testUpdateUserThatNotFound(){
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Object> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(201, baseResponse.getStatusCode());
        author.setAuthorId(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));

        baseResponse = authorService.updateAuthor(author);
        Assert.assertEquals(404, baseResponse.getStatusCode());
    }

    @Test(description = "Test of Update Author without body")
    private void testUpdateAuthorWithoutBody(){
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Object> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(201, baseResponse.getStatusCode());
        author = null;

        baseResponse = authorService.updateAuthor(author);
        Assert.assertEquals(400, baseResponse.getStatusCode());
    }
}
