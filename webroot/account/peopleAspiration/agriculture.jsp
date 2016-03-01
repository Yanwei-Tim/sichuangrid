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
		<input type="hidden" name="agriculture.id" id="agricultureId" value="${agriculture.id}" />
		<input type="hidden" id="basicId" name="agriculture.peopleAspiration.id" value="${peopleAspirations.id}"/>
 		<div class="grid_5 lable-right">        
			<label class="form-lbl">工程名称：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="agriculture.projectName" id="projectName"  maxlength="30" value="${agriculture.projectName}" 
				 class='form-txt' />
		</div>
		<div class="grid_5 lable-right">
			<em class="form-req"></em>
			<label class="form-lbl">受益人口：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" name="agriculture.beneficiaryNumber" id="beneficiaryNumber"  maxlength="10"  value="${agriculture.beneficiaryNumber}"
				class='form-txt'/>
		</div>
 		<div class='clearLine'>&nbsp;</div>
 		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">建设性质：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="agriculture.buildType.id" id="buildType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@AGRICULTURE_PROJECT_BUILD_TYPE"  	 defaultValue="${agriculture.buildType.id}"/>
			</select>
		</div>
		
		
 		
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">项目类别：</label>
	 	</div>
		<div class="grid_6 ">
		<select name="agriculture.projectCategory.id" id="projectCategory" class='form-txt '  <s:if test='"view".equals(mode)'>disabled</s:if>>
		   	<pop:PropertyDictReleationSelectTag name="@com.tianque.plugin.account.property.PropertyTypes@AGRICULTURE_PROJECT"  defaultValue="${agriculture.projectCategory.id}" 
		   		reletionId="projectSubCategory"    reletionName="@com.tianque.plugin.account.property.PropertyTypes@AGRICULTURE_PROJECT_SUB" id="projectCategory"/>
		
			</select>
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">项目类型：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="agriculture.projectSubCategory.id" id="projectSubCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@AGRICULTURE_PROJECT_SUB"  	 defaultValue="${agriculture.projectSubCategory.id}"/>
			</select>
		</div>
		<div id="_quantities">
			<div class="grid_5 lable-right">
				<label class="form-lbl">工程量（米、座、口）</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="agriculture.quantities" id="quantities"  maxlength="20" value="${agriculture.quantities}" 
					class='form-txt' />
			</div>
		</div>
		<div id="_scopeNumber">
			<div class="grid_5 lable-right">
				<label class="form-lbl">规模(亩、袋、平方米)</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="agriculture.scopeNumber" id="scopeNumber"  maxlength="20" value="${agriculture.scopeNumber}" 
					class='form-txt' />
			</div>
		</div>
		<div id="_num">
			<div class="grid_5 lable-right">
				<label class="form-lbl">数量：</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="agriculture.num" id="num"  maxlength="20" value="${agriculture.num}" 
					class='form-txt' />
			</div>
		</div>
		<div id="_waterYield">
			<div class="grid_5 lable-right">
				<label class="form-lbl">出水量（方/小时）</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="agriculture.waterYield" id="waterYield"  maxlength="20" value="${agriculture.waterYield}" 
					class='form-txt' />
			</div>
		</div>
		<div id="_capacity">
			<div class="grid_5 lable-right">
				<label class="form-lbl">装机容量（千瓦）</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="agriculture.capacity" id="capacity"  maxlength="20" value="${agriculture.capacity}" 
					class='form-txt' />
			</div>
		
		</div>
		<div id="_farmCategory">
			<div class="grid_5 lable-right">
				<label class="form-lbl">农业培训类型：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="agriculture.farmCategory.id" id="farmCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@AGRICULTURE_FARMING_TRAIN"  	 defaultValue="${agriculture.farmCategory.id}"/>
				</select>
			</div>
		</div>
		<div id="_machineCategory">
			<div class="grid_5 lable-right">
				<label class="form-lbl">农机培训类型：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="agriculture.machineCategory.id" id="machineCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@AGRICULTURE_MACHINERY_TRAIN"  	 defaultValue="${agriculture.machineCategory.id}"/>
				</select>
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div id="_train">
			<div class="grid_5 lable-right">
				<label class="form-lbl">培训次数：</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="agriculture.trainNumber" id="trainNumber"  maxlength="20" value="${agriculture.trainNumber}" 
					class='form-txt' />
			</div>
			
			<div class="grid_5 lable-right">
				<label class="form-lbl">培训人数:</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="agriculture.trainPeopleNumber" id="trainPeopleNumber"  maxlength="20" value="${agriculture.trainPeopleNumber}" 
					class='form-txt' />
			</div>
			<div class='clearLine'>&nbsp;</div>	
		</div>
		
		<div id="_other">
			<div class="grid_5 lable-right">
				<label class="form-lbl">其它：</label>
				</div>
			<div class="grid_18 heightAuto">
				<textarea  id="otherContent" name="agriculture.otherContent"  maxlength="50" class='form-txt' >${agriculture.otherContent}</textarea>
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
		
 		<div class="grid_5 lable-right">
			<label class="form-lbl">建设起点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="agriculture.fromAddress" id="fromAddress"  maxlength="60" value="${agriculture.fromAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设讫点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="agriculture.toAddress" id="toAddress"  maxlength="60" value="${agriculture.toAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
 		<div class="grid_5 lable-right">
			<label class="form-lbl">计划资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="agriculture.plannedInvestment" id="plannedInvestment"  maxlength="20" value="${agriculture.plannedInvestment}" 
				class='form-txt' />
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">自筹资金(万元):</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="agriculture.selfFund" id="selfFund"  maxlength="20" value="${agriculture.selfFund}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>	
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">缺口资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="agriculture.gapFund" id="gapFund"  maxlength="20" value="${agriculture.gapFund}" 
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
						if($('#agricultureId').val()=="")
							$.messageBox({message:"农业资源研究整理信息新增成功!"});
						else 
							$.messageBox({message:"农业资源研究整理信息修改成功!"});
						//$("#peopleaspirationsDialog").dialog("close");
						$('#agricultureId').val(data.id);
						//onOrgChanged(userOrgId,isGrid());
					//	$("#issueList").trigger("reloadGrid");
						$("#maintainForm1").attr("action","${path}/threeRecords/agriculture/updateAgriculture.action");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"agriculture.beneficiaryNumber":{
				digits:true,
				range:[1,9999999999]
			}
			,"agriculture.trainNumber":{
				digits:true,
				range:[1,9999999999]
			}
			,"agriculture.trainPeopleNumber":{
				digits:true,
				range:[1,9999999999]
			}
			,"agriculture.num":{
				digits:true,
				range:[1,9999999999]
			}
			,"agriculture.scopeNumber":{
				number:true,
				validatorFloat:true
			},"agriculture.plannedInvestment":{
				number:true,
				validatorFloat:true
			}
			,"agriculture.selfFund":{
				number:true,
				validatorFloat:true
			},"agriculture.gapFund":{
				number:true,
				validatorFloat:true
			},"agriculture.quantities":{
				number:true,
				validatorFloat:true
			},"agriculture.waterYield":{
				number:true,
				validatorFloat:true
			},"agriculture.capacity":{
				number:true,
				validatorFloat:true
			},
			"agriculture.projectCategory.id":{
				required:true
			},
			"agriculture.buildType.id":{
				required:true
			}
		},
		messages:{
			"agriculture.trainNumber":{
				digits:'培训次数只能输入1到1,9999999999之间的整数',
				range:$.format('培训次数只能输入{0}到{1}之间的整数')
			}
			,
			"agriculture.trainPeopleNumber":{
				digits:'培训人数只能输入1到1,9999999999之间的整数',
				range:$.format('培训人数只能输入{0}到{1}之间的整数')
			}
			,
			"agriculture.num":{
				digits:'数量只能输入1到1,9999999999之间的整数',
				range:$.format('数量只能输入{0}到{1}之间的整数')
			}
			,
			"agriculture.beneficiaryNumber":{
				digits:'受益人口只能输入1到1,9999999999之间的整数',
				range:$.format('受益人口只能输入{0}到{1}之间的整数')
			},"agriculture.quantities":{
				number:'工程量只能输入1到1,9999999999之间的数',
				validatorFloat:$.format('工程量只能输入0到9999999999之间的数,小数保留4位有效数')
			},"agriculture.waterYield":{
				number:'出水量（方/小时）只能输入1到1,9999999999之间的数',
				validatorFloat:$.format('出水量（方/小时）只能输入0到9999999999之间的数,小数保留4位有效数')
			},"agriculture.capacity":{
				number:'装机容量（千瓦）只能输入1到1,9999999999之间的数',
				validatorFloat:$.format('装机容量（千瓦）只能输入0到9999999999之间的数,小数保留4位有效数')
			}
			
			,"agriculture.scopeNumber":{
				number:'规模数量只能输入1到1,9999999999之间的数',
				validatorFloat:$.format('规模数量只能输入0到9999999999之间的数,小数保留4位有效数')
			},"agriculture.plannedInvestment":{
				number:'计划投资只能输入0到1,9999999999之间的数',
				range:$.format('计划投资只能输入0到9999999999之间的数,小数保留4位有效数')
			}
			,"agriculture.selfFund":{
				number:'自筹资金只能输入0到1,9999999999之间的数',
				range:$.format('自筹资金只能输入0到9999999999之间的数,小数保留4位有效数')
			},"agriculture.gapFund":{
				number:'缺口资金只能输入0到1,9999999999之间的数',
				validatorFloat:$.format('缺口资金只能输入0到9999999999之间的数,小数保留4位有效数')
			},
			"agriculture.projectCategory.id":{
				required:"请选择项目类别"
			},
			"agriculture.buildType.id":{
				required:"请选择建设性质"
			}
			
			
		},
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#maintainForm1").attr("action","${path}/threeRecords/agriculture/addAgriculture.action");
</s:if>
<s:if test='"edit".equals(mode)'>
	if($("#agricultureId").val()=="")
		 $("#maintainForm1").attr("action","${path}/threeRecords/agriculture/addAgriculture.action");
	else
   		 $("#maintainForm1").attr("action","${path}/threeRecords/agriculture/updateAgriculture.action");
