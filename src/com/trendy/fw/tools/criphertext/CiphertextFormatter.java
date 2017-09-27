package com.trendy.fw.tools.criphertext;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trendy.fw.common.util.BeanKit;
import com.trendy.fw.common.util.ReflectKit;

/**
 * 密文格式化
 * 
 * @author shine.chin
 * 
 */
public class CiphertextFormatter {
	private static Logger log = LoggerFactory.getLogger(CiphertextFormatter.class);

	/**
	 * 格式化bean，过来定义为transient和内容为null的值。格式为：URL键值对，如：key1=value1&key2=value2……
	 * 
	 * @param bean
	 * @return
	 */
	public static String format(Object bean) {
		StringBuilder sb = new StringBuilder();
		try {
			List<Field> fieldList = BeanKit.getDeclaredFieldList(bean.getClass());
			TreeMap<String, Object> map = new TreeMap<String, Object>();
			for (Field field : fieldList) {
				if (!Modifier.isTransient(field.getModifiers())) {
					map.put(field.getName(), bean.getClass().getMethod(ReflectKit.getGetterName(field.getName()))
							.invoke(bean));
				}
			}
			for (Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object obj = entry.getValue();
				if (obj == null) {
					continue;
				}

				if (sb.length() > 0) {
					sb.append("&");
				}

				if (obj instanceof Map) {
					sb.append(key + "=[" + formatMap(obj) + "]");
				} else if (obj instanceof Iterable) {
					sb.append(key + "=[" + formatIterator(obj) + "]");
				} else {
					if (ReflectKit.isPrimitiveObject(obj)) {
						sb.append(key + "=" + obj.toString());
					} else {
						sb.append(key + "=[" + format(obj) + "]");
					}
				}
			}
		} catch (Exception e) {
			log.error("CiphertextFormatter format :", e);
			sb.setLength(0);
		}
		return sb.toString();
	}

	/**
	 * 格式化可循环对象。格式为：obj1|obj2，对象以URL键值对
	 * 
	 * @param obj
	 * @return
	 */
	public static String formatIterator(Object obj) {
		StringBuilder sb = new StringBuilder();
		try {
			Iterator it = ((Iterable) obj).iterator();
			while (it.hasNext()) {
				Object element = it.next();
				if (sb.length() > 0) {
					sb.append("|");
				}
				if (ReflectKit.isPrimitiveObject(element)) {
					sb.append(element.toString());
				} else {
					sb.append(format(element));
				}
			}
		} catch (Exception e) {
			log.error("CiphertextFormatter formatIterator :", e);
			sb.setLength(0);
		}
		return sb.toString();
	}

	/**
	 * 格式化map，格式为：key与value以“#”分隔，每对key、value之间以“|”分隔，如：key=value1#value=value2|
	 * key=value3#value=value4
	 * 
	 * @param map
	 * @return
	 */
	public static String formatMap(Object map) {
		StringBuilder sb = new StringBuilder();
		try {
			TreeMap<Object, Object> treeMap = new TreeMap<Object, Object>();
			treeMap.putAll((Map<Object, Object>) map);
			for (Entry<Object, Object> entry : treeMap.entrySet()) {
				Object key = entry.getKey();
				Object obj = entry.getValue();
				if (sb.length() > 0) {
					sb.append("|");
				}
				sb.append("key=" + key.toString());
				if (ReflectKit.isPrimitiveObject(obj)) {
					sb.append("#value=" + obj.toString());
				} else {
					sb.append("#value=" + format(obj));
				}
			}
		} catch (Exception e) {
			log.error("CiphertextFormatter formatMap :", e);
			sb.setLength(0);
		}
		return sb.toString();
	}
}
