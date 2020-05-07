//Ignore this file, just a sample


package com.restassuredserenity.junit;

import org.junit.BeforeClass;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.Matchers.*;


@RunWith(SerenityRunner.class)
public class GetSample {
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI="http://restcountries.eu/rest/v1";
	}
	
	
	@Test
	public void GetCountry() {
//		RestAssured.given()
		SerenityRest.given()
				   .when()
				   .get("/name/India")
				   .then()
				   .statusCode(200)	
				   .assertThat()
				   .body(containsString("Republic of India"))
				   .body("altSpellings[1]", hasItem("Republic of India"))
				   .body("find {it.name=='India' }.altSpellings", hasItem("Republic of India"))
				   .log()
//				   .body();
				   .all();
	}
	
	@Test
	public void GetCountryNegativeTest() {
		SerenityRest.given()
				   .when()
				   .get("/name/xyz")
				   .then()
				   .statusCode(404)
				   .assertThat()
				   .body("status", equalTo(404))
				   .body("message",equalTo("Not Found"))
				   .log()
				   .all();
	}
	
	@Test
	public void GetCountryNorway() {
//		RestAssured.given()
		SerenityRest.given()
				   .when()
				   .get("/name/Norway")
				   .then()
				   .statusCode(200)	
				   .assertThat()
				   .body("capital[0]", equalTo("Oslo"))	
//				   .body("capital", hasItem("Oslo"))				   
				   .log()
				   .body();
	}
	

}
