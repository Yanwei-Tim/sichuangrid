<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<a id="submitReport" href="javascript:void(0)"><span>上报</span></a>
		</pop:JugePermissionTag>
		</s:if>

		<s:if test='"areaDailyYear".equals(modeType)'>
		<pop:JugePermissionTag ename="backAreaWorkingRecord">
		<a id="back" href="javascript:void(0)"><span>回退</span></a>
		</pop:JugePermissionTag>
		</s:if>

		<pop:JugePermissionTag ename="viewWorkingRecord,viewAreaWorkingRecord">
		<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	</div>

	<div style="width: 100%;" id="grid_content" >
		<table id="publicSecurityList"></table>
		<div id="publicSecurityListPager"></div>
	</div>
</div>
<form id="downloadReport" action="${path}/daily/publicSecurityRenovateManage/downloadEmphasisSafetyDetail.action" method="post">
	<input type="hidden" name="emphasisSafetyDetail.id" id="emphasisSafetyDetailId" />
	<input type="hidden" name="emphasisSafetyDetail.dailyDirectory" id="dailyDirectory" value="${emphasisSafetyDetail.dailyDirectory}"/>
	
</form>
<script type="text/javascript">
<pop:formatterProperty name="submitState" domainName="@com.tianque.domain.property.PropertyTypes@WORKING_RECORD_SUBMITSTATE" />
var widthWhenAdd=780;
var heightWhenAdd=600;
var dailyId = '${dailyDirectoryId}';
var globalFormatterArrary=new Array();
function formatterAttachFile(el,options,rowData){
	if(rowData.dailyLogAttachFile.length>0){
		return "<div style='clear:both' align='center'><a href='javascript:;' id='dailyLog_"+rowData.id+"' style='color:#666666' class='popUpMore' dailyLogId='"+rowData.id+"' >附件>></a></div>";
	}
	return "";
}

function deletePublicSecurity(){
	var selectedId = $("#publicSecurityList").getGridParam("selrow");
	$.ajax({
		url:'${path}/daily/publicSecurityRenovateManage/deleteEmphasisSafetyDetail.action?emphasisSafetyDetail.id='+selectedId,
		success:function(data){
			if(data == true){
				$("#publicSecurityList").delRowData(selectedId);
				$.messageBox({ message:"成功删除治安重点整治!"});
				return;
			}else{
				$.messageBox({ message:"考核评估已参考此项，请勿删除！",level: "warn"});
			}
        }
	});
}

