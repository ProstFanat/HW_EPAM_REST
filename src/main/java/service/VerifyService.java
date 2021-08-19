package service;

import io.qameta.allure.Step;
import org.testng.Assert;
import response.BaseResponse;
import utils.PropertiesReader;
import static org.apache.http.HttpStatus.*;

public class VerifyService {

    @Step("Verify that created Success - {object}")
    public void verifyCreatedSuccess(BaseResponse baseResponse, Object object){
        Assert.assertEquals(baseResponse.getStatusCode(), SC_CREATED, "Status code is invalid");
        Assert.assertEquals(baseResponse.getBody(), object, "RESPONSE BODY is not equals of expected");
    }

    @Step("Verify that updated Success - {object}")
    public void verifyUpdatedSuccess(BaseResponse baseResponse, Object object){
        Assert.assertEquals(baseResponse.getStatusCode(), SC_OK, "Status code is invalid");
        Assert.assertEquals(baseResponse.getBody(), object, "RESPONSE BODY is not equals of expected");
    }

    @Step("Verify that delete Success")
    public void verifyDeleteSuccess(BaseResponse baseResponse){
        Assert.assertEquals(baseResponse.getStatusCode(), SC_NO_CONTENT, "Status code is invalid");
    }

    @Step("Verify that Status code OK")
    public void verifyStatusCodeOk(BaseResponse baseResponse){
        Assert.assertEquals(baseResponse.getStatusCode(), SC_OK, "Status code is invalid");
    }

    @Step("Verify that Response body equals expected - {object}")
    public void verifyResponseBodyEqualsExpected(BaseResponse baseResponse, Object object){
        Assert.assertEquals(baseResponse.getStatusCode(), SC_OK, "Status code is invalid");
        Assert.assertEquals(baseResponse.getBody(), object);
    }

    @Step("Verify that Response body equals expected - {object}")
    public void verifyResponseBodyEqualsExpectedFromList(BaseResponse baseResponse, Object object){
        Assert.assertEquals(baseResponse.getStatusCode(), SC_OK, "Status code is invalid");
        Assert.assertEquals(baseResponse.getListOfBody().get(0), object);
    }

    @Step("Verify that entity already exist - {entity}")
    public void verifyEntityAlreadyExist(BaseResponse baseResponse, String entity){
        Assert.assertEquals(baseResponse.getStatusCode(), SC_CONFLICT, "Status code is invalid");
        Assert.assertEquals(baseResponse.getErrorMessage(),
                PropertiesReader.getProperty(String.format("ERROR_MESSAGE_%s_ALREADY_EXIST", entity)),
                "ERROR MESSAGE is not equals of expected");
    }

    @Step("Verify that body was missing in request")
    public void verifyRequestWithoutBody(BaseResponse baseResponse){
        Assert.assertEquals(baseResponse.getStatusCode(), SC_BAD_REQUEST, "Status code is invalid");
        Assert.assertEquals(baseResponse.getErrorMessage(),
                PropertiesReader.getProperty("ERROR_MESSAGE_BODY_IS_MISSING"),
                "ERROR MESSAGE is not equals of expected");
    }

    @Step("Verify that ID is invalid - {entity}")
    public void verifyRequestIDIsInvalid(BaseResponse baseResponse, String entity){
        Assert.assertEquals(baseResponse.getStatusCode(), SC_BAD_REQUEST,"Status code is invalid");
        Assert.assertEquals(baseResponse.getErrorMessage(),
                PropertiesReader.getProperty(String.format("ERROR_MESSAGE_%s_ID_MUST_BE_LONG", entity)),
                "ERROR MESSAGE is not equals of expected");
    }

    @Step("Verify that request value must be positive - {value}")
    public void verifyRequestValueMustBePositive(BaseResponse baseResponse, String value){
        Assert.assertEquals(baseResponse.getStatusCode(), SC_BAD_REQUEST, "Status code is invalid");
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty("ERROR_MESSAGE_VALUE_MUST_BE_POSITIVE"), value),
                "ERROR MESSAGE is not equals of expected");
    }

    @Step("Verify that status code is equals 400")
    public void verifyThatStatusCodeBadRequest(BaseResponse baseResponse){
        Assert.assertEquals(baseResponse.getStatusCode(), SC_BAD_REQUEST, "Status code is invalid");
    }

    @Step("Verify that entity is not exist - {entity}")
    public void verifyEntityIsNotExist(BaseResponse baseResponse, String entity){
        Assert.assertEquals(baseResponse.getStatusCode(), SC_NOT_FOUND, "Status code is invalid");
        Assert.assertEquals(baseResponse.getErrorMessage(),
                String.format(PropertiesReader.getProperty(String.format("ERROR_MESSAGE_%s_NOT_EXIST", entity)),
                        PropertiesReader.getProperty("NOT_FOUND_ID")),
                "ERROR MESSAGE is not equals of expected");
    }

    @Step("Verify that Message 'Phrase cant be blank is exist'")
    public void verifyMessagePhaseCantBeBlankExist(BaseResponse baseResponse){
        Assert.assertEquals(baseResponse.getStatusCode(), SC_BAD_REQUEST, "Status code is invalid");
        Assert.assertEquals(baseResponse.getErrorMessage(), PropertiesReader.getProperty("ERROR_MESSAGE_PHRASE_CANT_BE_BLANK"));
    }

    @Step("Verify That Response body size equals {size}")
    public void verifyThatResponseBodySizeEquals(BaseResponse baseResponse, int size){
        Assert.assertEquals(baseResponse.getStatusCode(), SC_OK, "Status code is invalid");
        Assert.assertEquals(baseResponse.getListOfBody().size(), size,"Size not equals of expected");
    }
}
