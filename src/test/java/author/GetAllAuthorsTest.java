package author;

import entity.ListOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;

public class GetAllAuthorsTest {
    private final AuthorService authorService = new AuthorService();

    @Test(description = "Test of get all authors")
    private void testGetAllAuthors(){
        BaseResponse<Object> baseResponse = authorService.getAuthors(new ListOptions().setPagination(false));

        Assert.assertEquals(200, baseResponse.getStatusCode());
    }

    @Test(description = "Test of get all authors with wrong parameters")
    private void testGetAuthorsWithBadRequest(){
        BaseResponse<Object> baseResponse = authorService.getAuthors(new ListOptions().setPage(-1));

        Assert.assertEquals(400, baseResponse.getStatusCode());
    }
}
