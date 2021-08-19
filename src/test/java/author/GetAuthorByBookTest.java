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

public class GetAuthorByBookTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();
    private final VerifyService verifyService = new VerifyService();

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
        verifyService.verifyCreatedSuccess(baseResponseBook, book);
    }


    @Test(description = "Test of search author by book")
    private void testSearchAuthorByBook(){
        baseResponseAuthor = authorService.searchAuthorByBookId(String.valueOf(book.getBookId()));
        verifyService.verifyResponseBodyEqualsExpected(baseResponseAuthor, author);
    }

    @Test(description = "Test of search author by book Bad Request")
    private void testSearchAuthorByBookBadRequest(){
        baseResponseAuthor = authorService.searchAuthorByBookId(PropertiesReader.getProperty("INVALID_ID"));
        verifyService.verifyRequestIDIsInvalid(baseResponseAuthor, PropertiesReader.getProperty("ENTITY_BOOK"));
    }

    @Test(description = "Test of search author by book that not exist")
    private void testSearchAuthorByBookThatNotExist(){
        baseResponseAuthor = authorService.searchAuthorByBookId(PropertiesReader.getProperty("NOT_FOUND_ID"));
        verifyService.verifyEntityIsNotExist(baseResponseAuthor, PropertiesReader.getProperty("ENTITY_BOOK"));
    }

}
