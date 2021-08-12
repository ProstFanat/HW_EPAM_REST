package author;

import entity.Author;
import methods.AuthorMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import utils.PropertiesReader;

public class DeleteAuthorTest {
    private final AuthorService authorService = new AuthorService();

    @Test(description = "Test of deleting author by id")
    private void testDeleteAuthorById(){
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Object> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(201, baseResponse.getStatusCode());

        baseResponse = authorService.deleteAuthor(author.getAuthorId());
        Assert.assertEquals(204, baseResponse.getStatusCode());
    }

    @Test(description = "Test of deleting author that not found")
    private void testGetAuthorNotFound(){
        BaseResponse<Object> baseResponse = authorService.deleteAuthor(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));
        Assert.assertEquals(404, baseResponse.getStatusCode());
    }
}
