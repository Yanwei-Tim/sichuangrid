<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

	<div class="btnbanner btnbannerData">
		<div class="ui-widget autosearch">
			<input type="text" value="请输入建筑物名称或地址" id="searchText" maxlength="18" class="searchtxt"
			onfocus="if(this.value == '请输入建筑物名称或地址') this.value = '';" onblur="if(this.value == '') this.value = '请输入建筑物名称或地址';"  />
			<button id="refreshOrgTree1" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
		<div class="btnlist">
			<a href="javascript:;" id="buildSearchButton"><span><strong class="search"></strong>检索</span></a>
<%-- 			<a href="javascript:;" id="addBuildingButton" ><span><strong class="search"></strong>新增</span></a> --%>
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
<pop:formatterProperty name="type" domainName="@com.tianque.domain.property.PropertyTypes@BUILDDATAS_TYPE" />
$(document).ready(function(){
	$("#houseInfoList").jqGridFunction({
		datatype: "local",
		colNames:['id','建筑物名称','建筑物类型','建筑物地址'],
	   	colModel:[
	        {name:"id",index:"id ",hidden:true,frozen:true},
	        {name:'name',index:"name",width:100,frozen:true},
	        {name:'type',index:"type",width:110,frozen:true,formatter:typeFormatter},
            {name:"address",index:'address',sortable:false,width:305}
		],
	  	showColModelButton:false,
	  	width:560,
	  	height:145
	});
	initOrSearchHousePropertyList();
	bindingHouseFormValidate();
	
	$("#buildSearchButton").click(function(event) {
		initOrSearchHousePropertyList();
	});
	$("#addBuildingButton").click(function(event) {
		addBuilding();
	});
	
})

function addBuilding(){
	if(isGridOrganization()){
		$("#builddatasMaintanceDialog").createDialog({
			width:600,
			height:280,
			title:"新增建筑物信息",
			url:"${path}/builddatasManage/dispatch.action?mode=add&flag=false&organization.id="+$("#currOrgId").val(),
			buttons: {
		   		"保存" : function(event){
		   			$("#maintainForm").submit();
		   			initOrSearchHousePropertyList();
		   		},
		   		"关闭" : function(){
		        	$("#builddatasMaintanceDialog").dialog("close");
		   		}
			}
		}); 
	}else {
 		$.messageBox({
 			message:"请选择网格层级进行绑定！",
 			level:'error'
 		});
 	}
}

function initOrSearchHousePropertyList(){
	var queryStr = $("#searchText").val();
	if(queryStr=="请输入建筑物名称或地址"){
		queryStr="";
	}
	$("#houseInfoList").setGridParam({
			url:'${path}/twoDimensionMap/buildDataTwoDimensionMapManage/getUnBoundedBuilddatas.action',
			datatype: "json",
			page:1
	});
	$("#houseInfoList").setPostData({
		 	"orgId" : $("#currOrgId").val(),
			"queryStr":queryStr
	});
	$("#houseInfoList").trigger("reloadGrid");
}
function bindingHouseFormValidate(){
	$("#maintainBindingHouseForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
			var selectedId = $("#houseInfoList").jqGrid("getGridParam", "selrow");
			var centerLon="<s:property value='#parameters.centerLon'/>";
			var centerLat="<s:property value='#parameters.centerLat'/>";
			var modeType="<s:property value='@com.tianque.openLayersMap.util.ServiceImplModeType@BUILDDATA_BING'/>";
			var buildDataId = "<s:property value='#parameters.buildDataId'/>";
			if(selectedId!=null&&selectedId!=""){
			   $.ajax({
				     async: false,
				     datatype: "json",
				     method:"post",
				     url:"${path}/openLayersMap/twoDimensionMapManageManage/boundTwoDimensionMap.action",
				     data:{
				    	 "gisBoundVo.ids":selectedId,
					     "gisBoundVo.lon":centerLon,
					     "gisBoundVo.lat":centerLat,
					     "gisBoundVo.gisType":gisType,
					     "gisBoundVo.buildDataId":buildDataId,
					     "gisBoundVo.isTransformat":TQMap.isTransformat,
					     "modeType":modeType,
					     "gisBoundVo.orgId":$("#currOrgId").val()
					 },
		             success: function(data){
	            		var lon = centerLon;
	            		var lat = centerLat;
		            	 $("#map").TqMap("deleteMarkerByMarkerId",{markerId:buildDataId+"_hourse"});
						 var imgUrl="/resource/openLayersMap/images/house-3.png";
						 var markerW=40;
						 var markerH=30;
						 if(data!=null&&data.type!=null){
								var buildData=getHourseTypeImgPathAndMarkerWH(data.type.id);
								imgUrl=buildData.path;
								markerW=buildData.markerW;
								markerH=buildData.markerH;
						 }
						 if(bindInWfs==false || $("#isViewHourse").val()=="true"){//如果在2d地图中或者建筑物绑定在除wfs之外的地方则显示楼宇
							 $("#map").TqMap("addMarker",{
								iconUrl:PATH+imgUrl,
								markerW:markerW,
								markerH:markerH,
								lon:lon,
								lat:lat,
								markerId:buildDataId+"_hourse",
								data:{lon:lon,lat:lat}
							}); 
							$("#map").TqMap("registerEvent",{objectName:"marker",eventName:"click",func:hourseClick});
						 }
						$("#map").TqMap("deletePopup");//删除popup
						$.messageBox({message:"建筑物绑定成功!"});
						$("#budlingDialog").dialog("close");
		      	   },
		      	   error: function(XMLHttpRequest, textStatus, errorThrown){
		      	    	$.messageBox({message:"提交错误!"});
		      	   }
		      	});
			}else{
				$.confirm({
					level: "warn",
					title:"警告",
					message:"请选择您要绑定的建筑物！",
					width: 230
				});
			}
		},
		rules:{
		},
		messages:{
		}
	});
}
</script>