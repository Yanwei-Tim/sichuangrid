<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<s:if test='!"areaDailyYear".equals(modeType)'>
			<pop:JugePermissionTag ename="addWorkingRecord">
				<a id="add" href="javascript:;"><span>新增</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="updateWorkingRecord">
				<a id="update" href="javascript:void(0)"><span>修改</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="deleteWorkingRecord">
				<a id="delete" href="javascript:void(0)"><span>删除</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="upWorkingRecord">
				<a id="reported" href="javascript:void(0)"><span>上报</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="backWorkingRecord">
				<a id="back" href="javascript:void(0)"><span>回退</span></a>
			</pop:JugePermissionTag>
		</s:if>
		
		<pop:JugePermissionTag ename="viewWorkingRecord,viewAreaWorkingRecord">
				<a id="view" href="javascript:void(0)"><span>查看</span></a>
			</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchWorkingRecord,searchAreaWorkingRecord">
			<a id="search" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
<%-- 		<a id="print" href="javascript:void(0)"><span>打印</span></a> --%>
	</div>

	<div style="width: 100%;" id="grid_content" >
		<table id="keyAreasOfInvestigationInfoList"></table>
		<div id="keyAreasOfInvestigationInfoListPager"></div>
	</div>
	<div id="backkeyDialog"></div>
</div>
<script type="text/javascript">

