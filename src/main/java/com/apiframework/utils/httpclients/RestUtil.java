package com.apiframework.utils.httpclients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.util.Map;

import static com.apiframework.utils.constants.APIEndPoints.BASE_URL;
import static io.restassured.RestAssured.given;

public class RestUtil {
    private RequestSpecBuilder requestSpecBuilder;
    private RequestSpecification requestSpecification;
    private Response apiResponse;

    public RestUtil() {
        initializeRequestSpec();
    }

    public static RestUtil init() {
        return new RestUtil();
    }

    private void initializeRequestSpec() {
        EncoderConfig encoderconfig = new EncoderConfig();
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(BASE_URL);
        requestSpecBuilder.setConfig(RestAssured.config().encoderConfig(encoderconfig.appendDefaultContentCharsetToContentTypeIfUndefined(false)));
    }

    public RestUtil path(String path) {
        requestSpecBuilder.setBasePath(path);
        return this;
    }

    public RestUtil pathParam(String key, String value) {
        requestSpecBuilder.addPathParam(key, value);
        return this;
    }

    public RestUtil queryParam(String key, String value) {
        requestSpecBuilder.addQueryParam(key, value);
        return this;
    }

    public RestUtil contentType(ContentType contentType) {
        requestSpecBuilder.setContentType(contentType);
        return this;
    }

    public RestUtil headers(Map<String, String> headers) {
        requestSpecBuilder.addHeaders(headers);
        return this;
    }

    public RestUtil cookies(Map<String, String> cookies) {
        requestSpecBuilder.addCookies(cookies);
        return this;
    }

    public RestUtil body(Object body) {
        requestSpecBuilder.setBody(body);
        return this;
    }

    public RestUtil put() {
        requestSpecification = requestSpecBuilder.build();
        apiResponse =
                given()
                        .log().all()
                        .spec(requestSpecification)
                        .when()
                        .put()
                        .then()
                        .extract()
                        .response();

        return this;
    }

    public RestUtil post() {
        requestSpecification = requestSpecBuilder.build();
        apiResponse =
                given()
                        .log().all()
                        .spec(requestSpecification)
                        .when()
                        .post()
                        .then()
                        .extract()
                        .response();

        return this;
    }

    public RestUtil get() {
        requestSpecification = requestSpecBuilder.build();
        apiResponse =
                given()
                        .log().all()
                        .spec(requestSpecification)
                        .when()
                        .get()
                        .then()
                        .extract()
                        .response();
        return this;
    }

    public Response response() {
        return apiResponse;
    }

    public <T> T responseToPojo(Class<T> type) throws JsonProcessingException {
        return new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY).readValue(apiResponse.toString(), type);
    }

    public <T> T responseToPojo(TypeReference type) throws JsonProcessingException {
        return (T) new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY).readValue(apiResponse.toString(), type);
    }
}
