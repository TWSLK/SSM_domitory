$(document).ready(function(){
	layui.config({
	 	base: '../../resource/extjs/'
	 });
		
	if ($("iframe").length>0){
		$('iframe').iFrameResize([{log: true}]);
	}
});