var widthWhenAdd=700;
var heightWhenAdd=460;
var dailyId = '${dailyDirectoryId}';
//var globalFormatterArrary=new Array();
function viewKeyAreasInfo(){
	var selrowId = $("#keyAreasOfInvestigationInfoList").jqGrid("getGridParam","selrow");
	if(null == selrowId){
		$.messageBox({message:"请选择要查看的台账信息!",level:"warn"});
		return ;
	}
	var _selectedNode = $.getSelectedNode(tree);
	$("#dailyLogMaintanceDialog").createDialog({
		width:widthWhenAdd,
		height:heightWhenAdd,
		title:'查看'+innerTitle+'信息',
		modal : true,
		url:"${path}/daily/keyAreasOfInvestigationInfoManage/dispatch.action?mode=view&dailyDirectory.id="+dailyDirectoryId+"&keyAreasOfInvestigationInfo.id="+selrowId,
		buttons :{
			"打印" : function(){
				investigationDlgPrint(selrowId);
	  	   	},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}

function investigationDlgPrint(id){
	var id = $("#keyAreasOfInvestigationInfoList").jqGrid("getGridParam", "selrow");
	$("#dailyLogMaintanceDialog").createDialog({
		width: 800,
		height: 430,
		title:"打印"+innerTitle+'信息',
		url:'${path}/daily/keyAreasOfInvestigationInfoManage/dispatch.action?mode=print&keyAreasOfInvestigationInfo.id='+id,
		buttons: {
		   "打印" : function(){
				$("#keyAreasOfInvestigationInfoDlgPrint").printArea();
	        	$(this).dialog("close");
	  	   },
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

$(document).ready(function(){
	var selectedNode = $.getSelectedNode(tree);
	<s:if test='"areaDailyYear".equals(modeType)'>
		var _currentOrgId = $("#currentOrgId").val();
	</s:if>
	<s:else>
		var _currentOrgId = '<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"/>';
	</s:else>
	var gridoption = {
		url:"${path}/daily/keyAreasOfInvestigationInfoManage/findKeyAreasOfInvestigationInfos.action?organization.id="+_currentOrgId+"&dailyDirectory.id="+'${dailyDirectoryId}'+"&modeType=${modeType}",
		datatype: "json",
		height:$("div.ui-layout-center").height()-130,
	   	colModel:[
			{name:"id",index:'id',hidden:true},
			{name:"investigationOrg.orgName",index:'investigationOrgid',sortable:true,width:100, label:'排查部门'},
			{name:"status",index:'status',width:100, label:'状态',align:"center",formatter:statusFormatter},
			{name:"warningOrListing",index:'warningOrListing',width:100, label:'警示或挂牌'},
			{name:"mainProblem",index:'mainProblem',width:250, label:'主要问题'},
			{name:"policiesAndMeasures",index:'policiesAndMeasures',width:300, label:'对策与措施'},
			{name:"areaName",index:'areaName',width:150, label:'地区名称'},
			{name:"investigationDate",index:'telephone',sortable:false,width:100, label:'排查时间'},
			{name:"reportedDate",sortable:false,width:100,align:"center", label:'上报时间'},
			{name:"remark",index:'remark',width:120, label:'备注'},
			{name:"subOrgid",index:'subOrgid',width:120,hidden:true,hidedlg:true,label:'上报单位'},
			{name:"investigationOrg.id",width:100,hidden:true,hidedlg:true,label:'排查id'}
			//{name:"expiredEntering",width:100,hidden:false,label:'有效性',formatter:expiredEnteringFormatter}
		],
		ondblClickRow:viewKeyAreasInfo
		//gridComplete:afterGridComplete
	};

	$("#keyAreasOfInvestigationInfoList").jqGridFunction(gridoption);

	$("#reload").click(function(){
		$("#keyAreasOfInvestigationInfoList").setGridParam({
			url:"${path}/daily/keyAreasOfInvestigationInfoManage/findKeyAreasOfInvestigationInfos.action",
			datatype:"json",
			page:1
		});
		$("#keyAreasOfInvestigationInfoList").setPostData({
			'dailyDirectory.id':'${dailyDirectoryId}',
			'organization.id':_currentOrgId,
			'modeType':'${modeType}'
		});
		
		$("#keyAreasOfInvestigationInfoList").trigger("reloadGrid");
	});
	
	$("#add").click(function(event){
		if(!selectedNode.attributes.leaf){
			$.messageBox({message:"该目录不能新增台账信息，请选择底层目录新增!",level:"warn"});
			return ;
		}else{$("#dailyLogMaintanceDialog").createDialog({
			width:widthWhenAdd,
			height:heightWhenAdd,
			title:'新增'+innerTitle+'信息',
			modal : true,
			url:'${path}/daily/keyAreasOfInvestigationInfoManage/dispatch.action?mode=add&organization.id='+getCurrentOrgId()+'&dailyDirectory.id='+dailyDirectoryId,
			buttons :{
				"保存" : function(){
					$("#dailyLog-form").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
		}
	});

	$("#update").click(function(event){
		var selrowId = $("#keyAreasOfInvestigationInfoList").jqGrid("getGridParam","selrow");
		if(null == selrowId){
			$.messageBox({message:"请选择要修改的台账信息!",level:"warn"});
			return ;
		}
		var rowData=$("#keyAreasOfInvestigationInfoList").getRowData(selrowId);
		if("已上报" == rowData["status"]){
			$.messageBox({message:"该台账信息已经上报,不能修改!",level:"warn"});
	 		return;
		}
		$("#dailyLogMaintanceDialog").createDialog({
			width:widthWhenAdd,
			height:heightWhenAdd,
			title:'修改'+innerTitle+'信息',
			url:'${path}/daily/keyAreasOfInvestigationInfoManage/dispatch.action?mode=edit&keyAreasOfInvestigationInfo.id='+selrowId,
			buttons : {
				"保存" : function(){
					$("#dailyLog-form").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});

	function deleteKeyAreas(selrowId){
		$.ajax({
			url:"${path}/daily/keyAreasOfInvestigationInfoManage/deleteKeyAreasOfInvestigationInfoById.action",
			data:{'keyAreasOfInvestigationInfoids':selrowId},
			success:function(responseData){
				if(responseData == true){
				    $.messageBox({message:"成功删除治安重点排查情况!"});
					$("#keyAreasOfInvestigationInfoList").trigger("reloadGrid");
				}else{
					$.messageBox({ message:"考核评估有引用，不能删除!",level: "warn"	});
				}
				return;
			}
		});
	}

	$("#delete").click(function(event){
		var selrowId = $("#keyAreasOfInvestigationInfoList").jqGrid("getGridParam","selrow");
		if(null == selrowId){
			$.messageBox({message:"请选择要删除的台账信息!",level:"warn"});
			return ;
		}
		var rowData=$("#keyAreasOfInvestigationInfoList").getRowData(selrowId);
		if("已上报" == rowData["status"]){
			$.messageBox({message:"该台账信息已经上报,不能删除!",level:"warn"});
	 		return;
		}
		$.confirm({
			title:"确认删除",
			message:'此'+innerTitle+'信息删除后，将无法恢复,您确认删除吗?',
			width: 400,
			okFunc: function(){
				deleteKeyAreas(selrowId);
			}
		});
	});

	$("#view").click(function(event){
		viewKeyAreasInfo();
	});
	
	$("#search").click(function(){
		$("#dailyLogMaintanceDialog").createDialog({
			width:widthWhenAdd,
			height:heightWhenAdd,
			title:'查询'+innerTitle+'信息',
			url:'${path}/daily/keyAreasOfInvestigationInfoManage/dispatch.action?mode=search&organization.id='+_currentOrgId,
			buttons : {
				"查询" : function(){
				 searchkeyAreasOfInvestigationInfo(_currentOrgId);
					$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});

	function subOrgIdsArrayRemove(orgids,id){
		for(var i=0;i<orgids.length;i++){
			if(orgids[i]==id)
				orgids.remove(i);
		}
		return orgids;
	}
	function getSubOrgIds(ids){
		var subOrgIds=new Array();
		for(var i=0;i<ids.length;i++){
			var rowData=$("#keyAreasOfInvestigationInfoList").getRowData(ids[i]);
			var orgId=rowData.subOrgid;
			if(orgId==''||orgId==null)
				continue;
			else{
				subOrgIds=subOrgIdsArrayRemove(subOrgIds,orgId);
				subOrgIds.push(orgId);
			}
		}
		return subOrgIds;
	}

	$("#back").click(function(event){
		var selrowId = $("#keyAreasOfInvestigationInfoList").jqGrid("getGridParam","selrow");
		if(null == selrowId){
			$.messageBox({message:"请选择要回退的台账信息!",level:"warn"});
			return ;
		}
		var rowData=$("#keyAreasOfInvestigationInfoList").getRowData(selrowId);
		if(!rowData["subOrgid"]){
			$.messageBox({message:"本部门新增的台账信息不能回退!",level:"warn"});
			return ;
		}
		if("已上报" == rowData["status"]){
			$.messageBox({message:"该台账信息已经上报,不能回退!",level:"warn"});
	 		return;
		}
		var orgids=rowData["subOrgid"];
		$("#backkeyDialog").createDialog({
			width: 500,
			height: 330,
			title:'回退'+innerTitle+'信息',
			modal : true,
			url:'${path}/daily/keyAreasOfInvestigationInfoManage/dispatch.action?mode=back&keyAreasOfInvestigationInfoids='+selrowId+'&subOrgIds='+orgids.toString(),
			buttons: {
				"保存" : function(event){
	   			$("#backDialog-form").submit();
	   			},
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	});

	function reportedInvestigation(id){
		$.ajax({
			url:"${path}/daily/keyAreasOfInvestigationInfoManage/reportedKeyAreasOfInvestigationInfoById.action",
			data:{
				"keyAreasOfInvestigationInfoids":id,
				 "keyAreasOfInvestigationInfo.dailyDirectory.id":dailyId
			},
			success:function(responseData){
				var now=new Date();
				if (responseData != null){
				    $.messageBox({message:"成功上报该台账信息!"});
				    $("#keyAreasOfInvestigationInfoList").trigger("reloadGrid");

				}else{
					$.messageBox({ message:"该台账信息不能上报!",level: "warn"});
				}
			}
		});
	}


	$("#reported").click(function(event){
		var selrowId = $("#keyAreasOfInvestigationInfoList").jqGrid("getGridParam","selrow");
		if(null == selrowId){
			$.messageBox({message:"请选择要上报的台账信息!",level:"warn"});
			return ;
		}
		var rowData=$("#keyAreasOfInvestigationInfoList").getRowData(selrowId);
		if("已上报" == rowData["status"]){
			$.messageBox({message:"该台账信息已经上报!",level:"warn"});
	 		return;
		}
		$.confirm({
			title:"确认上报",
			message:"上报后，将无法修改,您确认上报该台账信息吗?",
			width: 400,
			okFunc: function(){
				reportedInvestigation(selrowId);
			}
		});
	});
	
	$("#print").click(function(event){
		var selrowId = $("#keyAreasOfInvestigationInfoList").jqGrid("getGridParam","selrow");
		if(null == selrowId){
			$.messageBox({message:"请选择要打印的台账信息!",level:"warn"});
			return ;
		}
        $("#dailyLogMaintanceDialog").createDialog({
			width:990,
			height:500,
			title:'打印'+innerTitle+'信息',
			url:"${path}/daily/keyAreasOfInvestigationInfoManage/findKeyAreasOfInvestigationInfosForPrint.action?organization.id="+_currentOrgId+"&keyAreasOfInvestigationInfoids="+selrowId,
			buttons : {
				"打印" : function(){
				    $("#printKeyAreasOfInvestigationInfoList").printArea();
				    $(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
});

function statusFormatter(el, options, rowData){
	if(rowData.status == <s:property value="@com.tianque.state.KeyAreasOfInvestigationInfoState@UNREPORTED" />){
		return "未上报";
	}else if(rowData.status == <s:property value="@com.tianque.state.KeyAreasOfInvestigationInfoState@REPORTED" />){
		return "已上报";
	}else if(rowData.status == <s:property value="@com.tianque.state.KeyAreasOfInvestigationInfoState@BACKED" />){
		return "已回退";
	}
}

function searchkeyAreasOfInvestigationInfo(_currentOrgId){
	var orgName=$('#orgName').val();
	var areaName=$('#areaName').val();
	var investigationMaxDate=$('#dealTo').val();
	var investigationMinDate=$('#dealFrom').val();
	var mainProblem=$('#mainProblem').val();
	var policiesAndMeasures=$('#policiesAndMeasures').val();
	var dailyDirectoryId=$('#dailyDirectoryId').val();
	var warningOrListing=$('#warningOrListing').val();
	$("#keyAreasOfInvestigationInfoList").setGridParam({
		url:'${path}/daily/keyAreasOfInvestigationInfoManage/searchKeyAreasOfInvestigationInfoWorkingRecords.action',
		datatype: "json",
		page:1
	});
	var searchCondition={
	 	"organization.id":_currentOrgId,
	    "keyAreasOfInvestigationInfoVo.dailyDirectory.id":dailyDirectoryId,
	    "keyAreasOfInvestigationInfoVo.investigationOrg.orgName":orgName,
	    "keyAreasOfInvestigationInfoVo.investigationMinDate":investigationMinDate,
	    "keyAreasOfInvestigationInfoVo.investigationMaxDate":investigationMaxDate,
	    "keyAreasOfInvestigationInfoVo.areaName":areaName,
	    "keyAreasOfInvestigationInfoVo.mainProblem":mainProblem,
	    "keyAreasOfInvestigationInfoVo.policiesAndMeasures":policiesAndMeasures,
	    "keyAreasOfInvestigationInfoVo.warningOrListing":warningOrListing
	   }
	$("#keyAreasOfInvestigationInfoList").setPostData(searchCondition);
	$("#keyAreasOfInvestigationInfoList").trigger("reloadGrid");
}
/**
function expiredEnteringFormatter(cellvalue, options, rowObject){
	if(cellvalue=='1'){
		if(globalFormatterArrary==null){
			globalFormatterArrary=new Array();
		}
		globalFormatterArrary.push(options.rowId);
		return "过期录入";
	}else{
		return "有效录入";
	}
}
function afterGridComplete(){
	var rowId;
	for(var i=0;i<globalFormatterArrary.length;i++){
	  rowId=globalFormatterArrary[i];
	 $("#keyAreasOfInvestigationInfoList>tbody>tr[id='"+rowId+"']").css('color','#f60')
	}
	globalFormatterArrary=null;
}
**/
</script>


