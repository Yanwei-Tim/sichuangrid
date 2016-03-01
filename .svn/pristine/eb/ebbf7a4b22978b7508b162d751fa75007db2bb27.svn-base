<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="maintainForm" method="post" action="">
		<pop:token></pop:token>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>所属层级：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="weChatSource.org.orgName" class="form-txt"
				value="${weChatSource.org.orgName}" readonly /> <input type="hidden"
				name="weChatSource.org.id" class="form-txt" value="${weChatSource.org.id}" />
	
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>微信号：</label>
		</div>
		<div class="grid_19">
			<select name="weChatSource.weChatUserId" class='form-txt' id="weChatUserId"
				style="width: 510px" readonly>
				<option value="请选择微信号">请选择微信号</option>
				<s:iterator value="listTencentUser">
					<s:if test="#request.weChatUserId==#request.weChatSource.weChatUserId">
						<option value="${weChatUserId }" selected>${weChatUserId}&nbsp&nbsp&nbsp&nbsp&nbsp[昵称：
							${name}]</option>
					</s:if>
					<s:else>
						<option value="${weChatUserId }">${weChatUserId}&nbsp&nbsp&nbsp&nbsp&nbsp[昵称：
							${name}]</option>
					</s:else>
				</s:iterator>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材类型：</label>
		</div>
		<div class="grid_19">
			<input type="hidden" name="weChatSource.sourceTypeDict.id"
				value="${weChatSource.sourceTypeDict.id}" /> <input type="text"
				class='form-txt' readonly
				value='<pop:PropertyDictViewTag defaultValue="${weChatSource.sourceTypeDict.id}" name="@com.tianque.domain.property.PropertyTypes@WECHAT_TYPE"></pop:PropertyDictViewTag>' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材描述：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="weChatSource.sourceDescription"
				value="${weChatSource.sourceDescription }"
				class='form-txt {required:true,maxlength:100,messages:{required:"请输入素材描述",maxlength:$.format("素材描述最多可以输入{0}个字符")}}' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lbl">内容：</label>
		</div>
		<div class="grid_19 heightAuto">
			<textarea id="Content" name="weChatSource.content" 
				onkeyup="charlength(value)" style='height: 100px; width: 97%'
				class='form-txt {required:true,maxlength:600,messages:{required:"请输入自动回复的内容",maxlength:$.format("自动回复内容最多可以输入{0}个字符")}}'>${weChatSource.content }</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">已输入：</label>
		</div>
		<div class="grid_20 heightAuto">
			<span id="counter"
				style="line-height: 30px; padding-left: 5px; font-style: normal;">0</span>个字（注意：回复内容不要超过600字。)
		</div>
		<input type="hidden" name="weChatSource.id" value="${weChatSource.id}"/>
				<input type="hidden" name="weChatSource.org.orgInternalCode" class="form-txt"
				value="${weChatSource.org.orgInternalCode}" />
	</form>
</div>
<script type="text/javascript">
	document.getElementById("counter").innerText = $("#Content").val().length;
	//计算文字长度
	function charlength(value) {
		var a = value.length;
		document.getElementById("counter").innerText = a;
	}
	$(document)
			.ready(
					function() {
						 <s:if test='"updateSource".equals(mode)'>
							$("#maintainForm")
							.attr("action",
									"${path}/weChatSourceManage/updateWeChatSource.action");
						</s:if>
						<s:else>
						$("#maintainForm")
						.attr("action",
								"${path}/weChatSourceManage/addWeChatSource.action");
						</s:else>
						//提交
						$("#maintainForm")
								.formValidate(
										{
											promptPosition : "bottomLeft",
											submitHandler : function(form) {
												if ($("#weChatUserId").val() == "请选择微信号"
														|| $("#weChatUserId")
																.val() == "") {
													$.messageBox({
														level : "warn",
														message : "请选择要绑定的微信号"
													});
													return false;
												}
												$(form)
														.ajaxSubmit(
																{
																	success : function(
																			data) {
																		if (data == true
																				|| data == "true") {
																			$(
																					"#WeChatSourceMaintanceDialog")
																					.dialog(
																							"close");
																		    <s:if test='"updateSource".equals(mode)'>
																			$
																			.messageBox({
																				message : "文本素材修改成功!"
																			});
																		</s:if>
																		<s:else>
																		$
																		.messageBox({
																			message : "文本素材添加成功!"
																		});
																		</s:else>
																		
																			$(
																					"#weChatSourceList")
																					.trigger(
																							"reloadGrid");
																		} else {
																			$
																					.messageBox({
																						message : data,
																						level : "error"
																					});
																		}
																	},
																	error : function(
																			XMLHttpRequest,
																			textStatus,
																			errorThrown) {
																		alert("提交错误");
																	}
																});
											}
										});

					});
</script>


