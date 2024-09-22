package restAPIBDD;

import java.util.HashMap;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostRequest {
	
	public Logger logger =  Logger.getLogger("PostRequest");
	
	@Test
	public void test1() {
		
		BasicConfigurator.configure();
		logger.info("started the post call using BDD");

      HashMap<String,String> obj = new HashMap<String,String>();
		
		obj.put("id", "10");
		obj.put("name", "Asad");
		obj.put("salary", "9000");
		
		RestAssured.given()
		           .baseUri("http://localhost:3000/employees")
		           .contentType(ContentType.JSON)
		           .accept(ContentType.JSON)
		           .body(obj)
		           .when()
		           .post()
		           .then()
		           .log()
		           .all();
		logger.info("post call ended with---");



		
	}

}
