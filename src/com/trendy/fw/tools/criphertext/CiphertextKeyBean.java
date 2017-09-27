package com.trendy.fw.tools.criphertext;

import java.security.PrivateKey;
import java.security.PublicKey;

public class CiphertextKeyBean {
	private int appId = 0;
	private String appCode = "";
	private String md5Key = "";
	private PublicKey rsaPublicKey = null;
	private PrivateKey rsaPrivateKey = null;

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getMd5Key() {
		return md5Key;
	}

	public void setMd5Key(String md5Key) {
		this.md5Key = md5Key;
	}

	public PublicKey getRsaPublicKey() {
		return rsaPublicKey;
	}

	public void setRsaPublicKey(PublicKey rsaPublicKey) {
		this.rsaPublicKey = rsaPublicKey;
	}

	public PrivateKey getRsaPrivateKey() {
		return rsaPrivateKey;
	}

	public void setRsaPrivateKey(PrivateKey rsaPrivateKey) {
		this.rsaPrivateKey = rsaPrivateKey;
	}
}
