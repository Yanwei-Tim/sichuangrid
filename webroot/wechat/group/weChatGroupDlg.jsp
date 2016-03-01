<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="maintainForm" method="post" action="">
	<pop:token></pop:token>
	<div class="grid_4 lable-right">
		<label>微信号：</label>
	</div>
	<div class="grid_19">
		<s:if test='"edit".equals(mode)'>
			<input type="text" readonly  id="weChatUserId" name="weChatGroup.weChatUserId" value="${weChatGroup.weChatUserId}" class='form-txt' />
		</s:if>
		<s:else>
			<select  class='form-txt' id="weChatUserId" name="weChatGroup.weChatUserId" value="${weChatGroup.weChatUserId}"style="width: 510px" >
				<option value="请选择微信号">请选择微信号</option>
				<s:iterator value="listTencentUser">
				
						<option value="${weChatUserId }">${weChatUserId}&nbsp&nbsp&nbsp&nbsp&nbsp[昵称：
							${name}]</option>
				</s:iterator>
			</select>
		</s:else>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label>分组名称：</label>
	</div>
	<div class="grid_19">
		<input type="text"  id="groupName" name="weChatGroup.name" value="${weChatGroup.name }" class='form-txt {required:true,maxlength:10,messages:{required:" 请输入分组名称",maxlength:$.format("关键词描述最多可以输入{0}个字符")}}' />
	</div>
	<div class='clearLine'>&nbsp;</div>
		<input type="hidden" id="mode" name="mode" value="${mode}"/>
		<input type="hidden"  name="weChatGroup.groupId" value="${weChatGroup.groupId}"/>
		<input type="hidden" id="weChatGroupId" name="weChatGroup.weChatGroupId" value="${weChatGroup.weChatGroupId}"/>
  </form>
</div>
<script type="text/javascript">
$(document).ready(function() {
	
	<s:if test='"edit".equals(mode)'>
	  $("#maintainForm").attr("action", "${path}/weChatGroupManage/updateWeChatGroup.action");
	</s:if>
	<s:else>
	$("#maintainForm").attr("action", "${path}/weChatGroupManage/addWeChatGroup.action");
	</s:else>
	//提交
	$("#maintainForm").formValidate({
		promptPosition : "bottomLeft",
		submitHandler : function(form) {
			if($("#weChatUserId").val()=="请选择微信号"||$("#weChatUserId").val()==""){
				$.messageBox({level: "warn",message : "请选择要创建分组的微信号"});
				return false;
				}
			$(form).ajaxSubmit({
				success : function(data) {
					if (data ==true||data=="true") {
						$("#weChatGroupMaintanceDialog").dialog("close");
						<s:if test='"edit".equals(mode)'>
						$.messageBox({message : "修改成功!"});
						</s:if>
						<s:else>
						$.messageBox({message : "创建成功!"});
						</s:else>
						$("#weChatGroupList").trigger("reloadGrid");
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

});
</script>