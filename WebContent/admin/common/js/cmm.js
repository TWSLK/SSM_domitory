layui.config({
 	base: '../../resource/extjs/'
 });

layui.use(['jquery', 'menu'], function(){
	var $ = layui.jquery, menu = layui.menu;
	$(function() {
		menu.showMenuTree('../common/json/menu.json');
		$(this).cmm_code();
		$(this).cmm_category();
		
		
	});
	
});