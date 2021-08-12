package service;

import client.HttpClient;
import response.BaseResponse;
import utils.EndpointBuilder;
import utils.JsonBuilder;

public class AuthorService {
    public BaseResponse<Object> getAuthor(Integer authorId) {
        String endpoint = new EndpointBuilder().pathParameter("author").pathParameter(authorId).get();
        return new BaseResponse<>(HttpClient.get(endpoint), Object.class);
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
}
