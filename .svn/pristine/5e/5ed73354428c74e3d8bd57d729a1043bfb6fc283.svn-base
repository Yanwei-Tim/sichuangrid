<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="maintainForm" method="post" action="">
	<pop:token/>
	<input type="hidden" name="sendToWechatNo" value="${sendToWechatNo}"/>
	<input type="hidden" name="isSendByWechatNo" value="${isSendByWechatNo}"/>
	<div class="grid_4 lable-right">
		<label style="color: red;">*</label>
	</div>
	<div class="grid_20" style="color: red">
		微信用户每月只能接收同一公众号的4条群发消息
	</div>
	<div class='clearLine'>&nbsp;</div>
	<c:if test="${!isSendByWechatNo}">
	<div class="grid_4 lable-right">
		<label>红袖套队伍：</label>
	</div>
	<div class="grid_20">
		<pop:PropertyDictMultiCheckbox  name="weChatResponse.sendType" column="5" domainName="@com.tianque.domain.property.PropertyTypes@RED_CUFF_TEAM_TYPE"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	</c:if>
	<div class="grid_4 lable-right">
		<label>微信号：</label>
	</div>
	<div class="grid_20">
		<select  class='form-txt' id="weChatUserId" name="weChatResponse.wechatUserName" style="width: 450px" >
			<option value="请选择微信号">请选择微信号</option>
			<s:iterator value="listTencentUser">
					<option value="${weChatUserId }" data-weChatUserId="${weChatUserId }">${weChatUserId}&nbsp&nbsp&nbsp&nbsp&nbsp[昵称：
						${name}]</option>
			</s:iterator>
		</select><label id="sendCount"></label>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label>素材：</label>
	</div>
	<div class="grid_20">
		<select  class='form-txt' id="sourceId" name="weChatResponse.sourceId" style="width: 450px" >
			<option value="">请选择微信号</option>
		</select>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<input type="hidden" id="mode" name="mode" value="${mode}"/>
	<input type="hidden" id="orgId" name="weChatResponse.org.id" value="${weChatResponse.org.id}"/>
  </form>
</div>
<select id="sourceTypes" style="display:none" >
	<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@WECHAT_TYPE" ></pop:OptionTag>
</select>
<script type="text/javascript">
var sourceTypes = {};
$(document).ready(function() {
	
	$("#maintainForm").attr("action", "${path}/weChatResponseManage/weChatMassSend.action");
	//提交
	$("#maintainForm").formValidate({
		promptPosition : "bottomLeft",
		submitHandler : function(form) {
			<c:if test="${!isSendByWechatNo}">
			var sendType = $("checkbox[name='weChatResponse.sendType']:checked");
			if(!sendType||sendType.size<1){
				$.messageBox({level: "warn",message : "请选择接收消息的红袖套队伍类别"});
				return false;
			}
			</c:if>
			if($("#weChatUserId").val()=="请选择微信号"||!$("#weChatUserId").val()){
				$.messageBox({level: "warn",message : "请选择要群发使用的微信号"});
				return false;
			}
			if(!$("#sourceId").val()){
				$.messageBox({level: "warn",message : "请选择素材"});
				return false;
			}
			
			$(form).ajaxSubmit({
				success : function(data) {
					if (data ==true||data=="true") {
						$("#weChatResponseMaintanceDialog").dialog("close");
						$.messageBox({message : "群发成功!"});
						<c:if test="${!isSendByWechatNo}">
						$("#weChatResponseList").trigger("reloadGrid");
						</c:if>
					} else {
						$.messageBox({
							message : data,
							level : "error"
						});
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("提交错误");
				}
			});
		}
	});
	$("#weChatUserId").change(getSource);
});
function sendCount(){
	$("#sendCount").html('');
	var wechatUserName = $("#weChatUserId").val();
	if(!wechatUserName){
		return false;
	}
	$.get(PATH+"/weChatResponseManage/sendCount.action",{"weChatResponse.wechatUserName":wechatUserName},function(data){
		$("#sendCount").html("本月已发送<font color='red'> "+ data +" </font>条");
	});
}
function getSource(){
	sendCount();
	var param = {};
	param.weChatSource = {};
	param.weChatSource.sourceTypeDict = {};
	//param.weChatSource.sourceTypeDict.id = 1;//图文
	param.weChatSource.org = {};
	param.weChatSource.org.id = getCurrentOrgId();
	param.weChatSource.weChatUserId=$("#weChatUserId option:selected").attr("data-weChatUserId");
	if(!param.weChatSource.weChatUserId){
		$.messageBox({
			message : '请选择微信号',
			level : "error"
		});
		$("#sourceId").html('');
		return false;
	}
	param.rows=200;
	param.page=1;
	param.sidx='updateDate';
	param.sord='desc';
	$.get(PATH+"/weChatSourceManage/findWeChatSource.action",param,function(page){
		var options = "";
		for(var i=0;i<page.records;i++){
			var weChatSource = page.rows[i];
			var soruceType = "["+$("#sourceTypes option[value="+weChatSource.sourceTypeDict.id+"]").text()+"]";
			if(soruceType=='[图文]'){
				options+="<option value='"+weChatSource.id+"'>"+soruceType+weChatSource.title+"</option>";
			}else{
				options+="<option value='"+weChatSource.id+"'>"+soruceType+weChatSource.sourceDescription+"</option>";
			}
		}
		if(!options){
			options = "<option value=''>无(请到素材管理中添加图文素材)</option>";
			$.messageBox({level: "warn",message : "该微信号下没有图文素材，请到素材管理中添加图文素材"});
		}
		$("#sourceId").html(options);
	});
}
</script>