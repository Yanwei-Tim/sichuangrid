<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />
<%request.setAttribute("moduleName",request.getParameter("moduleName"));%>
<%request.setAttribute("lowerCaseModuleName",request.getParameter("moduleName").substring(0,1).toLowerCase()+request.getParameter("moduleName").substring(1));%>
<%request.setAttribute("upperCaseModuleName",request.getParameter("moduleName").toUpperCase());%>
<%request.setAttribute("moduleCName",request.getParameter("moduleCName"));%>
<%request.setAttribute("searchType",request.getParameter("searchType"));%>
<%request.setAttribute("orgIdForStat",request.getParameter("orgIdForStat"));%>
<%request.setAttribute("supervisorPerson",request.getParameter("supervisorPerson"));%>
<%request.setAttribute("detailedType",request.getParameter("detailedType"));%>
<%request.setAttribute("mapCurrentOrgId",request.getParameter("mapCurrentOrgId"));%>

<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>
<div class="content">
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="${lowerCaseModuleName}List"> </table>
		<div id="${lowerCaseModuleName}ListPager"></div>
	</div>
	<div id="view${lowerCaseModuleName}Dialog"></div>
	<div id="noticeDialog"></div>
	<div id="${lowerCaseModuleName}MaintanceDialog"></div>
	<div id="helpPersonnelDialog"></div>
	<div id="helpPrecordDialog"></div>
	<div id="scanHeaderImage"></div>
</div>
<script>
function viewDialog(event,id){
	var rowData = $("#${lowerCaseModuleName}List").getRowData(id);
	$("#view${lowerCaseModuleName}Dialog").createDialog({
		width:dialogWidth,
		height:580,
		title:"查看${moduleCName}信息",
		url:'${path}/baseinfo/${lowerCaseModuleName}Manage/dispatchOperateByEncrypt.action?superviceRecordName='+encodeURIComponent($("#_superviceRecordName_").val())+'&supervisorName='+encodeURIComponent('${supervisorPerson}')+'&mode=view&population.id='+rowData.encryptId+'&populationType='+$("#_populationType_").val(),
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
$(function(){
	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#${lowerCaseModuleName}List").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#${lowerCaseModuleName}List").getRowData(idCollection[i]);
				if(ent.isEmphasis==1){
				$("#"+idCollection[i]+"").css('color','#778899');
			}
		}
	}
	function getSelectedIds(){
		var selectedIds = $("#${lowerCaseModuleName}List").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
	}



	function doubleClickTable(){
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			 return;
		}
		if("${lowerCaseModuleName}" == "actualHouse"){
			viewHouseDialog(selectedId);
		}else{
			viewDialog(event,selectedId);
		}
	}



	function search${moduleName}(){
		$("#${lowerCaseModuleName}List").setGridParam({
			url:"${path}/baseinfo/search${moduleName}/find${moduleName}sByQueryCondition.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var data=$("#search${moduleName}Form").serializeArray();
		var dataJson={};
		for(i=0;i<data.length;i++){
			 if (dataJson[data[i].name]) {
				           dataJson[data[i].name]=dataJson[data[i].name]+","+data[i].value;
			} else {
				            dataJson[data[i].name] = data[i].value;
			}
			//dataJson[data[i].name]=data[i].value;
		}

		$("#${lowerCaseModuleName}List").setPostData($.extend({"search${moduleName}Vo.isEmphasis":$("#isLock").val(),"organizationId":getCurrentOrgId()},dataJson));
	    $("#${lowerCaseModuleName}List").trigger("reloadGrid");
	}
	
	//地图--辖区分布饼状图查看住房
	function search${moduleName}FromMap(){
		var detailedType = "${detailedType}";
		orgId=getCurrentOrgId();
		$("#${lowerCaseModuleName}List").setGridParam({
			url:"${path}/baseinfo/search${moduleName}/fastSearchFromMap.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		$("#${lowerCaseModuleName}List").setPostData({"organizationId":'${mapCurrentOrgId}',"detailedType":detailedType});
	    $("#${lowerCaseModuleName}List").trigger("reloadGrid");
	}
	
	//快速检索
	function search(orgId){
		if(orgId == null || orgId == '' || orgId == 'undefined'){
			orgId =  getCurrentOrgId();
		}
		var conditions = $("#searchByCondition").val();
		$("#${lowerCaseModuleName}List").setGridParam({
			url:"${path}/baseinfo/search${moduleName}/fastSearch.action",
			datatype: "json",
			page:1
		});
		
		if(conditions == '请输入姓名或身份证号码') {
			 $("#${lowerCaseModuleName}List").setPostData({
				 "organizationId":orgId,
				 "search${moduleName}Vo.isEmphasis":0,
				 "search${moduleName}Vo.fastSearchKeyWords":''
			 });
		}else{
			$("#${lowerCaseModuleName}List").setPostData({
				 "organizationId":orgId,
				 "search${moduleName}Vo.isEmphasis":0,
				 "search${moduleName}Vo.fastSearchKeyWords":conditions
		    });
		}
		$("#${lowerCaseModuleName}List").trigger("reloadGrid");
	}

	$("#${lowerCaseModuleName}List").jqGridFunction({
		mtype:'post',
		datatype: "local",
		colModel: gridOption.colModel,
		colNames: gridOption.colNames,
		width:870,
		height:430,
	  	multiselect:true,
		scrollrows:true,
	   	altclass:true,
	   	loadComplete: isEmphasisFormatter,
		<pop:JugePermissionTag ename="view${moduleName}">
        	ondblClickRow: function(data){if(doubleClickTable){doubleClickTable(data);}}
		</pop:JugePermissionTag>
	});
	jQuery("#${lowerCaseModuleName}List").jqGrid('setFrozenColumns');


	$("#view").click(function(){
		doubleClickTable();
	});

	function isEmphasisFormatter(){
		var idCollection = new Array();
		idCollection=$("#${lowerCaseModuleName}List").getDataIDs();
			for(var i=0;i<idCollection.length;i++){
				var ent =  $("#${lowerCaseModuleName}List").getRowData(idCollection[i]);
				if(ent.isEmphasis==1){
				$("#${lowerCaseModuleName}List tr[id="+idCollection[i]+"]").css('color','#778899');
			}
		}
	}

	$("#fastSearchButton").click(function(){
		search(getCurrentOrgId());
	});

	/**
	if (getOrgIdForStat() != null && getOrgIdForStat()!=""){
	if('<s:property value="#parameters.isSearch"/>' == 1){
		alert("1");
		searchRectificativePerson();
	}else{
		alert("2");
		onOrgChangedForStat(getOrgIdForStat(),isGrid());
	}
}
	*/

	function getOrgIdForStat(){
		var orgIdForStat = $("#orgIdForStat").val();
		if(orgIdForStat == null || orgIdForStat == '' || orgIdForStat == 'undefined'){
			return getCurrentOrgId();
		}else{
			return orgIdForStat;
		}
	}
	
	$(document).ready(function(){
		if("${searchType}"=='fast'){
			search(getOrgIdForStat())
		}else if("${searchType}"=='advanced'){
			search${moduleName}();
			$("#${moduleName}statisticsDialog").dialog("close");
		}else if("${searchType}"=='fastFromMap'){
			search${moduleName}FromMap();
		}
	});
})
</script>