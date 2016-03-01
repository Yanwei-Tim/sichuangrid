<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>	
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div style="height:100%;overflow:auto;overflow-x:hidden;position:relative;">
	<div class="content" >
		<div class="ui-corner-all" id="nav">
			<a id="add" href="javascript:;"><span>新增</span></a>
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
			<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
			
			<div style="float:right;"><input type="text" id="examineScoreRecordOrgName" width="200"/>
			<input type="hidden" name="examineScoreRecordOrgId" id="examineScoreRecordOrgId"/></div>
		</div>
		<div style="width:100%;" id="grid_content" >
			<table id="examineScoreRecordList"></table>
			<div id="examineScoreRecordListPager"></div>
		</div>
		<div id="examineScoreRecordMaintanceDialog"></div>
		<div id="noticeDialog"></div>
	</div>
</div>


<script type="text/javascript">
var dialogWidth = 890;
var dialogHeight = 600;

$(document).ready(function(){
	$("#examineScoreRecordOrgName").treeSelect({
		inputName:"examineScoreRecordOrgId",
		onSelect:reloadExamineScoreRecordList
	});
	
	$("#examineScoreRecordList").jqGridFunction({
		datatype: "local",
		colNames:['id','年度','考核部门','年度总分',"年度得分",'适用的年度评分计划'],
		
	   	colModel:[
	        {name:"id",index:"id",hidden:true},
	        {name:'year',sortable:false,width:200},
	        {name:'org.orgName',sortable:false,width:250},
	  		{name:"annualMaxScore",sortable:false,width:100},
	  		{name:"annualActualScore",sortable:false,width:100},
	        {name:'plan.title',sortable:false,width:400}
		],
		scrollrow:true,
		loadComplete: afterLoad,
		ondblClickRow: viewExamineScoreRecordInfo,
		onSelectRow:function(){setExamineScoreRecordButton();}
	});

	$("#add").click(function(){
		var selectedId = $("#examineScoreRecordOrgId").val();
		if(selectedId==null){
	 		return;
		}
		$("#examineScoreRecordMaintanceDialog").createDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:'新增评分记录',
			url:"${path}/examine/examineScoreRecordManage/dispatch.action?mode=add&examineScores.org.id="+selectedId,
			buttons :{
				"保存" : function(){
					$("#maintainForm").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});

	$("#view").click(function(event){
		var selectedId = $("#examineScoreRecordList").getGridParam("selrow");
		if(selectedId==null){
	 		return;
		}
		viewExamineScoreRecordInfo(selectedId);
	});

	$("#reload").click(function(event){
		reloadExamineScoreRecordList();
	});
});

function afterLoad(){
	setExamineScoreRecordButton();
}

function setExamineScoreRecordButton(){		
	if($("#examineScoreRecordList").getSelectedRowId()){
		$("#update").buttonEnable();
		$("#view").buttonEnable();
		$("#delete").buttonEnable();
	}else{
		$("#update").buttonDisable();
		$("#view").buttonDisable();
		$("#delete").buttonDisable();
	}
}


function reloadExamineScoreRecordList(){
	var orgId=$("#examineScoreRecordOrgId").val();
	if(orgId==null||orgId=='')
		return ;
	$("#examineScoreRecordList").setGridParam({
		url:"${path}/examine/examineScoreRecordManage/findExamineScoresList.action",
		datatype: "json",
		page:1
	});
	
	$("#examineScoreRecordList").setPostData({
		"examineScores.org.id":function(){
			return orgId;
		}
	});
	
	$("#examineScoreRecordList").trigger("reloadGrid");
}

function viewExamineScoreRecordInfo(rowid){
	if(rowid==null){
 		return;
	}
	var examineScoreRecord =  $("#examineScoreRecordList").getRowData(rowid);
	$("#examineScoreRecordMaintanceDialog").createDialog({
		width:dialogWidth,
		height:dialogHeight,
		title:'查看工作台帐信息',
		modal : true,
		url:"${path}/examine/examineScoreRecordManage/dispatch.action?mode=view&examineScores.id="+examineScoreRecord.id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}

</script>

