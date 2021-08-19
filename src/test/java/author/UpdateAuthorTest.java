package author;

import entity.Author;
import methods.AuthorMethods;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.VerifyService;
import utils.PropertiesReader;

public class UpdateAuthorTest {
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

    @Test(description = "Test of positive update author")
    private void testPositiveUpdateAuthor(){
        author.setNationality(PropertiesReader.getProperty("ANOTHER_NATIONALITY"));
        baseResponse = authorService.updateAuthor(author);;
        verifyService.verifyUpdatedSuccess(baseResponse, author);
    }

    @Test(description = "Test of update author that not found")
    private void testUpdateUserThatNotFound(){
        author.setAuthorId(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));

        baseResponse = authorService.updateAuthor(author);
        verifyService.verifyEntityIsNotExist(baseResponse, PropertiesReader.getProperty("ENTITY_AUTHOR"));
    }

    @Test(description = "Test of Update Author without body")
    private void testUpdateAuthorWithoutBody(){
        author = null;
        baseResponse = authorService.updateAuthor(author);
        verifyService.verifyRequestWithoutBody(baseResponse);
    }
}
