package com.trendy.fw.tools.order.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.trendy.fw.common.bean.StatusBean;
import com.trendy.fw.common.util.StatusKit;

public class DeliveryMethodConfig {
	public static final int DM_ONLINE = 1;// 线上发货
	public static final int DM_OFFLINE = 2;// 线下门店发货
	public static final int DM_IN_SHOP = 4; // 门店自提
	public static final int DM_SHOP_POST = 8; // 本门店快递

	// 二进制位置
	protected static final int DM_POSE_ONLINE = 0;// 线上发货
	protected static final int DM_POSE_OFFLINE = 1;// 线下门店发货
	protected static final int DM_POSE_IN_SHOP = 2;// 门店自提
	protected static final int DM_POSE_SHOP_POST = 3;// 本门店快递

	public static List<StatusBean> DELIVERY_METHOD_LIST = new ArrayList<StatusBean>();
	public static HashMap<String, String> DELIVERY_METHOD_MAP = new HashMap<String, String>();

	static {
		new DeliveryMethodConfig().init();
	}

	private void init() {
		initDeliveryMethodList();
		initDeliveryMethodMap();
	}

	private void initDeliveryMethodList() {
		DELIVERY_METHOD_LIST = new ArrayList<StatusBean>();
		DELIVERY_METHOD_LIST.add(new StatusBean(DM_ONLINE, "线上发货"));
		DELIVERY_METHOD_LIST.add(new StatusBean(DM_OFFLINE, "线下门店发货"));
		DELIVERY_METHOD_LIST.add(new StatusBean(DM_IN_SHOP, "门店自提"));
		DELIVERY_METHOD_LIST.add(new StatusBean(DM_SHOP_POST, "本门店快递"));
	}

	private void initDeliveryMethodMap() {
		DELIVERY_METHOD_MAP = StatusKit.toMap(DELIVERY_METHOD_LIST);
	}
}