</s:if>

$("#plannedInvestment").blur(function(){
	if($("#plannedInvestment").val()!=''&&$("#selfFund").val()!=''){
		$("#gapFund").val(($("#plannedInvestment").val()*10000-$("#selfFund").val()*10000)/10000);
	}
})
$("#selfFund").blur(function(){
	if($("#plannedInvestment").val()!=''&&$("#selfFund").val()!=''){
		$("#gapFund").val(($("#plannedInvestment").val()*10000-$("#selfFund").val()*10000)/10000);
	}
})
$("#_other").hide();
$("#_quantities").hide();
$("#_scopeNumber").hide();

$("#_num").hide();
$("#_waterYield").hide();
$("#_capacity").hide();
$("#_train").hide();
$("#_farmCategory").hide();
$("#_machineCategory").hide();

$("#projectCategory").change(function(){
	$("#_other").hide();
	$("#_quantities").hide();
	$("#_scopeNumber").hide();
	$("#_num").hide();
	$("#_waterYield").hide();
	$("#_capacity").hide();
	$("#_train").hide();
	
	projectCategoryEvent();
})
projectCategoryEvent();

$("#projectSubCategory").change(function(){
	$("#_num").hide();
	$("#_waterYield").hide();
	$("#_capacity").hide();
	projectSubCategoryEvent();
})
projectSubCategoryEvent();
var projectSubCategory = '${agriculture.projectSubCategory.id}';
$("#projectSubCategory").children('option').each(function(){
	if($(this).attr("id")>0){
		$(this).remove();
	}
	$("#projectSubCategory").val(projectSubCategory); 
	})

})




