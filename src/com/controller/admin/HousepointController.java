package com.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.core.AbstractRestController;
import com.core.Page;
import com.dao.CmmDao;
import com.util.MapUtil;
import com.util.WebUtils;

@Controller
@RequestMapping("admin/housepoint/*")
public class HousepointController extends AbstractRestController{	
	@Autowired
	CmmDao dao;
	
	private String tbNm = "house_point";
	
	@RequestMapping(value = "getPageList")
	@ResponseBody
	public String getPageList(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		pMap.put("tbNm", tbNm);
		if (MapUtil.isContains(pMap, "name")) {
			pMap.put("where", " name like '%" + pMap.get("name") + "%'");
			pMap.put("name", "");
		}
		Page page = dao.getPage(pMap);
		List<Map<String, Object>> dataList = page.getDataList();
		if (dataList!=null) {
			for(Map<String, Object> map : dataList) {
			}
		}
		return WebUtils.responseDataToJson(page);
	}
	
	@RequestMapping(value = "add")
	@ResponseBody
	public String add(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		Map<String, Object> houseMap = dao.getInfo("select * from house where no='"+pMap.get("no")+"'");
		if (houseMap.isEmpty()) {
			return WebUtils.errorResp("宿舍编号不存在！");
		}
		
		pMap.put("iuid", request.getSession().getAttribute("uid"));
		dao.add(pMap, this.tbNm);
		return WebUtils.successResp(null,"操作成功");
	}
	
	@RequestMapping(value = "getInfo")
	@ResponseBody
	public String getInfo(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		Map<String, Object> info = dao.getInfoById(String.valueOf(pMap.get("id")), this.tbNm);
		String rtStr = JSON.toJSONString(info);
		return rtStr;
	}
	
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		dao.update(pMap, this.tbNm);
		return WebUtils.successResp(null,"操作成功");
	}

}
