<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="addClaimProject" class="container container_24">
		<input type="hidden" id="orgId"  value="organization.id"/>
		<input type="hidden" id="type" value="${param.type }">
		<input type="hidden" id="modeClaim" value="${param.mode }">
		<div class="btnbanner btnbannerData" >
			<div class="ui-widget autosearch">
			    <input type="text" value="快速搜索项目名称" id="_deptName" maxlength="24" class="basic-input" onblur="value=(this.value=='')?'快速搜索项目名称':this.value;" onfocus="value=(this.value=='快速搜索项目名称')?'':this.value;" style="width:300;"/>
			</div>
			<a href="javascript:;" id="_fastSearch" class="search"><span>搜索</span></a>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div style="width: 100%;">
			<table id="claimProjectList"></table>
			<div id="claimProjectListPager"></div>
		</div>
</div>

<script type="text/javascript">
var orgId=<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"/>;
var flag = false;

$(document).ready(function() {
	$("#claimProjectList").jqGridFunction({
		mtype:'post',
		datatype: "local",
	    colNames:['id','orgId','项目名称','拟认领数'],
	   	colModel:[
	   	    {name:"id",index:"id",hidden:true},
	        {name:"organization.id",index:"organization.id",hidden:true},
	        {name:'projectName',index:"projectName",width:100,align:"center"},
	        {name:'claimPlans',index:'claimPlans',width:150,align:"center"}
		],
		height:102,
		multiselect:true,
		onSelectAll:function(data){
			setStr();
	  	},
		onSelectRow:function(data){
			setStr();
		},
		showColModelButton:false,
		gridComplete:function(){//列表默认选中的行
		 	if($("#claimProjectName").val()!=null && $("#claimServiceProjectIds").val()!=null){
		 		var selectIds = $("#claimServiceProjectIds").val();
		 		for(var i=0;i<selectIds.length;i++){
		 			jQuery("#claimProjectList").jqGrid('setSelection',selectIds[i],true);
		 		}
		 	}
		}
			
	});
	jQuery("#claimProjectList").jqGrid('setFrozenColumns');
	$("#claimProjectList").setGridParam({
		url:'${path}/serviceprojectManage/findServiceProjectPagerBySearchVo.action',
		datatype: "json",
		page:1
	});
	$("#claimProjectList").setPostData({
		"organization.id":orgId,
		"searchServiceProjectVo.projectName":""
   	});
	$("#claimProjectList").trigger("reloadGrid");
	
	
	$("#searchServiceTargetTeam").live("change",function(){
		var teamId = $(this).val();
		if(teamId<0){
			$("#teamId").val("-1");
			$("#sp_name").html("");
			$("#sp_memberNums").html("");
			$("#sp_establishDate").html("");
			$("#sp_remark").html("");
			return false;
		}
	});
	
	$("#_fastSearch").click(function(){
		var condition = $("#_deptName").val();
		var listParam = null;
		if('快速搜索项目名称' != condition) {
			listParam = {
				"organization.id":orgId,
				"searchServiceProjectVo.projectName":condition
			};
		}else listParam = {
				"organization.id":orgId,
				"searchServiceProjectVo.projectName":""
			};
		loadTargetTeams(listParam);
	});
	
	function loadTargetTeams(listParam){
		$("#claimProjectList").setGridParam({
			url:'${path}/serviceprojectManage/findServiceProjectPagerBySearchVo.action',
			datatype: "json",
			page:1
		});
		$("#claimProjectList").setPostData(listParam);
		$("#claimProjectList").trigger("reloadGrid");
	}
	
});


function setStr(){
	var str="";
	var id="";
	var selectId = "";
	selectId=$("#claimProjectList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectId.length;i++){
			var data=$("#claimProjectList").getRowData(selectId[i]);
			str+=data.projectName+",";
			id+=data.id+",";
	}
	$("#claimProjectName").val(str.substr(0,(str.length-1)));
	$("#claimServiceProjectIds").val(id.substr(0,(id.length-1)));
}
</script>