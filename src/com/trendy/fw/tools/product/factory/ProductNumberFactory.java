package com.trendy.fw.tools.product.factory;

import com.trendy.fw.tools.product.config.ProductNumberConfig;
import com.trendy.fw.tools.product.util.ProductNumberParser;
import com.trendy.fw.tools.product.util.impl.ProductNumberParser10CCImpl;
import com.trendy.fw.tools.product.util.impl.ProductNumberParserDefaultImpl;
import com.trendy.fw.tools.product.util.impl.ProductNumberParserDenhamImpl;
import com.trendy.fw.tools.product.util.impl.ProductNumberParserHackettImpl;
import com.trendy.fw.tools.product.util.impl.ProductNumberParserMissSixtyImpl;
import com.trendy.fw.tools.product.util.impl.ProductNumberParserOchirlyImpl;
import com.trendy.fw.tools.product.util.impl.ProductNumberParserSuperDryImpl;

public class ProductNumberFactory {
	static public ProductNumberParser getProductNumberParser(String numberStr) {
		if (numberStr != null) {
			if (numberStr.startsWith(ProductNumberConfig.BS_OCHIRLY)
					|| numberStr.startsWith(ProductNumberConfig.BS_FIVEPLUS)
					|| numberStr.startsWith(ProductNumberConfig.BS_TRENDIANO)
					|| numberStr.startsWith(ProductNumberConfig.BS_OCHIRLYKIDS)
					|| numberStr.startsWith(ProductNumberConfig.BS_COVENGARDEN)
					|| numberStr.startsWith(ProductNumberConfig.BS_WAXY)
					|| numberStr.startsWith(ProductNumberConfig.BS_TRENDIANOWOMEN)
					|| numberStr.startsWith(ProductNumberConfig.BS_OCHIRLYKIDS_NEW)
					) {
				return new ProductNumberParserOchirlyImpl();
			} else if (numberStr.startsWith(ProductNumberConfig.BS_MISSSIXTY)
					|| numberStr.startsWith(ProductNumberConfig.BS_ENERGIE)
					|| numberStr.startsWith(ProductNumberConfig.BS_KILLAH)) {
				return new ProductNumberParserMissSixtyImpl();
			} else if (numberStr.startsWith(ProductNumberConfig.BS_SUPERDRY)) {
				return new ProductNumberParserSuperDryImpl();
			} else if (numberStr.startsWith(ProductNumberConfig.BS_HACKETT)) {
				return new ProductNumberParserHackettImpl();
			}else if (numberStr.startsWith(ProductNumberConfig.BS_DENHAM)) {
				return new ProductNumberParserDenhamImpl();
			}else if (numberStr.startsWith(ProductNumberConfig.BS_10CC)) {
				return new ProductNumberParser10CCImpl();
			}
		}
		return new ProductNumberParserDefaultImpl();
	}
}
