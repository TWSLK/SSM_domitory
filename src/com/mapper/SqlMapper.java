package com.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface SqlMapper {
	/**
	 * 查询列表
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> getList(@Param("sql") String sql);
	
	/**
	 * 查询信息
	 * @param sql
	 * @return
	 */
	public Map<String, Object> getInfo(@Param("sql") String sql);
	
	/**
	 * 添加数据
	 * @param sql
	 */
	public Integer add(@Param("sql") String sql);
	
	/**
	 * 修改数据
	 * @param sql
	 */
	public void update(@Param("sql") String sql);
	
	/**
	 * 删除数据
	 * @param sql
	 */
	public void del(@Param("sql") String sql);
	
	public void runSql(@Param("sql") String sql);
	
}
