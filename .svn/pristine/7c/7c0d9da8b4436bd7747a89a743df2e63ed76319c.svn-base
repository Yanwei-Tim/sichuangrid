<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ include file="/baseinfo/commonPopulation/jsFunctionInclude.jsp"%>
<input type="hidden" id="orgIdForStat" value="<s:property value="#parameters.orgIdForStat"/>"/>
<%
	String isPlaning = request.getParameter("isPlaning");
	request.setAttribute("isPlaning",isPlaning);
%>
<div class="content">
<div class="ui-corner-all" id="nav">
<div class="btnbanner btnbannerData">
<jsp:include page="/common/orgSelectedComponent.jsp"/>
<div class="ui-widget autosearch">
	<input class="basic-input" type="text" value="请输入年份" name="searchText" id="searchText" maxlength="18" class="searchtxt"
		 style="width:180px;" onblur="value=(this.value=='')?'请输入年份':this.value;" onfocus="value=(this.value=='请输入年份')?'':this.value;" />
	<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
</div>
</div>
<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
<span class="lineBetween "></span>
<pop:JugePermissionTag
	ename="addNewCountrysideInfo">
	<a id="addNewCountrysideInfo" href="javascript:void(0)"><span><strong
		class="ui-ico-xz"></strong>新增</span></a>
</pop:JugePermissionTag>
<pop:JugePermissionTag ename="deleteNewCountrysideInfo">
	<a id="deleteNewCountrysideInfo" href="javascript:void(0)"><span><strong
		class="ui-ico-sc"></strong>批量删除</span></a>
</pop:JugePermissionTag> 

<pop:JugePermissionTag ename="reportNewCountrysideInfo">
	<a id="reportNewCountrysideInfo" href="javascript:void(0)"><span><strong
		class="ui-ico-sc"></strong>上报</span></a>
</pop:JugePermissionTag> 
<%-- <pop:JugePermissionTag ename="importNewCountrysideInfo">
	<a id="importNewCountrysideInfo" href="javascript:void(0)"><span><strong
		class="ui-ico-sc"></strong>导入</span></a>
</pop:JugePermissionTag> --%> 
<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
<a id="viewNewCountrysideInfo" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>查看</span></a>
	
</div>
<div style="clear: both;"></div>
<div style="">
<table id="basicInfoMationList">
</table>
<div id="basicInfoMationListPager"></div>
</div>
</div>

<div id="newVillageBasicDialog"></div>
<div id="addNewVillageBasicDialog"></div>

