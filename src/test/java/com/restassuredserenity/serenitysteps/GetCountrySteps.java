package com.restassuredserenity.serenitysteps;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class GetCountrySteps {
	
	@Step("Getting the details of the country: {0}")
	public ValidatableResponse getCountry(String country) {
		return SerenityRest.given()
				   .when()
				   .get(country)
				   .then();
	}

}
