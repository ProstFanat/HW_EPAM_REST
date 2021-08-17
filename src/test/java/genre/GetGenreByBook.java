package genre;

import entity.Author.Author;
import entity.Genre.Genre;
import entity.book.Book;
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

public class GetGenreByBook {

    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();
    private Author author;
    private Book book;
    private Genre genre;
    private BaseResponse<Author> baseResponseAuthor;
    private BaseResponse<Genre> baseResponseGenre;
    private BaseResponse<Book> baseResponseBook;

    @BeforeMethod
    public void setup(){
        book = BookMethods.generateBook();
        genre = GenreMethods.generateGenre();
        author = AuthorMethods.generateAuthor();

        baseResponseAuthor = authorService.createAuthor(author);
        baseResponseGenre = genreService.createGenre(genre);
        baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);
    }

    @Test(description = "Test of search genre by book")
    private void testSearchGenreByBook(){
        baseResponseGenre = genreService.searchGenreByBookId(String.valueOf(book.getBookId()));
        Assert.assertEquals(baseResponseGenre.getBody(), genre);
    }

    @Test(description = "Test of search genre by book Bad Request")
    private void testSearchGenreByBookBadRequest(){
        baseResponseGenre = genreService.searchGenreByBookId("test");
        Assert.assertEquals(baseResponseGenre.getStatusCode(), 400);
        Assert.assertEquals(baseResponseGenre.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_BOOK_ID_MUST_BE_LONG"));
    }

    @Test(description = "Test of search Genre by book that not exist")
    private void testSearchGenreByBookThatNotExist(){
        baseResponseGenre = genreService.searchGenreByBookId(PropertiesReader.getProperty("NOT_FOUND_ID"));
        Assert.assertEquals(baseResponseGenre.getStatusCode(), 404);
        Assert.assertEquals(baseResponseGenre.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_BOOK_NOT_EXIST"),
                        PropertiesReader.getProperty("NOT_FOUND_ID")));
    }
}
