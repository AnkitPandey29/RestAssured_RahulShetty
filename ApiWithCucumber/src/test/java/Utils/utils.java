package Utils;

import io.restassured.path.json.JsonPath;

public class utils {
	
	
  public static JsonPath rawtoJson(String str)
  {
	  JsonPath js = new JsonPath(str);
	return js;
	 
			  
  }
}
