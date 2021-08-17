package author;

import entity.Author.Author;
import methods.AuthorMethods;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import utils.PropertiesReader;

public class SearchAuthorTest {

    private final AuthorService authorService = new AuthorService();
    private Author author;
    private BaseResponse<Author> baseResponse;

    @BeforeMethod
    public void setup(){
        author = AuthorMethods.generateAuthor();
        baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);
    }

    @Test(description = "Test of search author by first name")
    private void testSearchAuthorByFirstName(){
        baseResponse = authorService.searchAuthor(author.getAuthorName().getFirst());
        Assert.assertEquals(baseResponse.getListOfBody().get(0), author);
    }

    @Test(description = "Test of search author by last name")
    private void testSearchAuthorByLastName(){
        baseResponse = authorService.searchAuthor(author.getAuthorName().getSecond());
        Assert.assertEquals(baseResponse.getListOfBody().get(0), author);
    }

    @Test(description = "Test of search author with Bad Request")
    private void testSearchAuthorBadRequest(){
        baseResponse = authorService.searchAuthor(PropertiesReader.getProperty("EMPTY"));
        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_PHRASE_CANT_BE_BLANK"));
    }

}
