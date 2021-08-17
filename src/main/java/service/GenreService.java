package service;

import client.HttpClient;
import entity.Genre.Genre;
import entity.ListOptions;
import entity.book.Book;
import io.qameta.allure.Step;
import response.BaseResponse;
import utils.EndpointBuilder;
import utils.JsonBuilder;
import utils.PropertiesReader;

import java.util.HashMap;

public class GenreService {

    @Step("Get Genre with id {genreId}")
    public BaseResponse<Genre> getGenre(Integer genreId) {
        String endpoint = new EndpointBuilder().pathParameter("genre").pathParameter(genreId).get();
        return new BaseResponse<>(HttpClient.get(endpoint, null), Genre.class);
    }

    @Step("Get Genres with options {options}")
    public BaseResponse<Genre> getGenres(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("genres");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        return new BaseResponse<>(HttpClient.get(endpoint.get(), null, "ass"), Genre.class);
    }

    @Step("Create Genre {genre}")
    public BaseResponse<Genre> createGenre(Genre genre) {
        String endpoint = new EndpointBuilder().pathParameter("genre").get();
        return new BaseResponse<>(HttpClient.post(endpoint, JsonBuilder.parseToJson(genre),
                PropertiesReader.getProperty("CONTENT_TYPE_JSON")), Genre.class);
    }

    @Step("Update Genre with {genre}")
    public BaseResponse<Genre> updateGenre(Genre genre) {
        String endpoint = new EndpointBuilder().pathParameter("genre").get();
        return new BaseResponse<>(HttpClient.put(endpoint, JsonBuilder.parseToJson(genre),
                PropertiesReader.getProperty("CONTENT_TYPE_JSON")), Genre.class);
    }

    @Step("Delete Genre by id {genreId}")
    public BaseResponse<Genre> deleteGenre(Integer genreId) {
        String endpoint = new EndpointBuilder().pathParameter("genre").pathParameter(genreId).get();
        return new BaseResponse<>(HttpClient.delete(endpoint, null), Genre.class);
    }

    @Step("Get Genre bad request with id {genreId}")
    public BaseResponse<Object> getGenreBadRequest(String genreId) {
        String endpoint = new EndpointBuilder().pathParameter("genre").pathParameter(genreId).get();
        return new BaseResponse<>(HttpClient.get(endpoint, null), Object.class);
    }

    @Step("Delete Genre with id {genreId}")
    public BaseResponse<Object> deleteGenreBadRequest(String genreId) {
        String endpoint = new EndpointBuilder().pathParameter("genre").pathParameter(genreId).get();
        return new BaseResponse<>(HttpClient.delete(endpoint,null), Object.class);
    }

    @Step("Search Genre with name {name}")
    public BaseResponse<Genre> searchGenre(String name) {
        String endpoint = new EndpointBuilder().pathParameter("genres/search").queryParam("query", name).get();
        return new BaseResponse<>(HttpClient.get(endpoint,null), Genre.class);
    }

    @Step("Search Genre by Author Id {authorId}")
    public BaseResponse<Genre> searchGenreByAuthorId(String authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).pathParameter("genres").get();
        return new BaseResponse<>(HttpClient.get(endpoint,null), Genre.class);
    }

    @Step("Search Genre by Book Id {bookId}")
    public BaseResponse<Genre> searchGenreByBookId(String bookId) {
        String endpoint = new EndpointBuilder().pathParameter("book").pathParameter(bookId).pathParameter("genre").get();
        return new BaseResponse<>(HttpClient.get(endpoint,null), Genre.class);
    }
}
