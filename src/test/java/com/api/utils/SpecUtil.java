package com.api.utils;

import static com.api.utils.ConfigManager.getProperty;

import com.api.constants.Role;
import com.api.pojo.UserCredentials;
import static org.hamcrest.Matchers.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.constants.Role.*;

public class SpecUtil {

	// works for get AND delete requests
	public static RequestSpecification requestSpec() {
		// take care of common request specifications or sections(methods)
		RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).log(LogDetail.URI).log(LogDetail.METHOD)
				.log(LogDetail.HEADERS).log(LogDetail.BODY).build();

		return requestSpec;

	}

	// works for post AND put requests- methodoverloading
	public static RequestSpecification requestSpec(Object payload) {
		// take care of common request specifications or sections(methods)
		RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).setBody(payload)
				.log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS).log(LogDetail.BODY)

				.build();

		return requestSpec;

	}
	
	//request with Auth
	public static RequestSpecification requestSpecWithAuth(Role role) {
		// take care of common request specifications or sections(methods)
		RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON)
				.addHeader("Authorization",getToken(role))
				
				.log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS).log(LogDetail.BODY)

				.build();

		return requestSpec;
		
	}
	
	
	
	//request with Auth
		public static RequestSpecification requestSpecWithAuth(Role role, Object payload) {
			// take care of common request specifications or sections(methods)
			RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URI"))
					.setContentType(ContentType.JSON).setAccept(ContentType.JSON)
					.addHeader("Authorization",getToken(role))
					.setBody(payload)
					
					.log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS).log(LogDetail.BODY)

					.build();

			return requestSpec;
			
		}
	
	
	// RESPONSE SPECIFICATION
	public static ResponseSpecification responseSpec_ok() {
		ResponseSpecification responseSpec = new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectContentType(ContentType.JSON)
		.expectResponseTime(lessThan(5000L))
		
		.log(LogDetail.ALL).build();
		return responseSpec;
		
	}
	
	
	public static ResponseSpecification responseSpec_JSON(int statusCode) {
		ResponseSpecification responseSpec = new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectContentType(ContentType.JSON)
		.expectResponseTime(lessThan(5000L))
		
		.log(LogDetail.ALL).build();
		return responseSpec;
		
	}
	
	public static ResponseSpecification responseSpec_text(int statusCode) {
		ResponseSpecification responseSpec = new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectResponseTime(lessThan(5000L))
		
		.log(LogDetail.ALL).build();
		return responseSpec;
		
	}

}
