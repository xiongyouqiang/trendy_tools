package com.trendy.fw.tools.product.util.impl;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.trendy.fw.common.util.StringKit;
import com.trendy.fw.tools.product.bean.CommodityNumberBean;
import com.trendy.fw.tools.product.bean.GenericProductNumberBean;
import com.trendy.fw.tools.product.bean.ProductItemNumberBean;
import com.trendy.fw.tools.product.bean.ProductNumberBean;
import com.trendy.fw.tools.product.config.ProductNumberConfig;
import com.trendy.fw.tools.product.util.ProductNumberParser;

public class ProductNumberParserWAXYImpl extends ProductNumberParser {
	private final static String commodityNumberRule = "(\\w{1})(\\w{1})(\\w{1})(\\d{2})(\\w{2})(\\w{3})";
	private final static String commodityNumber13Rule = "(\\w{1})(\\w{1})(\\d{2})(\\w{1})(\\w{4})(\\w{2})(\\w{2})";
	private final static String productNumberRule = commodityNumberRule + "(\\w{3})";
	private final static String productNumber16Rule = commodityNumber13Rule + "(\\w{3})";
	private final static String productItemNumberRule = productNumberRule + "(\\w{1})";
	private final static String productItemNumber17Rule = productNumber16Rule + "(\\w{1})";

	private static Pattern commodityPattern = Pattern.compile("^" + commodityNumberRule + "$");
	private static Pattern productPattern = Pattern.compile("^" + productNumberRule + "$");
	private static Pattern productItemPattern = Pattern.compile("^" + productItemNumberRule + "$");
	private static Pattern commodity13Pattern = Pattern.compile("^" + commodityNumber13Rule + "$");
	private static Pattern product16Pattern = Pattern.compile("^" + productNumber16Rule + "$");
	private static Pattern productItem17Pattern = Pattern.compile("^" + productItemNumber17Rule + "$");

	private static HashMap<String, String> yearCodeMap = new HashMap<String, String>();

	static {
		new ProductNumberParserWAXYImpl().init();
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
		Matcher matcher = null;
		if (commodityNumber.length() == 13) {
			matcher = commodity13Pattern.matcher(commodityNumber);
		} else {
			matcher = commodityPattern.matcher(commodityNumber);
		}

		if (matcher.find()) {
			bean.setValid(true);
			bean.setBrand(matcher.group(1));
			bean.setYearCode(matcher.group(2));
			if (commodityNumber.length() == 13) {
				bean.setSeries(matcher.group(4));
				bean.setMonth(matcher.group(3));
				bean.setProductClass(matcher.group(6));
				bean.setSerialNumber(matcher.group(5));
				bean.setFactoryCode(matcher.group(7));
			} else {
				bean.setSeries(matcher.group(3));
				bean.setMonth(matcher.group(4));
				bean.setProductClass(matcher.group(5));
				bean.setSerialNumber(matcher.group(6));
			}

			bean.setYear(yearCodeMap.get(bean.getYearCode()));
			bean.setBrandCode(getBrandCode(bean.getBrand()));
			bean.setCommodityNumber(getCommodityNumber(bean));
		}
		return bean;
	}

