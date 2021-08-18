package genre;

import entity.Genre;
import methods.GenreMethods;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;
import utils.PropertiesReader;

public class SearchGenreTest {
    private final GenreService genreService = new GenreService();
    private Genre genre;
    private BaseResponse<Genre> baseResponse;

    @BeforeMethod
    public void setup(){
        genre = GenreMethods.generateGenre();
        baseResponse = genreService.createGenre(genre);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);
    }

    @Test(description = "Test of search genre by name")
    private void testSearchGenreByName(){
        Assert.assertEquals(baseResponse.getBody(), genre);
    }

    @Test(description = "Test of search genre with Bad Request")
    private void testSearchGenreBadRequest(){
        baseResponse = genreService.searchGenre(PropertiesReader.getProperty("EMPTY"));
        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_PHRASE_CANT_BE_BLANK"));
    }
}
