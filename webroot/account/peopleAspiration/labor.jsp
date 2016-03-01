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
		<input type="hidden" name="labor.id" id="laborId" value="${labor.id}" />
		<input type="hidden" id="basicId" name="labor.peopleAspiration.id" value="${peopleAspirations.id}"/>
 		
 		
 		
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">项目类别：</label>
	 	</div>
		<div class="grid_6 ">
		<select name="labor.projectCategory.id" id="projectCategory" class="form-txt {required:true,messages:{required:'请选择项目类别'}}"  <s:if test='"view".equals(mode)'>disabled</s:if>>
		   	<pop:PropertyDictReleationSelectTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_PROJECT"  defaultValue="${labor.projectCategory.id}" 
		   		reletionId="projectSubCategory"    reletionName="@com.tianque.plugin.account.property.PropertyTypes@LABOR_PROJECT_SUB" id="projectCategory"/>
		
			</select>
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">项目类型：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="labor.projectSubCategory.id" id="projectSubCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_PROJECT_SUB"  showInternalId='true'	 defaultValue="${labor.projectSubCategory.id}"/>
			</select>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		
		
		<div id="_other">
			<div class="grid_5 lable-right">
				<label class="form-lbl">其它：</label>
				</div>
			<div class="grid_18 heightAuto">
				<textarea  id="otherContent" name="labor.otherContent"  maxlength="50" class='form-txt' >${labor.otherContent}</textarea>
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div id="_content">
			<div class="grid_5 lable-right">
				<label class="form-lbl">项目内容：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="labor.projectSubContentCategory.id" id="projectSubContentCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_PROJECT_CONTENT"  showInternalId='true'	 defaultValue="${labor.projectSubContentCategory.id}"/>
				</select>
				<select id="_projectSubContentCategory" style="display:none">
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_PROJECT_CONTENT"  showInternalId='true'/>
				</select>
			</div>
		</div>
		<div id="_intro">
			<div class="grid_5 lable-right">
				<label class="form-lbl">本人学历：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="labor.degree.id" id="degree" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LEDGER_DEGREE"  	 defaultValue="${labor.degree.id}"/>
				</select>
			</div>
			
			<div class="grid_5 lable-right">
			<em class="form-req"></em>
			<label class="form-lbl">本人技能特长：</label>
		 	</div>
			<div class="grid_6 ">
			 <input type="text"  name="labor.skill" id="skill"  maxlength="20" value="${labor.skill}" 
					class='form-txt' />
			</div>
			
			<div class='clearLine'>&nbsp;</div>
		</div>
		<div id="_ageAndCripple">
			<div class="grid_5 lable-right">
				<label class="form-lbl">年龄段：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="labor.ageLevel.id" id="ageLevel" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_AGE"  	 defaultValue="${labor.ageLevel.id}"/>
				</select>
			</div>
			
			<div class="grid_5 lable-right">
				<label class="form-lbl">残疾等级：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="labor.crippleLevel.id" id="crippleLevel" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_CRIPPLE"  	 defaultValue="${labor.crippleLevel.id}"/>
				</select>
			</div>
		</div>
		<div id="_dignity">
			<div class="grid_5 lable-right">
				<label class="form-lbl">身份性质：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="labor.dignity.id" id="dignity" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@LABOR_DIGNITY"  showInternalId='true'	 defaultValue="${labor.dignity.id}"/>
				</select>
			</div>
		</div>	
		
 		<div id="_yawn">
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_5 lable-right">        
				<label class="form-lbl">工资拖欠工程项目名称：</label>
		 	</div>
			<div class="grid_6">
				<input type="text"  name="labor.projectName" id="projectName"  maxlength="30" value="${labor.projectName}" 
					 class='form-txt' />
			</div>
	 		
			<div class="grid_5 lable-right">
				<label class="form-lbl">工资拖欠施工单位:</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="labor.company" id="company"  maxlength="20" value="${labor.company}" 
					class='form-txt' />
			</div>
			
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">工资拖欠施工单位地址:</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="labor.yawnAddr" id="yawnAddr"  maxlength="20" value="${labor.yawnAddr}" 
					class='form-txt' />
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">工资拖欠施工单位联系人姓名:</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="labor.yawnContactor" id="yawnContactor"  maxlength="20" value="${labor.yawnContactor}" 
					class='form-txt' />
			</div>
			<div class='clearLine'>&nbsp;</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">联系电话:</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="labor.yawnMobile" id="yawnMobile"  maxlength="20" value="${labor.yawnMobile}" 
					class='form-txt' />
			</div>
			<div class="grid_5 lable-right">
				<em class="form-req"></em>
				<label class="form-lbl">涉及人数：</label>
		 	</div>
			<div class="grid_6 ">
			 <input type="text"  name="labor.relationNumber" id="relationNumber"  maxlength="20" value="${labor.relationNumber}" 
					class='form-txt' />
			</div>
				<div class="grid_5 lable-right">
				<label class="form-lbl">涉及金额（万元）：</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="labor.money" id="money"  maxlength="20" value="${labor.money}" 
					class='form-txt' />
			</div>
			
			<div class='clearLine'>&nbsp;</div>
		</div>
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
						if($('#laborId').val()=="")
							$.messageBox({message:"劳动和社会保障研究整理信息新增成功!"});
						else 
							$.messageBox({message:"劳动和社会保障研究整理信息修改成功!"});
						//$("#peopleaspirationsDialog").dialog("close");
						//onOrgChanged(userOrgId,isGrid());
					//	$("#issueList").trigger("reloadGrid");
						$('#laborId').val(data.id);
						$("#maintainForm1").attr("action","${path}/threeRecords/labor/updateLabor.action");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"labor.relationNumber":{
				digits:true,
				range:[1,9999999999]
			},"labor.money":{
				number:true,
				validatorMoney:true
			}
			
		},
		messages:{
			"labor.relationNumber":{
				digits:'涉及人数只能输入1到1,9999999999之间的整数',
				range:$.format('涉及人数只能输入{0}到{1}之间的整数')
			}
			,"labor.money":{
				nubmer:'涉及金额只能输入0到9999999999之间的数',
				validatorMoney:'涉及金额只能输入0到9999999999之间的数,小数保留4位有效数'
			}
			
		},
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#maintainForm1").attr("action","${path}/threeRecords/labor/addLabor.action");
</s:if>
<s:if test='"edit".equals(mode)'>
	if($("#laborId").val()=="")
		 $("#maintainForm1").attr("action","${path}/threeRecords/labor/addLabor.action");
	else
   		 $("#maintainForm1").attr("action","${path}/threeRecords/labor/updateLabor.action");
