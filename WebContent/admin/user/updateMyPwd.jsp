<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/header_admin_list.jsp" %>
<body>
<form id="formId1" class="form-horizontal" style="padding:30px 20px 10px 25px;">
	<input type="hidden" name="id" />
	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>登录账号</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="text" class="form-control" name="login" disabled>
    	</div>
  	</div>
  	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>原密码</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
        	<input type="password" class="form-control" id="pwdold" name="pwdold" >
    	</div>
  	</div>
  	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>新密码</label>
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
  	<div class="form-group  mt-10">
    	<label class="col-xs-3 col-sm-3 col-md-3 control-label"   ><span style="color:#F00">*</span>验证码</label>
    	<div class="col-xs-6 col-sm-6 col-md-6">
    		<div class="col-xs-3 col-sm-3 col-md-3">
        	<input type="text" class="form-control" id="yzm" />
        	</div>
        	<div class="col-xs-3 col-sm-3 col-md-3">
           	<canvas id="canvas" width="100" height="38"></canvas>
           	</div>
    	</div>
  	</div>
  	<div class="form-group">
    	<div class=" col-xs-6 col-sm-6 col-md-6  col-md-offset-3 col-sm-offset-3 col-xs-offset-4">
      		<button type="button" class="btn btn-success" lay-submit lay-filter="formDemo" id="savesub">保存</button>
    	</div>
  	</div>
</form>

<script type="text/javascript">
$(function(){
	//数据回显
	$.getJSON("../../common/cmm/getSession.do?"+ new Date().getTime(), {}, function(data){
		$.getJSON("getInfo.do?"+ new Date().getTime(), {id:data.uid}, function(d){
			for(k in d){
				if ($("[name='"+k+"']").length){
					$("[name='"+k+"']").attr("val", d[k]);
					$("[name='"+k+"']").val(d[k]);
				}
			}
		});
	});
	
	 $("#savesub").on('click',function(){
		//判断验证码
	    var val = $("#yzm").val().toLowerCase();
	    var num = show_num.join("");
	    if(val==''){
	        alert('请输入验证码！');
	        return false;
	    }else if(val != num){
	        alert('验证码错误！请重新输入！');
	        draw(show_num);
	        return false;
	    }
	    $("#formId1").submit();
	});
	
    
	//数据提交
    $("#formId1").validate({
		rules:{
			pwdold:{required:true,minlength:1,maxlength:40},
			pwd:{required:true,minlength:1,maxlength:40},
			pwd1:{required:true,minlength:1,maxlength:40,equalTo: "#pwd"},
		},
		onkeyup:false,
		focusCleanup:true,
		submitHandler:function(form){
			$.ajax({
                type:"post", 
                dataType:"json", 
                data : $("#formId1").serialize(),
                url:"updateMyPwd.do",
                success:function(data){
                	if (data.success) {
                		layer.msg(data.msg, {icon:data.icon,time:1500},function(){
                			$("#canvas").click();
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
<script>
	//验证码初始化
	var show_num = [];
    $(function(){
        draw(show_num);

        $("#canvas").on('click',function(){
            draw(show_num);
        })
    })

    function draw(show_num) {
        var canvas_width=$('#canvas').width();
        var canvas_height=$('#canvas').height();
        var canvas = document.getElementById("canvas");//获取到canvas的对象，演员
        var context = canvas.getContext("2d");//获取到canvas画图的环境，演员表演的舞台
        canvas.width = canvas_width;
        canvas.height = canvas_height;
        var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
        var aCode = sCode.split(",");
        var aLength = aCode.length;//获取到数组的长度
        
        for (var i = 0; i <= 3; i++) {
            var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
            var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
            var txt = aCode[j];//得到随机的一个内容
            show_num[i] = txt.toLowerCase();
            var x = 10 + i * 20;//文字在canvas上的x坐标
            var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
            context.font = "bold 23px 微软雅黑";

            context.translate(x, y);
            context.rotate(deg);

            context.fillStyle = randomColor();
            context.fillText(txt, 0, 0);

            context.rotate(-deg);
            context.translate(-x, -y);
        }
        for (var i = 0; i <= 5; i++) { //验证码上显示线条
            context.strokeStyle = randomColor();
            context.beginPath();
            context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
            context.stroke();
        }
        for (var i = 0; i <= 30; i++) { //验证码上显示小点
            context.strokeStyle = randomColor();
            context.beginPath();
            var x = Math.random() * canvas_width;
            var y = Math.random() * canvas_height;
            context.moveTo(x, y);
            context.lineTo(x + 1, y + 1);
            context.stroke();
        }
    }

    function randomColor() {//得到随机的颜色值
        var r = Math.floor(Math.random() * 256);
        var g = Math.floor(Math.random() * 256);
        var b = Math.floor(Math.random() * 256);
        return "rgb(" + r + "," + g + "," + b + ")";
    }
</script>
</body>
</html>