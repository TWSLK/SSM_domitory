package com.util;

import java.util.ArrayList;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.core.Page;

public class WebUtils {
	
	
	/**
	 * 组装适用datatable组件的json数据
	 * @param resultPage
	 * @return
	 */
	public static String responseDataToJson(Page resultPage) {
		JSONObject retJson = new JSONObject();
		if (resultPage == null) {
			retJson.put("iTotalRecords", 0);
			retJson.put("iTotalDisplayRecords", 0);
		    retJson.put("rows", new ArrayList<Map<String, Object>>());
		    
		} else {
			retJson.put("iTotalRecords", resultPage.getRowTotal());
			retJson.put("iTotalDisplayRecords", resultPage.getPageTotal());
			retJson.put("page", resultPage.getPageNum());//数据页码
			retJson.put("rows", resultPage.getDataList());
		}
	    return retJson.toString();
	}

	
	/**
	 * 组装 错误/异常 JSON
	 * @param code
	 * @param msg
	 * @return json
	 */
	public static String errorResp(String msg) {
		JSONObject retJson = new JSONObject();
		retJson.put("success", false);
		retJson.put("msg", msg);
		retJson.put("icon", "2");
		return retJson.toString();
	}
	

	
	/**
	 * 组装正常运行JSON
	 * @param code
	 * @param msg
	 * @return json
	 */
	public static String successResp(Object data, String msg) {
		JSONObject retJson = new JSONObject();
		retJson.put("success", true);
		retJson.put("code", "OK");
		retJson.put("msg", msg == null ? "" : msg);
		retJson.put("data", data == null ? new JSONObject() : data);
		retJson.put("icon", "1");
		
		return retJson.toString();
	}
}
