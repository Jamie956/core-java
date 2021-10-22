package com.jamie.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Book {
	private Author author;
	@Autowired
	public void setAuthor(Author author) {
		this.author = author;
	}

	public Book() {
		System.out.println("New Book");
	}
	
	@Override
	public String toString() {
		return "Book [author=" + author + "]";
	}
}