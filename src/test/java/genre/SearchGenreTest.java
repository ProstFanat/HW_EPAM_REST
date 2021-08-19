package genre;

import entity.Genre;
import methods.GenreMethods;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;
import service.VerifyService;
import utils.PropertiesReader;

public class SearchGenreTest {
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

    @Test(description = "Test of search genre by name")
    private void testSearchGenreByName(){
        verifyService.verifyCreatedSuccess(baseResponse, genre);
    }

    @Test(description = "Test of search genre with Bad Request")
    private void testSearchGenreBadRequest(){
        baseResponse = genreService.searchGenre(PropertiesReader.getProperty("EMPTY"));
        verifyService.verifyMessagePhaseCantBeBlankExist(baseResponse);
    }
}
