package genre;

import entity.Genre;
import methods.GenreMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;
import utils.PropertiesReader;

public class DeleteGenreTest {
    private final GenreService genreService = new GenreService();

    @Test(description = "Test of deleting genre by id")
    private void testDeleteGenreById(){
        Genre genre = GenreMethods.generateGenre();

        BaseResponse<Genre> baseResponse = genreService.createGenre(genre);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);

        baseResponse = genreService.deleteGenre(genre.getGenreId());
        Assert.assertEquals(baseResponse.getStatusCode(), 204);
    }

    @Test(description = "Test of deleting genre that not found")
    private void testDeleteGenreNotFound(){
        BaseResponse<Genre> baseResponse = genreService.deleteGenre(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));
        Assert.assertEquals(baseResponse.getStatusCode(), 404);
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_GENRE_NOT_EXIST"),
                        PropertiesReader.getProperty("NOT_FOUND_ID")));
    }

    @Test(description = "Test of deleting genre BAD REQUEST")
    private void testDeleteGenreBadRequest(){
        BaseResponse<Object> baseResponse = genreService.deleteGenreBadRequest("BAD REQUEST");
        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_GENRE_ID_MUST_BE_LONG"));
    }
}