</s:if>


$("#_other").hide();
$("#_intro").hide();
$("#_yawn").hide();
$("#_dignity").hide();
$("#_ageAndCripple").hide();
$("#_content").hide();
$("#projectCategory").change(function(){
	$("#_other").hide();
	$("#_intro").hide();
	$("#_yawn").hide();
	$("#_dignity").hide();
	$("#_ageAndCripple").hide();
	$("#_content").hide();
	$("#projectSubContentCategory").val("");
	$('#otherContent').val("");
	resetBySubType();
	categoryEvent();
})
categoryEvent();
$("#projectSubCategory").change(function(){
	$("#projectSubContentCategory").val("");
	$("#dignity").val("");
	$("#_ageAndCripple").hide();
	resetBySubType();
	subEvent();
})

subEvent();

$("#projectSubContentCategory").change(function(){
	$("#dignity").val("");
	subContentEvent();
})
subContentEvent();

})

function resetBySubType(){
	$('#dignity').val("");
	$('#ageLevel').val("");
	$('#crippleLevel').val("");
	$('#degree').val("");
	$('#skill').val("");
	$('#projectName').val("");
	$('#company').val("");
	$('#yawnAddr').val("");
	$('#yawnContactor').val("");
	$('#yawnMobile').val("");
	$('#relationNumber').val("");
	$('#money').val("");
}


