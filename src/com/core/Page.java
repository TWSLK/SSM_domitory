package com.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Page{
	// 第几页
	private int pageNum;
	// 每页大小
	private int pageSize;
	// 总条数
	private int rowTotal;
	// 总页数
	private int pageTotal;
	// 查询sql
	private String sql;
	// 返回数据集
	private List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
	
	public Page() {
		this.pageSize=10;
		this.pageNum = 1;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRowTotal() {
		return rowTotal;
	}
	public void setRowTotal(int rowTotal) {
		this.rowTotal = rowTotal;
	}
	public int getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public List<Map<String, Object>> getDataList() {
		return dataList;
	}
	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}
	
}
