package br.ce.pedronascimento;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {
	
	@Test
	public void testOlaMundo() {
		Response response = request(Method.GET, "http://restapi.wcaquino.me:80/ola");
		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		Assert.assertTrue(response.statusCode() == 200);
		Assert.assertEquals(200, response.statusCode());
		
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
	}
	
	@Test
	public void devoConhecerOutrasFormasRestAssured() {
		Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
		
		get("http://restapi.wcaquino.me/ola").then().statusCode(200);
		
		 given()
		 
		.when()
			.get("http://restapi.wcaquino.me/ola")
		.then()
//			.assertThat()
			.statusCode(200);
		}
	
	@Test
	public void devoConhecerMatchersHamcrest() {
		assertThat("Maria", is("Maria"));
		assertThat(128, is(128));
	}
	
	@Test
	public void devoValidarOBody() {
		 given()
		 
			.when()
				.get("http://restapi.wcaquino.me/ola")
			.then()
//				.assertThat()
				.statusCode(200)
				.body(is("Ola Mundo!"))
				.body(containsString("Mundo"))
				.body(is(not(nullValue())));
		 		
	}
}
