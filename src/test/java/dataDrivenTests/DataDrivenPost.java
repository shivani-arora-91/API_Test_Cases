package dataDrivenTests;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class DataDrivenPost {
	
	@Test(dataProvider="politiciandataprovider")
	public void postNewPoliticians(String country, String name, String position, String risk, String yob)
	{
		RestAssured.baseURI = "http://ec2-34-250-139-60.eu-west-1.compute.amazonaws.com/peps";
		
		//Request Object
		RequestSpecification httpRequest = RestAssured.given();
		
		//Response Object, send data to server in json
		JSONObject requestParams = new JSONObject();
		
		httpRequest.header("Content-Type","application/json");
		
		//convert request to json format
		httpRequest.body(requestParams.toJSONString());
		
		
		
		requestParams.put("country",country); 
		requestParams.put("name",name);
		requestParams.put("position",position);
		requestParams.put("risk",risk);
		requestParams.put("yob",yob);
		
		
				
				httpRequest.header("Content-Type","application/json");
				
				//convert request to json format
				httpRequest.body(requestParams.toJSONString());
				
				//Response Object
				Response response = httpRequest.request(Method.POST,"");
				
				// Validate Response Code
				int code = response.getStatusCode();
				
				Assert.assertEquals(201, code);
				
				System.out.println("Status Code for Post Request " + code);
				
				//Validate Response Body
				String responseBody = response.getBody().asString();
				
				System.out.println("Response Body" + responseBody);
				
				String message = response.jsonPath().get("message");
				
				Assert.assertEquals("Entity created successfully!", message);
				
				System.out.println("Response message " + message);
				
				//Validate StatusLine
				String statusLine = response.getStatusLine();
				
				System.out.println("Status code for get request is" + statusLine);
			
				
				Assert.assertEquals(statusLine,"HTTP/1.1 201 CREATED");
				
				//Validate ResponseTime
				long responseTime = response.getTime();
				
				System.out.println("Response Time" + responseTime);
						
	}



		@DataProvider(name="politiciandataprovider")
		String[][]	getPoliticianData() throws IOException
	{
		String Path = System.getProperty("user.dir") + "/src/test/java/dataDrivenTests/TestDataPost.xlsx";
		
		int row = XLUtils.getRowCount(Path,"Sheet1");
		int col = XLUtils.getCellCount(Path,"Sheet1",1);
		
		String politiciandata[][] = new String[row][col];
		
		for(int i=1;i<=row;i++) {
			for(int j=0; j<col;j++) {
				politiciandata[i-1][j] = XLUtils.getCellData(Path,"Sheet1", i, j);
				//System.out.println(politiciandata[i-1][j]);
				
			}
		}
		
		return (politiciandata);
	
	
	}
	
}
