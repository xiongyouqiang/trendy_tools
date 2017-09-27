package com.trendy.fw.tools.criphertext;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Random;

import com.trendy.fw.common.config.Constants;
import com.trendy.fw.common.crypto.BASE64;
import com.trendy.fw.common.crypto.MD5;
import com.trendy.fw.common.crypto.RSA;
import com.trendy.fw.common.util.JsonKit;
import com.trendy.fw.common.web.ReturnMessageBean;

/**
 * 密文工具
 * 
 * @author shine.chin
 * 
 */
public class CiphertextKit {
	private static final String randomCharaters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int randomValueLength = 8;

	/**
	 * 获取random值
	 * 
	 * @param length
	 *            需要长度
	 * @return
	 */
	public static String getRandomValue(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(randomCharaters.charAt(random.nextInt(randomCharaters.length())));
		}
		return sb.toString();
	}

	/**
	 * 签名
	 * 
	 * @param bean
	 * @param md5Key
	 *            MD5 Key
	 * @return
	 */
	public static String sign(CiphertextBean bean, String md5Key) {
		String sign = CiphertextFormatter.format(bean);
		return MD5.getMD5(sign, md5Key);
	}

	/**
	 * 验证签名
	 * 
	 * @param bean
	 * @param md5Key
	 *            MD5 Key
	 * @return
	 */
	public static boolean verify(CiphertextBean bean, String md5Key) {
		String sign = CiphertextFormatter.format(bean);
		return bean.getSign().equals(MD5.getMD5(sign, md5Key));
	}

	/**
	 * 对内容进行加密
	 * 
	 * @param bean
	 *            CiphertextBean
	 * @param key
	 *            RSA的Key，可为公钥或者私钥
	 * @param md5Key
	 *            MD5 Key
	 * @return
	 */
	public static String encrypt(CiphertextBean bean, Key key, String md5Key) {
		bean.setRandomValue(getRandomValue(randomValueLength));
		bean.setTimestamp(String.valueOf(new Date().getTime()));
		bean.setSign(sign(bean, md5Key));

		String encryptStr = "";
		if (key instanceof PublicKey) {// 公钥
			encryptStr = BASE64.encodeBase64(RSA.encryptByPublicKey(JsonKit.toJson(bean).getBytes(), (PublicKey) key));
		} else {
			encryptStr = BASE64
					.encodeBase64(RSA.encryptByPrivateKey(JsonKit.toJson(bean).getBytes(), (PrivateKey) key));
		}
		return encryptStr;
	}

	/**
	 * 对密文进行解密
	 * 
	 * @param ciphertext
	 *            密文内容
	 * @param key
	 *            RSA的Key，可为公钥或者私钥
	 * @param md5Key
	 *            MD5 Key
	 * @param clazz
	 *            解释对象class，必须为CiphertextBean的扩展类
	 * @return
	 */
	public static ReturnMessageBean decrypt(String ciphertext, Key key, String md5Key,
			Class<? extends CiphertextBean> clazz) {
		ReturnMessageBean result = new ReturnMessageBean();
		String decryptStr = "";
		if (key instanceof PublicKey) {// 公钥
			decryptStr = new String(RSA.decryptByPublicKey(BASE64.decodeBase64(ciphertext), (PublicKey) key));
		} else {// 私钥
			decryptStr = new String(RSA.decryptByPrivateKey(BASE64.decodeBase64(ciphertext), (PrivateKey) key));
		}
		CiphertextBean bean = JsonKit.toBean(decryptStr, clazz);

		if (verify(bean, md5Key)) {
			result.setCode(Constants.STATUS_VALID);
			result.setMessage("OK");
			result.setContent(bean);
		} else {
			result.setCode(Constants.STATUS_NOT_VALID);
			result.setMessage("verify sign error");
		}
		return result;
	}
}
