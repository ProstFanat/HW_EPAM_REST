package client;

import config.ServiceConfig;
import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class HttpClient {
    private static final Logger LOG = Logger.getLogger(HttpClient.class);

    @Step("Get {endpoint}")
    public static Response get(String endpoint, String contentType) {
        return HttpClient.sendRequest(Method.GET, endpoint, contentType, null);
    }

    @Step("Get {endpoint} and queryParam - {queryParam}")
    public static Response get(String endpoint, String contentType, HashMap<String, String> queryParam) {
        return HttpClient.sendRequest(Method.GET, endpoint, contentType, queryParam);
    }

    @Step("Post {endpoint} with body - {body}")
    public static Response post(String endpoint, String body, String contentType) {
        return HttpClient.sendRequest(Method.POST, endpoint, body, contentType, null);
    }

    @Step("Put {endpoint} with body - {body}")
    public static Response put(String endpoint, String body, String contentType) {
        return HttpClient.sendRequest(Method.PUT, endpoint, body, contentType, null);
    }

    @Step("Delete {endpoint}")
    public static Response delete(String endpoint, String contentType) {
        return HttpClient.sendRequest(Method.DELETE, endpoint, contentType, null);
    }

    @Step("Send Request {method} to {endpoint}")
    private static Response sendRequest(Method method, String endpoint, String contentType, HashMap<String, String> queryParams) {
        return HttpClient.sendRequest(method, endpoint, null, contentType, queryParams);
    }

    @Step("Send Request {method} to {endpoint} with body - {body}")
    private static Response sendRequest(Method method, String endpoint, String body, String contentType, HashMap<String, String> queryParams) {
        String url = ServiceConfig.HOST + endpoint;
        RequestSpecification spec = given();
        if(contentType != null) spec.contentType(contentType);
        if(queryParams != null) spec.queryParams(queryParams);
        if(body != null) spec.body(body);
        Response response = spec.request(method, url);
        LOG.info(String.format("Send %s request to %s with body : %s", method, endpoint, body));
        return response;
    }
}
