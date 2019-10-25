package putRequest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class putMissingPrice {

	
	public RequestSpecification httpRequest;
	public JSONObject requestParams;
	public Response response;

	@BeforeClass
	public void getEndPoint() throws InterruptedException
	{
		//Specify the baseURL
				RestAssured.baseURI = "http://localhost:5000/v1/product";
				
				//Request Object
				 httpRequest = RestAssured.given();
				
				
				
				httpRequest.header("Content-Type","application/json");
				
				//Response Object, send data to server in json
				JSONObject requestParams = new JSONObject();
				
				
				requestParams.put("name","Power Backup"); 
				//requestParams.put("price",21);
				
				//convert request to json format
				httpRequest.body(requestParams.toJSONString());
				
				//Response Object
				 response = httpRequest.request(Method.PUT,"/4");
				
		         Thread.sleep(3);
				
				
	}
	
	@Test()
	public void testPutResponseCode() throws InterruptedException
	{
		
		
		// Validate Response Code
		int code = response.getStatusCode();
		
		AssertJUnit.assertEquals(200, code);
		
		System.out.println("Status Code for Put Request " + code);
		
	}
	
	
	
	@Test
	public void testPutResponseStatusLine()
	{
		//Validate StatusLine
		String statusLine = response.getStatusLine();
		
		System.out.println("Status Line for Put request is" + statusLine);
	
		
		AssertJUnit.assertEquals("HTTP/1.1 200 OK",statusLine);
		
	}
	
	@Test
	public void validateHeaders() throws ParseException
	{
		Headers allheaders = response.getHeaders();
		
		
		String Server = allheaders.get("Server").getValue();
		String type = allheaders.get("Content-Type").getValue();
		String Length = allheaders.get("Content-Length").getValue();
		
		
		System.out.println(Server);
		System.out.println(type);
		System.out.println(Length);
		
		
		Assert.assertEquals("waitress",Server);
		Assert.assertEquals("application/json",type);
		Assert.assertEquals("0",Length);
	}
	
	@Test()
	public void validateDB() throws ParseException
	{
		
		//Specify the baseURL
		RestAssured.baseURI = "http://localhost:5000/v1/product/";
		
		//Request Object
		RequestSpecification httpRequestPut = RestAssured.given();
		
		Response responsePut = httpRequestPut.request(Method.GET,"4");
        
		String responseBody = responsePut.getBody().asString();
		
		System.out.println("Response Body" + responseBody);
		
		JsonPath js = responsePut.jsonPath();
		
		
		String name = js.get("name");
		
		
		Assert.assertEquals(name, "Power Backup");
	
	}
	

}
