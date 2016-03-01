<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="content" >
	<div id="search-condition-form" title="查询" class="container container_24">
		<input type="hidden" id="keyId" value="${keyId}"/>
		<input type="hidden" id="mode" value="${mode}"/>
		<input type="hidden" id="adminTarget" value="${adminTarget}"/>
		<input type="hidden" id="dealCode" value="${dealCode}"/>
		<input type="hidden" id="exceptIds" value="${exceptIds}"/>
		
		<div class="grid_3 lable-right">
			<label class="form-lbl">部门名称：</label>
		</div>
		<div class="grid_18">
			<input name="tag" type="text" id="tag" maxlength="50" class="form-txt"/>
		</div>
		<div class="grid_3">
			<input name="search" type="button" style='width:55px;height:25px' value="查询" id="searchDealTarget" />
		</div>
		<div class='clearLine'>&nbsp;</div>
	</div>
	<div style="width: 100%;">
	<table id="orgList"></table>
	<div id="orgListPager"></div>
	</div>
</div>
<script>
$(document).ready(function(){
	$("#searchDealTarget").click(function(){
		$("#orgList").setGridParam({
			url:getSearchAction(),
			datatype: "json",
			page:1
		});
		$("#orgList").setPostData({
	 		"keyId":$("#keyId").val(),
	 		"adminTarget":$("#adminTarget").val(),
	 		"dealCode":$("#dealCode").val(),
	 		"exceptIds":$("#exceptIds").val(),
	 		"tag":$("#tag").val()
		});
		$("#orgList").trigger("reloadGrid");
 	});

	function getSearchAction(){
		if ($("#mode").val()=="searchTarget"){
			return "${path}/fourTeamsIssueManage/searchTransferTarget.action?mode=list";
		}else if ($("#mode").val()=="searchTells"){
			return "${path}/fourTeamsIssueManage/searchTellTarget.action?mode=list";
		}else{
			return 	"";
		}
	}
 	
	function canMultiSelected(){
		return 	$("#mode").val()=="searchTells";
	}

	$("#orgList").jqGridFunction({
		datatype: "local",
	   	colModel: [
		   	       {name:"value",sort:false,key:true,hidden:true},
		   	       {name:"label",label:"名称"},
		   	       {name:"desc",label:"备注"}
		   		],
	 	showColModelButton :false,
	   	scrollrows:true,
		multiselect:true,
		beforeSelectRow:selectCorrectOrg,
		//onSelectAll:selectCorrectOrg,
	    width:500,
	    height:205
	});
	$("#searchDealTarget").click();
	function selectCorrectOrg(rowId,e){
		if (!canMultiSelected()){
			$("#orgList").resetSelection();
		}
		return true;
	}
		
});


function fillOrgInputer(elId,isMultiselect){
	var selectedIds = $("#orgList").jqGrid("getGridParam", "selarrrow");
	if(selectedIds.length>0){
		if(!isMultiselect){
		$("#"+elId).clearPersonnelComplete();
		}
		for(var i=0;i<selectedIds.length;i++){
		var rowData = $("#orgList").jqGrid("getRowData",selectedIds[i]); 
		$("#"+elId).setPersonnelCompleteVal({value:rowData.value,label:rowData.label,desc:""});
		}
	}
}

function fillItemName(){
	var selectedId = $("#orgList").jqGrid("getGridParam", "selrow");
	fillItem(selectedId);
}
</script>

