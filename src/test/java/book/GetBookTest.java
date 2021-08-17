package book;

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

public class GetBookTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();

    @Test(description = "Test of getting book by id")
    private void testGetBookById(){
        Book book = BookMethods.generateBook();
        Genre genre = GenreMethods.generateGenre();
        Author author = AuthorMethods.generateAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());

        baseResponseBook = bookService.getBook(book.getBookId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 200);
        Assert.assertEquals(baseResponseBook.getBody(), book);
    }

    @Test(description = "Test of getting book that not found")
    private void testGetBookNotFound(){
        BaseResponse<Book> baseResponse = bookService.getBook(Integer.parseInt(PropertiesReader.getProperty("NOT_FOUND_ID")));
        Assert.assertEquals(baseResponse.getStatusCode(), 404);
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_BOOK_NOT_EXIST"),
                        PropertiesReader.getProperty("NOT_FOUND_ID")));
    }

    @Test(description = "Test of getting book BAD REQUEST")
    private void testGetBookBadRequest(){
        BaseResponse<Object> baseResponse = bookService.getBookBadRequest("BAD REQUEST");
        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_BOOK_ID_MUST_BE_LONG"));
    }
}
