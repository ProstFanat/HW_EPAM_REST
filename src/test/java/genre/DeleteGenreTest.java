package genre;

import entity.Genre;
import methods.GenreMethods;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;
import service.VerifyService;
import utils.PropertiesReader;

public class DeleteGenreTest {
    private final GenreService genreService = new GenreService();
    private final VerifyService verifyService = new VerifyService();

    @Test(description = "Test of deleting genre by id")
    private void testDeleteGenreById(){
        Genre genre = GenreMethods.generateGenre();

        BaseResponse<Genre> baseResponse = genreService.createGenre(genre);
        verifyService.verifyCreatedSuccess(baseResponse, genre);

        baseResponse = genreService.deleteGenre(genre.getGenreId());
        verifyService.verifyDeleteSuccess(baseResponse);
    }

    @Test(description = "Test of deleting genre that not found")
    private void testDeleteGenreNotFound(){
        BaseResponse<Genre> baseResponse = genreService.deleteGenre(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));
        verifyService.verifyEntityIsNotExist(baseResponse, PropertiesReader.getProperty("ENTITY_GENRE"));
    }

    @Test(description = "Test of deleting genre BAD REQUEST")
    private void testDeleteGenreBadRequest(){
        BaseResponse<Object> baseResponse = genreService.deleteGenreBadRequest("BAD REQUEST");
        verifyService.verifyRequestIDIsInvalid(baseResponse, PropertiesReader.getProperty("ENTITY_GENRE"));
    }
}
