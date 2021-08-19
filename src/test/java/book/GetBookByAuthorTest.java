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

public class GetBookByAuthorTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();
    private final VerifyService verifyService = new VerifyService();
    private Author author;
    private Book book;
    private BaseResponse<Book> baseResponseBook;

    @BeforeMethod
    public void setup(){
        book = BookMethods.generateBook();
        Genre genre = GenreMethods.generateGenre();
        author = AuthorMethods.generateAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        verifyService.verifyCreatedSuccess(baseResponseBook, book);
    }

    @Test(description = "Test of search book by author")
    private void testSearchBookByAuthor(){
        baseResponseBook = bookService.searchBookByAuthorId(String.valueOf(author.getAuthorId()));
        verifyService.verifyResponseBodyEqualsExpectedFromList(baseResponseBook, book);
    }

    @Test(description = "Test of search book by author Bad Request")
    private void testSearchBookByAuthorBadRequest(){
        baseResponseBook = bookService.searchBookByAuthorId(PropertiesReader.getProperty("INVALID_ID"));
        verifyService.verifyRequestIDIsInvalid(baseResponseBook, PropertiesReader.getProperty("ENTITY_AUTHOR"));
    }

    @Test(description = "Test of search book by author that not exist")
    private void testSearchBookByAuthorThatNotExist(){
        baseResponseBook = bookService.searchBookByAuthorId(PropertiesReader.getProperty("NOT_FOUND_ID"));
        verifyService.verifyEntityIsNotExist(baseResponseBook, PropertiesReader.getProperty("ENTITY_AUTHOR"));
    }
}
