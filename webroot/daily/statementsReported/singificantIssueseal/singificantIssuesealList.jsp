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
			<pop:JugePermissionTag ename="backWorkingRecord">
				<a id="back" href="javascript:void(0)"><span>回退</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="upWorkingRecord">
				<a id="reported" href="javascript:void(0)"><span>上报</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="deleteWorkingRecord">
				<a id="delete" href="javascript:void(0)"><span>删除</span></a>
			</pop:JugePermissionTag>
		</s:if>
		
		<pop:JugePermissionTag ename="viewWorkingRecord,viewAreaWorkingRecord">
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchWorkingRecord,searchAreaWorkingRecord">
			<a id="filtered" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
<%-- 		<a id="print" href="javascript:void(0)"><span>打印</span></a> --%>
	</div>

	<div style="width: 100%;" id="grid_content" >
		<table id="significantIssuedealList"></table>
		<div id="significantIssuedealListPager"></div>
	</div>
</div>
<div id="significantIssuedealDialog"></div>
<div id="backsignificantIssuedealDialog"></div>

<script type="text/javascript">
var widthWhenAdd=670;
var heightWhenAdd=480;
var strJson = {};
var dailyId = '${dailyDirectoryId}';
//var globalFormatterArrary=new Array();
function deleteSignificantIssuedeal(){
	var selrowId = $("#significantIssuedealList").jqGrid("getGridParam","selrow");
	if(!selrowId){return ;};
	$.ajax({
		url:'${path}/daily/significantIssuedealManage/deleteSignificantIssuedealWorkingRecord.action?significantIssuedealids='+selrowId,
		success:function(responseData){
			if(responseData == true){
				$.messageBox({ message:"成功删除台账信息!"});
			}else{
				$.messageBox({ message:"考核评估有引用，不能删除!",level: "warn"	});
			}
			$("#significantIssuedealList").trigger("reloadGrid");
			return;
        }
	});
}

