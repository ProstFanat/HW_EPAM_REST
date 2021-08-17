package author;

import entity.Author.Author;
import methods.AuthorMethods;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import utils.PropertiesReader;

public class GetAuthorTest {

    private final AuthorService authorService = new AuthorService();

    @Test(description = "Test of getting author by id")
    private void testGetAuthorById(){
        Author author = AuthorMethods.generateAuthor();

        BaseResponse<Author> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);

        baseResponse = authorService.getAuthor(author.getAuthorId());
        Assert.assertEquals(baseResponse.getStatusCode(), 200);
        Assert.assertEquals(baseResponse.getBody(), author);
    }

    @Test(description = "Test of getting author that not found")
    private void testGetAuthorNotFound(){
        BaseResponse<Author> baseResponse = authorService.getAuthor(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));
        Assert.assertEquals(baseResponse.getStatusCode(), 404);
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_AUTHOR_NOT_EXIST"),
                        PropertiesReader.getProperty("NOT_FOUND_ID")));
    }

    @Test(description = "Test of getting author BAD REQUEST")
    private void testGetAuthorBadRequest(){
        BaseResponse<Object> baseResponse = authorService.getAuthorBadRequest("BAD REQUEST");
        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_AUTHOR_ID_MUST_BE_LONG"));
    }
}
