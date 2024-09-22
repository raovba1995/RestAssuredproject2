package apiAuth;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BearerTokenExample {
	
	@Test
	public void test1() throws IOException {
		
        byte[] file = Files.readAllBytes(Paths.get("repo.json"));
		
		RestAssured.baseURI = "https://api.github.com/user/repos";

		RequestSpecification request = RestAssured.given()
				                                  .auth()
				                                  .oauth2("ABCD");

		Response response = request.contentType(ContentType.JSON)
									.accept(ContentType.JSON)
									.body(file)
									.post();
		
        System.out.println("The response code is " + response.getStatusCode());
		
		System.out.println("The response body is " + response.body().asString());
		
	}

}
