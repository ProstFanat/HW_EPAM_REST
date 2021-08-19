package genre;

import entity.Genre;
import methods.GenreMethods;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;
import service.VerifyService;
import utils.PropertiesReader;

public class GetGenreTest {

    private final GenreService genreService = new GenreService();
    private final VerifyService verifyService = new VerifyService();

    @Test(description = "Test of getting genre by id")
    private void testGetGenreById(){
        Genre genre = GenreMethods.generateGenre();

        BaseResponse<Genre> baseResponse = genreService.createGenre(genre);
        verifyService.verifyCreatedSuccess(baseResponse, genre);

        baseResponse = genreService.getGenre(genre.getGenreId());
        verifyService.verifyResponseBodyEqualsExpected(baseResponse, genre);
    }

    @Test(description = "Test of getting genre that not found")
    private void testGetGenreNotFound(){
        BaseResponse<Genre> baseResponse = genreService.getGenre(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));
        verifyService.verifyEntityIsNotExist(baseResponse, PropertiesReader.getProperty("ENTITY_GENRE"));
    }

    @Test(description = "Test of getting genre BAD REQUEST")
    private void testGetGenreBadRequest(){
        BaseResponse<Object> baseResponse = genreService.getGenreBadRequest("BAD REQUEST");
        verifyService.verifyRequestIDIsInvalid(baseResponse, PropertiesReader.getProperty("ENTITY_GENRE"));
    }
}
