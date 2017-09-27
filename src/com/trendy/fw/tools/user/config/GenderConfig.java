package com.trendy.fw.tools.user.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.trendy.fw.common.bean.StatusBean;
import com.trendy.fw.common.util.StatusKit;

public class GenderConfig {
	public static final int GENDER_MALE = 1;// 男
	public static final int GENDER_FEMALE = 2;// 女

	public static List<StatusBean> GENDER_LIST = new ArrayList<StatusBean>();
	public static HashMap<String, String> GENDER_MAP = new HashMap<String, String>();

	static {
		new GenderConfig().init();
	}

	private void init() {
		initGenderList();
		initGenderMap();
	}

	private void initGenderList() {
		GENDER_LIST = new ArrayList<StatusBean>();
		GENDER_LIST.add(new StatusBean(GENDER_MALE, "男"));
		GENDER_LIST.add(new StatusBean(GENDER_FEMALE, "女"));
	}

	private void initGenderMap() {
		GENDER_MAP = StatusKit.toMap(GENDER_LIST);
	}
}
