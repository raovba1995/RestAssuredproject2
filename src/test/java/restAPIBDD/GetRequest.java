package restAPIBDD;

import java.util.HashMap;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequest {
	
	@Test
	public void test1() {
		
		RestAssured.given()
				.baseUri("https://reqres.in/api/users")
				.when()
				.get()
				.then()
				.log()
				.all()
				.statusCode(200)
				.body("data.first_name[0]", Matchers.equalTo("George"));	

		
	}
	
	  @Test
      public void TestWithPathParam() {
		
		RestAssured.given()
				.baseUri("https://reqres.in/api/users")
				.when()
				.get("/2")
				.then()
				.log()
				.all()
				.statusCode(200)
		        .body("data.first_name", Matchers.equalTo("Janet"));

		
	}
	  
	  @Test
	  public void TestWithQueryParams() {
		  
		  HashMap<String,String> params = new HashMap<String,String>();
			params.put("CUSTOMER_ID", "68195");
			params.put("Account_No", "1");
			params.put("PASSWORD", "1234!");
			
			RestAssured.given()
			.baseUri("http://demo.guru99.com/V4/sinkministatement.php")
			.queryParams(params)
			.when()
			.get()
			.then()
			.log()
			.all()
			.statusCode(200);
		  
	  }
	  
	    @Test
		public void TestWithBasicAuth() {
				
			RestAssured.given()
					.baseUri("http://httpbin.org/basic-auth/user/passwd")
					.auth()
					.basic("user", "passwd")
					.when()
					.get()
					.then()
					.log()
					.all()
					.statusCode(200);				
		}
	    
	    @Test
		public void TestUsingResponseObject() {
			
			Response response = RestAssured.given()
									.baseUri("https://reqres.in/api/users")
									.when()
									.get();
			
			JsonPath json = response.jsonPath();
			List<String> firstNames = json.get("data.first_name");
			
			for(String fname: firstNames) {
				System.out.println(fname);
			}
			
			System.out.println("Second name is " + firstNames.get(1));
					
		}




}
