package author;

import entity.Author;
import entity.Genre;
import entity.Book;
import methods.AuthorMethods;
import methods.BookMethods;
import methods.GenreMethods;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.BookService;
import service.GenreService;
import utils.PropertiesReader;

public class GetAuthorByBookTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();

    private Author author;
    private Book book;
    private BaseResponse<Author> baseResponseAuthor;

    @BeforeMethod
    public void setup(){
        book = BookMethods.generateBook();
        Genre genre = GenreMethods.generateGenre();
        author = AuthorMethods.generateAuthor();

        baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);
    }


    @Test(description = "Test of search author by book")
    private void testSearchAuthorByBook(){
        baseResponseAuthor = authorService.searchAuthorByBookId(String.valueOf(book.getBookId()));
        Assert.assertEquals(baseResponseAuthor.getBody(), author);
    }

    @Test(description = "Test of search author by book Bad Request")
    private void testSearchAuthorByBookBadRequest(){
        baseResponseAuthor = authorService.searchAuthorByBookId("test");
        Assert.assertEquals(baseResponseAuthor.getStatusCode(), 400);
        Assert.assertEquals(baseResponseAuthor.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_BOOK_ID_MUST_BE_LONG"));
    }

    @Test(description = "Test of search author by book that not exist")
    private void testSearchAuthorByBookThatNotExist(){
        baseResponseAuthor = authorService.searchAuthorByBookId(PropertiesReader.getProperty("NOT_FOUND_ID"));
        Assert.assertEquals(baseResponseAuthor.getStatusCode(), 404);
        Assert.assertEquals(baseResponseAuthor.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_BOOK_NOT_EXIST"),
                        PropertiesReader.getProperty("NOT_FOUND_ID")));
    }

}
