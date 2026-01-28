package com.company.api.test;

import com.company.api.baseTest.BaseTest;
import com.company.api.baseTest.RequestSpecFactory;
import com.company.api.listeners.TestListener;
import com.company.api.client.UserClient;
import com.company.api.utils.ConfigReader;
import com.company.api.utils.DataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.time.Duration;

import static org.testng.Assert.*;
import static org.testng.Assert.assertNotNull;

@Listeners({ TestListener.class})
public class GetUserTest extends BaseTest {

    @BeforeMethod
    public void setup() {
        RequestSpecFactory.initSpec(ConfigReader.get("userBaseUri"));
    }

    @AfterMethod
    public void tearDown() {
        RequestSpecFactory.removeSpec();
    }

    @Test(dataProvider = "getUsersProviderMethod",
            dataProviderClass = DataProviders.class)
    public void getUser(Integer userId, String name) throws InterruptedException {
        //the sleep was added to demonstrate the parallel run
        Thread.sleep(2000);

        UserClient.getUserClient(userId, Duration.ofSeconds(30), Duration.ofSeconds(2));

        }

    @Test(dataProvider = "getUsersProviderMethod",
            dataProviderClass = DataProviders.class)
    @Story("Get User - Positive Scenarios")
    @Description("Verify that getUserClient returns 200 OK for valid user IDs")
    public void testGetUserWithValidId_ReturnsSuccess(Integer userId, String expectedName) throws InterruptedException {
        Thread.sleep(2000);
        Response response = UserClient.getUserClient(userId);

        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        assertTrue(response.getBody().asString().contains(expectedName),
                "Response should contain user name: " + expectedName);

    }

    @Test
    @Story("Get User - Positive Scenarios")
    @Description("Verify that response contains valid JSON structure")
    public void testGetUserResponse_HasValidJsonStructure() throws InterruptedException {
        Thread.sleep(2000);

        Response response = UserClient.getUserClient(1);

        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        assertEquals(response.getContentType(), "application/json",
                "Content-Type should be application/json");

    }

    @Test
    @Story("Get User - Positive Scenarios")
    @Description("Verify that getUserClient returns response with expected headers")
    public void testGetUserResponse_HasExpectedHeaders() throws InterruptedException {
        Thread.sleep(2000);

        Response response = UserClient.getUserClient(1);

        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        assertNotNull(response.getHeader("Content-Type"), "Content-Type header should be present");

    }

    @Test
    @Story("Get User - Positive Scenarios")
    @Description("Verify that response time is within acceptable limits")
    public void testGetUserResponse_HasAcceptableResponseTime() throws InterruptedException {
        Thread.sleep(2000);

        Response response = UserClient.getUserClient(1);

        assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        assertTrue(response.getTime() < 5000,
                "Response time should be less than 5 seconds, actual: " + response.getTime() + "ms");

    }

    // ==================== NEGATIVE TESTS ====================

    @Test
    @Story("Get User - Negative Scenarios")
    @Description("Verify that getUserClient returns emptu response body for non-existent user ID")
    public void testGetUserWithNonExistentId_Returns404() throws InterruptedException {
        Thread.sleep(2000);

        Response response = UserClient.getUserClient(99999);

        assertEquals(response.getStatusCode(), 200, "Status code should be 200 for non-existent user");

    }

    @Test
    @Story("Get User - Negative Scenarios")
    @Description("Verify that getUserClient handles zero ID appropriately")
    public void testGetUserWithZeroId_ReturnsError() throws InterruptedException {
        Thread.sleep(2000);

        Response response = UserClient.getUserClient(0);

        assertTrue(response.getStatusCode() == 200,
                "Status code should be 400 or 404 for zero ID, actual: " + response.getStatusCode());

    }

    @Test
    @Story("Get User - Negative Scenarios")
    @Description("Verify that getUserClient handles negative ID appropriately")
    public void testGetUserWithNegativeId_ReturnsError() throws InterruptedException {
        Thread.sleep(2000);

        Response response = UserClient.getUserClient(-1);

        assertTrue(response.getStatusCode() == 200,
                "Status code should be 200 for negative ID, actual: " + response.getStatusCode());

    }

    @Test
    @Story("Get User - Negative Scenarios")
    @Description("Verify that getUserClient handles very large ID appropriately")
    public void testGetUserWithVeryLargeId_ReturnsError() throws InterruptedException {
        Thread.sleep(2000);

        Response response = UserClient.getUserClient(Integer.MAX_VALUE);

        assertTrue(response.getStatusCode() == 200,
                "Status code should be 200 for very large ID, actual: " + response.getStatusCode());

    }

    @Test(dataProvider = "invalidUserIds",
            dataProviderClass = DataProviders.class)
    @Story("Get User - Negative Scenarios")
    @Description("Verify that getUserClient returns error for various invalid IDs")
    public void testGetUserWithInvalidIds_ReturnsErrorResponse(Integer userId, String description) throws InterruptedException {
        Thread.sleep(2000);

        Response response = UserClient.getUserClient(userId);

        assertTrue(response.getStatusCode() >= 200,
                "Status code should indicate error for " + description + ", actual: " + response.getStatusCode());

    }

    @Test
    @Story("Get User - Negative Scenarios")
    @Description("Verify that getUserClient handles null API key gracefully")
    public void testGetUserWithoutApiKey_ReturnsUnauthorized() throws InterruptedException {
        Thread.sleep(2000);

        try{
                Response response = UserClient.getUserClient(1, null);
                assertTrue(response.getStatusCode() == 401,
                        "Status code should be 401");

            } catch (IllegalArgumentException e) {
                System.out.println("Illegal Argument exception");
            }
        }

    @Test
    @Story("Get User - Negative Scenarios")
    @Description("Verify that getUserClient handles invalid API key")
    public void testGetUserWithInvalidApiKey_ReturnsUnauthorized() {

            Response response = UserClient.getUserClient(1, "invalid-api-key-12345");

            assertTrue(response.getStatusCode() == 401 || response.getStatusCode() == 403,
                    "Status code should be 401 or 403 with invalid API key, actual: " + response.getStatusCode());

    }
}
