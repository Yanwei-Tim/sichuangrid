<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="" class="container container_24">
	<form name="helpPrecordForm" id="helpPrecordForm" method="post" action="">
	<pop:token />
		<input type="hidden" id="domainId" name="helpPrecord.personnelId" value="${personnelId}"/>
		<input type="hidden" id="keyType" name="helpPrecord.personnelType" value="${personnelType}"/>

		<input name="helpPrecord.id" id="helpPrecord.id" value="${helpPrecord.id}" type="hidden">

		<input type="hidden" id="helpPersonnelName" name="" value="${helpPrecord.helpPersonnelName}"/>
		<input type="hidden" id="personNmae" name="helpPrecord.personNmae" value=""/>
		<input type="hidden" id="personTypeNames" name="helpPrecord.personTypeName" value=""/>


		<div class="grid_6 lable-right">
			<label class="form-lbl">帮教人员：</label>
		</div>
		<div class="grid_15 form-left heightAuto" id="selectPerson" style="line-height:20px;">
			<input name="helpPrecord.helpPersonnelName" id="helpPersonName"  class="form-txt">
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">帮教时间：</label>
		</div>
		<div class="grid_15">
			<input type="text" id="helpPrecordTime" name="helpPrecord.helpTime" value="<s:date name="helpPrecord.helpTime" format="yyyy-MM-dd"/>" class="form-txt" readonly="readonly" <s:if test='"view".equals(mode)'>disabled='true'</s:if>/>
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">帮教地点：</label>
		</div>
		<div class="grid_15">
			<input type="text" id="helpPrecordAddress"  name="helpPrecord.address" value="${helpPrecord.address}" class="form-txt" <s:if test='"view".equals(mode)'>disabled='true'</s:if>/>
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">帮教情况：</label>
		</div>
		<div class="grid_15">
			<textarea rows="10" cols="20" id="helpPrecordEvents"  name="helpPrecord.events"  class="form-txt" <s:if test='"view".equals(mode)'>disabled='true'</s:if>>${helpPrecord.events}</textarea>
		</div>
	</form>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		$("#personTypeNames").val($("#personTypeName").val());
		$("#personNmae").val($("#name").val());
		$('#helpPrecordTime').datePicker({
			yearRange: '1900:2020',
			dateFormat: 'yy-mm-dd',
	        maxDate:'+0d'});
	<s:if test='"add".equals(mode)'>
		$("#helpPrecordForm").attr("action","${path}/baseinfo/helpPrecord/addHelpPrecord.action");
	</s:if>
	<s:if test='"edit".equals(mode)'>
		$("#helpPrecordForm").attr("action","${path}/baseinfo/helpPrecord/updateHelpPrecord.action");
	</s:if>
		$("#helpPrecordForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					success:function(data){
						<s:if test='"add".equals(mode) || "copy".equals(mode) '>
							$("#helpPrecordList").addRowData(data.id, data, "first");
							$.messageBox({message:"已成功保存新信息"});
						</s:if>
						<s:if test='"edit".equals(mode)'>
							$("#helpPrecordList").setRowData(data.id,data);
							$.messageBox({message:"已成功修改信息"});
						</s:if>
						$("#helpPrecordDialog").dialog("close");
					    $("#helpPrecordList").setSelection(data.id);
					}
				})
			},
			rules:{
				"helpPrecord.helpPersonnelName":{
					required:true
				},
				"helpPrecord.helpTime":{
					required:true
				},
				"helpPrecord.address":{
					required:true,
					maxlength:50
				},
				"helpPrecord.events":{
					maxlength:100
				}

			},
			messages:{
				"helpPrecord.helpPersonnelName":{
					required:"帮教人员姓名不能为空"
				},
				"helpPrecord.helpTime":{
					required: "帮教时间不能为空"
				},
				"helpPrecord.address":{
					required:"帮教地址不能为空",
					maxlength:$.format("帮教地址最多输入{0}个字符")
				}
				,
				"helpPrecord.events":{
					maxlength:$.format("帮教情况最多输入{0}个字符")
				}

			}
		});
		$("#helpPersonName").personnelComplete({
			url: "${path}/baseinfo/helpPersonnel/searchPersonInCharegeForAutoComplete.action",
			multiple: true,
			postDataFunction: function(){
			    var personnelId=$("#domainId").val();
			   	var  personnelType = $("#keyType").val();
			    return {personnelId:personnelId,personnelType:personnelType};
			}
		});
		<s:if test='!"add".equals(mode)'>
			var helpPersonnelName = $("#helpPersonnelName").val().split(",");
			for(var i=0;i<helpPersonnelName.length;i++){
				$("#helpPersonName").setPersonnelCompleteVal({value:helpPersonnelName[i],label:helpPersonnelName[i],desc:""});
			}
		</s:if>

	});

</script>