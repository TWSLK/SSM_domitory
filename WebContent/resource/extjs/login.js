layui.define(['jquery'], function(exports) {
	var $ = layui.jquery;
	var login = {
		login: function(toUrl) {
			var login = $("[name='login']").val();
			var pwd = $("[name='pwd']").val();
			var utype = $("[name='utype']").val();
			if (!login) {
				alert("请输入用户名"); return;
			}
			if (!pwd) {
				alert("请输入密码"); return;
			}
			$.ajax({
	            url: "../../admin/user/login.do",
	            data: {login:login, pwd:pwd, utype:utype},
	            type: "POST",
	            dataType: 'json',
	            success: function(data){
	            	if(data.flag) {
	            		window.location.href = toUrl;
	                } else {
	                	alert(data.msg);
                	}
	            }
			});
		},
		
		logout: function() {
			$.ajax({
	            url: "../../admin/user/logout.do",
	            data: {},
	            type: "POST",
	            dataType: 'json',
	            success: function(data){
	            	if(data.flag) {
	            		window.location.reload();
	                } else {
	                	alert(data.msg);
                	}
	            }
			});
		},
		
		checkLoginAdmin: function() {
			$.ajax({
	            url: "../../admin/user/permission.do",
	            data: {},
	            type: "POST",
	            dataType: 'json',
	            success: function(data){
	            	if(!data.flag) {
	            		//alert(data.msg);
	            		//window.location ="../../";
	            		window.location = "../../admin/login/login.html";
	                }
	            }
			});
		},
				
		
	};
	exports('login', login);
});