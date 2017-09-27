package com.trendy.fw.tools.common.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.trendy.fw.common.bean.StatusBean;
import com.trendy.fw.common.util.StatusKit;

public class InfoTypeConfig {
	// 商品类
	public static final int INFO_TYPE_COMMODITY = 1;// 货品信息
	public static final int INFO_TYPE_PRODUCT = 2;// 商品信息
	public static final int INFO_TYPE_PRODUCT_ITEM = 3;// 商品项信息
	public static final int INFO_TYPE_STORE = 4;// 商店信息
	public static final int INFO_TYPE_CATALOG = 5;// 销售目录
	public static final int INFO_TYPE_PRODUCT_PRICE = 6;// 商品价格

	// 订单类
	public static final int INFO_TYPE_ORDER = 20;// 订单
	public static final int INFO_TYPE_PROMOTION = 21;// 促销
	public static final int INFO_TYPE_PROMOTION_TYPE = 22;// 促销类型
	public static final int INFO_TYPE_RETURN = 23;// 退货
	
	// 物流类
	public static final int INFO_TYPE_INBOUND_EXCEPTION = 50;//疑难件 

	// 文章类
	public static final int INFO_TYPE_WEBSITE = 40;// 网站信息
	public static final int INFO_TYPE_SECTION = 41;// 栏目信息
	public static final int INFO_TYPE_ARTICLE = 42;// 文章信息

	// 忠诚度
	public static final int INFO_TYPE_LOYALTY_EVENT = 60;// 忠诚度事件
	public static final int INFO_TYPE_SCORE_RULE = 61;// 积分规则

	public static List<StatusBean> INFO_TYPE_LIST = new ArrayList<StatusBean>();
	public static HashMap<String, String> INFO_TYPE_MAP = new HashMap<String, String>();

	static {
		new InfoTypeConfig().init();
	}

	private void init() {
		initInfoTypeList();
		initInfoTypeMap();
	}

	private void initInfoTypeList() {
		INFO_TYPE_LIST = new ArrayList<StatusBean>();
		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_COMMODITY, "货品"));
		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_PRODUCT, "商品"));
		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_PRODUCT_ITEM, "商品项"));
		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_STORE, "商店"));
		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_CATALOG, "销售目录"));
		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_PRODUCT_PRICE, "商品价格"));

		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_ORDER, "订单"));
		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_PROMOTION, "促销"));
		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_PROMOTION_TYPE, "促销类型"));
		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_RETURN, "退货"));
		
		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_INBOUND_EXCEPTION, "疑难件 "));

		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_WEBSITE, "网站"));
		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_SECTION, "栏目"));
		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_ARTICLE, "文章"));
		
		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_LOYALTY_EVENT, "忠诚度事件"));
		INFO_TYPE_LIST.add(new StatusBean(INFO_TYPE_SCORE_RULE, "积分规则"));
	}

	private void initInfoTypeMap() {
		INFO_TYPE_MAP = StatusKit.toMap(INFO_TYPE_LIST);
	}
}
