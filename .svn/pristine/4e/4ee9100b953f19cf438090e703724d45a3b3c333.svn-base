<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<div class="content" >
	<div id="search-condition-form" title="查询" class="container container_24">
		<input type="hidden" id="targetOrgId" value="${targetOrgId}"/>
		<input type="hidden" id="mode" value="${mode}"/>
		<input type="hidden" id="adminTarget" value="${adminTarget}"/> 
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">部门名称：</label>
		</div>
		<div class="grid_17">
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
			url:'${path}/account/accountLogsManage/searchFunctionTarget.action?mode=list',
			datatype: "json",
			page:1
		});
		$("#orgList").setPostData({
			//目标处理部门的orgId
	 		"targetOrgId":$("#targetOrgId").val(),
	 		//是查询行政部门还是职能部门
	 		"adminTarget":$("#adminTarget").val(),
	 		//查询匹配条件
	 		"tag":$("#tag").val()
		});
		$("#orgList").trigger("reloadGrid");
 	});

	$("#orgList").jqGridFunction({
		datatype: "local",
	   	colModel: [
		   	       {name:"value",sort:false,key:true,hidden:true},
		   	       {name:"label",label:"名称",align:"center"},
		   	       {name:"desc",label:"备注",width:200}
		   		],
	 	showColModelButton :false,
	   	scrollrows:true,
		multiselect:false,
		beforeSelectRow:selectCorrectOrg,
	    width:300,
	    height:205
	});
	$("#searchDealTarget").click();
	function selectCorrectOrg(rowId,e){
			$("#orgList").resetSelection();
		return true;
	}
		
});


function fillOrgInputer(elId){
	var selectedId = $("#orgList").jqGrid("getGridParam", "selrow");
	if(selectedId.length>0){
		$("#"+elId).clearPersonnelComplete();
		var rowData = $("#orgList").jqGrid("getRowData",selectedId); 
		if($("#"+elId).val()!=null && $("#"+elId)!=undefined){
			$("#"+elId+"s").val("");
			$("#"+elId).val("");
		}
		$("#"+elId).setPersonnelCompleteVal({value:rowData.value,label:rowData.label,desc:""});
		$("#"+elId+"s").setPersonnelCompleteVal({value:rowData.label,label:rowData.label,desc:""});
		
	}
}

function fillItemName(){
	var selectedId = $("#orgList").jqGrid("getGridParam", "selrow");
	fillItem(selectedId);
}
</script>

