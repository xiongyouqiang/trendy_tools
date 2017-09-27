package com.trendy.fw.tools.common.util;

import java.util.HashSet;

import com.trendy.fw.tools.common.config.SourceConfig;

public class SourceParser extends SourceConfig {
	private HashSet<Integer> sourceSet = new HashSet<Integer>();
	private int sourceValue = 0;

	public SourceParser() {
		sourceSet = new HashSet<Integer>();
	}

	public SourceParser(int sourceValue) {
		this.sourceValue = sourceValue;
	}

	public SourceParser(HashSet<Integer> sourceSet) {
		this.sourceSet = sourceSet;
	}

	public void setSourceValue(Integer value) {
		sourceSet.add(value);
	}

	public int getSourceValue() {
		sourceValue = 0;
		for (Integer i : sourceSet) {
			sourceValue = sourceValue + i;
		}
		return sourceValue;
	}

	public boolean isOnline() {
		int m = (sourceValue >> SF_POSE_ONLINE) & 1;
		return m == 1;
	}

	public boolean isOffline() {
		int m = (sourceValue >> SF_POSE_OFFLINE) & 1;
		return m == 1;
	}

	public boolean isInclude(int value) {
		int m = sourceValue & value;
		return m > 0;
	}
}
