<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/header_admin_list.jsp" %>
<body>
<form id="formId1" class="form-horizontal" style="padding:30px 20px 10px 25px;">
	<input type="hidden" name="id" />
	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>宿舍编号</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="text" class="form-control" name="no" >
    	</div>
  	</div>
  	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>宿舍管理员</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="text" class="form-control" name="admin" >
    	</div>
  	</div>
  	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>床位数量</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="text" class="form-control" name="bedqty" >
    	</div>
  	</div>
  	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>所属班级</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<select class="form-control" name="classid" id="classid">
        		<option value=''>--请选择--</option>
			</select> 
    	</div>
  	</div>
  	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>水费余额</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="text" class="form-control" name="waterlast" >
    	</div>
  	</div>
  	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>电费余额</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="text" class="form-control" name="spotlast" >
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
       		
       		//数据回显
       		var id = $(this).getUrlParam("id");
       		$.getJSON("getInfo.do?"+ new Date().getTime(), {id:id}, function(data){
       			for(k in data){
       				if ($("[name='"+k+"']").length){
       					$("[name='"+k+"']").attr("val", data[k]);
       					$("[name='"+k+"']").val(data[k]);
       				}
       			}
       		});
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
        	alert(XMLHttpRequest.status + "\r\n"+ errorThrown);
        	console.log(XMLHttpRequest, XMLHttpRequest.status,textStatus, errorThrown);
        }
	});
	
	
	//数据提交
    $("#formId1").validate({
		rules:{
			no:{required:true,minlength:1,maxlength:50},
			admin:{required:true,minlength:1,maxlength:50},
			bedqty:{required:true,minlength:1,maxlength:50},
			classid:{required:true,minlength:1,maxlength:50},
			waterlast:{required:true,minlength:1,maxlength:50},
			spotlast:{required:true,minlength:1,maxlength:50},
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