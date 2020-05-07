package com.restassuredserenity.junit;

import org.junit.BeforeClass;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.restassuredserenity.serenitysteps.GetCountrySteps;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import static org.hamcrest.Matchers.*;


@RunWith(SerenityRunner.class)
public class GetCountryByNameTest {
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI="http://restcountries.eu/rest/v1/name/";
	}
	
	@Steps
	GetCountrySteps steps;
	
	@Title("Assignment 1: Verifying Republic of India ")
	@Test
	public void GetCountryIndia() {
		
			  steps.getCountry("India")
				   .statusCode(200)	
				   .assertThat()
				   .body(containsString("Republic of India"))
				   .body("altSpellings[1]", hasItem("Republic of India"))
				   .body("find {it.name=='India' }.altSpellings", hasItem("Republic of India"))
				   .log()
//				   .body();
				   .all();
	}
	
	@Title("Assignment 1: Verifying 404 Not found")
	@Test
	public void GetCountryNegativeTest() {
		      steps.getCountry("xyz")
				   .statusCode(404)
				   .assertThat()
				   .body("status", equalTo(404))
				   .body("message",equalTo("Not Found"))
				   .log()
				   .all();
	}
	
	@Title("Assignment 2: Verifying capital of Oslo")	
	@Test
	public void GetCountryNorway() {
		      steps.getCountry("Norway")
				   .statusCode(200)	
				   .assertThat()
				   .body("capital[0]", equalTo("Oslo"))	
//				   .body("capital", hasItem("Oslo"))				   
				   .log()
				   .body();
	}
	

}
