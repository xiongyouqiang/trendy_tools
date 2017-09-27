package com.trendy.fw.tools.product.util;

import java.util.ArrayList;
import java.util.List;

import com.trendy.ec.system.business.SystemInfoHelper;
import com.trendy.fw.common.util.ListKit;
import com.trendy.fw.common.util.StringKit;
import com.trendy.fw.tools.common.util.MessageKit;
import com.trendy.fw.tools.product.bean.CommodityNumberBean;
import com.trendy.fw.tools.product.bean.ProductNumberBean;

public class ProductNumberHelper {
	/**
	 * 检查货品的货号是否合法
	 * 
	 * @param systemInfoHelper
	 * @param inputStr
	 *            textarea的内容
	 * @param resultList
	 * @param resultMsg
	 * @return
	 */
	public static boolean checkCommodityNumber(SystemInfoHelper systemInfoHelper, String inputStr,
			List<String> resultList, StringBuilder resultMsg, boolean isBreak) {
		List<String> inputList = ListKit.string2List(inputStr, "\r");
		return checkCommodityNumber(systemInfoHelper, inputList, resultList, resultMsg, isBreak);
	}

	/**
	 * 检查货品的货号是否合法
	 * 
	 * @param systemInfoHelper
	 * @param inputList
	 * @param resultList
	 * @param resultMsg
	 * @return
	 */
	public static boolean checkCommodityNumber(SystemInfoHelper systemInfoHelper, List<String> inputList,
			List<String> resultList, StringBuilder resultMsg, boolean isBreak) {
		boolean result = true;
		for (String commodityNumber : inputList) {
			if (StringKit.isValid(commodityNumber)) {
				commodityNumber = commodityNumber.trim();
				if (commodityNumber.length() <= 0) {
					continue;
				}
				CommodityNumberBean bean = CommodityNumberKit.parseCommodityNumber(commodityNumber);
				if (bean == null || !bean.isValid()) {
					addMessage(resultMsg, "货号[" + commodityNumber + "]不是有效的货品编号");
					result = false;
					if (isBreak) {
						break;
					} else {
						continue;
					}
				}
				if (!systemInfoHelper.isUserValidBrandByCode(bean.getBrandCode())) {
					addMessage(resultMsg, "货号[" + commodityNumber + "]没有权限");
					result = false;
					if (isBreak) {
						break;
					} else {
						continue;
					}
				}
				resultList.add(commodityNumber);
			}
		}
		return result;
	}

	/**
	 * 检查货品的sku是否合法
	 * 
	 * @param systemInfoHelper
	 * @param inputStr
	 *            textarea的内容
	 * @param resultList
	 * @param resultMsg
	 * @return
	 */
	public static boolean checkProductNumber(SystemInfoHelper systemInfoHelper, String inputStr,
			List<String> resultList, StringBuilder resultMsg, boolean isBreak) {
		List<String> inputList = ListKit.string2List(inputStr, "\r");
		return checkProductNumber(systemInfoHelper, inputList, resultList, resultMsg, isBreak);
	}

	/**
	 * 检查商品的货号是否合法
	 * 
	 * @param systemInfoHelper
	 * @param inputList
	 * @param resultList
	 * @param resultMsg
	 * @return
	 */
	public static boolean checkProductNumber(SystemInfoHelper systemInfoHelper, List<String> inputList,
			List<String> resultList, StringBuilder resultMsg, boolean isBreak) {
		boolean result = true;
		for (String sku : inputList) {
			if (StringKit.isValid(sku)) {
				sku = sku.trim();
				if (sku.length() <= 0) {
					continue;
				}
				ProductNumberBean bean = ProductNumberKit.parseProductNumber(sku);
				if (!bean.isValid()) {
					addMessage(resultMsg, "sku[" + sku + "]非法");
					result = false;
					if (isBreak) {
						break;
					} else {
						continue;
					}
				}
				if (!systemInfoHelper.isUserValidBrandByCode(bean.getBrandCode())) {
					addMessage(resultMsg, "sku[" + sku + "]没有权限");
					result = false;
					if (isBreak) {
						break;
					} else {
						continue;
					}
				}
				resultList.add(sku);
			}
		}
		return result;
	}

	public static List<String> filterNumberList(SystemInfoHelper infoHelper, List<String> list) {
		List<String> result = new ArrayList<String>();
		for (String sku : list) {
			if (infoHelper.isUserValidSku(sku)) {
				result.add(sku);
			}
		}
		return result;
	}

	private static void addMessage(StringBuilder sb, String content) {
		MessageKit.addMessage(sb, content, "<br/>");
	}
}
