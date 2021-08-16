package genre;

import entity.Genre.Genre;
import entity.ListOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;
import utils.PropertiesReader;

public class GetAllGenresTest {
    private final GenreService genreService = new GenreService();

    @Test(description = "Test of get all genres")
    private void testGetAllGenres(){
        BaseResponse<Genre> baseResponse = genreService.getGenres(new ListOptions().setPagination(false));
        //TODO u know
        Assert.assertEquals(baseResponse.getStatusCode(), 200);
    }

    @Test(description = "Test of get all genres with wrong parameters")
    private void testGetGenresWithBadPageNumber(){
        BaseResponse<Genre> baseResponse = genreService.getGenres(new ListOptions().setPage(-1));

        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_VALUE_MUST_BE_POSITIVE"), "page"));
    }

    @Test(description = "Test of get all genres with wrong parameters")
    private void testGetGenresWithBadPaginationValue(){
        BaseResponse<Genre> baseResponse = genreService.getGenres(new ListOptions().setSize(-1));

        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_VALUE_MUST_BE_POSITIVE"), "size"));
    }
}