$(document).ready(function(){
	<s:if test='"areaDailyYear".equals(modeType)'>
		var _currentOrgId = $("#currentOrgId").val();
	</s:if>
	<s:else>
		var _currentOrgId = '<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"/>';
	</s:else>
	var selectedNode = $.getSelectedNode(tree);
	var gridoption = {
		url:"${path}/daily/significantIssuedealManage/significantIssuedealWorkingRecordList.action?organization.id="+_currentOrgId+"&dailyDirectory.id="+dailyId+"&modeType=${modeType}",
		datatype: "json",
		height:$("div.ui-layout-center").height()-130,
		colNames:['id',"排查部门",'状态','时间','单位地点','矛盾纠纷简况及原因',"责任单位","责任领导","调处情况",'上报时间','上报单位','单位Id','附件'],
	   	colModel:[
		   {name:"id",index:'id',hidden:true},
	       {name:"investigationOrg.orgName",index:'investigationOrgId',width:100, label:'排查部门'},
	       {name:"status",index:'status',width:50,align:"center", label:'状态',formatter:statusFormatter},
      	   {name:"investigationDate",index:'investigationDate',width:100,align:"center", label:'时间'},
      	   {name:"address",index:'address',width:100, label:'单位地点'},
      	   {name:"significantIssuedealReason",index:'significantIssuedealReason',width:160, label:'矛盾纠纷简况及原因'},
      	   {name:"accountabilityUnit",index:'accountabilityUnit',width:100, label:'责任单位'},
      	   {name:"accountabilityLeading",index:'accountabilityLeading',width:100, label:'责任领导'},
      	   {name:"remarks",index:'remarks',width:100, label:'调处情况'},
	       {name:"reportedDate",width:100,align:"center", label:'上报时间'},
      	   {name:"subOrgid",index:'subOrgid',width:120,hidden:true,hidedlg:true,label:'上报单位'},
      	   {name:"investigationOrg.id",width:100,hidden:true,hidedlg:true,label:'排查id'},
      	   {name:'disputeAttachFiles', sortable:false, width:60,align:'center',formatter:formatterDisputeAttachFile}
      	  // {name:"expiredEntering",hidden:false,index:'expiredEntering',width:100, label:'有效性',formatter:expiredEnteringFormatter}
      	],
		ondblClickRow: viewDailyLogInfo,
	//	gridComplete:afterGridComplete,
		loadComplete:function(){disputeAttachFileShow();}
	};

	$("#significantIssuedealList").jqGridFunction(gridoption);
	
	function viewDailyLogInfo(){
		var selectedId = $("#significantIssuedealList").getGridParam("selrow");
		if(null == selectedId){
			$.messageBox({message:"请选择要查看的台账信息!",level:"warn"});
			return ;
		}
		$("#dailyLogMaintanceDialog").createDialog({
			width:widthWhenAdd,
			height:heightWhenAdd,
			title:'查看'+innerTitle+'信息',
			modal : true,
			url:"${path}/daily/significantIssuedealManage/dispatch.action?mode=view&dailyDirectory.id="+dailyDirectoryId+"&significantIssuedealWorkingRecord.id="+selectedId,
			buttons :{
				"打印" : function(){
					significantIssuedealDlgPrint(selectedId);
		  	   	},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	}

	$("#add").click(function(event){
		var selectedNode = $.getSelectedNode(tree);
		if(!selectedNode.attributes.leaf){
			$.messageBox({message:"该目录不能新增台账信息，请选择底层目录新增!",level:"warn"});
			return ;
		}else{$("#dailyLogMaintanceDialog").createDialog({
			width:widthWhenAdd,
			height:heightWhenAdd,
			title:'新增'+innerTitle+'信息',
			modal : true,
			url:"${path}/daily/significantIssuedealManage/dispatch.action?mode=add&organization.id="+getCurrentOrgId()+"&dailyDirectory.id="+dailyDirectoryId,
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
		var selectedId = $("#significantIssuedealList").getGridParam("selrow");
		if(null == selectedId){
			$.messageBox({message:"请选择要修改的台账信息!",level:"warn"});
			return ;
		}
		var rowData=$("#significantIssuedealList").getRowData(selectedId);
		if("已上报" == rowData["status"]){
			$.messageBox({message:"该台账信息已经上报,不能修改!",level:"warn"});
	 		return;
		}
		$("#dailyLogMaintanceDialog").createDialog({
			width:widthWhenAdd,
			height:heightWhenAdd,
			title:'修改'+innerTitle+'信息',
			url:"${path}/daily/significantIssuedealManage/dispatch.action?mode=edit&significantIssuedealWorkingRecord.id="+selectedId+"&dailyDirectory.id="+dailyDirectoryId,
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

	$("#delete").click(function(event){
		var selectedId = $("#significantIssuedealList").getGridParam("selrow");
		if(null == selectedId){
			$.messageBox({message:"请选择要删除的台账信息!",level:"warn"});
			return ;
		}
		var rowData=$("#significantIssuedealList").getRowData(selectedId);
		if("已上报" == rowData["status"]){
			$.messageBox({message:"该台账信息已经上报,不能删除!",level:"warn"});
	 		return;
		}
		$.confirm({
			title:"确认删除",
			message:'此'+innerTitle+'信息删除后，将无法恢复,您确认删除吗?',
			width: 400,
			okFunc: function(){
				deleteSignificantIssuedeal();
			}
		});
	});

	$("#view").click(function(event){
		viewDailyLogInfo();
	});

	$("#filtered").click(function(){
		$("#dailyLogMaintanceDialog").createDialog({
			width:widthWhenAdd,
			height:heightWhenAdd,
			title:'查询'+innerTitle+'信息',
			url:'${path}/daily/significantIssuedealManage/dispatch.action?mode=search&organization.id='+_currentOrgId,
			buttons : {
				"查询" : function(){
					searchsignificantIssuedeal(_currentOrgId);
					$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});

	$("#reload").click(function(){
		$("#significantIssuedealList").setGridParam({
			url:"${path}/daily/significantIssuedealManage/significantIssuedealWorkingRecordList.action",
			datatype: "json",
			page:1
		});
		$("#significantIssuedealList").setPostData({
			'dailyDirectory.id':dailyId,
			'organization.id':_currentOrgId,
			'modeType':'${modeType}'
		});
		$("#significantIssuedealList").trigger("reloadGrid");
	});

	$("#back").click(function(event){
			backInvestigation(selectedNode);
	});

	$("#reported").click(function(event){
		var selectedId = $("#significantIssuedealList").getGridParam("selrow");
		if(null == selectedId){
			$.messageBox({message:"请选择要上报的台账信息!",level:"warn"});
			return ;
		}
		var rowData=$("#significantIssuedealList").getRowData(selectedId);
		if("已上报" == rowData["status"]){
			$.messageBox({message:"该台账信息已经上报!",level:"warn"});
	 		return;
		}
		$.confirm({
			title:"确认上报",
			message:""+innerTitle+"上报后，将无法修改,您确认上报该"+innerTitle+"吗?",
			width: 400,
			okFunc: function(){
				reportedInvestigation(selectedId);
			}
		});
	});
	
	$("#print").click(function(event){
		var selrowId = $("#significantIssuedealList").jqGrid("getGridParam","selrow");
		if(null == selrowId){
			$.messageBox({message:"请选择要打印的台账信息!",level:"warn"});
			return ;
		}
        $("#significantIssuedealDialog").createDialog({
			width:990,
			height:500,
			title:'打印'+innerTitle+'信息',
			url:"${path}/daily/significantIssuedealManage/findSingificantIssuesealsForPrint.action?organization.id="+_currentOrgId+"&significantIssuedealids="+selrowId,
			buttons : {
				"打印" : function(){
				    $("#printSignificantIssuedealList").printArea();
				    $(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	
});

function significantIssuedealDlgPrint(id){
	$("#dailyLogMaintanceDialog").createDialog({
		width:800,
		height: heightWhenAdd,
		title:'打印'+innerTitle+'信息',
		url:'${path}/daily/significantIssuedealManage/dispatch.action?mode=print&significantIssuedealWorkingRecord.id='+id,
		buttons: {
		   "打印" : function(){
			$("#singificantIssuesealDlgPrint").printArea();
        	$(this).dialog("close");
	  	   },
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function formatterDisputeAttachFile(el, options, rowData){
	if(rowData.id == 0 || rowData.significantIssuedealAttachFiles == ''
		||rowData.significantIssuedealAttachFiles==null
		||rowData.significantIssuedealAttachFiles==undefined){
		return '无';
	}
	var arr = new Array();
	for(var i=0; i<rowData.significantIssuedealAttachFiles.length; i++){
		arr[i] = ''+rowData.significantIssuedealAttachFiles[i].id+'_'+rowData.significantIssuedealAttachFiles[i].fileName
	}
	strJson[""+rowData.id+""] = arr;
	return "<div style='clear:both' align='center'><a href='javascript:;' id='dailyLog_"+rowData.id+"' style='color:#666666' class='popUpMore' dailyLogId='"+rowData.id+"' >附件>></a></div>";
}

function disputeAttachFileShow(){
	$.each($(".popUpMore"), function(i, n){
		var key = $(n).attr("dailyLogId");
		var fileDatas = strJson[key];
		if(fileDatas==null || fileDatas=='' 
			|| fileDatas == undefined || fileDatas.length<=0){
			return ;
		}
		var popMoreData = [];
		for(var j = 0; j < fileDatas.length; j++){
			var _index = fileDatas[j].indexOf('_');
			var _id = fileDatas[j].substring(0,_index);
			var _name = fileDatas[j].substring(_index+1);
			popMoreData[j]={id:_id, url:'/daily/significantIssuedealManage/downLoadAttachFile.action?disputeAttachFiles.id='+_id, text:_name,clickFun:function(){}};
		}
		$(n).popUpMore({data: popMoreData});
	});

	strJson = {};
}

function statusFormatter(el, options, rowData){
	if(rowData.status == <s:property value="@com.tianque.state.KeyAreasOfInvestigationInfoState@UNREPORTED" />){
		return "未上报";
	}else if(rowData.status == <s:property value="@com.tianque.state.KeyAreasOfInvestigationInfoState@REPORTED" />){
		return "已上报";
	}else if(rowData.status == <s:property value="@com.tianque.state.KeyAreasOfInvestigationInfoState@BACKED" />){
		return "已回退";
	}
}

function backInvestigation(selectedNode){
	var selrowId = $("#significantIssuedealList").jqGrid("getGridParam","selrow");
	if(null == selrowId){
		$.messageBox({message:"请选择要回退的台账信息!",level:"warn"});
		return ;
	}
	var rowData=$("#significantIssuedealList").getRowData(selrowId);
	if(!rowData["subOrgid"]){
		$.messageBox({message:"本部门新增的台账信息不能回退!",level:"warn"});
		return ;
	}
	if("已上报" == rowData["status"]){
		$.messageBox({message:"该台账信息已经上报,不能回退!",level:"warn"});
 		return;
	}
	var orgids=rowData["subOrgid"];
	$("#backsignificantIssuedealDialog").createDialog({
		width: 500,
		height: 330,
		title:'回退'+innerTitle+'信息',
		modal : true,
		url:'${path}/daily/significantIssuedealManage/dispatch.action?mode=back&significantIssuedealids='+selrowId+'&subOrgIds='+orgids,
		buttons: {
			"保存" : function(event){
   			 $("#backsignificantIssuedeal-form").submit();
   			},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

function reportedInvestigation(selectedId){
	$.ajax({
		url:"${path}/daily/significantIssuedealManage/reportedSignificantIssuedealById.action",
		data:{
			"significantIssuedealids":selectedId,
			"significantIssuedeal.dailyDirectory.id":dailyId
		},
		success:function(responseData){
			if (responseData != null){
			    $.messageBox({message:"成功上报该台账信息!"});
			    $("#significantIssuedealList").trigger("reloadGrid");

			}else{
				$.messageBox({ message:"该台账信息不能上报!",level: "warn"	});
			}
		}
	});
}
function searchsignificantIssuedeal(_currentOrgId){
	    var orgName=$('#orgName').val();
		var investigationMaxDate=$('#dealTo').val();
		var investigationMinDate=$('#dealFrom').val();
		var address=$('#address').val();
		var accountabilityLeading=$('#accountabilityLeading').val();
		var accountabilityUnit=$('#accountabilityUnit').val();
		var significantIssuedealReason=$('#significantIssuedealReason').val();
		var remarks=$('#remarks').val();
		var dailyDirectoryId=$('#dailyDirectoryId').val();
		$("#significantIssuedealList").setGridParam({
			url:'${path}/daily/significantIssuedealManage/searchSignificantIssuedealWorkingRecords.action',
			datatype: "json",
			mtype:"POST",
			page:1
		});
		var searchCondition={
				"organization.id":_currentOrgId,
			     "searchSignificantIssuedealWorkingRecord.dailyDirectory.id":dailyDirectoryId,
			     "searchSignificantIssuedealWorkingRecord.investigationOrg.orgName":orgName,
			     "searchSignificantIssuedealWorkingRecord.investigationMinDate":investigationMinDate,
			     "searchSignificantIssuedealWorkingRecord.investigationMaxDate":investigationMaxDate,
			     "searchSignificantIssuedealWorkingRecord.address":address,
			     "searchSignificantIssuedealWorkingRecord.significantIssuedealReason":significantIssuedealReason,
			     "searchSignificantIssuedealWorkingRecord.accountabilityUnit":accountabilityUnit,
			     "searchSignificantIssuedealWorkingRecord.accountabilityLeading":accountabilityLeading,
			     "searchSignificantIssuedealWorkingRecord.remarks":remarks,
			     "modeType":"${modeType}"
			  }
		$("#significantIssuedealList").setPostData(searchCondition);
		$("#significantIssuedealList").trigger("reloadGrid");

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
	 $("#significantIssuedealList>tbody>tr[id='"+rowId+"']").css('color','#f60')
	}
	globalFormatterArrary=null;
}
**/
</script>
