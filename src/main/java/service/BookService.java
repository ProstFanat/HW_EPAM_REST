package service;

import client.HttpClient;
import entity.Author.Author;
import entity.Genre.Genre;
import entity.book.Book;
import entity.ListOptions;
import io.qameta.allure.Step;
import response.BaseResponse;
import utils.EndpointBuilder;
import utils.JsonBuilder;
import utils.PropertiesReader;

import java.util.HashMap;

public class BookService {
    @Step("Get book with id {bookId}")
    public BaseResponse<Book> getBook(Integer bookId) {
        String endpoint = new EndpointBuilder().pathParameter("book").pathParameter(bookId).get();
        return new BaseResponse<>(HttpClient.get(endpoint, null), Book.class);
    }

    @Step("Get books with options {options}")
    public BaseResponse<Book> getBooks(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("books");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        return new BaseResponse<>(HttpClient.get(endpoint.get(), null), Book.class);
    }

    @Step("Get books by genreId with options {options}")
    public BaseResponse<Book> getBooksByGenre(ListOptions options, int genreId) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("genre").pathParameter(genreId).pathParameter("books");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        return new BaseResponse<>(HttpClient.get(endpoint.get(), null), Book.class);
    }

    @Step("Create book {book}")
    public BaseResponse<Book> createBook(Book book, int genreId, int authorId) {
        String endpoint = new EndpointBuilder().pathParameter("book").pathParameter(authorId).pathParameter(genreId).get();
        return new BaseResponse<>(HttpClient.post(endpoint, JsonBuilder.parseToJson(book),
                PropertiesReader.getProperty("CONTENT_TYPE_JSON")), Book.class);
    }

    @Step("Update book with {book}")
    public BaseResponse<Book> updateBook(Book book) {
        String endpoint = new EndpointBuilder().pathParameter("book").get();
        return new BaseResponse<>(HttpClient.put(endpoint, JsonBuilder.parseToJson(book),
                PropertiesReader.getProperty("CONTENT_TYPE_JSON")), Book.class);
    }

    @Step("Delete book by id {bookId}")
    public BaseResponse<Book> deleteBook(Integer bookId) {
        String endpoint = new EndpointBuilder().pathParameter("book").pathParameter(bookId).get();
        return new BaseResponse<>(HttpClient.delete(endpoint, null), Book.class);
    }

    @Step("Get book bad request with id {bookId}")
    public BaseResponse<Object> getBookBadRequest(String bookId) {
        String endpoint = new EndpointBuilder().pathParameter("book").pathParameter(bookId).get();
        return new BaseResponse<>(HttpClient.get(endpoint, null), Object.class);
    }

    @Step("Delete book with id {bookId}")
    public BaseResponse<Object> deleteBookBadRequest(String bookId) {
        String endpoint = new EndpointBuilder().pathParameter("book").pathParameter(bookId).get();
        return new BaseResponse<>(HttpClient.delete(endpoint,null), Object.class);
    }

    @Step("Search book with name {name}")
    public BaseResponse<Book> searchBook(String name) {
        String endpoint = new EndpointBuilder().pathParameter("books/search").queryParam("q", name).get();
        return new BaseResponse<>(HttpClient.get(endpoint,null), Book.class);
    }

    @Step("Search book by Author Id {authorId}")
    public BaseResponse<Book> searchBookByAuthorId(String authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).pathParameter("books").get();
        return new BaseResponse<>(HttpClient.get(endpoint,null), Book.class);
    }

    @Step("Search book by Author Id {authorId} and genre id {genreId}")
    public BaseResponse<Book> searchBookByAuthorIdAndGenreId(String authorId, String genreId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).pathParameter("genre").pathParameter(genreId).pathParameter("books").get();
        return new BaseResponse<>(HttpClient.get(endpoint,null), Book.class);
    }
}
