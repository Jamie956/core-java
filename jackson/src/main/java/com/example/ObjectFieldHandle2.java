package com.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class ObjectFieldHandle2 {
	@JsonIgnoreProperties({ "id" })
	public static class Object21 {
		public int id;
		public String name;
		public Object21(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
	}

	public static class Object22 {
		@JsonIgnore
		public int id;
		public String name;
		public Object22(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Object23 {
		public int id;
		public String name;
		public Object23(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
	}

	@Test
	public void ignoreObjectFields() throws JsonProcessingException {
		Object21 o = new Object21(1, "a");

		String result = new ObjectMapper().writeValueAsString(o);
		System.out.println(result);
	}

	@Test
	public void ignoreObjectField() throws JsonProcessingException {
		Object22 o = new Object22(1, "a");

		String result = new ObjectMapper().writeValueAsString(o);
		System.out.println(result);
	}

	@Test
	public void hiddenNullField() throws JsonProcessingException {
		String result = new ObjectMapper().writeValueAsString(new Object23(1, null));
		System.out.println(result);
	}
}
