<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<s:if test='!"areaDailyYear".equals(modeType)'>
			<pop:JugePermissionTag ename="addWorkingRecord">
				<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增  </span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="updateWorkingRecord">
				<a id="update" href="javascript:void(0)"><span><strong class="ui-ico-xg"></strong>修改</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="deleteWorkingRecord">
				<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>删除</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="upWorkingRecord">
				<a id="submitReport" href="javascript:void(0)"><span><strong class="ui-ico-appearnewspapers"></strong>上报</span></a>
			</pop:JugePermissionTag>
		</s:if>
		<s:if test='"areaDailyYear".equals(modeType)'>
			<pop:JugePermissionTag ename="backWorkingRecord">
				<a id="back" href="javascript:void(0)"><span><strong class="ui-ico-rollback"></strong>回退</span></a>
			</pop:JugePermissionTag>
		</s:if>
		<pop:JugePermissionTag ename="viewWorkingRecord,viewAreaWorkingRecord">
			<a id="view" href="javascript:void(0)"><span><strong class="ui-ico-cakan"></strong>查看</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
	</div>

	<div style="width: 100%;" id="grid_content" >
		<table id="dailyLogList"></table>
		<div id="dailyLogListPager"></div>
	</div>
</div>
<form id="downloadReport" action="${path}/workingRecord/investigationRemediationManage/downloadInvestigationRemediation.action" method="post">
	<input type="hidden" name="investigationRemediation.id" id="investigationRemediationId" />
