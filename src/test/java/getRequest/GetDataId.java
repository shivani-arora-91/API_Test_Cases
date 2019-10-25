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


public class GetDataId {
	
	public Response response;
	
	@BeforeClass
	public void getEndPointId() throws InterruptedException
	{
		//Specify the baseURL
				RestAssured.baseURI = "http://localhost:5000/v1/product";
				
				//Request Object
				RequestSpecification httpRequest = RestAssured.given();
				
				//Response Object
				response = httpRequest.request(Method.GET,"/3");
				
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
	public void testResponseDataId()
	{
				JsonPath jsonpath = response.jsonPath();
				
				
				int id = jsonpath.get("id");
				
				System.out.println("Id : " + id);
				
				
			
					//System.out.println("true");
					Assert.assertSame(id, 3);
				
				
		
	}
	
	
	@Test
	public void validateResponseBody()
	{
		String responseBody = response.getBody().asString();
		
		System.out.println("Response Body" + responseBody);
		
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
		Assert.assertEquals(Length, "60");
		
	}
	
	@Test void validateDataType()
	{
		JsonPath jsonpath = response.jsonPath();
		
		
		try
		{
			Float price = jsonpath.get("price");
		}
		catch(Exception e)
		{
			System.out.println("Exception is " + e);
			Assert.assertEquals("String","float");
		}
		
		
	
	
			
		
	}
	


}
	
	

