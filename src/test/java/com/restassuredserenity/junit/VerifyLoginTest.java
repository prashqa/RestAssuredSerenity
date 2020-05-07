package com.restassuredserenity.junit;

import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;

@RunWith(SerenityRunner.class)
public class VerifyLoginTest {
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI="http://restapi.demoqa.com/authentication/CheckForAuthentication";
	}
	
	
	@Test
	public void VerifyLoginIsSuccessful() {
		SerenityRest.given()
					.auth()
					.basic("ToolsQA", "TestPassword")
				   .when()
				   .get()
				   .then()
				   .statusCode(200);	
				   
	}
	
	@Test
	public void VerifyLoginIsUnsuccessful() {
		SerenityRest.given()
					.auth()
					.basic("unique123AbdielTest", "welcome@1")
				   .when()
				   .get()
				   .then()
				   .statusCode(401)
				   .assertThat()
				   .body("fault", equalTo("Invalid username or password"));
	
				   
	}

}
