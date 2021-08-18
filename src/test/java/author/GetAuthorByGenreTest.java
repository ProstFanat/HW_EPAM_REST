package author;

import entity.Author;
import entity.Genre;
import entity.Book;
import methods.AuthorMethods;
import methods.BookMethods;
import methods.GenreMethods;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.BookService;
import service.GenreService;
import utils.PropertiesReader;

public class GetAuthorByGenreTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();

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
        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);
    }

    @Test(description = "Test of search author by genre")
    private void testSearchAuthorByGenre(){
        baseResponseAuthor = authorService.searchAuthorByGenreId(String.valueOf(genre.getGenreId()));
        Assert.assertEquals(baseResponseAuthor.getListOfBody().get(0), author);
    }

    @Test(description = "Test of search author by genre Bad Request")
    private void testSearchAuthorByGenreBadRequest(){
        baseResponseAuthor = authorService.searchAuthorByGenreId("test");
        Assert.assertEquals(baseResponseAuthor.getStatusCode(), 400);
        Assert.assertEquals(baseResponseAuthor.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_GENRE_ID_MUST_BE_LONG"));
    }

    @Test(description = "Test of search author by genre that not exist")
    private void testSearchAuthorByGenreThatNotExist(){
        baseResponseAuthor = authorService.searchAuthorByGenreId(PropertiesReader.getProperty("NOT_FOUND_ID"));
        Assert.assertEquals(baseResponseAuthor.getStatusCode(), 404);
        Assert.assertEquals(baseResponseAuthor.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_GENRE_NOT_EXIST"),
                        PropertiesReader.getProperty("NOT_FOUND_ID")));
    }

}
