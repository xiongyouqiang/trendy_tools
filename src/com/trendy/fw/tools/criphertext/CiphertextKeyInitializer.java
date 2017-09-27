package com.trendy.fw.tools.criphertext;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trendy.fw.common.crypto.RSA;
import com.trendy.fw.common.util.StringKit;

public class CiphertextKeyInitializer {
	private static Logger log = LoggerFactory.getLogger(CiphertextKeyInitializer.class);

	// 配置文件路径
	private static String CIPHERTEXT_KEY_CONFIG_PATH = "/config/ciphertext_key_config.xml";

	private static Map<Integer, CiphertextKeyBean> ciphertextKeyByAppIdMap = new HashMap<Integer, CiphertextKeyBean>();
	private static Map<String, CiphertextKeyBean> ciphertextKeyByAppCodeMap = new HashMap<String, CiphertextKeyBean>();

	static {
		init();
	}

	private static void init() {
		initCiphertextKeyMap();
	}

	/**
	 * 读取配置文件内容到Map
	 */
	private static void initCiphertextKeyMap() {
		try {
			InputStreamReader in = new InputStreamReader(
					CiphertextKeyInitializer.class.getResourceAsStream(CIPHERTEXT_KEY_CONFIG_PATH));
			SAXReader reader = new SAXReader();
			Document document = reader.read(in);
			Element root = document.getRootElement();
			Iterator<Element> iter = root.elementIterator("ciphertext_key");

			while (iter.hasNext()) {
				CiphertextKeyBean bean = new CiphertextKeyBean();
				Element config = iter.next();

				bean.setAppId(Integer.parseInt(config.elementText("app_id")));
				bean.setAppCode(config.elementText("app_code"));
				bean.setMd5Key(config.elementText("md5_key"));

				String rsaPublicKey = config.elementText("rsa_publice_key");
				if (StringKit.isValid(rsaPublicKey)) {
					bean.setRsaPublicKey(RSA.getPublicKey(rsaPublicKey));
				}

				String rsaPrivateKey = config.elementText("rsa_private_key");
				if (StringKit.isValid(rsaPrivateKey)) {
					bean.setRsaPrivateKey(RSA.getPrivateKey(rsaPrivateKey));
				}

				ciphertextKeyByAppIdMap.put(bean.getAppId(), bean);
				ciphertextKeyByAppCodeMap.put(bean.getAppCode(), bean);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("[CipherKey配置]加载CipherKey配置文件异常：", e);
		}
	}

	public static CiphertextKeyBean getCiphertextKey(int appId) {
		return ciphertextKeyByAppIdMap.get(appId);
	}

	public static CiphertextKeyBean getCiphertextKey(String appCode) {
		return ciphertextKeyByAppCodeMap.get(appCode);
	}
}
