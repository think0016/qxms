package com.qxms.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("qx_department", "did", Department.class);
		arp.addMapping("qx_menu", "menuid", Menu.class);
		arp.addMapping("qx_role", "roleid", Role.class);
		arp.addMapping("qx_role_menu", "rmid", RoleMenu.class);
		arp.addMapping("qx_user", "uid", User.class);
		arp.addMapping("qx_user_role", "urid", UserRole.class);
	}
	
}