<script type="text/javascript">
var dialogWidth=850;
var dialogHeight=600;
var currentOrgId=getCurrentOrgId();
function operatorFormatter(el,options,rowData){
	return "<pop:JugePermissionTag ename='editNewCountrysideInfo'><a href='javascript:;' onclick='updateOperator("+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteNewCountrysideInfo'><a href='javascript:;' onclick='deleteOperator("+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
}

function onOrgChanged(orgId){
    var	initParam = {
		"newVillageBasic.organization.id":orgId,
		"newVillageBasic.isPlaning":${isPlaning}
	}

	$("#basicInfoMationList").setGridParam({
		url:'${path}/baseinfo/newVillageBasicManage/findNewVillageBasicForList.action',
		datatype:'json',
		page:1
	});
	$("#basicInfoMationList").setPostData(initParam);
	$("#basicInfoMationList").trigger("reloadGrid");
}
	
	
$(function(){
	var model=[
				{name:"id", index:"id",hidden:true,sortable:false, frozen :true},
				{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,align:'center'},
				{name:"organization.orgName",label:"上报单位",sortable:false,width:147},
				{name:"year",label:"年份",sortable:true,width:127},
				{name:"quarter",label:"季度",sortable:true,width:100,align:'center'},
				{name:"reportDate",label:'上报时间',sortable:false,width:150,align:'center'},
		        {name:"reportStatus",label:'上报状态',sortable:false,width:100,align:'center',formatter:reportStatusFormatter},
		        {name:"reportUser",label:'上报人',sortable:false,width:100,align:'center'},
		        {name:"checkStatus",label:"审核状态",sortable:true,width:100,align:'center',formatter:checkStatusFormatter},
		        {name:"checkDate",label:"审核时间",sortable:true,width:150,align:'center'},
		        {name:"checkUser",label:"审核人",sortable:true,width:100,align:'center'}
		];
	if(${isPlaning}=='1'){
		$("#importNewCountrysideInfo").remove();
		model=[
				{name:"id", index:"id",hidden:true,sortable:false, frozen :true},
				{name:"operator", index:'id',label:'操作',formatter:operatorFormatter,width:80,align:'center'},
				{name:"organization.orgName",label:"上报单位",sortable:false,width:147},
				{name:"year",label:"年份",sortable:true,width:127},
				{name:"reportDate",label:'上报时间',sortable:false,width:150,align:'center'},
		        {name:"reportStatus",label:'上报状态',sortable:false,width:100,align:'center',formatter:reportStatusFormatter},
		        {name:"reportUser",label:'上报人',sortable:false,width:100,align:'center'},
		        {name:"checkStatus",label:"审核状态",sortable:true,width:100,align:'center',formatter:checkStatusFormatter},
		        {name:"checkDate",label:"审核时间",sortable:true,width:150,align:'center'},
		        {name:"checkUser",label:"审核人",sortable:true,width:100,align:'center'}
		];
	}
	$("#basicInfoMationList").jqGridFunction({
		datatype: "local",
		mytype:"post",
	    colModel:model,
		multiselect:true,
	  	onSelectAll:function(aRowids,status){},
		onSelectRow:selectRow
	});
	jQuery("#basicInfoMationList").jqGrid('setFrozenColumns');
	onOrgChanged(getCurrentOrgId());
	
	
	$("#addNewCountrysideInfo").click(function(){
		if(!isVillage()){
			 $.messageBox({level:"warn",message:"请选择村社区层级进行新增！"});
			 return;
		}
		var title='请选择年份和季度';
		if(${isPlaning=='1'}){
			title="请选择年份";
		}
		$("#newVillageBasicDialog").createDialog({
			width: 300,
			height: 180,
			title:title,
			url:'${path}/baseinfo/newVillageBasicManage/dispatchOpearte.action?organization.id='+getCurrentOrgId()+'&mode=addBasic&isPlaning='+${isPlaning},
			buttons: {
		   		"确定" : function(event){
		   			$("#maintainForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});		
	});
	
	$("#reportNewCountrysideInfo").click(function(){
		var rowIds = $("#basicInfoMationList").jqGrid("getGridParam", "selarrrow");
		if(rowIds.length ==0){
			 $.messageBox({level:"warn",message:"请选择一条数据进行上报！"});
			 return;
		}
		if(rowIds.length >1){
			 $.messageBox({level:"warn",message:"一次只能选择一条数据上报！"});
			 return;
		}
		var newVillageBasic = $("#basicInfoMationList").getRowData(rowIds);
		if(newVillageBasic.reportStatus=="已上报" && newVillageBasic.checkStatus!="审核不通过"){
			$.messageBox({level:"warn",message:"该数据已经上报或者已经审核，无法继续上报!"});
			 return;
		}
		$.confirm({
			title:"确认上报",
			message:"确定要上报吗?一经上报，在非审核不通过情况下不可修改",
			okFunc: function(){
				$.ajax({
					url:PATH+"/baseinfo/newVillageBasicManage/reportNewVillageBasicInfo.action",
					type:"post",
					data:{
						"id":rowIds+"",
						"isPlaning":${isPlaning}
					},
					success:function(data){
						$("#basicInfoMationList").trigger("reloadGrid");
					    $.messageBox({message:"已经成功上报信息!"});
				    }
			    });
			}
		});
	});
	
	$("#deleteNewCountrysideInfo").click(function(){
		var rowIds = $("#basicInfoMationList").jqGrid("getGridParam", "selarrrow");
		if(rowIds.length ==0){
			 $.messageBox({level:"warn",message:"请选择一条或多条记录，再进行删除！"});
			 return;
		}

		for(var i=0;i<rowIds.length;i++){
			var newVillageBasic = $("#basicInfoMationList").getRowData(rowIds[i]);
			if(newVillageBasic.reportStatus=="已上报" && newVillageBasic.checkStatus!="审核不通过"){
				$.messageBox({level:"warn",message:"所选数据中包含已经上报或者已经审核信息，无法删除!"});
				 return;
			}
		}
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除，数据无法恢复",
			okFunc: function(){
				$.ajax({
					url:PATH+"/baseinfo/newVillageBasicManage/deleteNewVillageBasic.action",
					type:"post",
					data:{
						"ids":rowIds+""
					},
					success:function(data){
						$("#basicInfoMationList").trigger("reloadGrid");
					    $.messageBox({message:"已经成功删除该上报信息!"});
				    }
			    });
		   }
	 	});
	});
	
	$("#viewNewCountrysideInfo").click(function(){
		var rowIds = $("#basicInfoMationList").jqGrid("getGridParam", "selarrrow");
		if(rowIds.length ==0){
			 $.messageBox({level:"warn",message:"请至少选择一条数据进行查看！"});
			 return;
		}
		if(rowIds.length >1){
			 $.messageBox({level:"warn",message:"只能选择一条数据进行查看！"});
			 return;
		}
		$("#addNewVillageBasicDialog").createDialog({
			width: 1000,
			height: 600,
			title:${isPlaning}==0?'查看上报信息':'查看年度任务规划',
			url:'${path}/baseinfo/newVillageBasicManage/dispatchOpearte.action?organization.id='+getCurrentOrgId()+'&mode=view&newVillageBasic.id='+rowIds+'&isPlaning=${isPlaning}',
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});		
	});
	
	$("#fastSearchButton").click(function(){
		var	initParam;
		if('请输入年份' == $("#searchText").val()){
			initParam = {
					"newVillageBasic.organization.id":getCurrentOrgId(),
					"newVillageBasic.isPlaning":${isPlaning}
			}
		}else{
			initParam = {
				"newVillageBasic.organization.id":getCurrentOrgId(),
				"newVillageBasic.isPlaning":${isPlaning},
				"newVillageBasic.year":$("#searchText").val()
			}
		}
			$("#basicInfoMationList").setGridParam({
				url:'${path}/baseinfo/newVillageBasicManage/findNewVillageBasicForList.action',
				datatype:'json',
				page:1
			});
			$("#basicInfoMationList").setPostData(initParam);
			$("#basicInfoMationList").trigger("reloadGrid");
	});
	
	$("#reload").click(function(){
		$("#searchText").attr("value","请输入年份");
		onOrgChanged(getCurrentOrgId());
	});
	
	$("#refreshSearchKey").click(function(){
		$("#searchText").attr("value","请输入年份");
	});
	
	//刷新
	$("#reload").click(function(){
		onOrgChanged(getCurrentOrgId());
	});
});

function deleteOperator(id){
	if(id==null || id==""){
		$.messageBox({level:"warn",message:"请至少选择一条记录进行删除！"});
		 return;
	}
	var newVillageBasic = $("#basicInfoMationList").getRowData(id);
	if(newVillageBasic.reportStatus=="已上报" && newVillageBasic.checkStatus!="审核不通过"){
		$.messageBox({level:"warn",message:"该数据已经上报或者已经审核，无法删除!"});
		 return;
	}
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除，数据无法恢复",
		okFunc: function(){
			$.ajax({
				url:PATH+"/baseinfo/newVillageBasicManage/deleteNewVillageBasic.action",
				type:"post",
				data:{
					"ids":id+""
				},
				success:function(data){
					$("#basicInfoMationList").trigger("reloadGrid");
				    $.messageBox({message:"已经成功删除该上报信息!"});
			    }
		    });
	   }
 	});
}

function updateOperator(id){
	if(id==null || id==""){
		$.messageBox({level:"warn",message:"请至少选择一条记录进行修改！"});
		 return;
	}
	var newVillageBasic = $("#basicInfoMationList").getRowData(id);
	if(newVillageBasic.reportStatus=="已上报" && newVillageBasic.checkStatus!="审核不通过"){
		$.messageBox({level:"warn",message:"该数据已经上报或者已经审核，无法修改!"});
		 return;
	}
	$("#addNewVillageBasicDialog").createDialog({
		width: 1000,
		height: 500,
		title:${isPlaning}==0?'修改上报信息':'修改年度任务规划',
		url:'${path}/baseinfo/newVillageBasicManage/dispatchOpearte.action?organization.id='+getCurrentOrgId()+'&mode=edit&newVillageBasic.id='+id+'&isPlaning=${isPlaning}',
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});		
}

function reportStatusFormatter(el,options,rowData){
	if(rowData.reportStatus==0 || rowData.reportStatus=='0'){
		return "未上报";
	}
	return "已上报";
}
function checkStatusFormatter(el,options,rowData){
	if(rowData.checkStatus==0 || rowData.checkStatus=='0'){
		return "未审核";
	}else if(rowData.checkStatus==1 || rowData.checkStatus=='1'){
		return "审核通过";
	}else{
		return "审核不通过";
	}
}
function selectRow(){
	var count = $("#basicInfoMationList").jqGrid("getGridParam","records");
	var selectedCounts = getActualjqGridMultiSelectCount("basicInfoMationList");
	if(selectedCounts == count){
		jqGridMultiSelectState("basicInfoMationList", true);
	}else{
		jqGridMultiSelectState("basicInfoMationList", false);
	}
}
	
</script>