function categoryEvent(){
	if($("#projectCategory").children('option:selected').attr("internalid")==4){
		$("#_other").show();
	}else if($("#projectCategory").children('option:selected').attr("internalid")==1){
		$("#_intro").show();
		subEvent();
	}else if($("#projectCategory").children('option:selected').attr("internalid")==3){
		$("#_yawn").show();
	} else if($("#projectCategory").children('option:selected').attr("internalid")==2){
		subEvent();
	} else{
		$("#otherContent").val("");
	}
}

function subContentEvent(){
	if($("#projectCategory").children('option:selected').attr("internalid")==2){
		if($("#projectSubContentCategory").find("option:selected").text().indexOf('城镇职工医疗保险')>-1){
			$("#_dignity").show();
			$("#dignity").children('option').each(function(){
				if($(this).attr("internalid")==1||$(this).attr("internalid")==2||$(this).attr("internalid")==3) $(this).show();
				else $(this).hide();
			})
		}else {
			$("#_dignity").show();
			$("#dignity").children('option').each(function(){
				if($(this).attr("internalid")>=4&&$(this).attr("internalid")<=8) $(this).show();
				else $(this).hide();
			})
		}
	}
}
var projectSubContentCategory='${labor.projectSubContentCategory.id}';
var projectCategory ='${labor.projectCategory.id}';
function appendOption(i){
	$("#projectSubContentCategory").children('option').each(function(){
		 $(this).remove();
	})
	$("#_projectSubContentCategory option").each(function(){
		if($(this).attr("internalid")==i)
		$("#projectSubContentCategory").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
	});
	if(projectCategory!=''&&projectCategory==$('#projectCategory').val())
		$("#projectSubContentCategory").val(projectSubContentCategory);
	else{
		$("#projectSubContentCategory").val("");
	}
	
}
function subEvent(){
	$("#_content").hide();
	if($("#projectCategory").children('option:selected').attr("internalid")==1){
		$("#_content").show();
		if($("#projectSubCategory").find("option:selected").text().indexOf('求职意愿登记')>-1){
			appendOption(1);
		}else if($("#projectSubCategory").find("option:selected").text().indexOf('就业登记')>-1){
			appendOption(2);
		}else if($("#projectSubCategory").find("option:selected").text().indexOf('失业登记')>-1){
			appendOption(3);
		}else if($("#projectSubCategory").find("option:selected").text().indexOf('就业技能培训')>-1){
			appendOption(4);
		}
	}else if($("#projectCategory").children('option:selected').attr("internalid")==2){
		
		if($("#projectSubCategory").find("option:selected").text().indexOf('养老保险')>-1){
			$("#_content").show();
			$("#_ageAndCripple").show();
			$("#_dignity").show();
			appendOption(5);
			$("#dignity").children('option').each(function(){
				if($(this).attr("internalid")==1||$(this).attr("internalid")==2||$(this).attr("internalid")==8||$(this).attr("internalid")==3||$(this).attr("internalid")==5) $(this).show();
				else $(this).hide();
			})
		}else if($("#projectSubCategory").find("option:selected").text().indexOf('医疗保险')>-1){
			$("#_content").show();
			appendOption(6);
			$("#dignity").children('option').each(function(){
				$(this).hide();
			})
		}else{
			$("#_dignity").show();
			$("#dignity").children('option').each(function(){
				if($(this).attr("internalid")==1||$(this).attr("internalid")==2||$(this).attr("internalid")==8) $(this).show();
				else $(this).hide();
			})
		}
	}
}

var projectSubCategory = '${labor.projectSubCategory.id}';
$("#projectSubCategory").children('option').each(function(){
	if($(this).attr("id")>0){
		$(this).remove();
	}
	$("#projectSubCategory").val(projectSubCategory); 
})


</script>


