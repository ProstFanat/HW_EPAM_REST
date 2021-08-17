package book;

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

public class CreateBookTest {
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
    }


    @Test(description = "Test of positive creating book")
    private void testPositiveScenario(){
        Book book = BookMethods.generateBook();
        Genre genre = GenreMethods.generateGenre();
        Author author = AuthorMethods.generateAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());

        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);
        Assert.assertEquals(baseResponseBook.getBody(), book);
    }

    @Test(description = "Test of book with such id already exists")
    private void testCreateAuthorWithExistsId(){
        baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);

        baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 409);
        Assert.assertEquals(baseResponseBook.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_BOOK_ALREADY_EXIST"));
    }

    @Test(description = "Test of book without body")
    private void testCreateAuthorWithoutBody(){
        baseResponseBook = bookService.createBook(null, genre.getGenreId(), author.getAuthorId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 400);
        Assert.assertEquals(baseResponseBook.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_BODY_IS_MISSING"));
    }
}
