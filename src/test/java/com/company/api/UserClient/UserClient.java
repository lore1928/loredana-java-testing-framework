package com.company.api.UserClient;

import com.company.api.BaseTest.RequestSpecFactory;
import com.company.api.Utils.PollingUtils;
import io.restassured.response.Response;

import java.time.Duration;

import static io.restassured.RestAssured.given;

public class UserClient {

    public static Response getUserClient(Integer id) {
         String xApiKey = System.getProperty("xApiKey");

        Response response = given().log().all()
                .spec(RequestSpecFactory.getSpec())
                .header("x-api-key", xApiKey)
                .when()
                .get("/user/" + id);
        System.out.println(response.body().prettyPrint());

        return response;
    }

    public static Response getUserClient(Integer id, String xApiKey) {
        Response response = given().log().all()
                .spec(RequestSpecFactory.getSpec())
                .header("x-api-key", xApiKey)
                .when()
                .get("/user/" + id);
        System.out.println(response.body().prettyPrint());
        return response;
    }

    public static Response getUserClient(Integer userId, Duration timeOutDuration, Duration poolingDuration) throws InterruptedException {
        return PollingUtils.pollUntil(() -> UserClient.getUserClient(userId), timeOutDuration, poolingDuration, r -> r.getStatusCode() == 200);
    }
}
