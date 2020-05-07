package com.restassuredserenity.utils;

import io.restassured.builder.RequestSpecBuilder;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;


public class ReUsableSpecifications {
	
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	public static RequestSpecBuilder requestBuilder;
	public static ResponseSpecBuilder responseBuilder;

	public static RequestSpecification getRequest() {
		requestBuilder = new RequestSpecBuilder();
		requestBuilder.setContentType(ContentType.JSON);
		requestBuilder.addHeader("source","mobile");
		//requestBuilder.setAuth();
		requestSpec = requestBuilder.build();		
		return requestSpec;
	}
	
	public static ResponseSpecification getResponse() {
		responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);
		responseBuilder.expectHeader("Content-Type", "application/json");
		responseSpec = responseBuilder.build();
		return responseSpec;
	}
	
	
}
