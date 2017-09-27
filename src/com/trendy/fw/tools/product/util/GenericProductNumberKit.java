package com.trendy.fw.tools.product.util;

import com.trendy.fw.tools.product.bean.GenericProductNumberBean;
import com.trendy.fw.tools.product.factory.ProductNumberFactory;

public class GenericProductNumberKit {
	static public GenericProductNumberBean parseGenericProductNumber(String number) {
		number = number.toUpperCase();
		ProductNumberParser parser = ProductNumberFactory.getProductNumberParser(number);
		return parser.parseGenericProductNumber(number);
	}
}
