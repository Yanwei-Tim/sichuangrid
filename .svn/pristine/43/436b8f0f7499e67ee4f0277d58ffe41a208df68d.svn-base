<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

	<div class="btnbanner btnbannerData">
		<div class="ui-widget autosearch">
			<input type="text" value="请输入房主姓名或常住地址" id="searchText" maxlength="18" class="searchtxt"
			onfocus="if(this.value == '请输入房主姓名或常住地址') this.value = '';" onblur="if(this.value == '') this.value = '请输入房主姓名或常住地址';"  />
			<button id="refreshOrgTree1" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
		<div class="btnlist">
			<a href="javascript:;" id="houseSearchButton"><span><strong class="search"></strong>检索</span></a>
			<a href="javascript:;" id="addHouseButton" ><span><strong class="search"></strong>新增</span></a>
		</div>
	</div>
	<div class="content" >
		<div style="width: 100%;">
			<table id="houseInfoList"> </table>
			<div id="houseInfoListPager"></div>
		</div>
	</div>
	<form id="maintainBindingHouseForm" method="post" action="" ></form>


<script type="text/javascript">

var dialogWidth=470;
var dialogHeight=240;
var orgId = "<s:property value='#parameters.orgId'/>";
var orgLevel = "<s:property value='#parameters.orgLevle'/>";
var centerLon="<s:property value='#parameters.centerLon'/>";
var centerLat="<s:property value='#parameters.centerLat'/>";

$(document).ready(function(){
	initButtons();
	initGrid();
	getHouseList();
    formValid();
})

function initButtons(){
	$("#houseSearchButton").click(function(event) {
		searchHouse();
	});
	$("#addHouseButton").click(function(event) {
		addHouse();
	});
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
			   			$("#houseInfoDialog").dialog("close");
			   			$("#houseInfoList").trigger("reloadGrid");
			   			
			   		},
			   		"关闭" : function(){
			        	$("#houseInfoDialog").dialog("close");
			   		}
				}
			}); 
		}
	}

function searchHouse(){
	var queryStr = $("#searchText").val();
	if(null != queryStr && queryStr != '请输入房主姓名或常住地址'){
		 $("#houseInfoList").setGridParam({
				url:'${path}/gis/commonOpenLayersManage/getUnBoundedHousePropertyList.action',
				datatype: "json",
				page:1
		 });
		 $("#houseInfoList").setPostData({
			 	"orgId" : orgId,
				"queryStr":queryStr,
				"type":'<s:property value="@com.tianque.gis.util.ModuleMap@PLACE_HOUSE_INFO.getModuleType()"/>'
		 });
		$("#houseInfoList").trigger("reloadGrid");
	}else{
		getHouseList();
	}
}

function initGrid(){
	$("#houseInfoList").jqGridFunction({
		datatype: "local",
		colNames:['id','住房编号','房主','常住地址'],
	   	colModel:[
	        {name:"id",index:"id",hidden:true,frozen:true},
	        {name:'houseCode',index:"houseCode",width:100,frozen:true},
	        {name:'houseName',index:"houseName",width:110,frozen:true},
            {name:"address",index:'address',sortable:false,width:305}
            //{name:"buildingId",label:"绑定状态",width:100,formatter:mapBoundState,align:"center"}
		],
	  	multiselect:true,
	  	showColModelButton:false,
	  	width:560,
	  	height:145
	});
}

function formValid(){
	$("#maintainBindingHouseForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
		var selectedIds = $("#houseInfoList").jqGrid("getGridParam", "selarrrow");
		var featureId = "<s:property value='#parameters.featureId'/>";
			if(selectedIds.length>0){
				for(var i=0; i<selectedIds.length;i++){
		   			var row=$("#houseInfoList").getRowData(selectedIds[i]);
				}
			   $.ajax({
				     async: false,
				     datatype: "json",
				     method:"post",
				     url:"${path}/gis/commonOpenLayersManage/updateHouseInfoForOpenLayersMap.action",
				     data:{
				    	 "idArr":selectedIds+'',
					     "openLayersMapInfo.featureId":featureId,
					     "openLayersMapInfo.centerLon":centerLon,
					     "openLayersMapInfo.centerLat":centerLat
					 },
		             success: function(data){
							 $.messageBox({message:"成功绑定住房信息!"});
							 $("#bindingHouseDialog").dialog("close");
							 getHouseList(featureId);
							 var oldHouseSum = $("#houseSum").html();
	                         var newHouseSum = parseInt(oldHouseSum)+selectedIds.length;
	                         $("#houseSum").html(newHouseSum);
		      	   },
		      	   error: function(XMLHttpRequest, textStatus, errorThrown){
		      	      alert("提交错误");
		      	   }
		      	});
			}else{
				$.confirm({
					level: "warn",
					title:"警告",
					message:"请选择您要绑定的住房信息！",
					width: 200
				});}
		},
		rules:{
		},
		messages:{
		}
	});
}

function getHouseList(){
	var address = '';
	$("#houseInfoList").setGridParam({
		url:'${path}/gis/commonOpenLayersManage/getUnBoundedHousePropertyList.action',
		datatype: "json",
		page:1
	});
	$("#houseInfoList").setPostData({
		 "orgId":orgId,
		 "type":'<s:property value="@com.tianque.openLayersMap.util.ModuleMap@PLACE_HOUSE_INFO.getModuleType()"/>'
	 });
	$("#houseInfoList").trigger("reloadGrid");
}

</script>