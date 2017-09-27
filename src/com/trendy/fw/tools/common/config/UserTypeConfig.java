package com.trendy.fw.tools.common.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.trendy.fw.common.bean.StatusBean;
import com.trendy.fw.common.util.StatusKit;

public class UserTypeConfig {
	public static final int USER_TYPE_ADMIN = 1;// CMS
	public static final int USER_TYPE_PASSPORT = 0;// PORTAL

	public static List<StatusBean> USER_TYPE_LIST = new ArrayList<StatusBean>();
	public static HashMap<String, String> USER_TYPE_MAP = new HashMap<String, String>();

	static {
		new UserTypeConfig().init();
	}

	private void init() {
		initSourceFromList();
		initSourceFromMap();
	}

	private void initSourceFromList() {
		USER_TYPE_LIST = new ArrayList<StatusBean>();
		USER_TYPE_LIST.add(new StatusBean(USER_TYPE_ADMIN, "CMS"));
		USER_TYPE_LIST.add(new StatusBean(USER_TYPE_PASSPORT, "官网"));
	}

	private void initSourceFromMap() {
		USER_TYPE_MAP = StatusKit.toMap(USER_TYPE_LIST);
	}


}
