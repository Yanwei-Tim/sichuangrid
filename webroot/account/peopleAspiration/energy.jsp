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
		<input type="hidden" name="energy.id" id="energyId" value="${energy.id}" />
		<input type="hidden" id="basicId" name="energy.peopleAspiration.id" value="${peopleAspirations.id}"/>
 		<div class="grid_5 lable-right">        
			<label class="form-lbl">工程名称：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="energy.projectName" id="projectName"  maxlength="30" value="${energy.projectName}" 
				 class='form-txt' />
		</div>
		<div class="grid_5 lable-right">
			
			<label class="form-lbl">受益人口：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" name="energy.beneficiaryNumber" id="beneficiaryNumber"  maxlength="10"  value="${energy.beneficiaryNumber}"
				class='form-txt'/>
		</div>
 		<div class='clearLine'>&nbsp;</div>
 		<div class="grid_5 lable-right">
			<label class="form-lbl">建设性质：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="energy.buildType.id" id="buildType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_BUILD_TYPE"  	 defaultValue="${energy.buildType.id}"/>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
 		
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">项目类别：</label>
	 	</div>
		<div class="grid_6 ">
		<select name="energy.projectCategory.id" id="projectCategory" class='form-txt'  <s:if test='"view".equals(mode)'>disabled</s:if>>
			<pop:PropertyDictReleationSelectTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_PROJECT"  defaultValue="${energy.projectCategory.id}" 
		   		reletionId="projectSubCategory"    reletionName="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_PROJECT_SUB_TYPE" id="projectCategory"/>
			
			</select>
			
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">工程类型：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="energy.projectSubCategory.id" id="projectSubCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_PROJECT_SUB_TYPE"  	 defaultValue="${energy.projectSubCategory.id}"/>
			</select>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		
		<div id="_other">
			<div class="grid_5 lable-right">
				<label class="form-lbl">其它：</label>
				</div>
			<div class="grid_18 heightAuto">
				<textarea  id="otherContent" name="energy.otherContent"  maxlength="50" class='form-txt' >${energy.otherContent}</textarea>
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设起点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="energy.fromAddress" id="fromAddress"  maxlength="60" value="${energy.fromAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设讫点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="energy.toAddress" id="toAddress"  maxlength="60" value="${energy.toAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
			<div id="_line">
				<div class="grid_5 lable-right">
					<em class="form-req"></em>
					<label class="form-lbl">线路类型：</label>
			 	</div>
				<div class="grid_6 ">
				<select name="energy.lineCategory.id" id="lineCategory" class='form-txt'  <s:if test='"view".equals(mode)'>disabled</s:if>>
				   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_LINE_CATEGORY"  defaultValue="${energy.lineCategory.id}" />
					</select>
				</div>
			</div>
			<div id="_energyNumber">
				<div class="grid_5 lable-right">
					<label class="form-lbl">数量：</label>
				</div>
				<div class="grid_3">
					<input type="text"  name="energy.energyNumber" id="energyNumber"  maxlength="20" value="${energy.energyNumber}" 
						class='form-txt' />
				</div>
			</div>
			
			<div id="_capacity">
				<div class="grid_3"></div>
				<div class="grid_5 lable-right">
					<label class="form-lbl">容积(立方米)：</label>
					</div>
				<div class="grid_3">
					<input type="text"  name="energy.capacity" id="capacity"  maxlength="20" value="${energy.capacity}" 
						class='form-txt' />
				</div>
			</div>
			
			<div class='clearLine'>&nbsp;</div>
		
			<div id="_pipe">
			<div class="grid_5 lable-right">
				<label class="form-lbl">管道类型：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="energy.pipeLineCategory.id" id="pipeLineCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_PIPELINE_CATEGORY"  	 defaultValue="${energy.pipeLineCategory.id}"/>
				</select>
			</div>
			
			<div class="grid_5 lable-right">
				<em class="form-req"></em>
				<label class="form-lbl">管道材质：</label>
		 	</div>
			<div class="grid_6 ">
			<select name="energy.pipeMaterialCategory.id" id="pipeMaterialCategory" class='form-txt '  <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_PIPE_MATERIAL_CATEGORY"  defaultValue="${energy.pipeMaterialCategory.id}" />
				</select>
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_5 lable-right">
			<label class="form-lbl">长度（公里）：</label>
			</div>
			<div class="grid_3">
				<input type="text"  name="energy.mileage" id="mileage"  maxlength="20" value="${energy.mileage}" 
					class='form-txt' />
			</div>
			<div class="grid_3"></div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">填埋深度（米）：</label>
				</div>
			<div class="grid_3">
				<input type="text"  name="energy.depth" id="depth"  maxlength="20" value="${energy.depth}" 
					class='form-txt' />
			</div>
			<div class='clearLine'>&nbsp;</div>
		</div>
		
		<div id="_security">
			<div class="grid_5 lable-right">
				<label class="form-lbl">安全设施类型：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="energy.securityCategory.id" id="securityCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@ENERGY_SECURITY_CATEGORY"  showInternalId="true" 	 defaultValue="${energy.securityCategory.id}"/>
				</select>
			</div>
		</div>
		<div id="_securityNum">
	 		<div class="grid_5 lable-right">
				<label class="form-lbl">数量：</label>
				</div>
			<div class="grid_3">
				<input type="text"  name="energy.securityNum" id="securityNum"  maxlength="20" value="${energy.securityNum}" 
					class='form-txt' />
			</div>
			<div class='clearLine'>&nbsp;</div>
		</div>
		
		
 		<div class="grid_5 lable-right">
			<label class="form-lbl">计划资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="energy.plannedInvestment" id="plannedInvestment"  maxlength="20" value="${energy.plannedInvestment}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">自筹资金(万元):</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="energy.selfFund" id="selfFund"  maxlength="20" value="${energy.selfFund}" 
				class='form-txt' />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">缺口资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="energy.gapFund" id="gapFund"  maxlength="20" value="${energy.gapFund}" 
				class='form-txt' readonly/>
		</div>
		
   		<div class='clearLine'></div>
 		
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
						if($('#energyId').val()=="")
							$.messageBox({message:"能源研究整理信息新增成功!"});
						else 
							$.messageBox({message:"能源研究整理信息修改成功!"});
						//$("#peopleaspirationsDialog").dialog("close");
						//onOrgChanged(userOrgId,isGrid());
					//	$("#issueList").trigger("reloadGrid");
						$('#energyId').val(data.id);
						$("#maintainForm1").attr("action","${path}/threeRecords/energy/updateEnergy.action");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{"energy.beneficiaryNumber":{
				digits:true,
				range:[1,9999999999]
			},
			"energy.securityNum":{
				digits:true,
				range:[1,9999999999]
			},
			"energy.energyNumber":{
				number:true,
				validatorFloat:true
			}
			,"energy.plannedInvestment":{
				number:true,
				validatorFloat:true
			}
			,"energy.selfFund":{
				number:true,
				validatorFloat:true
			},"energy.gapFund":{
				number:true,
				validatorFloat:true
			},"energy.depth":{
				number:true,
				validatorFloat:true
			},"energy.mileage":{
				number:true,
				validatorFloat:true
			},"energy.capacity":{
				number:true,
				validatorFloat:true
			},
			"energy.projectCategory.id":{
				required:true
			}
		},
		messages:{
			
			"energy.securityNum":{
				digits:'数量只能输入1到1,9999999999之间的整数',
				range:$.format('数量只能输入{0}到{1}之间的整数')
			}
			,
			"energy.beneficiaryNumber":{
				digits:'受益人口只能输入1到1,9999999999之间的整数',
				range:$.format('受益人口只能输入{0}到{1}之间的整数')
			}
			,"energy.energyNumber":{
				number:'数量只能输入0到9999999999之间的数',
				validatorFloat:$.format('里程只能输入0到9999999999之间的数,小数保留4位有效数')
			}
			,"energy.plannedInvestment":{
				number:'计划投资只能输入0到9999999999之间的数',
				validatorFloat:$.format('计划投资只能输入0到9999999999之间的数,小数保留4位有效数')
			}
			,"energy.selfFund":{
				number:'自筹资金只能输入0到9999999999之间的数',
				validatorFloat:$.format('自筹资金只能输入0到9999999999之间的数,小数保留4位有效数')
			},"energy.gapFund":{
				number:'缺口资金只能输入0到9999999999之间的数',
				validatorFloat:'缺口资金只能输入0到9999999999之间的数,小数保留4位有效数'
			},"energy.capacity":{
				number:'容积只能输入0到9999999999之间的数',
				validatorFloat:'容积只能输入0到9999999999之间的数,小数保留4位有效数'
			},"energy.mileage":{
				number:'长度只能输入0到9999999999之间的数',
				validatorFloat:'长度只能输入0到9999999999之间的数,小数保留4位有效数'
			},"energy.depth":{
				number:'填埋深度只能输入0到9999999999之间的数',
				validatorFloat:'填埋深度只能输入0到9999999999之间的数,小数保留4位有效数'
			},
			"energy.projectCategory.id":{
				required:"请选择项目类别"
			}
			
			
		},
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#maintainForm1").attr("action","${path}/threeRecords/energy/addEnergy.action");
</s:if>
<s:if test='"edit".equals(mode)'>
	if($("#energyId").val()=="")
		 $("#maintainForm1").attr("action","${path}/threeRecords/energy/addEnergy.action");
	else
   		 $("#maintainForm1").attr("action","${path}/threeRecords/energy/updateEnergy.action");
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
$("#_pipe").hide();
$("#_security").hide();
$("#_line").hide();
$("#_energyNumber").hide();
$("#_securityNum").hide();
$("#_capacity").hide();
$("#projectCategory").change(function(){
	$("#_other").hide();
	$("#securityCategory").val("");
	projectCategoryEvent();
})
projectCategoryEvent();

$("#projectSubCategory").change(function(){
	$("#_energyNumber").hide();
	$("#_securityNum").hide();
	$("#_capacity").hide();
	$("#_security").hide();
	subEvent();
})

subEvent();

})

