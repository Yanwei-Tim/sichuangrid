<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%
if(ThreadVariable.getUser()!=null){
	request.setAttribute("NAME",ThreadVariable.getUser().getName());
}
%>

	<form id="maintainForm1" method="post" action="">
		<pop:token />
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="medical.id" id="medicalId" value="${medical.id}" />
		<input type="hidden" id="basicId" name="medical.peopleAspiration.id" value="${peopleAspirations.id}"/>
 		<div class="grid_5 lable-right">        
			<label class="form-lbl">工程名称：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="medical.projectName" id="projectName"  maxlength="30" value="${medical.projectName}" 
				 class='form-txt' />
		</div>
		<div class="grid_5 lable-right">
			
			<label class="form-lbl">受益人口：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" name="medical.beneficiaryNumber" id="beneficiaryNumber"  maxlength="10"  value="${medical.beneficiaryNumber}"
				class='form-txt'/>
		</div>
 		<div class='clearLine'>&nbsp;</div>
 		<div class="grid_5 lable-right">
			<label class="form-lbl">建设性质：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="medical.buildType.id" id="buildType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@MEDICAL_BUILD_TYPE"  	 defaultValue="${medical.buildType.id}"/>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
 		
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">项目类别：</label>
	 	</div>
		<div class="grid_6 ">
		<select name="medical.projectCategory.id" id="projectCategory" class='form-txt '  <s:if test='"view".equals(mode)'>disabled</s:if>>
		   	<pop:PropertyDictReleationSelectTag name="@com.tianque.plugin.account.property.PropertyTypes@MEDICAL_PROJECT"  defaultValue="${medical.projectCategory.id}" 
		   		reletionId="projectSubCategory"    reletionName="@com.tianque.plugin.account.property.PropertyTypes@MEDICAL_PROJECT_SUB" id="projectCategory"/>
		
			</select>
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">项目内容：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="medical.projectSubCategory.id" id="projectSubCategory"  class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@MEDICAL_PROJECT_SUB"  	 defaultValue="${medical.projectSubCategory.id}"/>
			</select>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		
		<div id="_other">
			<div class="grid_5 lable-right">
				<label class="form-lbl">其它：</label>
				</div>
			<div class="grid_18 heightAuto">
				<textarea  id="otherContent" name="medical.otherContent"  maxlength="50" class='form-txt' >${medical.otherContent}</textarea>
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设起点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="medical.fromAddress" id="fromAddress"  maxlength="60" value="${medical.fromAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设讫点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="medical.toAddress" id="toAddress"  maxlength="60" value="${medical.toAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<em class="form-req"></em>
			<label class="form-lbl">建设工程量：</label>
	 	</div>
		<div class="grid_6 ">
		 <input type="text"  name="medical.buildArea" id="buildArea"  maxlength="20" value="${medical.buildArea}" 
				class='form-txt' />
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">设备：</label>
	 	</div>
		<div class="grid_6 ">
			<input type="text"  name="medical.equipment" id="equipment"  maxlength="20" value="${medical.equipment}" 
				class='form-txt' />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		
		
		
 		<div class="grid_5 lable-right">
			<label class="form-lbl">计划资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="medical.plannedInvestment" id="plannedInvestment"  maxlength="20" value="${medical.plannedInvestment}" 
				class='form-txt' />
		</div>
		
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">自筹资金(万元):</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="medical.selfFund" id="selfFund"  maxlength="20" value="${medical.selfFund}" 
				class='form-txt' />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">缺口资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="medical.gapFund" id="gapFund"  maxlength="20" value="${medical.gapFund}" 
				class='form-txt' readonly/>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
 	</form>


<script type="text/javascript">
jQuery.validator.addMethod("validatorFloat", function (value,element){
	if(value==null||value==undefined||value==""){return true}
	var pattern = /^\d{1,9}(\.\d{1,4})?$/;
	return pattern.test(value) ; 
});


$(document).ready(function(){
	$("#maintainForm1").formValidate({
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
// 						$("#peopleAspirationsList").addRowData(data.id,data,"first");
						if($('#medicalId').val()=="")
							$.messageBox({message:"医疗卫生研究整理信息新增成功!"});
						else 
							$.messageBox({message:"医疗卫生研究整理信息修改成功!"});
						//$("#peopleaspirationsDialog").dialog("close");
						//onOrgChanged(userOrgId,isGrid());
					//	$("#issueList").trigger("reloadGrid");
						$('#medicalId').val(data.id);
						$("#maintainForm1").attr("action","${path}/threeRecords/medical/updateMedical.action");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"medical.beneficiaryNumber":{
				digits:true,
				range:[1,9999999999]
			}
			,"medical.equipment":{
				digits:true,
				range:[1,9999999999]
			},"medical.buildArea":{
				number:true,
				validatorFloat:true
			},"medical.plannedInvestment":{
				number:true,
				validatorFloat:true
			}
			,"medical.selfFund":{
				number:true,
				validatorFloat:true
			},"medical.gapFund":{
				number:true,
				validatorFloat:true
			},
			"medical.projectCategory.id":{
				required:true
			}
		},
		messages:{
			"medical.beneficiaryNumber":{
				digits:'受益人口只能输入1到1,9999999999之间的整数',
				range:$.format('受益人口只能输入{0}到{1}之间的整数')
			}
			,"medical.equipment":{
				digits:'设备只能输入1到1,9999999999之间的整数',
				range:$.format('设备只能输入{0}到{1}之间的整数')
			},"medical.buildArea":{
				number:'建设工程量只能输入1到1,9999999999之间的数',
				validatorFloat:$.format('建设工程量只能输入{0}到{1}之间的数,小数保留2位有效数')
			},"medical.plannedInvestment":{
				number:'计划投资只能输入0到9999999999之间的数',
				validatorFloat:'计划投资只能输入0到9999999999之间的数,小数保留4位有效数'
			}
			,"medical.selfFund":{
				number:'自筹资金只能输入0到9999999999之间的数',
				validatorFloat:'自筹资金只能输入0到9999999999之间的数,小数保留4位有效数'
			},"medical.gapFund":{
				number:'缺口资金只能输入0到9999999999之间的数',
				validatorFloat:'缺口资金只能输入0到9999999999之间的数,小数保留4位有效数'
			},
			"medical.projectCategory.id":{
				required:"请选择项目类别"
			}
			
			
		},
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#maintainForm1").attr("action","${path}/threeRecords/medical/addMedical.action");
</s:if>
<s:if test='"edit".equals(mode)'>
	if($("#medicalId").val()=="")
		 $("#maintainForm1").attr("action","${path}/threeRecords/medical/addMedical.action");
	else
   		 $("#maintainForm1").attr("action","${path}/threeRecords/medical/updateMedical.action");
</s:if>

$("#plannedInvestment").blur(function(){
	if($("#plannedInvestment").val()!=''&&$("#selfFund").val()!=''){
		$("#gapFund").val(($("#plannedInvestment").val()*10000-$("#selfFund").val()*10000)/10000);
	}
	if($("#plannedInvestment").val()==''||$("#selfFund").val()==''){
		$("#gapFund").val(0);
	}
})
$("#selfFund").blur(function(){
	if($("#plannedInvestment").val()!=''&&$("#selfFund").val()!=''){
		$("#gapFund").val(($("#plannedInvestment").val()*10000-$("#selfFund").val()*10000)/10000);
	}
	if($("#plannedInvestment").val()==''||$("#selfFund").val()==''){
		$("#gapFund").val(0);
	}
})
$("#_other").hide();
$("#projectCategory").change(function(){
	 if($(this).children('option:selected').attr("internalid")==6){
		$("#_other").show();
	}else{
		$("#_other").hide();
		$("#otherContent").val("");
	}
})
if($("#projectCategory").children('option:selected').attr("internalid")==6){
	$("#_other").show();
}
var projectSubCategory = '${medical.projectSubCategory.id}';
$("#projectSubCategory").children('option').each(function(){
	if($(this).attr("id")>0){
		$(this).remove();
	}
	$("#projectSubCategory").val(projectSubCategory); 
	})
})


</script>


