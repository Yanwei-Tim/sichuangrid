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
			<pop:JugePermissionTag ename="upWorkingRecord">
				<a id="submitReport" href="javascript:void(0)"><span>上报</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="deleteWorkingRecord">
				<a id="delete" href="javascript:void(0)"><span>删除</span></a>
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
		<pop:JugePermissionTag ename="searchWorkingRecord,searchAreaWorkingRecord">
<!--			<a id="search" href="javascript:void(0)"><span>查询</span></a>-->
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	</div>

	<div style="width: 100%;" id="grid_content" >
		<table id="socialConflictList"></table>
		<div id="socialConflictListPager"></div>
	</div>
</div>

<input id="orgId" type="hidden" value="${organization.id}" />
<input id="orgCode" type="hidden" value="${organization.orgInternalCode}" />

<form id="downloadReport" action="${path}/daily/socialConflictReordManage/downloadSocialConflict.action" method="post">
	<input type="hidden" name="disputEsexamine.id" id="disputEsexamineId" />
</form>

<script type="text/javascript">
<pop:formatterProperty name="submitState" domainName="@com.tianque.domain.property.PropertyTypes@WORKING_RECORD_SUBMITSTATE" />
var widthWhenAdd=880;
var heightWhenAdd=600;
var orgLevelForDailyYearTree = $("#orgLevelForDailyYearTree").val();
var dailyId = '${dailyDirectoryId}';
var globalFormatterArrary=new Array();
switch(orgLevelForDailyYearTree){
	case '10':
		widthWhenAdd = 800;
		break;
	case '20':
		widthWhenAdd = 820;
		break;
	case '30':
		widthWhenAdd = 980;
		break;
	case '40':
		widthWhenAdd = 1010;
		break;
	case '50':
		widthWhenAdd = 1010;
		break;
	case '60':
		widthWhenAdd = 1000;
		break;
}

function formatterAttachFile(el,options,rowData){
	if(rowData.dailyLogAttachFile.length>0){
		return "<div style='clear:both' align='center'><a href='javascript:;' id='dailyLog_"+rowData.id+"' style='color:#666666' class='popUpMore' dailyLogId='"+rowData.id+"' >附件>></a></div>";
	}
	return "";
}

function deleteDailyLog(){
	var selectedId = $("#socialConflictList").getGridParam("selrow");
	$.ajax({
		url:'${path}/daily/socialConflictReordManage/deleteDisputEsexamine.action?disputEsexamine.id='+selectedId,
		success:function(data){
			if(data == true){
				$("#socialConflictList").delRowData(selectedId);
				$.messageBox({ message:"成功删除矛盾纠纷!"});
				return;
			}else{
				$.messageBox({ message:"考核评估已参考此项，请勿删除！",level: "warn"});
			}
        }
	});
}

function filterDailyLog(){
	var dealFrom =$("#seachReportTimeStart").val();
	var dealTo=$("#seachReportTimeEnd").val();
	if(parseInt(dealFrom)>parseInt(dealTo)){
		$.messageBox({message:"起始日期不能大于结束日期!!",level:"warn"});
		return;
	}
	var postDataFilter = "searchDisputEsexamine.seachReportTimeStart="+dealFrom+"&searchDisputEsexamine.seachReportTimeEnd="+dealTo+
			"&searchDisputEsexamine.dailyDirectoryId="+ dailyDirectoryId +
			"&searchDisputEsexamine.orgId=" + $("#currentOrgIdForWr").val();
	$("#socialConflictList").setGridParam({
		url:'${path}/workingRecord/searchDisputEsexamine/searchDisputEsexamine.action',
		postData: postDataFilter
	});
	$("#socialConflictList").trigger("reloadGrid");
	$("#dailyLogMaintanceDialog").dialog("close");
}

