package com.trendy.fw.tools.exception;

import javax.servlet.ServletException;

public class PortalServletException extends ServletException {

	private static final long serialVersionUID = 2577789380799128518L;

	private String appId;
	private int errorCode;

	public PortalServletException(String appId, int errorCode, String msg) {
		super(msg);
		this.appId = appId;
		this.errorCode = errorCode;
	}

	public PortalServletException(String appId, int errorCode, Exception e) {
		super(e);
		this.appId = appId;
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getAppId() {
		return appId;
	}
}