function projectCategoryEvent(){
	if($("#projectCategory").children('option:selected').attr("internalid")==5){
		$("#_other").show();
		$("#quantities").val("");
		$("#scopeNumber").val("");
		$("#num").val("");
		$("#waterYield").val("");
		$("#capacity").val("");
		$("#farmCategory").val("");
		$("#machineCategory").val("");
		$("#trainNumber").val("");
		$("#trainPeopleNumber").val("");
		
	}else if($("#projectCategory").children('option:selected').attr("internalid")==1){
		$("#otherContent").val("");
		$("#quantities").val("");
		$("#num").val("");
		$("#waterYield").val("");
		$("#capacity").val("");
		$("#farmCategory").val("");
		$("#machineCategory").val("");
		$("#trainNumber").val("");
		$("#trainPeopleNumber").val("");
		$("#_scopeNumber").show();
		
	}else if($("#projectCategory").children('option:selected').attr("internalid")==2){
		$("#otherContent").val("");
		$("#scopeNumber").val("");
		$("#num").val("");
		$("#waterYield").val("");
		$("#capacity").val("");
		$("#farmCategory").val("");
		$("#machineCategory").val("");
		$("#trainNumber").val("");
		$("#trainPeopleNumber").val("");
		$("#_quantities").show();
	}else if($("#projectCategory").children('option:selected').attr("internalid")==3){
		$("#otherContent").val("");
		$("#quantities").val("");
		$("#scopeNumber").val("");
		$("#waterYield").val("");
		$("#capacity").val("");
		$("#farmCategory").val("");
		$("#machineCategory").val("");
		$("#trainNumber").val("");
		$("#trainPeopleNumber").val("");
		$("#_num").show();
		projectSubCategoryEvent();
	}else if($("#projectCategory").children('option:selected').attr("internalid")==4){
		$("#otherContent").val("");
		$("#quantities").val("");
		$("#scopeNumber").val("");
		$("#num").val("");
		$("#waterYield").val("");
		$("#capacity").val("");
		$("#_train").show();
		projectSubCategoryEvent();
	}
}
function projectSubCategoryEvent(){
	$("#_farmCategory").hide();
	$("#_machineCategory").hide();
	$("#_num").hide();
	$("#_waterYield").hide();
	$("#_capacity").hide();
	$("#_train").hide();
	if($("#projectCategory").children('option:selected').attr("internalid")==3){
		$("#farmCategory").val("");
		$("#machineCategory").val("");
		$("#trainNumber").val("");
		$("#trainPeopleNumber").val("");
		if($("#projectSubCategory").find("option:selected").text().indexOf("电力提灌站")>-1){
			$("#_capacity").show();
			$("#_num").show();
		}else{
			$("#_waterYield").show();
			$("#_num").show();
			
		}
	}else if($("#projectCategory").children('option:selected').attr("internalid")==4){
		$("#num").val("");
		$("#waterYield").val("");
		$("#capacity").val("");
		if($("#projectSubCategory").find("option:selected").text().indexOf("农业培训")>-1){
			$("#_farmCategory").show();
			$("#_train").show();
		}else{
			$("#_machineCategory").show();
			$("#_train").show();
		}
	}
}
</script>