$(document).ready(function(){
	var _orgId = "<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()'/>";
	var _currentOrgId = $("#currentOrgId").val();
	var _parentOrg = $("#childFirstParentOrg").val();
	if(parseInt(_orgId) != parseInt(_parentOrg)){
		$("#back").hide();
	}
	var selectedNode = $.getSelectedNode(tree);
	var gridoption = {
		<s:if test='"areaDailyYear".equals(modeType)'>
			url:"${path}/daily/socialConflictReordManage/findSocialConflictRecords.action?organization.id="+_currentOrgId+"&dailyDirectory.id="+dailyId+"&modeType=${modeType}",
		</s:if>
		<s:else>
			url:"${path}/daily/socialConflictReordManage/findSocialConflictRecords.action?organization.id="+_orgId+"&dailyDirectory.id="+dailyId+"&modeType=${modeType}",
		</s:else>
		datatype: "json",
		height:$("div.ui-layout-center").height()-130,
		colNames:['id','报表名称',"上报状态",'上报时间','制表单位',"制表人","签发人",'建表时间','上报时段',"描述","报表时间","报表类型","台账类型","","有效性"],
	   	colModel:[
	        {name:"id",index:"id",hidden:true},
	        {name:'name',sortable:false,width:300},
	  		{name:'submitState.id',sortable:false,width:100,align:"center",formatter:submitStateFormatter},
	  		{name:"submitTime",sortable:false,align:"center",width:150},
	  		{name:"organization.orgName",sortable:false,width:200},
	  		{name:"lister",sortable:false,width:100},
	        {name:'dealPerson',sortable:false,width:100},
	        {name:'createDate',sortable:false,align:"center",width:150},
	        {name:'reportTime',align:"center",hidden:true},
	        {name:"remark",hidden:true},
	        {name:'repdate',hidden:true},
	        {name:'issueLevel',hidden:true},
	        {name:'dailyDirectoryId',hidden:true},
	        {name:"submitState.internalId",sortable:false,hidden:true,hidedlg:true},
	        {name:'expiredEntering',hidden:false,sortable:false,width:150,formatter:expiredEnteringFormatter}
		],
		scrollrow:true,
		showColModelButton:true,
		viewrecords: true,
		ondblClickRow: doubleClickTable,
		gridComplete:afterGridComplete
	};

	$("#socialConflictList").jqGridFunction(gridoption);

	function doubleClickTable(){
		var selectedId = $("#socialConflictList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({message:"请选择要查看的台账信息!",level:"warn"});
	 		return;
		}
		var rowData=$("#socialConflictList").getRowData(selectedId);
		$("#dailyLogMaintanceDialog").createDialog({
			width:widthWhenAdd,
			height:heightWhenAdd+20,
			title:'查看'+innerTitle+'信息',
			modal : true,
			url:"${path}/daily/socialConflictReordManage/dispatch.action?reportTypeInternalId="+reportTypeInternalId+"&mode=view&disputEsexamine.id="+selectedId+"&organization.id="+getCurrentOrgId(),
			buttons :{
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
			},
			shouldEmptyHtml:false
		});
		}
	function print(selectedId){
		$("#dailyLogMaintanceDialog").createDialog({
			width: widthWhenAdd,
			height: heightWhenAdd,
			title: '打印'+innerTitle+'信息',
			url:"${path}/daily/socialConflictReordManage/dispatch.action?reportTypeInternalId="+reportTypeInternalId+"&mode=print&disputEsexamine.id="+selectedId+"&organization.id="+_currentOrgId,
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
		if(!selectedNode.attributes.leaf){
			$.messageBox({message:"该目录不能新增台账信息，请选择底层目录新增!",level:"warn"});
			return ;
		}
		var levelId = '<s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>';
		var uesrlevelId = '${organization.orgLevel.internalId}';
		if(parseInt(uesrlevelId) == levelId){
			$.messageBox({message:"网格级帐号不能新增矛盾纠纷排查台账信息!",level:"warn"});
			return ;
		}else{
			$("#dailyLogMaintanceDialog").createDialog({
				width:widthWhenAdd,
				height:heightWhenAdd+20,
				title:'新增'+innerTitle+'信息',
				modal : true,
				url:"${path}/daily/socialConflictReordManage/dispatch.action?reportTypeInternalId="+reportTypeInternalId+"&mode=add&dailyDirectory.id="+dailyDirectoryId,
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
		var selectedId = $("#socialConflictList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({message:"请选择要修改的台账信息!",level:"warn"});
	 		return;
		}
		var rowData=$("#socialConflictList").getRowData(selectedId);
		if("已上报"==rowData["submitState.id"]){
			$.messageBox({message:"该台账信息已经上报,不能修改!",level:"warn"});
	 		return;
		}
		$("#dailyLogMaintanceDialog").createDialog({
			width:widthWhenAdd,
			height:heightWhenAdd+20,
			title:'修改'+innerTitle+'信息',
			url:"${path}/daily/socialConflictReordManage/dispatch.action?reportTypeInternalId="+reportTypeInternalId+"&mode=edit&disputEsexamine.id="+selectedId,
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
		var selectedId = $("#socialConflictList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({message:"请选择要删除的台账信息!",level:"warn"});
	 		return;
		}
		var rowData=$("#socialConflictList").getRowData(selectedId);
		if("已上报"==rowData["submitState.id"]){
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

	$("#submitReport").click(function(){
		var selectedId = $("#socialConflictList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({message:"请选择要上报的台账信息!",level:"warn"});
	 		return;
		}
		var rowData=$("#socialConflictList").getRowData(selectedId);
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
			reportedInvestigation();
			}
		});
	});

	$("#back").click(function(event){
		var selectedId = $("#socialConflictList").getGridParam("selrow");
		if(selectedId==null){
			$.messageBox({message:"请选择要回退的台账信息!",level:"warn"});
	 		return;
		}
		var rowData=$("#socialConflictList").getRowData(selectedId);
		if(selectedId!=null){
	 		if(rowData["submitState.internalId"]=='<s:property value="@com.tianque.domain.property.WorkingRecordSubmitstate@HAS_SUBMIT"/>'){
	 			backRemindMessage(selectedId,rowData);
		 	}else{
		 		$.messageBox({message:"该信息未上报!",level:"warn"});
				return;
			}
		}
	});

	$("#view").click(function(event){
		doubleClickTable();
	});

	$("#search").click(function(){
		$("#dailyLogMaintanceDialog").createDialog({
			width:450,
			height:180,
			title:'查询'+innerTitle+'信息',
			url:"${path}/workingRecord/filterReportWorkingRecord.jsp?reportTypeInternalId="+reportTypeInternalId,
			buttons : {
				"查询" : function(){
					filterDailyLog();
					$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});

	$("#reload").click(function(){
		<s:if test='"areaDailyYear".equals(modeType)'>
			var orgId = $("#currentOrgId").val();
		</s:if>
		<s:else>
			var orgId=<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()'/>;
		</s:else>
		$("#socialConflictList").setGridParam({
			url:"${path}/daily/socialConflictReordManage/findSocialConflictRecords.action",
			datatype: "json",
			page:1
		});
		$("#socialConflictList").setPostData({
			'dailyDirectory.id':dailyId,
			'organization.id':orgId,
			'modeType':'${modeType}'
		});
		$("#socialConflictList").trigger("reloadGrid");
		
	});
	
});
function reportedInvestigation(){
	var selectedId = $("#socialConflictList").getGridParam("selrow");
	$.ajax({
		url:"${path}/daily/socialConflictReordManage/reportDisputEsexamine.action",
		data:{
			"disputEsexamine.id":selectedId,
			"disputEsexamine.dailyDirectory.id":dailyId
		},
		success:function(responseData){
			if (responseData.id){
			    $.messageBox({message:"已经成功上报该信息!"});
			    $("#socialConflictList").trigger("reloadGrid");
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
	var selectedId = $("#socialConflictList").getGridParam("selrow");
	var judgeReport;
	$.ajax({
		url:"${path}/daily/socialConflictReordManage/judgeReportCondition.action",
		data:{
			"disputEsexamine.id":selectedId
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
			encodeURIComponent(rowData.name+"退回提醒！")+"&mode=society";
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
function exportData(selectedId){
	$("#disputEsexamineId").val(selectedId);
	$("#downloadReport").submit();
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
	 $("#socialConflictList>tbody>tr[id='"+rowId+"']").css('color','#f60')
	}
	globalFormatterArrary=new Array();
}
</script>


