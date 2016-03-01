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
		<input type="hidden" name="education.id" id="educationId" value="${education.id}" />
		<input type="hidden" id="basicId" name="education.peopleAspiration.id" value="${peopleAspirations.id}"/>
 		
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">项目类别：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="education.projectCategory.id" id="projectCategory" class='form-txt '  <s:if test='"view".equals(mode)'>disabled</s:if>>
				<pop:PropertyDictReleationSelectTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_PROJECT"  defaultValue="${education.projectCategory.id}" 
			   		reletionId="projectSubCategory"    reletionName="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_PROJECT_SUB" id="projectCategory"/>
			</select>
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">项目内容：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="education.projectSubCategory.id" id="projectSubCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_PROJECT_SUB"  showInternalId="true"	 defaultValue="${education.projectSubCategory.id}"/>
			</select>
		</div>
		
		
		<div class='clearLine'>&nbsp;</div>
		
		
		<div id="_other">
			<div class="grid_5 lable-right">
				<label class="form-lbl">其它：</label>
				</div>
			<div class="grid_18 heightAuto">
				<textarea  id="otherContent" name="education.otherContent"  maxlength="50" class='form-txt' >${education.otherContent}</textarea>
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
		<div id='_project'>
		<div class="grid_5 lable-right">        
			<label class="form-lbl">工程名称：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="education.projectName" id="projectName"  maxlength="30" value="${education.projectName}" 
				 class='form-txt' />
		</div>
 		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设性质：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="education.buildType.id" id="buildType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_BUILD_TYPE"  	 defaultValue="${education.buildType.id}"/>
			</select>
		</div>
		
		
		<div class="grid_5 lable-right">
			
			<label class="form-lbl">受益人口：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" name="education.beneficiaryNumber" id="beneficiaryNumber"  maxlength="10"  value="${education.beneficiaryNumber}"
				class='form-txt'/>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		
			<div class="grid_5 lable-right">
			<label class="form-lbl">建设单位名称：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" name="education.buildCompanyName" id="buildCompanyName"  maxlength="6"  value="${education.buildCompanyName}"
				class='form-txt'/>
		</div>
		
		<div id="_addressCategory">
			<div class="grid_5 lable-right">
				<label class="form-lbl">建设类型：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="education.addressCategory.id" id="addressCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_ADDRESS_TYPE" showInternalId="true"  	 defaultValue="${education.addressCategory.id}"/>
				</select>
			</div>
		</div>
		
		<div id="_scopeCategory">
			<div class="grid_5 lable-right">
				<label class="form-lbl">规模：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="education.scopeCategory.id" id="scopeCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_SCOPE_TYPE"  showInternalId="true" 	 defaultValue="${education.scopeCategory.id}"/>
				</select>
			</div>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设面积：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" name="education.buildArea" id="buildArea"  maxlength="6"  value="${education.buildArea}"
				class='form-txt'/>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设起点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="education.fromAddress" id="fromAddress"  maxlength="60" value="${education.fromAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设讫点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="education.toAddress" id="toAddress"  maxlength="60" value="${education.toAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
		
   		</div>
   		<div class="grid_5 lable-right">
			<label class="form-lbl">计划资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="education.plannedInvestment" id="plannedInvestment"  maxlength="20" value="${education.plannedInvestment}" 
				class='form-txt' />
		</div>
	
		<div class="grid_5 lable-right">
			<label class="form-lbl">自筹资金(万元):</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="education.selfFund" id="selfFund"  maxlength="20" value="${education.selfFund}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">缺口资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="education.gapFund" id="gapFund"  maxlength="20" value="${education.gapFund}" 
				class='form-txt' readonly/>
		</div>
		
   		<div class='clearLine'>&nbsp;</div>
		<div id="_student">
			<div id="_school">
		 		<div class="grid_5 lable-right">
					<label class="form-lbl">学生姓名：</label>
					</div>
				<div class="grid_6">
					<input type="text"  name="education.studentName" id="studentName"  maxlength="20" value="${education.studentName}" 
						class='form-txt' />
				</div>
			
				<div class="grid_5 lable-right">
					<label class="form-lbl">学生就读学校：</label>
					</div>
				<div class="grid_6">
					<input type="text"  name="education.schoolName" id="schoolName"  maxlength="20" value="${education.schoolName}" 
						class='form-txt' />
				</div>
			</div>
			<div class='clearLine'>&nbsp;</div>
			
			<div id="_degree">
				<div class="grid_5 lable-right">
				<label class="form-lbl">就学类型：</label>
			 	</div>
				<div class="grid_6 ">
					<select name="education.degreeCategory.id" id="degreeCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
				   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_DEGREE_TYPE"  showInternalId="true"	 defaultValue="${education.degreeCategory.id}"/>
					</select>
				</div>
			</div>
			<div id="_studyDate">
				<div class="grid_5 lable-right">
				<label class="form-lbl" id="_date">时间：</label>
			 	</div>
				<div class="grid_6 ">
					<input type="text"  name="education.studyDate" id="studyDate"  maxlength="20" value="<s:date name="education.studyDate" format="yyyy-MM-dd "/>" 
						class='form-txt' />
				</div>
			</div>
			<div id="_mode">
				<div class="grid_5 lable-right">
				<label class="form-lbl">方式：</label>
			 	</div>
				<div class="grid_6 ">
					<select name="education.modeCategory.id" id="modeCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
				   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_MODE_TYPE"  	 defaultValue="${education.modeCategory.id}"/>
					</select>
				</div>
			</div>
			<div id="_item">
				<div class="grid_5 lable-right">
				<label class="form-lbl">项目名称：</label>
			 	</div>
				<div class="grid_6 ">
					<select name="education.itemCategory.id" id="itemCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
				   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_ITEM_TYPE"  showInternalId="true"	 defaultValue="${education.itemCategory.id}"/>
					</select>
				</div>
			</div>
			
			<div id="_road">
				<!--<div class="grid_5 lable-right">
				<label class="form-lbl">上学路途难类型：</label>
			 	</div>
				<div class="grid_6 ">
					<select name="education.roadCategory.id" id="roadCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
				   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_ROAD_TYPE" showInternalId="true" 	 defaultValue="${education.roadCategory.id}"/>
					</select>
				</div>  -->
				<div id="_distanceCategory">
					<div class="grid_5 lable-right">
					<label class="form-lbl">路程：</label>
				 	</div>
					<div class="grid_6 ">
						<select name="education.distanceCategory.id" id="distanceCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
					   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_DISTANCE_TYPE"  	 defaultValue="${education.distanceCategory.id}"/>
						</select>
					</div>
				</div>
				<div id="_roadConditionCategory">
					<div class="grid_5 lable-right">
					<label class="form-lbl">路况：</label>
				 	</div>
					<div class="grid_6 ">
						<select name="education.roadConditionCategory.id" id="roadConditionCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
					   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@EDUCATION_ROAD_CONDITION_TYPE"  	 defaultValue="${education.roadConditionCategory.id}"/>
						</select>
					</div>
				</div>
			</div>
			
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
						if($('#educationId').val()=="")
							$.messageBox({message:"能源研究整理信息新增成功!"});
						else 
							$.messageBox({message:"能源研究整理信息修改成功!"});
						//$("#peopleaspirationsDialog").dialog("close");
						//onOrgChanged(userOrgId,isGrid());
				//		$("#issueList").trigger("reloadGrid");
						$('#educationId').val(data.id);
						$("#maintainForm1").attr("action","${path}/threeRecords/education/updateEducation.action");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"education.beneficiaryNumber":{
				digits:true,
				range:[1,9999999999]
			},"education.educationNumber":{
				digits:true,
				range:[1,9999999999]
			}
			,"education.plannedInvestment":{
				number:true,
				validatorMoney:true
			}
			,"education.selfFund":{
				number:true,
				validatorMoney:true
			},"education.gapFund":{
				number:true,
				validatorMoney:true
			},
			"education.projectCategory.id":{
				required:true
			}
		},
		messages:{
			"education.beneficiaryNumber":{
				digits:'受益人口只能输入1到1,9999999999之间的整数',
				range:$.format('受益人口只能输入{0}到{1}之间的整数')
			}
			,"education.educationNumber":{
				digits:'数量只能输入1到1,9999999999之间的整数',
				range:$.format('里程只能输入{0}到{1}之间的整数')
			}
			,"education.plannedInvestment":{
				number:'计划投资只能输入0到9999999999之间的数',
				validatorMoney:'计划投资只能输入0到9999999999之间的数,小数保留4位有效数'
			}
			,"education.selfFund":{
				number:'自筹资金只能输入0到9999999999之间的数',
				validatorMoney:'自筹资金只能输入0到9999999999之间的数,小数保留4位有效数'
			},"education.gapFund":{
				number:'缺口资金只能输入0到9999999999之间的数',
				validatorMoney:'缺口资金只能输入0到9999999999之间的数,小数保留4位有效数'
			},
			"education.projectCategory.id":{
				required:"请选择项目类别"
			}
			
			
		},
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#maintainForm1").attr("action","${path}/threeRecords/education/addEducation.action");
</s:if>
<s:if test='"edit".equals(mode)'>
	if($("#educationId").val()=="")
		 $("#maintainForm1").attr("action","${path}/threeRecords/education/addEducation.action");
	else
   		 $("#maintainForm1").attr("action","${path}/threeRecords/education/updateEducation.action");
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
$("#_project").hide();
$("#_student").hide();
$("#_other").hide();
$("#_scopeCategory").hide();
$("#_addressCategory").hide();
$("#_road").hide();
$("#_school").hide();
$("#_studyDate").hide();
$("#_mode").hide();
$("#_item").hide();
$("#_degree").hide();

