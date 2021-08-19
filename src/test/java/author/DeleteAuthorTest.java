package author;

import entity.Author;
import methods.AuthorMethods;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.VerifyService;
import utils.PropertiesReader;

public class DeleteAuthorTest {
    private final AuthorService authorService = new AuthorService();
    private final VerifyService verifyService = new VerifyService();

    @Test(description = "Test of deleting author by id")
    private void testDeleteAuthorById(){
        Author author = AuthorMethods.generateAuthor();

        BaseResponse<Author> baseResponse = authorService.createAuthor(author);
        verifyService.verifyCreatedSuccess(baseResponse, author);

        baseResponse = authorService.deleteAuthor(author.getAuthorId());
        verifyService.verifyDeleteSuccess(baseResponse);
    }

    @Test(description = "Test of deleting author that not found")
    private void testDeleteAuthorNotFound(){
        BaseResponse<Author> baseResponse = authorService.deleteAuthor(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));
        verifyService.verifyEntityIsNotExist(baseResponse, PropertiesReader.getProperty("ENTITY_AUTHOR"));
    }

    @Test(description = "Test of deleting author BAD REQUEST")
    private void testDeleteAuthorBadRequest(){
        BaseResponse<Object> baseResponse = authorService.deleteAuthorBadRequest("BAD REQUEST");
        verifyService.verifyRequestIDIsInvalid(baseResponse, PropertiesReader.getProperty("ENTITY_AUTHOR"));
    }
}
