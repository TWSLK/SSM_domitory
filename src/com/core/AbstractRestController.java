package com.core;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public abstract class AbstractRestController {
	protected String trueJson(Object o) {
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("flag", true);
		rtMap.put("data", o);
		return JSONObject.toJSONString(rtMap);
	}

	protected String falseJson(String error) {
		Map<String, Object> rtMap = new HashMap<String, Object>();
		rtMap.put("flag", false);
		rtMap.put("error", error);
		return JSONObject.toJSONString(rtMap);
	}
}