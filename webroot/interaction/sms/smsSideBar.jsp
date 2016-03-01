<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<script type="text/javascript">
$(".subnavbar li>a").each(function(){
	var subWidth=$(this).innerWidth()+15;
	$(this).parent().css("width",subWidth);		
});
function hideLoading_contact(){
	$("#loading_sms").css("display","none");
}
function asyncContactOpen(srcObj, url) {
	if($(srcObj).hasClass("click")){
		return;
	}
	document.title = $(srcObj).text();
	$("#contentDiv_sms").html("");
	$("#loading_sms").css("display","block");
	$.ajax({
		url : url,
		cache: false,
		success : function(result) {
			proccessLoginResult(result,function(){hideLoading_contact();$("#contentDiv_sms").html(result);});
		}
	});
	$(".subnavbar li>a").removeClass("click");
	$(srcObj).addClass("click");	
}

$(function() {
	$(".subnavbar li:first>a").click();
});
</script>
<div class="tabbox">
	<ul class="subnavbar">
		<pop:JugePermissionTag ename="smsInboxManagement">
			<li><a href="javascript:void(0)" onclick="asyncContactOpen(this,'${path}/interaction/sms/inbox/smsReceivedBoxList.jsp')"><span>收件箱</span></a></li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="smsOutboxManagement">
			<li><a href="javascript:void(0)" onclick="asyncContactOpen(this,'${path}/interaction/sms/outbox/smsSendList.jsp')"><span>发件箱</span></a></li>
		</pop:JugePermissionTag>
	</ul>
</div>
<div>
	<div class="content_sms" >
		<div id="loading_sms" style="display: none;">
	  		<img src="${resource_path}/resource/images/loading.gif" alt="加载中..." />
	  	</div>
	  	<div id="contentDiv_sms"></div>
	</div>
</div>

