package apiAuth;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BasicAuthExample {
	
	@Test
	public void test1() {
		
       RestAssured.baseURI = "http://httpbin.org/basic-auth/user/passwd";
		
		RequestSpecification request = RestAssured
				                       .given()
				                       .auth()
				                       .basic("user", "passwd");
		
		Response response = request.get();
		
		String resBody = response.getBody().asString();
		
		int resCode = response.statusCode();
		
		System.out.println(resBody);
		
		System.out.println(resCode);
		
	}

}
