<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="content">
		<input id="isNowYear"  type="hidden"/>
		<input id="isNowMonth"  type="hidden"/>
	<div class="ui-cornRr-all" id="nav">
		<pop:JugePermissionTag ename="viewSmsInbox">
			<a id="view" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="updateSmsInbox">
			<a id="update"  href="javascript:void(0)"><span>处理</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="deleteSmsInbox">
			<a id="delete" href="javascript:void(0)"><span>删除</span></a>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="searchSmsInbox">
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
		<table id="smsReceivedList"></table>
		<div id="smsReceivedListPager"></div>
	</div>
	<div id="smsReceivedDialog"></div>
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

function isSend(el,options,rowData){
	if (rowData.processFlag==1) {
      return "<input type=\"checkbox\" checked='checked' onclick='clickRecord(this)' disabled/>";
	}else{
      return "<input type=\"checkbox\" disabled/>";
	}
}
$(document).ready(function(){
	$("#smsReceivedList").jqGridFunction({
		url:"${path}/interaction/sms/inbox/findSmsReceivedBoxByOwnerId.action?year="+$("#cyear").val()+"&month="+$("#cmonth").val(),
		datatype: "json",
		colModel:[
	    	{name:"id",index:"id",hidden:true},
  			{name:"sourceMobile",index:"sourceMobile",width:110,label:"来源手机"},
  			{name:"receiverTime",index:"receiverTime",width:140,label:"发送时间"},
  			{name:"smsContent",index:"smsContent",width:500,label:"内容",sortable:false},
 			{name:"processFlag",index:"processFlag",width:67,label:"是否已处理",align:"center",formatter:isSend}
	  	],
		scrollrow:true,
       	ondblClickRow: viewSmsReceived,
		loadComplete: afterLoad,
		onSelectRow:selectRow
	});
	
	$("#view").click(function(event){
		viewSmsReceived();
	});
	
	$("#update").click(function(){
		var selectedId = $("#smsReceivedList").getGridParam("selrow");
		if(selectedId==null){
			 return;
		}
		$("#smsReceivedDialog").createDialog({
			width: 500,
			height: 280,
			title:'短信处理',
			url:"${path}/interaction/sms/inbox/dispatchProcessPage.action?smsReceivedBox.id="+selectedId,
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
	
	$("#delete").click(function(){
		var selectedId = $("#smsReceivedList").getGridParam("selrow");
		if(selectedId==null){
	 		return;
		}
		$.confirm({
			title:"确认删除",
			message:"短信一经删除，无法恢复，确定删除此短信吗?",
			width: 300,
			okFunc: deleteSmsReceived
		});
		afterLoad();
	});
	
	$("#search").click(function(event){
		$("#smsReceivedDialog").createDialog({
			width: 400,
			height: 180,
			title:'查询短信',
			url:'${path}/interaction/sms/inbox/searchSmsReceivedBoxDlg.jsp',
			buttons: {
				"查询" : function(event){
					searchSmsReceived();
					$(this).dialog("close");
				},
			   	"关闭" : function(){
			       	$(this).dialog("close");
			   	}
			}
		});
	});	
	
	$("#reload").click(function(){
		reload();
	});	
});


function reload(){
	$("#smsReceivedList").setGridParam({
		url:"${path}/interaction/sms/inbox/findSmsReceivedBoxByOwnerId.action?year="+$("#cyear").val()+"&month="+$("#cmonth").val(),
		datatype:'json',
		page:1
   	});
	$("#smsReceivedList").setPostData({
   	});
	$("#smsReceivedList").trigger("reloadGrid");
}

function viewSmsReceived(){
	var selectedId = $("#smsReceivedList").getGridParam("selrow");
	if(selectedId==null){
 		return;
	}
	$("#smsReceivedDialog").createDialog({
		width:600,
		height:420,
		title:"查看短信",
		url:"${path}/interaction/sms/inbox/getSmsReceivedBoxById.action?smsReceivedBox.id="+selectedId,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close"); 
		   }
		}
	});
}

function deleteSmsReceived(){
	var selectedId = $("#smsReceivedList").getGridParam("selrow");
	if(selectedId==null){
		 return;
	}
	$.ajax({
		url:"${path}/interaction/sms/inbox/deleteSmsReceivedBoxById.action?smsReceivedBox.id="+selectedId,
		success:function(data){
			if(data){
				$("#smsReceivedList").delRowData(selectedId);
				$.messageBox({ message:"成功删除该已收到短信!"});
				return;
			}
            $.messageBox({
				message:data,
				level: "error"	
			});						
        }
	});
}
function searchSmsReceived(){
	var conditionSourceMobile=$("#conditionSourceMobile").val();
	var conditionStartReceiverTime=$("#conditionStartReceiverTime").val();
	var conditionEndReceiverTime=$("#conditionEndReceiverTime").val();
	$("#smsReceivedList").setGridParam({
		url:"${path}/interaction/sms/inbox/searchSmsReceivedBoxs.action?year="+$("#cyear").val()+"&month="+$("#cmonth").val(),
		page:1,
		postData: {
			"searchSmsReceivedBoxVo.sourceMobile":conditionSourceMobile,
			"searchSmsReceivedBoxVo.startReceiverTime":conditionStartReceiverTime,
			"searchSmsReceivedBoxVo.endReceiverTime":conditionEndReceiverTime
		}
   	});
	$("#smsReceivedList").trigger("reloadGrid");
}
function afterLoad(){
	disableButtons();
}
function disableButtons(){
	$("#view").buttonDisable();
	$("#update").buttonDisable();
	$("#delete").buttonDisable();
}

var existed = true;
function isTreated(){
	var selectedId = $("#smsReceivedList").getGridParam("selrow");
	if(selectedId==null){
		 return;
	}
	$.ajax({
		async:false,
		url:"${path}/interaction/sms/inbox/isTreated.action?smsReceivedBox.id="+selectedId,
		success:function(responseData){
			existed = responseData > 0 ? false : true;			
		}
	});
	return existed; 
}

function selectRow(){
	$("#view").buttonEnable();
	if (isTreated()==true) {
		$("#update").buttonEnable();
	}
	if (isTreated()==false) {
		$("#update").buttonDisable();
	}
	$("#delete").buttonEnable();
}
</script>