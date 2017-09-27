package com.trendy.fw.tools.product.util.impl;

import com.trendy.fw.tools.product.bean.CommodityNumberBean;
import com.trendy.fw.tools.product.bean.GenericProductNumberBean;
import com.trendy.fw.tools.product.bean.ProductItemNumberBean;
import com.trendy.fw.tools.product.bean.ProductNumberBean;
import com.trendy.fw.tools.product.util.ProductNumberParser;

public class ProductNumberParserDefaultImpl extends ProductNumberParser {

	@Override
	public CommodityNumberBean parseCommodityNumber(String commodityNumber) {
		return new CommodityNumberBean();
	}

	@Override
	public ProductNumberBean parseProductNumber(String productNumber) {
		return new ProductNumberBean();
	}

	@Override
	public ProductItemNumberBean parseProductItemNumber(String productItemNumber) {
		return new ProductItemNumberBean();
	}

	@Override
	public GenericProductNumberBean parseGenericProductNumber(String number) {
		return new GenericProductNumberBean();
	}
}
