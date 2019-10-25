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

public class PostRequestId {
	
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
				
				requestParams.put("Id",1);
				requestParams.put("name","IPhone"); 
				requestParams.put("price",1000.00);
				
				
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
		
		Assert.assertEquals(404, code);
		
		System.out.println("Status Code for Post Request " + code);
		
	}
	
	


}
