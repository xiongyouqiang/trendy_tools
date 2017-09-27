package com.trendy.fw.tools.product.util;

import com.trendy.fw.tools.product.bean.ProductNumberBean;
import com.trendy.fw.tools.product.factory.ProductNumberFactory;

public class ProductNumberKit {
	/**
	 * 分析货品编码
	 * 
	 * @param productNumber
	 *            货品编码
	 * @return
	 */
	static public ProductNumberBean parseProductNumber(String productNumber) {
		productNumber = productNumber.toUpperCase();
		ProductNumberParser parser = ProductNumberFactory.getProductNumberParser(productNumber);
		return parser.parseProductNumber(productNumber);
	}
}
