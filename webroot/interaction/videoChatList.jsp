<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div style="height: 100%; overflow: auto; overflow-x: hidden; position: relative;">
	<div class="content">
		<div class="ui-corner-all" id="nav">
			<pop:JugePermissionTag ename="searchWorkContact">
				<a id="search" href="javascript:void(0)"><span>查询</span></a>
			</pop:JugePermissionTag> 
			<a id="reload" href="javascript:void(0)"><span>刷新</span></a> 
		 	<!-- 此处user表示被邀请人员列表 -->
		 	<INPUT id=user class=req type=hidden value=""/>
			<!-- 此处ID为u表示会议主持人,即当前登录用户 -->
			<INPUT id=u class=req type=hidden value="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getUserName()"/>@hztianque.com">
			<A href="javascript:void(0);" id="startVideoChat" 
			url="/?r=/api/invitemeet"><span>发起邀请</span></A>
		  	<span>会议类型</span>
		  	<SELECT id=type>
				<OPTION selected value=2>视频</OPTION>
				<OPTION value=1>语音</OPTION>
			</SELECT> 
			<input type="hidden" id="tempMeetInput"/>
		</div>
		<div style="display:none;" >
			<INPUT id=ppmeet class=req type=text>
			<INPUT id=host class=req type=text> 
			<INPUT id=exp type=text>
			<INPUT id=key size=32 type=text value="<s:property value="@com.tianque.controller.WorkContactController@key"/>">
			<INPUT id=code class=req size=100 type=text 
				value="<s:property value="@com.tianque.controller.WorkContactController@code"/>">
			<INPUT name=request value=post CHECKED type=radio> 
			<INPUT name=request value=get type=radio>
		</div>
		<div style="width: 100%;" id="grid_content">
			<table id="workContactList"></table>
			<div id="workContactListPager"></div>
		</div>
		<div id="workContactMaintanceDialog"></div>
		<div id="searchDialog"></div>
	</div>
</div>

<script type="text/javascript">
var dialogWidth=550;
var dialogHeight=270;
/**
function getMd5() {
	$.getJSON($('#ppmeet').val()+'/?r=/api/getMD5&callback=?', {str:$('#key').val()}, function(json) {
		$('#key').val(json.md5);
	});
}
function getCreateCode() {
	if($('#code').val()==''){
		$.getJSON($('#ppmeet').val()+'/?r=/api/createCode&callback=?', {host:$('#host').val(), exp:$('#exp').val(), key:encodeURIComponent($('#key').val())}, function(json) {
			$('#code').val(json.code);
		});
	}
}
* 
*/

$(document).ready(function(){
	//视频开始
	$("#startVideoChat").click(function(event){
		var rowIds = $("#workContactList").jqGrid("getGridParam", "selarrrow");
		if(rowIds.length == 0){
			$.messageBox({"message":"请先选中联系人再开启视频！","level":'warn'});
			return;
		}
		var rowIds = $("#workContactList").jqGrid("getGridParam", "selarrrow");
		var str = "";
		for(var i=0;i<rowIds.length;i++){
			var rowData = $("#workContactList").getRowData(rowIds[i]);
			str += rowData.userName;
			str += '@hztianque.com';
			str +=',';
		}
		str = str.substring(0,str.length-1);
		$("#user").val(str);
		sendMessage(rowIds);
	});
    $('a[id!=search]a[id!=reload]').click(function() {
        var data = {};
        var form = $("#tempMeetInput").prevAll('input,select');
        var url = '';
        for (var i=0; i<form.length; i++) {
            data[$(form[i]).attr('id')] = $(form[i]).val();
        }
		data.code = $('#code').val();
		data.key = encodeURIComponent($('#key').val());
		var url = 'http:\//115.236.101.203:8898/?r=/api/invitemeet&callback=?';
        $.getJSON(url, data, function(json) {
			if (json.url) {
				window.open(json.url);
			}
        });
    });
	//视频结束
	$("#workContactList").jqGridFunction({
		url:"${path}/contact/workContactManage/findOtherWorkContacts.action",
		datatype: "json",
		colNames:['id','部门','姓名','联系手机','userName'],
	   	colModel:[
	        {name:"id",index:"id",hidden:true},
	        {name:'organization.orgName',sortable:false,width:200},
	        {name:'name',sortable:false,width:200},
	  		{name:"mobileNumber",sortable:false,width:200},
	  		{name:"userName",index:"userName",hidden:true,sortable:false,width:200}
		],
		multiselect:true,
		scrollrow:true,
		loadComplete: afterLoad,
		onSelectRow:function(){setWorkContactOperateButton();}
	});
	$("#search").click(function(event){
		$("#searchDialog").createDialog({
			width: dialogWidth,
			height: 200,
			datatype: "json",
			title:'查询平台内联系人信息',
			url:'${path}/interaction/contact/workContact/searchWorkContactDlg.jsp',
			buttons: {
				"查询" : function(event){
					refreshWorkContactGrid();
	   				$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});	
	$("#reload").click(function(event){
		reloadList();
	});
	
	function sendMessage(rowIds){
		var ids = "";
		for(var i=0;i<rowIds.length;i++){
			ids += $("#workContactList").getRowData(rowIds[i]).id;
			ids += ",";
		}
		ids = ids.substring(0,ids.length-1);
		$.ajax({
			url:'${path}/interactive/platformMessageOutboxManage/sendPlatformMessage.action?userReceivers='+ids+'&platformMessage.title=视频聊天邀请&platformMessage.content=<a href="javascript:void(0);" onclick="toJoinMeet()">点此参加视频会议<a>',
			success:function(data){
				if(data){
					//$.messageBox({ message:"成功删除台账!"});
					//loadList();
					//return;
				}else{
					//$.messageBox({ message:"考核评估已参考其中的台账，请勿删除！",level: "warn"});
				}
	        }
		});
	}
	function refreshWorkContactGrid() {
		var workContactVoName=$('#workContactVoName').val();
		var workContactVoMobileNumber = $("#workContactVoMobileNumber").val();
		var workContactOrgId=$("#workContactOrgId").val();
		$("#workContactList").setGridParam({
			url:'${path}/contact/workContactManage/searchWorkContacters.action',
			postData: {
				"contacterVo.name":workContactVoName,
				"contacterVo.mobileNumber":workContactVoMobileNumber,
				"contacterVo.organization.id":workContactOrgId
			}
		});
		$("#workContactList").trigger("reloadGrid");
	}
	function reloadList(){
		$("#workContactList").setGridParam({
			url:"${path}/contact/workContactManage/findOtherWorkContacts.action",
			datatype: "json",
			page:1
		});
		$("#workContactList").trigger("reloadGrid");
	}
	function afterLoad(){
		setWorkContactOperateButton();
	}

	function setWorkContactOperateButton(){
	}
	
});
</script>