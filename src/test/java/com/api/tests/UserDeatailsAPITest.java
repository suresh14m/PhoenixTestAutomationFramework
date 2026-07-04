package com.api.tests;
import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import static com.api.constants.Role.*;

import io.restassured.http.Header;




public class UserDeatailsAPITest {
	
	
	@Test
	public void testUserDetailsAPI() throws IOException {
		// Rest Assured code to test User Details API
		
		Header authHeader = new Header("Authorization",getToken(SUP));
		given()
			.baseUri(getProperty("BASE_URI"))
			.and()
			.contentType(JSON)
			.and()
			.header(authHeader)
			.and()
			.accept(JSON)
			.log().uri()
			.log().method()
			.log().headers()
		.when()
			.get("/userdetails")
		.then()
			.log().all()
			.statusCode(200)
			.time(lessThan(1000L))
			.and()
			.body("message", equalTo("Success")).and()
			.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponceSchema.json"));
		
		
		
	}

}
