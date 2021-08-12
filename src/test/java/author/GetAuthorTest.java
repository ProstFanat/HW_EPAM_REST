package author;

import entity.Author;
import methods.AuthorMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import utils.PropertiesReader;

public class GetAuthorTest {

    private final AuthorService authorService = new AuthorService();

    @Test(description = "Test of getting author by id")
    private void testGetAuthorById(){
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Object> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(201, baseResponse.getStatusCode());

        baseResponse = authorService.getAuthor(author.getAuthorId());
        Assert.assertEquals(200, baseResponse.getStatusCode());
    }

    @Test(description = "Test of getting author that not found")
    private void testGetAuthorNotFound(){
        BaseResponse<Object> baseResponse = authorService.getAuthor(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));
        Assert.assertEquals(404, baseResponse.getStatusCode());
    }

    @Test(description = "Test of getting author BAD REQUEST")
    private void testDeleteAuthorBadRequest(){
        BaseResponse<Object> baseResponse = authorService.getAuthorBadRequest("BAD REQUEST");
        Assert.assertEquals(400, baseResponse.getStatusCode());
    }
}