function projectCategoryEvent(){
	$("#_other").hide();
	$("#_pipe").hide();
	$("#_security").hide();
	$("#_line").hide();

	$("#_energyNumber").hide();
	$("#_securityNum").hide();
	$("#_capacity").hide();
	
	if($("#projectCategory").children('option:selected').attr("internalid")==6){
		$("#_other").show();
		$("#pipeLineCategory").val("");
		$("#pipeMaterialCategory").val("");
		$("#mileage").val("");
		$("#depth").val("");
		$("#lineCategory").val("");
		$("#energyNumber").val("");
		$("#capacity").val("");
		$("#securityNum").val("");
		
		
	}else if($("#projectCategory").children('option:selected').attr("internalid")==1||$("#projectCategory").children('option:selected').attr("internalid")==2){
		$("#otherContent").val("");
		$("#lineCategory").val("");
		$("#energyNumber").val("");
		$("#capacity").val("");
		$("#_pipe").show();
		$("#_security").show();
		$("#_securityNum").show();
		$("#securityCategory").children('option').each(function(){
			if($(this).attr("internalid")!=0&&$(this).attr("internalid")!=4&&$(this).attr("internalid")!=1) $(this).hide();
			else $(this).show();
		})
	}else if($("#projectCategory").children('option:selected').attr("internalid")==3){
		$("#otherContent").val("");
		$("#pipeLineCategory").val("");
		$("#pipeMaterialCategory").val("");
		$("#mileage").val("");
		$("#depth").val("");
		$("#capacity").val("");
		$("#_security").show();
		$("#securityNum").val("");
		$("#lineCategory").val("");
		$("#energyNumber").val("");
		$("#securityCategory").children('option').each(function(){
			if($(this).attr("internalid")!=0&&$(this).attr("internalid")!=4&&$(this).attr("internalid")!=2) $(this).hide();
			else $(this).show();
		})
	}else if($("#projectCategory").children('option:selected').attr("internalid")==4){
		$("#otherContent").val("");
		$("#pipeLineCategory").val("");
		$("#pipeMaterialCategory").val("");
		$("#mileage").val("");
		$("#depth").val("");
		$("#capacity").val("");
		$("#_line").show();
		$("#_security").show();
		$("#_energyNumber").show();
		$("#_securityNum").show();
		$("#securityCategory").children('option').each(function(){
			if($(this).attr("internalid")!=4) $(this).hide();
			else $(this).show();
		})
	}else if($("#projectCategory").children('option:selected').attr("internalid")==5){
		$("#otherContent").val("");
		$("#pipeLineCategory").val("");
		$("#pipeMaterialCategory").val("");
		$("#mileage").val("");
		$("#depth").val("");
		$("#securityNum").val("");
		$("#lineCategory").val("");
		$("#securityCategory").children('option').each(function(){
			if($(this).attr("internalid")!=5) $(this).hide();
			else $(this).show();
		})
		if($("#projectSubCategory").find("option:selected").text().indexOf('沼气池建设')>-1){
			$("#_energyNumber").show();
			$("#_capacity").show();
		}else{
			$("#_security").show();
		}
		
	}
}
function subEvent(){
	if($("#projectCategory").children('option:selected').attr("internalid")==5){
		if($("#projectSubCategory").find("option:selected").text().indexOf('沼气池建设')>-1){
			$("#_energyNumber").show();
			$("#_capacity").show();
		}else{
			$("#_security").show();
			
		}
	}else if($("#projectCategory").children('option:selected').attr("internalid")==4){
		$("#_security").show();
		$("#_energyNumber").show();
		$("#_securityNum").show();
	}


	
}
var projectSubCategory = '${energy.projectSubCategory.id}';
$("#projectSubCategory").children('option').each(function(){
	if($(this).attr("id")>0){
		$(this).remove();
	}
	$("#projectSubCategory").val(projectSubCategory); 
	})

</script>



