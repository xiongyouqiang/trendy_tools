package com.trendy.fw.tools.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class DomainKit {
	private static String domainRule = "^.*\\.(.*\\.com(\\.cn)?)$";
	private static Pattern domainPattern = Pattern.compile(domainRule, Pattern.CASE_INSENSITIVE);

	/**
	 * 通过request获取顶级域名
	 * 
	 * @param request
	 * @return
	 */
	public static String getTopDomain(HttpServletRequest request) {
		String serverName = request.getServerName();
		return getTopDomain(serverName);
	}

	/**
	 * 通过域名获取顶级域名
	 * 
	 * @param serverName
	 * @return
	 */
	public static String getTopDomain(String serverName) {
		String topDomain = "";
		Matcher matcher = domainPattern.matcher(serverName);
		if (matcher.find()) {
			topDomain = matcher.group(1);
		}
		return topDomain;
	}
}
