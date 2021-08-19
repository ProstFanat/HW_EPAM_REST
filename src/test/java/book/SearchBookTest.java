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

public class SearchBookTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();
    private final VerifyService verifyService = new VerifyService();
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
        verifyService.verifyCreatedSuccess(baseResponseBook, book);
    }

    @Test(description = "Test of search book by name")
    private void testSearchBookByName(){
        baseResponseBook = bookService.searchBook(book.getBookName());
        verifyService.verifyResponseBodyEqualsExpectedFromList(baseResponseBook, book);
    }

    @Test(description = "Test of search book returning 5 most relevant results")
    private void testSearchBookReturn5Results(){
        BaseResponse<Author> baseResponseAuthor;
        BaseResponse<Genre> baseResponseGenre;
        BaseResponse<Book> baseResponseBook;

        for(int i = 0; i <= 6; i++) {
            Book book = BookMethods.generateBook();
            Genre genre = GenreMethods.generateGenre();
            Author author = AuthorMethods.generateAuthor();

            baseResponseAuthor = authorService.createAuthor(author);
            baseResponseGenre = genreService.createGenre(genre);
            baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());

            verifyService.verifyCreatedSuccess(baseResponseBook, book);
        }

        baseResponseBook = bookService.searchBook(PropertiesReader.getProperty("BOOK_NAME"));
        verifyService.verifyThatResponseBodySizeEquals(baseResponseBook, 5);
    }

    @Test(description = "Test of search book with Bad Request")
    private void testSearchBookBadRequest(){
        baseResponseBook = bookService.searchBook(null);
        verifyService.verifyThatStatusCodeBadRequest(baseResponseBook);
    }
}
