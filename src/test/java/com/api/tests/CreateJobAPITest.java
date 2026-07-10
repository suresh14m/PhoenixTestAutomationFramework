package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

public class CreateJobAPITest {
	
	@Test
	public void createJobAPITest() {
		// Implement the test logic for creating a job via API
		
		
		Customer customer = new Customer("suresh", "varma", "9008123826", "", "sure@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("123 Main St", "Cityville", "12345", "partyhall", "banglore", "583121", "India", "Karnataka");
		CustomerProduct customerProduct = new CustomerProduct(
				"2026-05-25T18:30:00.000Z", "81446453973122", "81446453973122", "81446453973122", "2026-05-25T18:30:00.000Z", 3, 3);
		
		Problems problems = new Problems(3, "problem with the product");
		Problems[] problemsArray = {problems}; // Create an array of Problems| new Problems[1]; // Create an array of Problems with size 1
		problemsArray[0] = problems; // Assign the Problems object to the array
		
		CreateJobPayload createJobPayload = new CreateJobPayload(2, 0, 1, 2, customer, customerAddress, customerProduct, problemsArray);
		
		
		given().baseUri(ConfigManager.getProperty("BASE_URI"))
		.spec(SpecUtil.requestSpecWithAuth(Role.FD, createJobPayload))
		.when()
		.post("/job/create")     
		.then()
		.spec(SpecUtil.responseSpec_ok());
	}

}
