package genre;

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

public class GetGenreByBook {

    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();
    private final VerifyService verifyService = new VerifyService();
    private Book book;
    private Genre genre;
    private BaseResponse<Genre> baseResponseGenre;

    @BeforeMethod
    public void setup(){
        book = BookMethods.generateBook();
        genre = GenreMethods.generateGenre();
        Author author = AuthorMethods.generateAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        verifyService.verifyCreatedSuccess(baseResponseBook, book);
    }

    @Test(description = "Test of search genre by book")
    private void testSearchGenreByBook(){
        baseResponseGenre = genreService.searchGenreByBookId(String.valueOf(book.getBookId()));
        verifyService.verifyResponseBodyEqualsExpected(baseResponseGenre, genre);
    }

    @Test(description = "Test of search genre by book Bad Request")
    private void testSearchGenreByBookBadRequest(){
        baseResponseGenre = genreService.searchGenreByBookId("test");
        verifyService.verifyRequestIDIsInvalid(baseResponseGenre, PropertiesReader.getProperty("ENTITY_BOOK"));
    }

    @Test(description = "Test of search Genre by book that not exist")
    private void testSearchGenreByBookThatNotExist(){
        baseResponseGenre = genreService.searchGenreByBookId(PropertiesReader.getProperty("NOT_FOUND_ID"));
        verifyService.verifyEntityIsNotExist(baseResponseGenre, PropertiesReader.getProperty("ENTITY_BOOK"));
    }
}
