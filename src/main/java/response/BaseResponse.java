package response;

import io.qameta.allure.Attachment;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import java.util.List;

public class BaseResponse<T> {
    protected Response response;
    private Class<T> responseClass;
    private static final Logger LOG = Logger.getLogger(BaseResponse.class);

    public BaseResponse(Response response, Class<T> responseClass) {
        this.response = response;
        this.responseClass = responseClass;
    }

    @Attachment
    public int getStatusCode() {
        int code = this.response.getStatusCode();
        LOG.info(String.format("Get status code - %s", code));
        return code;
    }

    @Attachment
    public String getErrorMessage(){
        String message = this.response.getBody().path("errorMessage");
        LOG.info(String.format("Get error message - %s", message));
        return message;
    }

    @Attachment
    public String getHeader(String header) {
        String headerResult = this.response.getHeader(header);
        LOG.info(String.format("Get header - %s", headerResult));
        return headerResult;
    }

    @Attachment
    public T getBody() {
        LOG.info(this.response.getBody().prettyPrint());
        T body = this.response.body().as(this.responseClass);
        return body;
    }

    @Attachment
    public List<T> getListOfBody(){
        List <T> bodys  = response.jsonPath().getList("$", this.responseClass);
        LOG.info(String.format("Getting list.  List -> %s", bodys));
        return bodys;
    }
}
