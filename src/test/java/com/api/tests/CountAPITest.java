package com.api.tests;

import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

public class CountAPITest {

	@Test
	public void verifyCountAPIResponse() {
		// Implement test logic for Count API
		given().spec(SpecUtil.requestSpecWithAuth(FD)) // helper method to get the request specification with auth token
				.when().get("/dashboard/count").then().spec(SpecUtil.responseSpec_ok()) // helper method to get the
																						// response specification
				.and().body("message", equalTo("Success")).body("data", notNullValue()).body("data.size()", equalTo(3))
				.body("data.count", everyItem(greaterThanOrEqualTo(0)))
				.body("data.label", everyItem(not(blankOrNullString())))
				.body("data.key", containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
				.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema.json"));
	}

	@Test
	public void verifyCountAPIResponseWithoutAuthToken() {
		// Implement test logic for Count API without auth token
		given().spec(SpecUtil.requestSpec()) // helper method to get the request specification without auth token
				.when().get("/dashboard/count")
				.then()
				.spec(SpecUtil.responseSpec_text(401));// helper method to get the response specification for 401
	}
}
