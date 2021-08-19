package author;

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

public class GetAuthorByGenreTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();
    private final VerifyService verifyService = new VerifyService();

    private Author author;
    private Genre genre;
    private BaseResponse<Author> baseResponseAuthor;

    @BeforeMethod
    public void setup(){
        Book book = BookMethods.generateBook();
        genre = GenreMethods.generateGenre();
        author = AuthorMethods.generateAuthor();

        baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        verifyService.verifyCreatedSuccess(baseResponseBook, book);

    }

    @Test(description = "Test of search author by genre")
    private void testSearchAuthorByGenre(){
        baseResponseAuthor = authorService.searchAuthorByGenreId(String.valueOf(genre.getGenreId()));
        verifyService.verifyResponseBodyEqualsExpectedFromList(baseResponseAuthor, author);
    }

    @Test(description = "Test of search author by genre Bad Request")
    private void testSearchAuthorByGenreBadRequest(){
        baseResponseAuthor = authorService.searchAuthorByGenreId("test");
        verifyService.verifyRequestIDIsInvalid(baseResponseAuthor, PropertiesReader.getProperty("ENTITY_GENRE"));
    }

    @Test(description = "Test of search author by genre that not exist")
    private void testSearchAuthorByGenreThatNotExist(){
        baseResponseAuthor = authorService.searchAuthorByGenreId(PropertiesReader.getProperty("NOT_FOUND_ID"));
        verifyService.verifyEntityIsNotExist(baseResponseAuthor, PropertiesReader.getProperty("ENTITY_GENRE"));
    }

}
