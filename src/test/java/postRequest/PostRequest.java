package postRequest;

import java.util.List;

import org.json.simple.JSONObject;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class PostRequest {
	
public RequestSpecification httpRequest;
public JSONObject requestParams;
public Response response;
	
	@BeforeClass
	public void getEndPoint() throws InterruptedException
	{
		//Specify the baseURL
				RestAssured.baseURI = "http://localhost:5000/v1/product";
				
				//Request Object
				RequestSpecification httpRequest = RestAssured.given();
				
				//Response Object, send data to server in json
				JSONObject requestParams = new JSONObject();
				
				
				requestParams.put("name","Computer"); 
				requestParams.put("price",1500.50);
				
				
				httpRequest.header("Content-Type","application/json");
				
				//convert request to json format
				httpRequest.body(requestParams.toJSONString());
				
				//Response Object
				 response = httpRequest.request(Method.POST,"");
				
				
				
				Thread.sleep(3);
	}
	
	
	@Test
	public void testPostResponseCode()
	{
		// Validate Response Code
		int code = response.getStatusCode();
		
		Assert.assertEquals(200, code);
		
		System.out.println("Status Code for Post Request " + code);
		
	}
	
	
	
	@Test
	public void testPostResponseStatusLine()
	{
		//Validate StatusLine
		String statusLine = response.getStatusLine();
		
		System.out.println("Status code for post request is" + statusLine);
	
		
		Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
		
	}
	
	
	@Test
	public void validateDB()
	{
		
		//Specify the baseURL
				RestAssured.baseURI = "http://localhost:5000/v1/products";
				
				//Request Object
				RequestSpecification httpRequest = RestAssured.given();
				
				Response response = httpRequest.request(Method.GET);
		        
				String responseBody = response.getBody().asString();
				
				System.out.println("Response Body" + responseBody);
				
				JsonPath js = response.jsonPath();
				
				List<String> name = js.getList("name");
				List<String> price = js.getList("price");
				
				Assert.assertEquals(true, name.contains("Computer"));
				Assert.assertEquals(true, price.contains("1500.50"));
	}

}
