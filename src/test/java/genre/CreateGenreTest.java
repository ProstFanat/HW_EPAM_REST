package genre;

import entity.Genre.Genre;
import methods.GenreMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;
import utils.PropertiesReader;

public class CreateGenreTest {
    private final GenreService genreService = new GenreService();

    @Test(description = "Test of positive creating genre")
    private void testPositiveScenario(){
        Genre genre = GenreMethods.createNewGenre();

        BaseResponse<Genre> baseResponse = genreService.createGenre(genre);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);
        Assert.assertTrue(baseResponse.getBody().equals(genre));
    }

    @Test(description = "Test of Genre with such id already exists")
    private void testCreateAuthorWithExistsId(){
        Genre genre = GenreMethods.createNewGenre();

        BaseResponse<Genre> baseResponse = genreService.createGenre(genre);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);

        baseResponse = genreService.createGenre(genre);
        Assert.assertEquals(baseResponse.getStatusCode(), 409);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_GENRE_ALREADY_EXIST"));
    }

    @Test(description = "Test of Genre without body")
    private void testCreateAuthorWithoutBody(){
        BaseResponse<Genre> baseResponse = genreService.createGenre(null);
        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_BODY_IS_MISSING"));
    }
}
