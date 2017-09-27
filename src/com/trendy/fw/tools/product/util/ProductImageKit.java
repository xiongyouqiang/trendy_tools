package com.trendy.fw.tools.product.util;

import com.trendy.fw.common.util.PropertiesKit;
import com.trendy.fw.tools.common.config.ToolsConfig;
import com.trendy.fw.tools.product.bean.ProductNumberBean;

public class ProductImageKit {
	public final static String PRODUCT_IMAGE_SITE = PropertiesKit.getBundleProperties(ToolsConfig.PROP_FILE_NAME,
			"PRODUCT_IMAGE_SITE");

	public final static String IT_DETAIL = "detail";
	public final static String IT_LIST = "list";
	public final static String IT_MODEL = "model";
	public final static String IT_COLOR = "color";

	public final static String IS_FULL = "f";
	public final static String IS_LARGE = "b";
	public final static String IS_MIDDLE = "m";
	public final static String IS_NORMAL = "n";
	public final static String IS_SMALL = "s";

	public static String[] PRODUCT_SEASONS = new String[] { "a", "b", "c", "d", "e", "f" };

	public static String getProductImageUrl(String productNumber, String imageType, String imageSize, int sequence) {
		return PRODUCT_IMAGE_SITE + getProductImageUri(productNumber, imageType, imageSize, sequence);
	}

	public static String getProductImageUrl(ProductNumberBean bean, String imageType, String imageSize, int sequence) {
		StringBuilder sb = new StringBuilder(PRODUCT_IMAGE_SITE);
		sb.append(getProductImagePath(bean));
		sb.append(getProductImageFileName(bean.getProductNumber(), imageType, imageSize, sequence));
		return sb.toString();
	}

	public static String getProductImageUri(String productNumber, String imageType, String imageSize, int sequence) {
		StringBuilder sb = new StringBuilder();
		sb.append(getProductImagePath(productNumber));
		sb.append(getProductImageFileName(productNumber, imageType, imageSize, sequence));

		return sb.toString();
	}

	public static String getProductImageFileName(String productNumber, String imageType, String imageSize, int sequence) {
		StringBuilder sb = new StringBuilder();
		sb.append(productNumber.toUpperCase());

		if (!IT_DETAIL.equals(imageType)) {
			sb.append("_" + imageType);
		}
		if (!IT_LIST.equals(imageType) && !IT_COLOR.equals(imageType)) {
			sb.append("_" + imageSize);
		}
		sb.append("_" + sequence);
		sb.append(".jpg");

		return sb.toString();
	}

	public static String getProductImagePath(String productNumber) {
		ProductNumberBean bean = ProductNumberKit.parseProductNumber(productNumber);
		return getProductImagePath(bean);
	}

	public static String getProductImagePath(ProductNumberBean bean) {
		StringBuilder sb = new StringBuilder();
		if (bean.isValid()) {
			sb.append(bean.getBrandCode().toLowerCase() + "/");
			sb.append("20" + bean.getYear() + "/");
			sb.append(PRODUCT_SEASONS[Integer.parseInt(bean.getSeason()) - 1] + "/");
			sb.append(bean.getProductNumber() + "/");
		}
		return sb.toString();
	}
}
