package com.trendy.fw.tools.product.bean;

public class ProductNumberBean extends CommodityNumberBean {
	/** 颜色 */
	protected String color = "";
	/** 商品编码 */
	private String productNumber = "";

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
}
