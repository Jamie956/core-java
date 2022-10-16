package com.example;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.junit.Test;

public class Object2StrTest {
	public static class Object01 {
		private String desc;

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

	}

	public static class Object02 {
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		private Date date;

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public Object02(Date date) {
			this.date = date;
		}
	}

	@JsonFilter("myFilter")
	public static class Object03 {
		public int id;
		public String name;
		public Object03(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
	}

	@Test
	public void object2str() throws IOException {
		Object01 o = new Object01();
		o.setDesc("o1 desc");
		String result = new ObjectMapper().writeValueAsString(o);
		System.out.println(result);
	}

	@Test
	public void str2object() throws IOException {
		Object01 result = new ObjectMapper().readerFor(Object01.class).readValue("{\"desc\":\"o1 desc\"}");
		System.out.println(result);
	}

	@Test
	public void jsonDateFormat() throws JsonProcessingException {
		String result = new ObjectMapper().writeValueAsString(new Object02(new Date()));
		System.out.println(result);
	}

	@Test
	public void filterJsonField() throws JsonProcessingException {
		Object03 bean = new Object03(1, "a");
		FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", SimpleBeanPropertyFilter.filterOutAllExcept("name"));

		String result = new ObjectMapper().writer(filters).writeValueAsString(bean);
		System.out.println(result);
	}
}
