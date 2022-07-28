import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;
import org.testng.Assert;

import files.Payload;
import files.reUsableMethods;


public class Courses {
	
	@Test
	public void assignment()
	{
		JsonPath js =reUsableMethods.rawTojson(Payload.Courseprices());
		int sum=0;
		
		//Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println("Number of courses- "+count);
		
		//Print Purchase Amount
		System.out.println ("Title of first course is "+ js.getString("courses[0].title"));
		int purchaseamount= js.getInt("dashboard.purchaseAmount");
		for(int i=0;i<count;i++)
		{
			int price = js.getInt("courses["+i+"].price");
			int copies =js.getInt("courses["+i+"].copies");
			String coursename = js.get("courses["+i+"].title");
			System.out.println("Purchase amount of "+coursename+" is "+price*copies);
			sum=sum+(price*copies);
		}
		
		Assert.assertEquals(sum, purchaseamount);
	
	}

}
