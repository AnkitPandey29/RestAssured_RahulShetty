package RahulShettyRestAssured;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Oath2GrantTypeClientCredentials {
	
	String access_token;
	
	@Test(priority=1)
	public void getAccessTokenfromAutorizationServer()
	{
		 String response=given()
		.param("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.param("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.param("grant_type", "client_credentials")
		.param("scope", "trust")
		.log().all()
		.when()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.then()
		.log().all()
		.extract().response().asString();
		 JsonPath js = Utils.utils.rawtoJson(response);
		 access_token=js.getString("access_token");
		 System.out.println(access_token);

	}
	
	@Test(priority=2)
	public void getCourseDetails()
	{
		String courses=given()
		.log().all()
		.queryParam("access_token", access_token)
		.header("Content-Type","application/json")
		.when()
		.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		.then()
		.log().all()
		//.assertThat().statusCode(200)
		.extract().response().asString();
		System.out.println(courses);
		System.out.println("Ankit");
		
	}

}
