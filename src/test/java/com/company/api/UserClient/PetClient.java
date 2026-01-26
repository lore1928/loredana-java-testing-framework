package com.company.api.UserClient;

import com.company.api.BaseTest.RequestSpecFactory;
import com.company.api.Models.PetRequest;
import com.company.api.Utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public  class PetClient {

    public static Response createPet(PetRequest petObj) throws JsonProcessingException {

        String json = Utils.getStringJsonResponse(petObj);
        Response response =  given().log().all()
                .spec(RequestSpecFactory.getSpec())
                .body(json)
                .when()
                .post("/pet");
        System.out.println(response.body().prettyPrint());
        return response;
    }
}
