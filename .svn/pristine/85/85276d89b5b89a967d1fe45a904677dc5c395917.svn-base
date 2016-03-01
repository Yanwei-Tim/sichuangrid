<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<s:set var="currentTime" value="@java.lang.System@currentTimeMillis()"/>
<div class="btnbanner btnbannerData">
	<div class="ui-widget autosearch">
		<input type="text" value="请输入场所名称或地址" id="searchText" maxlength="18" class="searchtxt"
		onfocus="if(this.value == '请输入场所名称或地址') this.value = '';" onblur="if(this.value == '') this.value = '请输入场所名称或地址';"  />
		<button class="ui-icon ui-icon-refresh searchbtnico" onclick="$('#searchText').val('请输入场所名称或地址');"></button>
	</div>
	<div class="btnlist">
		<a href="javascript:;" id="placeSearchButton"><span><strong class="search"></strong>检索</span></a>
<%-- 		<a href="javascript:;" id="placeAddButton" ><span><strong class="search"></strong>新增</span></a> --%>
	</div>
</div>
<div class="content" >
	<div style="width: 100%;">
		<table id="emphsisPlaceList"> </table>
		<div id="emphsisPlaceListPager"></div>
	</div>
</div>
<form id="maintainBindingPlaceForm" method="post" action="" ></form>
<div id="emphsisPlaceDialog"></div>

<script type="text/javascript">
var dialogWidth = 560;
var dialogHeight = 150;
var intervalHandle;
var orgId = "<s:property value='#parameters.orgId'/>";
var orgLevel = "<s:property value='#parameters.orgLevle'/>";

$(document).ready(function(){
	initButtons();
	initGrid();
	getPlacePage();
    formValid();
})

function initButtons(){
	$("#placeSearchButton").click(searchPlace);
	$("#placeAddButton").click(addPlasce);
}

