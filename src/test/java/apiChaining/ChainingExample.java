package apiChaining;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ChainingExample {

	String orderIdVal;

	@Test
	public void test1() throws IOException {

		createOrdeId();
		updateJson();
		placeOrder();

	}

	public void createOrdeId() {
		// First API call to generate the Order ID
		RestAssured.baseURI = "http://httpbin.org/uuid";
		RequestSpecification request = RestAssured.given();
		Response response = request.get();

		String resBody = response.getBody().asString();
		int resCode = response.statusCode();
		System.out.println(resBody);
		System.out.println(resCode);

		JsonPath json = response.jsonPath();
		orderIdVal = json.get("uuid");
		System.out.println(orderIdVal);

	}

	public void updateJson() throws IOException {

		// Update the order id on the order.json
		Gson gson = new Gson();
		FileReader reader = new FileReader("order.json");
		JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

		// Update the JSON object
		jsonObject.addProperty("orderId", orderIdVal);

		// Write the updated JSON back to the file
		FileWriter writer = new FileWriter("order.json");
		gson.toJson(jsonObject, writer);
		writer.close();

		System.out.println("JSON file updated successfully.");
	}

	public void placeOrder() throws IOException {

		// Second API Call

		byte[] file = Files.readAllBytes(Paths.get("order.json"));
		RestAssured.baseURI = "http://httpbin.org/post";
		RequestSpecification request1 = RestAssured.given();

		Response response1 = request1.contentType(ContentType.JSON).accept(ContentType.JSON).body(file).post();
		System.out.println("The response code is " + response1.getStatusCode());
		System.out.println("The response body is " + response1.body().asString());

	}

}
