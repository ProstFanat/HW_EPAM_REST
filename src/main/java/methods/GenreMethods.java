package methods;

import entity.Genre.Genre;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import response.BaseResponse;
import service.GenreService;
import utils.PropertiesReader;

public class GenreMethods {
    private static final Logger LOG = Logger.getLogger(AuthorMethods.class);

    @Step("Generating author object")
    public static Genre generateGenre(){
        boolean isRight = false;
        int id;
        do {
            id = (int) (Math.random() * 100000);
            LOG.info(String.format("Get id for genre: '%s'", id));
            BaseResponse<Genre> baseResponse = new GenreService().getGenre(id);
            if(id != Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")) && baseResponse.getStatusCode() == 404){
                LOG.info("ID is created successful!");
                isRight = true;
            }
        } while (!isRight);

        return new Genre().setGenreId(id)
                .setGenreName(PropertiesReader.getProperty("GENRE_NAME") + id)
                .setGenreDescription(PropertiesReader.getProperty("GENRE_DESCRIPTION"));
    }
}
