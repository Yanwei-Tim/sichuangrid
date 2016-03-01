<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="content">
	<div class="ui-corner-all" id="nav">
		<pop:JugePermissionTag ename="addDailyDirectory">
		<a id="addDailyYear" href="javascript:void(0)" ><span>新增台账目录</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteDailyDirectory">
		<a id="delete" href="javascript:void(0)" ><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateDailyDirectory">
		<a id="update" href="javascript:void(0)" ><span>修改</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewDailyDirectory">
		<a id="view" href="javascript:void(0)" ><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="startDailyDirectory">
		<a id="start" href="javascript:void(0)" ><span>启用</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="stopDailyDirectory">
		<a id="stop" href="javascript:void(0)" ><span>停用</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)" ><span>刷新</span></a>
	</div>
	<div style="width: 100%;">
		<table id="dailyYearList"></table>
		<div id="dailyYearListPager"></div>
	</div>
	<div id="dailyDialog"></div>
	<div id="nextStepDialog"></div>
	<div id="dailyDirectoryDialog"></div>
</div>

<script type="text/javascript">
function statusFormatter(el, options, rowData){
	if(rowData.status == 1){
		return '启用';
	}
	return '停用';
}
function afterLoad(){
	var ids = $("#dailyYearList").getDataIDs();
	for(var i=0;i<ids.length;i++){
		var row =  $("#dailyYearList").getRowData(ids[i]);
		if('启用'==row.status){
			$("#"+ids[i]+"").css('color','blue');
		}
	}
}
$(document).ready(function(){
	$("#dailyYearList").jqGridFunction({
		datatype: "local",
		colNames:['id','encryptId','年度','台账名称','状态'],
		colModel:[
			{name:"id",index:"id",align:'center',sortable:false,hidden:true,frozen:true},
			{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
			{name:"yearDate",align:'center',width:100,sortable:true},
			{name:"name",index:'name',align:'center',width:500,sortable:true},
			{name:"status",index:'status',align:'center',width:100,sortable:true,formatter:statusFormatter}
		],
		sortname:"status",
		loadComplete: afterLoad,
		onSelectRow: function(data){}
	});
	loadList();
	$("#addDailyYear").click(function(){
		if($("#addDailyYear").attr("disabled")=="true" || $("#addDailyYear").attr("disabled")=="disabled"){
			return;
		}
		$("#dailyTrunkDirectoryDailog").createDialog({
			width:800,
			height:590,
			title:'新增年度台帐目录',
			url:"${path}/daily/dailyYearManage/dispatchOperate.action?mode=add",
			close:function(){
				$("#dailyYearList").trigger("reloadGrid");//关闭dialog的时候调用的事件 用来 解决新增年度台帐目录 选择不起用而不刷新列表的问题
			},
			buttons :{
				"保存" : function(){
					$("#dailyYear-form").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	$("#delete").click(function(){
		var dailyYearId = $("#dailyYearList").getGridParam("selrow");
		if(dailyYearId==undefined || dailyYearId==null || dailyYearId==''){
			$.messageBox({message:"请选择所要删除的台账目录!",level:"warn"});
			return ;
		}
		var rowData = $("#dailyYearList").getRowData(dailyYearId);
		if(rowData.status == '启用'){
			$.messageBox({message:"该台账目录已经启用,请停用后再删除!",level:"warn"});
			return ;
		}
		$.ajax({
			url:'${path}/daily/dailyYearManage/validateDeleteByEncrypt.action?dailyYear.id='+rowData.encryptId,
			success:function(msg){
				if(msg == true){
					$.confirm({
						title:"确认删除",
						message:"台账目录信息删除后，将无法恢复,您确认删除该台账目录信息吗?",
						width: 400,
						okFunc: function(){deleteDailyYear(dailyYearId);}
					});
				}else{
					$.notice({level:'warn',message:msg});
				}
				return ;
			}
		});
	});
	$("#update").click(function(){
		var dailyYearId = $("#dailyYearList").getGridParam("selrow");
		if(dailyYearId==undefined || dailyYearId==null || dailyYearId==''){
			$.messageBox({message:"请选择所要修改的台账目录!",level:"warn"});
			return ;
		}
		var rowData = $("#dailyYearList").getRowData(dailyYearId);
		if(rowData.status == '启用'){
			$.messageBox({message:"该台账目录已经启用,请停用后再修改!",level:"warn"});
			return ;
		}
		$("#nextStepDialog").createDialog({
			width:800,
			height:590,
			title:'修改台账目录',
			url:"${path}/daily/dailyYearManage/dispatchOperateByEncrypt.action?mode=update&dailyYear.id="+rowData.encryptId,
			buttons :{
				"保存" : function(){
					$("#updateDailyDirectory-form").submit();
				},
				"关闭" : function(){
					$("#nextStepDialog").dialog("close");
				}
			}
		});
	});
	$("#view").click(function(){
		var dailyYearId = $("#dailyYearList").getGridParam("selrow");
		if(dailyYearId==undefined || dailyYearId==null || dailyYearId==''){
			$.messageBox({message:"请选择所要查看的台账目录!",level:"warn"});
			return ;
		}
		var rowData=  $("#dailyYearList").getRowData(dailyYearId);
		$("#nextStepDialog").createDialog({
			width:800,
			height:600,
			title:'查看台账目录',
			url:"${path}/daily/dailyYearManage/dispatchOperateByEncrypt.action?mode=view&dailyYear.id="+rowData.encryptId,
			buttons :{
				"关闭" : function(){
					$("#nextStepDialog").dialog("close");
				}
			}
		});
	});
	$("#start").click(function(){
		var dailyYearId = $("#dailyYearList").getGridParam("selrow");
		if(dailyYearId==undefined || dailyYearId==null || dailyYearId==''){
			$.messageBox({message:"请选择所要启用的台账目录!",level:"warn"});
			return ;
		}
		var rowData = $("#dailyYearList").getRowData(dailyYearId);
		if(rowData.status == '启用'){
			$.messageBox({message:"该台账目录已经启用!",level:"warn"});
			return ;
		}
		$.ajax({
			url:'${path}/daily/dailyYearManage/startDailyYearByEncrypt.action?dailyYear.id='+rowData.encryptId,
			success:function(data){
				if(parseInt(data.id)>0){
					$.messageBox({message:"该台账目录成功启用!"});
				}else{
					$.messageBox({message:msg,level:"error"});
				}
				$("#dailyYearList").trigger("reloadGrid");
				return ;
			}
		});
	});
	$("#stop").click(function(){
		var dailyYearId = $("#dailyYearList").getGridParam("selrow");
		if(dailyYearId==undefined || dailyYearId==null || dailyYearId==''){
			$.messageBox({message:"请选择所要停用的台账目录!",level:"warn"});
			return ;
		}
		var rowData = $("#dailyYearList").getRowData(dailyYearId);
		if(rowData.status == '停用'){
			$.messageBox({message:"该台账目录已经停用!",level:"warn"});
			return ;
		}
		$.ajax({
			url:'${path}/daily/dailyYearManage/stopDailyYearByEncrypt.action?dailyYear.id='+rowData.encryptId,
			success:function(data){
				if(parseInt(data.id)>0){
					$.messageBox({message:"该台账目录成功停用!"});
				}else{
					$.messageBox({message:msg,level:"error"});
				}
				$("#dailyYearList").trigger("reloadGrid");
				return ;
			}
		});
	});
	$("#reload").click(function(){
		loadList();
	});
	function loadList(){
		$("#dailyYearList").setGridParam({
			url:"${path}/daily/dailyYearManage/findDailyYearForPageByOrgId.action",
			datatype: "json",
			page:1
		});
		$("#dailyYearList").trigger("reloadGrid");
	}
	function deleteDailyYear(id){
		var rowData = $("#dailyYearList").getRowData(id);
		$.ajax({
			url:'${path}/daily/dailyYearManage/deleteDailyYearByIdByEncrypt.action?dailyYear.id='+rowData.encryptId,
			success:function(msg){
				if(msg == true){
					$.messageBox({message:"该台账目录成功删除!"});
				}else{
					$.messageBox({message:msg,level:"error"});
				}
				$("#dailyYearList").trigger("reloadGrid");
				return ;
			}
		});
	}
});

function createNextStepDialog(id,name){
	$("#nextStepDialog").createDialog({
		width:800,
		height:600,
		title:'设置'+name,
		url:"${path}/daily/dailyYearManage/dispatchOperate.action?mode=nextStep&dailyYear.id="+id,
		buttons :{
			/* "保存" : function(){
				$("#updateDailyDirectory-form").submit();
			}, */
			"关闭" : function(){
				$("#nextStepDialog").dialog("close");
			}
		}
	});
}
</script>