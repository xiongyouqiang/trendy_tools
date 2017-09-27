package com.trendy.fw.tools.product.util;

import com.trendy.fw.tools.product.bean.CommodityNumberBean;
import com.trendy.fw.tools.product.bean.GenericProductNumberBean;
import com.trendy.fw.tools.product.bean.ProductItemNumberBean;
import com.trendy.fw.tools.product.bean.ProductNumberBean;

public abstract class ProductNumberParser {
	public abstract CommodityNumberBean parseCommodityNumber(String commodityNumber);

	public abstract ProductNumberBean parseProductNumber(String productNumber);

	public abstract ProductItemNumberBean parseProductItemNumber(String productItemNumber);

	public abstract GenericProductNumberBean parseGenericProductNumber(String number);
}
