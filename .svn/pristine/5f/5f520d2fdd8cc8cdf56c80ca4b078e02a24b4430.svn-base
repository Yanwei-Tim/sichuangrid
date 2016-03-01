<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="tabbox">
	<ul class="subnavbar">
		<pop:JugePermissionTag ename="workContactManagement">
			<li><a href="javascript:void(0)" onclick="asyncContactOpen(this,'${path}/interaction/contact/workContact/workContactList.jsp')"><span>平台内联系人</span></a></li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="myContactManagement">
			<li><a href="javascript:void(0)" onclick="asyncContactOpen(this,'${path}/interaction/contact/myContact/myContactList.jsp')"><span>其他联系人</span></a></li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="groupManagement">
			<li><a href="javascript:void(0)" onclick="asyncContactOpen(this,'${path}/contact/myGroupManage/findMyGroupsForPage.action')"><span>我的群组</span></a></li>
		</pop:JugePermissionTag>
	</ul>
</div>
<div>
	<div class="content_contact" >
		<div id="loading_contact" style="display: none;">
	  		<img src="${resource_path}/resource/images/loading.gif" alt="加载中..." />
	  	</div>
	  	<div id="contentDiv_contact"></div>
	</div>
</div>
<div id="myGroupDailog"></div>
<div id="myContactMaintanceDialog"></div>
<div id="myGroupContacterMaintanceDialog"></div>
<script type="text/javascript">
$(".subnavbar li>a").each(function(){
	var subWidth=$(this).innerWidth()+15;
	$(this).parent().css("width",subWidth);
});
$(function() {
	$(".subnavbar li:first>a").click();
});
function asyncContactOpen(srcObj, url) {
	if($(srcObj).hasClass("click")){
		return;
	}
	document.title = $(srcObj).text();
	$("#contentDiv_contact").html("");
	$("#loading_contact").css("display","block");
	$.ajax({
		url : url,
		cache: false,
		success : function(result) {
			proccessLoginResult(result,function(){hideLoading_contact();$("#contentDiv_contact").html(result);});
		}
	});
	$(".subnavbar li>a").removeClass("click");
	$(srcObj).addClass("click");
}
function hideLoading_contact(){
	$("#loading_contact").css("display","none");
}
</script>