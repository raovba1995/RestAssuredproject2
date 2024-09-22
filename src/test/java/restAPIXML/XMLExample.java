package restAPIXML;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class XMLExample {
	
	@Test
	public void test1() {
		
        RestAssured.baseURI = "https://chercher.tech/sample/api/books.xml";
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.get();
		
		String resBody = response.getBody().asString();
		
		int resCode = response.statusCode();
		
		System.out.println(resBody);
		
		System.out.println(resCode);
		
		NodeChildrenImpl books = response.then().extract().path("bookstore.book.title");
		
		System.out.println("All the books are " + books.toString());
		System.out.println("First book is " + books.get(0));
		System.out.println("Second book is " + books.get(1));
		
		NodeChildrenImpl bookTags = response.then().extract().path("bookstore.book");
		
		for(int i =0; i < bookTags.size(); i++) {
			
			System.out.println(bookTags.get(i).getAttribute("category"));
		}

		
		
	}

}
