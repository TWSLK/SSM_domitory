package com.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.util.StringUtils;

/**
 * Map辅助类
 */
public final class MapUtil {

	/**
	 * 检查参数列表不为null或"".
	 * 
	 * @param paramList
	 *            the param list
	 * @return true, if successful
	 */
	public static boolean checkParamIsNotEmptyForList(final List<String> paramList) {
		boolean flag = true;
		for (String str : paramList) {
			if (!StringUtils.hasText(str) || str.equalsIgnoreCase("null")) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * 检查列表中参数不为Null和"".
	 * 
	 * @param fieldList
	 *            the field list
	 * @param param
	 *            the param
	 * @return the string
	 */
	public static String checkParamIsNotEmptyForList(final List<String> fieldList, Map<String, Object> param) {
		String checked = "";
		for (String str : fieldList) {
			String val = String.valueOf(param.get(str));
			if (!StringUtils.hasText(val) || str.equalsIgnoreCase("null")) {
				checked = val + ",";
				continue;
			}
		}
		return checked;
	}

	/**
	 * 检查Map的参数列表中参数不为null或"".
	 * 
	 * @param param
	 *            参数列表
	 * @return true, if successful
	 */
	public static boolean checkParamIsNotEmptyForMap(final Map<String, Object> param) {
		Map<String, Object> map = Collections.synchronizedMap(param);
		Set<String> keys = map.keySet();
		List<String> list = new ArrayList<String>();
		for (String key : keys) {
			list.add(String.valueOf(map.get(key)));
		}
		return checkParamIsNotEmptyForList(list);
	}

	/**
	 * 判断Map中是否缺少比传参数.
	 * 
	 * @param param
	 * @param key
	 * @return
	 */
	public static boolean isContains(final Map<String, Object> param, String... keys) {
		boolean flag = true;
		if (param == null){
			flag = false;
		} else {
			for (String str : keys) {
				if (!param.containsKey(str) || param.get(str) == null || String.valueOf(param.get(str)).equals("") || String.valueOf(param.get(str)).equalsIgnoreCase("null")) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 判断Map是否包含key列表.
	 * 
	 * @param param
	 * @param keys
	 * @return
	 */
	public static boolean isContainsKey(final Map<String, Object> param, String... keys) {
		boolean flag = true;
		for (String str : keys) {
			if (!param.containsKey(str)) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * 验证传入的map是否有value
	 * 
	 * @param parMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean checkMapHaveVal(Map parMap) {
		Set set = parMap.entrySet();
		Iterator iter = set.iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Entry) iter.next();
			if (entry.getValue() == null || entry.getValue() == "" || entry.getValue() == "null") {
				// 没有验证通过 返回false
				return false;
			}
		}
		// 全部有值,返回true
		return true;
	}

	/**
	 * 检查Map是否包含制定的key,如果全部包含返回true,否则返回false.
	 * 
	 * @param params
	 *            map参数
	 * @param checkedList
	 *            是否包含这些keys
	 * @return
	 */
	public static boolean checkMapHaveKey(Map<String, String> params, List<String> checkedList) {
		boolean isContains = true;
		for (Iterator<String> it = checkedList.iterator(); it.hasNext();) {
			String key = it.next();
			if (!params.containsKey(key)) {
				isContains = false;
				break;
			}
		}

		return isContains;
	}

}
