package com.trendy.fw.tools.common.util;

public class MessageKit {
	public static void addMessage(StringBuilder sb, String content, String regex) {
		if (sb == null) {
			return;
		}
		sb.append(content).append(regex);
	}
}
