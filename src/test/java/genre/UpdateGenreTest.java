package genre;


import entity.Genre;
import methods.GenreMethods;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;
import service.VerifyService;
import utils.PropertiesReader;

public class UpdateGenreTest {
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

    @Test(description = "Test of positive update genre")
    private void testPositiveUpdateGenre(){
        genre.setGenreDescription(PropertiesReader.getProperty("GENRE_ANOTHER_DESCRIPTION"));
        baseResponse = genreService.updateGenre(genre);
        verifyService.verifyResponseBodyEqualsExpected(baseResponse, genre);
    }

    @Test(description = "Test of update genre that not found")
    private void testUpdateGenreThatNotFound(){
        genre.setGenreId(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));
        baseResponse = genreService.updateGenre(genre);
        verifyService.verifyEntityIsNotExist(baseResponse, PropertiesReader.getProperty("ENTITY_GENRE"));
    }

    @Test(description = "Test of Update genre without body")
    private void testUpdateGenreWithoutBody(){
        genre = null;
        baseResponse = genreService.updateGenre(genre);
        verifyService.verifyRequestWithoutBody(baseResponse);
    }
}
