package com.trendy.fw.tools.currency.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.trendy.fw.common.bean.StatusBean;
import com.trendy.fw.common.util.StatusKit;

public class CurrencyConfig {
	public static final String CUR_CNY = "CNY";// 人民币
	public static final String CUR_USD = "USD";// 美元
	public static final String CUR_EUR = "EUR";// 欧元
	public static final String CUR_PNT = "PNT";// 积分

	public static List<StatusBean> CURRENCY_LIST = new ArrayList<StatusBean>();
	public static HashMap<String, String> CURRENCY_MAP = new HashMap<String, String>();

	static {
		new CurrencyConfig().init();
	}

	private void init() {
		initCurrencyList();
		initCurrencyMap();
	}

	private void initCurrencyList() {
		CURRENCY_LIST = new ArrayList<StatusBean>();
		CURRENCY_LIST.add(new StatusBean(CUR_CNY, "CNY"));
		CURRENCY_LIST.add(new StatusBean(CUR_USD, "USD"));
		CURRENCY_LIST.add(new StatusBean(CUR_EUR, "EUR"));
		CURRENCY_LIST.add(new StatusBean(CUR_PNT, "PNT"));
	}

	private void initCurrencyMap() {
		CURRENCY_MAP = StatusKit.toMap(CURRENCY_LIST);
	}
}
