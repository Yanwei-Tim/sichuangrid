<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="dialog-form" title="" class="container container_24">
	<form id="serviceRecordForm" method="post" action="${path}/baseinfo/mentalPatientManage/saveServiceSupervisionMeasures.action" >
	<pop:token />
	<input type="hidden" name="serviceSupervisionMeasures.id" value="${serviceSupervisionMeasures.id}">
	<input type="hidden" name="serviceSupervisionMeasures.type" value="${serviceSupervisionMeasures.type}">
	<input type="hidden" name="serviceSupervisionMeasures.mentalPatient.id" value="${population.id }">
	<div class="grid_4 lable-right"><c:if test='${mode!="view"}'>
		<em class="form-req">*</em></c:if> 
		<label class="form-lbl">时间：</label>
	</div>
	<div class="grid_20">
		<input type="text" id="serviceSupervisionMeasures_upTime" <c:if test='${mode=="view"}'>disabled="disabled"</c:if> name="serviceSupervisionMeasures.upTime" readonly="readonly" value="<fmt:formatDate value="${serviceSupervisionMeasures.upTime}" type="date" pattern="yyyy-MM-dd" />" class="form-txt {required:true,messages:{required:'时间必须输入'}}">
	</div>
	<div class="grid_4 lable-right"><c:if test='${mode!="view" }'>
		<em class="form-req">*</em></c:if> 
		<label class="form-lbl">监管人员：</label>
	</div>
	<div class="grid_20">
		<input type="text" name="serviceSupervisionMeasures.supervisepersonnel" <c:if test='${mode=="view"}'>disabled="disabled"</c:if> maxlength="20" value="${serviceSupervisionMeasures.supervisepersonnel}" class="form-txt {required:true,maxlength:20,messages:{required:'监管人员必须输入',maxlength:$.format('监管人员输入最多需要输入{0}个字符')}}">
	</div>
	<div class='clearLine'></div>
	<div  class="grid_4 lable-right"><c:if test='${mode!="view"}'>
		<em class="form-req">*</em></c:if> 
		<label class="form-lbl">监管措施：</label>
	</div>
	<div class="grid_20">
		<textarea style="width: 97%; height: 120px;" name="serviceSupervisionMeasures.superviseinfo" <c:if test='${mode=="view"}'>disabled="disabled"</c:if> maxlength="200" class="form-txt {required:true,maxlength:200,messages:{required:'监管措施必须输入',maxlength:$.format('监管措施最多需要输入{0}个字符')}}">${serviceSupervisionMeasures.superviseinfo}</textarea>
	</div>
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#serviceSupervisionMeasures_upTime").datePicker({yearRange: '1900:2030', dateFormat: 'yy-mm-dd',maxDate:'+0d'});
	$("#serviceRecordForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				async:false,
				success:function(data){
					if(!data.id){
	       	 			$.messageBox({ 
							evel: "error"
			 			});
	        			return;
					}
	                if("add"=="${mode}"){
	                 	 $("#serviceRecordList").trigger("reloadGrid");
	                	 $.messageBox({message:"服务监管措施新增成功！"});
		        		$("#serviceRecordDialog").dialog("close");
	                }
	                if("edit"=="${mode}"){
					  	$("#serviceRecordList").trigger("reloadGrid");
	                	$.messageBox({message:"服务监管措施修改成功！"});
		        		$("#serviceRecordDialog").dialog("close");
	                }
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
		},
		messages:{
		}
	});
});
</script>