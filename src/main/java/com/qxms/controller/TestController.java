package com.qxms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.jfinal.core.Controller;
import com.qxms.model.Menu;

public class TestController extends Controller {
	
	public void index() {

		render("index.jsp");
	}
	
	public void test(){
		System.out.println(getPara(0));;
		
		
		renderText("ccc");
	}
	
	
	public void gsontest(){
		
		
		
		Gson gson = new Gson();
		ArrayList<String> list = new ArrayList<String>();
		list.add("qqqq");
		list.add("qqqq");
		list.add("qqqq");
		list.add("qqqq");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("aa", "da");
		map.put("aaa", "daa");
		map.put("aaaa", "daaa");
		
		renderText(gson.toJson(map));
		
	}
	
	public static void main(String[] args) {
//		Menu m1 = new Menu();
//		Menu m2 = new Menu();
//		Menu m3 = new Menu();
//		Menu m4 = new Menu();
//		
//		m1.setMenuid(new Integer(1));
//		m2.setMenuid(new Integer(1));
//		m3.setMenuid(new Integer(5));
//		m4.setMenuid(new Integer(3));
//		
//		ArrayList<Menu> a= new ArrayList<Menu>();
//		a.add(m1);a.add(m3);
//		ArrayList<Menu> b= new ArrayList<Menu>();
//		b.add(m2);b.add(m4);
//		
//		System.out.println(a.contains(m4));
		
		for (int i = 5; i >=0; i--) {
			System.out.println(i);
		}
	}
}
