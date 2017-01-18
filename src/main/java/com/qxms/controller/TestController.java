package com.qxms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.jfinal.core.Controller;
import com.qxms.model.Menu;
import com.qxms.service.sys.MenuService;

public class TestController extends Controller {

	MenuService menuService = new MenuService();

	public void index() {

		renderText("index");
	}

	public void test() {
		List<Menu> mlist = menuService.findAllShowMenu();
		List<Menu> list = new ArrayList<Menu>();
		MenuService.sortList(list, mlist, 1, true);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		
		renderText("ccc");
	}

	public void gsontest() {

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

		renderText(gson.toJson(list));

	}

	public static void main(String[] args) {
		String  s= "    http://blog.sina.com.cn/s/blog_4550f3ca0100qrsd.html";
		
		System.out.println(StringUtils.indexOf( s,"http://"));
		
		
		
	}
}
