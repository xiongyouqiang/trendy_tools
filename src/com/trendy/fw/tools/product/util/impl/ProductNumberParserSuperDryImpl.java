package com.trendy.fw.tools.product.util.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.trendy.fw.tools.product.bean.CommodityNumberBean;
import com.trendy.fw.tools.product.bean.GenericProductNumberBean;
import com.trendy.fw.tools.product.bean.ProductItemNumberBean;
import com.trendy.fw.tools.product.bean.ProductNumberBean;
import com.trendy.fw.tools.product.config.ProductNumberConfig;
import com.trendy.fw.tools.product.util.ProductNumberParser;

public class ProductNumberParserSuperDryImpl extends ProductNumberParser {

	private final static String commodityNumberRule = "(\\w{1})(\\w{1})(\\w{2})(\\w{5,8})";
	private final static String productNumberRule = commodityNumberRule + "(\\w{3})";
	private final static String productItemNumberRule = productNumberRule + "(\\d{1})";
	
	private final static String skuNumberRule = "(\\w{1})(\\w{1})(\\w{2})(\\w{5,12})";

	private static Pattern commodityPattern = Pattern.compile("^" + commodityNumberRule + "$");
	private static Pattern productPattern = Pattern.compile("^" + productNumberRule + "$");
	private static Pattern productItemPattern = Pattern.compile("^" + productItemNumberRule + "$");
	
	private static Pattern skuPattern = Pattern.compile("^" + skuNumberRule + "$");
	
//	private static HashMap<String, String> yearCodeMap = new HashMap<String, String>();
//	
//	static {
//		new ProductNumberParserSuperDryImpl().init();
//	}
//
//	private void init() {
//		initYearCodeMap();
//	}
//	
//	private void initYearCodeMap() {
//		yearCodeMap = new HashMap<String, String>();
//		yearCodeMap.put("K", "15");
//		yearCodeMap.put("L", "15");
//		yearCodeMap.put("M", "16");
//		yearCodeMap.put("N", "16");
//		yearCodeMap.put("O", "17");
//		yearCodeMap.put("P", "17");
//		yearCodeMap.put("Q", "18");
//		yearCodeMap.put("R", "18");
//		yearCodeMap.put("S", "19");
//	}
	
	private String getBrandCode(String brand) {
		return ProductNumberConfig.BRAND_CODE_MAP.get(brand);
	}
	
	private String getCommodityNumber(CommodityNumberBean bean) {
		return bean.getBrand() + bean.getGender() + bean.getProductClass() + bean.getSerialNumber();
	}

	private String getProductNumber(ProductNumberBean bean) {
		return bean.getBrand() + bean.getGender() + bean.getProductClass() +bean.getSerialNumber() + bean.getColor();
	}

	private String getItemNumber(ProductItemNumberBean bean) {
		return bean.getBrand() + bean.getGender() + bean.getProductClass() +bean.getSerialNumber()	+ bean.getColor() + bean.getSize();
	}
	
	@Override
	public CommodityNumberBean parseCommodityNumber(String commodityNumber) {
		CommodityNumberBean bean = new CommodityNumberBean();

		Matcher matcher = commodityPattern.matcher(commodityNumber);
		if (matcher.find()) {
			bean.setValid(true);
			bean.setBrand(matcher.group(1));
			bean.setGender(matcher.group(2));
			bean.setProductClass(matcher.group(3));
//			bean.setYearCode(matcher.group(4));
//			bean.setYear(yearCodeMap.get(matcher.group(4)));
			bean.setSerialNumber(matcher.group(4));

			bean.setBrandCode(getBrandCode(bean.getBrand()));
			bean.setCommodityNumber(getCommodityNumber(bean));
		}

		return bean;
	}

	@Override
	public ProductNumberBean parseProductNumber(String productNumber) {
		ProductNumberBean bean = new ProductNumberBean();

		Matcher matcher = productPattern.matcher(productNumber);
		if (matcher.find()) {
			bean.setValid(true);
			bean.setBrand(matcher.group(1));
			bean.setGender(matcher.group(2));
			bean.setProductClass(matcher.group(3));
//			bean.setYearCode(matcher.group(4));
//			bean.setYear(yearCodeMap.get(matcher.group(4)));
			bean.setSerialNumber(matcher.group(4));
			bean.setColor(matcher.group(5));

			bean.setBrandCode(getBrandCode(bean.getBrand()));
			bean.setCommodityNumber(getCommodityNumber(bean));
			bean.setProductNumber(getProductNumber(bean));
		}

		return bean;
	}

	@Override
	public ProductItemNumberBean parseProductItemNumber(String productItemNumber) {
		ProductItemNumberBean bean = new ProductItemNumberBean();

		Matcher matcher = productItemPattern.matcher(productItemNumber);
		if (matcher.find()) {
			bean.setValid(true);
			bean.setBrand(matcher.group(1));
			bean.setGender(matcher.group(2));
			bean.setProductClass(matcher.group(3));
//			bean.setYearCode(matcher.group(4));
//			bean.setYear(yearCodeMap.get(matcher.group(4)));
			bean.setSerialNumber(matcher.group(4));
			bean.setColor(matcher.group(5));
			bean.setSize(matcher.group(6));

			bean.setBrandCode(getBrandCode(bean.getBrand()));
			bean.setCommodityNumber(getCommodityNumber(bean));
			bean.setProductNumber(getProductNumber(bean));
			bean.setItemNumber(getItemNumber(bean));
		}

		return bean;
	}

	
	/**
	 * SuperDry 尺码规则 : S + 英国的款号（8-11位） + 颜色（3位） + 尺码（1位）
	 * 没法区分commodity和product 
	 */
	@Override
	public GenericProductNumberBean parseGenericProductNumber(String number) {
		GenericProductNumberBean bean = new GenericProductNumberBean();
		Matcher matcher = skuPattern.matcher(number);
		
		if (matcher.find()) {
			bean.setValid(true);
			bean.setBrand(matcher.group(1));
			bean.setGender(matcher.group(2));
			bean.setProductClass(matcher.group(3));
			bean.setSerialNumber(matcher.group(4));

			bean.setBrandCode(getBrandCode(bean.getBrand()));
			bean.setCommodityNumber(getCommodityNumber(bean));
		}
		return bean;
	}

}
