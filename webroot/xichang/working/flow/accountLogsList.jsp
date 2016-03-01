<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div style="width: 100%;">
		<table id="accountLogsList"></table>
		<div id="accountLogsListPager"></div>
	</div>
</div>
<div id="accountLogsDialog"></div>
<script type="text/javascript">
function loadAccountLogsData() {
	$("#accountLogsList").setGridParam({
		url:"${path}/account/accountLogsManage/findAccountLogsForPageByAccountId.action",
		datatype: "json",
		page:1
	});
	$("#accountLogsList").setPostData({
		'accountLogs.accountId':'${param.accountId}',
		'accountType':'${param.accountType}'
	});
	$("#accountLogsList").trigger("reloadGrid");
}

function operateFormatter(el,options,rowData){
	return "<a href='javascript:;' onclick='updateOperatorAccountLogs("+rowData.id+")'><span>修改</span></a> | <a href='javascript:;' onclick='deleteOperatorAccountLogs("+rowData.id+")'><span>删除</span></a>";
}

function finishTypeNameFormatter(el,options,rowData){
	var prefix;
	if(rowData.finishType<100) {
		prefix="实质性办结方式:";
	} else {
		prefix="程序性办结方式:";
	}
	if(rowData.finishTypeName == null ){
		return "";
	}else if(rowData.finishTypeName!=null && rowData.finishTypeName!=""){
		return prefix+rowData.finishTypeName;
	}
	
}

function gridFunction(){
	if(accountType=="<s:property value='@com.tianque.xichang.working.flow.constant.AccountType@PEOPLEASPIRATION'/>"){
		$("#accountLogsList").jqGridFunction({
			datatype: "local",
		   	colModel:[
		        {name:'id',index:'id',hidden:true,sortable:false},
		        <s:if test='"edit".equals(#parameters.mode[0])'>
		        {name:"encryptId",index:'id',frozen:true,hidden:true},
		        {name:'operation',index:'id',label:'操作',sortable:false,formatter:operateFormatter,width:90,frozen :true,align:'center'},
		       	</s:if>
		        {name:'dealDate',sortable:true,label:'处理时间',width:80},
	        	{name:'content',sortable:true,label:'工作措施及内容',width:140},
	        	{name:'dealUser',sortable:true,label:'承办人',width:80},
		        {name:'finishDate',label:'办结时间',sortable:true,align:'center',width:80},
		        {name:'finishType',hidden:true,hidedlg:true},
		        {name:'finishTypeName',label:'办结方式',width:130,formatter:finishTypeNameFormatter},
		        {name:'finishContent',label:'办理结果',width:140,align:'center'}
			],
			multiselect: false
		});
	}
	if(accountType=="<s:property value='@com.tianque.xichang.working.flow.constant.AccountType@POORPEOPLE'/>"){
		$("#accountLogsList").jqGridFunction({
			datatype: "local",
		   	colModel:[
		        {name:'id',index:'id',hidden:true,sortable:false},
		        <s:if test='"edit".equals(#parameters.mode[0])'>
		        {name:"encryptId",index:'id',frozen:true,hidden:true},
		        {name:'operation',index:'id',label:'操作',sortable:false,formatter:operateFormatter,width:90,frozen :true,align:'center'},
		        </s:if>
		        {name:'dealDate',sortable:true,label:'处理时间',width:80},
	        	{name:'content',sortable:true,label:'帮扶措施及落实情况',width:140},
	        	{name:'dealUser',sortable:true,label:'承办人',width:80},
		        {name:'finishDate',label:'办结时间',sortable:true,align:'center',width:80},
		        {name:'finishType',hidden:true,hidedlg:true},
		        {name:'finishTypeName',label:'办结方式',width:130,formatter:finishTypeNameFormatter},
		        {name:'finishContent',label:'办理结果',width:140,align:'center'}
			],
			multiselect: false
		});
	}
	if(accountType=="<s:property value='@com.tianque.xichang.working.flow.constant.AccountType@STEADYWORK'/>"){
		$("#accountLogsList").jqGridFunction({
			datatype: "local",
		   	colModel:[
		        {name:'id',index:'id',hidden:true,sortable:false},
		        <s:if test='"edit".equals(#parameters.mode[0])'>
		        {name:"encryptId",index:'id',frozen:true,hidden:true},
		        {name:'operation',index:'id',label:'操作',sortable:false,formatter:operateFormatter,width:90,frozen :true,align:'center'},
		        </s:if>
		        {name:'dealDate',sortable:true,label:'处理时间',width:80},
	        	{name:'content',sortable:true,label:'工作措施及内容',width:100},
	        	{name:'opinion',sortable:true,label:'当事人或当事人代表意见',width:100},
	        	{name:'dealUser',sortable:true,label:'参加人',width:60},
	        	{name:'site',sortable:true,label:'地点',width:40},
		        {name:'finishDate',label:'办结时间',sortable:true,align:'center',width:80},
		        {name:'finishType',hidden:true,hidedlg:true},
		        {name:'finishTypeName',label:'办结方式',width:130,formatter:finishTypeNameFormatter},
		        {name:'finishContent',label:'办理结果',width:140,align:'center'}
			],
			multiselect: false
		});
	}
}

$(function (){
	gridFunction();
	loadAccountLogsData();
});

function updateOperatorAccountLogs(selectedId){
	var accountLogs = $("#accountLogsList").getRowData(selectedId);
	$("#accountLogsDialog").createDialog({
		title:"修改处理情况信息",
		width: 800,
		height: 600,
		url:'${path}/account/accountLogsManage/dispatch.action?mode=edit&accountLogs.id='+accountLogs.encryptId+"&accountType="+accountType+"&accountId=${param.accountId }",
		buttons: {
			"保存" : function(){
				$("#maintainAccountLogsForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
		
	});
}

function deleteOperatorAccountLogs(allValue){
	var accountLogs = $("#accountLogsList").getRowData(allValue);
	$.confirm({
		title:"确认删除",
		message:"确定要删除吗?一经删除将无法恢复",
		okFunc: function() {
			$.ajax({
				url:'${path}/account/accountLogsManage/deleteAccountLogsById.action?accountLogsId='+accountLogs.encryptId,
				success:function(data){
					if(data!=null) {
						$.messageBox({message:"已经成功删除该处理情况信息!"});
						$("#accountLogsList").trigger("reloadGrid");
					} else {
						$.messageBox({message:data,level:"error"});
					}
				}
			});
		}
	});
}
</script>