<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="" class="container container_24">
	<form name="floorpersonForm" id="floorpersonForm" method="post" action="">
		<input type="hidden" id="domainId" name="floorperson.placeId" value="${placeId}"/>
		<input type="hidden" id="keyType" name="floorperson.placeType" value="${placeType}"/>
		<input type="hidden" id="" name="floorperson.id" value="${floorperson.id}"/>
		<input type="hidden" id="personInChargeName" name="personInChargeName" value="${floorperson.personInChargeName}"/>
		<input type="hidden" id="placeName" name="floorperson.placeName" value=""/>
		<input type="hidden" id="placeTypeNames" name="floorperson.placeTypeName" value=""/>


		<div class="grid_6 lable-right">
			<label class="form-lbl">巡场人员：</label>
		</div>
		<div class="grid_15 form-left heightAuto" id="selectPerson" style="line-height:20px;">
			<input name="floorperson.personInChargeName" id="personName" class="form-txt">
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">巡场时间：</label>
		</div>
		<div class="grid_15">
			<input type="text" id="floorpersonsTime" name="floorperson.floorpersonsTime" value="<s:date name="floorperson.floorpersonsTime" format="yyyy-MM-dd"/>" class="form-txt" readonly="readonly" <s:if test='"view".equals(mode)'>disabled='true'</s:if>/>
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">巡场地点：</label>
		</div>
		<div class="grid_15">
			<input type="text" id="floorpersonAddress"  name="floorperson.address" value="${floorperson.address}" class="form-txt" <s:if test='"view".equals(mode)'>disabled='true'</s:if>/>
		</div>
		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_6 lable-right">
			<label class="form-lbl">巡场情况：</label>
		</div>
		<div class="grid_15">
			<textarea rows="10" cols="20" id="floorpersonEvents"  name="floorperson.events"  class="form-txt" <s:if test='"view".equals(mode)'>disabled='true'</s:if>>${floorperson.events}</textarea>
		</div>
	</form>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		$("#placeTypeNames").val($("#placeTypeName").val());
		$("#placeName").val($("#name").val());
		$('#floorpersonsTime').datePicker({
			yearRange: '1900:2020',
			dateFormat: 'yy-mm-dd',
	        maxDate:'+0d'});
	<s:if test='"add".equals(mode)'>
		$("#floorpersonForm").attr("action","${path}/baseinfo/floorpersons/addFloorperson.action");
	</s:if>
	<s:if test='"edit".equals(mode)'>
		$("#floorpersonForm").attr("action","${path}/baseinfo/floorpersons/updateFloorperson.action");
	</s:if>
		$("#floorpersonForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form){
				$(form).ajaxSubmit({
					success:function(data){
						<s:if test='"add".equals(mode) || "copy".equals(mode) '>
							$("#floorpersonList").addRowData(data.id, data, "first");
							$.messageBox({message:"已成功保存新信息"});
							$("#floorpersonDialog").dialog("close");
						</s:if>
						<s:if test='"edit".equals(mode)'>
							$("#floorpersonList").setRowData(data.id,data);
							$.messageBox({message:"已成功修改信息"});
							$("#floorpersonDialog").dialog("close");
						</s:if>
					    $("#floorpersonList").setSelection(data.id);
					}
				})
			},
			rules:{
				"floorperson.personInChargeName":{
					required:true
				},
				"floorperson.floorpersonsTime":{
					required:true
				},
				"floorperson.address":{
					required:true,
					maxlength:50
				},
				"floorperson.events":{
					maxlength:100
				}

			},
			messages:{
				"floorperson.personInChargeName":{
					required:"综治负责人不能为空"
				},
				"floorperson.floorpersonsTime":{
					required: "巡场时间不能为空"
				},
				"floorperson.address":{
					required:"巡场地址不能为空",
					maxlength:$.format("巡场地址最多输入{0}个字符")
				}
				,
				"floorperson.events":{
					maxlength:$.format("巡场情况最多输入{0}个字符")
				}

			}
		});
		$("#personName").personnelComplete({
			url: "${path}/baseinfo/personInCharegeManage/findPersonInCharegeForList.action",
			multiple: true,
			postDataFunction: function(){
			    var placeId=$("#domainId").val();
			   	var  placeType = $("#keyType").val();
			    return {placeId:placeId,placeType:placeType};
			}
		});
		<s:if test='!"add".equals(mode)'>
			var personInChargeName = $("#personInChargeName").val().split(",");
			for(var i=0;i<personInChargeName.length;i++){
				$("#personName").setPersonnelCompleteVal({value:personInChargeName[i],label:personInChargeName[i],desc:""});
			}
		</s:if>

	});

</script>