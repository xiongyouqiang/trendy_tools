package com.trendy.fw.tools.product.util.impl;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.trendy.fw.tools.product.bean.CommodityNumberBean;
import com.trendy.fw.tools.product.bean.GenericProductNumberBean;
import com.trendy.fw.tools.product.bean.ProductItemNumberBean;
import com.trendy.fw.tools.product.bean.ProductNumberBean;
import com.trendy.fw.tools.product.config.ProductNumberConfig;
import com.trendy.fw.tools.product.util.ProductNumberParser;

public class ProductNumberParserMissSixtyImpl extends ProductNumberParser {
	private final static String commodityNumberRule = "(\\w{1})(\\w{1})(\\d{1})(\\w{1})(\\w{1})(\\w{4})(\\w{3})";
	private final static String productNumberRule = commodityNumberRule + "(\\w{3})";
	private final static String productItemNumberRule = productNumberRule + "(\\d{2})";

	private static Pattern commodityPattern = Pattern.compile("^" + commodityNumberRule + "$");
	private static Pattern productPattern = Pattern.compile("^" + productNumberRule + "$");
	private static Pattern productItemPattern = Pattern.compile("^" + productItemNumberRule + "$");

	private static HashMap<String, String> yearCodeMap = new HashMap<String, String>();

	static {
		new ProductNumberParserMissSixtyImpl().init();
	}

	private void init() {
		initYearCodeMap();
	}

	private void initYearCodeMap() {
		yearCodeMap = new HashMap<String, String>();
		yearCodeMap.put("1", "11");
		yearCodeMap.put("2", "12");
		yearCodeMap.put("3", "13");
		yearCodeMap.put("4", "14");
		yearCodeMap.put("5", "15");
		yearCodeMap.put("6", "16");
		yearCodeMap.put("7", "17");
		yearCodeMap.put("8", "18");
		yearCodeMap.put("9", "19");
		yearCodeMap.put("0", "20");
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
			bean.setDesignedId(matcher.group(5));
			bean.setSerialNumber(matcher.group(6));
			bean.setSerialNumber2(matcher.group(7));

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
			bean.setDesignedId(matcher.group(5));
			bean.setSerialNumber(matcher.group(6));
			bean.setSerialNumber2(matcher.group(7));
			bean.setColor(matcher.group(8));

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
			bean.setDesignedId(matcher.group(5));
			bean.setSerialNumber(matcher.group(6));
			bean.setSerialNumber2(matcher.group(7));
			bean.setColor(matcher.group(8));
			bean.setSize(matcher.group(9));

			bean.setBrandCode(getBrandCode(bean.getBrand()));
			bean.setCommodityNumber(getCommodityNumber(bean));
			bean.setProductNumber(getProductNumber(bean));
			bean.setItemNumber(getItemNumber(bean));
		}

		return bean;
	}

	private String getCommodityNumber(CommodityNumberBean bean) {
		return bean.getBrand() + bean.getYearCode() + bean.getSeason() + bean.getProductClass() + bean.getDesignedId()
				+ bean.getSerialNumber() + bean.getSerialNumber2();
	}

	private String getProductNumber(ProductNumberBean bean) {
		return bean.getBrand() + bean.getYearCode() + bean.getSeason() + bean.getProductClass() + bean.getDesignedId()
				+ bean.getSerialNumber() + bean.getSerialNumber2() + bean.getColor();
	}

	private String getItemNumber(ProductItemNumberBean bean) {
		return bean.getBrand() + bean.getYearCode() + bean.getSeason() + bean.getProductClass() + bean.getDesignedId()
				+ bean.getSerialNumber() + bean.getSerialNumber2() + bean.getColor() + bean.getSize();
	}

	private String getBrandCode(String brand) {
		return ProductNumberConfig.BRAND_CODE_MAP.get(brand);
	}

	private void parseYearStr(CommodityNumberBean bean, String str) {
		bean.setYearCode(str);
		bean.setYear(yearCodeMap.get(str));
	}

	@Override
	public GenericProductNumberBean parseGenericProductNumber(String number) {
		GenericProductNumberBean bean = new GenericProductNumberBean();
		int length = number.length();
		int numberType = ProductNumberConfig.NT_NONE;
		Matcher matcher = null;
		if (length == 12) {
			numberType = ProductNumberConfig.NT_COMMODITY;
			matcher = commodityPattern.matcher(number);
		} else if (length == 15) {
			numberType = ProductNumberConfig.NT_PRODUCT;
			matcher = productPattern.matcher(number);
		} else if (length == 17) {
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
				bean.setDesignedId(matcher.group(5));
				bean.setSerialNumber(matcher.group(6));
				bean.setSerialNumber2(matcher.group(7));

				bean.setBrandCode(getBrandCode(bean.getBrand()));
				bean.setCommodityNumber(getCommodityNumber(bean));

				if (numberType >= ProductNumberConfig.NT_PRODUCT) {
					bean.setColor(matcher.group(8));
					bean.setProductNumber(getProductNumber(bean));
				}

				if (numberType == ProductNumberConfig.NT_PRODUCT_ITEM) {
					bean.setSize(matcher.group(9));
					bean.setItemNumber(getItemNumber(bean));
				}
			}
		}
		return bean;
	}

}
