package com.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
@RequestMapping("admin/usera/*")
public class UseraController extends AbstractRestController{	
	@Autowired
	CmmDao dao;
	
	private String tbNm = "user";
	
	@RequestMapping(value = "getPageList")
	@ResponseBody
	public String getPageList(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		pMap.put("tbNm", "user");
		if (MapUtil.isContains(pMap, "name")) {
			pMap.put("where", " name like '%" + pMap.get("name") + "%'");
			pMap.put("name", "");
		}
		if (pMap.containsKey("where")) {
			pMap.put("where", pMap.get("where")+" and utype<>'10'");
		} else {
			pMap.put("where", " utype<>'10'");
		}
		Page page = dao.getPage(pMap);
		List<Map<String, Object>> dataList = page.getDataList();
		if (dataList!=null) {
			for(Map<String, Object> map : dataList) {
				map.put("sexNm", dao.getCodeNm("C013", String.valueOf(map.get("sex"))));
				map.put("cardtypeNm", dao.getCodeNm("C012", String.valueOf(map.get("cardtype"))));
			}
		}
		return WebUtils.responseDataToJson(page);
	}
	
	@RequestMapping(value = "add")
	@ResponseBody
	public String add(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		String sql = "select 1 from " + this.tbNm + " where login='"+pMap.get("login")+"'";
		List<Map<String, Object>> havaList = dao.getList(sql);
		if (havaList!=null && havaList.size()>0) {
			return WebUtils.errorResp("登录账号已存在！");
		}
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
	
	@RequestMapping(value = "getInfoByLogin")
	@ResponseBody
	public String getList(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		Map<String, Object> info = dao.getInfoById(request.getSession().getAttribute("uid").toString(), this.tbNm);
		return JSON.toJSONString(info);
	}
	
	@RequestMapping(value = "update")
	@ResponseBody
	public String update(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		dao.update(pMap, this.tbNm);
		return WebUtils.successResp(null,"操作成功");
	}
	
	@RequestMapping(value = "resetPwd")
	@ResponseBody
	public String resetPwd(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		dao.update(pMap, this.tbNm);
		return WebUtils.successResp(null,"操作成功");
	}
	
	@RequestMapping(value = "updateMyPwd")
	@ResponseBody
	public String updateMyPwd(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		HttpSession session = request.getSession();
		String id = String.valueOf(session.getAttribute("uid"));
		Map<String, Object> userInfo = dao.getInfoById(id, this.tbNm);
		if (!String.valueOf(pMap.get("pwdold")).equals(userInfo.get("pwd"))) {
			return WebUtils.errorResp("原密码错误！");
		}
		dao.update(pMap, this.tbNm);
		return WebUtils.successResp(null,"操作成功");
	}
	
	
	
	
	
	
	
	
	
	

}
