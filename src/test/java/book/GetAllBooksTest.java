package book;

import entity.book.Book;
import entity.ListOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.BookService;
import utils.PropertiesReader;

public class GetAllBooksTest {
    private final BookService bookService = new BookService();

    @Test(description = "Test of get all books")
    private void testGetAllBooks(){
        BaseResponse<Book> baseResponse = bookService.getBooks(new ListOptions().setPagination(false));
        //TODO u know
        Assert.assertEquals(baseResponse.getStatusCode(), 200);
    }

    @Test(description = "Test of get all books with wrong parameters")
    private void testGetBooksWithBadPageNumber(){
        BaseResponse<Book> baseResponse = bookService.getBooks(new ListOptions().setPage(-1));

        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_VALUE_MUST_BE_POSITIVE"), "page"));
    }

    @Test(description = "Test of get all books with wrong parameters")
    private void testGetBooksWithBadPaginationValue(){
        BaseResponse<Book> baseResponse = bookService.getBooks(new ListOptions().setSize(-1));

        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_VALUE_MUST_BE_POSITIVE"), "size"));
    }
}
