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

public class SearchBookTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();

    @Test(description = "Test of search book by name")
    private void testSearchBookByName(){
        Book book = BookMethods.createNewBook();
        Genre genre = GenreMethods.createNewGenre();
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());

        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);

        baseResponseBook = bookService.searchBook(book.getBookName());
        Assert.assertEquals(baseResponseBook.getListOfBody().get(0), book);
    }

    @Test(description = "Test of search book returning 5 most relevant results")
    private void testSearchBookReturn5Results(){
        BaseResponse<Author> baseResponseAuthor;
        BaseResponse<Genre> baseResponseGenre;
        BaseResponse<Book> baseResponseBook;

        for(int i = 0; i <= 6; i++) {
            Book book = BookMethods.createNewBook();
            Genre genre = GenreMethods.createNewGenre();
            Author author = AuthorMethods.createNewAuthor();

            baseResponseAuthor = authorService.createAuthor(author);
            baseResponseGenre = genreService.createGenre(genre);
            baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());

            Assert.assertEquals(baseResponseBook.getStatusCode(), 201);
        }

        baseResponseBook = bookService.searchBook(PropertiesReader.getProperty("BOOK_NAME"));
        Assert.assertEquals(baseResponseBook.getListOfBody().size(), 5);
    }

    @Test(description = "Test of search book with Bad Request")
    private void testSearchBookBadRequest(){
        Book book = BookMethods.createNewBook();
        Genre genre = GenreMethods.createNewGenre();
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);

        baseResponseBook = bookService.searchBook(null);
        Assert.assertEquals(baseResponseBook.getStatusCode(), 400);
    }
}
