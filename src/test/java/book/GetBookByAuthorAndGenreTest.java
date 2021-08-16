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

public class GetBookByAuthorAndGenreTest {
    private final BookService bookService = new BookService();
    private final GenreService genreService = new GenreService();
    private final AuthorService authorService = new AuthorService();

    @Test(description = "Test of search book by author and genre")
    private void testSearchBookByAuthorAndGenre(){
        Book book = BookMethods.createNewBook();
        Genre genre = GenreMethods.createNewGenre();
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);

        baseResponseBook = bookService.searchBookByAuthorIdAndGenreId(String.valueOf(author.getAuthorId()), String.valueOf(genre.getGenreId()));

        Assert.assertTrue(baseResponseBook.getListOfBody().get(0).equals(book));
    }

    @Test(description = "Test of search author by book Bad Request and Genre")
    private void testSearchBookByAuthorBadRequestAndGenre(){
        Book book = BookMethods.createNewBook();
        Genre genre = GenreMethods.createNewGenre();
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);

        baseResponseBook = bookService.searchBookByAuthorIdAndGenreId("test", String.valueOf(genre.getGenreId()));
        Assert.assertEquals(baseResponseBook.getStatusCode(), 400);
        Assert.assertEquals(baseResponseBook.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_AUTHOR_ID_MUST_BE_LONG"));
    }

    @Test(description = "Test of search author by genre Bad Request and Author")
    private void testSearchBookByGenreBadRequestAndAuthor(){
        Book book = BookMethods.createNewBook();
        Genre genre = GenreMethods.createNewGenre();
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);

        baseResponseBook = bookService.searchBookByAuthorIdAndGenreId(String.valueOf(author.getAuthorId()), "test");
        Assert.assertEquals(baseResponseBook.getStatusCode(), 400);
        Assert.assertEquals(baseResponseBook.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_GENRE_ID_MUST_BE_LONG"));
    }

    @Test(description = "Test of search book by author that not exist and genre")
    private void testSearchBookByAuthorThatNotExistAndGenre(){
        Book book = BookMethods.createNewBook();
        Genre genre = GenreMethods.createNewGenre();
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);

        baseResponseBook = bookService.searchBookByAuthorIdAndGenreId(PropertiesReader.getProperty("NOT_FOUND_ID"), String.valueOf(genre.getGenreId()));
        Assert.assertEquals(baseResponseBook.getStatusCode(), 404);
        Assert.assertEquals(baseResponseBook.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_AUTHOR_NOT_EXIST"),
                        PropertiesReader.getProperty("NOT_FOUND_ID")));
    }

    @Test(description = "Test of search book by genre that not exist and author")
    private void testSearchBookByGenreThatNotExistAndAuthor(){
        Book book = BookMethods.createNewBook();
        Genre genre = GenreMethods.createNewGenre();
        Author author = AuthorMethods.createNewAuthor();

        BaseResponse<Author> baseResponseAuthor = authorService.createAuthor(author);
        BaseResponse<Genre> baseResponseGenre = genreService.createGenre(genre);
        BaseResponse<Book> baseResponseBook = bookService.createBook(book, genre.getGenreId(), author.getAuthorId());
        Assert.assertEquals(baseResponseBook.getStatusCode(), 201);

        baseResponseBook = bookService.searchBookByAuthorIdAndGenreId(String.valueOf(author.getAuthorId()), PropertiesReader.getProperty("NOT_FOUND_ID"));
        Assert.assertEquals(baseResponseBook.getStatusCode(), 404);
        Assert.assertEquals(baseResponseBook.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_GENRE_NOT_EXIST"),
                        PropertiesReader.getProperty("NOT_FOUND_ID")));
    }
}
