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
import com.trendy.fw.tools.product.util.ProductNumberParser;

public class ProductNumberParserDenhamImpl extends ProductNumberParser {

	private final static String commodityNumberRule = "(\\w{3})(\\d{1})(\\w{2})(\\w{2})(\\w{2})(\\w{3})";
	private final static String productNumberRule = commodityNumberRule + "(\\w{3})";
	private final static String productItemNumberRule = productNumberRule + "(\\w{1})";
	
	private static Pattern commodityPattern = Pattern.compile("^" + commodityNumberRule + "$");
	private static Pattern productPattern = Pattern.compile("^" + productNumberRule + "$");
	private static Pattern productItemPattern = Pattern.compile("^" + productItemNumberRule + "$");

	private static HashMap<String, String> yearCodeMap = new HashMap<String, String>();

	static {
		new ProductNumberParserDenhamImpl().init();
	}

	private void init() {
		initYearCodeMap();
	}

	private void initYearCodeMap() {
		yearCodeMap = new HashMap<String, String>();
		yearCodeMap.put("17", "2017");
		yearCodeMap.put("18", "2018");
		yearCodeMap.put("19", "2019");
		yearCodeMap.put("20", "2020");
		yearCodeMap.put("21", "2021");
		yearCodeMap.put("22", "2022");
		yearCodeMap.put("23", "2023");
		yearCodeMap.put("24", "2024");
		yearCodeMap.put("25", "2025");
		yearCodeMap.put("26", "2026");
		yearCodeMap.put("27", "2027");
	}
	
	@Override
	public CommodityNumberBean parseCommodityNumber(String commodityNumber) {
		CommodityNumberBean bean = new CommodityNumberBean();

		Matcher matcher = commodityPattern.matcher(commodityNumber);
		if (matcher.find()) {
			bean.setValid(true);
			bean.setBrand(matcher.group(1));
			bean.setGender(matcher.group(2));
			bean.setYearCode(matcher.group(3));
			bean.setYear(yearCodeMap.get(bean.getYearCode()));
			bean.setSeason(matcher.group(4));
			bean.setProductClass(matcher.group(5));
			bean.setSerialNumber(matcher.group(6));

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
			bean.setYearCode(matcher.group(3));
			bean.setYear(yearCodeMap.get(bean.getYearCode()));
			bean.setSeason(matcher.group(4));
			bean.setProductClass(matcher.group(5));
			bean.setSerialNumber(matcher.group(6));
			bean.setColor(matcher.group(7));
			
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
			bean.setYearCode(matcher.group(3));
			bean.setYear(yearCodeMap.get(bean.getYearCode()));
			bean.setSeason(matcher.group(4));
			bean.setProductClass(matcher.group(5));
			bean.setSerialNumber(matcher.group(6));
			bean.setColor(matcher.group(7));
			
			bean.setSize(matcher.group(8));
			
			bean.setBrandCode(getBrandCode(bean.getBrand()));
			bean.setCommodityNumber(getCommodityNumber(bean));
			bean.setProductNumber(getProductNumber(bean));
			bean.setItemNumber(getItemNumber(bean));
		}

		return bean;
	}

	@Override
	public GenericProductNumberBean parseGenericProductNumber(String number) {
		GenericProductNumberBean bean = new GenericProductNumberBean();
		int length = number.length();
		int numberType = ProductNumberConfig.NT_NONE;
		Matcher matcher = null;
		if (length == 13) {
			numberType = ProductNumberConfig.NT_COMMODITY;
			matcher = commodityPattern.matcher(number);
		} else if (length == 16) {
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
				bean.setGender(matcher.group(2));
				bean.setYearCode(matcher.group(3));
				bean.setSeason(matcher.group(4));
				bean.setProductClass(matcher.group(5));
				bean.setSerialNumber(matcher.group(6));

				bean.setBrandCode(getBrandCode(bean.getBrand()));
				bean.setYear(yearCodeMap.get(bean.getYearCode()));
				bean.setCommodityNumber(getCommodityNumber(bean));

				if (numberType >= ProductNumberConfig.NT_PRODUCT) {
					bean.setColor(matcher.group(7));
					
					bean.setProductNumber(getProductNumber(bean));
				}

				if (numberType == ProductNumberConfig.NT_PRODUCT_ITEM) {
					bean.setSize(matcher.group(8));
					
					bean.setItemNumber(getItemNumber(bean));
				}
			}
		}
		return bean;
	}
	


	private String getCommodityNumber(CommodityNumberBean bean) {
		return bean.getBrand() + bean.getGender() + bean.getYearCode() + bean.getSeason() + bean.getProductClass() + bean.getSerialNumber();
	}

	private String getProductNumber(ProductNumberBean bean) {
		return bean.getBrand() + bean.getGender() + bean.getYearCode() +bean.getSeason() + bean.getProductClass() + bean.getSerialNumber()
				+ bean.getColor();
	}

	private String getItemNumber(ProductItemNumberBean bean) {
		return bean.getBrand() + bean.getGender() + bean.getYearCode() + bean.getSeason() + bean.getProductClass() + bean.getSerialNumber()
				+ bean.getColor() + bean.getSize();
	}

	private String getBrandCode(String brand) {
		return ProductNumberConfig.BRAND_CODE_MAP.get(brand);
	}
	
	public static void main(String[] args) {
		ProductNumberParser p = new ProductNumberParserDenhamImpl();// 97A1ALAKN014101
		// 93F0TBECH001
		// ProductNumberBean b = p.parseProductNumber("93F0TBECH001");
		ProductItemNumberBean item = p.parseProductItemNumber("UDH1170351001U571");// 97A1ALAKN01410103
		System.out.println("93F0TBECH0011".substring(0, 12));
	}
}
