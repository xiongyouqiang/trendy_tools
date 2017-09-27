package com.trendy.fw.tools.language.util;

import javax.servlet.http.HttpServletRequest;

import com.trendy.fw.tools.language.config.LanguageConfig;

public class LanguageKit {
	/**
	 * 通过分析URL，获取请求的语言，默认简体中文。语言位为域名后面第一段
	 * 
	 * @param request
	 * @return
	 */
	public static String getLanguage(HttpServletRequest request) {
		String language = LanguageConfig.LANG_CN;
		try {
			String url = request.getRequestURI();
			String[] urlArr = url.split("/");
			String langFlag = urlArr[1].toUpperCase();
			if (LanguageConfig.LANGUAGE_MAP.containsKey(langFlag)) {
				language = langFlag;
			}
		} catch (Exception e) {
		}
		return language;
	}
}
