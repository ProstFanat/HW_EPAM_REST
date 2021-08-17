package genre;

import entity.Author.Author;
import entity.Genre.Genre;
import entity.book.Book;
import methods.AuthorMethods;
import methods.BookMethods;
import methods.GenreMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.BookService;
import service.GenreService;
import utils.PropertiesReader;

public class GetGenreByAuthorTest {

    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();

    @Test(description = "Test of search genre by author")
    private void testSearchGenreByAuthor(){
        Book book = BookMethods.createNewBook();
        Genre genre = GenreMethods.createNewGenre();
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);

        baseResponseGenre = genreService.searchGenreByAuthorId(String.valueOf(author.getAuthorId()));

        Assert.assertEquals(baseResponseGenre.getListOfBody().get(0), genre);
    }

    @Test(description = "Test of search genre by author Bad Request")
    private void testSearchGenreByAuthorBadRequest(){
        Book book = BookMethods.createNewBook();
        Genre genre = GenreMethods.createNewGenre();
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);

        baseResponseGenre = genreService.searchGenreByAuthorId("test");
        Assert.assertEquals(baseResponseGenre.getStatusCode(), 400);
        Assert.assertEquals(baseResponseGenre.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_AUTHOR_ID_MUST_BE_LONG"));
    }

    @Test(description = "Test of search Genre by author that not exist")
    private void testSearchGenreByAuthorThatNotExist(){
        Book book = BookMethods.createNewBook();
        Genre genre = GenreMethods.createNewGenre();
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);

        baseResponseGenre = genreService.searchGenreByAuthorId(PropertiesReader.getProperty("NOT_FOUND_ID"));
        Assert.assertEquals(baseResponseGenre.getStatusCode(), 404);
        Assert.assertEquals(baseResponseGenre.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_AUTHOR_NOT_EXIST"),
                        PropertiesReader.getProperty("NOT_FOUND_ID")));
    }
}
