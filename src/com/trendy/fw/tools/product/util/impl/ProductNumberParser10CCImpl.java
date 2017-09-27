package com.trendy.fw.tools.product.util.impl;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.trendy.fw.common.util.JsonKit;
import com.trendy.fw.tools.product.bean.CommodityNumberBean;
import com.trendy.fw.tools.product.bean.GenericProductNumberBean;
import com.trendy.fw.tools.product.bean.ProductItemNumberBean;
import com.trendy.fw.tools.product.bean.ProductNumberBean;
import com.trendy.fw.tools.product.config.ProductNumberConfig;
import com.trendy.fw.tools.product.util.ProductNumberParser;

public class ProductNumberParser10CCImpl extends ProductNumberParser {

	private final static String commodityNumberRule = "(\\w{1})(\\d{1})(\\w{1})(\\w{1})(\\w{3})(\\w{2})(\\w{3})";
	private final static String productNumberRule = commodityNumberRule + "(\\w{3})";
	private final static String productItemNumberRule = productNumberRule + "(\\w{2})";

	private static Pattern commodityPattern = Pattern.compile("^" + commodityNumberRule + "$");
	private static Pattern productPattern = Pattern.compile("^" + productNumberRule + "$");
	private static Pattern productItemPattern = Pattern.compile("^" + productItemNumberRule + "$");
	private static Pattern specialProductItemPattern = Pattern.compile("^" + commodityNumberRule + "(\\w{1})$");

	private static HashMap<String, String> yearCodeMap = new HashMap<String, String>();

	private static String[] productClassArr = { "0", "5", "6", "7", "9" };

	static {
		new ProductNumberParser10CCImpl().init();
	}

	private void init() {
		initYearCodeMap();
	}

	private void initYearCodeMap() {
		yearCodeMap = new HashMap<String, String>();
		yearCodeMap.put("3", "2013");
		yearCodeMap.put("4", "2014");
		yearCodeMap.put("5", "2015");
		yearCodeMap.put("6", "2016");
		yearCodeMap.put("7", "2017");
		yearCodeMap.put("8", "2018");
		yearCodeMap.put("9", "2019");
		yearCodeMap.put("0", "2020");
		yearCodeMap.put("1", "2021");
		yearCodeMap.put("2", "2020");
	}

	@Override
	public CommodityNumberBean parseCommodityNumber(String commodityNumber) {
		CommodityNumberBean bean = new CommodityNumberBean();

		Matcher matcher = commodityPattern.matcher(commodityNumber);
		if (matcher.find()) {
			bean.setValid(true);
			bean.setBrand(matcher.group(1));
			bean.setYearCode(matcher.group(2));
			bean.setYear(yearCodeMap.get(bean.getYearCode()));
			bean.setSeason(matcher.group(3));
			bean.setProductClass(matcher.group(4));
			bean.setSubBrand(matcher.group(5));
			bean.setSubCat(matcher.group(6));
			bean.setSerialNumber(matcher.group(7));

			bean.setBrandCode(getBrandCode(bean.getBrand()));
			bean.setCommodityNumber(getCommodityNumber(bean));
		}
		return bean;
	}

	@Override
	public ProductNumberBean parseProductNumber(String productNumber) {
		ProductNumberBean bean = new ProductNumberBean();
		Matcher matcher = null;
		boolean isSpecialProductClass = isSpecialProductClass(productNumber);
		if (isSpecialProductClass) {
			matcher = commodityPattern.matcher(productNumber);
		} else {
			matcher = productPattern.matcher(productNumber);
		}
		if (matcher.find()) {
			bean.setValid(true);
			bean.setBrand(matcher.group(1));
			bean.setYearCode(matcher.group(2));
			bean.setYear(yearCodeMap.get(bean.getYearCode()));
			bean.setSeason(matcher.group(3));
			bean.setProductClass(matcher.group(4));
			bean.setSubBrand(matcher.group(5));
			bean.setSubCat(matcher.group(6));
			bean.setSerialNumber(matcher.group(7));
			if (!isSpecialProductClass) {
				bean.setColor(matcher.group(8));
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
		Matcher matcher = null;
		boolean isSpecialProductClass = isSpecialProductClass(productItemNumber);
		if (isSpecialProductClass) {
			matcher = specialProductItemPattern.matcher(productItemNumber);
		} else {
			matcher = productItemPattern.matcher(productItemNumber);
		}
		if (matcher.find()) {
			bean.setValid(true);
			bean.setBrand(matcher.group(1));
			bean.setYearCode(matcher.group(2));
			bean.setYear(yearCodeMap.get(bean.getYearCode()));
			bean.setSeason(matcher.group(3));
			bean.setProductClass(matcher.group(4));
			bean.setSubBrand(matcher.group(5));
			bean.setSubCat(matcher.group(6));
			bean.setSerialNumber(matcher.group(7));
			if (isSpecialProductClass) {
				bean.setSize(matcher.group(8));
			} else {
				bean.setColor(matcher.group(8));
				bean.setSize(matcher.group(9));
			}

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
		if (length == 12) {
			numberType = ProductNumberConfig.NT_COMMODITY;
			matcher = commodityPattern.matcher(number);
		} else if (length == 15) {
			numberType = ProductNumberConfig.NT_PRODUCT;
			matcher = productPattern.matcher(number);
		} else if (length == 17 || length == 13) {
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
				bean.setYearCode(matcher.group(2));
				bean.setYear(yearCodeMap.get(bean.getYearCode()));
				bean.setSeason(matcher.group(3));
				bean.setProductClass(matcher.group(4));
				bean.setSubBrand(matcher.group(5));
				bean.setSubCat(matcher.group(6));
				bean.setSerialNumber(matcher.group(7));

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

	private String getCommodityNumber(CommodityNumberBean bean) {
		return bean.getBrand() + bean.getYearCode() + bean.getSeason() + bean.getProductClass() + bean.getSubBrand()
				+ bean.getSubCat() + bean.getSerialNumber();
	}

	private String getProductNumber(ProductNumberBean bean) {
		return bean.getBrand() + bean.getYearCode() + bean.getSeason() + bean.getProductClass() + bean.getSubBrand()
				+ bean.getSubCat() + bean.getSerialNumber() + bean.getColor();
	}

	private String getItemNumber(ProductItemNumberBean bean) {
		return bean.getBrand() + bean.getYearCode() + bean.getSeason() + bean.getProductClass() + bean.getSubBrand()
				+ bean.getSubCat() + bean.getSerialNumber() + bean.getColor() + bean.getSize();
	}

	private String getBrandCode(String brand) {
		return ProductNumberConfig.BRAND_CODE_MAP.get(brand);
	}

	private boolean isSpecialProductClass(String productItemNumber) {
		if(productItemNumber.length()<12){
			return false;
		}
		CommodityNumberBean commodityNumberBean = parseCommodityNumber(productItemNumber.substring(0, 12));
		if (commodityNumberBean.isValid()) {
			for (String productClass : productClassArr) {
				if (commodityNumberBean.getProductClass().equals(productClass)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		ProductNumberParser p = new ProductNumberParser10CCImpl();// 97A1ALAKN014101
																	// 93F0TBECH001
		// ProductNumberBean b = p.parseProductNumber("93F0TBECH001");
		CommodityNumberBean item = p.parseCommodityNumber("97A3CHLBG012");// 97A1ALAKN01410103 93C1ALABN02110102
		System.out.println(JsonKit.toJson(item));
	}
}
