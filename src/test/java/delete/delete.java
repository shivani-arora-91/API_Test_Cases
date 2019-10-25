package delete;

import java.text.ParseException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class delete {
	
	public RequestSpecification httpRequest;
	public JSONObject requestParams;
	public Response response;

	@BeforeClass
	public void getEndPoint() throws InterruptedException
	{
		//Specify the baseURL
				RestAssured.baseURI = "http://localhost:5000/v1/product/";
				
				//Request Object
				 httpRequest = RestAssured.given();
				
				
				
				httpRequest.header("Content-Type","application/json");
				
				
				
				//Response Object
				 response = httpRequest.request(Method.DELETE,"5");
				
				 
		         Thread.sleep(3);
				
				
	}
	
	@Test()
	public void testDeleteResponseCode() throws InterruptedException
	{
		
		
		// Validate Response Code
		int code = response.getStatusCode();
		
		AssertJUnit.assertEquals(code,200);
		
		System.out.println("Status Code for Delete Request " + code);
		
	}
	
	
	
	@Test
	public void testDeleteResponseStatusLine()
	{
		//Validate StatusLine
		String statusLine = response.getStatusLine();
		
		System.out.println("Status code for delete request is" + statusLine);
	
		
		AssertJUnit.assertEquals(statusLine,"HTTP/1.1 200 OK");
		
	}
	
	@Test
	public void validateHeaders() throws ParseException
	{
		Headers allheaders = response.getHeaders();
		
		
		String Server = allheaders.get("Server").getValue();
		String type = allheaders.get("Content-Type").getValue();
	
		
		System.out.println(Server);
		System.out.println(type);
		
		
		
		AssertJUnit.assertEquals(Server,"waitress");
		AssertJUnit.assertEquals(type, "application/json");
		
	}

	@Test()
	public void validateDB() throws ParseException
	{
		
		//Specify the baseURL
		RestAssured.baseURI = "http://localhost:5000/v1/product/";
		
		//Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response = httpRequest.request(Method.GET,"5");
        
		int code = response.getStatusCode();
		
		System.out.println("Status code for get request is" + code);
	
		
		Assert.assertEquals(code,404);
		
	
	}
}
