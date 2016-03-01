<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript">
function clientModeFormatter(el,options,rowData){
	if(rowData.clientMode == 1 || rowData.clientMode == '1'){
        return "手机在线";
    }
    return "pc在线";
}

$(document).ready(function(){
	$("#accountType").change(function(){
		$("#sessionList").setGridParam({
			url:"${path}/sessionManage/onlineSessions.action",
			datatype: "json",
			page:1
		});
		$("#sessionList").setPostData({
			"organization.id":getCurrentOrgId(),
			"accountType":function(){return $("#accountType").val();}
	   	});
		$("#sessionList").trigger("reloadGrid");
	});
	function selectedRow(rowid){
		var rowData=$("#sessionList").getRowData(rowid);
		if(rowData.sessionId==$.cookie('<s:property value="@com.tianque.common.util.GlobalValue@LOGIN_SESSION_ID"/>'))
			$("#sessionLogout").buttonDisable();
		else
			$("#sessionLogout").buttonEnable();
	}
	jQuery("#sessionList").jqGridFunction({
	   	url:'${path}/sessionManage/onlineSessions.action',
	   	postData: {
			"organization.id":getCurrentOrgId(),
			"accountType":function(){return $("#accountType").val();}
        },
		datatype: "json",
	   	colModel:[
	   	    {name:'id',index:'id',hidden:true, sortable:false},
	   	    {name:'sessionId',hidden:true, sortable:false},
	   		{name:'userName',label:'在线用户',width:100,index:'userName', sortable:true},
	   		{name:'clientMode',label:'登陆类型',sortable:false,formatter:clientModeFormatter,align:'center',width:100},
	   		{name:'userRealName', label:'姓名', sortable:true, width:100},
	   	 	{name:'organization.orgName',label:"所属部门",sortable:false,width:350},
	   		{name: 'lastUrl',label:'访问链接',width:280,sortable:true},
	   		{name:'accessTime',index:'accessTime',label:'访问时间',width:140,sortable:true},
	   		{name:'accessIp',label:'访问IP',sortable:true,width:130},
	   		{name:'loginIp',label:'登录IP',sortable:true,width:130},
	   	    {name:'loginDate',label:'登录时间',sortable:true,width:140}
	   	],
	   	showColModelButton:false,
	   	onSelectRow:selectedRow
	});

	$("#reload").click(function(){
		$("#sessionList").trigger("reloadGrid");
	});
	$("#sessionLogout").click(function(){
		var selectId = $("#sessionList").getGridParam("selrow");
		if(selectId == null){
			$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
		var rowData=$("#sessionList").getRowData(selectId);
		if(rowData.sessionId==$.cookie('<s:property value="@com.tianque.common.util.GlobalValue@LOGIN_SESSION_ID"/>')){
			$.messageBox({level:'warn',message:"正在登陆的用户不可注销！"});
			return;
		}
		$.confirm({
			title:"确认注销",
			message:"确定注销此用户吗?",
			okFunc: function() {
				$.ajax({
					url:'${path}/sessionManage/deleteSession.action?sessionId='+$("#sessionList").getRowData($("#sessionList").getGridParam("selrow")).sessionId,
					success:function(data){
						if(data == true){
							$("#sessionList").delRowData($("#sessionList").getGridParam("selrow"));
						    $.messageBox({message:"已经成功注销该用户!"});
						}else{
							$.messageBox({level:'error',message:"找不到要注销的用户，不能注销!"});
						}
					}
				});
			}
		});
	});

});
</script>
<div class="content">
	<div class="ui-corner-all" id="nav">
		<div class="btnbanner btnbannerData">
			<jsp:include page="/common/orgSelectedComponent.jsp"/>
		</div>
		<select class="basic-input" id="accountType" name="accountType">
			<option value="0" selected="selected">全部</option>
			<option value="1">PC</option>
			<option value="2">手机</option>
		</select>
		<pop:JugePermissionTag ename="sessionLogout">
			<s:if test="@com.tianque.core.util.ThreadVariable@getUser().admin">
				<a id="sessionLogout" href="javascript:void(0)"><span>注销</span></a>
			</s:if>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="width: 100%">
		<table id="sessionList"></table>
		<div id="sessionListPager"></div>
	</div>
</div>

