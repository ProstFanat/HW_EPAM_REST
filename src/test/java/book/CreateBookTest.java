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

public class CreateBookTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();
    private final VerifyService verifyService = new VerifyService();
    private Author author;
    private Book book;
    private Genre genre;
    private BaseResponse<Book> baseResponseBook;

    @BeforeMethod
    public void setup(){
        book = BookMethods.generateBook();
        genre = GenreMethods.generateGenre();
        author = AuthorMethods.generateAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
    }


    @Test(description = "Test of positive creating book")
    private void testPositiveScenario(){
        Book book = BookMethods.generateBook();
        Genre genre = GenreMethods.generateGenre();
        Author author = AuthorMethods.generateAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());

        verifyService.verifyCreatedSuccess(baseResponseBook, book);
    }

    @Test(description = "Test of book with such id already exists")
    private void testCreateAuthorWithExistsId(){
        baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        verifyService.verifyCreatedSuccess(baseResponseBook, book);

        baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
         verifyService.verifyEntityAlreadyExist(baseResponseBook, PropertiesReader.getProperty("ENTITY_BOOK"));
    }

    @Test(description = "Test of book without body")
    private void testCreateAuthorWithoutBody(){
        baseResponseBook = bookService.createBook(null, genre.getGenreId(), author.getAuthorId());
        verifyService.verifyRequestWithoutBody(baseResponseBook);
    }
}
