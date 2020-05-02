package com.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.core.Page;
import com.dao.CmmDao;


public class PageUtil{
	public static Page getPage(Map<String, Object> pMap){
		if (!MapUtil.isContains(pMap, "sql")) {
			throw new RuntimeException("分页SQL不能为空");
		}
		String sql = String.valueOf(pMap.get("sql"));
		Integer pageNum = 1, pageSize = 10;
		Page page = new Page();
		if (MapUtil.isContains(pMap, "pageNum")) {
			pageNum = Integer.valueOf(pMap.get("pageNum").toString());
		}
		if (MapUtil.isContains(pMap, "pageSize")) {
			pageSize = Integer.valueOf(pMap.get("pageSize").toString());
		}
		if (sql.indexOf("SELECT")==-1 || sql.indexOf("FROM")==-1) {
			throw new RuntimeException("分页SQL查询关键词(SELECT,FROM)未找到");
		}
		Integer pageStart = (pageNum-1)*pageSize;
		String sqlData = sql + " limit " + pageStart + "," + pageSize;
		CmmDao dao = new CmmDao();
		List<Map<String, Object>> dataList = dao.getList(sqlData);
		
		String Regex = "^SELECT [\\s\\S])* FROM";
		String sqlTotal = sql.replaceAll(Regex, " count(1) rowtotal ");
		Map<String, Object> totalMap = dao.getInfo(sqlTotal);
		Integer rowTotal = Integer.valueOf(totalMap.get("rowtotal").toString());
		Integer pageTotal = (int)Math.floor(rowTotal/pageSize) + 1;
		
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);
		page.setPageTotal(pageTotal);
		page.setRowTotal(rowTotal);
		page.setDataList(dataList);
		return page;
	}
	
	public static List<Map<String, Object>> getPageData(Map<String, Object> pMap){
		Page page = PageUtil.getPage(pMap);
		return page.getDataList();
	}
}
