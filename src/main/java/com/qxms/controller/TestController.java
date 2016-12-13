package com.qxms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.jfinal.core.Controller;

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
}
