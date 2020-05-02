<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/header_admin_list.jsp" %>
<body>
<form id="formId1" class="form-horizontal" style="padding:30px 20px 10px 25px;">
	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>登录账号</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="text" class="form-control" name="login" >
    	</div>
  	</div>
	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>用户昵称</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="text" class="form-control" name="name" >
    	</div>
  	</div>
  	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>登录密码</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="password" class="form-control" id="pwd" name="pwd" >
    	</div>
  	</div>
  	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>重复密码</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="password" class="form-control" id="pwd1" name="pwd1" >
    	</div>
  	</div>
  	<div class="form-group">
    	<label for="fTable" class="col-xs-3 col-sm-3 col-md-3 control-label"><span style="color:#F00">*</span>用户类型</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
       		<select class="form-control cmm_code" code="C001" name="utype">
			</select>
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
    $("#formId1").validate({
		rules:{
			login:{required:true,minlength:1,maxlength:40},
			name:{required:true,minlength:1,maxlength:40},
			pwd:{required:true,minlength:1,maxlength:40},
			pwd1:{required:true,minlength:1,maxlength:40,equalTo: "#pwd"},
			utype:{required:true,minlength:1,maxlength:20},
		},
		onkeyup:false,
		focusCleanup:true,
		submitHandler:function(form){
			$.ajax({
                type:"post",  //提交方式  
                dataType:"json", //数据类型  
                data : $("#formId1").serialize(),
                url:"add.do", //请求url  
                success:function(data){ //提交成功的回调函数 
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