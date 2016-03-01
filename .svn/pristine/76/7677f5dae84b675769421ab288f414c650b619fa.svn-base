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
		</div>
		<pop:JugePermissionTag ename="updateWechatInboxVoicePrompt">
			<a id="updateWechatInboxVoicePrompt" href="javascript:void(0)"><span>修改</span></a>
		</pop:JugePermissionTag>
		<a id="reload" href="javascript:void(0)"><span>刷新</span></a>
	</div>
	<div style="clear: both;"></div>
	<div style="width: 100%;">
		<table id="wechatInboxVoicePromptList"> </table>
		<div id="wechatInboxVoicePromptListPager"></div>
	</div>
	<div id="wechatInboxVoicePromptDialog"></div>
</div>
<script type="text/javascript">

$(document).ready(function(){
	
		$("#wechatInboxVoicePromptList").jqGridFunction({
			datatype: "local",
			sortname:"createDate",
			multiselect:true,
		   	colModel:[      
		   	    {name:'id',index:'id',hidden:true,frozen:true,sortable:false},
		   	    {name:'toUserName',index:'toUserName',label:'微信公众号',sortable:false,width:60,align:'center'},
		   	    //{name:'voiceUrl',index:'voiceUrl',label:'语音路径',sortable:false,width:60,align:'center'},  
		   	    {name:'displayName',index:'displayName',label:'语音提示状态',sortable:false,width:60,align:'center',formatter:formatterAvailability},
		   	    {name:'createDate',index:'createDate',label:'创建时间',sortable:true,width:60,align:'center'}   	  
			],
			shrinkToFit:true
		});

		
	wechatInboxVoicePromptList({
		"wechatInboxVoicePrompt.org.id" :getCurrentOrgId()
	});
	// 刷新
	$("#reload").click(function() {
		wechatInboxVoicePromptList({
			"wechatInboxVoicePrompt.org.id" :getCurrentOrgId()
		});
	});
    //修改
	$("#updateWechatInboxVoicePrompt").click(function() {
		
		var allValue = getSelectedIds();
		
		if(allValue.length ==0||allValue.length>1){
			$.messageBox({level : 'warn',message:"请选择一条数据，再进行修改！"});
			return;
		}	
		$("#wechatInboxVoicePromptDialog").createDialog({
	    	title:'修改微信消息提示语音',
	    	width:500,
			height:330,
			postData:{
				"wechatInboxVoicePrompt.org.id":getCurrentOrgId(),
				"wechatInboxVoicePrompt.id":allValue,
				"mode":"edit"
			},
			url:'${path}/wechatInboxVoicePromptManage/dispatch.action',
			buttons: {
				"保存" : function(event){
					$("#editWechatInboxVoicePromptForm").submit();
				},
				"关闭" : function(){
					$(this).dialog("close");
				}
			}
		});
	});
   
});

//列表显示
function wechatInboxVoicePromptList(obj){
	 $("#wechatInboxVoicePromptList").setGridParam({
		url:'${path}/wechatInboxVoicePromptManage/findWechatInboxVoicePrompts.action',
		datatype: "json",
		page:1,
		mytype:"post"
	});
	$("#wechatInboxVoicePromptList").setPostData(obj);
	$("#wechatInboxVoicePromptList").trigger("reloadGrid");  
}

function formatterAvailability(el,options,rowData){
	var displayName=rowData.voicePromptStatus.displayName;

	if(displayName!=null&&displayName!=""){
	  return displayName;
	}
}

function getSelectedIds(){
	var selectedIds = $("#wechatInboxVoicePromptList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
</script>


