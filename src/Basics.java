import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;
import files.reUsableMethods;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Add new address
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key","qacheck123").header("Content-type","application/json")
		.body(Payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
		.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		JsonPath js =reUsableMethods.rawTojson(response);
		String placeID=js.getString("place_id");
		
		System.out.println("Our place id is" +placeID);
	
	
	// Update address
	String newAddress = "54 Sheetal Summer park Summer walk, USA";
	given().queryParam("key","qaclick123").header("Content-type","application/json")
			.body("{\r\n"
					+ "\"place_id\":\""+placeID+"\",\r\n"
					+ "\"address\":\""+newAddress+"\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}\r\n"
					+ "")
			.when().put("/maps/api/place/update/json")
			.then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
	
	// Get Address
	String response1=given().queryParam("key","qaclick123").queryParam("place_id",placeID).
	when().get("/maps/api/place/get/json").
	then().log().all().extract().response().asString();
	//System.out.println(response1);
	
	JsonPath js2=reUsableMethods.rawTojson(response1);
	String actualAddress = js2.getString("address");
	Assert.assertEquals(actualAddress, newAddress);
	System.out.println(actualAddress);
	System.out.println(newAddress);	
	//Delete address
	System.out.println("Deleting address");
	given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
	.when().delete("/maps/api/place/get/json")
	.then().log().all().assertThat().statusCode(200);
				
	}

}
