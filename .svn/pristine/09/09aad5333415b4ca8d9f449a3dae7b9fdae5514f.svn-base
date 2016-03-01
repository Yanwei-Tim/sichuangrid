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
		<input type="hidden" name="science.id" id="scienceId" value="${science.id}" />
		<input type="hidden" id="basicId" name="science.peopleAspiration.id" value="${peopleAspirations.id}"/>
 		<div class="grid_5 lable-right">        
			<label class="form-lbl">工程名称：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="science.projectName" id="projectName"  maxlength="30" value="${science.projectName}" 
				 class='form-txt' />
		</div>
		<div class="grid_5 lable-right">
			
			<label class="form-lbl">受益人口：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" name="science.beneficiaryNumber" id="beneficiaryNumber"  maxlength="10"  value="${science.beneficiaryNumber}"
				class='form-txt'/>
		</div>
 		<div class='clearLine'>&nbsp;</div>
 		<div class="grid_5 lable-right">
			<label class="form-lbl">建设性质：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="science.buildType.id" id="buildType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_BUILD_TYPE"  	 defaultValue="${science.buildType.id}"/>
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
 		
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">类别：</label>
	 	</div>
		<div class="grid_6 ">
		<select name="science.projectCategory.id" id="projectCategory" class="form-txt {required:true,messages:{required:'请选择类别'}}"  <s:if test='"view".equals(mode)'>disabled</s:if>>
			<pop:PropertyDictReleationSelectTag name="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_PROJECT"  defaultValue="${science.projectCategory.id}" 
		   		reletionId="projectSubCategory"    reletionName="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_PROJECT_SUB" id="projectCategory"/>
			
			</select>
			
		</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">项目类型：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="science.projectSubCategory.id" id="projectSubCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_PROJECT_SUB"  	 defaultValue="${science.projectSubCategory.id}"/>
			</select>
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		
		
		<div id="_other">
			<div class="grid_5 lable-right">
				<label class="form-lbl">其它：</label>
				</div>
			<div class="grid_18 heightAuto">
				<textarea  id="otherContent" name="science.otherContent"  maxlength="50" class='form-txt' >${science.otherContent}</textarea>
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div id="_levelAndName">
			<div class="grid_5 lable-right">
				<em class="form-req"></em>
				<label class="form-lbl">项目级别：</label>
		 	</div>
			<div class="grid_6 ">
			<select name="science.projectLevelCategory.id" id="projectLevelCategory" class='form-txt'  <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_PROJECT_LEVEL"  defaultValue="${science.projectLevelCategory.id}" />
				</select>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">项目名称：</label>
		 	</div>
			<div class="grid_6 ">
				<input type="text"  name="science.itemName" id="itemName"  maxlength="20" value="${science.itemName}" 
					class='form-txt' />
			</div>
			<div class='clearLine'>&nbsp;</div>
		</div>
		
		<div id="_broadType">		
			<div class="grid_5 lable-right">
				<em class="form-req"></em>
				<label class="form-lbl">广播电视播放方式：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="science.contentCategory.id" id="contentCategory" class='form-txt'  <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@SCIENCE_BROADCAST" showInternalId="true"  defaultValue="${science.contentCategory.id}" />
				</select>
			</div>
		</div>
		<div id="_scienceScope">	
			<div class="grid_5 lable-right">
				<label class="form-lbl">数量（户、个、场）：</label>
		 	</div>
			<div class="grid_6 ">
				<input type="text"  name="science.scienceScope" id="scienceScope"  maxlength="20" value="${science.scienceScope}" 
					class='form-txt' />
			</div>
			<div class='clearLine'>&nbsp;</div>
		</div>
		
	
		<div id="_publicizeNumAndContent">
			<div class="grid_5 lable-right">
				<label class="form-lbl">宣传次数：</label>
		 	</div>
			<div class="grid_6 ">
				<input type="text"  name="science.publicizeNum" id="publicizeNum"  maxlength="20" value="${science.publicizeNum}" 
					class='form-txt' />
			</div>
			<div class='clearLine'>&nbsp;</div>
			
			<div class="grid_5 lable-right">
				<label class="form-lbl">主题内容：</label>
				</div>
			<div class="grid_18 heightAuto">
				<textarea  id="content" name="science.content"  maxlength="50" class='form-txt' >${science.content}</textarea>
			</div>
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设起点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="science.fromAddress" id="fromAddress"  maxlength="60" value="${science.fromAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设讫点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="science.toAddress" id="toAddress"  maxlength="60" value="${science.toAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
 		<div class="grid_5 lable-right">
			<label class="form-lbl">计划资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="science.plannedInvestment" id="plannedInvestment"  maxlength="20" value="${science.plannedInvestment}" 
				class='form-txt' />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">自筹资金(万元):</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="science.selfFund" id="selfFund"  maxlength="20" value="${science.selfFund}" 
				class='form-txt' />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">缺口资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="science.gapFund" id="gapFund"  maxlength="20" value="${science.gapFund}" 
				class='form-txt' readonly/>
		</div>
		
   		<div class='clearLine'></div>
 		
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
						if($('#scienceId').val()=="")
							$.messageBox({message:"科技文体研究整理信息新增成功!"});
						else 
							$.messageBox({message:"科技文体研究整理信息修改成功!"});
						//$("#peopleaspirationsDialog").dialog("close");
						//onOrgChanged(userOrgId,isGrid());
						//$("#issueList").trigger("reloadGrid");
						$('#scienceId').val(data.id);
						$("#maintainForm1").attr("action","${path}/threeRecords/science/updateScience.action");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"science.beneficiaryNumber":{
				digits:true,
				range:[1,9999999999]
			},"science.scienceNumber":{
				digits:true,
				range:[1,9999999999]
			}
			,"science.plannedInvestment":{
				number:true,
				validatorMoney:true
			}
			,"science.selfFund":{
				number:true,
				validatorMoney:true
			},"science.gapFund":{
				number:true,
				validatorMoney:true
			}
		},
		messages:{
			"science.beneficiaryNumber":{
				digits:'受益人口只能输入1到1,9999999999之间的整数',
				range:$.format('受益人口只能输入{0}到{1}之间的整数')
			}
			,"science.scienceScope":{
				digits:'规模只能输入1到1,9999999999之间的整数',
				range:$.format('里程只能输入{0}到{1}之间的整数')
			}
			,"science.plannedInvestment":{
				number:'计划投资只能输入0到9999999999之间的数',
				validatorMoney:'计划投资只能输入0到9999999999之间的数,小数保留4位有效数'
			}
			,"science.selfFund":{
				number:'自筹资金只能输入0到9999999999之间的数',
				validatorMoney:'自筹资金只能输入0到9999999999之间的数,小数保留4位有效数'
			},"science.gapFund":{
				number:'缺口资金只能输入0到9999999999之间的数',
				validatorMoney:'缺口资金只能输入0到9999999999之间的数,小数保留4位有效数'
			}
			
			
		},
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#maintainForm1").attr("action","${path}/threeRecords/science/addScience.action");
</s:if>
<s:if test='"edit".equals(mode)'>
	if($("#scienceId").val()=="")
		 $("#maintainForm1").attr("action","${path}/threeRecords/science/addScience.action");
	else
   		 $("#maintainForm1").attr("action","${path}/threeRecords/science/updateScience.action");
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
$("#_publicizeNumAndContent").hide();
$("#_levelAndName").hide();
$("#_scienceScope").hide();
$("#_broadType").hide();


