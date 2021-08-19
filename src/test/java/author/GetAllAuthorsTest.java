package author;

import entity.Author;
import entity.ListOptions;
import methods.AuthorMethods;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.VerifyService;
import utils.PropertiesReader;

public class GetAllAuthorsTest {
    private final AuthorService authorService = new AuthorService();
    private final VerifyService verifyService = new VerifyService();

    @Test(description = "Test of get all authors")
    private void testGetAllAuthors(){
        Author author = AuthorMethods.generateAuthor();

        BaseResponse<Author> baseResponse = authorService.createAuthor(author);
        verifyService.verifyCreatedSuccess(baseResponse, author);

        baseResponse = authorService.getAuthors(new ListOptions().setPagination(false));
        verifyService.verifyStatusCodeOk(baseResponse);

        baseResponse = authorService.deleteAuthor(author.getAuthorId());
        verifyService.verifyDeleteSuccess(baseResponse);
    }

    @Test(description = "Test of get all authors with wrong parameters")
    private void testGetAuthorsWithBadPageNumber(){
        BaseResponse<Author> baseResponse = authorService.getAuthors(new ListOptions().setPage(-1));
        verifyService.verifyRequestValueMustBePositive(baseResponse, PropertiesReader.getProperty("VALUE_PAGE"));


    }

    @Test(description = "Test of get all authors with wrong parameters")
    private void testGetAuthorsWithBadPaginationValue(){
        BaseResponse<Author> baseResponse = authorService.getAuthors(new ListOptions().setSize(-1));
        verifyService.verifyRequestValueMustBePositive(baseResponse, PropertiesReader.getProperty("VALUE_SIZE"));
    }
}
