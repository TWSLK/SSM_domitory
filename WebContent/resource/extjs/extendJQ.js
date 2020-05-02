(function($) {
	$.fn.extend({
		"jqGridTable" : function(tabId, pagerId,params) {
			var postData ={jqGrid:"jqGrid"};
			$.extend(postData, params.params); 
			var option = {
		        	postData:postData,
		        	height:250,
		        	autowidth:true,
		        	//shrinkToFit:true,
		        	rowNum:10,
		        	rowList:[10,20,30],
		        	datatype:"json",
		        	mtype: 'post', 
		            jsonReader: {
		              total: "iTotalDisplayRecords", 
		              records: "iTotalRecords",
		              root: "rows", 
		              page :'page',
		              repeatitems: true
			        },       
		            viewrecords:true,
		            hidegrid:false,
		            pager:"#"+pagerId,
		            loadComplete: function () {
			           var records =$('#' + tabId).jqGrid('getGridParam', 'records'); //获取数据总条数
			           /* if (records && params.params && !params.params.foldSign){
	               			$("#queryPanel").hide();
			           }*/
		            }
			};
			 if(params.viewrecords==false){
				 $.jgrid.regional["cn"].defaults.pgtext="{0}";
			 }
			 $.extend(option, params );
			
			$('#' + tabId).jqGrid(option);
			
			var cols = $('#' + tabId).jqGrid('getGridParam','colModel');
           for (i = 0; i < cols.length; i++) {
        	   if (cols[i].name){
        		   $('#' + tabId).setColProp(cols[i].name, { sortable: false });
        	   }
           }
			
	        $(".ui-jqgrid-bdiv").css("height","100%");
		},
		"cmm_code" : function () {
			var codeObj = $(".cmm_code");
			var temObj = false;
			if(typeof(codeObj)=='object'){
				for(var i = 0;i < codeObj.length;i++){
					temObj = $(codeObj).eq(i);
					var tCode = temObj.attr("code");
					var tparam = {
						url:"../../common/cmm/getCodeList.do",
						data:{"codetype":tCode},
						obj:temObj
					};
					$(this).select_init(tparam);
				}
			}
			
			
		},
		"cmm_category" : function () {
			var codeObj = $(".cmm_category");
			var temObj = false;
			if(typeof(codeObj)=='object'){
				for(var i = 0;i < codeObj.length;i++){
					temObj = $(codeObj).eq(i);
					var type = temObj.attr("type");
					var tparam = {
						url:"../../common/cmm/getCategoryList.do",
						data:{"type":type},
						obj:temObj
					};
					$(this).select_init(tparam);
				}
			}
			
			
		},
		"cmm_categoryimage" : function () {
			var codeObj = $(".cmm_categoryimage");
			var temObj = false;
			if(typeof(codeObj)=='object'){
				for(var i = 0;i < codeObj.length;i++){
					temObj = $(codeObj).eq(i);
					var type = temObj.attr("type");
					var tparam = {
						url:"../../common/cmm/getCategoryimageList.do",
						data:{"type":type},
						obj:temObj
					};
					$(this).select_init(tparam);
				}
			}
			
			
		},
		"select_init" : function (params) {
			//url,data,obj
			var url = params.url;
			var obj = params.obj;
			var data = params.data;
			$.ajax({
				type : "POST",
				url :url,
				dataType : "json",
				async:params.sync,
				//jsonp: 'callback',
				crossDomain: true,
				data:data,
				success : function(datalist){
					
					if(obj.attr("def")!=null){
						obj.empty();
						obj.append('<option value="">'+obj.attr("def")+'</option>');
					}else{
						obj.empty();
						obj.append('<option value="">--&#x8BF7;&#x9009;&#x62E9;--</option>');
					}
					$.each(datalist, function(i, item) {
						obj.append("<option value='"+item.id+"'>"+item.name+"</option>");
					});
					if(typeof(obj.attr("val"))=='string')
					{
						if (obj.attr("val")!=null){obj.val(params.obj.attr("val")).change();}
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					 console.log(params.url+" 加载出错");
				}
			});
		},
		"getUrlParam" : function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) return unescape(r[2]); return null;
        },
        "form2JsonString" : function(formId){
        	var paramArray = $('#' + formId).serializeArray();  
        	var jsonObj={};
        	$(paramArray).each(function(){
        		jsonObj[this.name]=this.value;  
        	});  
        	return JSON.stringify(jsonObj);
        },
        
        
	}
);
	
})(jQuery);

$(function() {
	$(this).cmm_code();
	$(this).cmm_category();
	$(this).cmm_categoryimage();
});
