package com.apiframework.service;

import com.apiframework.model.User;
import com.apiframework.utils.httpclients.RestUtil;
import com.apiframework.utils.reportMgmt.ExtentTestManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.apiframework.utils.constants.APIEndPoints.*;

public class UserService {
    private final Logger _logger = LogManager.getLogger(UserService.class);
    private final int httpStatus = HttpStatus.SC_OK;
    private final ContentType responseContentType = ContentType.JSON;
    private User requestPayload;
    private Object responsePayload;

    public static UserService init() {
        return new UserService();
    }

    public UserService getUserByID(String userid) {
        ExtentTestManager.step(_logger, "Get User By ID");
        RestUtil restInstance = RestUtil.init().path(USER_GET).pathParam("userid", userid).get();
//      responsePayload = restInstance.responseToPojo(new TypeReference<List<User>>() {});
        responsePayload = restInstance.response();
        return this;
    }

    public UserService addUsers(List<User> userList) {
        ExtentTestManager.step(_logger, "Add User Profile");
        RestUtil restInstance = RestUtil.init().path(USER_POST).contentType(ContentType.JSON).body(userList).post();
        responsePayload = restInstance.response();
        return this;
    }

    public UserService modifyUser(User user) {
        ExtentTestManager.step(_logger, "Modify User Profile");
        RestUtil restInstance = RestUtil.init().path(USER_PUT).pathParam("username", user.getUserName()).contentType(ContentType.JSON).body(user).put();
        responsePayload = restInstance.response();
        return this;
    }

    public Object getResponse() {
        return responsePayload;
    }
}
