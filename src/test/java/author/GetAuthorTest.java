package author;

import entity.Author;
import methods.AuthorMethods;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.VerifyService;
import utils.PropertiesReader;

public class GetAuthorTest {

    private final AuthorService authorService = new AuthorService();
    private final VerifyService verifyService = new VerifyService();

    @Test(description = "Test of getting author by id")
    private void testGetAuthorById(){
        Author author = AuthorMethods.generateAuthor();

        BaseResponse<Author> baseResponse = authorService.createAuthor(author);
        verifyService.verifyCreatedSuccess(baseResponse, author);

        baseResponse = authorService.getAuthor(author.getAuthorId());
        verifyService.verifyResponseBodyEqualsExpected(baseResponse, author);
    }

    @Test(description = "Test of getting author that not found")
    private void testGetAuthorNotFound(){
        BaseResponse<Author> baseResponse = authorService.getAuthor(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));
        verifyService.verifyEntityIsNotExist(baseResponse, PropertiesReader.getProperty("ENTITY_AUTHOR"));
    }

    @Test(description = "Test of getting author BAD REQUEST")
    private void testGetAuthorBadRequest(){
        BaseResponse<Object> baseResponse = authorService.getAuthorBadRequest("BAD REQUEST");
        verifyService.verifyRequestIDIsInvalid(baseResponse, PropertiesReader.getProperty("ENTITY_AUTHOR"));
    }
}
