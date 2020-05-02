package com.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;

import com.mapper.SqlMapper;
import com.util.MapUtil;
import com.util.StringUtil;

@SuppressWarnings("unchecked")
public abstract class AbstractDao {
	@Autowired
	private SqlMapper sqlMapper;
	private static String dbName = "";
	
	static {
		Properties properties = new Properties();
		InputStream inputStream = AbstractDao.class.getClassLoader().getResourceAsStream("config/db.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		dbName = properties.getProperty("jdbc_dbname", "mydb");
	}
	public String add(Map<String, Object> pMap, String tbNm) {		
		String rtId = "";
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (pMap!=null && !pMap.isEmpty()) {
			List<String> columnList = this.getTbColumnList(tbNm);
			for(String key : pMap.keySet()){
				if (columnList.contains(key)) {
					dataMap.put(key, pMap.get(key));
				}
			}
		}
		//插入数据
		if (dataMap!=null && !dataMap.isEmpty()) {
			String sql = "insert into "+tbNm+ "(";
			for(String key : dataMap.keySet()){
				sql += key+",";
			}
			sql = sql.substring(0, sql.length()-1);
			sql += ") values (";
			for(String key : dataMap.keySet()){
				String val = MapUtil.isContains(dataMap, key)?String.valueOf(dataMap.get(key)).replaceAll("'", ""):"";
				sql += "'"+val+"',";
			}
			sql = sql.substring(0, sql.length()-1) + ")";
			sqlMapper.add(sql);
			sql = "SELECT LAST_INSERT_ID() as id";
			Map<String, Object> info = sqlMapper.getInfo(sql);
			if (MapUtil.isContains(info, "id")) {
				rtId = String.valueOf(info.get("id"));
			}
		}
		return rtId;
	}
	
	public void del(String ids, String tbNm) {	
		String sql = "delete from "+tbNm+" where id in("+ids+")";
		sqlMapper.del(sql);
	}
	
	public void delBatch(String whereStr, String tbNm) {	
		String sql = "delete from "+tbNm+" where " + whereStr;
		sqlMapper.del(sql);
	}
	
	public void runSql(String sql) {	
		sqlMapper.runSql(sql);
	}
	
	public void update(Map<String, Object> pMap, String tbNm) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (pMap!=null && !pMap.isEmpty()) {
			List<String> columnList = this.getTbColumnList(tbNm);
			for(String key : pMap.keySet()){
				if (columnList.contains(key)) {
					dataMap.put(key, pMap.get(key));
				}
			}
		}
		//修改数据
		if (dataMap!=null && !dataMap.isEmpty()) {
			String sql = "update "+tbNm+ " set ";
			for(String key : dataMap.keySet()){
				if(!"id".equals(key)) {
					String val = MapUtil.isContains(dataMap, key)?String.valueOf(dataMap.get(key)).replaceAll("'", ""):"";
					sql += key+"='"+val+"',";
				}
			}
			sql = sql.substring(0, sql.length()-1);
			sql += " where id="+pMap.get("id");
			sqlMapper.update(sql);
		}
	}
	
