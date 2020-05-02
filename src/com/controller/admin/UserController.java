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
import com.alibaba.fastjson.JSONObject;
import com.core.AbstractRestController;
import com.core.Page;
import com.dao.CmmDao;
import com.util.MapUtil;
import com.util.StringUtil;
import com.util.WebUtils;

@Controller
@RequestMapping("admin/user/*")
public class UserController extends AbstractRestController{	
	@Autowired
	CmmDao dao;
	
	private String tbNm = "user";
	
	@ResponseBody
	@RequestMapping("login")
	public String login(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		JSONObject json = new JSONObject();
		Boolean flag = false;
		String msg = "";
		String login = pMap.get("login").toString();
		String pwd = pMap.get("pwd").toString();
		String sql = "select * from user where login='"+ login +"'";
		if (MapUtil.isContains(pMap, "utype")){
			sql += " and utype='"+pMap.get("utype")+"'";
		}
		Map<String, Object> userInfo = dao.getInfo(sql);
		if (userInfo.isEmpty()) {
			msg = "登录账户不存在";
		} else {
			if (pwd.equals(userInfo.get("pwd"))) {
				flag = true;
				HttpSession session = request.getSession();
				session.setAttribute("uid", userInfo.get("id"));
				session.setAttribute("name", userInfo.get("name"));
				session.setAttribute("login", userInfo.get("login"));
				session.setAttribute("utype", userInfo.get("utype"));
			} else {
				msg = "登录密码错误";
			}
		}
		json.put("flag", flag);
		json.put("msg", msg);
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("logout")
	public String logout(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		JSONObject json = new JSONObject();
		HttpSession session = request.getSession();
		session.setAttribute("uid", "");
		session.setAttribute("name", "");
		session.setAttribute("login", "");
		session.setAttribute("utype", "");
		json.put("flag", true);
		json.put("msg", "");
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping("permission")
	public String permission(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		JSONObject json = new JSONObject();
		Boolean flag = false;
		String msg = "";
		HttpSession session = request.getSession();
		if (!StringUtil.isEmpty(session.getAttribute("uid"))) {
			flag = true;
		} else {
			msg = "请登录";
		}
		
		json.put("flag", flag);
		json.put("msg", msg);
		return json.toJSONString();
	}
	
	
	@RequestMapping(value = "getPageList")
	@ResponseBody
	public String getPageList(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		pMap.put("tbNm", "user");
		if (MapUtil.isContains(pMap, "name")) {
			pMap.put("where", " name like '%" + pMap.get("name") + "%'");
			pMap.put("name", "");
		}
		Page page = dao.getPage(pMap);
		List<Map<String, Object>> dataList = page.getDataList();
		if (dataList!=null) {
			for(Map<String, Object> map : dataList) {
				String name = dao.getCodeNm("C001", String.valueOf(map.get("utype")));
				map.put("usertypeNm", name);
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
