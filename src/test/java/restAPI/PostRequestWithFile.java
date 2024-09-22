package restAPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithFile {
	
	@Test
	public void test1() throws IOException {
		
		byte[] file = Files.readAllBytes(Paths.get("data.json"));
		
		RestAssured.baseURI = "http://localhost:3000/employees";

		RequestSpecification request = RestAssured.given();

		Response response = request.contentType(ContentType.JSON)
									.accept(ContentType.JSON)
									.body(file)
									.post();
		
        System.out.println("The response code is " + response.getStatusCode());
		
		System.out.println("The response body is " + response.body().asString());

		
	}

}
