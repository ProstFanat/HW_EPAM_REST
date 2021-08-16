package genre;

import entity.Genre.Genre;
import methods.GenreMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;

public class SearchGenreTest {
    private final GenreService genreService = new GenreService();

    @Test(description = "Test of search genre by name")
    private void testSearchGenreByName(){
        Genre genre = GenreMethods.createNewGenre();

        BaseResponse<Genre> baseResponse = genreService.createGenre(genre);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);

        baseResponse = genreService.searchGenre(genre.getGenreName());
        Assert.assertTrue(baseResponse.getListOfBody().get(0).equals(genre));
    }

    @Test(description = "Test of search genre with Bad Request")
    private void testSearchGenreBadRequest(){
        Genre genre = GenreMethods.createNewGenre();

        BaseResponse<Genre> baseResponse = genreService.createGenre(genre);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);

        baseResponse = genreService.searchGenre(null);
        Assert.assertEquals(baseResponse.getStatusCode(), 400);
    }
}
