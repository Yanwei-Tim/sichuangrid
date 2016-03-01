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
		<input type="hidden" name="other.id" id="otherId" value="${other.id}" />
		<input type="hidden" id="basicId" name="other.peopleAspiration.id" value="${peopleAspirations.id}"/>
 		<div class="grid_5 lable-right">        
			<label class="form-lbl">工程名称：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="other.projectName" id="projectName"  maxlength="30" value="${other.projectName}" 
				 class='form-txt' />
		</div>
 	
 	
 		<div class="grid_5 lable-right">        
			<label class="form-lbl">受益人口：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="other.beneficiaryNumber" id="beneficiaryNumber"  maxlength="30" value="${other.beneficiaryNumber}" 
				 class='form-txt' />
		</div>
 		<div class='clearLine'>&nbsp;</div>
 		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设性质：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="other.buildType.id" id="buildType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_OTHER_BUILD_TYPE"  showInternalId="true"	 defaultValue="${other.buildType.id}"/>
			</select>
		</div>
		<div id="_otherBuildType">
			<div class="grid_6">
				<input type="text"  name="other.otherBuildTypeContent" id="otherBuildTypeContent"  maxlength="30" value="${other.otherBuildTypeContent}" 
					 class='form-txt' />
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
		
 		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">项目类别：</label>
	 	</div>
		<div class="grid_6 ">
		<select name="other.projectCategory.id" id="projectCategory" class='form-txt '  <s:if test='"view".equals(mode)'>disabled</s:if>>
			<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@OTHER_PROJECT"  defaultValue="${other.projectCategory.id}" 
		   		 showInternalId="true"	/>
			
			</select>
			
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div id="_project">
		<div class="grid_5 lable-right">
			<label class="form-lbl">工程规模及内容：</label>
			</div>
		<div class="grid_18 heightAuto">
			<textarea  id="scopeContent" name="other.scopeContent"  maxlength="50" class='form-txt' >${other.scopeContent}</textarea>
		</div>
		<div class='clearLine'>&nbsp;</div>
		</div>
		
		<div id="_other">
			<div class="grid_5 lable-right">
				<label class="form-lbl">其它：</label>
				</div>
			<div class="grid_18 heightAuto">
				<textarea  id="otherContent" name="other.otherContent"  maxlength="50" class='form-txt' >${other.otherContent}</textarea>
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
 		<div class="grid_5 lable-right">
			<label class="form-lbl">建设起点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="other.fromAddress" id="fromAddress"  maxlength="60" value="${other.fromAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设讫点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="other.toAddress" id="toAddress"  maxlength="60" value="${other.toAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
 		<div class="grid_5 lable-right">
			<label class="form-lbl">计划资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="other.plannedInvestment" id="plannedInvestment"  maxlength="20" value="${other.plannedInvestment}" 
				class='form-txt' />
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">自筹资金(万元):</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="other.selfFund" id="selfFund"  maxlength="20" value="${other.selfFund}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>	
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">缺口资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="other.gapFund" id="gapFund"  maxlength="20" value="${other.gapFund}" 
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
						if($('#otherId').val()=="")
							$.messageBox({message:"其他类研究整理信息新增成功!"});
						else 
							$.messageBox({message:"其他类研究整理信息修改成功!"});
						//$("#peopleaspirationsDialog").dialog("close");
						$('#otherId').val(data.id);
						//onOrgChanged(userOrgId,isGrid());
					//	$("#issueList").trigger("reloadGrid");
						$("#maintainForm1").attr("action","${path}/threeRecords/other/updateOther.action");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"other.beneficiaryNumber":{
			digits:true,
			range:[1,9999999999]
			},
			"other.plannedInvestment":{
				number:true,
				validatorFloat:true
			}
			,"other.selfFund":{
				number:true,
				validatorFloat:true
			},"other.gapFund":{
				number:true,
				validatorFloat:true
			},
			"other.projectCategory.id":{
				required:true
			}
		},
		messages:{
			"other.beneficiaryNumber":{
			digits:'受益人口只能输入1到1,9999999999之间的整数',
			range:$.format('受益人口只能输入{0}到{1}之间的整数')
			},
			"other.plannedInvestment":{
				number:'计划投资只能输入0到9999999999之间的数',
				validatorFloat:'计划投资只能输入0到9999999999之间的数,小数保留4位有效数'
			}
			,"other.selfFund":{
				number:'自筹资金只能输入0到9999999999之间的数',
				validatorFloat:'自筹资金只能输入0到9999999999之间的数,小数保留4位有效数'
			},"other.gapFund":{
				number:'缺口资金只能输入0到9999999999之间的数',
				validatorFloat:'缺口资金只能输入0到9999999999之间的数,小数保留4位有效数'
			},
			"other.projectCategory.id":{
				required:"请选择项目类别"
			}
			
			
		},
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#maintainForm1").attr("action","${path}/threeRecords/other/addOther.action");
</s:if>
<s:if test='"edit".equals(mode)'>
	if($("#otherId").val()=="")
		 $("#maintainForm1").attr("action","${path}/threeRecords/other/addOther.action");
	else
   		 $("#maintainForm1").attr("action","${path}/threeRecords/other/updateOther.action");
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
$("#_project").hide();
$("#projectCategory").change(function(){
	if($(this).children('option:selected').attr("internalid")==1){
		$("#_project").show();
		$("#_other").hide();
		$("#otherContent").val('');
	}else if($(this).children('option:selected').attr("internalid")==2){
		$("#_project").hide();
		$("#scopeContent").val('');
		$("#_other").show();
	}
})
if($("#projectCategory").children('option:selected').attr("internalid")==1){
	$("#_project").show();
}else if($("#projectCategory").children('option:selected').attr("internalid")==2){
	$("#_other").show();
}

$("#_otherBuildType").hide();
$("#buildType").change(function(){
	if($(this).children('option:selected').attr("internalid")==5){
		$("#_otherBuildType").show();
	}else {
		$("#_otherBuildType").hide();
	}
})
if($("#buildType").children('option:selected').attr("internalid")==5){
	$("#_otherBuildType").show();
}

})

</script>


