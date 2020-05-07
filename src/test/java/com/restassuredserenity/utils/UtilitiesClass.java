package com.restassuredserenity.utils;

import com.github.javafaker.Faker;

public class UtilitiesClass {

	public static String getUniqueName() {		
			Faker faker = new Faker();
			String firstName = faker.name().firstName();		
			return firstName;		
	}

}
