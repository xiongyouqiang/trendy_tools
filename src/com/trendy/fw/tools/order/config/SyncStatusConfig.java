package com.trendy.fw.tools.order.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.trendy.fw.common.bean.StatusBean;
import com.trendy.fw.common.util.StatusKit;

public class SyncStatusConfig {
	public static final String SYNCS_NONE = "NONE";// 未同步
	public static final String SYNCS_SENT = "SENT";// 已同步
	public static final String SYNCS_SUCCESS = "SUCCESS";// 同步成功
	public static final String SYNCS_FAIL = "FAIL";// 同步失败

	public static List<StatusBean> SYNC_STATUS_LIST = new ArrayList<StatusBean>();
	public static HashMap<String, String> SYNC_STATUS_MAP = new HashMap<String, String>();

	static {
		new SyncStatusConfig().init();
	}

	private void init() {
		initSyncStatusList();
		initSyncStatusMap();
	}

	private void initSyncStatusList() {
		SYNC_STATUS_LIST = new ArrayList<StatusBean>();
		SYNC_STATUS_LIST.add(new StatusBean(SYNCS_NONE, "未同步"));
		SYNC_STATUS_LIST.add(new StatusBean(SYNCS_SENT, "已同步"));
		SYNC_STATUS_LIST.add(new StatusBean(SYNCS_SUCCESS, "同步成功"));
		SYNC_STATUS_LIST.add(new StatusBean(SYNCS_FAIL, "同步失败"));
	}

	private void initSyncStatusMap() {
		SYNC_STATUS_MAP = StatusKit.toMap(SYNC_STATUS_LIST);
	}
}
