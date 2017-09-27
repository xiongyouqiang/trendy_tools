package com.trendy.fw.tools.criphertext;

public class CiphertextBean {
	/** 随机值 */
	protected String randomValue = "";
	/** 时间戳 */
	protected String timestamp = "";
	/** 签名 */
	protected transient String sign = "";
	/** 数据内容 */
	protected Object data = null;

	public String getRandomValue() {
		return randomValue;
	}

	public void setRandomValue(String randomValue) {
		this.randomValue = randomValue;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
