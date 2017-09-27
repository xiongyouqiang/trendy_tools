package com.trendy.fw.tools.order.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.trendy.fw.common.bean.StatusBean;
import com.trendy.fw.common.util.StatusKit;

public class ConsignerTypeConfig {
	// 发货方类型
	public static final int CT_WAREHOUSE = 1;// 仓库
	public static final int CT_SHOP = 2;// 商店

	public static List<StatusBean> CONSIGNER_TYPE_LIST = new ArrayList<StatusBean>();
	public static HashMap<String, String> CONSIGNER_TYPE_MAP = new HashMap<String, String>();

	static {
		new ConsignerTypeConfig().init();
	}

	private void init() {
		initConsignerTypeList();
		initConsignerTypeMap();
	}

	private void initConsignerTypeList() {
		CONSIGNER_TYPE_LIST = new ArrayList<StatusBean>();
		CONSIGNER_TYPE_LIST.add(new StatusBean(CT_WAREHOUSE, "仓库"));
		CONSIGNER_TYPE_LIST.add(new StatusBean(CT_SHOP, "商店"));
	}

	private void initConsignerTypeMap() {
		CONSIGNER_TYPE_MAP = StatusKit.toMap(CONSIGNER_TYPE_LIST);
	}
}
