package genre;

import entity.Genre;
import entity.ListOptions;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.GenreService;
import service.VerifyService;
import utils.PropertiesReader;

public class GetAllGenresTest {
    private final GenreService genreService = new GenreService();
    private final VerifyService verifyService = new VerifyService();

    @Test(description = "Test of get all genres")
    private void testGetAllGenres(){
        BaseResponse<Genre> baseResponse = genreService.getGenres(new ListOptions().setPagination(false));
        verifyService.verifyStatusCodeOk(baseResponse);
    }

    @Test(description = "Test of get all genres with wrong parameters")
    private void testGetGenresWithBadPageNumber(){
        BaseResponse<Genre> baseResponse = genreService.getGenres(new ListOptions().setPage(-1));
        verifyService.verifyRequestValueMustBePositive(baseResponse, PropertiesReader.getProperty("VALUE_PAGE"));
    }

    @Test(description = "Test of get all genres with wrong parameters")
    private void testGetGenresWithBadPaginationValue(){
        BaseResponse<Genre> baseResponse = genreService.getGenres(new ListOptions().setSize(-1));
        verifyService.verifyRequestValueMustBePositive(baseResponse, PropertiesReader.getProperty("VALUE_SIZE"));
    }
}
