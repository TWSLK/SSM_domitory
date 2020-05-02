package com.controller.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.core.AbstractRestController;
import com.dao.CmmDao;
import com.util.MapUtil;

@Controller
@SuppressWarnings("unchecked")
@RequestMapping("common/cmm/*")
public class CmmController extends AbstractRestController{	
	@Autowired
	CmmDao dao;
	
	@RequestMapping(value = "getCodeList")
	@ResponseBody
	public String getCodeList(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		String sql = "select code as id, name from code";
		if (MapUtil.isContains(pMap, "codetype")) {
			sql += " where codetype='" + pMap.get("codetype") +"'";
		}
		List<Map<String, Object>> rtList = dao.getList(sql);
		return JSON.toJSONString(rtList);
	}
	
	@RequestMapping(value = "getCategoryList")
	@ResponseBody
	public String getCategoryList(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		String sql = "select id, name from category where yn='Y'";
		if (MapUtil.isContains(pMap, "type")) {
			sql += " and type='" + pMap.get("type") +"'";
		}
		List<Map<String, Object>> rtList = dao.getList(sql);
		return JSON.toJSONString(rtList);
	}
	
	@RequestMapping(value = "getCategoryimageList")
	@ResponseBody
	public String getCategoryimageList(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		String sql = "select id, name from categoryimage where yn='Y'";
		if (MapUtil.isContains(pMap, "type")) {
			sql += " and type='" + pMap.get("type") +"'";
		}
		List<Map<String, Object>> rtList = dao.getList(sql);
		return JSON.toJSONString(rtList);
	}
	
	@RequestMapping(value = "getSession")
	@ResponseBody
	public String getSession(@RequestParam Map<String, Object> pMap, HttpServletRequest request){
		Map<String, Object> rtMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		rtMap.put("uid", session.getAttribute("uid"));
		rtMap.put("name", session.getAttribute("name"));
		rtMap.put("login", session.getAttribute("login"));
		rtMap.put("utype", session.getAttribute("utype"));
		return JSON.toJSONString(rtMap);
	}
	
	@RequestMapping(value = "uploadFile")
	@ResponseBody
	public String uploadFile(@RequestParam Map<String, Object> pMap, HttpServletRequest request) {
		//初始化工具
	    DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
	    ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
	    FileOutputStream fileOutputStream = null;
	    InputStream inputStream = null;
	    String imagePath = null;
	    String realnm = "";
        try {
            //解析请求
            List<FileItem> fileItems = servletFileUpload.parseRequest(request);
            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {
                	realnm = fileItem.getName();
                	String[] realnmA = realnm.split("\\\\");
                    // 不是普通表单域,上传的文件
                    inputStream = fileItem.getInputStream();//获取文件输入流
                    byte[] bytes = new byte[1024];//缓冲区
                    Date date = new Date();
                    imagePath = request.getServletContext().getRealPath("") + "\\upload\\" + date.getTime() + realnmA[realnmA.length-1];
                    File file = new File(imagePath);
                    fileOutputStream = new FileOutputStream(file);
                    while (inputStream.read(bytes) != -1) {
                        fileOutputStream.write(bytes);
                    }
                }
            }
            imagePath = imagePath.substring(imagePath.lastIndexOf("\\") + 1, imagePath.length());
            //关闭输入输出流
            fileOutputStream.close();
            inputStream.close();
        } catch (org.apache.commons.fileupload.FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> rtMap = new HashMap<String, Object>();
        rtMap.put("uploaded", "1");
        rtMap.put("url", "../../upload/"+imagePath);
        rtMap.put("realnm", realnm);
		return JSON.toJSONString(rtMap);
	}
	
	

}
