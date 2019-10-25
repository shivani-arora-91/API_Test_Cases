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

public class putProductCode {

	
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
				
				
				//requestParams.put("name","Update Test"); 
				requestParams.put("Product-Code",1);
				requestParams.put("name","Incorrect Update");
				requestParams.put("price",130);
				
				//convert request to json format
				httpRequest.body(requestParams.toJSONString());
				
				//Response Object
				 response = httpRequest.request(Method.PUT,"/8");
				
		         Thread.sleep(3);
				
				
	}
	
	@Test()
	public void testPutResponseCode() throws InterruptedException
	{
		
		
		// Validate Response Code
		int code = response.getStatusCode();
		
		AssertJUnit.assertEquals(404, code);
		
		System.out.println("Status Code for Put Request " + code);
		
	}

	

}
