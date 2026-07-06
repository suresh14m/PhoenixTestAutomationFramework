package com.api.tests;

import static com.api.constants.Role.FD;
import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

public class MasterAPITest {
	
	@Test
	public void testMasterAPI() {
		// Implement your test logic here
		given()
		.spec(SpecUtil.requestSpecWithAuth(FD))		
		.when()
		.post("/master")
		.then()
		.spec(SpecUtil.responseSpec_ok())
		.and()
		
		.body("message", equalTo("Success"))
		.body("data", notNullValue())
		.body("data", hasKey("mst_warrenty_status"))
		.body("$", hasKey("message"), "data", notNullValue())
		.body("data.map_fst_pincode.size()", greaterThan(0))
		.body("data.mst_oem.size()", equalTo(2))//size of json array using matchers
		.body("data.mst_oem.id" , everyItem(notNullValue()))
		.body("data.mst_oem.name" , everyItem(notNullValue()))
		.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchema.json"));
		
	}
	
	
	@Test	
	public void testMasterAPIWithInvalidToken() {
		given().spec(SpecUtil.requestSpec())// request without auth token
				.when().post("/master").then().spec(SpecUtil.responseSpec_text(401));
				

	}

}
