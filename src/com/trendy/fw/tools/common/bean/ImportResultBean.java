package com.trendy.fw.tools.common.bean;

import java.util.ArrayList;
import java.util.List;

public class ImportResultBean {
	List<String> successMsgList = new ArrayList<String>();
	List<String> errorMsgList = new ArrayList<String>();

	public List<String> getSuccessMsgList() {
		return successMsgList;
	}

	public void setSuccessMsgList(List<String> successMsgList) {
		this.successMsgList = successMsgList;
	}

	public List<String> getErrorMsgList() {
		return errorMsgList;
	}

	public void setErrorMsgList(List<String> errorMsgList) {
		this.errorMsgList = errorMsgList;
	}

	public void setSuccessMsg(Object obj) {
		this.successMsgList.add(String.valueOf(obj));
	}

	public void setErrorMsg(Object obj) {
		this.errorMsgList.add(String.valueOf(obj));
	}
}
