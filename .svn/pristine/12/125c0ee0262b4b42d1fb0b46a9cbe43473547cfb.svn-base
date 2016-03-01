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
		<input type="hidden" name="waterResource.id" id="waterId" value="${waterResource.id}" />
		<input type="hidden" id="basicId" name="waterResource.peopleAspiration.id" value="${peopleAspirations.id}"/>
 		<div class="grid_5 lable-right">        
			<label class="form-lbl">工程名称：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="waterResource.projectName" id="projectName"  maxlength="30" value="${waterResource.projectName}" 
				 class='form-txt' />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">受益人口：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" name="waterResource.beneficiaryNumber" id="beneficiaryNumber"  maxlength="10"  value="${waterResource.beneficiaryNumber}"
				class='form-txt'/>
		</div>
 		<div class='clearLine'>&nbsp;</div>
 		<div class="grid_5 lable-right">
			<label class="form-lbl">建设性质：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="waterResource.buildType.id" id="buildType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_WATEER_BUILD_TYPE"  	 defaultValue="${waterResource.buildType.id}"/>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
 		
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">项目类别：</label>
	 	</div>
		<div class="grid_6 ">
		<select name="waterResource.projectCategory.id" id="projectCategory" class='form-txt'  <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:PropertyDictReleationSelectTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_WATEER_PROJECT"  defaultValue="${waterResource.projectCategory.id}" 
		   		reletionId="projectSubCategory"    reletionName="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_WATEER_PROJECT_SUB_TYPE" id="projectCategory"/>
			</select>
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">工程类别：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="waterResource.projectSubCategory.id" id="projectSubCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_WATEER_PROJECT_SUB_TYPE"  	 defaultValue="${waterResource.projectSubCategory.id}"/>
			</select>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div id="_other">
			<div class="grid_5 lable-right">
				<label class="form-lbl">其它：</label>
				</div>
			<div class="grid_18 heightAuto">
				<textarea  id="otherContent" name="waterResource.otherContent"  maxlength="50" class='form-txt' >${waterResource.otherContent}</textarea>
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设起点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="waterResource.fromAddress" id="fromAddress"  maxlength="60" value="${waterResource.fromAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设讫点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="waterResource.toAddress" id="toAddress"  maxlength="60" value="${waterResource.toAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div id="_cenAndSca">	
			<div class="grid_5 lable-right">
				<label class="form-lbl">集中供水(处)：</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="waterResource.centralized" id="centralized"  maxlength="20" value="${waterResource.centralized}" 
					class='form-txt' />
			</div>
			
	 		<div class="grid_5 lable-right">
				<label class="form-lbl">分散供水(日供水量:方)：</label>
			</div>
			<div class="grid_6">
				<input type="text"  name="waterResource.scatter" id="scatter"  maxlength="20" value="${waterResource.scatter}" 
					class='form-txt' />
			</div>
			<div class='clearLine'>&nbsp;</div>
		</div>
		<div id="_num">
			<div class="grid_5 lable-right">
				<label class="form-lbl">数量(口、节)：</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="waterResource.num" id="num"  maxlength="10" value="${waterResource.num}" 
					class='form-txt' />
			</div>
				
			<div class="grid_5 lable-right">
				<label class="form-lbl">蓄水量(立方)：</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="waterResource.impoundage" id="impoundage"  maxlength="20" value="${waterResource.impoundage}" 
					class='form-txt' />
			</div>
			<div class='clearLine'>&nbsp;</div>
		</div>
		<div id='_mileage'>
	 		<div class="grid_5 lable-right">
				<label class="form-lbl">里程(公里)：</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="waterResource.mileage" id="mileage"  maxlength="20" value="${waterResource.mileage}" 
					class='form-txt' />
			</div>
		</div>
 		<div class="grid_5 lable-right">
			<label class="form-lbl">计划资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="waterResource.plannedInvestment" id="plannedInvestment"  maxlength="20" value="${waterResource.plannedInvestment}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">自筹资金(万元):</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="waterResource.selfFund" id="selfFund"  maxlength="20" value="${waterResource.selfFund}" 
				class='form-txt' />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">缺口资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="waterResource.gapFund" id="gapFund"  maxlength="20" value="${waterResource.gapFund}" 
				class='form-txt'  readonly/>
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
						if($('#waterId').val()=="")
							$.messageBox({message:"水利研究整理信息新增成功!"});
						else 
							$.messageBox({message:"水利研究整理信息修改成功!"});
						//$("#peopleaspirationsDialog").dialog("close");
						//onOrgChanged(userOrgId,isGrid());
					//	$("#issueList").trigger("reloadGrid");
						$('#waterId').val(data.id);
						$("#maintainForm1").attr("action","${path}/threeRecords/water/updateWaterResource.action");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"waterResource.centralized":{
			digits:true,
			range:[1,9999999999]
			},
			"waterResource.beneficiaryNumber":{
				digits:true,
				range:[1,9999999999]
			},"waterResource.mileage":{
				number:true,
				validatorFloat:true
			},"waterResource.num":{
				number:true,
				range:[1,9999999999]
			}
			,"waterResource.impoundage":{
				number:true,
				validatorFloat:true
			},"waterResource.scatter":{
				number:true,
				validatorFloat:true
			},"waterResource.plannedInvestment":{
				number:true,
				validatorFloat:true
			}
			,"waterResource.selfFund":{
				number:true,
				validatorFloat:true
			},"waterResource.gapFund":{
				number:true,
				validatorFloat:true
			},
			"waterResource.projectCategory.id":{
				required:true
			}
		},
		messages:{
			"waterResource.centralized":{
			digits:'集中供水只能输入1到1,9999999999之间的整数',
			range:$.format('集中供水只能输入{0}到{1}之间的整数')
			},
			"waterResource.beneficiaryNumber":{
				digits:'受益人口只能输入1到1,9999999999之间的整数',
				range:$.format('受益人口只能输入{0}到{1}之间的整数')
			},"waterResource.num":{
				number:'只能输入1到1,9999999999之间的整数',
				range:$.format('只能输入{0}到{1}之间的整数')
			}
			,"waterResource.mileage":{
				number:'里程只能输入1到1,9999999999之间的数',
				validatorFloat:$.format('里程只能输入0到9999999999之间的数,小数保留4位有效数')
			}
			,"waterResource.impoundage":{
				number:'蓄水量只能输入1到1,9999999999之间的数',
				validatorFloat:$.format('蓄水量只能输入0到9999999999之间的数,小数保留4位有效数')
			},"waterResource.scatter":{
				number:'分散供水只能输入1到1,9999999999之间的数',
				validatorFloat:$.format('分散供水只能输入0到9999999999之间的数,小数保留4位有效数')
			},"waterResource.plannedInvestment":{
				number:'计划投资只能输入0到9999999999之间的数',
				validatorFloat:$.format('计划投资只能输入0到9999999999之间的数,小数保留4位有效数')
			}
			,"waterResource.selfFund":{
				number:'自筹资金只能输入0到9999999999之间的数',
				validatorFloat:$.format('自筹资金只能输入0到9999999999之间的数,小数保留4位有效数')
			},"waterResource.gapFund":{
				number:'缺口资金只能输入0到9999999999之间的数',
				validatorFloat:$.format('缺口资金只能输入0到9999999999之间的数,小数保留4位有效数')
			},
			"waterResource.projectCategory.id":{
				required:"请选择项目类别"
			}
			
			
		},
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#maintainForm1").attr("action","${path}/threeRecords/water/addWaterResource.action");
</s:if>
<s:if test='"edit".equals(mode)'>
	if($("#waterId").val()=="")
		 $("#maintainForm1").attr("action","${path}/threeRecords/water/addWaterResource.action");
	else
   		 $("#maintainForm1").attr("action","${path}/threeRecords/water/updateWaterResource.action");
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
$("#_cenAndSca").hide();
$("#_num").hide();
$("#_mileage").hide();
$("#projectCategory").change(function(){
	 if($(this).children('option:selected').attr("internalid")==6){
		$("#_other").show();
		$("#_mileage").hide();
		$("#_num").hide();
		$("#_cenAndSca").hide();
		$("#otherContent").val("");
		$("#num").val("");
		$("#impoundage").val("");
		$("#mileage").val("");
		$("#centralized").val("");
		$("#scatter").val("");
	}else if($("#projectCategory").children('option:selected').attr("internalid")==0){
		$("#_cenAndSca").show();
		$("#_mileage").hide();
		$("#_num").hide();
		$("#_other").hide();
		$("#otherContent").val("");
		$("#num").val("");
		$("#impoundage").val("");
		$("#mileage").val("");
	}
	else if($("#projectCategory").children('option:selected').attr("internalid")==1||$("#projectCategory").children('option:selected').attr("internalid")==2||$("#projectCategory").children('option:selected').attr("internalid")==3){
		$("#_num").show();
		$("#_mileage").hide();
		$("#_cenAndSca").hide();
		$("#_other").hide();
		$("#otherContent").val("");
		$("#centralized").val("");
		$("#scatter").val("");
		$("#mileage").val("");
	}
	else if($("#projectCategory").children('option:selected').attr("internalid")==4||$("#projectCategory").children('option:selected').attr("internalid")==5){
		$("#_mileage").show();
		$("#_num").hide();
		$("#_cenAndSca").hide();
		$("#_other").hide();
		$("#otherContent").val("");
		$("#centralized").val("");
		$("#scatter").val("");
		$("#num").val("");
		$("#impoundage").val("");
	}

})
if($("#projectCategory").children('option:selected').attr("internalid")==6){
	$("#_other").show();
}else if($("#projectCategory").children('option:selected').attr("internalid")==0){
	$("#_cenAndSca").show();
}
else if($("#projectCategory").children('option:selected').attr("internalid")==1||$("#projectCategory").children('option:selected').attr("internalid")==2||$("#projectCategory").children('option:selected').attr("internalid")==3){
	$("#_num").show();
}
else if($("#projectCategory").children('option:selected').attr("internalid")==4||$("#projectCategory").children('option:selected').attr("internalid")==5){
	$("#_mileage").show();
}


})
var projectSubCategory='${waterResource.projectSubCategory.id}';
$("#projectSubCategory").children('option').each(function(){
	if($(this).attr("id")>0){
		$(this).remove();
	}
	$("#projectSubCategory").val(projectSubCategory); 
	})
</script>


