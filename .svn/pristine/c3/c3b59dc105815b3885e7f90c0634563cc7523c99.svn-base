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
		<input type="hidden" name="environment.id" id="environmentId" value="${environment.id}" />
		<input type="hidden" id="basicId" name="environment.peopleAspiration.id" value="${peopleAspirations.id}"/>
 		<div class="grid_5 lable-right">        
			<label class="form-lbl">工程名称：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="environment.projectName" id="projectName"  maxlength="30" value="${environment.projectName}" 
				 class='form-txt' />
		</div>
		<div class="grid_5 lable-right">
			<em class="form-req"></em>
			<label class="form-lbl">受益人口：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" name="environment.beneficiaryNumber" id="beneficiaryNumber"  maxlength="10"  value="${environment.beneficiaryNumber}"
				class='form-txt'/>
		</div>
 		<div class='clearLine'>&nbsp;</div>
 		
 		
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">项目类别：</label>
	 	</div>
		<div class="grid_6 ">
		<select name="environment.projectCategory.id" id="projectCategory" class='form-txt '  <s:if test='"view".equals(mode)'>disabled</s:if>>
		   	<pop:PropertyDictReleationSelectTag name="@com.tianque.plugin.account.property.PropertyTypes@ENVIRONMENT_PROJECT"  defaultValue="${environment.projectCategory.id}" 
		   		reletionId="projectSubCategory"    reletionName="@com.tianque.plugin.account.property.PropertyTypes@ENVIRONMENT_PROJECT_SUB" id="projectCategory"/>
		
			</select>
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">治理类型：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="environment.projectSubCategory.id" id="projectSubCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@ENVIRONMENT_PROJECT_SUB"   defaultValue="${environment.projectSubCategory.id}"/>
			</select>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
			<div id="_other">
			<div class="grid_5 lable-right">
				<label class="form-lbl">其它：</label>
				</div>
			<div class="grid_18 heightAuto">
				<textarea  id="otherContent" name="environment.otherContent"  maxlength="50" class='form-txt' >${environment.otherContent}</textarea>
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_5 lable-right">
			<label class="form-lbl">建设性质：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="environment.buildType.id" id="buildType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@ENVIRONMENT_BUILD_TYPE"  	 defaultValue="${environment.buildType.id}"/>
			</select>
		</div>
		
		<div class="grid_5 lable-right">
		<em class="form-req"></em>
		<label class="form-lbl">治理数量：</label>
	 	</div>
		<div class="grid_6 ">
		 <input type="text"  name="environment.governNumber" id="governNumber"  maxlength="20" value="${environment.governNumber}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
				<label class="form-lbl">建设规模及建设内容：</label>
				</div>
		<div class="grid_18 heightAuto">
				<textarea  id="content" name="environment.content"  maxlength="50" class='form-txt' >${environment.content}</textarea>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设起点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="environment.fromAddress" id="fromAddress"  maxlength="60" value="${environment.fromAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设讫点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="environment.toAddress" id="toAddress"  maxlength="60" value="${environment.toAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
 		<div class="grid_5 lable-right">
			<label class="form-lbl">计划资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="environment.plannedInvestment" id="plannedInvestment"  maxlength="20" value="${environment.plannedInvestment}" 
				class='form-txt' />
		</div>
		
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">自筹资金(万元):</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="environment.selfFund" id="selfFund"  maxlength="20" value="${environment.selfFund}" 
				class='form-txt' />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">缺口资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="environment.gapFund" id="gapFund"  maxlength="20" value="${environment.gapFund}" 
				class='form-txt' readonly />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
 	</form>


<script type="text/javascript">
jQuery.validator.addMethod("validatorMoney", function (value,element){
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
						if($('#environmentId').val()=="")
							$.messageBox({message:"环境保护研究整理信息新增成功!"});
						else 
							$.messageBox({message:"环境保护研究整理信息修改成功!"});
						//$("#peopleaspirationsDialog").dialog("close");
						//onOrgChanged(userOrgId,isGrid());
				//		$("#issueList").trigger("reloadGrid");
						$('#environmentId').val(data.id);
						$("#maintainForm1").attr("action","${path}/threeRecords/environment/updateEnvironment.action");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"environment.beneficiaryNumber":{
				digits:true,
				range:[1,9999999999]
			},"environment.governNumber":{
				number:true
				//range:[1,9999999999]
			},"environment.plannedInvestment":{
				number:true,
				validatorMoney:true
			}
			,"environment.selfFund":{
				number:true,
				validatorMoney:true
			},"environment.gapFund":{
				number:true,
				validatorMoney:true
			},
			"environment.projectCategory.id":{
				required:true
			}
		},
		messages:{
			"environment.beneficiaryNumber":{
				digits:'受益人口只能输入1到1,9999999999之间的整数',
				range:$.format('受益人口只能输入{0}到{1}之间的整数')
			}
			,"environment.governNumber":{
				number:'治理数量只能输入1到1,9999999999之间的数'
				//range:$.format('治理数量只能输入{0}到{1}之间的整数')
			},"environment.plannedInvestment":{
				number:'计划投资只能输入0到9999999999之间的数',
				validatorMoney:'计划投资只能输入0到9999999999之间的数,小数保留4位有效数'
			}
			,"environment.selfFund":{
				number:'自筹资金只能输入0到9999999999之间的数',
				validatorMoney:'自筹资金只能输入0到9999999999之间的数,小数保留4位有效数'
			},"environment.gapFund":{
				number:'缺口资金只能输入1到1,9999999999之间的数',
				validatorMoney:'缺口资金只能输入0到9999999999之间的数,小数保留4位有效数'
			},
			"environment.projectCategory.id":{
				required:"请选择项目类别"
			}
			
			
		},
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#maintainForm1").attr("action","${path}/threeRecords/environment/addEnvironment.action");
</s:if>
<s:if test='"edit".equals(mode)'>
	if($("#environmentId").val()=="")
		 $("#maintainForm1").attr("action","${path}/threeRecords/environment/addEnvironment.action");
	else
   		 $("#maintainForm1").attr("action","${path}/threeRecords/environment/updateEnvironment.action");
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
	
	 if($(this).children('option:selected').attr("internalid")==5){
		$("#_other").show();
	}else{
		$("#projectSubCategory").val("");
		$("#_other").hide();
		$("#otherContent").val("");
	}
})
if($("#projectCategory").children('option:selected').attr("internalid")==5){
	$("#_other").show();
}else{
	var projectSubCategory = '${environment.projectSubCategory.id}';
	$("#projectSubCategory").children('option').each(function(){
		if($(this).attr("id")>0){
			$(this).remove();
		}
		$("#projectSubCategory").val(projectSubCategory); 
	})
}

})

</script>


