package restAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetReqWithParameters {

	@Test
	public void test1() {

		RestAssured.baseURI = "https://reqres.in/api/users";

		RequestSpecification request = RestAssured.given();

		Response response = request.get("/3");

		String resBody = response.getBody().asString();

		int resCode = response.statusCode();

		System.out.println(resBody);

		System.out.println(resCode);
		
		Assert.assertEquals(resCode, 200);
		Assert.assertTrue(resBody.contains("Emma"));
		
		JsonPath json = response.jsonPath();
		String firstName = json.get("data.first_name");
		System.out.println(firstName);
		Assert.assertEquals(firstName, "Emma");

	}

}
