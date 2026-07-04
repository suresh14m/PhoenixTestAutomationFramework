package com.api.utils;

import static com.api.constants.Role.*;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.api.constants.Role;
import com.api.pojo.UserCredentials;

import io.restassured.http.ContentType;

public class AuthTokenProvider {

    private AuthTokenProvider() {
    }

    public static String getToken(Role role) {

        UserCredentials userCredentials;

        switch (role) {

            case FD:
                userCredentials = new UserCredentials("iamfd", "password");
                break;

            case SUP:
                userCredentials = new UserCredentials("iamsup", "password");
                break;

            case ENG:
                userCredentials = new UserCredentials("iameng", "password");
                break;

            case QC:
                userCredentials = new UserCredentials("iamqc", "password");
                break;

            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }

        String token = given()
                .baseUri(getProperty("BASE_URI"))
                .contentType(ContentType.JSON)
                .body(userCredentials)
                .when()
                .post("/login")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("message", equalTo("Success"))
                .extract()
                .jsonPath()
                .getString("data.token");

        System.out.println("-----------------------------------------");
        System.out.println("Generated Token: " + token);
        System.out.println("-----------------------------------------");

        return token;
    }
}