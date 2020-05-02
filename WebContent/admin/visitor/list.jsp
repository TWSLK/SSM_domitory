<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/header_admin_list.jsp" %>
<body class="gray-bg">
<div class="row wrapper white-bg">
	<div class="col-sm-12">
	    <div class="ibox float-e-margins" style="padding-bottom:0px;margin-bottom:0px;"  >
	        <div class="ibox-title">
	            <h5 id="menuTreeJs" style="color:#999;font-weight:normal;"></h5>
	        </div>
	        <form id="formId1" class="form-horizontal">
	        	<div id="show" class="ibox-content" style="padding-left:16px;">
	            	<div class="col-xs-10 col-sm-8 col-md-10 b-r">
		                <div class="form-group">
		                	<div class="col-md-2">
		                         <input name="no"  value="" placeholder="宿舍编号"  class="form-control " value="">    
		                    </div>
		                </div>
	               </div>
	               <div class="col-md-2">
	                    <div class="col-md-4">
	                        <button class="btn btn-primary input-sm col-sm-12 col-md-12 pull-left" id="search" type="button">&nbsp;搜&nbsp;&nbsp;&nbsp;索</button>
	                    </div>
	                    <div class="col-md-4">
	                        <button class="btn btn-success input-sm col-sm-12 col-md-12 pull-left mr-5" id="add" type="button">&nbsp;添加</button>
	                    </div>
	               </div>
	           </div>
	    	</form>
	 	</div>
	</div>
	
    <div class="mt-5 wrapper  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-content">
                        <div class="jqGrid_wrapper">
                            <table id="table_list_1"></table>
                            <div id="pager_list_1"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>    

<script>
$(document).ready(function(){
	$('#add').hide();
    //表格
    var _this_page_data_url = "getPageList.do";
    $.jgrid.defaults.styleUI="Bootstrap";
    $.getJSON("../../common/cmm/getSession.do?"+ new Date().getTime(), {}, function(sesdata){
		var utype = sesdata.utype;
		
		if (utype=='10') {
			$('#add').show();
		    $(this).jqGridTable("table_list_1","pager_list_1",{
		        url:_this_page_data_url,
		        postData : JSON.parse($(this).form2JsonString("formId1")),
		        colNames:["宿舍编号","受访人","访客姓名","与受访者关系","到访时间","离开时间","操作"],//"最大审核级别",
		        colModel:[
		          {name:"no",index:"no"},
		          {name:"name",index:"name"},
		          {name:"vname",index:"vname"},
		          {name:"rel",index:"rel"},
		          {name:"starttime",index:"starttime"},
		          {name:"endtime",index:"endtime"},
		          {name:"url",index:"url",formatter:function(cellvalue, options, rowObject){
		        	 	var optstr = "";
			        	optstr += '<a class="ml-10" onclick="update('+rowObject.id+')" >修改</a>';
		         		return optstr;
		          } }    
		        ]
			});
		} else {
			$(this).jqGridTable("table_list_1","pager_list_1",{
		        url:_this_page_data_url,
		        postData : JSON.parse($(this).form2JsonString("formId1")),
		        colNames:["宿舍编号","受访人","访客姓名","与受访者关系","到访时间","离开时间"],//"最大审核级别",
		        colModel:[
		          {name:"no",index:"no"},
		          {name:"name",index:"name"},
		          {name:"vname",index:"vname"},
		          {name:"rel",index:"rel"},
		          {name:"starttime",index:"starttime"},
		          {name:"endtime",index:"endtime"},
		        ]
			}); 
    	}
	}); 

    $("#search").click(function(){
    	var params = JSON.parse($(this).form2JsonString("formId1"));
    	console.log(params);
        jQuery("#table_list_1").jqGrid('setGridParam',{
        url:_this_page_data_url,
        datatype : 'json',
        postData : params,
        page : 1          
      }).trigger('reloadGrid');
    });
     
    $("#add").click(function(){
        layer.open({
            type: 2,
            title: '添加',
            shadeClose: true,
            shade: 0.6,
            maxmin: true, //开启最大化最小化按钮
            area: ['800px', '450px'],
            content: "add.jsp"
       });   
    }); 
});

function update(id){
   	layer.open({
	    type: 2,
	    title:'修改',
	    shadeClose: true,
	    shade: 0.6,
	    area: ['800px', '450px'],
	    content: 'update.jsp?id='+id
	}); 
}

function show(id){
   	layer.open({
	    type: 2,
	    title:'查看',
	    shadeClose: true,
	    shade: 0.6,
	    area: ['800px', '450px'],
	    content: 'show.jsp?id='+id
	}); 
}
</script>
	
</body>
</html>
