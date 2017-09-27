package com.trendy.fw.tools.common.util;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelKit {
	private static final String exponent2StrRegex = "^\\d\\.\\d+E\\d+$";
	private static Pattern exponent2StrPattern = Pattern.compile(exponent2StrRegex);

	public static String exponent2Str(String str) {
		Matcher matcher = exponent2StrPattern.matcher(str);
		if (matcher.find()) {
			str = new DecimalFormat("####").format(Double.parseDouble(str));
		}
		return str;
	}
}
