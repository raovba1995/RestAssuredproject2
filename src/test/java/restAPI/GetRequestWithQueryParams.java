package restAPI;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestWithQueryParams {
	
	@Test
	public void test1() {
		
		HashMap<String,String> params = new HashMap<String,String>();
		
		params.put("CUSTOMER_ID", "68195");
		params.put("Account_No", "1");
		params.put("PASSWORD", "1234!");
		
		RestAssured.baseURI = "http://demo.guru99.com/V4/sinkministatement.php";
		RequestSpecification request = RestAssured.given();
		Response response = request.queryParams(params).get();
		
		String resBody = response.getBody().asString();
		int resCode = response.statusCode();
		System.out.println(resBody);
		System.out.println(resCode);
		



		
	}

}