</form>
<script type="text/javascript">
<pop:formatterProperty name="submitState" domainName="@com.tianque.domain.property.PropertyTypes@WORKING_RECORD_SUBMITSTATE" />
var widthWhenAdd=1000;
var heightWhenAdd=620;
var dailyId = '${dailyDirectoryId}';
var globalFormatterArrary=new Array();
function deleteDailyLog(){
	var selectedId = $("#dailyLogList").getGridParam("selrow");
	$.ajax({
		url:'${path}/workingRecord/investigationRemediationManage/deleteInvestigationRemediation.action?investigationRemediation.id='+selectedId,
		success:function(data){
			if(data == true){
				$("#dailyLogList").delRowData(selectedId);
				$.messageBox({ message:"成功删除“排查整治、强基促稳”专项活动月报表!"});
				return;
			}else{
				$.messageBox({ message:"考核评估已参考此项，请勿删除！",level: "error"});
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
		url:"${path}/workingRecord/investigationRemediationManage/investigationRemediationList.action?organization.id="+_currentOrgId+"&dailyDirectoryId="+dailyId,
		datatype: "json",
		colNames:['id','报表名称','上报状态','上报时间','制表单位','审核人','填报人','填报时间','','','有效性'],
	   	colModel:[
	        {name:"id",index:"id",hidden:true},
	        {name:"name",sortable:false,width:300},
	        {name:"submitState.id",sortable:false,width:100,formatter: submitStateFormatter},
	        {name:"submitTime",sortable:false,width:150},
	  		{name:"organization.orgName",sortable:false,width:200},
	        {name:"lister",sortable:false,width:100},
	        {name:'dealPerson',sortable:false,width:100},
	        {name:"createDate",sortable:false,width:150},
	        {name:"orgInternalCode",sortable:false,hidden:true,hidedlg:true},
	        {name:"submitState.internalId",sortable:false,hidden:true,hidedlg:true},
	        {name:"expiredEntering",sortable:false,width:200,formatter:expiredEnteringFormatter}

		],
		ondblClickRow: doubleClickTable,
		gridComplete:afterGridComplete
	};

	$("#dailyLogList").jqGridFunction(gridoption);

	function doubleClickTable(){
		var selectedId = $("#dailyLogList").getGridParam("selrow");
		if(selectedId==null){
	 		return;
		}
		$("#dailyLogMaintanceDialog").createDialog({
			width:widthWhenAdd,
			height:heightWhenAdd,
			title:'查看'+innerTitle+'信息',
			url:"${path}/workingRecord/investigationRemediationManage/dispatch.action?mode=view&investigationRemediation.id="+selectedId+"&dailyDirectoryId="+dailyDirectoryId+"&reportTypeInternalId="+reportTypeInternalId,
			buttons : {
 				"导出":function(){
 			    exportData(selectedId);
		    	$(this).dialog("close");
 			    },
				"打印" : function(){
				$(this).dialog("close");
				 print(selectedId);
	  	       	},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
		}
	function exportData(selectedId){
		$("#investigationRemediationId").val(selectedId);
		$("#downloadReport").submit();
	}
	function print(selectedId){
		$("#dailyLogMaintanceDialog").createDialog({
			width: widthWhenAdd,
			height: heightWhenAdd,
			title: '打印'+innerTitle+'信息',
			url:"${path}/workingRecord/investigationRemediationManage/dispatch.action?mode=print&investigationRemediation.id="+selectedId+"&dailyDirectoryId="+dailyDirectoryId+"&reportTypeInternalId="+reportTypeInternalId,
			buttons: {
				"确定" : function(){
				$("#securityOfImportPrint").printArea();
		        $(this).dialog("close");
		   		},
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}

	
	$("#add").click(function(event){
		var year=$("#dailyYear").find("option[value="+$("#dailyYear").val()+"]").text();

		if(!selectedNode.attributes.leaf){
			$.messageBox({message:"该目录不能新增台账信息，请选择底层目录新增!",level:"warn"});
			return ;
		}else{$("#dailyLogMaintanceDialog").createDialog({
			width:widthWhenAdd,
			height:heightWhenAdd,
			title:'新增'+innerTitle+'信息',
			modal : true,
			url:"${path}/workingRecord/investigationRemediationManage/dispatch.action?reportTypeInternalId="+reportTypeInternalId+"&mode=add&dailyDirectoryId="+dailyDirectoryId+"&dailyYear.name="+year,
			buttons :{
				"保存" : function(){
					submitData();

				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
		}
	});

	$("#update").click(function(event){
		var selectedId = $("#dailyLogList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({message:"请选择要修改的台账信息!",level:"warn"});
	 		return;
		}
		var rowData=$("#dailyLogList").getRowData(selectedId);
		if('<s:property value="@com.tianque.domain.property.WorkingRecordSubmitstate@HAS_SUBMIT"/>'==rowData["submitState.internalId"]){
			$.messageBox({message:"该台账信息已经上报,不能修改!",level:"warn"});
	 		return;
		}
		$("#dailyLogMaintanceDialog").createDialog({
			width:widthWhenAdd,
			height:heightWhenAdd,
			title:'修改'+innerTitle+'信息',
			url:"${path}/workingRecord/investigationRemediationManage/dispatch.action?mode=edit&investigationRemediation.id="+selectedId+"&reportTypeInternalId="+reportTypeInternalId,
			buttons : {
				"保存" : function(){
					submitData();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});

	$("#delete").click(function(event){
		var selectedId = $("#dailyLogList").getGridParam("selrow");
		var rowData=$("#dailyLogList").getRowData(selectedId);
		if(selectedId==null){
			$.messageBox({message:"请选择要删除的台账信息!",level:"warn"});
	 		return;
		}
		if('<s:property value="@com.tianque.domain.property.WorkingRecordSubmitstate@HAS_SUBMIT"/>'==rowData["submitState.internalId"]){
			$.messageBox({message:"该台账信息已经上报,不能删除!",level:"warn"});
	 		return;
		}
		$.confirm({
			title:"确认删除",
			message:'此'+innerTitle+'信息删除后，将无法恢复,您确认删除吗?',
			width: 400,
			okFunc: function(){
				deleteDailyLog();
			}
		});
	});

	$("#view").click(function(event){
		var selectedId = $("#dailyLogList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({message:"请选择要查看的台账信息!",level:"warn"});
	 		return;
		}
		doubleClickTable();
	});

	$("#back").click(function(event){
		var selectedId = $("#dailyLogList").getGridParam("selrow");
		var rowData=$("#dailyLogList").getRowData(selectedId);
		if(selectedId == null){
			$.messageBox({message:"请选择要回退的台账信息!",level:"warn"});
			return ;
		}
		if(selectedId!=null){
	 		if(rowData["submitState.internalId"]=='<s:property value="@com.tianque.domain.property.WorkingRecordSubmitstate@HAS_SUBMIT"/>'){
	 			backRemindMessage(selectedId,rowData);
		 	}else{
		 		$.messageBox({message:"该信息未上报!"});
				return;
			}
		}else{
			return;
		}
	});

	$("#reload").click(function(){
		$("#dailyLogList").setGridParam({
			url:"${path}/workingRecord/investigationRemediationManage/investigationRemediationList.action",
			postData: {
				'dailyDirectoryId':dailyId,
				'organization.id':_currentOrgId
			}
		});
		$("#dailyLogList").trigger("reloadGrid");
	})

	$("#submitReport").click(function(){
		var selectedId = $("#dailyLogList").getGridParam("selrow");
		var rowData=$("#dailyLogList").getRowData(selectedId);
		if(selectedId==null){
			$.messageBox({message:"请选择要上报的台账信息!",level:"warn"});
	 		return;
		}
		if('<s:property value="@com.tianque.domain.property.WorkingRecordSubmitstate@HAS_SUBMIT"/>'==rowData["submitState.internalId"]){
			$.messageBox({message:"该台账信息已经上报!",level:"warn"});
	 		return;
		}
		if(!judgeReportCondition()){
			$.messageBox({message:"该月上报该信息未填写填报人!"});
			return;
		}
		$.confirm({
			title:"确认上报",
			message:"上报后，将无法修改,您确认上报吗?",
			width: 400,
			okFunc: function(){
			reportedInvestigation();
			}
		});
	});
});
function reportedInvestigation(){
	var selectedId = $("#dailyLogList").getGridParam("selrow");
	$.ajax({
		url:"${path}/workingRecord/investigationRemediationManage/reportInvestigationRemediation.action",
		data:{
			"investigationRemediation.id":selectedId,
			"investigationRemediation.dailyDirectory.id":dailyId
		},
		success:function(responseData){
			if (responseData != null){
			    $.messageBox({message:"已经成功上报该信息!"});
			    $("#dailyLogList").trigger("reloadGrid");
			}else{
				$.messageBox({ message:"该信息不能上报!"});
			}
		}
	});
}
function judgeReportCondition(){
	var selectedId = $("#dailyLogList").getGridParam("selrow");
	var judgeReport;
	$.ajax({
		url:"${path}/workingRecord/investigationRemediationManage/judgeReportCondition.action",
		data:{
			"investigationRemediation.id":selectedId
		},
		async : false,
		success:function(responseData){
			judgeReport = responseData;
		}
	});
	return judgeReport;
}
function backRemindMessage(selectedId,rowData){
	var url="${path}/daily/statementsReportedManage/backDailyLog.action?dailyLogId="+selectedId+"&msgtitle="+
	encodeURIComponent(rowData.name+"退回提醒！")+"&mode=issue";
	$("#dailyLogMaintanceDialog").createDialog({
		width:480,
		height:260,
		title:"退回提醒",
		url:url,
		buttons :{
			"保存" : function(){
				$("#backDialog-form").submit();
				$("#dailyLogList").trigger("reloadGrid");
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}
function expiredEnteringFormatter(cellvalue, options, rowObject){
	if(cellvalue=='1'){
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
	 $("#dailyLogList>tbody>tr[id='"+rowId+"']").css('color','#f60')
	}
	globalFormatterArrary=new Array();
}
</script>


