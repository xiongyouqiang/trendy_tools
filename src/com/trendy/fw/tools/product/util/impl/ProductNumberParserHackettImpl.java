package com.trendy.fw.tools.product.util.impl;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.trendy.fw.common.util.JsonKit;
import com.trendy.fw.common.util.NumberKit;
import com.trendy.fw.common.util.StringKit;
import com.trendy.fw.tools.product.bean.CommodityNumberBean;
import com.trendy.fw.tools.product.bean.GenericProductNumberBean;
import com.trendy.fw.tools.product.bean.ProductItemNumberBean;
import com.trendy.fw.tools.product.bean.ProductNumberBean;
import com.trendy.fw.tools.product.config.ProductNumberConfig;
import com.trendy.fw.tools.product.util.ProductNumberParser;

public class ProductNumberParserHackettImpl extends ProductNumberParser {

	private final static String commodityNumberRule = "(\\w{2})(\\w{1})(\\d{1})(\\w{2})(\\w{4})(\\w{3})(\\w{1})";
	private final static String productNumberRule = commodityNumberRule + "(\\w{3})";
	private final static String productItemNumberRule = productNumberRule + "(\\w{1})";
	
	private static Pattern commodityPattern = Pattern.compile("^" + commodityNumberRule + "$");
	private static Pattern productPattern = Pattern.compile("^" + productNumberRule + "$");
	private static Pattern productItemPattern = Pattern.compile("^" + productItemNumberRule + "$");

	private static HashMap<String, String> yearCodeMap = new HashMap<String, String>();

	static {
		new ProductNumberParserHackettImpl().init();
	}

	private void init() {
		initYearCodeMap();
	}

	private void initYearCodeMap() {
		yearCodeMap = new HashMap<String, String>();
		yearCodeMap.put("Y17", "2017");
		yearCodeMap.put("Y18", "2018");
		yearCodeMap.put("Y19", "2019");
		yearCodeMap.put("Y20", "2020");
		yearCodeMap.put("Y21", "2021");
		yearCodeMap.put("Y22", "2022");
		yearCodeMap.put("Y23", "2023");
		yearCodeMap.put("Y24", "2024");
		yearCodeMap.put("Y25", "2025");
		yearCodeMap.put("Y26", "2026");
		yearCodeMap.put("Y27", "2027");
	}
	
	@Override
	public CommodityNumberBean parseCommodityNumber(String commodityNumber) {
		CommodityNumberBean bean = new CommodityNumberBean();

		Matcher matcher = commodityPattern.matcher(commodityNumber);
		if (matcher.find()) {
			bean.setValid(true);
			bean.setBrand(matcher.group(1));
			bean.setGender(matcher.group(2));
			bean.setSeason(matcher.group(3));
			bean.setProductClass(matcher.group(4));
			bean.setSerialNumber(matcher.group(5));
			bean.setHackettColor(matcher.group(6));
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
			bean.setGender(matcher.group(2));
			bean.setSeason(matcher.group(3));
			bean.setProductClass(matcher.group(4));
			bean.setSerialNumber(matcher.group(5));
			bean.setHackettColor(matcher.group(6));
			
			if(yearCodeMap.get(matcher.group(8))==null){
				bean.setColor(matcher.group(8));
			}else{
				bean.setColor(matcher.group(6));
				bean.setYearCode(matcher.group(8));
				bean.setYear(yearCodeMap.get(bean.getYearCode()));
			}
			
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
			bean.setSeason(matcher.group(3));
			bean.setProductClass(matcher.group(4));
			bean.setSerialNumber(matcher.group(5));
			bean.setHackettColor(matcher.group(6));
			bean.setSerialNumber2(matcher.group(7));
			
			if(yearCodeMap.get(matcher.group(8))==null){
				bean.setColor(matcher.group(8));
			}else{
				bean.setColor(matcher.group(6));
				bean.setYearCode(matcher.group(8));
				bean.setYear(yearCodeMap.get(bean.getYearCode()));
			}
			bean.setSize(matcher.group(9));
			
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
		if (length == 14) {
			numberType = ProductNumberConfig.NT_COMMODITY;
			matcher = commodityPattern.matcher(number);
		} else if (length == 17) {
			numberType = ProductNumberConfig.NT_PRODUCT;
			matcher = productPattern.matcher(number);
		} else if (length == 18) {
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
				bean.setSeason(matcher.group(3));
				bean.setProductClass(matcher.group(4));
				bean.setSerialNumber(matcher.group(5));
				bean.setHackettColor(matcher.group(6));
				bean.setSerialNumber2(matcher.group(7));
				
				bean.setBrandCode(getBrandCode(bean.getBrand()));
				bean.setCommodityNumber(getCommodityNumber(bean));

				if (numberType >= ProductNumberConfig.NT_PRODUCT) {
					if(yearCodeMap.get(matcher.group(8))==null){
						bean.setColor(matcher.group(8));
					}else{
						bean.setColor(matcher.group(6));
						bean.setYearCode(matcher.group(8));
						bean.setYear(yearCodeMap.get(bean.getYearCode()));
					}
					
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
	


	private String getCommodityNumber(CommodityNumberBean bean) {
		return bean.getBrand() + bean.getGender() + bean.getSeason() + bean.getProductClass() + bean.getSerialNumber()+bean.getHackettColor()+bean.getSerialNumber2();
	}

	private String getProductNumber(ProductNumberBean bean) {
		return bean.getBrand() + bean.getGender() + bean.getSeason() + bean.getProductClass() + bean.getSerialNumber()
				+ bean.getHackettColor() + bean.getSerialNumber2() + (StringKit.isValid(bean.getYearCode())?bean.getYearCode():bean.getColor());
	}

	private String getItemNumber(ProductItemNumberBean bean) {
		return bean.getBrand() + bean.getGender() + bean.getSeason() + bean.getProductClass() + bean.getSerialNumber()
				+ bean.getColor() + bean.getSerialNumber2() + (StringKit.isValid(bean.getYearCode())?bean.getYearCode():bean.getColor()) + bean.getSize();
	}

	private String getBrandCode(String brand) {
		return ProductNumberConfig.BRAND_CODE_MAP.get(brand);
	}
	
	public static void main(String[] args) {
		ProductNumberParser p = new ProductNumberParserHackettImpl();
		ProductItemNumberBean bean = p.parseProductItemNumber("VHM20116248GKRY170");
		System.out.println(JsonKit.toJson(bean));
		
	}
}
