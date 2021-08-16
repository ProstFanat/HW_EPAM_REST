package book;

import entity.Author.Author;
import entity.Genre.Genre;
import entity.ListOptions;
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

public class GetAllBookOfSpecialGenreTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();

    @Test(description = "Test of get all books")
    private void testGetAllBooks(){
        BaseResponse<Author> baseResponseAuthor;
        BaseResponse<Book> baseResponseBook;
        Genre genre = GenreMethods.createNewGenre();
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);;

        for(int i = 0; i < 5 ; i++) {
            Book book = BookMethods.createNewBook();
            Author author = AuthorMethods.createNewAuthor();

            baseResponseAuthor = authorService.createAuthor(author);
            baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
            Assert.assertEquals(baseResponseBook.getStatusCode(), 201);
        }
        baseResponseBook = bookService.getBooksByGenre(new ListOptions().setPagination(false), genre.getGenreId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 200);
        Assert.assertEquals(baseResponseBook.getListOfBody().size(), 5);
    }

    @Test(description = "Test of get all books with wrong parameters")
    private void testGetBooksWithBadPageNumber(){
        Genre genre = GenreMethods.createNewGenre();
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);

        BaseResponse<Book> baseResponse = bookService.getBooksByGenre(new ListOptions().setPage(-1), genre.getGenreId());

        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_VALUE_MUST_BE_POSITIVE"), "page"));
    }

    @Test(description = "Test of get all books with wrong parameters")
    private void testGetBooksWithBadPaginationValue(){
        Genre genre = GenreMethods.createNewGenre();
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);

        BaseResponse<Book> baseResponse = bookService.getBooksByGenre(new ListOptions().setSize(-1), genre.getGenreId());

        Assert.assertEquals(baseResponse.getStatusCode(), 400);
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_VALUE_MUST_BE_POSITIVE"), "size"));
    }
}
