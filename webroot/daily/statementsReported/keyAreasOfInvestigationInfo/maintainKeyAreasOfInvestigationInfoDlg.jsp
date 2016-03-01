<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div id="dialog-form" class="container container_24">
	<s:if test='!"view".equals(mode)'>
	<form id="dailyLog-form" method="post" action="">
	</s:if>
	<input id="mode" name="mode" type="hidden" value="${mode}" />
	<input id="keyAreasOfInvestigationInfoId" name="keyAreasOfInvestigationInfo.id" type="hidden" value="${keyAreasOfInvestigationInfo.id}" />
	<input id="dailyDirectoryId" name="dailyDirectory.id" type="hidden" value="${dailyDirectory.id}" />
	<div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">地区名称：</label>
		</div>
		<div class="grid_10 lable-right">
			<input type="text" name="keyAreasOfInvestigationInfo.areaName" maxlength="40" id="areaName" class="form-txt"
				<s:if test='"view".equals(mode)'>readonly="readonly"</s:if> value="<s:property escape="true" value="keyAreasOfInvestigationInfo.areaName"/>"/>
		</div>
		<div class="grid_1">
		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		</div>
		<div class="grid_3 lable-right">
   			<label class="form-lb1">排查日期：</label>
   		</div>
   		<div class="grid_5 form-left" id="workTimeDiv">
   			<input type="text" name="keyAreasOfInvestigationInfo.investigationDate" id="investigationDate" maxlength="32"
  				readonly <s:if test='"view".equals(mode)'>disabled='true'</s:if> value='<s:date name="keyAreasOfInvestigationInfo.investigationDate" format="yyyy-MM-dd" />'
  				class="form-txt" />
   		</div>
   		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">警示或挂牌：</label>
		</div>
		<div class="grid_10 lable-right heightAuto">
			<input type="text" name="keyAreasOfInvestigationInfo.warningOrListing" maxlength="10" id="warningOrListing" class="form-txt"
				<s:if test='"view".equals(mode)'>disabled='true'</s:if>value="<s:property escape="true" value="keyAreasOfInvestigationInfo.warningOrListing"/>"/>
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">主要问题：</label>
		</div>
		<div class="grid_19 lable-right heightAuto"  >
			<textarea name="keyAreasOfInvestigationInfo.mainProblem" rows="5" style="width: 98%;margin-top:10px;border:1px solid #7F9DB9;"
					<s:if test='"view".equals(mode)'>readonly="readonly"</s:if> id="mainProblem" >${keyAreasOfInvestigationInfo.mainProblem}</textarea>
		</div>
		<div class="grid_1">
		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		</div>
		<div class='clearLine'></div>

		<div class="grid_4 lable-right">
			<label class="form-lbl">对策与措施：</label>
		</div>
		<div class="grid_19 lable-right heightAuto">
			<textarea name="keyAreasOfInvestigationInfo.policiesAndMeasures" rows="5" style="width: 98%;margin-top:10px;border:1px solid #7F9DB9;"
					<s:if test='"view".equals(mode)'>readonly="readonly"</s:if> id="policiesAndMeasures" >${keyAreasOfInvestigationInfo.policiesAndMeasures}</textarea>
		</div>
		<div class="grid_1">
		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		</div>
		<div class='clearLine'></div>

		<div class="grid_4 lable-right heightAuto">
			<label class="form-lbl">备注：</label>
		</div>
		<div class="grid_19 lable-right">
			<input type="hidden" id="realRemark" value="${keyAreasOfInvestigationInfo.remark}" name="keyAreasOfInvestigationInfo.remark"/>
			<textarea name="" rows="4" cols="80" class="form-txt" style="color:gray; width: 98%"
					<s:if test='"view".equals(mode)'>readonly</s:if> id="remark" 
					onblur="value=(this.value=='')?'请填写“整治结束”或“正在整治”':this.value;" onfocus="value=(this.value=='请填写“整治结束”或“正在整治”')?'':this.value;" ><s:if test='"add".equals(mode)'>请填写“整治结束”或“正在整治”</s:if>${keyAreasOfInvestigationInfo.remark}</textarea>
		</div>
		<div class='clearLine'></div>
	</div>
	<s:if test='!"view".equals(mode)'>
	</form>
	</s:if>
