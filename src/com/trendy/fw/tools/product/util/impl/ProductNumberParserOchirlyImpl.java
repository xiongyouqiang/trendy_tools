package com.trendy.fw.tools.product.util.impl;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.trendy.fw.common.util.NumberKit;
import com.trendy.fw.common.util.StringKit;
import com.trendy.fw.tools.product.bean.CommodityNumberBean;
import com.trendy.fw.tools.product.bean.GenericProductNumberBean;
import com.trendy.fw.tools.product.bean.ProductItemNumberBean;
import com.trendy.fw.tools.product.bean.ProductNumberBean;
import com.trendy.fw.tools.product.config.ProductNumberConfig;
import com.trendy.fw.tools.product.util.ProductNumberKit;
import com.trendy.fw.tools.product.util.ProductNumberParser;

public class ProductNumberParserOchirlyImpl extends ProductNumberParser {
	private final static String commodityNumberRule = "(\\w{1})(\\w{2})(\\d{1})(\\w{2})(\\w{4})";
	private final static String productNumberRule = commodityNumberRule + "(\\w{3})";
	private final static String productItemNumberRule = productNumberRule + "(\\d{1})";

	private static Pattern commodityPattern = Pattern.compile("^" + commodityNumberRule + "$");
	private static Pattern productPattern = Pattern.compile("^" + productNumberRule + "$");
	private static Pattern productItemPattern = Pattern.compile("^" + productItemNumberRule + "$");

	private static HashMap<String, String> yearCodeMap = new HashMap<String, String>();

	static {
		new ProductNumberParserOchirlyImpl().init();
	}

	private void init() {
		initYearCodeMap();
	}

	private void initYearCodeMap() {
		yearCodeMap = new HashMap<String, String>();
		yearCodeMap.put("Y", "15");
		yearCodeMap.put("H", "16");
		yearCodeMap.put("J", "17");
		yearCodeMap.put("G", "18");
		yearCodeMap.put("Z", "19");
		yearCodeMap.put("R", "20");
		yearCodeMap.put("N", "21");
		yearCodeMap.put("W", "22");
		yearCodeMap.put("T", "23");
		yearCodeMap.put("L", "24");
		yearCodeMap.put("S", "25");
		yearCodeMap.put("M", "26");
	}

	@Override
	public CommodityNumberBean parseCommodityNumber(String commodityNumber) {
		CommodityNumberBean bean = new CommodityNumberBean();

		Matcher matcher = commodityPattern.matcher(commodityNumber);
		if (matcher.find()) {
			bean.setValid(true);
			bean.setBrand(matcher.group(1));
			parseYearStr(bean, matcher.group(2));
			bean.setSeason(matcher.group(3));
			bean.setProductClass(matcher.group(4));
			bean.setSerialNumber(matcher.group(5));

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
			parseYearStr(bean, matcher.group(2));
			bean.setSeason(matcher.group(3));
			bean.setProductClass(matcher.group(4));
			bean.setSerialNumber(matcher.group(5));
			bean.setColor(matcher.group(6));

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
			parseYearStr(bean, matcher.group(2));
			bean.setSeason(matcher.group(3));
			bean.setProductClass(matcher.group(4));
			bean.setSerialNumber(matcher.group(5));
			bean.setColor(matcher.group(6));
			bean.setSize(matcher.group(7));

			bean.setBrandCode(getBrandCode(bean.getBrand()));
			bean.setCommodityNumber(getCommodityNumber(bean));
			bean.setProductNumber(getProductNumber(bean));
			bean.setItemNumber(getItemNumber(bean));
		}

		return bean;
	}

	private String getCommodityNumber(CommodityNumberBean bean) {
		return bean.getBrand() + getYearStr(bean) + bean.getSeason() + bean.getProductClass() + bean.getSerialNumber();
	}

	private String getProductNumber(ProductNumberBean bean) {
		return bean.getBrand() + getYearStr(bean) + bean.getSeason() + bean.getProductClass() + bean.getSerialNumber()
				+ bean.getColor();
	}

	private String getItemNumber(ProductItemNumberBean bean) {
		return bean.getBrand() + getYearStr(bean) + bean.getSeason() + bean.getProductClass() + bean.getSerialNumber()
				+ bean.getColor() + bean.getSize();
	}

	private String getBrandCode(String brand) {
		return ProductNumberConfig.BRAND_CODE_MAP.get(brand);
	}

	private String getYearStr(CommodityNumberBean bean) {
		String result = "";
		if (StringKit.isValid(bean.getYearCode()) && StringKit.isValid(bean.getSeries())) {
			result = bean.getYearCode() + bean.getSeries();
		} else {
			result = bean.getYear();
		}
		return result;
	}

	private void parseYearStr(CommodityNumberBean bean, String str) {
		if (NumberKit.isAllDigit(str)) {// 都是数字，代码年份
			bean.setYear(str);
		} else {// 不是数字，代表年份+系列
			String yearCode = str.substring(0, 1);
			String series = str.substring(1);
			bean.setYearCode(yearCode);
			bean.setYear(yearCodeMap.get(yearCode));
			bean.setSeries(series);
		}
	}

	@Override
	public GenericProductNumberBean parseGenericProductNumber(String number) {
		GenericProductNumberBean bean = new GenericProductNumberBean();
		int length = number.length();
		int numberType = ProductNumberConfig.NT_NONE;
		Matcher matcher = null;
		if (length == 10) {
			numberType = ProductNumberConfig.NT_COMMODITY;
			matcher = commodityPattern.matcher(number);
		} else if (length == 13) {
			numberType = ProductNumberConfig.NT_PRODUCT;
			matcher = productPattern.matcher(number);
		} else if (length == 14) {
			numberType = ProductNumberConfig.NT_PRODUCT_ITEM;
			matcher = productItemPattern.matcher(number);
		} else {
			return bean;
		}

		bean.setNumberType(numberType);
		if (numberType > ProductNumberConfig.NT_NONE) {
			if (matcher.find()) {
				bean.setValid(true);
				bean.setBrand(matcher.group(1));
				parseYearStr(bean, matcher.group(2));
				bean.setSeason(matcher.group(3));
				bean.setProductClass(matcher.group(4));
				bean.setSerialNumber(matcher.group(5));

				bean.setBrandCode(getBrandCode(bean.getBrand()));
				bean.setCommodityNumber(getCommodityNumber(bean));

				if (numberType >= ProductNumberConfig.NT_PRODUCT) {
					bean.setColor(matcher.group(6));
					bean.setProductNumber(getProductNumber(bean));
				}

				if (numberType == ProductNumberConfig.NT_PRODUCT_ITEM) {
					bean.setSize(matcher.group(7));
					bean.setItemNumber(getItemNumber(bean));
				}
			}
		}
		return bean;
	}
	public static void main(String[] args) {
		ProductNumberParser p = new ProductNumberParserOchirlyImpl();
		CommodityNumberBean c = p.parseCommodityNumber("HJR1001010");
		System.out.println(c.isValid());
	}
}