$("#projectCategory").change(function(){
	$("#publicizeNum").val("");
	$("#content").val("");
	$("#projectLevelCategory").val("");
	$("#itemName").val("");
	$("#scienceScope").val("");
	
	$("#_publicizeNumAndContent").hide();
	$("#_levelAndName").hide();
	$("#_other").hide();
	$("#_scienceScope").hide();
	$("#_broadType").hide();
	$("#contentCategory").val("");
	projectCategoryEvent();
})
$("#projectSubCategory").change(function(){
	$("#_broadType").hide();
	$("#contentCategory").val("");
	projectSubCategoryEvent();
})
projectCategoryEvent();
projectSubCategoryEvent();
var projectSubCategory='${science.projectSubCategory.id}';
$("#projectSubCategory").children('option').each(function(){
	if($(this).attr("id")>0){
		$(this).remove();
	}
	$("#projectSubCategory").val(projectSubCategory); 
	})
})

function projectCategoryEvent(){
	if($("#projectCategory").children('option:selected').attr("internalid")==7){
		$("#_other").show();
	}else if($("#projectCategory").children('option:selected').attr("internalid")==5){
		$("#_levelAndName").show();
		$("#projectSubCategory").val("");
	}else if($("#projectCategory").children('option:selected').attr("internalid")==6){
		$("#_publicizeNumAndContent").show();
		$("#projectSubCategory").val("");
	}else if($("#projectCategory").children('option:selected').attr("internalid")==1){
		projectSubCategoryEvent();
	}
	else{
		$("#_scienceScope").show();
	}
}

function projectSubCategoryEvent(){
	
	if($("#projectCategory").children('option:selected').attr("internalid")==1){
		$("#_broadType").show();
		$("#_scienceScope").show();
		if($("#projectSubCategory").find("option:selected").text().indexOf("户户")>-1){
			$("#contentCategory").children('option').each(function(){
				if($(this).attr("internalid")!=1&&$(this).attr("internalid")!=7)$(this).hide();
				else{
					$(this).show();
				}
			})
			
		}else if($("#projectSubCategory").find("option:selected").text().indexOf("村村")>-1){
			$("#contentCategory").children('option').each(function(){
				if($(this).attr("internalid")!=2&&$(this).attr("internalid")!=7)$(this).hide();
				else{
					$(this).show();
				}
			})
		}else {
			$("#contentCategory").children('option').each(function(){
				if($(this).attr("internalid")!=3&&$(this).attr("internalid")!=7)$(this).hide();
				else{
					$(this).show();
				}
			})
			
		}
	}
}

</script>