$(document).ready(function(){
	var selectedNode = $.getSelectedNode(tree);
	var _orgId = "<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()'/>";
	var _currentOrgId = $("#currentOrgId").val();
	var _parentOrg = $("#childFirstParentOrg").val();
	if(parseInt(_orgId) != parseInt(_parentOrg)){
		$("#back").hide();
	}
	$("#publicSecurityList").jqGridFunction({
		<s:if test='"areaDailyYear".equals(modeType)'>
		url:"${path}/daily/publicSecurityRenovateManage/emphasisSafetyDetailList.action?organization.id="+_currentOrgId+"&dailyDirectoryId="+dailyId+"&modeType=${modeType}",
		</s:if>
		<s:else>
		url:"${path}/daily/publicSecurityRenovateManage/emphasisSafetyDetailList.action?organization.id="+_orgId+"&dailyDirectoryId="+dailyId+"&modeType=${modeType}",
		</s:else>
		datatype: "json",
		height:$("div.ui-layout-center").height()-130,
		colNames:['id','报表名称','上报状态','上报时间','制表单位','制表人','签发人','建表时间','','','有效性'],
		colModel:[
			{name:"id",index:"id",hidden:true},
			{name:"name",sortable:false,width:260},
	        {name:"submitState.id",sortable:false,width:100,align:"center",formatter: submitStateFormatter},
	        {name:"submitTime",sortable:false,align:"center",width:150},
	  		{name:"organization.orgName",sortable:false,width:120},
	        {name:"lister",sortable:false,width:100},
	        {name:'dealPerson',sortable:false,width:100},
	        {name:"createDate",sortable:false,align:"center",width:150},
	        {name:"orgInternalCode",sortable:false,hidden:true,hidedlg:true},
	        {name:"submitState.internalId",sortable:false,hidden:true,hidedlg:true},
	        {name:'expiredEntering',hidden:false,sortable:false,width:150,formatter:expiredEnteringFormatter}
		],
		scrollrow:true,
		showColModelButton:true,
		viewrecords: true,
		ondblClickRow: doubleClickTable,
		gridComplete:afterGridComplete
	});
	$("#add").click(function(event){
		var year=$("#dailyYear").find("option[value="+$("#dailyYear").val()+"]").text();
		if(!selectedNode.attributes.leaf){
			$.messageBox({message:"该目录不能新增台账信息，请选择底层目录新增!",level:"warn"});
			return ;
		}else{
			$("#dailyLogMaintanceDialog").createDialog({
				width:780,
				height:630,
				title:'新增'+innerTitle+'信息',
				modal : true,
				url:"${path}/daily/publicSecurityRenovateManage/dispatch.action?reportTypeInternalId="+reportTypeInternalId+"&mode=add&dailyDirectoryId="+dailyDirectoryId+"&dailyYear.name="+year,
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
		var selectedId = $("#publicSecurityList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({message:"请选择要修改的台账信息!",level:"warn"});
	 		return;
		}
		var rowData=$("#publicSecurityList").getRowData(selectedId);
		if("已上报"==rowData["submitState.id"]){
			$.messageBox({message:"该台账信息已经上报,不能修改!",level:"warn"});
	 		return;
		}

		$("#dailyLogMaintanceDialog").createDialog({
			width:780,
			height:630,
			title:'修改'+innerTitle+'信息',
			url:"${path}/daily/publicSecurityRenovateManage/dispatch.action?mode=edit&emphasisSafetyDetail.id="+selectedId+"&reportTypeInternalId="+reportTypeInternalId,
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
		var selectedId = $("#publicSecurityList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({message:"请选择要删除的台账信息!",level:"warn"});
	 		return;
		}
		var rowData=$("#publicSecurityList").getRowData(selectedId);
		if("已上报"==rowData["submitState.id"]){
			$.messageBox({message:"该台账信息已经上报,不能删除!",level:"warn"});
	 		return;
		}
		$.confirm({
			title:"确认删除",
			message:'此'+innerTitle+'信息删除后，将无法恢复,您确认删除吗?',
			width: 400,
			okFunc: function(){
				deletePublicSecurity();
			}
		});
	});
	$("#submitReport").click(function(){
		var selectedId = $("#publicSecurityList").getGridParam("selrow");
		var rowData=$("#publicSecurityList").getRowData(selectedId);
		if(selectedId==null){
			$.messageBox({message:"请选择要上报的台账信息!",level:"warn"});
	 		return;
		}
		if("已上报"==rowData["submitState.id"]){
			$.messageBox({message:"该台账信息已经上报!",level:"warn"});
	 		return;
		}
		if(!judgeReportCondition()){
			$.messageBox({message:"该月上报该信息未填写签发人!",level:"warn"});
			return;
		}
		$.confirm({
			title:"确认上报",
			message:"上报后，将无法修改,您确认上报吗?",
			width: 400,
			okFunc: function(){
			reportedInvestigation(selectedId);
			}
		});
	});
	$("#view").click(function(event){
		doubleClickTable();
	});
	$("#back").click(function(event){
		var selectedId = $("#publicSecurityList").getGridParam("selrow");
		if(selectedId == null){
			$.messageBox({message:"请选择要回退的台账信息!",level:"warn"});
			return ;
		}
		var selectedId = $("#publicSecurityList").getGridParam("selrow");
		var rowData=$("#publicSecurityList").getRowData(selectedId);
 		if(rowData["submitState.internalId"]=='<s:property value="@com.tianque.domain.property.WorkingRecordSubmitstate@HAS_SUBMIT"/>'){
 			backRemindMessage(selectedId,rowData);
	 	}else{
	 		$.messageBox({message:"该信息未上报!",level:"warn"});
			return;
		}
	});
	$("#reload").click(function(){
		<s:if test='"areaDailyYear".equals(modeType)'>
			var orgId = $("#currentOrgId").val();
		</s:if>
		<s:else>
			var orgId=<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()'/>;
		</s:else>
		$("#publicSecurityList").setGridParam({
			url:"${path}/daily/publicSecurityRenovateManage/emphasisSafetyDetailList.action",
			datatype: "json",
			page:1
		});
		$("#publicSecurityList").setPostData({
			'dailyDirectoryId':dailyId,
			'organization.id':orgId,
			'modeType':'${modeType}'
	   	});
		$("#publicSecurityList").trigger("reloadGrid");
	});
	
	function doubleClickTable(){
		var selectedId = $("#publicSecurityList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({message:"请选择要查看的台账信息!",level:"warn"});
	 		return;
		}
		$("#dailyLogMaintanceDialog").createDialog({
			width:780,
			height:630,
			title:'查看'+innerTitle+'信息',
			url:"${path}/daily/publicSecurityRenovateManage/dispatch.action?mode=view&emphasisSafetyDetail.id="+selectedId+"&dailyDirectoryId="+dailyDirectoryId+"&reportTypeInternalId="+reportTypeInternalId,
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
		$("#emphasisSafetyDetailId").val(selectedId);
		$("#downloadReport").submit();
	}
});
function reportedInvestigation(selectedId){
	$.ajax({
		url:"${path}/daily/publicSecurityRenovateManage/reportEmphasisSafetyDetail.action",
		data:{
			"emphasisSafetyDetail.id":selectedId,
			"emphasisSafetyDetail.dailyDirectory.id":dailyId
		},
		success:function(responseData){
			if (responseData.id){
			    $.messageBox({message:"已经成功上报该信息!"});
			    $("#publicSecurityList").trigger("reloadGrid");
			}else{
				if(null == responseData){
					$.messageBox({ message:"操作失败，该信息不能上报!",level:"error"});
				}else{
					$.messageBox({ message:responseData,level:"error"});
				}
			}
		}
	});
}
function judgeReportCondition(){
	var selectedId = $("#publicSecurityList").getGridParam("selrow");
	var judgeReport;
	$.ajax({
		url:"${path}/daily/publicSecurityRenovateManage/judgeReportCondition.action",
		data:{
			"emphasisSafetyDetail.id":selectedId
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
		height:270,
		title:"退回提醒",
		url:url,
		buttons :{
			"保存" : function(){
				$("#backDialog-form").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}

function print(selectedId){
	$("#dailyLogMaintanceDialog").createDialog({
		width: widthWhenAdd,
		height: heightWhenAdd,
		title: '打印'+$(innerTitle).text()+'信息',
		url:"${path}/daily/publicSecurityRenovateManage/dispatch.action?mode=print&emphasisSafetyDetail.id="+selectedId+"&dailyDirectoryId="+dailyDirectoryId+"&reportTypeInternalId="+reportTypeInternalId,
		buttons: {
			"确定" : function(){
				$("#publicSecurityRenovatePrint").printArea();
		        $(this).dialog("close");
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
	 $("#publicSecurityList>tbody>tr[id='"+rowId+"']").css('color','#f60')
	}
	globalFormatterArrary=new Array();
}
</script>