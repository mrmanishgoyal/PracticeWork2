package files;

import io.restassured.path.json.JsonPath;

public class reUsableMethods {
	
	public static JsonPath rawTojson(String response)
	{
		JsonPath js = new JsonPath(response);
		return js;
	}

}
