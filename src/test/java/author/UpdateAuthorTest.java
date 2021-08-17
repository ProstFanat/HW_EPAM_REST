package author;

import entity.Author.Author;
import methods.AuthorMethods;
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

        BaseResponse<Author> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);

        author.setNationality(PropertiesReader.getProperty("ANOTHER_NATIONALITY"));
        baseResponse = authorService.updateAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 200);
        Assert.assertEquals(baseResponse.getBody(), author);
    }

    @Test(description = "Test of update author that not found")
    private void testUpdateUserThatNotFound(){
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);
        author.setAuthorId(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));

        baseResponse = authorService.updateAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 404);
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_AUTHOR_NOT_EXIST"),
                        PropertiesReader.getProperty("NOT_FOUND_ID")));
    }

    @Test(description = "Test of Update Author without body")
    private void testUpdateAuthorWithoutBody(){
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);
        author = null;

        baseResponse = authorService.updateAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_BODY_IS_MISSING"));
    }
}