</div>


<script type="text/javascript">
$(document).ready(function(){
	<s:if test='!"view".equals(mode)'>
		if($("#realRemark").val() == "") {
			$("#remark").val("请填写“整治结束”或“正在整治”");
		}
	</s:if>
	$('#investigationDate').datePicker({
		yearRange: '1900:2030',
   		dateFormat: 'yy-mm-dd',
           maxDate:'+0d'});
	});

	<s:if test='"add".equals(mode)'>
	$("#dailyLog-form").attr("action","${path}/daily/keyAreasOfInvestigationInfoManage/addKeyAreasOfInvestigationInfo.action");
	</s:if>
	<s:if test='"edit".equals(mode)'>
	$("#dailyLog-form").attr("action","${path}/daily/keyAreasOfInvestigationInfoManage/editKeyAreasOfInvestigationInfo.action");
	</s:if>

	<s:if test='!"view".equals(mode)'>
	$("#dailyLog-form").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
		 if($("#remark").val() != '请填写“整治结束”或“正在整治”') {
			 $("#realRemark").val($("#remark").val());
		 }
         $(form).ajaxSubmit({
             success: function(data){
                     if(!data.id){
                    	 $.messageBox({
							message:data,
							level: "error"
						 });
                     	return;
                     }
        	   		 <s:if test='"add".equals(mode) || "copy".equals(mode) '>
		   		 		$("#keyAreasOfInvestigationInfoList").addRowData(data.id,data,"first");
						$.messageBox({message:"成功保存新排查信息!"});
				     </s:if>
				     <s:if test='"edit".equals(mode)'>
				        $("#keyAreasOfInvestigationInfoList").setRowData(data.id,data);
					    $.messageBox({message:"成功保存排查修改信息!"});
				     </s:if>
				     $("#keyAreasOfInvestigationInfoList").setSelection(data.id);
				     $("#dailyLogMaintanceDialog").dialog("close");
				     $("#keyAreasOfInvestigationInfoList").trigger("reloadGrid");
	      	   },
	      	   error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      alert("提交错误");
	      	   }
	       });
		},
		rules:{
				"keyAreasOfInvestigationInfo.areaName":{
					required:true,
					minlength:2,
					maxlength:40
					},
				"keyAreasOfInvestigationInfo.investigationDate":{
					required:true
				},
				"keyAreasOfInvestigationInfo.mainProblem":{
					required:true,
					maxlength:1000
				},
				"keyAreasOfInvestigationInfo.policiesAndMeasures":{
					required:true,
					maxlength:1000
				},
				"keyAreasOfInvestigationInfo.remark":{
					maxlength:1000
				}
			},
		messages:{
				"keyAreasOfInvestigationInfo.areaName":{
					required:"请输入地区名称",
					minlength:$.format("地区名称至少需要输入{0}个字符"),
					maxlength:$.format("地区名称最多需要输入{0}个字符")
					},
				"keyAreasOfInvestigationInfo.investigationDate":{
					required:"请选择排查日期"
				},
				"keyAreasOfInvestigationInfo.mainProblem":{
					required:"请输入主要问题",
					maxlength:$.format("主要问题最多需要输入{0}个字符")
				},
				"keyAreasOfInvestigationInfo.policiesAndMeasures":{
					required:"请输入对策与措施",
					maxlength:$.format("对策与措施最多需要输入{0}个字符")
				},
				"keyAreasOfInvestigationInfo.remark":{
					maxlength:$.format("备注最多需要输入{0}个字符")
				}
			}
	});
	</s:if>
</script>