package author;

import entity.Author;
import entity.ListOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import utils.PropertiesReader;

public class GetAllAuthorsTest {
    private final AuthorService authorService = new AuthorService();

    @Test(description = "Test of get all authors")
    private void testGetAllAuthors(){
        //TODO добавить проверку, что только что созданный юзер там отоброжаеться
        BaseResponse<Author> baseResponse = authorService.getAuthors(new ListOptions().setPagination(false));

        Assert.assertEquals(baseResponse.getStatusCode(), 200);
    }

    @Test(description = "Test of get all authors with wrong parameters")
    private void testGetAuthorsWithBadPageNumber(){
        BaseResponse<Author> baseResponse = authorService.getAuthors(new ListOptions().setPage(-1));

        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_VALUE_MUST_BE_POSITIVE"), "page"));
    }

    @Test(description = "Test of get all authors with wrong parameters")
    private void testGetAuthorsWithBadPaginationValue(){
        BaseResponse<Author> baseResponse = authorService.getAuthors(new ListOptions().setSize(-1));

        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_VALUE_MUST_BE_POSITIVE"), "size"));
    }
}
