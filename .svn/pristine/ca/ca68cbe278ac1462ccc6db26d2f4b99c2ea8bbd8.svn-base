<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="ui-corner-all" id="nav">
    <div class="btnbanner btnbannerData">
			<select id="isLock" name="" style="height:25px;width:100px">
				<option value="true">受点评的日志</option>
				<option value="false" selected="selected">全部</option>
			</select>
			<input type="text"
				value="请输入标题" name="searchText" id="searchText" maxlength="18"
				class="searchtxt"
				onblur="value=(this.value=='')?'请输入标题':this.value;"
				onfocus="value=(this.value=='请输入标题')?'':this.value;" />
			<button id="refreshSearchKey_myLog" class="ui-icon ui-icon-refresh searchbtnico"
			style="border: 0; background-color: transparent; position: absolute; top: 10px; left: 310px; cursor: pointer; outline: none;"></button>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="searchMyPeopleLog">
		<a id="search" href="javascript:void(0)"><span><strong class="ui-ico-cx"></strong>高级搜索</span></a>
		</pop:JugePermissionTag>
	</div>
	<span class="lineBetween"></span>
	<pop:JugePermissionTag ename="addMyPeopleLog">
	<a id="add" href="javascript:void(0)"><span><strong class="ui-ico-xz"></strong>新增</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="deleteMyPeopleLog">
	<a id="delete" href="javascript:void(0)"><span><strong class="ui-ico-sc"></strong>批量删除</span></a>
	</pop:JugePermissionTag>
	<a id="reload" href="javascript:void(0)"><span><strong class="ui-ico-refresh"></strong>刷新</span></a>
</div>
<div id="statistics" style="line-height:12px;margin:6px;">
	<span style="">今日新增日志：<span style="color:red" id="todayNum">${statistics.todayNum}</span>项</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<span style="">本周新增日志：<span style="color:red" id="weekNum">${statistics.weekNum}</span>项</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<span>本月新增日志：<span style="color:red" id="mouthNum">${statistics.mouthNum}</span>项</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<span style="">本月点评日志：<span style="color:red" id="mouthCommentNum">${statistics.mouthCommentNum}</span>项</span>&nbsp;&nbsp;&nbsp;
</div>
<div style="clear: both;"></div>
<div style="width: 100%;">
	<table id="peopleLogList" style="width: 100%;">
	</table>
	<div id="peopleLogListPager"></div>
</div>
<div id="peopleLogDialog"></div>
<div id="logViewDialog"></div>
<div id="updateDialog"></div>
<div id="subDialog"></div>
<div id="searchDialog"></div>

<div style="display: none;">
	<span id="title">我的日志</span>
</div>
<script type="text/javascript">
var dialogWidth=700;
var dialogHeight=550;
var title=$("#title").html().trim();
<pop:formatterProperty name="logType" domainName="@com.tianque.domain.property.PropertyTypes@PEOPLELOG_LOGTYPE" />

function formatterAttachFile(el,options,rowData){
	if(rowData.peopleLogFiles.length>0){
		$("#peopleLogList").data(String(rowData.id), rowData.peopleLogFiles);
		return "<div style='clear:both' align='center'><a href='javascript:;' id='peopleLog_"+rowData.id+"' style='color:#666666' class='popUpMore' peopleLogId='"+rowData.id+"' >附件>></a></div>";
	}
	return "";
}
function statistics(){
	$.ajax({
		async: false,
		url: "${path}/peopleLog/commentLogManage/getStatisticsByUserId.action",
		success:function(data){
			$("#todayNum").html(data.todayNum);
			$("#weekNum").html(data.weekNum);
			$("#mouthNum").html(data.mouthNum);
			$("#mouthCommentNum").html(data.mouthCommentNum);
		}
	});
}
function updateMyPeopleLog(event,id){
	var ent =  $("#peopleLogList").getRowData(id);
	if(ent.commentNums!=0){
		 $.messageBox({message:"该日志已经点评，不能修改!",level:"warn"});
		 return;
	}
	
	$("#updateDialog").createDialog({
				width:dialogWidth,
				height:dialogHeight,
				title:'修改'+title+'信息',
				url:'${path}/peopleLog/peopleLogManage/dispatchByEncrypt.action?mode=edit&logId='+ent.encryptId,
				buttons: {
				"保存" : function(event){
		   			$("#isSubmit").val("true");
		   			$("#maintainForm").submit();
	   				},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		        	document.title = $("#myPeopleLogManagement").text();
		   		}
			}
		});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
