<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>

<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />

<div id="dialog-form" title="工作日志信息维护" class="container container_24">
	<s:if test='!"view".equals(mode)'>
	<form id="maintainForm" method="post" action="">
	</s:if>
	<input id="mode" name="mode" type="hidden" value="${mode}" /> 
	<input id="workDiaryId" name="workDiary.id" type="hidden" value="${workDiary.id}" />
	<input id="organizationId" name="workDiary.organization.id" type="hidden" value="${workDiary.organization.id}" />
	
	<div>
		<div class="grid_4 lable-right">
  			<label class="form-lbl">工作人员：</label>
  		</div>
  		<div class="grid_7 form-left">
  			<input type="text" name="workDiary.workUserName" id="workUserName" maxlength="20"
	  				<s:if test='"view".equals(mode)'>disabled='true'</s:if> value="<s:property escape="true" value="workDiary.workUserName"/>"
	  				class="form-txt" />
  		</div>
  		<div class="grid_1">
   		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
   		</div>
   		
   		<s:if test='"edit".equals(mode)'>
	   		<div class="grid_4 lable-right">
	  			<label class="form-lbl">日志类型：</label>
	  		</div>
	  		<div class="grid_7 form-left">
	  			<input name="workDiary.diaryType.id" type="hidden" value="${workDiary.diaryType.id}" />
	  			<select name="workDiary.diaryType.id" id="diaryType" class="form-txt" disabled>
	   				<s:iterator value="workDiaryTypes">
						<s:if test="@com.tianque.domain.property.WorkDiaryTypes@OTHER.equals(displayName)">
							<option value="<s:property value="id"/>"><s:property value="displayName"/></option>
						</s:if>
	   				</s:iterator>
				</select>
	  		</div>
   		</s:if>
   		<s:else>
			<div class="grid_4 lable-right">
	  			<label class="form-lbl">日志类型：</label>
	  		</div>
	  		<div class="grid_7 form-left">
	  			<select name="workDiary.diaryType.id" id="diaryType" class="dialogtip form-txt" title="">
	   				<s:iterator value="workDiaryTypes">
   						<s:if test="@com.tianque.domain.property.WorkDiaryTypes@OTHER.equals(displayName)">
   							<option value="<s:property value="id"/>"><s:property value="displayName"/></option>
   						</s:if>
	   				</s:iterator>
				</select>
	  		</div>
  		</s:else>
  		<div class="grid_1">
   		<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
   		</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">工作时间：</label>
   		</div>
   		<div class="grid_7 form-left" id="workTimeDiv">
   			<input type="text" name="workDiary.workTime" id="workTime" maxlength="32"
  				readonly <s:if test='"view".equals(mode)'>disabled='true'</s:if> value='<s:date name="workDiary.workTime" format="yyyy-MM-dd" />'
  				class="form-txt" />
   		</div>
   		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
   		<div class="clearLine">&nbsp;</div>
   		
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">地点：</label>
   		</div>
   		<div class="grid_19 form-left">
   			<input type="text" name="workDiary.workPlace" id="workPlace" maxlength="50" value="<s:property escape="true" value="workDiary.workPlace"/>" 
   				<s:if test='"view".equals(mode)'>disabled='true'</s:if> class="form-txt" style="width: 98%"/>
   		</div>
   		<div class="grid_1"><s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if></div>
   		<div class="clearLine">&nbsp;</div>
   		
   		<div class="grid_4 lable-right">
			<label class="form-lbl">工作内容：</label>
		</div>
		<div class="grid_19 form-left">
			<textarea name="workDiary.workContent" rows="13" cols="80" class="form-txt" style="width: 98%"
				<s:if test='"view".equals(mode)'>disabled='true'</s:if> id="workContent" ><s:property value="workDiary.workContent" escape="true" /></textarea>
		</div>
		<div class="grid_1">
			<s:if test='!"view".equals(mode)'><em class="form-req">*</em></s:if>
		</div> 
		<div class='clearLine'></div>
   		
	</div>
	<s:if test='!"view".equals(mode)'>
	</form>
	</s:if>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$('#workTime').datePicker({
		yearRange: '1900:2030',
   		dateFormat: 'yy-mm-dd',
           maxDate:'+0d'});

	$("#maintainForm").attr("action","${path}/dailyLog/workDiaryManage/addWorkDiary.action");
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form) {
         $(form).ajaxSubmit({
             success: function(data){
                     if(!data.id){
                    	 $.messageBox({
							message:data,
							level: "error"							
						 });
                     	return;
                     }
				    $.messageBox({message:"成功保存新工作日志信息!"});
					$("#shortCutDialog").dialog("close");
      	   },
      	   error: function(XMLHttpRequest, textStatus, errorThrown){
      	      alert("提交错误");
      	   }
         });
	},
    rules:{
			"workDiary.workUserName":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
				},
			"workDiary.diaryType.id":{
				required:true
			},
			"workDiary.workTime":{
				required:true
			},
			"workDiary.workPlace":{
				required:true,
				maxlength:50
			},
			"workDiary.workContent":{
				required:true,
				maxlength:200
			}
		},
	messages:{
			"workDiary.workUserName":{
				required:"请输入工作人员",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("姓名至少需要输入{0}个字符"),
				maxlength:$.format("姓名最多需要输入{0}个字符")
				},
			"workDiary.diaryType.id":{
				required:"请选择日志类型"
			},
			"workDiary.workTime":{
				required:"请输入时间"
			},
			"workDiary.workPlace":{
				required:"请输入地点",
				maxlength:$.format("地点最多需要输入{0}个字符")
			},
			"workDiary.workContent":{
				required:"请输入内容",
				maxlength:$.format("内容最多需要输入{0}个字符")
			}
		}
	});
})
</script>
