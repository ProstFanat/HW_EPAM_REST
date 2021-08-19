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

public class GetGenreByAuthorTest {

    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();
    private final VerifyService verifyService = new VerifyService();
    private Author author;
    private Genre genre;
    private BaseResponse<Genre> baseResponseGenre;

    @BeforeMethod
    public void setup(){
        Book book = BookMethods.generateBook();
        genre = GenreMethods.generateGenre();
        author = AuthorMethods.generateAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        verifyService.verifyCreatedSuccess(baseResponseBook, book);
    }

    @Test(description = "Test of search genre by author")
    private void testSearchGenreByAuthor(){
        baseResponseGenre = genreService.searchGenreByAuthorId(String.valueOf(author.getAuthorId()));
        verifyService.verifyResponseBodyEqualsExpectedFromList(baseResponseGenre, genre);
    }

    @Test(description = "Test of search genre by author Bad Request")
    private void testSearchGenreByAuthorBadRequest(){
        baseResponseGenre = genreService.searchGenreByAuthorId("test");
        verifyService.verifyRequestIDIsInvalid(baseResponseGenre, PropertiesReader.getProperty("ENTITY_AUTHOR"));
    }

    @Test(description = "Test of search Genre by author that not exist")
    private void testSearchGenreByAuthorThatNotExist(){
        baseResponseGenre = genreService.searchGenreByAuthorId(PropertiesReader.getProperty("NOT_FOUND_ID"));
        verifyService.verifyEntityIsNotExist(baseResponseGenre, PropertiesReader.getProperty("ENTITY_AUTHOR"));
    }
}
