package com.trendy.fw.tools.common.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.trendy.fw.common.bean.StatusBean;
import com.trendy.fw.common.util.StatusKit;

public class SourceConfig {
	// 来源
	public static final int SF_ONLINE = 1;// 线上
	public static final int SF_OFFLINE = 2;// 线下

	// 二进制位置
	protected static final int SF_POSE_ONLINE = 0;// 线上
	protected static final int SF_POSE_OFFLINE = 1;// 线下

	public static List<StatusBean> SOURCE_FROM_LIST = new ArrayList<StatusBean>();
	public static HashMap<String, String> SOURCE_FROM_MAP = new HashMap<String, String>();

	static {
		new SourceConfig().init();
	}

	private void init() {
		initSourceFromList();
		initSourceFromMap();
	}

	private void initSourceFromList() {
		SOURCE_FROM_LIST = new ArrayList<StatusBean>();
		SOURCE_FROM_LIST.add(new StatusBean(SF_ONLINE, "线上"));
		SOURCE_FROM_LIST.add(new StatusBean(SF_OFFLINE, "线下"));
	}

	private void initSourceFromMap() {
		SOURCE_FROM_MAP = StatusKit.toMap(SOURCE_FROM_LIST);
	}
}
