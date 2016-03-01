<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript" src="/resource/external/jPlayer/js/jquery.jplayer.min.js"></script>
<style>
.jp-jplayer{width:0 !important;height:0 !important;}
.jp-audio{background:url(/resource/system/images/jplayer/bg.png) no-repeat;overflow:hidden;width:90px;height:29px;line-height:27px;padding-left:15px;margin: 3px auto 0;}
.jp-audio a{float: left;top: 6px;position: relative;}
.jp-pause{display:none;}
.jp-time{float:right;color:#333;}
.jp-current-time{display:none;}
</style>
<div class="content" >
	<div class="ui-corner-all contentNav" id="nav">
		<div class="btnbanner btnbannerData">
			<s:include value="/common/orgSelectedComponent.jsp"/>
			<div class="ui-widget autosearch">
				<input class="basic-input" type="text" id="searchByCondition" class="searchtxt" placeholder='请输入粉丝昵称'/>
				<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
			</div>
		</div>
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="searchEvaluationIssueHandle">
			<a id="search" href="javascript:void(0)"><span>高级搜索</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<div id="jquery_jplayer_1" class="jp-jplayer"></div>
		<table id="evaluationIssueHandleList"> </table>
		<div id="evaluationIssueHandleListPager"></div>
	</div>
	<div id="evaluationIssueHandleDialog"></div>
	<div id="issueDialog"></div>
	<div id="replyMessageDlg"></div>
	<div id="div1"></div>
	<div id="div2"></div>
	<div id="fanManagementDlg"></div>
</div>
<script type="text/javascript">

$(document).ready(function(){
	
		$("#evaluationIssueHandleList").jqGridFunction({
			datatype: "local",
			sortname:"createDate",
			multiselect:true,
		   	colModel:[  	          
		   	    {name:'id',index:'id',hidden:true,frozen:true,sortable:false},
		   	    {name:'toUserName',index:'toUserName',label:'微信公众号（隐藏）',frozen:true,hidden:true,sortable:false},
		   	    {name:'openId',index:'openId',label:'粉丝唯一标识',hidden:true,sortable:true,width:100,align:'center'},
		   	    //{name:'groupName',index:'group_Name',label:'所属群组',sortable:false,width:50,align:'center'},
		   	    {name:'scorePerson',index:'scorePerson',label:'评分人(粉丝昵称)',sortable:false,width:60,align:'center'},
		   	    {name:'serialNumber',index:'serialNumber',label:'事件服务单号',sortable:false,width:60,align:'center'},
		   	    {name:'issueName',index:'issueName',label:'事件事件名称',sortable:false,width:60,align:'center'},
		   	    {name:'scoreStarNumber',index:'scoreStarNumber',label:'满意度',sortable:false,width:60,align:'center'},
		   	    {name:'content',index:'content',label:'点评内容',sortable:false,width:60,align:'center'},	   	    
		   	    {name:'closeCaseDate',index:'closeCaseDate',label:'结案时间',sortable:false,width:60,align:'center'},
		   	    {name:'createDate',index:'createDate',label:'创建时间',sortable:true,width:60,align:'center'}   	  
			],
			shrinkToFit:true
		});

	evaluationIssueHandleList({
		"evaluationIssueHandle.org.id" :getCurrentOrgId()
	});
	// 刷新
	$("#reload").click(function() {
		evaluationIssueHandleList({
			"evaluationIssueHandle.org.id" :getCurrentOrgId()
		});
	});

	
});

$("#refreshSearchKey").click(function(){
	$("#searchByCondition").attr("value","请输入粉丝昵称");
	evaluationIssueHandleList({
		"evaluationIssueHandle.org.id" :getCurrentOrgId()
	});
});

//搜索
 $("#fastSearchButton").click(function(){
	 var scorePerson=$("#searchByCondition").val();
	 if(scorePerson!=null&&"请输入粉丝昵称"!=scorePerson){
		 var queryObj = {
			"evaluationIssueHandle.org.id" : getCurrentOrgId(),
			"evaluationIssueHandle.scorePerson" : scorePerson
		};
		 evaluationIssueHandleList(queryObj);
	}
});

 //高级搜索
 $("#search").click(function(event){
	$("#evaluationIssueHandleDialog").createDialog({
			width:550,
			height:350,
			title:"消息管理查询-请输入查询条件",
			url:'${path}/evaluationIssueHandleManage/dispatch.action?mode=search&evaluationIssueHandle.org.id='+getCurrentOrgId(),
			buttons:{
				"查询" : function(event) {
					searchEvaluationIssueHandle();
					$(this).dialog("close");
				},
				"关闭" : function() {
					$(this).dialog("close");
				}
			}
	});
 });
 
function searchEvaluationIssueHandle(){
	var queryObj = {
		"evaluationIssueHandle.org.id" : getCurrentOrgId(),
		"evaluationIssueHandle.scorePerson" : $("#scorePerson").val(),
		"evaluationIssueHandle.startCloseCaseDate" : $("#startCloseCaseDate").val(),
		"evaluationIssueHandle.endCloseCaseDate" : $("#endCloseCaseDate").val(),
		"evaluationIssueHandle.serialNumber"    :$("#serialNumber").val(),	
		"evaluationIssueHandle.issueName"    :$("#issueName").val()
		};
	//alert($("#dealState").val());
	evaluationIssueHandleList(queryObj);
 }
function getSelectedIds(){
		var selectedIds = $("#evaluationIssueHandleList").jqGrid("getGridParam", "selarrrow");
		return selectedIds;
}


//列表显示
function evaluationIssueHandleList(obj){
	 $("#evaluationIssueHandleList").setGridParam({
		url:'${path}/evaluationIssueHandleManage/findEvaluationIssueHandles.action',
		datatype: "json",
		page:1,
		mytype:"post"
	});
	$("#evaluationIssueHandleList").setPostData(obj);
	$("#evaluationIssueHandleList").trigger("reloadGrid");  
}


</script>


