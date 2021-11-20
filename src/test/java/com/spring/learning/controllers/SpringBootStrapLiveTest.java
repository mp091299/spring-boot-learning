package com.spring.learning.controllers;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Assertions;

import com.spring.learning.simpleapplication.models.Book;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SpringBootStrapLiveTest {

	private static final String API_ROOT = "http://localhost:8080/api/books";

	private Book createRandomBook() {
		Book book = new Book();
		book.setTitle(RandomStringUtils.randomAlphabetic(10));
		book.setAuthor(RandomStringUtils.randomAlphabetic(15));
		return book;
	}

	private String createBookAsUri(Book book) {
		Response response = RestAssured.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(book)
				.post(API_ROOT);
		return API_ROOT + "/" + response.jsonPath().get("id");
	}
	
	@Test
	public void whenGetAllBook_thenOk() {
		Response response = RestAssured.get(API_ROOT);
		Assertions.assertEquals(HttpStatus.OK.value(),response.getStatusCode());
	}
	
	@Test
	public void whenGetBooksByTitle_thenOk() {
		Book book = createRandomBook();
		createBookAsUri(book);
		Response response = RestAssured.get(API_ROOT+"/title/"+book.getTitle());
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	    Assertions.assertTrue(response.as(List.class)
	      .size() > 0);
	}
	
	@Test
	public void whenGetCreatedBookById_thenOK() {
	    Book book = createRandomBook();
	    String location = createBookAsUri(book);
	    Response response = RestAssured.get(location);
	    
	    Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	    Assertions.assertEquals(book.getTitle(), response.jsonPath()
	      .get("title"));
	}

	@Test
	public void whenGetNotExistBookById_thenNotFound() {
	    Response response = RestAssured.get(API_ROOT + "/" + RandomStringUtils.randomNumeric(4));
	    
	    Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}
	
	@Test
	public void whenCreateNewBook_thenCreated() {
	    Book book = createRandomBook();
	    Response response = RestAssured.given()
	      .contentType(MediaType.APPLICATION_JSON_VALUE)
	      .body(book)
	      .post(API_ROOT);
	    
	    Assertions.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
	}

	@Test
	public void whenInvalidBook_thenError() {
	    Book book = createRandomBook();
	    book.setAuthor(null);
	    Response response = RestAssured.given()
	      .contentType(MediaType.APPLICATION_JSON_VALUE)
	      .body(book)
	      .post(API_ROOT);
	    
	    Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
	}
	
	@Test
	public void whenUpdateCreatedBook_thenUpdated() {
	    Book book = createRandomBook();
	    String location = createBookAsUri(book);
	    book.setId(Long.parseLong(location.split("api/books/")[1]));
	    book.setAuthor("newAuthor");
	    Response response = RestAssured.given()
	      .contentType(MediaType.APPLICATION_JSON_VALUE)
	      .body(book)
	      .put(location);
	    
	    Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

	    response = RestAssured.get(location);
	    
	    Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	    Assertions.assertEquals("newAuthor", response.jsonPath()
	      .get("author"));
	}
	
	@Test
	public void whenDeleteCreatedBook_thenOk() {
	    Book book = createRandomBook();
	    String location = createBookAsUri(book);
	    Response response = RestAssured.delete(location);
	    
	    Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

	    response = RestAssured.get(location);
	    Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}
}
