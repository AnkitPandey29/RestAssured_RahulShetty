
package RahulShettyRestAssured;
//

//public class JiraAPI {

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import java.io.File;

public class JiraAPI {
	public static void main(String[] args) { // TODO Auto-generated method stub
		RestAssured.baseURI = "https://ankitpandeyjh.atlassian.net/";
		String createIssueResponse = given().header("Content-Type", "application/json")
				.header("Authorization","Basic YW5raXRwYW5kZXlqaEBnbWFpbC5jb206QVRBVFQzeEZmR0YwNlJzSkhMOU9QaGlFOGlWYnY3MjMyUk00NEFXNE9jSGNsbE1kRVVUanprSzBINDVKWkUzbzVFcVhkQ1l3NWpqbUViNnF3MW1GMTBpdHJLM1ZoU1RDOWh2LUNMRk4wSzR2a25Ea2FGV05hV3RDOXRSSTFkeGJBQXBQNUc0eFhzZExZaEdCdXB4NmJqaUgxN0d1X1N4Uko0REZ1SUxEaTNZd3RJY3VMVUh2a2g4PTk5RERFNjM0")
				.body("{\n" + "    \"fields\": {\n" + "       \"project\":\n" + "       {\n"
						+ "          \"key\": \"SCRUM\"\n" + "       },\n"
						+ "       \"summary\": \"Website items are not working- automation Rest Assured\",\n"
						+ "       \"issuetype\": {\n" + "          \"name\": \"Bug\"\n" + "       }\n" + "   }\n" + "}")
				.log().all().post("rest/api/3/issue").then().log().all().assertThat().statusCode(201)
				.contentType("application/json").extract().response().asString();
		JsonPath js = new JsonPath(createIssueResponse);
		String issueId = js.getString("id");
		System.out.println(issueId);
		given().pathParam("key", issueId).header("X-Atlassian-Token", "no-check").header("Authorization",
				"Basic YW5raXRwYW5kZXlqaEBnbWFpbC5jb206QVRBVFQzeEZmR0YwNlJzSkhMOU9QaGlFOGlWYnY3MjMyUk00NEFXNE9jSGNsbE1kRVVUanprSzBINDVKWkUzbzVFcVhkQ1l3NWpqbUViNnF3MW1GMTBpdHJLM1ZoU1RDOWh2LUNMRk4wSzR2a25Ea2FGV05hV3RDOXRSSTFkeGJBQXBQNUc0eFhzZExZaEdCdXB4NmJqaUgxN0d1X1N4Uko0REZ1SUxEaTNZd3RJY3VMVUh2a2g4PTk5RERFNjM0")
				.multiPart("file", new File("D:\\a.png")).log().all()
				.post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
		// Add attachment }
	}
}
