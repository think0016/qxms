package com.qxms.common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.qxms.controller.TestController;
import com.qxms.controller.sys.DepartmentController;
import com.qxms.controller.sys.IndexController;
import com.qxms.controller.sys.MenuController;
import com.qxms.controller.sys.RoleController;
import com.qxms.controller.sys.UserController;
import com.qxms.interceptor.common.CommonHandler;
import com.qxms.interceptor.common.MenuHandler;
import com.qxms.interceptor.common.VerifyLoginInterceptor;
import com.qxms.model._MappingKit;

/**
 * API引导式配置
 */
public class DemoConfig extends JFinalConfig {
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("commoncfg.properties");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		//me.setViewType(ViewType.JSP);		
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", IndexController.class, "/index");	// 第三个参数为该Controller的视图存放路径
		me.add("/user", UserController.class);
		me.add("/department", DepartmentController.class);
		me.add("/role", RoleController.class);
		me.add("/menu", MenuController.class);
		me.add("/test", TestController.class);
	}
	
	public static C3p0Plugin createC3p0Plugin() {
		return new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// ehcache缓存插件
        me.add(new EhCachePlugin());
		
		// 配置C3p0数据库连接池插件
		C3p0Plugin C3p0Plugin = createC3p0Plugin();
		me.add(C3p0Plugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
		me.add(arp);
		//输出SQL语句(测试)
		//arp.setShowSql(true);
		// 所有配置在 MappingKit 中搞定
		_MappingKit.mapping(arp);
		

	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		//me.add(new TestInterceptor());
		me.add(new VerifyLoginInterceptor());
		//me.add(new AuthenticationValidator());
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("ctxroot"));
		//me.add(new MenuHandler());
		me.add(new CommonHandler());
	}
}
