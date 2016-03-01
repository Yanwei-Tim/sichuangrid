<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<input type="text" name="tencentUser.organization.orgName" class="form-txt"
				value="${tencentUser.organization.orgName}" readonly /> <input type="hidden"
				name="tencentUser.organization.id" class="form-txt" value="${tencentUser.organization.id}" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>微信号：</label>
		</div>
		<div class="grid_19">
			<input name="tencentUser.weChatUserId" class='form-txt' readonly value="${tencentUser.weChatUserId}" id="weChatUserId"/>
				
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材类型：</label>
		</div>
		<div class="grid_19">
		<input  type="hidden" name="tencentUser.sourceTypeDict.id" value="${tencentUser.sourceTypeDict.id}"/>
		<input type="text" class='form-txt' id="sourceType" readonly value='<pop:PropertyDictViewTag defaultValue="${tencentUser.sourceTypeDict.id}" name="@com.tianque.domain.property.PropertyTypes@WECHAT_TYPE"></pop:PropertyDictViewTag>'/>
		
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材编号：</label>
		</div>
		<div class="grid_19">
			<select name="tencentUser.sourceId" id="SourceId"  class="form-txt" style="width: 510px" readonly
				onchange="changeSourceId(value)">
				<option value="请选择素材编号">请选择素材编号</option>
				<s:iterator value="listWeChatSources">
					<s:if test="#request.id==#request.tencentUser.sourceId">
						<option value="${id }" selected>${id}[${sourceDescription}]</option>
					</s:if>
					<s:else>
					   <option value="${id }">${id}[${sourceDescription}]</option>
					</s:else>
				</s:iterator>
				<option value=-1 >无</option>
			</select>
			<s:iterator value="listWeChatSources">
				<span class="sourceDescriptions" id="${id}">${sourceDescription}</span>
			</s:iterator>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材描述：</label>
		</div>
		<div class="grid_19">
			<input name="tencentUser.sourceDescription" id="tencentUserSourceDescription" value="${tencentUser.sourceDescription}" readonly
				class="form-txt" />
		</div>
	
		<div class='clearLine'>&nbsp;</div>
		<div id="isAppendKeyWord">
			<div class="grid_4 lable-right">
				<em class="form-req">*</em> <label>是否追加关键字：</label>
			</div>
			<div class="grid_19">
			<span id="isAppendValue">${tencentUser.isAppendKeyWord}</span>
				<select name="tencentUser.isAppendKeyWord" style="width: 510px" id="tencentUserIsAppendKeyWord" class="form-txt"  readonly value="${tencentUser.isAppendKeyWord}">
					<option value="1">是</option>
					<option value="2">否</option>
					
				</select>
			</div>
		</div>
		
		<input type="hidden" id="mode" name="mode" value="${mode}"/>
		<input type="hidden" id="tencentUserId" name="tencentUser.tencentUserId" value="${tencentUser.tencentUserId}"/>
	</form>

</div>
<div id="selectPersonDialog"></div>
<script type="text/javascript">
	$(".sourceDescriptions").hide();
	function changeSourceId(value) {
		$(".sourceDescriptions").each(function() {
			var id = $(this).attr("id");
			if (value == "请选择素材编号"||value ==-1  || value == null) {
				$("#tencentUserSourceDescription").val("");
				$("#isAppendKeyWord").hide();
			}
			if (id == value) {
				$("#tencentUserSourceDescription").val($(this).text());
				if($("#sourceType").val()=="图片"||$("#sourceType").val()=="语音")
					$("#isAppendKeyWord").hide();
				else
					$("#isAppendKeyWord").show();
			}
		})
	}
	$(document).ready(function() {
		$("#isAppendKeyWord").show();
		if($("#SourceId").val()=="请选择素材编号"||$("#SourceId").val()==-1 ){
			$("#isAppendKeyWord").hide();
			$("#tencentUserSourceDescription").val("");
		}
		if($("#sourceType").val()=="图片"||$("#sourceType").val()=="语音"){
			$("#isAppendKeyWord").hide();
		}
		$("#isAppendValue").hide();
		$("#tencentUserIsAppendKeyWord option").each(function(){
			if($(this).val()==$("#isAppendValue").text())
				$(this).attr("selected","selected");
		})
		
		
		$("#maintainForm").attr("action", "${path}/tencentUserManage/updateTencentUser.action");
		//提交
		$("#maintainForm").formValidate({
			promptPosition : "bottomLeft",
			submitHandler : function(form) {
				if($("#SourceId").val()=="请选择素材编号"||$("#SourceId").val()=="")
					{	
					$.messageBox({level: "warn",message : "请选择要绑定的素材编号"});
					return false;
					}
				$(form).ajaxSubmit({
					success : function(data) {
						if (data ==true||data=="true") {
							$("#replyMessageDlg").dialog("close");
							$.messageBox({message : "绑定成功!"});
							$("#tencentUserList").trigger("reloadGrid");
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


