package methods;

import entity.Author;
import entity.AuthorName;
import entity.Birth;
import org.apache.log4j.Logger;
import utils.PropertiesReader;

public class AuthorMethods {
    private static final Logger LOG = Logger.getLogger(AuthorMethods.class);

    public static Author createNewAuthor(){
        boolean isRight = false;
        int id;
        do {
            id = (int) (Math.random() * 100000);
            LOG.info(String.format("Get id for author: '%s'", id));
            if(id != Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID"))){
                isRight = true;
            }
        } while (!isRight);

        return new Author().setAuthorId(id)
                .setAuthorName(new AuthorName(PropertiesReader.getProperty("FIRST_NAME"),
                        PropertiesReader.getProperty("LAST_NAME")))
                .setNationality(PropertiesReader.getProperty("NATIONALITY"))
                .setBirth(new Birth(PropertiesReader.getProperty("BIRTH_DATE"),
                        PropertiesReader.getProperty("BIRTH_COUNTRY"),
                        PropertiesReader.getProperty("BIRTH_CITY")))
                .setAuthorDescription(PropertiesReader.getProperty("AUTHOR_DESCRIPTION"));
    }
}
