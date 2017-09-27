package com.trendy.fw.tools.product.util;

import com.trendy.fw.tools.product.bean.CommodityNumberBean;
import com.trendy.fw.tools.product.factory.ProductNumberFactory;

public class CommodityNumberKit {
	/**
	 * 分析货品编码
	 * 
	 * @param commodityNumber
	 *            货品编码
	 * @return
	 */
	static public CommodityNumberBean parseCommodityNumber(String commodityNumber) {
		commodityNumber = commodityNumber.toUpperCase();
		ProductNumberParser parser = ProductNumberFactory.getProductNumberParser(commodityNumber);
		return parser.parseCommodityNumber(commodityNumber);
	}
}
