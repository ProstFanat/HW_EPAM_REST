package book;

import entity.Author;
import entity.Genre;
import entity.Book;
import methods.AuthorMethods;
import methods.BookMethods;
import methods.GenreMethods;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.BookService;
import service.GenreService;
import service.VerifyService;
import utils.PropertiesReader;

public class UpdateBookTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();
    private final VerifyService verifyService = new VerifyService();
    private Book book;
    private BaseResponse<Book> baseResponseBook;

    @BeforeMethod
    public void setup(){
        book = BookMethods.generateBook();
        Genre genre = GenreMethods.generateGenre();
        Author author = AuthorMethods.generateAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        verifyService.verifyCreatedSuccess(baseResponseBook, book);
    }

    @Test(description = "Test of positive update book")
    private void testPositiveUpdateBook(){
        book.setBookDescription(PropertiesReader.getProperty("BOOK_ANOTHER_DESCRIPTION"));
        baseResponseBook = bookService.updateBook(book);
        verifyService.verifyUpdatedSuccess(baseResponseBook, book);
    }

    @Test(description = "Test of update book that not found")
    private void testUpdateBookThatNotFound(){
        book.setBookId(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));
        baseResponseBook = bookService.updateBook(book);
        verifyService.verifyEntityIsNotExist(baseResponseBook, PropertiesReader.getProperty("ENTITY_BOOK"));
    }

    @Test(description = "Test of Update book without body")
    private void testUpdateBookWithoutBody(){
        book = null;
        baseResponseBook = bookService.updateBook(book);
        verifyService.verifyRequestWithoutBody(baseResponseBook);
    }
}
