package com.api.tests;

import static com.api.constants.Role.FD;
import static com.api.utils.SpecUtil.requestSpecWithAuth;
import static com.api.utils.SpecUtil.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

public class UserDeatailsAPITest {

	@Test
	public void testUserDetailsAPI() throws IOException {
		// Rest Assured code to test User Details API

		given().spec(requestSpecWithAuth(FD)).when().get("/userdetails").then().spec(responseSpec_ok()).body("message", equalTo("Success")).and()
				.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponceSchema.json"));

	}

}
