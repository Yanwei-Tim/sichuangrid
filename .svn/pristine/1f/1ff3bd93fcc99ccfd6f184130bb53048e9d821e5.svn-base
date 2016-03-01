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
		<input type="hidden" name="town.id" id="townId" value="${town.id}" />
		<input type="hidden" id="basicId" name="town.peopleAspiration.id" value="${peopleAspirations.id}"/>
 		<div class="grid_5 lable-right">        
			<label class="form-lbl">工程名称：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="town.projectName" id="projectName"  maxlength="30" value="${town.projectName}" 
				 class='form-txt' />
		</div>
		<div class="grid_5 lable-right">
			<em class="form-req"></em>
			<label class="form-lbl">受益人口：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" name="town.beneficiaryNumber" id="beneficiaryNumber"  maxlength="10"  value="${town.beneficiaryNumber}"
				class='form-txt'/>
		</div>
 		<div class='clearLine'>&nbsp;</div>
 		
 		
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">项目类别：</label>
	 	</div>
		<div class="grid_6 ">
		<select name="town.projectCategory.id" id="projectCategory" class='form-txt '  <s:if test='"view".equals(mode)'>disabled</s:if>>
		   	<pop:PropertyDictReleationSelectTag name="@com.tianque.plugin.account.property.PropertyTypes@TOWN_PROJECT"  defaultValue="${town.projectCategory.id}" 
		   		reletionId="projectSubCategory"    reletionName="@com.tianque.plugin.account.property.PropertyTypes@TOWN_PROJECT_SUB" id="projectCategory"/>
		
			</select>
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设类型：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="town.projectSubCategory.id" id="projectSubCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@TOWN_PROJECT_SUB"  	 defaultValue="${town.projectSubCategory.id}"/>
			</select>
		</div>
		<div id="_securityType">
			<div class="grid_5 lable-right">
				<label class="form-lbl">安保设施类型：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="town.securityType.id" id="securityType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@TOWN_SECURITY_TYPE"  	 defaultValue="${town.securityType.id}"/>
				</select>
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div id="_other">
			<div class="grid_5 lable-right">
				<label class="form-lbl">其它：</label>
				</div>
			<div class="grid_18 heightAuto">
				<textarea  id="otherContent" name="town.otherContent"  maxlength="50" class='form-txt' >${town.otherContent}</textarea>
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设性质：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="town.buildType.id" id="buildType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@TOWN_PROJECT_BUILD_TYPE"  	 defaultValue="${town.buildType.id}"/>
			</select>
		</div>
		
		<div class="grid_5 lable-right">
		<em class="form-req"></em>
		<label class="form-lbl">项目库编号：</label>
	 	</div>
		<div class="grid_6 ">
		 <input type="text"  name="town.projectNumber" id="projectNumber"  maxlength="20" value="${town.projectNumber}" 
				class='form-txt' />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设起点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="town.fromAddress" id="fromAddress"  maxlength="60" value="${town.fromAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设讫点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="town.toAddress" id="toAddress"  maxlength="60" value="${town.toAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
 		<div class="grid_5 lable-right">
			<label class="form-lbl">数量：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="town.scopeNumber" id="scopeNumber"  maxlength="20" value="${town.scopeNumber}" 
				class='form-txt' />
		</div>
		
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">面积:</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="town.area" id="area"  maxlength="20" value="${town.area}" 
				class='form-txt' />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">里程：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="town.mileage" id="mileage"  maxlength="20" value="${town.mileage}" 
				class='form-txt' />
		</div>
		
 		<div class="grid_5 lable-right">
			<label class="form-lbl">计划资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="town.plannedInvestment" id="plannedInvestment"  maxlength="20" value="${town.plannedInvestment}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">自筹资金(万元):</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="town.selfFund" id="selfFund"  maxlength="20" value="${town.selfFund}" 
				class='form-txt' />
		</div>
		
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">缺口资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="town.gapFund" id="gapFund"  maxlength="20" value="${town.gapFund}" 
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
						if($('#townId').val()=="")
							$.messageBox({message:"环境保护研究整理信息新增成功!"});
						else 
							$.messageBox({message:"环境保护研究整理信息修改成功!"});
						//$("#peopleaspirationsDialog").dialog("close");
						$('#townId').val(data.id);
						//onOrgChanged(userOrgId,isGrid());
						//$("#issueList").trigger("reloadGrid");
						$("#maintainForm1").attr("action","${path}/threeRecords/town/updateTown.action");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"town.beneficiaryNumber":{
				digits:true,
				range:[1,9999999999]
			},"town.mileage":{
				number:true,
				validatorFloat:true
			}
			,"town.scopeNumber":{
				digits:true,
				range:[1,9999999999]
			},"town.area":{
				number:true,
				validatorFloat:true
			},"town.plannedInvestment":{
				number:true,
				validatorFloat:true
			}
			,"town.selfFund":{
				number:true,
				validatorFloat:true
			},"town.gapFund":{
				number:true,
				validatorFloat:true
			},
			"town.projectCategory.id":{
				required:true
			}
		},
		messages:{
			"town.beneficiaryNumber":{
				digits:'受益人口只能输入1到1,9999999999之间的整数',
				range:$.format('受益人口只能输入{0}到{1}之间的整数')
			}
			,"town.mileage":{
				number:'里程只能输入1到1,9999999999之间的整数',
				validatorFloat:$.format('里程只能输入{0}到{1}之间的数,小数保留2位有效数')
			}
			,"town.scopeNumber":{
				digits:'数量只能输入1到1,9999999999之间的整数',
				range:$.format('数量只能输入{0}到{1}之间的整数')
			},"town.area":{
				number:'面积只能输入1到1,9999999999之间的整数',
				validatorFloat:$.format('面积只能输入{0}到{1}之间的数,小数保留2位有效数')
			},"town.plannedInvestment":{
				number:'计划投资只能输入0到9999999999之间的数',
				validatorFloat:$.format('计划投资只能输入0到9999999999之间的数,小数保留4位有效数')
			}
			,"town.selfFund":{
				number:'自筹资金只能输入0到9999999999之间的数',
				validatorFloat:$.format('自筹资金只能输入0到9999999999之间的数,小数保留4位有效数')
			},"town.gapFund":{
				number:'缺口资金只能输入0到9999999999之间的数',
				validatorFloat:$.format('缺口资金只能输入0到9999999999之间的数,小数保留4位有效数')
			},
			"town.projectCategory.id":{
				required:"请选择项目类别"
			}
			
			
		},
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#maintainForm1").attr("action","${path}/threeRecords/town/addTown.action");
</s:if>
<s:if test='"edit".equals(mode)'>
	if($("#townId").val()=="")
		 $("#maintainForm1").attr("action","${path}/threeRecords/town/addTown.action");
	else
   		 $("#maintainForm1").attr("action","${path}/threeRecords/town/updateTown.action");
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
$("#_securityType").hide();
$("#projectCategory").change(function(){
	$("#_other").hide();
	$("#_securityType").hide();
	 if($(this).children('option:selected').attr("internalid")==8){
		$("#_other").show();
		$("#securityType").val("");
	}else if($(this).children('option:selected').attr("internalid")==2){
		$("#_securityType").show();
		$("#otherContent").val("");
	}else{
		$("#securityType").val("");
		$("#otherContent").val("");
	}
})
if($("#projectCategory").children('option:selected').attr("internalid")==8){
	$("#_other").show();
}else if($("#projectCategory").children('option:selected').attr("internalid")==2){
	$("#_securityType").show();
	
}
var projectSubCategory='${town.projectSubCategory.id}';
$("#projectSubCategory").children('option').each(function(){
	if($(this).attr("id")>0){
		$(this).remove();
	}
	$("#projectSubCategory").val(projectSubCategory); 
	})
})

</script>


