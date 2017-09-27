package com.trendy.fw.tools.order.util;

import java.util.HashSet;

import com.trendy.fw.tools.order.config.DeliveryMethodConfig;

public class DeliveryMethodParser extends DeliveryMethodConfig {
	private HashSet<Integer> methodSet = new HashSet<Integer>();
	private int methodValue = 0;

	public DeliveryMethodParser() {
		methodSet = new HashSet<Integer>();
	}

	public DeliveryMethodParser(int methodValue) {
		this.methodValue = methodValue;
	}

	public DeliveryMethodParser(HashSet<Integer> methodSet) {
		this.methodSet = methodSet;
	}

	public void setMethodValue(Integer value) {
		methodSet.add(value);
	}

	public int getMethodValue() {
		methodValue = 0;
		for (Integer i : methodSet) {
			methodValue = methodValue + i;
		}
		return methodValue;
	}

	public boolean isOnline() {
		int m = (methodValue >> DM_POSE_ONLINE) & 1;
		return m == 1;
	}

	public boolean isOffline() {
		int m = (methodValue >> DM_POSE_OFFLINE) & 1;
		return m == 1;
	}

	public boolean isInShop() {
		int m = (methodValue >> DM_POSE_IN_SHOP) & 1;
		return m == 1;
	}

	public boolean isShopPost() {
		int m = (methodValue >> DM_POSE_SHOP_POST) & 1;
		return m == 1;
	}
	
	public boolean isInclude(int value) {
		int m = methodValue & value;
		return m > 0;
	}
}
