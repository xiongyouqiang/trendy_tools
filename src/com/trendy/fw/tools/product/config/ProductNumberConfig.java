package com.trendy.fw.tools.product.config;

import java.util.HashMap;

public class ProductNumberConfig {
	// 编码类型
	public final static int NT_NONE = 0;
	public final static int NT_COMMODITY = 1;
	public final static int NT_PRODUCT = 2;
	public final static int NT_PRODUCT_ITEM = 3;

	// 品牌标签
	public final static String BS_OCHIRLY = "1";
	public final static String BS_FIVEPLUS = "2";
	public final static String BS_TRENDIANO = "3";
	public final static String BS_OCHIRLYKIDS = "5";
	public final static String BS_OCHIRLYKIDS_NEW = "H";
	public final static String BS_COVENGARDEN = "C";
	public final static String BS_MISSSIXTY = "6";
	public final static String BS_ENERGIE = "7";
	public final static String BS_KILLAH = "8";
	public final static String BS_WAXY = "X";
	public final static String BS_TRENDIANOWOMEN = "W";
	public final static String BS_SUPERDRY = "S";
	public final static String BS_HACKETT = "VH";
	public final static String BS_DENHAM = "UDH";
	public final static String BS_10CC = "9";

	// 品牌代码
	public final static String BC_OCHIRLY = "OCHIRLY";
	public final static String BC_FIVEPLUS = "FIVEPLUS";
	public final static String BC_TRENDIANO = "TRENDIANO";
	public final static String BC_OCHIRLYKIDS = "OCHIRLYKIDS";
	public final static String BC_COVENGARDEN = "COVENGARDEN";
	public final static String BC_MISSSIXTY = "MISSSIXTY";
	public final static String BC_ENERGIE = "ENERGIE";
	public final static String BC_KILLAH = "KILLAH";
	public final static String BC_WAXY = "WAXY";
//	public final static String BC_TRENDIANOWOMEN = "TRENDIANOWOMEN";
	public final static String BC_SUPERDRY = "SUPERDRY";
	public final static String BC_HACKETT = "HACKETT";
	public final static String BC_DENHAM = "DENHAM";
	public final static String BC_10CC = "10CC";

	// 品牌标签：代码Map
	public static HashMap<String, String> BRAND_CODE_MAP = new HashMap<String, String>();
	// 品牌代码：标签Map
	public static HashMap<String, String> BRAND_SIGN_MAP = new HashMap<String, String>();

	static {
		new ProductNumberConfig().init();
	}

	private void init() {
		initBrandCodeMap();
		initBrandSignMap();
	}

	// 初始化品牌标签：代码Map
	private void initBrandCodeMap() {
		BRAND_CODE_MAP = new HashMap<String, String>();
		BRAND_CODE_MAP.put(BS_OCHIRLY, BC_OCHIRLY);
		BRAND_CODE_MAP.put(BS_FIVEPLUS, BC_FIVEPLUS);
		BRAND_CODE_MAP.put(BS_TRENDIANO, BC_TRENDIANO);
		BRAND_CODE_MAP.put(BS_TRENDIANOWOMEN, BC_TRENDIANO);
		BRAND_CODE_MAP.put(BS_OCHIRLYKIDS, BC_OCHIRLYKIDS);
		BRAND_CODE_MAP.put(BS_OCHIRLYKIDS_NEW, BC_OCHIRLYKIDS);
		BRAND_CODE_MAP.put(BS_COVENGARDEN, BC_COVENGARDEN);
		BRAND_CODE_MAP.put(BS_MISSSIXTY, BC_MISSSIXTY);
		BRAND_CODE_MAP.put(BS_ENERGIE, BC_ENERGIE);
		BRAND_CODE_MAP.put(BS_KILLAH, BC_KILLAH);
		BRAND_CODE_MAP.put(BS_WAXY, BC_WAXY);
		BRAND_CODE_MAP.put(BS_SUPERDRY, BC_SUPERDRY);
		BRAND_CODE_MAP.put(BS_HACKETT, BC_HACKETT);
		BRAND_CODE_MAP.put(BS_DENHAM, BC_DENHAM);
		BRAND_CODE_MAP.put(BS_10CC, BC_10CC);
	}

	// 初始化品牌代码：标签Map
	private void initBrandSignMap() {
		BRAND_SIGN_MAP = new HashMap<String, String>();
		BRAND_SIGN_MAP.put(BC_OCHIRLY, BS_OCHIRLY);
		BRAND_SIGN_MAP.put(BC_FIVEPLUS, BS_FIVEPLUS);
		BRAND_SIGN_MAP.put(BC_TRENDIANO, BS_TRENDIANO + "," + BS_TRENDIANOWOMEN);
		BRAND_SIGN_MAP.put(BC_OCHIRLYKIDS, BS_OCHIRLYKIDS + "," + BS_OCHIRLYKIDS_NEW);
		BRAND_SIGN_MAP.put(BC_COVENGARDEN, BS_COVENGARDEN);		
		BRAND_SIGN_MAP.put(BC_MISSSIXTY, BS_MISSSIXTY);
		BRAND_SIGN_MAP.put(BC_ENERGIE, BS_ENERGIE);
		BRAND_SIGN_MAP.put(BC_KILLAH, BS_KILLAH);
		BRAND_SIGN_MAP.put(BC_WAXY, BS_WAXY);
//		BRAND_SIGN_MAP.put(BC_TRENDIANO, BS_TRENDIANOWOMEN);
		BRAND_SIGN_MAP.put(BC_SUPERDRY, BS_SUPERDRY);
		BRAND_SIGN_MAP.put(BC_HACKETT, BS_HACKETT);
		BRAND_SIGN_MAP.put(BC_DENHAM, BS_DENHAM);
		BRAND_SIGN_MAP.put(BC_10CC, BS_10CC);
	}
}
