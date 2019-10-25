package getRequest;




import java.util.List;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class GetDataNegative {
	
	public Response response;
	
	@BeforeClass
	public void getEndPointId() throws InterruptedException
	{
		//Specify the baseURL
				RestAssured.baseURI = "http://localhost:5000/v1/product";
				
				//Request Object
				RequestSpecification httpRequest = RestAssured.given();
				
				//Response Object
				response = httpRequest.request(Method.GET,"/abc");
				
				Thread.sleep(3);
	}
	

	@Test
	public void testResponseCode()
	{
		
		
		int code = response.getStatusCode();
		
		System.out.println("Status code for get request is" + code);
	
		
		Assert.assertEquals(code,404);
		
	}
	
	@Test
	public void testResponseLine()
	{
		
		
		String statusLine = response.getStatusLine();
		
		System.out.println("Status code for get request is" + statusLine);
	
		
		Assert.assertEquals(statusLine,"HTTP/1.1 404 NOT FOUND");
		
	}
	
	
	
	@Test
	public void validateResponseBody()
	{
		String responseBody = response.getBody().asString();
		
		System.out.println("Response Body" + responseBody);
		
		JsonPath js = response.jsonPath();
		//validate jsonFormat
		
		Assert.assertEquals(js.get("status"), 404);
		Assert.assertEquals(js.get("title"), "Not Found");
		
		}
	
	@Test
	public void validateHeaders()
	{
		Headers allheaders = response.headers();
		
		String Server = allheaders.get("Server").getValue();
		String type = allheaders.get("Content-Type").getValue();
		String Length = allheaders.get("Content-Length").getValue();
		
		System.out.println(Server);
		System.out.println(type);
		System.out.println(Length);
		
		
		Assert.assertEquals(Server, "waitress");
		Assert.assertEquals(type, "application/json");
		
		
	}
	}
	
	

