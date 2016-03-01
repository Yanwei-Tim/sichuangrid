<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<form  method="post" >
	<pop:token />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input id="organizationId" name="organizationId" type="hidden" value="${organizationId}" />
	</form>
	<div class="ui-corner-all" id="nav">
   		<a id="reloadScenicSpot"  href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width:98%;margin:0 auto;">
		<table id="searchScenicSpotList"> </table>
		<div id="searchScenicSpotListPager"></div>
	</div>
</div>

<script type="text/javascript">
var title="旅游景点列表";
function reloadScenicSpots(){
	$("#searchScenicSpotList").setGridParam({
		url:'${path}/baseinfo/scenicSpotManage/scenicSpotList.action',
		datatype: "json",
		page:1
	});
	 var searchpostData={
			 "organizationId":$("#organizationId").val(),
			"location.orgInternalCode":"",
			"location.isEmphasis":false
		};
	$("#searchScenicSpotList").setPostData(searchpostData);
	$("#searchScenicSpotList").trigger("reloadGrid");
}

$(document).ready(function(){
	$("#searchScenicSpotList").jqGridFunction({
		datatype: "local",
		sortname:'id',
	   	colModel:[
	        {name:"id",index:"id",hidden:true,hidedlg:true},
	        {name:"name",index:"name",label:"景点名称",width:200,sortable:true}
		],
		height:300,
	  	onSelectAll:function(aRowids,status){ },
	    loadComplete: afterLoads,
	    multiselect:true,
	    onSelectRow:selectRowDatas,
	    rowNum:15,
	    gridComplete:function(){//列表默认选中的行
		 	if($("#aroundScenic").val()!=null && $("#aroundScenicIds").val()!=null){
		 		var selectIds = $("#aroundScenicIds").val();
		 		for(var i=0;i<selectIds.length;i++){
		 			jQuery("#searchScenicSpotList").jqGrid('setSelection',selectIds[i],true);
		 		}
		 	}
		}
	});
	function selectRowDatas(){
		var selectedCounts = getActualjqGridMultiSelectCount("searchScenicSpotList");
		var count = $("#searchScenicSpotList").jqGrid("getGridParam","records");
		if(selectedCounts == count){
			jqGridMultiSelectState("searchScenicSpotList", true);
		}else{
			jqGridMultiSelectState("searchScenicSpotList", false);
		}
	}
	reloadScenicSpots();
	function afterLoads(){
	}

	$("#reloadScenicSpot").click(function(event){
		reloadScenicSpots();
	});
	
});

</script>

