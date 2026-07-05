package com.api.tests;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

import io.restassured.response.Response;

public class LogivAPITest {

    @Test
    public void testLogivAPI() throws IOException {

        UserCredentials userCredentials = new UserCredentials("iamfd", "password");

        String schemaPath = "response-schema/LoginResponseSchema.json";

        // Print schema details
        System.out.println("=========================================");
        System.out.println("Schema Path : " + schemaPath);
        System.out.println("Schema URL  : "
                + Thread.currentThread()
                        .getContextClassLoader()
                        .getResource(schemaPath));
        System.out.println("=========================================");

        Response response = given()
                .baseUri(getProperty("BASE_URI"))
                .contentType(JSON)
                .accept(JSON)
                .body(userCredentials)
                .log().uri()
                .log().method()
                .log().headers()
                .log().body()

        .when()
                .post("/login");

        // Print response
        response.then().log().all();

        System.out.println("Status Code : " + response.getStatusCode());
        System.out.println("Response Body : ");
        System.out.println(response.getBody().asPrettyString());

        // Validate response
        response.then()
                .statusCode(200)
                .time(lessThan(5000L))
                .body("message", equalTo("Success"));

        // Validate JSON Schema
        response.then()
                .body(matchesJsonSchemaInClasspath(schemaPath));

        System.out.println("Login API Schema Validation Passed.");
    }
}