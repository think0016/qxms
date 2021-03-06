package com.qxms.model;

import org.apache.commons.lang3.StringUtils;

import com.jfinal.kit.PropKit;
import com.qxms.model.base.BaseMenu;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class Menu extends BaseMenu<Menu> {
	public static final Menu dao = new Menu();

	@Override
	public String toString() {
		return "PID:" + getParentId().toString() + "    ID:" + getMenuid().toString();
	}

	public String getTrueHref() {
		// StringUtils.
		String url = getHref();

		if (StringUtils.indexOf(url, "http://") != -1 || StringUtils.indexOf(url, "https://") != -1) {

		}else{
			url = PropKit.get("rooturl") + url;
		}
		return url;
	}
}
