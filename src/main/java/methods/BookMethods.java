package methods;

import entity.Book;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import response.BaseResponse;
import service.BookService;
import utils.PropertiesReader;

public class BookMethods {
    private static final Logger LOG = Logger.getLogger(BookMethods.class);

    @Step("Generating book object")
    public static Book generateBook(){
        boolean isRight = false;
        int id;
        do {
            id = (int) (Math.random() * 100000);
            LOG.info(String.format("Get id for book: '%s'", id));
            BaseResponse<Book> baseResponse = new BookService().getBook(id);
            if(id != Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")) && baseResponse.getStatusCode() == 404){
                LOG.info("ID is created successful!");
                isRight = true;
            }
        } while (!isRight);

        return new Book().setBookId(id)
                .setBookName(PropertiesReader.getProperty("BOOK_NAME") + id)
                .setBookLanguage(PropertiesReader.getProperty("BOOK_LANGUAGE"))
                .setBookDescription(PropertiesReader.getProperty("BOOK_DESCRIPTION"))
                .setAdditional(
                        new Book.Additional()
                                .setPageCount(Integer.parseInt(PropertiesReader.getProperty("BOOK_PAGE_COUNT")))
                        .setSize(
                        new Book.Size()
                                .setHeight(Integer.parseInt(PropertiesReader.getProperty("BOOK_HEIGHT")))
                                .setWidth(Integer.parseInt(PropertiesReader.getProperty("BOOK_WIDTH")))
                                .setLength(Integer.parseInt(PropertiesReader.getProperty("BOOK_LENGTH")))))
                .setPublicationYear(2001);
    }


}
