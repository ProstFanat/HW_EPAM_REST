package genre;

import entity.Genre.Genre;
import methods.GenreMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;
import utils.PropertiesReader;

public class GetGenreTest {

    private final GenreService genreService = new GenreService();

    @Test(description = "Test of getting genre by id")
    private void testGetGenreById(){
        Genre genre = GenreMethods.createNewGenre();

        BaseResponse<Genre> baseResponse = genreService.createGenre(genre);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);

        baseResponse = genreService.getGenre(genre.getGenreId());
        Assert.assertEquals(baseResponse.getStatusCode(), 200);
        Assert.assertEquals(baseResponse.getBody(), genre);
    }

    @Test(description = "Test of getting genre that not found")
    private void testGetGenreNotFound(){
        BaseResponse<Genre> baseResponse = genreService.getGenre(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));
        Assert.assertEquals(baseResponse.getStatusCode(), 404);
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_GENRE_NOT_EXIST"),
                        PropertiesReader.getProperty("NOT_FOUND_ID")));
    }

    @Test(description = "Test of getting genre BAD REQUEST")
    private void testGetGenreBadRequest(){
        BaseResponse<Object> baseResponse = genreService.getGenreBadRequest("BAD REQUEST");
        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_GENRE_ID_MUST_BE_LONG"));
    }
}
