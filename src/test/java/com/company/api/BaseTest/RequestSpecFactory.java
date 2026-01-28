package com.company.api.baseTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

public class RequestSpecFactory {

    private static final ThreadLocal<RequestSpecification> requestSpec = new ThreadLocal<>();

    public static void initSpec(String baseUri) {
        RequestSpecification spec = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType("application/json")
                .setConfig(RestAssuredConfig.config()
                        .logConfig(LogConfig.logConfig()
                                .blacklistHeader("x-api-key", "***")))
                .build();
        requestSpec.set(spec);
    }

    public static RequestSpecification getSpec() {
        return requestSpec.get();
    }

    public static void removeSpec() {
        requestSpec.remove();
    }
}
