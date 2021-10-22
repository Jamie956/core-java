package com.jamie;

import lombok.Data;

@Data
public class User {
	private String name;
	private int age = 10;

	public void setName(String name) {
		this.name = name;
	}
}