function addHouse(){
	if (orgId==null || orgId==""){
		$.notice({level:"warn", message:"请先选择一个部门"});
	}else{
		$("#houseInfoDialog").createDialog({
			width:750,
			height:550,
			title:"新增住房信息",
			url:"${path}/baseinfo/housePropertyManage/dispatchOperate.action?mode=add&orgId="+orgId,
			buttons: {
		   		"保存" : function(event){
		   			$("#maintainForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		}); 
	}
}
function addPlasce(){
	var type = "<s:property value='#parameters.moduleType'/>";
	$.ajax({
		url:"${path}/gis/gisTypeManage/getGisTypeManagesByKey.action",
		data:{
		 "gisTypeManage.key":type
		},
       	success: function(result) {
	    	if(result==null || result.addUrl==null){
    			alert("请配置对应此类型（"+type+"）的新增链接");
    			return;
    		}
	    	var urls = result.addUrl+orgId;
	    	$("#emphsisPlaceDialog").createDialog({
	    		width: 880,
	    		height: 570,
	    		title:'新增信息',
	    		url:urls,
	    		zIndex:1007,
	    		buttons: {
	    	   		"保存" : function(event){
	    	   			$("#maintainForm").submit();
	    	   			intervalHandle = window.setInterval('triggerAndDialogClose()',1000);
	    	   		},
	    			"关闭" : function(){
	    	        	$(this).dialog("close");
	    	   		}
	    		}
	    		
	    	});
	    }
	});
	//var urls = addPlaceUrls[type]+orgId;
}

function triggerAndDialogClose(){
	$("#emphsisPlaceList").trigger("reloadGrid");
	$("#emphsisPlaceDialog").dialog("close");
	window.clearInterval(intervalHandle);
}
//此方法和三维公用(有需要提取出来)
function searchPlace(){
	var queryStr = $("#searchText").val();
	if(null != queryStr && queryStr != '请输入场所名称或地址'){
		 $("#emphsisPlaceList").setGridParam({
				url:'${path}/twoDimensionMap/keyPlaceMapManage/findUnBoundKeyPlaceByOrgIdAndType.action',
				datatype: "json",
				page:1
		 });
		 $("#emphsisPlaceList").setPostData({
			 "orgId":orgId,
			 "searchValue":queryStr,
			 "type":'<s:property value="#parameters.moduleType"/>'
		 });
		$("#emphsisPlaceList").trigger("reloadGrid");
	}else{
		getPlacePage();
	}
}

function initGrid(){
	$("#emphsisPlaceList").jqGridFunction({
		datatype: "local",
		colNames:['id','场所名称','地址', '类型'],
	   	colModel:[
	   	       {name:"id",index:"id",hidden:true},
		       {name:"name",index:'name'},
		       {name:'address',index:'address'},
		       {name:'typeName',index:'typeName'}
	   	],
	  	multiselect:true,
	  	showColModelButton:false,
	  	sortable:false,
	  	width:dialogWidth,
	  	height:dialogHeight
	});
}

function formValid(){
	$("#maintainBindingPlaceForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			var selectedIds = $("#emphsisPlaceList").jqGrid("getGridParam", "selarrrow");
			var buildDataId = "<s:property value='#parameters.buildDataId'/>";
			var centerLon = "<s:property value='#parameters.centerLon'/>";
			var centerLat = "<s:property value='#parameters.centerLat'/>";
			var type = "<s:property value='#parameters.moduleType'/>";
			var mainTableName = "<s:property value='#parameters.mainTableName'/>";
			var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@KEY_PLACE_BING'/>";
			if(selectedIds.length>0) {
			   $.ajax({
				   	 url:"${path}/openLayersMap/twoDimensionMapManageManage/boundTwoDimensionMap.action",
					 data:{
						 "gisBoundVo.ids":selectedIds+"",
				         "gisBoundVo.lon":centerLon,
				         "gisBoundVo.lat":centerLat,
				         "gisBoundVo.buildDataId":buildDataId,
				         "gisBoundVo.gisType":gisType,
				         "gisBoundVo.isTransformat":TQMap.isTransformat,
				         "type":type,
				         "modeType":modeType,
				         "gisBoundVo.orgId":$("#currOrgId").val()
				     },
			         success: function(result) {
			             if(result) {
			            	 $.messageBox({message:"成功绑定!"});	
				         	 $("#bindingPlaceDialog").dialog("close");
				         } else {
				        	 $.messageBox({message:"绑定失败", level:"error"});
				         }
				         var lab = $("label[for='"+type+"']");
				         if(lab.length > 0) {
				        	 var str = $(lab).children("a").html();
				        	 $(lab).children("a").html("【"+(parseInt(str.substring(1,str.length-1))+selectedIds.length)+"】");
					     }else{
						  	lab = $("<label />").html("<div class='houseTotel'>"+getLocationName(type)+"</div><span>：</span><a href=\"javascript:void(0);\" onclick=\"javascript:window.parent.getKeyPlaceList("+buildDataId+","+centerLon+","+centerLat+",'"+type+"','"+mainTableName+"')\">【"+selectedIds.length+"】</a>个").addClass("form-lbl").attr("for",type);
						  	var li=$("<li/>").html(lab);
				          	$(".houseList").append(li);
				         }
				   },
		      	   error: function(XMLHttpRequest, textStatus, errorThrown){
		      	      alert("提交错误");
		      	   }
		      	});
			}else{
				$.confirm({
					level: "warn",
					title:"警告",
					message:"请选择您要绑定的场所信息！",
					width: 200
				});}
		},
		rules:{
		},
		messages:{
		}
	});
}

function getPlacePage(){
	var address = '';
	$("#emphsisPlaceList").setGridParam({
		url:'${path}/twoDimensionMap/keyPlaceMapManage/findUnBoundKeyPlaceByOrgIdAndType.action',
		datatype: "json",
		page:1
	});
	$("#emphsisPlaceList").setPostData({
		 "orgId":orgId,
		 "queryStr": $("#searchText").val() == "请输入场所名称或地址" ? "" : $("#searchText").val(),
		 "type":'<s:property value="#parameters.moduleType"/>'
	 });
	$("#emphsisPlaceList").trigger("reloadGrid");
}

function checkExport(){
	$("#emphsisPlaceDialog").dialog("close");
}

</script>