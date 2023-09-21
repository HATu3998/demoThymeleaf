package com.example.demoThymeleaf.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoThymeleaf.model.Book;
import com.example.demoThymeleaf.model.RequestBook;



@RestController
@RequestMapping("/book")
public class BookController {
private ConcurrentHashMap<String, Book> books;
public BookController() {
	books=new ConcurrentHashMap<String, Book>();
	books.put("0X-13", new Book("0X-13","Gone with the wind","Cuong",1945));
	books.put("0X-14", new Book("0X-14","The Word","mm",1960));
	books.put("0X-15", new Book("0X-15","Family","Fa",1980));
}
@GetMapping
public List<Book> getBooks(){
	return books.values().stream().toList();
}
@PostMapping
public Book createBook(@RequestBody RequestBook bookRequest) {
	String uuid=UUID.randomUUID().toString();
	Book newBook=new Book(uuid,bookRequest.title(),bookRequest.author(),bookRequest.year());
	books.put(uuid, newBook);
	return newBook;
	
}
@GetMapping(value="/{id}")
public Book getBookById(@PathVariable("id") String id) {
	return books.get(id);
}

@PutMapping(value="/{id}")
public Book updateBook(@PathVariable("id")String id,@RequestBody RequestBook bookRequest) {
	Book updateBook=new Book(id,bookRequest.title(),bookRequest.author(),bookRequest.year());
	books.put(id, updateBook);
	return updateBook;
}
@DeleteMapping(value="/{id}")
public Book deleteBook(@PathVariable("id")String id) {
	Book book=books.remove(id);
	return book;
	
}
}