function deleteMyPeopleLog(event,id){
	var ent =  $("#peopleLogList").getRowData(id);
	var str="确定要删除吗?一经删除将无法恢复";
	$.confirm({
		title:"确认删除",
		message:str,
		okFunc: function() {
			$.ajax({
				url:"${path}/peopleLog/peopleLogManage/deletePeopleLogByEncrypt.action",
				type:"post",
				data:{
					"logIds":ent.encryptId+""
				},
				success:function(data){
				    $.messageBox({message:"已经成功删除该民情日志信息!"});
				    	$("#peopleLogList").trigger("reloadGrid");
				    	statistics();
				}
			});

		}
	});
	var evt = event || window.event;
	if (window.event) { 
	evt.cancelBubble=true; 
	} else { 
	evt.stopPropagation(); 
	} 
}
function viewDialog(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#peopleLogList").getRowData(selectedId);
	var id = rowData.id;
	if(id==null){
		 return;
	}
	$("#logViewDialog").createDialog({
		width:dialogWidth,
		height:450,
		title:"查看日志信息",
		url:"${path}/peopleLog/commentLogManage/dispatchOperateByEncrypt.action?mode=view&peopleLog.id="+rowData.encryptId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
}
function viewMyPeopleLog(id){
	viewDialog(id);
}
$(function(){
	$("#peopleLogList").jqGridFunction({
		url:"${path}/peopleLog/peopleLogManage/peopleLogList.action",
		datatype: "json",
		page:1,
		postData:{
			"isComment":$("#isLock").val()
		},
		mtype:'post',
		colModel: [
			{name:"id",index:"id",sortable:false,hidden:true,frozen:true},
			{name:"encryptId",index:"encryptId",sortable:false,hidedlg:true,frozen:true,hidden:true},
			{name:"userId",index:"userId",hidden:true,sortable:true,hidedlg:true},
			{name:"name", index:"operate",label:"操作",align:'center',width:65,formatter:operateFormatter,sortable:true,frozen : true},
			{name:"title",index:"title",label:'标题',formatter:titleFont,sortable:true,width:250},
			{name:"publishDate",label:'时间',align:'center',sortable:true,width:120,formatter:'date',formatoptions:{newformat: 'Y-m-d'}},
			{name:"logType",index:'logType',label:'日志种类',width:80,hidden:true,sortable:true,formatter:logTypeFormatter},
			{name:"belonger",index:'belonger',sortable:true,label:'所属人',width:120},
			{name:"organization.orgName",index:'organization.orgName',label:'所属区域',sortable:false,width:150},
			{name:"createDate",label:'创建时间',hidden:true,align:'center',width:120,formatter:'date',sortable:true,formatoptions:{newformat: 'Y-m-d'}},
			{name:"address",index:'address',sortable:true,label:'地址',hidden:true,width:150},
			{name:"commentNums",label:'点评次数',sortable:true,align:'center',width:75},
			{name:'attachments',label:'附件',sortable:true, width:200, formatter:formatterAttachFile}
		],
	  	multiselect:true,
	  	<pop:JugePermissionTag ename='viewMyPeopleLog'>
			ondblClickRow: doubleClickTable,
		</pop:JugePermissionTag>
	  	loadComplete: afterLoad
	});
	$("#peopleLogList").trigger("reloadGrid");
	statistics();
	$("#isLock").change(function(){
		getList();
	});
	$("#refreshSearchKey_myLog").click(function(){
		$("#searchText").attr("value","请输入标题");
	});
	$("#fastSearchButton").click(function(){
		var condition = $("#searchText").val();
		if(condition == '请输入标题') {
			return;
		}
		var	postData = {
			"searchPeopleLogVo.title":condition,"isComment":$("#isLock").val()
		}
		$("#peopleLogList").setGridParam({
			url:"${path}/peopleLog/searchPeopleLog/findPeopleLogByQueryCondition.action",
			datatype: "json",
			page:1
		});
		$("#peopleLogList").setPostData(postData);
		$("#peopleLogList").trigger("reloadGrid");
	});
	$("#search").click(function(event){
		$("#searchDialog").createDialog({
			width:650,
			height:300,
			title:'我的日志查询-请输入查询条件',
			url:'${path}/peopleLog/peopleLogManage/dispatch.action?mode=search',
			buttons: {
				"查询" : function(event){
					searchPeopleLog();
					$(this).dialog("close");
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
	$("#add").click(function(event){
		$("#peopleLogDialog").createDialog({
			width:dialogWidth,
			height:dialogHeight,
			title:'新增'+title+'信息',
			url:'${path}/peopleLog/peopleLogManage/dispatch.action?mode=add',
			buttons: {
				"保存并关闭" : function(event){
					$("#isSubmit").val("true");
					$("#maintainForm").submit();
					},
				"保存并继续" : function(event){
					$("#maintainForm").submit();
					$("#isSubmit").val("false");
				},
				"关闭" : function(){
					$(this).dialog("close");
					document.title = $("#myPeopleLogManagement").text();
				}
			}
		});
	});
	$("#delete").click(function(){
		var rowIds = $("#peopleLogList").jqGrid("getGridParam", "selarrrow");
		if(rowIds.length ==0){
			$.messageBox({level:"warn",message:'没有选中任何记录，可供删除！'});
			return;
		}
		var str="确定要删除吗?一经删除将无法恢复";
		var allValue=deleteOperatorByEncrypt('peopleLog',rowIds,'encryptId');
		$.confirm({
			title:"确认删除",
			message:str,
			okFunc: function() {
				$.ajax({
					url:"${path}/peopleLog/peopleLogManage/deletePeopleLogByEncrypt.action",
					type:'post',
					data:{
						'logIds':allValue
						},
					success:function(data){
						$.messageBox({message:"已经成功删除该民情日志信息!"});
							$("#peopleLogList").trigger("reloadGrid");
							statistics();
					}
				});
			}
		});
	});
	$("#reload").click(function(){
		$("#searchText").attr("value","请输入标题");
		getList();
		statistics();
	});
	function operateFormatter(el, options, rowData){
		return "<pop:JugePermissionTag ename='updateMyPeopleLog'><a href='javascript:;' onclick='updateMyPeopleLog(event,"+rowData.id+");'><span>修改</span></a> | </pop:JugePermissionTag><pop:JugePermissionTag ename='deleteMyPeopleLog'><a href='javascript:;' onclick='deleteMyPeopleLog(event,"+rowData.id+");'><span>删除</span></a></pop:JugePermissionTag>";
	}
	function titleFont(el,options,rowData){
		return "<pop:JugePermissionTag ename='viewMyPeopleLog'><a href='javascript:viewMyPeopleLog("+rowData.id+")'>"+rowData.title+"</a></pop:JugePermissionTag>";
	}
	function getList(){
		$("#peopleLogList").setGridParam({
			url:"${path}/peopleLog/peopleLogManage/peopleLogList.action",
			datatype: "json",
			page:1
		});
		
		$("#peopleLogList").setPostData({"isComment":$("#isLock").val()});
		
		$("#peopleLogList").trigger("reloadGrid");
	}
	function afterLoad(){
		loadFiles();
	}
	function doubleClickTable(selectedId){
		var rowData = $("#peopleLogList").getRowData(selectedId);
		var id = rowData.id;
		if(id==null){
			 return;
		}
		$("#logViewDialog").createDialog({
			width:dialogWidth,
			height:450,
			title:"查看日志信息",
			url:"${path}/peopleLog/commentLogManage/dispatchOperateByEncrypt.action?mode=view&peopleLog.id="+rowData.encryptId,
			buttons: {
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
	}
	function searchPeopleLog(){
		$("#peopleLogList").setGridParam({
			url:"${path}/peopleLog/searchPeopleLog/findPeopleLogByQueryCondition.action",
			datatype: "json",
			page:1,
			mtype:"post"
		});
		var postData=$.extend({"isComment":$('#isLock').val()},$("#searchPeopleLogForm").serializeObject());
		$("#peopleLogList").setPostData(postData);
	    $("#peopleLogList").trigger("reloadGrid");
	}
	function loadFiles() {
		$.each($(".popUpMore"), function(i, n){
			$.ajax({
				url:"${path}/peopleLog/peopleLogManage/getPeopleLogById.action?peopleLog.id="+$(n).attr("peopleLogId"),
				success:function(data){
					var popMoreData = [];
					if(data != null){
						for(var j = 0; j < data.peopleLogFiles.length; j++){
							popMoreData[j]={
								id:data.peopleLogFiles[j].id, 
								url:'${path}/peopleLog/peopleLogManage/downloadPeopleLogAttachFile.action?peopleLogAttachFile.id='+data.peopleLogFiles[j].id, 
								text:data.peopleLogFiles[j].fileName,
								clickFun:function(){}
							};
						}
					}
					$(n).popUpMore({data: popMoreData});
				}
			});
		});
	}
});
</script>