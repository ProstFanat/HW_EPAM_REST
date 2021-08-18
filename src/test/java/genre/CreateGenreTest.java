package genre;

import entity.Genre;
import methods.GenreMethods;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;
import utils.PropertiesReader;

public class CreateGenreTest {
    private final GenreService genreService = new GenreService();
    private Genre genre;
    private BaseResponse<Genre> baseResponse;

    @BeforeMethod
    public void setup(){
        genre = GenreMethods.generateGenre();
        baseResponse = genreService.createGenre(genre);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);
    }

    @Test(description = "Test of positive creating genre")
    private void testPositiveScenario(){
        Assert.assertEquals(baseResponse.getBody(), genre);
    }

    @Test(description = "Test of Genre with such id already exists")
    private void testCreateAuthorWithExistsId(){
        baseResponse = genreService.createGenre(genre);
        Assert.assertEquals(baseResponse.getStatusCode(), 409);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_GENRE_ALREADY_EXIST"));
    }

    @Test(description = "Test of Genre without body")
    private void testCreateAuthorWithoutBody(){
        baseResponse = genreService.createGenre(null);
        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_BODY_IS_MISSING"));
    }
}
