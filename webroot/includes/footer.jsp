<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="com.tianque.core.globalSetting.service.GlobalSettingService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.tianque.core.globalSetting.util.GlobalSetting"%>
<s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"></s:param>
</s:action>
<%
GlobalSettingService globalSettingService = (GlobalSettingService)WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("globalSettingService");
String sysTitle = globalSettingService.getGlobalValue(GlobalSetting.SYS_TITLE);
String sysBottomPage = globalSettingService.getGlobalValue(GlobalSetting.SYS_BOTTOM_PAGE);
String sysHeaderPage = globalSettingService.getGlobalValue(GlobalSetting.SYS_HEADER_PAGE);
String filePath = globalSettingService.getGlobalValue(GlobalSetting.MOBILE_APK_PATH);
if (filePath != null) {
	filePath = filePath.replace("\\","/");
}
request.setAttribute("sysHeaderPage",sysHeaderPage);
%>
<style>
.jp-jplayer{width:0 !important;height:0 !important;}
</style>
<script type="text/javascript" src="/resource/external/jPlayer/js/jquery.jplayer.min.js"></script>

<span><%=sysBottomPage %></span>
<a href="javascript:upgradeContent();" style="position:relative;left:150px;">升级内容</a>
<div id="wechat_inbox_voice_prompt_jquery_jplayer" class="jp-jplayer"></div>
<!--  
<span class="service">服务热线：400-80-800</span>
<span><a target="_blank" class="feedback" href="http://115.236.101.203:9006/feedback/?username=<s:property value="@java.io.File@getUser().getName()"/>&version=产品3.0">反馈区</a></span>
<a class="down" id="clientDownload" href="javascript:;" title="即时通讯客户端" target="_blank">下载IM客户端</a>
<a class="down" id="clientDownloadandroid" href="javascript:;" title="手机客户端" target="_blank">下载android客户端</a>
<a href="javascript:;" id="showVersion"><span>新功能介绍</span></a>
-->
<script>
	$('#clientDownload').attr('href','${path}/clientDownload/clientDownloadManage/doDownload.action?fileName=TQIM.exe&filePath=download/TQIM.exe');
	$('#clientDownloadandroid').attr('href','${path}/clientDownload/clientDownloadManage/doDownload.action?fileName=tq_product.apk&filePath=<%=filePath%>');
	//定时刷新
	var refreshWechatInbox;
	$(document).ready(function(){
		
		$("#wechat_inbox_voice_prompt_jquery_jplayer").jPlayer({
			ready: function () {
			},
			swfPath: "/resource/external/jPlayer/js",
			solution: "flash, html",
			supplied: "mp3,m4a,flv,arm",
			wmode: "window",
			keyEnabled: true
		});
		initfindWechatInboxVoicePrompt();
	});
	
	function upgradeContentCreateDialog(userId){
    	$("#userCheckUpgradeContentDialog").createDialog({
			width:650,
			height:550,
			title:'最新升级内容',
			url:'${path}/sysadmin/userCheckUpgradeContentManage/findTheLatestUpgradeContent.action?userId='+userId,
			buttons: {
				"导出":function(event){
					excport();
				},
// 			  "一星期不再提醒" : function(event){
// 				  $("#checkState").val('<s:property value="@com.tianque.upgradeContent.constants.CheckState@WEEKNOTSEE"/>');
// 			      $("#mForm").submit();
// 			   },
// 			   "不再提醒" : function(event){
// 				   $("#checkState").val('<s:property value="@com.tianque.upgradeContent.constants.CheckState@LONGERSEE"/>');
// 				   $("#mForm").submit();
// 			   },
			   "关闭" : function(){
			        $(this).dialog("close");
			   }
			}
		});
    }
	
	function upgradeContent(){
		upgradeContentCreateDialog(USER_ID);
	}
	
	
	//查找当前层级是否创建语音提示(如果没有创建，添加一条默认数据，播放默认路径语音)
	function initfindWechatInboxVoicePrompt(){
		
		var currentOrgId = getCurrentOrgId();
		var url = "${path}/wechatInboxVoicePromptManage/findVoicePromptStatusAndInit.action";
		var dataList="wechatInboxVoicePrompt.org.id="+currentOrgId;
		
		$.ajax({
			type:'post',  		   
			url:url,
  		   // async:false,
  		    data:dataList,
  		    dataType:'json',
			success : function(res) {
				if(res==false||res=="false"){
					return;
				}
				
				var displayName = res.voicePromptStatus.displayName;
				//此参数表示是否为新消息(0表示否,1为是)
				var isNewInbox = res.isNewInbox;
				//console.log(res);
				if(displayName==null||displayName==""||isNewInbox==null||isNewInbox==""){
					setTimeout("initfindWechatInboxVoicePrompt()",60000);
					return;
				}
				
				if(displayName=="开启"&&isNewInbox=="1"){	
					
					var voiceUrl = res.voiceUrl;
					openWechatInboxVoicePrompt(voiceUrl);
				}
				setTimeout("initfindWechatInboxVoicePrompt()",60000);
			}
		});
	}
	
	//开启微信消息语音提示
	function openWechatInboxVoicePrompt(voiceUrl){
		
		$("#wechat_inbox_voice_prompt_jquery_jplayer").jPlayer( "clearMedia" );
		$("#wechat_inbox_voice_prompt_jquery_jplayer").jPlayer("setMedia", {
			"mp3": voiceUrl
		}).jPlayer("play");
		
		setTimeout("stopWechatInboxVoicePrompt()",20000);
	}
	//关闭微信消息语音提示
	function stopWechatInboxVoicePrompt(){
		$("#wechat_inbox_voice_prompt_jquery_jplayer").jPlayer("stop");
	}
</script>