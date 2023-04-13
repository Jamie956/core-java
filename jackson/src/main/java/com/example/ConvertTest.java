package com.example;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.junit.Assert;
import org.junit.Test;

public class ConvertTest {
	public static class Object01 {
		public String a;
	}
	// java 对象转 json string
	@Test
	public void javaObject2jsonStrTest() throws IOException {
		Object01 o = new Object01();
		o.a = "123";
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"a\":\"123\"}", result);
	}
	// json string 转 java object
	@Test
	public void jsonStr2javaObjectTest() throws IOException {
		Object01 o = new ObjectMapper().readerFor(Object01.class).readValue("{\"a\":\"123\"}");
		Assert.assertEquals("123", o.a);
	}

	public static class Object02 {
		// object date 按格式转成 json string
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		public Date date;
	}
	@Test
	public void jsonDateFormatTest() throws JsonProcessingException, ParseException {
		Object02 o = new Object02();
		o.date = new Date(1419013800000L);
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"date\":\"19-12-2014\"}", result);
	}

	// 使用json key过滤器
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
		// mapping json key 的别名
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
		// 注入字段到 json
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

	public static class Object011 {
		private Map<String, String> properties;
		// 展开map k-v，不使用字段名
		@JsonAnyGetter
		public Map<String, String> getProperties() {
			return properties;
		}

		public void setProperties(Map<String, String> properties) {
			this.properties = properties;
		}
	}
	@Test
	public void flatObjectMapTest() throws JsonProcessingException {
		Map<String, String> map = new HashMap<>();
		map.put("k1", "v1");
		map.put("k2", "v2");
		Object011 o = new Object011();
		o.setProperties(map);

		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"k1\":\"v1\",\"k2\":\"v2\"}", result);
	}

	// key 排序
	@JsonPropertyOrder({"name", "id"})
	public static class Object022 {
		public int id;
		public String name;
	}
	@Test
	public void fieldsOrderTest() throws JsonProcessingException {
		Object022 o = new Object022();
		o.id = 1;
		o.name = "a";
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"name\":\"a\",\"id\":1}", result);
	}

	public static class Object033 {
		// 字符串字段识别为 json
		@JsonRawValue
		public String json;
	}
	@Test
	public void rawJsonFieldTest() throws JsonProcessingException {
		Object033 o = new Object033();
		o.json = "{\"attr\":false}";
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"json\":{\"attr\":false}}", result);
	}

	@JsonRootName(value = "rootName")
	public static class Object04 {
		public int id;
	}
	@Test
	public void jsonWithRootNameTest() throws JsonProcessingException {
		Object04 o = new Object04();
		o.id = 1;
		String result = new ObjectMapper().enable(SerializationFeature.WRAP_ROOT_VALUE)
				.writeValueAsString(o);
		Assert.assertEquals("{\"rootName\":{\"id\":1}}", result);
	}

	// 声明 json string 不转换的 key
	@JsonIgnoreProperties({ "id" })
	public static class Object21 {
		public int id;
		public String name;
	}
	@Test
	public void ignoreObjectFieldsTest() throws JsonProcessingException {
		Object21 o = new Object21();
		o.id = 1;
		o.name = "a";
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"name\":\"a\"}", result);
	}

	public static class Object22 {
		// 声明的字段不会转换到 json string
		@JsonIgnore
		public int id;
		public String name;
	}
	@Test
	public void ignoreObjectField() throws JsonProcessingException {
		Object22 o = new Object22();
		o.id = 1;
		o.name = "a";
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"name\":\"a\"}", result);
	}

	// 只转换非空字段
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Object23 {
		public int id;
		public String name;
	}
	@Test
	public void hiddenNullField() throws JsonProcessingException {
		Object23 o = new Object23();
		o.id = 1;
		o.name = null;
		String result = new ObjectMapper().writeValueAsString(o);
		Assert.assertEquals("{\"id\":1}", result);
	}
}
