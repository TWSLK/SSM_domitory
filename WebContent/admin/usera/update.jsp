<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/header_admin_list.jsp" %>
<body>
<form id="formId1" class="form-horizontal" style="padding:30px 20px 10px 25px;">
	<input type="hidden" name="id" />
	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>学生编号</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="text" class="form-control" name="login" disabled>
    	</div>
  	</div>
  	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>所属班级</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<select class="form-control" name="classid" id="classid" >
        		<option value=''>--请选择--</option>
			</select> 
    	</div>
  	</div>
  	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>姓名</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="text" class="form-control" name="name" >
    	</div>
  	</div>
  	<div class="form-group">
    	<label for="fTable" class="col-xs-3 col-sm-3 col-md-3 control-label"><span style="color:#F00">*</span>性别</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
       		<select class="form-control cmm_code" code="C013" name="sex">
			</select>
    	</div>
  	</div>
  	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>电话</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="text" class="form-control" name="tel" >
    	</div>
  	</div>
  	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>地址</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="text" class="form-control" name="addr" >
    	</div>
  	</div>
  	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>邮箱</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="text" class="form-control" name="email" >
    	</div>
  	</div>
  	<div class="form-group">
    	<label for="fTable" class="col-xs-3 col-sm-3 col-md-3 control-label">头像</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
    		<input type="hidden" name="icon" /> 
    		<img  src=""  id="iconImg" style="width:100px; height:80px; float:left;"/>
    		<div class="layui-btn" id="uploadfile1" style="float:left; "></div>
    	</div>
  	</div>
  	<div class="form-group">
    	<div class=" col-xs-6 col-sm-6 col-md-6  col-md-offset-3 col-sm-offset-3 col-xs-offset-4">
      		<button type="submit" class="btn btn-success" lay-submit lay-filter="formDemo">保存</button>
    	</div>
  	</div>
</form>

<script type="text/javascript">
$(function(){
	var seltype = $(this).getUrlParam("seltype");
	$.ajax({
        type:"post", 
        dataType:"json", 
        data : {},
        url:"../class/getList.do",
        success:function(data){
       		for(k in data){
       			var options="<option value='"+data[k].id+"'>"+data[k].name+"</option>";
       	        $("#classid").append(options);
        	}
       		
       		if (seltype=='gr') {
       			$("#classid").attr("disabled", "disabled");
       			//数据回显
       			$.getJSON("../../common/cmm/getSession.do?"+ new Date().getTime(),function(data1){
       				//数据回显
       				var id = data1.uid;
       				$.getJSON("getInfo.do?"+ new Date().getTime(), {id:id}, function(data){
       					for(k in data){
       						if ($("[name='"+k+"']").length){
       							$("[name='"+k+"']").attr("val", data[k]);
       							$("[name='"+k+"']").val(data[k]);
       						}
       					}
       					$("#iconImg").attr("src", data.icon);
       				});
       			});
       		} else {
       			//数据回显
       			var id = $(this).getUrlParam("id");
       			$.getJSON("getInfo.do?"+ new Date().getTime(), {id:id}, function(data){
       				for(k in data){
       					if ($("[name='"+k+"']").length){
       						$("[name='"+k+"']").attr("val", data[k]);
       						$("[name='"+k+"']").val(data[k]);
       					}
       					$("#iconImg").attr("src", data.icon);
       				}
       			});
       		}
       		
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	alert(XMLHttpRequest.status + "\r\n"+ errorThrown);
        	console.log(XMLHttpRequest, XMLHttpRequest.status,textStatus, errorThrown);
        }
	});
	
	//文件上传
	layui.use('upload', function(){
	  var upload = layui.upload;
	  var uploadInst = upload.render({
	    elem: '#uploadfile1'
	    ,url: '../../common/cmm/uploadFile.do'
	    ,accept: 'jpg,png,gif' //允许上传的文件类型
	    ,size: 102400
	    ,done: function(res){
	    	$("#iconImg").attr("src", res.url);
	    	$("[name='icon']").val(res.url);
	    }
	  });
	});
	
	//数据提交
    $("#formId1").validate({
		rules:{
			name:{required:true,minlength:1,maxlength:40},
			sex:{required:true,minlength:1,maxlength:40},
			tel:{required:true,minlength:1,maxlength:40},
			addr:{required:true,minlength:1,maxlength:40},
			email:{required:true,minlength:1,maxlength:40},
		},
		onkeyup:false,
		focusCleanup:true,
		submitHandler:function(form){
			$.ajax({
                type:"post", 
                dataType:"json", 
                data : $("#formId1").serialize(),
                url:"update.do",
                success:function(data){
                	if (data.success) {
                		layer.msg(data.msg, {icon:data.icon,time:1500},function(){
                			var index = parent.layer.getFrameIndex(window.name);
    		                parent.$('#search').click();
    		                parent.layer.close(index);
    		            });
                	} else {
                		layer.alert(data.msg,{icon : data.icon});
                	}
                },
                error:function(XMLHttpRequest, textStatus, errorThrown) {
                	alert(XMLHttpRequest.status + "\r\n"+ errorThrown);
                	console.log(XMLHttpRequest, XMLHttpRequest.status,textStatus, errorThrown);
                }  
            });
		}
	});

});
</script> 
</body>
</html>