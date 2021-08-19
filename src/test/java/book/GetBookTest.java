package book;

import entity.Author;
import entity.Genre;
import entity.Book;
import methods.AuthorMethods;
import methods.BookMethods;
import methods.GenreMethods;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.BookService;
import service.GenreService;
import service.VerifyService;
import utils.PropertiesReader;

public class GetBookTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();
    private final VerifyService verifyService = new VerifyService();

    @Test(description = "Test of getting book by id")
    private void testGetBookById(){
        Book book = BookMethods.generateBook();
        Genre genre = GenreMethods.generateGenre();
        Author author = AuthorMethods.generateAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());

        baseResponseBook = bookService.getBook(book.getBookId());
        verifyService.verifyResponseBodyEqualsExpected(baseResponseBook, book);
    }

    @Test(description = "Test of getting book that not found")
    private void testGetBookNotFound(){
        BaseResponse<Book> baseResponse = bookService.getBook(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));
        verifyService.verifyEntityIsNotExist(baseResponse, PropertiesReader.getProperty("ENTITY_BOOK"));
    }

    @Test(description = "Test of getting book BAD REQUEST")
    private void testGetBookBadRequest(){
        BaseResponse<Object> baseResponse = bookService.getBookBadRequest("BAD REQUEST");
        verifyService.verifyRequestIDIsInvalid(baseResponse, PropertiesReader.getProperty("ENTITY_BOOK"));
    }
}
