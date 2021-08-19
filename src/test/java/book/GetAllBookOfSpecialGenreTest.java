package book;

import entity.Author;
import entity.Genre;
import entity.ListOptions;
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

public class GetAllBookOfSpecialGenreTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();
    private final VerifyService verifyService = new VerifyService();

    @Test(description = "Test of get all books")
    private void testGetAllBooks(){
        BaseResponse<Author> baseResponseAuthor;
        BaseResponse<Book> baseResponseBook;
        Genre genre = GenreMethods.generateGenre();
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);;

        for(int i = 0; i < 5 ; i++) {
            Book book = BookMethods.generateBook();
            Author author = AuthorMethods.generateAuthor();

            baseResponseAuthor = authorService.createAuthor(author);
            baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
            verifyService.verifyCreatedSuccess(baseResponseBook, book);
        }
        baseResponseBook = bookService.getBooksByGenre(new ListOptions().setPagination(false), genre.getGenreId());
        verifyService.verifyThatResponseBodySizeEquals(baseResponseBook, 5);
    }

    @Test(description = "Test of get all books with wrong parameters")
    private void testGetBooksWithBadPageNumber(){
        Genre genre = GenreMethods.generateGenre();
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponse = bookService.getBooksByGenre(new ListOptions().setPage(-1), genre.getGenreId());
        verifyService.verifyRequestValueMustBePositive(baseResponse, PropertiesReader.getProperty("VALUE_PAGE"));
    }

    @Test(description = "Test of get all books with wrong parameters")
    private void testGetBooksWithBadPaginationValue(){
        Genre genre = GenreMethods.generateGenre();
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponse = bookService.getBooksByGenre(new ListOptions().setSize(-1), genre.getGenreId());
        verifyService.verifyRequestValueMustBePositive(baseResponse, PropertiesReader.getProperty("VALUE_SIZE"));

    }
}
