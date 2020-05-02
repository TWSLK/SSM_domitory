package com.util;

import java.util.List;

public final class StringUtil {

	/** 汉字. */
	public static final int TYPE_CHINESE = 1;

	/** 数字. */
	public static final int TYPE_DIGIT = 2;

	/** 字母. */
	public static final int TYPE_LETTER = 3;

	/** 其他. */
	public static final int TYPE_OTHER = 4;

	/**
	 * 将以逗号分割的字符串列表中添加上单引号.如1,2,3,4,5,6转化为'1','2','3','4','5','6'.
	 * 
	 * @param str
	 *            字符串
	 * @return the string
	 */
	public static String batchAddDYH(String str) {
		str = str.replaceAll("'", "");
		String[] strs = str.split(",");
		String s = "";
		for (int i = 0; i < strs.length; i++) {
			if (s.equals("")) {
				s = "'" + strs[i] + "'";
			} else {
				s = s + "," + "'" + strs[i] + "'";
			}
		}
		return s;
	}
	
	/**
	 * 将字符串两侧添加上单引号.
	 * 
	 * @param str
	 *            the str
	 * @return the string
	 */
	public static String addDYH(String str) {
		return "'" + str + "'";
	}

	/**
	 * 根据char类型数据进行判断字符串是汉字,数字,字母,其他.
	 * 
	 * @param ch
	 *            the ch
	 * @return the int
	 */
	public static int charDistinguish(char ch) {
		int flag = 0;
		// 汉字
		if (Character.getType(ch) == Character.OTHER_LETTER) {
			flag = TYPE_CHINESE;
		}
		// 数字
		else if (Character.isDigit(ch)) {
			flag = TYPE_DIGIT;
		}
		// 字母
		else if (Character.isLetter(ch)) {
			flag = TYPE_LETTER;
		}
		// 其它字符
		else {
			flag = TYPE_OTHER;
		}
		return flag;
	}

	public static boolean isEmpty(String str){
		boolean flag=false;
		if (str==null || "".equals(str) || "null".equals(str)){
			flag=true;
		}
		return flag;
	}	
	
	public static boolean isEmpty(Object str){
		boolean flag=false;
		if (str==null || "".equals(str) || "null".equals(str)){
			flag=true;
		}
		return flag;
	}	
	
	/**
	 * @Description:把list转换为一个用逗号分隔的字符串
	 */
	public static String listToString(List list) {
		StringBuilder sb = new StringBuilder();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i < list.size() - 1) {
					sb.append(list.get(i) + ",");
				} else {
					sb.append(list.get(i));
				}
			}
		}
		return sb.toString();
	}
	
}
