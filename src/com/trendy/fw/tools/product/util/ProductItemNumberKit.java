package com.trendy.fw.tools.product.util;

import com.trendy.fw.tools.product.bean.ProductItemNumberBean;
import com.trendy.fw.tools.product.factory.ProductNumberFactory;

public class ProductItemNumberKit {
	/**
	 * 分析商品项编码
	 * 
	 * @param productItenNumber
	 *            商品项编码
	 * @return
	 */
	static public ProductItemNumberBean parseProductItemNumber(String productItenNumber) {
		productItenNumber = productItenNumber.toUpperCase();
		ProductNumberParser parser = ProductNumberFactory.getProductNumberParser(productItenNumber);
		return parser.parseProductItemNumber(productItenNumber);
	}
}
