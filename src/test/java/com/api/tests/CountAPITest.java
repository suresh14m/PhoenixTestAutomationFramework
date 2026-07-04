package com.api.tests;

import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static com.api.constants.Role.*;

import static com.api.utils.AuthTokenProvider.*;

import static io.restassured.RestAssured.*;
import static com.api.utils.ConfigManager.*;
import static org.hamcrest.Matchers.*;

public class CountAPITest {
	
	@Test
	public void verifyCountAPIResponse() {
		// Implement test logic for Count API
		given()
            .baseUri(getProperty("BASE_URI")) //helper method to get the base URI from configuration
            .and()
            .header("Authorization",getToken(FD)) // Assuming you have a method to get the token)
            .log().uri()
            .log().method()
            .log().headers()
            .and()
            .when()
            .get("/dashboard/count")
            .then() 
            .log().all()
            .statusCode(200)
            .and()
            .body("message", equalTo("Success"))
            .time(lessThan(1000L))
            .body("data", notNullValue())
		    .body("data.size()", equalTo(3))
		    .body("data.count", everyItem(greaterThanOrEqualTo(0)))
			.body("data.label",everyItem(not(blankOrNullString())))
			.body("data.key", containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
            .body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema.json"));
	}
	
	
	
	@Test
	public void verifyCountAPIResponseWithoutAuthToken() {
		// Implement test logic for Count API without auth token
		given().baseUri(getProperty("BASE_URI")) // helper method to get the base URI from configuration
				.and().log().uri()
	            .log().method()
	            .log().headers()
	            .when().get("/dashboard/count")
	            .then().log().all()
	            .statusCode(401).and()
				.time(lessThan(1000L));
	}
}
