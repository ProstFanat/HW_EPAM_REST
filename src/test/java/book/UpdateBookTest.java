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

public class UpdateBookTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();
    private Book book;
    private BaseResponse<Book> baseResponseBook;

    @BeforeMethod
    public void setup(){
        book = BookMethods.generateBook();
        Genre genre = GenreMethods.generateGenre();
        Author author = AuthorMethods.generateAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);
    }

    @Test(description = "Test of positive update book")
    private void testPositiveUpdateBook(){
        book.setBookDescription(PropertiesReader.getProperty("BOOK_ANOTHER_DESCRIPTION"));
        baseResponseBook = bookService.updateBook(book);
        Assert.assertEquals(baseResponseBook.getStatusCode(), 200);
        Assert.assertEquals(baseResponseBook.getBody(), book);
    }

    @Test(description = "Test of update book that not found")
    private void testUpdateBookThatNotFound(){
        book.setBookId(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));

        baseResponseBook = bookService.updateBook(book);
        Assert.assertEquals(baseResponseBook.getStatusCode(), 404);
        Assert.assertEquals(baseResponseBook.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_BOOK_NOT_EXIST"),
                        PropertiesReader.getProperty("NOT_FOUND_ID")));
    }

    @Test(description = "Test of Update book without body")
    private void testUpdateBookWithoutBody(){
        book = null;

        baseResponseBook = bookService.updateBook(book);
        Assert.assertEquals(baseResponseBook.getStatusCode(), 400);
        Assert.assertEquals(baseResponseBook.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_BODY_IS_MISSING"));
    }
}