	@Override
	public ProductNumberBean parseProductNumber(String productNumber) {
		ProductNumberBean bean = new ProductNumberBean();
		Matcher matcher = null;
		if (productNumber.length() == 16) {
			matcher = product16Pattern.matcher(productNumber);
		} else {
			matcher = productPattern.matcher(productNumber);
		}
		if (matcher.find()) {
			bean.setValid(true);
			bean.setBrand(matcher.group(1));
			bean.setYearCode(matcher.group(2));
			if (productNumber.length() == 16) {
				bean.setSeries(matcher.group(4));
				bean.setMonth(matcher.group(3));
				bean.setProductClass(matcher.group(6));
				bean.setSerialNumber(matcher.group(5));
				bean.setFactoryCode(matcher.group(7));
				bean.setColor(matcher.group(8));
			} else {
				bean.setSeries(matcher.group(3));
				bean.setMonth(matcher.group(4));
				bean.setProductClass(matcher.group(5));
				bean.setSerialNumber(matcher.group(6));
				bean.setColor(matcher.group(7));
			}

			bean.setYear(yearCodeMap.get(bean.getYearCode()));
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
		if (productItemNumber.length() == 17) {
			matcher = productItem17Pattern.matcher(productItemNumber);
		} else {
			matcher = productItemPattern.matcher(productItemNumber);
		}
		if (matcher.find()) {
			bean.setValid(true);
			bean.setBrand(matcher.group(1));
			bean.setYearCode(matcher.group(2));
			if (productItemNumber.length() == 17) {
				bean.setSeries(matcher.group(4));
				bean.setMonth(matcher.group(3));
				bean.setProductClass(matcher.group(6));
				bean.setSerialNumber(matcher.group(5));
				bean.setFactoryCode(matcher.group(7));
				bean.setColor(matcher.group(8));
				bean.setSize(matcher.group(9));
			} else {
				bean.setSeries(matcher.group(3));
				bean.setMonth(matcher.group(4));
				bean.setProductClass(matcher.group(5));
				bean.setSerialNumber(matcher.group(6));
				bean.setColor(matcher.group(7));
				bean.setSize(matcher.group(8));
			}

			bean.setYear(yearCodeMap.get(bean.getYearCode()));
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
		if (length == 10) {
			numberType = ProductNumberConfig.NT_COMMODITY;
			matcher = commodityPattern.matcher(number);
		} else if (length == 13) {
			// 13位有可能是spu有可能是SKU
			matcher = commodity13Pattern.matcher(number);
			if (matcher.find()) {
				numberType = ProductNumberConfig.NT_COMMODITY;
			} else {
				numberType = ProductNumberConfig.NT_PRODUCT;
				matcher = productPattern.matcher(number);
			}

		} else if (length == 16) {
			numberType = ProductNumberConfig.NT_PRODUCT;
			matcher = product16Pattern.matcher(number);

		} else if (length == 14 || length == 17) {
			numberType = ProductNumberConfig.NT_PRODUCT_ITEM;
			if (length == 17) {
				matcher = productItem17Pattern.matcher(number);
			} else {
				matcher = productItemPattern.matcher(number);
			}
		} else {
			return bean;
		}

		bean.setNumberType(numberType);
		if (numberType > ProductNumberConfig.NT_NONE) {
			if (matcher.find()) {
				bean.setValid(true);
				bean.setBrand(matcher.group(1));
				bean.setYearCode(matcher.group(2));
				if (length == 13) {
					bean.setSeries(matcher.group(4));
					bean.setMonth(matcher.group(3));
					bean.setProductClass(matcher.group(6));
					bean.setSerialNumber(matcher.group(5));
					bean.setFactoryCode(matcher.group(7));
				} else {
					bean.setSeries(matcher.group(3));
					bean.setMonth(matcher.group(4));
					bean.setProductClass(matcher.group(5));
					bean.setSerialNumber(matcher.group(6));
				}

				bean.setYear(yearCodeMap.get(bean.getYearCode()));
				bean.setBrandCode(getBrandCode(bean.getBrand()));
				bean.setCommodityNumber(getCommodityNumber(bean));
				bean.setCommodityNumber(getCommodityNumber(bean));

				if (numberType >= ProductNumberConfig.NT_PRODUCT) {
					if (length == 16) {
						bean.setColor(matcher.group(8));
					} else {
						bean.setColor(matcher.group(7));
					}
					bean.setProductNumber(getProductNumber(bean));
				}

				if (numberType == ProductNumberConfig.NT_PRODUCT_ITEM) {
					if (length == 17) {
						bean.setSize(matcher.group(9));
					} else {
						bean.setSize(matcher.group(8));
					}
					bean.setItemNumber(getItemNumber(bean));
				}
			}
		}
		return bean;
	}

	private String getCommodityNumber(CommodityNumberBean bean) {
		if (StringKit.isValid(bean.getFactoryCode()) && StringKit.isValid(bean.getSerialNumber())
				&& bean.getSerialNumber().length() == 4) {
			// X-品牌
			// H-年份生肖
			// 10-月份
			// V-系列代码
			// 0203-流水号
			// 04-包型类目
			// 03-工厂代码
			return bean.getBrand() + bean.getYearCode() + bean.getMonth() + bean.getSeries() + bean.getSerialNumber()
					+ bean.getProductClass() + bean.getFactoryCode();
		}
		// X-品牌
		// H-年份生肖
		// V-系列代码
		// 11-月份
		// 09-包型类目
		// 002-流水号
		return bean.getBrand() + bean.getYearCode() + bean.getSeries() + bean.getMonth() + bean.getProductClass()
				+ bean.getSerialNumber();
	}

	private String getProductNumber(ProductNumberBean bean) {
		return bean.getCommodityNumber() + bean.getColor();
	}

	private String getItemNumber(ProductItemNumberBean bean) {
		return bean.getProductNumber() + bean.getSize();
	}

	private String getBrandCode(String brand) {
		return ProductNumberConfig.BRAND_CODE_MAP.get(brand);
	}

	public static void main(String[] args) {
		ProductNumberParserWAXYImpl p = new ProductNumberParserWAXYImpl();// XHV11090025019
																	// XH10V020304030089
		ProductItemNumberBean b = p.parseProductItemNumber("XHV11090025019");
		System.out.println(b.getCommodityNumber()+">>>"+b.getProductNumber()+">>>"+b.getItemNumber()+">>>"+b.getBrandCode()+">>>"+b.getYear());
		
		ProductItemNumberBean item= p.parseProductItemNumber("XH10V020304030089");
		System.out.println(item.getCommodityNumber()+">>>"+item.getProductNumber()+">>>"+item.getItemNumber()+">>>"+b.getBrandCode()+">>>"+b.getYear());
		
		GenericProductNumberBean bean = p.parseGenericProductNumber("XHV1109002501");
		System.out.println(bean.getNumberType());
		GenericProductNumberBean b1 = p.parseGenericProductNumber("XH10V02030403");
		System.out.println(b1.getNumberType());
	}
}
