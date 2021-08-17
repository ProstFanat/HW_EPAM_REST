package author;

import entity.Author.Author;
import methods.AuthorMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import utils.PropertiesReader;

public class SearchAuthorTest {

    private final AuthorService authorService = new AuthorService();

    @Test(description = "Test of search author by first name")
    private void testSearchAuthorByFirstName(){
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);

        baseResponse = authorService.searchAuthor(author.getAuthorName().getFirst());
        Assert.assertEquals(baseResponse.getListOfBody().get(0), author);
    }

    @Test(description = "Test of search author by last name")
    private void testSearchAuthorByLastName(){
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);

        baseResponse = authorService.searchAuthor(author.getAuthorName().getSecond());
        Assert.assertEquals(baseResponse.getListOfBody().get(0), author);
    }

    @Test(description = "Test of search author with Bad Request")
    private void testSearchAuthorBadRequest(){
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);

        baseResponse = authorService.searchAuthor(PropertiesReader.getProperty("EMPTY"));
        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_PHRASE_CANT_BE_BLANK"));
    }

}
