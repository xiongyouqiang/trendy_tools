package com.trendy.fw.tools.order.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.trendy.fw.common.bean.StatusBean;
import com.trendy.fw.common.util.StatusKit;

public class DeliveryStatusConfig {
	// 发货方类型
	public static final String DELYS_CREATED = "CREATED";// 创建
	public static final String DELYS_ALLOCATED = "ALLOCATED";// 分配
	public static final String DELYS_SENT = "SENT";// 发货
	public static final String DELYS_REJECTED = "REJECTED";// 拒绝
	public static final String DELYS_WAIT_TIMEOUT = "WAIT_TIMEOUT";// 等待超时
	public static final String DELYS_FAILED = "FAILED";// 失败
	public static final String DELYS_CANCELLED = "CANCELLED";// 取消
	public static final String DELYS_FINISHED = "FINISHED";// 完成
	public static final String DELYS_PRINTED = "PRINTED";// 已打印

	public static List<StatusBean> DELIVERY_STATUS_LIST = new ArrayList<StatusBean>();
	public static HashMap<String, String> DELIVERY_STATUS_MAP = new HashMap<String, String>();

	static {
		new DeliveryStatusConfig().init();
	}

	private void init() {
		initDeliveryStatusList();
		initDeliveryStatusMap();
	}

	private void initDeliveryStatusList() {
		DELIVERY_STATUS_LIST = new ArrayList<StatusBean>();
		DELIVERY_STATUS_LIST.add(new StatusBean(DELYS_CREATED, "创建"));
		DELIVERY_STATUS_LIST.add(new StatusBean(DELYS_ALLOCATED, "分配"));
		DELIVERY_STATUS_LIST.add(new StatusBean(DELYS_SENT, "发货"));
		DELIVERY_STATUS_LIST.add(new StatusBean(DELYS_REJECTED, "拒绝"));
		DELIVERY_STATUS_LIST.add(new StatusBean(DELYS_WAIT_TIMEOUT, "等待超时"));
		DELIVERY_STATUS_LIST.add(new StatusBean(DELYS_FAILED, "失败"));
		DELIVERY_STATUS_LIST.add(new StatusBean(DELYS_CANCELLED, "取消"));
		DELIVERY_STATUS_LIST.add(new StatusBean(DELYS_FINISHED, "完成"));
		DELIVERY_STATUS_LIST.add(new StatusBean(DELYS_PRINTED, "已打印"));
	}

	private void initDeliveryStatusMap() {
		DELIVERY_STATUS_MAP = StatusKit.toMap(DELIVERY_STATUS_LIST);
	}
}
