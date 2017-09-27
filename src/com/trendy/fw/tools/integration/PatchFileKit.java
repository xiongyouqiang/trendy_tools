package com.trendy.fw.tools.integration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

public class PatchFileKit {

	private final static String prefix = "Index:";

	/**
	 * 导出增量文件
	 * 
	 * @param fileStr
	 *            补丁文件名
	 * @param baseDir
	 *            项目工程路径
	 * @param destDir
	 *            增量文件保存位置
	 * @throws Exception
	 */
	private static void export(String fileStr, String baseDir, String destDir) throws Exception {
		String srcFile = baseDir + fileStr;
		String desFile = destDir + fileStr;
		int lastIndex = desFile.lastIndexOf("/");
		String desPath = lastIndex == -1 ? destDir.toString() : desFile.substring(0, lastIndex);
		File srcF = new File(srcFile);
		if (srcF.exists()) {// 如果不存在这样的源文件，就不再拷贝，这个用来解决版本之间有删除文件的情况。
			File desF = new File(desFile);
			File desP = new File(desPath);
			if (!desP.exists()) {
				desP.mkdirs();
			}
			System.out.println("导出文件：" + fileStr);
			FileInputStream fis = new FileInputStream(srcF);
			FileOutputStream fos = new FileOutputStream(desF);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = fis.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			fos.flush();
			fos.close();
			fis.close();
		} else {
			// System.out.println("no src file:" + srcFile.toString());
		}
	}

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			if (args.length < 3) {
				System.out.println("args[0] is patch file full path");
				System.out.println("args[1] is workspace project base path");
				System.out.println("args[2] is increment files export path");
			} else {
				String configPath = args[0];
				String baseDir = args[1];
				String destDir = args[2];
				System.out.println("补丁文件：" + configPath);
				System.out.println("工程路径：" + baseDir);
				System.out.println("增量文件存放路径：" + destDir);
				System.out.println("--------------开始导出增量文件-----------------");
				try {
					BufferedReader br = new BufferedReader(new FileReader(configPath));
					String s = null;
					while ((s = br.readLine()) != null) {
						s = s.trim();// 去掉路径前面的空格
						// String str = destDir + s;
						// if (!destDir.equals(str)) {// 过滤空行
						if (s != null && s.startsWith(prefix)) {
							String fileStr = s.replaceFirst(prefix, "").trim();
							export(fileStr, baseDir, destDir);
						}
					}
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("------------- 导出增量文件结束-----------------");
			}
		}
	}

}
