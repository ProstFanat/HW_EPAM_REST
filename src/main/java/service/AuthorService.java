package service;

import client.HttpClient;
import entity.ListOptions;
import response.BaseResponse;
import utils.EndpointBuilder;
import utils.JsonBuilder;

public class AuthorService {
    public BaseResponse<Object> getAuthor(Integer authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).get();
        return new BaseResponse<>(HttpClient.get(endpoint), Object.class);
    }

    public BaseResponse<Object> getAuthors(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter("authors");
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Object.class);
    }

    public BaseResponse<Object> createAuthor(Object author) {
        String endpoint = new EndpointBuilder().pathParameter("author").get();
        return new BaseResponse<>(HttpClient.post(endpoint, JsonBuilder.parseToJson(author)), Object.class);
    }

    public BaseResponse<Object> updateAuthor(Object author) {
        String endpoint = new EndpointBuilder().pathParameter("author").get();
        return new BaseResponse<>(HttpClient.put(endpoint, JsonBuilder.parseToJson(author)), Object.class);
    }

    public BaseResponse<Object> deleteAuthor(Integer authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).get();
        return new BaseResponse<>(HttpClient.delete(endpoint), Object.class);
    }

    public BaseResponse<Object> getAuthorBadRequest(String authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).get();
        return new BaseResponse<>(HttpClient.get(endpoint), Object.class);
    }

    public BaseResponse<Object> deleteAuthorBadRequest(String authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).get();
        return new BaseResponse<>(HttpClient.delete(endpoint), Object.class);
    }
}
