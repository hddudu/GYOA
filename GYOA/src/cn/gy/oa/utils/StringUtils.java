package cn.gy.oa.utils;

public class StringUtils {
	
	/**
	 * @param str
	 * @return
	 */
	public static String removeNullPointer(String str) {
		return str == null ? "" : str;
	}
}
