package genre;

import entity.Genre;
import methods.GenreMethods;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;
import service.VerifyService;
import utils.PropertiesReader;

public class CreateGenreTest {
    private final GenreService genreService = new GenreService();
    private final VerifyService verifyService = new VerifyService();
    private Genre genre;
    private BaseResponse<Genre> baseResponse;

    @BeforeMethod
    public void setup(){
        genre = GenreMethods.generateGenre();
        baseResponse = genreService.createGenre(genre);
        verifyService.verifyCreatedSuccess(baseResponse, genre);
    }

    @Test(description = "Test of positive creating genre")
    private void testPositiveScenario(){
        verifyService.verifyCreatedSuccess(baseResponse, genre);
    }

    @Test(description = "Test of Genre with such id already exists")
    private void testCreateAuthorWithExistsId(){
        baseResponse = genreService.createGenre(genre);
        verifyService.verifyEntityAlreadyExist(baseResponse, PropertiesReader.getProperty("ENTITY_GENRE"));
    }

    @Test(description = "Test of Genre without body")
    private void testCreateAuthorWithoutBody(){
        baseResponse = genreService.createGenre(null);
        verifyService.verifyRequestWithoutBody(baseResponse);
    }
}
