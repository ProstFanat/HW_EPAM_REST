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

public class GetBookByAuthorAndGenreTest {
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
        baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        verifyService.verifyCreatedSuccess(baseResponseBook, book);
    }

    @Test(description = "Test of search book by author and genre")
    private void testSearchBookByAuthorAndGenre(){
        baseResponseBook = bookService.searchBookByAuthorIdAndGenreId(String.valueOf(author.getAuthorId()), String.valueOf(genre.getGenreId()));
        verifyService.verifyResponseBodyEqualsExpectedFromList(baseResponseBook, book);
    }

    @Test(description = "Test of search author by book Bad Request and Genre")
    private void testSearchBookByAuthorBadRequestAndGenre(){
        baseResponseBook = bookService.searchBookByAuthorIdAndGenreId("test", String.valueOf(genre.getGenreId()));
        verifyService.verifyRequestIDIsInvalid(baseResponseBook, PropertiesReader.getProperty("ENTITY_AUTHOR"));
    }

    @Test(description = "Test of search author by genre Bad Request and Author")
    private void testSearchBookByGenreBadRequestAndAuthor(){
        baseResponseBook = bookService.searchBookByAuthorIdAndGenreId(String.valueOf(author.getAuthorId()), "test");
        verifyService.verifyRequestIDIsInvalid(baseResponseBook, PropertiesReader.getProperty("ENTITY_GENRE"));
    }

    @Test(description = "Test of search book by author that not exist and genre")
    private void testSearchBookByAuthorThatNotExistAndGenre(){
        baseResponseBook = bookService.searchBookByAuthorIdAndGenreId(PropertiesReader.getProperty("NOT_FOUND_ID"), String.valueOf(genre.getGenreId()));
        verifyService.verifyEntityIsNotExist(baseResponseBook, PropertiesReader.getProperty("ENTITY_AUTHOR"));
    }

    @Test(description = "Test of search book by genre that not exist and author")
    private void testSearchBookByGenreThatNotExistAndAuthor(){
        baseResponseBook = bookService.searchBookByAuthorIdAndGenreId(String.valueOf(author.getAuthorId()), PropertiesReader.getProperty("NOT_FOUND_ID"));
        verifyService.verifyEntityIsNotExist(baseResponseBook, PropertiesReader.getProperty("ENTITY_GENRE"));
    }
}
