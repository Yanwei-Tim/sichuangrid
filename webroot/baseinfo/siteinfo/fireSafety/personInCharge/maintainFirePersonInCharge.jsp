<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div >
	<div class="container container_24">
	<form name="personInChargeForm" id="personInChargeForm" method="post" action="">
		<input type="hidden" id="domainId" name="personInCharges.placeId" value="${placeId}"/>
		<input type="hidden" id="keyType" name="personInCharges.placeType" value="${placeType}"/>
		<input type="hidden" id="" name="personInCharges.id" value="${personInCharges.id}"/>
		<!-- <input type="hidden" id="placeId" name="personInCharges.placeId" value="<s:property value="#parameters.placeNmae"/>" class="form-txt"/> -->
		<div class="grid_8 lable-right">
			<label class="form-lbl">姓名：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="personInChargesName" name="personInCharges.name" value="${personInCharges.name}" class="form-txt" maxlength="15" <s:if test='"view".equals(mode)'>disabled='true'</s:if>/>
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_8 lable-right">
			<label class="form-lbl">手机：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="personInChargesMobile"  name="personInCharges.moblie" value="${personInCharges.moblie}" class="form-txt" maxlength="11" <s:if test='"view".equals(mode)'>disabled='true'</s:if>/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_8 lable-right">
			<label class="form-lbl">电话：</label>
		</div>
		<div class="grid_10">
			<input type="text" id="personInChargesTelephone"  name="personInCharges.telephone" value="${personInCharges.telephone}" class="form-txt" maxlength="15" <s:if test='"view".equals(mode)'>disabled='true'</s:if>/>
		</div>

	</form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
	<s:if test='"add".equals(mode)'>
		$("#personInChargeForm").attr("action","${path}/baseinfo/personInCharegeManage/addPersonInCharege.action");
	</s:if>
	<s:if test='"edit".equals(mode)'>
		$("#personInChargeForm").attr("action","${path}/baseinfo/personInCharegeManage/updatePersonInCharege.action");
	</s:if>
		$("#personInChargeForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					success:function(data){
						<s:if test='"add".equals(mode) || "copy".equals(mode) '>
							$("#personInChargeList").addRowData(data.trId, data, "first");
							$.messageBox({message:"已成功保存新信息"});
							if($("#domainId").val()!=null && $("#domainId").val()!="" && $("#domainId").val()!=0){
								$("#floorpersons").show();
							}
							$("#perUuid").append('<input  type="hidden" name="uuid" value="'+data.personInChargesUuid+'" />');
							$("#personInChargeDialog").dialog("close");
						</s:if>
						<s:if test='"edit".equals(mode)'>
							$("#personInChargeList").setRowData(data.trId,data);
							$.messageBox({message:"已成功修改信息"});
							if(data.isEmphasis==1){
								 $("#"+data.trId+"").css('color','#778899');
								}
							$("#personInChargeDialog").dialog("close");
							$("#personInChargeList").trigger("reloadGrid");
						</s:if>
					    $("#helpPersonnelList").setSelection(data.trId);
					}
				})
			},
			rules:{
				"personInCharges.name":{
					required:true,
					exculdeParticalChar:true,
					minlength:2,
					maxlength:15
				},
				"personInCharges.moblie":{
					mobile:true
				},
				"personInCharges.telephone":{
					telephone:true
				}

			},
			messages:{
				"personInCharges.name":{
					required: "消防安全负责人姓名不能为空",
					exculdeParticalChar:"不能输入非法字符",
					minlength:$.format("至少需要输入{0}个字符"),
					maxlength:$.format("最多需要输入{0}个字符")
				},
				"personInCharges.moblie":{
					mobile:"手机号码输入只能是以1开头的11位数字"
				}
				,
				"personInCharges.telephone":{
					telephone:$.format("联系电话不合法，只能输数字和横杠(-)")
				}

			}
		});
	});

</script>