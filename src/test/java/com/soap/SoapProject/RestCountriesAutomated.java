package com.soap.SoapProject;

import java.util.ArrayList;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import utility.Utility;

public class RestCountriesAutomated {

	@Test
	public void fetchCountryData() throws Exception {
		
		RestAssured.baseURI=Utility.getValues("baseUrl");
		RequestSpecification request=RestAssured.given();
		ArrayList<String> al=Utility.getExcelData("CountryName");
		ArrayList<String> bl=Utility.getExcelData("CountryValue");
		for(int i=1;i<al.size();i++) {
		request.pathParam(al.get(i),bl.get(i));
		Response response=	request.get(Utility.getValues("extendedUrl"));
		
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertTrue(response.getContentType().contains("application/json"));
		System.out.println(response.getBody().asString());
		}
	}
	
	
}
