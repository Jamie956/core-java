package com.example;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.junit.Assert;
import org.junit.Test;

/**
 * test java object to json str and json str to java object
 * @author jamie
 */
public class JavaObject22jsonStrTest {
	public static class Object01 {
		public String desc;
	}
	@Test
	public void javaObject2jsonStrTest() throws IOException {
		Object01 o = new Object01();
		o.desc = "123";
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"desc\":\"123\"}", result);
	}
	@Test
	public void jsonStr2javaObjectTest() throws IOException {
		Object01 o = new ObjectMapper().readerFor(Object01.class).readValue("{\"desc\":\"123\"}");
		Assert.assertEquals("123", o.desc);
	}


	public static class Object02 {
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		public Date date;
	}
	@Test
	public void jsonDateFormatTest() throws JsonProcessingException, ParseException {
		Object02 o = new Object02();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		o.date = sdf.parse("02-02-2022");
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"date\":\"01-02-2022\"}", result);
	}


	@JsonFilter("myFilter")
	public static class Object03 {
		public int id;
		public String name;
	}
	@Test
	public void filterJsonField() throws JsonProcessingException {
		Object03 o = new Object03();
		o.id = 1;
		o.name = "a";
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("myFilter", SimpleBeanPropertyFilter.filterOutAllExcept("name"));

		String result = new ObjectMapper().writer(filters).writeValueAsString(o);
		Assert.assertEquals("{\"name\":\"a\"}", result);
	}


	public static class Object31 {
		public int id;
		public String name;
		@JsonCreator
		public Object31(@JsonProperty("id") int id, @JsonProperty("theName") String name) {
			this.id = id;
			this.name = name;
		}
	}
	@Test
	public void jsonStr2JavaObjectWithNameTest() throws IOException {
		Object31 o = new ObjectMapper().readerFor(Object31.class).readValue("{\"id\":1,\"theName\":\"a\"}");
		Assert.assertEquals(1, o.id);
		Assert.assertEquals("a", o.name);
	}


	public static class Object32 {
		@JacksonInject
		public int id;

		public String name;
	}
	@Test
	public void jsonStr2JavaObjectWithInjectTest() throws IOException {
		InjectableValues inject = new InjectableValues.Std().addValue(int.class, 1);
		Object32 o = new ObjectMapper().reader(inject).forType(Object32.class).readValue("{\"name\":\"a\"}");
		Assert.assertEquals(1, o.id);
		Assert.assertEquals("a", o.name);
	}
}
