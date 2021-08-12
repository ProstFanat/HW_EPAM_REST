package client;

import com.google.gson.Gson;
import config.ServiceConfig;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.*;

public class HttpClient {
    private static final Logger LOG = Logger.getLogger(HttpClient.class);

    public static Response get(String endpoint) {
        return HttpClient.sendRequest(Method.GET, endpoint);
    }

    public static Response post(String endpoint, String body) {
        return HttpClient.sendRequest(Method.POST, endpoint, body);
    }

    public static Response put(String endpoint, String body) {
        return HttpClient.sendRequest(Method.PUT, endpoint, body);
    }

    public static Response delete(String endpoint) {
        return HttpClient.sendRequest(Method.DELETE, endpoint);
    }

    private static Response sendRequest(Method method, String endpoint) {
        return HttpClient.sendRequest(method, endpoint, null);
    }

    private static Response sendRequest(Method method, String endpoint, String body) {
        String url = ServiceConfig.HOST + endpoint;
        RequestSpecification spec = given();
        spec.contentType("application/json");
        if (body != null) spec.body(body);
        Response response = spec.request(method, url);
        LOG.info(String.format("Send %s request to %s with body : %s", method, endpoint, body));
        return response;
    }
}
