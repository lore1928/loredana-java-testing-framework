package com.company.api.client;

import com.company.api.baseTest.RequestSpecFactory;
import com.company.api.models.PetRequest;
import com.company.api.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Allure;
import io.restassured.response.Response;

import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public  class PetClient {

    public static Response createPet(PetRequest petObj) throws JsonProcessingException {

        String json = Utils.getStringJsonResponse(petObj);
        Response response =  given()
                .spec(RequestSpecFactory.getSpec())
                .log().all()
                .body(json)
                .when()
                .post("/pet");

        Logger.getLogger(response.body().prettyPrint());
        Allure.addAttachment("Response Body", "application/json", response.body().prettyPrint());

        return response;
    }
}
