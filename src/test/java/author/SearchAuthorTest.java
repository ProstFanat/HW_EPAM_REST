package author;

import entity.Author;
import methods.AuthorMethods;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.VerifyService;
import utils.PropertiesReader;

public class SearchAuthorTest {

    private final AuthorService authorService = new AuthorService();
    private final VerifyService verifyService = new VerifyService();
    private Author author;
    private BaseResponse<Author> baseResponse;

    @BeforeMethod
    public void setup(){
        author = AuthorMethods.generateAuthor();
        baseResponse = authorService.createAuthor(author);
        verifyService.verifyCreatedSuccess(baseResponse, author);
    }

    @Test(description = "Test of search author by first name")
    private void testSearchAuthorByFirstName(){
        baseResponse = authorService.searchAuthor(author.getAuthorName().getFirst());
        verifyService.verifyResponseBodyEqualsExpectedFromList(baseResponse, author);
    }

    @Test(description = "Test of search author by last name")
    private void testSearchAuthorByLastName(){
        baseResponse = authorService.searchAuthor(author.getAuthorName().getSecond());
        verifyService.verifyResponseBodyEqualsExpectedFromList(baseResponse, author);
    }

    @Test(description = "Test of search author with Bad Request")
    private void testSearchAuthorBadRequest(){
        baseResponse = authorService.searchAuthor(PropertiesReader.getProperty("EMPTY"));
        verifyService.verifyMessagePhaseCantBeBlankExist(baseResponse);
    }

}
