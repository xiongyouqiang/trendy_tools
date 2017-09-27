package com.trendy.fw.tools.portal.util;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trendy.fw.common.web.BrowserKit;
import com.trendy.fw.tools.language.util.LanguageKit;
import com.trendy.fw.tools.portal.config.PortalConfig;

public class PortalKit {
	public static void forwardPage(HttpServletRequest request, HttpServletResponse response, String pagePath)
			throws ServletException, IOException {
		String path = MessageFormat.format(pagePath, getDevicePath(request), LanguageKit.getLanguage(request)
				.toLowerCase());
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	public static String getDevicePath(HttpServletRequest request) {
		if (BrowserKit.isMobileBrowser(request)) {
			return PortalConfig.WAP_PATH;
		}
		return PortalConfig.WEB_PATH;
	}
}