$("#projectCategory").change(function(){
	$('#buildCompanyName').val("");
	$('#addressCategory').val("");
	$('#scopeCategory').val("");
	$('#buildArea').val("");
	$('#fromAddress').val("");
	$('#toAddress').val("");
	$('#studentName').val("");
	$('#schoolName').val("");
	$('#degreeCategory').val("");
	$('#studyDate').val("");
	$('#modeCategory').val("");
	$('#itemCategory').val("");
	$('#otherContent').val("");
	$('#distanceCategory').val("");
	$('#roadConditionCategory').val("");
	$('#projectName').val("");
	$('#beneficiaryNumber').val("");
	$("#_other").hide();
	projectCategoryEvent();
})
projectCategoryEvent();
$("#projectSubCategory").change(function(){
	$("#scopeCategory").val("");
	$("#addressCategory").val("");
	$("#degreeCategory").val("");
	$("#itemCategory").val("");
	$("#modeCategory").val("");
	projectSubCategoryEvent();
})
projectSubCategoryEvent();

})



$("#roadCategory").change(function(){
	$("#_distanceCategory").val("");
	$("#_roadConditionCategory").val("");
})

function projectCategoryEvent(){
	$("#_other").hide();
	$("#_student").hide();
	$("#_project").hide();
	if($("#projectCategory").children('option:selected').attr("internalid")==3){
		$("#_other").show();
	}else   if($("#projectCategory").children('option:selected').attr("internalid")==1){
		$("#_project").show();
	}else   if($("#projectCategory").children('option:selected').attr("internalid")==2){
		$("#_student").show();
	}
		
}

