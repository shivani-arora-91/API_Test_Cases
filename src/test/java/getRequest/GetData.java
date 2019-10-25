package getRequest;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetData {
	
	public Response response;
	
	@BeforeClass
	public void getEndPoint() throws InterruptedException
	{
		//Specify the baseURL
				RestAssured.baseURI = "http://localhost:5000/v1/products";
				
				//Request Object
				RequestSpecification httpRequest = RestAssured.given();
				
				//Response Object
				response = httpRequest.request(Method.GET,"");
				
				Thread.sleep(3);
	}
	

	@Test
	public void testResponseCode()
	{
		
		
		int code = response.getStatusCode();
		
		System.out.println("Status code for get request is" + code);
	
		
		Assert.assertEquals(code,200);
		
	}
	
	@Test
	public void testResponseLine()
	{
		
		
		String statusLine = response.getStatusLine();
		
		System.out.println("Status code for get request is" + statusLine);
	
		
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
		
	}
	
	
	
	
	@Test
	public void validateResponseBody()
	{
		String responseBody = response.getBody().asString();
		
		System.out.println("Response Body" + responseBody);
		
		JsonPath js = response.jsonPath();
		
		String node = null;
		//validate jsonFormat
		if(!responseBody.contains("product-code"))
		{
			node = "id";
		}
		Assert.assertEquals(node, "product-code");
		AssertJUnit.assertEquals(responseBody.contains("name"), true);
		AssertJUnit.assertEquals(responseBody.contains("price"), true);	
		
		}
	
	@Test
	public void validateHeaders() throws ParseException
	{
		Headers allheaders = response.getHeaders();
		
		Date d = new Date();
		
		System.out.println("Date is: " + d);
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String d1 = sdf.format(d);
		System.out.println("Formatted Date is: " + sdf.parse(d1));
		String date = allheaders.get("Date").getValue();
		String Server = allheaders.get("Server").getValue();
		String type = allheaders.get("Content-Type").getValue();
		
		
		System.out.println(date);
		System.out.println(Server);
		System.out.println(type);
		
		
		
		Assert.assertEquals(Server, "waitress");
		Assert.assertEquals(type, "application/json");
		
		
	}
		
	
		
	
	

}
	
	

