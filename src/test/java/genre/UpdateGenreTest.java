package genre;


import entity.Genre.Genre;
import methods.GenreMethods;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;
import utils.PropertiesReader;

public class UpdateGenreTest {
    private final GenreService genreService = new GenreService();
    private Genre genre;
    private BaseResponse<Genre> baseResponse;

    @BeforeMethod
    public void setup(){
        genre = GenreMethods.generateGenre();
        baseResponse = genreService.createGenre(genre);
        Assert.assertEquals(baseResponse.getStatusCode(), 201);
    }

    @Test(description = "Test of positive update genre")
    private void testPositiveUpdateGenre(){
        genre.setGenreDescription(PropertiesReader.getProperty("GENRE_ANOTHER_DESCRIPTION"));
        baseResponse = genreService.updateGenre(genre);
        Assert.assertEquals(baseResponse.getStatusCode(), 200);
        Assert.assertEquals(baseResponse.getBody(), genre);
    }

    @Test(description = "Test of update genre that not found")
    private void testUpdateGenreThatNotFound(){
        genre.setGenreId(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));

        baseResponse = genreService.updateGenre(genre);
        Assert.assertEquals(baseResponse.getStatusCode(), 404);
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_GENRE_NOT_EXIST"),
                        PropertiesReader.getProperty("NOT_FOUND_ID")));
    }

    @Test(description = "Test of Update genre without body")
    private void testUpdateGenreWithoutBody(){
        genre = null;

        baseResponse = genreService.updateGenre(genre);
        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_BODY_IS_MISSING"));
    }
}
