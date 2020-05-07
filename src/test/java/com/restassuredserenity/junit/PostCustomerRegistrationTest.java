package com.restassuredserenity.junit;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;

import static org.hamcrest.Matchers.*;
import static com.restassuredserenity.utils.UtilitiesClass.*;

@RunWith(SerenityRunner.class)
public class PostCustomerRegistrationTest {
	
	static String name = getUniqueName();
	String request = "{\r\n" + 
	   		"\"FirstName\": \"" + name + "\",\r\n" + 
	   		"\"LastName\": \"TestQA\",\r\n" + 
	   		"\"UserName\": \"unique123"+ name +"Test\",\r\n" + 
	   		"\"Password\": \"welcome@1\",\r\n" + 
	   		"\"Email\": \"unique"+name+"Test@demoqa.com\"\r\n" + 
	   		"}\r\n" + 
	   		"";
	String endpoint = "http://restapi.demoqa.com/customer/register";
	
	@Title("The customer is able to create an account")
	@Test
	public void RegisterCustomer() {
		SerenityRest.rest()
				   .given()
		           .contentType(ContentType.JSON)
		           .log()
		           .all()
				   .when()
				   .body(request)
				   .post(endpoint)
				   .then()	
				   .log()
				   .all()
				   .and()
				   .statusCode(201)	
				   .assertThat()
				   .body("SuccessCode", equalTo("OPERATION_SUCCESS"))
				   .body(containsString("Operation completed successfully"));
	}
	@Title("The customer is not able to create an account with the information registered already")
	@Test
	public void RegisterCustomerNegativeTest() {

		SerenityRest.rest()
				   .given()
		           .contentType(ContentType.JSON)
		           .log()
		           .all()
				   .when()
				   .body(request)
				   .post(endpoint)
				   .then()				   
				   .statusCode(200)	
				   .assertThat()
				   .body("FaultId", equalTo("User already exists"))
				   .body(containsString("User already exists"))
				   .body("fault", equalTo("FAULT_USER_ALREADY_EXISTS"))
				   .log()
				   .all();
	}
}
