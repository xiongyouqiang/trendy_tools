package com.trendy.fw.tools.product.bean;

public class ProductItemNumberBean extends ProductNumberBean {
	/** 码数 */
	private String size = "";
	/** 商品项编码 */
	private String itemNumber = "";

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
}
