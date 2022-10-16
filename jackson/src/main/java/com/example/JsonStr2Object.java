package com.example;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class JsonStr2Object {
	public static class Object31 {
		public int id;
		public String name;
		@JsonCreator
		public Object31(@JsonProperty("id") int id, @JsonProperty("theName") String name) {
			this.id = id;
			this.name = name;
		}
	}

	public static class Object32 {
		@JacksonInject
		public int id;

		public String name;
	}

	public static class Object33 {
		@JsonDeserialize(using = CustomDateDeserializer.class)
		public Date eventDate;
	}

	@Test
	public void jsonStr2JObject() throws IOException {
		Object31 o = new ObjectMapper().readerFor(Object31.class).readValue("{\"id\":1,\"theName\":\"a\"}");
		System.out.println(o);
	}

	@Test
	public void injectField() throws IOException {
		InjectableValues inject = new InjectableValues.Std().addValue(int.class, 1);
		Object32 o = new ObjectMapper().reader(inject).forType(Object32.class).readValue("{\"name\":\"a\"}");
		System.out.println(o);
	}

	@Test
	public void whenDeserializingUsingJsonDeserialize_thenCorrect() throws IOException {
		Object33 o = new ObjectMapper().readerFor(Object33.class).readValue("{\"eventDate\":\"20-12-2014 02:30:00\"}");
		System.out.println(o);
	}
}
