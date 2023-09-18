package com.apiframework.service;

import com.apiframework.model.Pet;
import com.apiframework.model.User;
import com.apiframework.utils.httpclients.RestUtil;
import com.apiframework.utils.reportMgmt.ExtentTestManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.apiframework.utils.constants.APIEndPoints.*;

public class PetService {
    private final Logger _logger = LogManager.getLogger(PetService.class);
    private Object responsePayload;

    public static PetService init() {
        return new PetService();
    }

    public PetService getPetByStatus(Pet.PetStatus status) {
        ExtentTestManager.step(_logger, "Get Pet list by status");
        RestUtil restInstance = RestUtil.init().path(PET_GET).pathParam("status", status.toString()).get();
//        responsePayload = restInstance.responseToPojo(new TypeReference<List<Pet>>() {});
        responsePayload = restInstance.response();
        return this;
    }

    public PetService addPet(Pet pet) {
        ExtentTestManager.step(_logger, "Add Pet");
        RestUtil restInstance = RestUtil.init().path(PET_POST).contentType(ContentType.JSON).body(pet).post();
        responsePayload = restInstance.response();
        return this;
    }

    public PetService modifyPet(Pet pet) {
        ExtentTestManager.step(_logger, "Modify Pet");
        RestUtil restInstance = RestUtil.init().path(PET_PUT).contentType(ContentType.JSON).body(pet).put();
        responsePayload = restInstance.response();
        return this;
    }

    public Object getResponse() {
        return responsePayload;
    }
}
