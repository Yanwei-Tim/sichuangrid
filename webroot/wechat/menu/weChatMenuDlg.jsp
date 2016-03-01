<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="container container_24">
	<form id="maintainForm" method="post" action="">
		<pop:token></pop:token>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>微信号：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="weChatMenu.weChatUserId" class='form-txt' readonly value="${weChatMenu.weChatUserId }"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>菜单名称：</label>
		</div>
		<div class="grid_19">
			<input type="text" name="weChatMenu.menuName" readonly value="${weChatMenu.menuName}"
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>菜单类型：</label>
		</div>
		<div class="grid_19">
			<input type="text"  name="menuType" readonly value="单击" class='form-txt' />
			<input type="hidden" name="weChatMenu.menuType" value="${weChatMenu.menuType}"
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材类型：</label>
		</div>
		<div class="grid_19">
		<input type="hidden" name="weChatMenu.sourceTypeDict.id"
				value="${weChatMenu.sourceTypeDict.id}" /> <input type="text"
				class='form-txt' readonly
				value='<pop:PropertyDictViewTag defaultValue="${weChatMenu.sourceTypeDict.id}" name="@com.tianque.domain.property.PropertyTypes@WECHAT_TYPE"></pop:PropertyDictViewTag>' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label>素材编号：</label>
		</div>
		<div class="grid_19">
			<select name="weChatMenu.sourceID" id="SourceId"  class="form-txt" style="width: 510px" readonly
				onchange="changeSourceId(value)">
				<option value="请选择素材编号">请选择素材编号</option>
				<s:iterator value="listWeChatSources">
					<s:if test="#request.id==#request.weChatMenu.sourceID">
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
			<input name="weChatMenu.sourceDescription" id="weChatMenuSourceDescription" value="${weChatMenu.sourceDescription}" readonly
				class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<input type="hidden" id="mode" name="mode" value="${mode}"/>
		<input type="hidden" id="weChatMenuId" name="weChatMenu.id" value="${weChatMenu.id}"/>
	</form>

</div>
<div id="selectPersonDialog"></div>
<script type="text/javascript">
	$(".sourceDescriptions").hide();
	function changeSourceId(value) {
		$(".sourceDescriptions").each(function() {
			var id = $(this).attr("id");
			if (value == "请选择素材编号" ||value ==-1 || value == null) {
				$("#weChatMenuSourceDescription").val("");
			}
			if (id == value) {
				$("#weChatMenuSourceDescription").val($(this).text());
			}
		})
	}
	$(document).ready(function() {
		
		if($("#SourceId").val()=="请选择素材编号"||$("#SourceId").val()==-1 ){
			$("#weChatMenuSourceDescription").val("");
		}
    	  $("#maintainForm").attr("action", "${path}/weChatMenuManage/updateWeChatMenu.action");
		
		//提交
		$("#maintainForm").formValidate({
			promptPosition : "bottomLeft",
			submitHandler : function(form) {
				if($("#SourceId").val()=="请选择素材编号"||$("#SourceId").val()=="")
					{	
					$.messageBox({level: "warn",message : "请选择要綁定的素材编号"});
					return false;
					}
				$(form).ajaxSubmit({
					success : function(data) {
						if (data ==true||data=="true") {
							$("#weChatMenuMaintanceDialog").dialog("close");
							$.messageBox({message : "绑定成功!"});
							$("#weChatMenuList").trigger("reloadGrid");
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