initDateSelector();
var projectSubCategory = '${education.projectSubCategory.id}';
$("#projectSubCategory").children('option').each(function(){
	if($(this).attr("id")>0){
		$(this).remove();
	}
	$("#projectSubCategory").val(projectSubCategory); 
	})

function initDateSelector(){
	if($("#ui-datepicker-div")[0]){
		$("body").append("<div id='ui-datepicker-div' />")
	}
	$('#studyDate').datePicker({
		yearRange:'1930:2060',
		dateFormat:'yy-mm-dd'
		//maxDate:'%y-%M-#{%d}'
	});
}



function projectSubCategoryEvent(){
	
	if($("#projectSubCategory").find("option:selected").text().indexOf("幼儿园建设")>-1){
		$("#_scopeCategory").show();
		$("#_addressCategory").show();
		showAddressCategory(1);
		$("#scopeCategory").children('option').each(function(){
			if($(this).attr("internalid")==0||$(this).attr("internalid")==1)$(this).show();
			else $(this).hide();
		})
	}else if($("#projectSubCategory").find("option:selected").text().indexOf("农村薄弱学校改造")>-1||$("#projectSubCategory").find("option:selected").text().indexOf("危房改造")>-1){
		$("#_scopeCategory").show();
		$("#_addressCategory").show();
		showAddressCategory(2);
		$("#scopeCategory").children('option').each(function(){
			if($(this).attr("internalid")==0||$(this).attr("internalid")==2)$(this).show();
			else $(this).hide();
		})
	}else if($("#projectSubCategory").find("option:selected").text().indexOf("教师周转房建设")>-1){
		$("#_scopeCategory").show();
		$("#_addressCategory").show();
		showAddressCategory(2);
		$("#scopeCategory").children('option').each(function(){
			if($(this).attr("internalid")==3)$(this).show();
			else $(this).hide();
		})
	}else if($("#projectSubCategory").find("option:selected").text().indexOf("维修维护")>-1){
		$("#_scopeCategory").show();
		$("#_addressCategory").show();
		showAddressCategory(2);
		$("#scopeCategory").children('option').each(function(){
			if($(this).attr("internalid")==4)$(this).show();
			else $(this).hide();
		})
	}else if($("#projectSubCategory").find("option:selected").text().indexOf("贫困大学新生资助")>-1){
		$("#_road").hide();
		$("#_school").show();
		$("#_studyDate").show();
		$("#_date").text("资助时间：");
		$("#_mode").show();
		$("#_degree").show();
		$("#_item").hide();
		showDegreeCategory("1");
	}else if($("#projectSubCategory").find("option:selected").text().indexOf("两免一补")>-1){
		$("#_road").hide();
		$("#_school").show();
		$("#_studyDate").show();
		$("#_date").text("免补时间：");
		$("#_item").show();
		$("#_mode").hide();
		$("#_degree").show();
		showItemCategory("");
		showDegreeCategory("2");
	}else if($("#projectSubCategory").find("option:selected").text().indexOf("进城农民工子女就读")>-1){
		$("#_road").hide();
		$("#_school").show();
		$("#_studyDate").show();
		$("#_date").text("拟进城入学时间：");
		$("#_mode").hide();
		$("#_degree").show();
		$("#_item").hide();
		showDegreeCategory("0,2");
	}else if($("#projectSubCategory").find("option:selected").text().indexOf("学杂费及生活困难补助")>-1){
		$("#_road").hide();
		$("#_school").show();
		$("#_studyDate").show();
		$("#_date").text("补助时间：");
		$("#_mode").hide();
		$("#_degree").show();
		$("#_item").show();
		showDegreeCategory("1");
		showItemCategory("1");
	}
	else if($("#projectSubCategory").find("option:selected").text().indexOf("上学路途难")>-1){
		$("#_road").show();
		$("#_school").hide();
		$("#_studyDate").hide();
		$("#_mode").hide();
		$("#_item").hide();
		$("#_degree").hide();
	}
}

function showAddressCategory(id){
	$("#addressCategory").children('option').each(function(){
		if($(this).attr("internalid")==id)$(this).show();
		else $(this).hide();
	})
}

function showDegreeCategory(id){
	var ids = id.split(",");
	$("#degreeCategory").children('option').each(function(){
		 $(this).hide();
	})
	for(var i=0;i<ids.length;i++)
	$("#degreeCategory").children('option').each(function(){
		if($(this).attr("internalid")==ids[i])$(this).show();
	})
}
function showItemCategory(id){
	if(id!=""){ 
		$("#itemCategory").children('option').each(function(){
			if($(this).attr("internalid")==id)$(this).show();
			else $(this).hide();
		})
	}
}
</script>


