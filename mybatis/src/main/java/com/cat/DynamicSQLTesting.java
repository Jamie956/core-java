package com.cat;

import com.cat.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DynamicSQLTesting {
	public static SqlSession session = null;
	
	static {
		try {
			SqlSessionFactory sf = new SqlSessionFactoryBuilder()
					.build(Resources.getResourceAsStream("mybatis-config.xml"));
			session = sf.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		listByIf();
		listBywhere();
	}

	// list by if tag
	public static void listByIf() {
		System.out.println("查询所有的");
		List<Product> ps = session.selectList("listProduct2");
		for (Product p : ps) {
			System.out.println(p);
		}

		System.out.println("模糊查询");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "a");
		List<Product> ps2 = session.selectList("listProduct2", params);
		for (Product p : ps2) {
			System.out.println(p);
		}
	}

	// list by where tag
	public static void listBywhere() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "a");
		params.put("price", "10");
		List<Product> ps2 = session.selectList("listProduct3", params);
		for (Product p : ps2) {
			System.out.println(p);
		}
	}

	// update by tag set
	public static void updateBySet() {
		Product p = new Product();
		p.setId(6);
		p.setName("product xf");
		p.setPrice(99.99f);
		session.update("updateProduct", p);
	}

	// list by trim tag using where
	public static void listByTrimWhere() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", "a");
		params.put("price", "10");
		List<Product> ps2 = session.selectList("listProduct4", params);
		for (Product p : ps2) {
			System.out.println(p);
		}
	}

	// update by trim tag using set
	public static void updateByTrimSet() {
		Product p = new Product();
		p.setId(6);
		p.setName("product bf");
		p.setPrice(9.99f);
		session.update("updateProduct2", p);
	}

	// list by when-otherwise tag
	public static void listByWhenOtherwise() {
		Map<String, Object> params = new HashMap<String, Object>();
		// params.put("name","a");
		// params.put("price","10");
		List<Product> ps = session.selectList("listProduct5", params);
		for (Product p : ps) {
			System.out.println(p);
		}
	}

	// list by foreach
	public static void listByForeach() {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(3);
		ids.add(5);

		List<Product> ps = session.selectList("listProduct6", ids);
		for (Product p : ps) {
			System.out.println(p);
		}
	}

}
