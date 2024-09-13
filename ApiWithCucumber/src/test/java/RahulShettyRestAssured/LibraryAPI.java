package RahulShettyRestAssured;

import org.testng.annotations.Test;

import Payloads.payload;
import Utils.TestDataProvider;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class LibraryAPI extends TestDataProvider {
	
	
	@Test(dataProvider="AddBooksData")
	public void addBook(String isbn,String aisle)
	{
		
		RestAssured.baseURI="http://216.10.245.166";
		String response =given().header("Content-Type", "application/json")
		.body(payload.addBook(isbn, aisle))
		.when().post("/Library/Addbook.php")
		.then().statusCode(200).extract().response().asString();
		System.out.println(response);
		JsonPath js = Utils.utils.rawtoJson(response);
		String id = js.get("ID");
		System.out.println(id);
	}

}
