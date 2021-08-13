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

        BaseResponse<Author> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);

        baseResponse = authorService.deleteAuthor(author.getAuthorId());
        Assert.assertEquals(baseResponse.getStatusCode(), 204);
    }

    @Test(description = "Test of deleting author that not found")
    private void testDeleteAuthorNotFound(){
        BaseResponse<Author> baseResponse = authorService.deleteAuthor(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));
        Assert.assertEquals(baseResponse.getStatusCode(), 404);
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_AUTHOR_NOT_EXIST"),
                        PropertiesReader.getProperty("NOT_FOUND_ID")));
    }

    @Test(description = "Test of deleting author BAD REQUEST")
    private void testDeleteAuthorBadRequest(){
        BaseResponse<Object> baseResponse = authorService.deleteAuthorBadRequest("BAD REQUEST");
        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_ID_MUST_BE_LONG"));
    }
}
