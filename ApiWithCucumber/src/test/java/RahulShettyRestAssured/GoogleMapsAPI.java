package RahulShettyRestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import Payloads.payload;
import Utils.utils;

public class GoogleMapsAPI {

	String Place_Id;

	// 18-Rahul-Restassured

	// @Test
	public void addRequest0() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" + "    \"lng\": 33.427362\r\n"
						+ "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"Frontline house\",\r\n"
						+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
						+ "  \"address\": \"29, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n"
						+ "    \"shoe park\",\r\n" + "    \"shop\"\r\n" + "  ],\r\n"
						+ "  \"website\": \"http://google.com\",\r\n" + "  \"language\": \"French-IN\"\r\n" + "}\r\n"
						+ "")
				.when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Content-Type", "application/json;charset=UTF-8");
	}

	// @Test
	public void addRequest1() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.AddPlace()).when().post("/maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Content-Type", "application/json;charset=UTF-8");
	}

	@Test(priority = 0)
	public void addRequest2() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload.AddPlace()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Content-Type", "application/json;charset=UTF-8").extract()
				.response().asString();
		System.out.println(response);
		JsonPath js = new JsonPath(response);// Accept String
		Place_Id = js.get("place_id");
		System.out.println(Place_Id);

	}

	@Test(priority = 2)
	public void getRequest() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", Place_Id)

				.header("Content-Type", "application/json").when().get("/maps/api/place/get/json").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		System.out.println(response);

	}

	@Test(priority = 1)
	public void updateRequest() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + Place_Id + "\",\r\n" + "\"address\":\"70 Summer walk, sonu\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}\r\n" + "")
				.when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();

		System.out.println(response);

	}

	@Test(priority = 3)
	public void deleteRequest() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "    \"place_id\":\"" + Place_Id + "\"\r\n" + "}\r\n" + "").when()
				.delete("/maps/api/place/delete/json").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();
		JsonPath js =utils.rawtoJson(response);
		String status=js.getString("status");
		Assert.assertEquals(status, "OK");

		

	}
}