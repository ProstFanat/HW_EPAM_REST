package service;

import client.HttpClient;
import entity.Author;
import entity.ListOptions;
import io.qameta.allure.Step;
import response.BaseResponse;
import utils.EndpointBuilder;
import utils.JsonBuilder;
import utils.PropertiesReader;

public class AuthorService {
    @Step("Get Author with id {authorId}")
    public BaseResponse<Author> getAuthor(Integer authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).get();
        return new BaseResponse<>(HttpClient.get(endpoint, null), Author.class);
    }

    @Step("Get Authors with options {options}")
    public BaseResponse<Author> getAuthors(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("authors");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        return new BaseResponse<>(HttpClient.get(endpoint.get(), null), Author.class);
    }

    @Step("Create Author {author}")
    public BaseResponse<Author> createAuthor(Author author) {
        String endpoint = new EndpointBuilder().pathParameter("author").get();
        return new BaseResponse<>(HttpClient.post(endpoint, JsonBuilder.parseToJson(author),
                PropertiesReader.getProperty("CONTENT_TYPE_JSON")), Author.class);
    }

    @Step("Update Author with {author}")
    public BaseResponse<Author> updateAuthor(Author author) {
        String endpoint = new EndpointBuilder().pathParameter("author").get();
        return new BaseResponse<>(HttpClient.put(endpoint, JsonBuilder.parseToJson(author),
                PropertiesReader.getProperty("CONTENT_TYPE_JSON")), Author.class);
    }

    @Step("Delete Author by id {authorId}")
    public BaseResponse<Author> deleteAuthor(Integer authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).get();
        return new BaseResponse<>(HttpClient.delete(endpoint, null), Author.class);
    }

    @Step("Get Author bad request with id {authorId}")
    public BaseResponse<Object> getAuthorBadRequest(String authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).get();
        return new BaseResponse<>(HttpClient.get(endpoint, null), Object.class);
    }

    @Step("Delete Author with id {authorId}")
    public BaseResponse<Object> deleteAuthorBadRequest(String authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).get();
        return new BaseResponse<>(HttpClient.delete(endpoint,null), Object.class);
    }
}
