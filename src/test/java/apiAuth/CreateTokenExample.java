package apiAuth;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateTokenExample {
	
	@Test
	public void test1() {
		String token = generateToken();
		getStudent(token);
		
	}
	
	public String generateToken() {
		
		Response response = RestAssured.given()
								.baseUri("http://localhost:8088/oauth/token")
								.auth()
								.basic("rest-assured", "password")
								.contentType("application/x-www-form-urlencoded")
								.formParam("grant_type", "password")
								.formParam("username", "onlyfullstack")
			                    .formParam("password", "secret")
			                    .when()
			                    .post();
		
		JsonPath json = response.jsonPath();
		String token = json.get("access_token");
		System.out.println("Token values is " + token);
		return token;


		
	}
	
	public void getStudent(String token) {
		
		RestAssured.given()
			.baseUri("http://localhost:8088/students/2")
			.auth()
			.oauth2(token)
			.when()
			.get()
			.then()
			.statusCode(200)
			.log()
			.all();

		
	}

}
