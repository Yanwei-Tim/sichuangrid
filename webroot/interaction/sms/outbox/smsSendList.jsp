<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
	<div class="ui-cornRr-all" id="nav">
		<pop:JugePermissionTag ename="addSmsOutbox">
			<a id="add" href="javascript:void(0)" >新短信</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="viewSmsOutbox">
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteSmsOutbox">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchSmsOutbox">
			<a id="search" href="javascript:void(0)"><span>查询</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
		<select name="queryYear" id="cyear" onchange="" >
        </select>
	    <label style="padding:0 10px;line-height:25px;">年</label>
	        <select  id="cmonth" onchange="">
	        </select>
	    <label style="padding:10 10px;line-height:25px;">月</label>
	</div>
	<div style="width: 100%;">
		<table id="smsSendList"></table>
		<div id="smsSendListPager"></div>
	</div>
	<div id="smsSendDialog"></div>
</div>
<script type="text/javascript">

$(function() {
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForYearToSpecial.action",
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cyear").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
			getmonth();
		}
	});
	$("#cyear").change(function(){
		$("#cmonth").empty();
		getmonth();
		reload();
	});
	$("#cmonth").change(function(){
		reload();
	});
});


function getmonth(){
	$.ajax({
		async: false,
		url: "${path }/stat/currentTime/getCurrentTimeForMonth.action?currenYear="+$("#cyear").val(),
		success:function(responseData){
			for(var i = 0;i<responseData.length;i++){
				$("#cmonth").append("<option value='"+responseData[i]+"'>"+responseData[i]+"</option>"); 
			}
		}
	});
}

function reload(){
	$("#smsSendList").setGridParam({
		url:"${path}/interaction/sms/outbox/findSmsSendBoxByOwnerId.action?year="+$("#cyear").val()+"&month="+$("#cmonth").val(),
		datatype: "json",
		page:1
	});
	$("#smsSendList").setPostData({
   	});
	$("#smsSendList").trigger("reloadGrid");
}
function isSend(el,options,rowData){
	if (rowData.sendStatus==0) {
      return "<input type=\"checkbox\" checked='checked' onclick='clickRecord(this)' disabled/>";
	}else{
      return "<input type=\"checkbox\" disabled/>";
	}
}
$(document).ready(function(){
	$("#smsSendList").jqGridFunction({
		url:"${path}/interaction/sms/outbox/findSmsSendBoxByOwnerId.action?year="+$("#cyear").val()+"&month="+$("#cmonth").val(),
		datatype: "json",
		colModel:[
	    	{name:"id",index:"id",hidden:true},
  			{name:"receiver",index:"receiver",width:190,label:"发送目标"},
  			{name:"createDate",index:"createDate",width:140,label:"发送时间"},
  			{name:"smsContent",index:"smsContent",width:423,label:"内容",sortable:false},
 			{name:"sendStatus",index:"sendStatus",width:65,label:"是否已发送",align:"center",formatter:isSend}
	  	],
		loadComplete: afterLoad,
       	ondblClickRow: viewSmsSend,
		onSelectRow:selectRow
	});
	$("#add").click(function(event){
		$("#smsSendDialog").createDialog({
			width: 750,
			height: 560,
			title:'新建短消息',
			url:"${path}/interaction/sms/outbox/dispatch.action",
			buttons: {
				"发送" : function(event){
			    	$("#maintainForm").submit();
			    },
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#search").click(function(event){
		$("#smsSendDialog").createDialog({
			width: 400,
			height: 180,
			title:'查询短消息',
			url:'${path}/interaction/sms/outbox/searchSmsSendDlg.jsp',
			buttons: {
				"查询" : function(event){
					searchSmsSend();
					$(this).dialog("close");
			   	},
			    "关闭" : function(){
			        $(this).dialog("close");
			    }
			}
		});
	});

	$("#view").click(function(event){
		viewSmsSend();
	});

	$("#delete").click(function(){
		var selectedId = $("#smsSendList").getGridParam("selrow");
		if(selectedId==null){
			 return;
		}
		$.confirm({
			title:"确认删除",
			message:"短消息一经删除，无法恢复，确定删除此短消息吗?",
			width: 300,
			okFunc: deleteSmsSend
		});
		afterLoad();
	});
	
	$("#reload").click(function(){
		reload();
	});
});

function viewSmsSend(){
	var selectedId = $("#smsSendList").getGridParam("selrow");
	if(selectedId==null){
 		return;
	}
	$("#smsSendDialog").createDialog({
		width:600,
		height:300,
		title:"查看短消息",
		url:"${path}/interaction/sms/outbox/getSmsSendBoxById.action?smsSendBox.id="+selectedId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close"); 
		   }
		}
	});
}

function deleteSmsSend(){
	var selectedId = $("#smsSendList").getGridParam("selrow");
	if(selectedId==null){
		 return;
	}
	$.ajax({
		url:"${path}/interaction/sms/outbox/deleteSmsSendBoxById.action?smsSendBox.id="+selectedId,
		success:function(data){
			if(data){
				$("#smsSendList").delRowData(selectedId);
				$.messageBox({ message:"成功删除该已发送信息!"});
				return;
			}
            $.messageBox({
				message:data,
				level: "error"	
			});						
        }
	});
}

function searchSmsSend(){
	var conditionReceiver=$("#conditionReceiver").val();
	var conditionStartCreateTime=$("#conditionStartCreateTime").val();
	var conditionEndCreateTime=$("#conditionEndCreateTime").val();
	$("#smsSendList").setGridParam({
		url:"${path}/interaction/sms/outbox/searchSmsSendBoxs.action?year="+$("#cyear").val()+"&month="+$("#cmonth").val(),
		datatype: "json",
		page:1
	});
	$("#smsSendList").setPostData({
		"searchSmsSendBoxVo.receiver":conditionReceiver,
		"searchSmsSendBoxVo.startCreateTime":conditionStartCreateTime,
		"searchSmsSendBoxVo.endCreateTime":conditionEndCreateTime
   	});
	$("#smsSendList").trigger("reloadGrid");
}

function afterLoad(){
	disableButtons();
}
function disableButtons(){
	$("#view").buttonDisable();
	$("#delete").buttonDisable();
}
function selectRow(){
	$("#view").buttonEnable();
	$("#delete").buttonEnable();
}
</script>