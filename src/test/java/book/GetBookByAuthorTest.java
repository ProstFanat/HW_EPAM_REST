package book;

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

public class GetBookByAuthorTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();
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
        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);
    }

    @Test(description = "Test of search book by author")
    private void testSearchBookByAuthor(){
        baseResponseBook = bookService.searchBookByAuthorId(String.valueOf(author.getAuthorId()));

        Assert.assertEquals(baseResponseBook.getListOfBody().get(0), book);
    }

    @Test(description = "Test of search book by author Bad Request")
    private void testSearchBookByAuthorBadRequest(){
        baseResponseBook = bookService.searchBookByAuthorId("test");
        Assert.assertEquals(baseResponseBook.getStatusCode(), 400);
        Assert.assertEquals(baseResponseBook.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_AUTHOR_ID_MUST_BE_LONG"));
    }

    @Test(description = "Test of search book by author that not exist")
    private void testSearchBookByAuthorThatNotExist(){
        baseResponseBook = bookService.searchBookByAuthorId(PropertiesReader.getProperty("NOT_FOUND_ID"));
        Assert.assertEquals(baseResponseBook.getStatusCode(), 404);
        Assert.assertEquals(baseResponseBook.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_AUTHOR_NOT_EXIST"),
                        PropertiesReader.getProperty("NOT_FOUND_ID")));
    }
}
