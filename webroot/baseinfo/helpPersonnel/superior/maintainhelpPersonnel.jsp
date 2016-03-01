<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div >
	<div class="grid_24 container_24">
	<form name="helpPersonnelForm" id="helpPersonnelForm" method="post" action="">
	<pop:token />
	<input type="hidden" id="domainId" name="helpPersonnel.personId" value="${personnelId}"/>
		<input type="hidden" id="" name="helpPersonnel.personType" value="${personnelType}"/>
		<input type="hidden" id="" name="helpPersonnel.id" value="${helpPersonnel.id}"/>
		<div class="grid_9 lable-right">
			<label class="form-lbl">综治人员姓名：</label>
		</div>
		<div class="grid_14">
			<input type="text" maxlength="15" id="helpPersonnelName" name="helpPersonnel.name" value="${helpPersonnel.name}" class="form-txt" <s:if test='"view".equals(mode)'>disabled='true'</s:if>/>
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_9 lable-right">
			<label class="form-lbl">综治人员身份：</label>
		</div>
		<div class="grid_14">
			<input type="text" id="helpPersonnelIdentity" maxlength="15" name="helpPersonnel.identity" value="${helpPersonnel.identity}" class="form-txt" <s:if test='"view".equals(mode)'>disabled='true'</s:if>/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_9 lable-right">
			<label class="form-lbl">综治人员手机：</label>
		</div>
		<div class="grid_14">
			<input type="text" id="helpPersonnelMobile"  name="helpPersonnel.mobile" value="${helpPersonnel.mobile}" class="form-txt" maxlength="11" <s:if test='"view".equals(mode)'>disabled='true'</s:if>/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_9 lable-right">
			<label class="form-lbl">综治人员电话：</label>
		</div>
		<div class="grid_14">
			<input type="text" id="helpPersonnelTelephone"  name="helpPersonnel.telephone" value="${helpPersonnel.telephone}" class="form-txt" maxlength="15" <s:if test='"view".equals(mode)'>disabled='true'</s:if>/>
		</div>

	</form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
	<s:if test='"add".equals(mode)'>
		$("#helpPersonnelForm").attr("action","${path}/baseinfo/helpPersonnel/addHelpPersonnel.action");
	</s:if>
	<s:if test='"edit".equals(mode)'>
		$("#helpPersonnelForm").attr("action","${path}/baseinfo/helpPersonnel/updateHelpPersonnel.action");
	</s:if>
		$("#helpPersonnelForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					success:function(data){
						<s:if test='"add".equals(mode) || "copy".equals(mode) '>
							$("#helpPersonnelList").addRowData(data.trId, data, "first");
							$.messageBox({message:"已成功保存新信息"});
							$("#perUuid").append('<input type="hidden" name="uuid" value="'+data.helpPersonnelUuid+'" />');
							if($("#domainId").val()!=null && $("#domainId").val()!="" && $("#domainId").val()!=0){
								$("#superiorHelpPrecord").show();
								}
						</s:if>
						<s:if test='"edit".equals(mode)'>
							$("#helpPersonnelList").setRowData(data.trId,data);
							$.messageBox({message:"已成功修改信息"});
							if(data.isEmphasis==1){
								 $("#"+data.trId+"").css('color','#778899');
								}
						</s:if>
						$("#helpPersonnelDialog").dialog("close");
					    $("#helpPersonnelList").setSelection(data.trId);
					}
				})
			},
			rules:{
				"helpPersonnel.name":{
					required:true,
					exculdeParticalChar:true,
					minlength:2,
					maxlength:15
				},
				"helpPersonnel.identity":{
					exculdeParticalChar:true
				},
				"helpPersonnel.mobile":{
					mobile:true
				},
				"helpPersonnel.telephone":{
					telephone:true
				}

			},
			messages:{
				"helpPersonnel.name":{
					required: "综治人员姓名不能为空",
					exculdeParticalChar:"不能输入非法字符",
					minlength:$.format("至少需要输入{0}个字符"),
					maxlength: $.format("最多需要输入{0}个字符")
				},
				"helpPersonnel.identity":{
					exculdeParticalChar:"不能输入非法字符"
				},
				"helpPersonnel.mobile":{
					mobile:"手机号码输入只能是以1开头的11位数字"
				}
				,
				"helpPersonnel.telephone":{
					telephone:$.format("联系电话不合法，只能输数字和横杠(-)")
				}

			}
		});
	});

</script>