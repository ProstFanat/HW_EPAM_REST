package book;

import entity.Book;
import entity.ListOptions;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.BookService;
import service.VerifyService;
import utils.PropertiesReader;

public class GetAllBooksTest {
    private final BookService bookService = new BookService();
    private final VerifyService verifyService = new VerifyService();

    @Test(description = "Test of get all books")
    private void testGetAllBooks(){
        BaseResponse<Book> baseResponse = bookService.getBooks(new ListOptions().setPagination(false));
        verifyService.verifyStatusCodeOk(baseResponse);
    }

    @Test(description = "Test of get all books with wrong parameters")
    private void testGetBooksWithBadPageNumber(){
        BaseResponse<Book> baseResponse = bookService.getBooks(new ListOptions().setPage(-1));
        verifyService.verifyRequestValueMustBePositive(baseResponse, PropertiesReader.getProperty("VALUE_PAGE"));
    }

    @Test(description = "Test of get all books with wrong parameters")
    private void testGetBooksWithBadPaginationValue(){
        BaseResponse<Book> baseResponse = bookService.getBooks(new ListOptions().setSize(-1));
        verifyService.verifyRequestValueMustBePositive(baseResponse, PropertiesReader.getProperty("VALUE_SIZE"));
    }
}
