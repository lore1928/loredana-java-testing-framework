package com.company.api.test;

import com.company.api.baseTest.BaseTest;
import com.company.api.baseTest.RequestSpecFactory;
import com.company.api.listeners.TestListener;
import com.company.api.models.PetCategoryRequest;
import com.company.api.models.PetRequest;
import com.company.api.models.PetTagRequest;
import com.company.api.client.PetClient;
import com.company.api.utils.ConfigReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;


@Listeners({ TestListener.class})
public class PostPetTest extends BaseTest {
    @BeforeMethod
    public void setup() {
        RequestSpecFactory.initSpec(ConfigReader.get("petBaseUri"));
    }

    @AfterMethod
    public void tearDown() {
        RequestSpecFactory.removeSpec();
    }

    @Test(invocationCount = 2)
    public void postPet() throws JsonProcessingException, InterruptedException {
        Thread.sleep(2000);

        PetRequest myPet = PetRequest.builder()
                .id(1)
                .name("Max")
                .petCategoryRequest(PetCategoryRequest
                        .builder()
                        .id(1)
                        .name("Dog")
                        .build())
                .photoUrls(List.of("category string"))
                .petTagRequests(List.of(PetTagRequest
                        .builder()
                        .id(1).name("tag string")
                        .build()))
                .status("true")
                .build();

        Response response = PetClient.createPet(myPet);
                response.then()
                        .statusCode(200)
                        .body("id", not(emptyOrNullString()))
                        .body("name", equalTo("Max"))
                        .body("tags.name[0]", equalTo("tag string"));

        Allure.addAttachment("Response Body", "application/json", response.body().prettyPrint());
    }

}