	public void update(Map<String, Object> pMap, String whereStr, String tbNm) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (pMap!=null && !pMap.isEmpty()) {
			List<String> columnList = this.getTbColumnList(tbNm);
			for(String key : pMap.keySet()){
				if (columnList.contains(key)) {
					dataMap.put(key, pMap.get(key));
				}
			}
		}
		if (dataMap!=null && !dataMap.isEmpty()) {
			String sql = "update "+tbNm+ " set ";
			for(String key : dataMap.keySet()){
				String val = MapUtil.isContains(dataMap, key)?String.valueOf(dataMap.get(key)).replaceAll("'", ""):"";
				sql += key+"='"+val+"',";
			}
			sql = sql.substring(0, sql.length()-1);
			sql += " where "+whereStr;
			sqlMapper.update(sql);
		}
	}
	
	public List<Map<String, Object>> getList(Map<String, Object> pMap, String tbNm) {
		String sql = this.getSelectSql(pMap, tbNm);
		List<Map<String, Object>> list = sqlMapper.getList(sql);
		return list;
	}
	
	public List<Map<String, Object>> getList(String sql) {
		return sqlMapper.getList(sql);
	}
	
	public String getFieldValById(String id, String fieldNm, String tableNm) {
		String rtStr = "";
		if (StringUtil.isEmpty(id) || StringUtil.isEmpty(fieldNm) || StringUtil.isEmpty(tableNm) ) {
			return rtStr;
		}
		String sql = "select * from "+tableNm+" where id="+id;
		List<Map<String, Object>> list = sqlMapper.getList(sql);
		Map<String, Object> rtMap = new HashMap<>();
		if (list!=null && list.size()>0) {
			rtMap = list.get(0);
			if (MapUtil.isContains(rtMap, fieldNm)) {
				rtStr = rtMap.get(fieldNm).toString();
			}
		}
		return rtStr;
	}
	
	public Map<String, Object> getInfo(String sql) {
		List<Map<String, Object>> list = sqlMapper.getList(sql);
		Map<String, Object> rtMap = new HashMap<>();
		if (list!=null && list.size()>0) {
			rtMap = list.get(0);
		}
		return rtMap;
	}
	
	public Map<String, Object> getInfoById(String id, String tableNm) {
		if (StringUtil.isEmpty(id)) {
			return new HashMap<>();
		}
		String sql = "select * from "+tableNm+" where id="+id;
		List<Map<String, Object>> list = sqlMapper.getList(sql);
		Map<String, Object> rtMap = new HashMap<>();
		if (list!=null && list.size()>0) {
			rtMap = list.get(0);
		}
		return rtMap;
	}
	
	
	public Page getPage(Map<String, Object> pMap) {
		String sql = "";
		if (MapUtil.isContains(pMap, "sql")) {
			sql = String.valueOf(pMap.get("sql"));
		} else if (MapUtil.isContains(pMap, "tbNm")) {
			if (!MapUtil.isContains(pMap, "orderby")) {
				pMap.put("orderby", " id desc ");
			}
			sql = this.getSelectSql(pMap, pMap.get("tbNm").toString());
		} else {
			throw new RuntimeException("分页查询参数sql,tbNm不能同时为空");
		}
		Integer pageNum = 1, pageSize = 10;
		Page page = new Page();
		if (MapUtil.isContains(pMap, "page")) {
			pageNum = Integer.valueOf(pMap.get("page").toString());
		}
		if (MapUtil.isContains(pMap, "rows")) {
			pageSize = Integer.valueOf(pMap.get("rows").toString());
		}
		if (sql.indexOf("Select")==-1 || sql.indexOf("From")==-1) {
			throw new RuntimeException("分页SQL查询关键词(Select,From)未找到");
		}
		Integer pageStart = (pageNum-1)*pageSize;
		String sqlData = sql + " limit " + pageStart + "," + pageSize;
		System.out.println(sqlData);
		List<Map<String, Object>> dataList = this.getList(sqlData);
		String Regex = "^Select[\\s\\S]*From";
		String sqlTotal = sql.replaceAll(Regex, "Select count(1) rowtotal From");
		Map<String, Object> totalMap = this.getInfo(sqlTotal);
		Integer rowTotal = Integer.valueOf(totalMap.get("rowtotal").toString());
		Integer pageTotal = (int)Math.floor(rowTotal/pageSize);
		if (rowTotal%pageSize!=0) {
			pageTotal ++;
		}
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);
		page.setPageTotal(pageTotal);
		page.setRowTotal(rowTotal);
		page.setDataList(dataList);
		return page;
	}
	
	private String getSelectSql(Map<String, Object> pMap, String tbNm) {
		List<String> tbColumnList = this.getTbColumnList(tbNm);
		String selectStr = MapUtil.isContains(pMap, "select")?","+String.valueOf(pMap.get("select")):"";
		String sql = "Select *"+ selectStr +" From " + tbNm + " where 1=1 ";
		String limitStr = "";
		String orderByStr = "";
		String groupByStr = "";
		String havingStr = "";
		if (pMap!=null && !pMap.isEmpty()) {
			for(String key : pMap.keySet()){
				String str = MapUtil.isContains(pMap, key)?String.valueOf(pMap.get(key)):"";
				if ("limit".equals(key.toLowerCase())) {
					limitStr = " limit "+str;
				} else if ("orderby".equals(key.toLowerCase())){
					orderByStr = " order by "+str;
				}else if ("groupby".equals(key.toLowerCase())){
					groupByStr = " group by "+str;
				}else if ("having".equals(key.toLowerCase())){
					havingStr = " having "+str;
				}else if ("where".equals(key.toLowerCase())){
					sql += " and "+str;
				}else {
					if (!tbColumnList.contains(key)) {
						continue;
					}
					if (MapUtil.isContains(pMap, key)) {
						sql += " and "+key+"="+StringUtil.addDYH(String.valueOf(str)); 
					}
				}
			}
			sql += groupByStr;
			sql += havingStr;
			sql += orderByStr;
			sql += limitStr;
		}
		return sql;
	}
	
	private List<String> getTbColumnList(String tbNm) {
		List<String> columnList = new ArrayList<String>();
		//查询数据表字段
		String sql = "select * from information_schema.columns where table_schema='"+dbName+"' and table_name='"+tbNm+"' ";
		List<Map<String, Object>> colList = sqlMapper.getList(sql);
		if (colList!=null && colList.size()>0) {
			for (Map<String, Object> map : colList) {
				String colName = MapUtil.isContains(map, "COLUMN_NAME")?String.valueOf(map.get("COLUMN_NAME")):String.valueOf(map.get("column_name"));
        		columnList.add(colName);
        	}
		}
        return columnList;
	}
	
}