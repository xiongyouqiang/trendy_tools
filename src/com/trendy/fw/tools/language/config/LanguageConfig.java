package com.trendy.fw.tools.language.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.trendy.fw.common.bean.StatusBean;
import com.trendy.fw.common.util.StatusKit;

public class LanguageConfig {
	public static final String LANG_CN = "CN";// 简体中文
	public static final String LANG_EN = "EN";// 英文
	public static final String LANG_HK = "HK";// 香港

	public static List<StatusBean> LANGUAGE_LIST = new ArrayList<StatusBean>();
	public static HashMap<String, String> LANGUAGE_MAP = new HashMap<String, String>();

	static {
		new LanguageConfig().init();
	}

	private void init() {
		initLanguageList();
		initLanguageMap();
	}

	private void initLanguageList() {
		LANGUAGE_LIST = new ArrayList<StatusBean>();
		LANGUAGE_LIST.add(new StatusBean(LANG_CN, "中文"));
		LANGUAGE_LIST.add(new StatusBean(LANG_EN, "英文"));
		LANGUAGE_LIST.add(new StatusBean(LANG_HK, "繁体"));
	}

	private void initLanguageMap() {
		LANGUAGE_MAP = StatusKit.toMap(LANGUAGE_LIST);
	}
}
