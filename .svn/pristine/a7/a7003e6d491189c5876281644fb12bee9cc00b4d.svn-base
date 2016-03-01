<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<form id="maintainForm" name="maintainForm" method="post" >
	<pop:token />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input id="visitId" name="visitHistory.visitId" type="hidden" value="${visitHistory.visitId}" />
		
	</form>
	<s:if test="!'viewList'.equals(mode)">
		<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addSuperiorVisitHistory">
			<a id="addSupervisitHistory" href="javascript:;"><span>新增</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchSuperiorVisitHistory">
	   		<a id="reloadSupervisitHistory"  href="javascript:void(0)"><span>刷新</span></a>
	   	</pop:JugePermissionTag>
		</div>
	</s:if>
	<div style="clear: both;"></div>
	<div style="width:98%;margin:0 auto;">
		<table id="supervisitHistoryList"> </table>
		<div id="supervisitHistoryListPager"></div>
	</div>
</div>

<script type="text/javascript">
<pop:formatterProperty name="visitState" domainName="@com.tianque.domain.property.PropertyTypes@SUPERIOR_VISIT_STATUS" />
var title="特殊人群上访历史";
function reloadSupervisor(){
	$("#supervisitHistoryList").setGridParam({
		url:'${path}/baseinfo/superiorVisitHistoryManage/findSuperiorVisitHistorys.action',
		datatype: "json",
		page:1
	});
	 var postData={
			"visitHistory.visitId":$("#visitId").val()
		};
	$("#supervisitHistoryList").setPostData(postData);
	$("#supervisitHistoryList").trigger("reloadGrid");
}
function operateFormatter(el,options,rowData){
	var operate = "<pop:JugePermissionTag ename='updateSuperiorVisitHistory'><a href='javascript:updateOperatorById("+rowData.id+")'><span>修改</span></a></pop:JugePermissionTag> | <pop:JugePermissionTag ename='deleteSuperiorVisitHistory'><a href='javascript:deleteOperatorById("+rowData.id+")'><span>删除</span></a></pop:JugePermissionTag>";
	if($("#mode").val()=="viewList"){
		operate = "<a href='javascript:viewSupervisor("+rowData.id+")'><span>查看</span></a>";
	}
	return operate;
}
function deleteOperatorById(rowid){
	if(rowid==null||rowid==""){
		return;
	}
	var str="确定要删除吗?";
	$.confirm({
		title:"确认删除",
		message:str,
		okFunc: function() {
			$.ajax({
				url:"${path}/baseinfo/superiorVisitHistoryManage/deleteSuperiorVisitByIds.action?locationIds="+rowid,
				success:function(data){
					$("#supervisitHistoryList").trigger("reloadGrid");
				    $.messageBox({message:"已经成功删除该"+title+"信息!"});
				}
			});
		}
	});
}
function updateOperatorById(rowid){
	if(rowid==null||rowid==""){
		return;
	}
	var objectId=$("#visitId").val();
	$("#supervisorMaintanceDialog").createDialog({
			width: 600,
			height: 430,
			title: '修改'+title,
			url:'${path}/baseinfo/superiorVisitHistoryManage/dispatchOperate.action?mode=edit&visitHistory.visitId='+objectId+'&visitHistory.id='+rowid,
			buttons: {
		   		"保存" : function(event){
					$("#maintainHisForm").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
}
function viewSupervisor(rowid) {
	$("#supervisorMaintanceDialog").createDialog({
		width: 620,
		height: 430,
		title:'查看'+title,
		url:'${path}/baseinfo/superiorVisitHistoryManage/dispatchOperate.action?mode=view&visitHistory.id='+rowid,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}
$(document).ready(function(){
	$("#supervisitHistoryList").jqGridFunction({
		datatype: "local",
		sortname:'id',
	   	colModel:[
	        {name:"id",index:"id",hidden:true,hidedlg:true},
	        {name:"operation",index:"id",label:"操作",sortable:false,formatter:operateFormatter,width:80,align:"center"},
	        {name:"visitDate",index:'visitDate',label:'上访时间',sortable:true,width:80},
	        {name:'visitState',index:'visitState',label:'上访状态',sortable:true,width:170,formatter:visitStateFormatter},
      		{name:'visitReason',label:'上访原因',sortable:false, width:200}
		],
		height:370,
	  	onSelectAll:function(aRowids,status){ },
	    loadComplete: afterLoad,
	    multiselect:true,
	    onSelectRow:selectRowData,
	    ondblClickRow:viewSupervisor,
	    rowNum:15
	});
	function selectRowData(){
		var selectedCounts = getActualjqGridMultiSelectCount("supervisitHistoryList");
		var count = $("#supervisitHistoryList").jqGrid("getGridParam","records");
		if(selectedCounts == count){
			jqGridMultiSelectState("supervisitHistoryList", true);
		}else{
			jqGridMultiSelectState("supervisitHistoryList", false);
		}
	}
	reloadSupervisor();

	function getSelectedIdLast(){
		var selectedId;
		var selectedIds = $("#supervisitHistoryList").jqGrid("getGridParam", "selarrrow");
		for(var i=0;i<selectedIds.length;i++){
			selectedId = selectedIds[i];
		}
		return selectedId;
	}
	//页面加载完成之后a,b,c
	function afterLoad(){
		checkExport();
	}

	function checkExport(){
		if($("#supervisitHistoryList").getGridParam("records")>0
				|| $("#supervisitHistoryList").getGridParam("selrow")!=null){
			$("#exportSupervisor").buttonEnable();
		}
	}
	$("#addSupervisitHistory").click(function(event){
		var objectId=$("#visitId").val();
		$("#supervisorMaintanceDialog").createDialog({
				width: 600,
				height: 430,
				title: '新增'+title,
				url:'${path}/baseinfo/superiorVisitHistoryManage/dispatchOperate.action?mode=add&visitHistory.visitId='+objectId,
				buttons: {
			   		"保存" : function(event){
						$("#maintainHisForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
	});
	

	$("#deleteSupervisor").click(function(){
		var selectedIds = $("#supervisitHistoryList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds=="" || selectedIds==null){
			$.messageBox({level:'warn',message:"没有"+title+"可以删除！"});
			return;
		}
		deleteOperator();
	});


	$("#reloadSupervisitHistory").click(function(event){
		reloadSupervisor();
	});

	$("#viewSupervisor").click(function(event){
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
	 		return;
		}
		viewSupervisor(selectedId);
	});

	
});

</script>

