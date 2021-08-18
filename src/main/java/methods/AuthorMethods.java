package methods;

import entity.Author;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import response.BaseResponse;
import service.AuthorService;
import utils.PropertiesReader;

public class AuthorMethods {
    private static final Logger LOG = Logger.getLogger(AuthorMethods.class);

    @Step("Generating author object ")
    public static Author generateAuthor(){
        boolean isRight = false;
        int id;
        do {
            id = (int) (Math.random() * 100000);
            LOG.info(String.format("Get id for author: '%s'", id));
            BaseResponse<Author> baseResponse = new AuthorService().getAuthor(id);
            if(id != Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")) && baseResponse.getStatusCode() == 404){
                LOG.info("ID is created successful!");
                isRight = true;
            }
        } while (!isRight);

        return new Author().setAuthorId(id)
                .setAuthorName(new Author.AuthorName(PropertiesReader.getProperty("FIRST_NAME") + id,
                        PropertiesReader.getProperty("LAST_NAME") + id))
                .setNationality(PropertiesReader.getProperty("NATIONALITY") + id)
                .setBirth(new Author.Birth(PropertiesReader.getProperty("BIRTH_DATE"),
                        PropertiesReader.getProperty("BIRTH_COUNTRY"),
                        PropertiesReader.getProperty("BIRTH_CITY")))
                .setAuthorDescription(PropertiesReader.getProperty("AUTHOR_DESCRIPTION"));
    }
}
