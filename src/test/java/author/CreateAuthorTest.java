package author;

import entity.Author;
import methods.AuthorMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
public class CreateAuthorTest {

    private final AuthorService authorService = new AuthorService();


    @Test(description = "Test of positive creating author")
    private void testPositiveScenario(){
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Object> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(201, baseResponse.getStatusCode());
    }

    @Test(description = "Test of Author with such id already exists")
    private void testCreateUserWithExistsId(){
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Object> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(201, baseResponse.getStatusCode());

        baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(409, baseResponse.getStatusCode());
    }

    @Test(description = "Test of Author without body")
    private void testCreateUserWithoutBody(){
        Author author = new Author();
        int id = (int) (Math.random() * 100000);

        BaseResponse<Object> baseResponse = authorService.createAuthor(author);
        Assert.assertEquals(400, baseResponse.getStatusCode());
    }
}
