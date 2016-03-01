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
		<input type="hidden" name="traffic.id" id="trafficId" value="${traffic.id}" />
		<input type="hidden" id="basicId" name="traffic.peopleAspiration.id" value="${peopleAspirations.id}"/>
 		<div class="grid_5 lable-right">        
			<label class="form-lbl">工程名称：</label>
	 	</div>
		<div class="grid_6">
			<input type="text"  name="traffic.projectName" id="projectName"  maxlength="30" value="${traffic.projectName}" 
				 class='form-txt' />
		</div>
		<div class="grid_5 lable-right">
			
			<label class="form-lbl">受益人口：</label>
	 	</div>
		<div class="grid_6">
			<input type="text" name="traffic.beneficiaryNumber" id="beneficiaryNumber"  maxlength="10"  value="${traffic.beneficiaryNumber}"
				class='form-txt'/>
		</div>
 		<div class='clearLine'>&nbsp;</div>
 		<div class="grid_5 lable-right">
 			<em class="form-req">*</em>
			<label class="form-lbl">建设性质：</label>
	 	</div>
		<div class="grid_6 ">
			<select name="traffic.buildType.id" id="buildType" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_BUILD_TYPE"  	 defaultValue="${traffic.buildType.id}"/>
			</select>
		</div>
		
		<div class="grid_5 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">项目类别：</label>
	 	</div>
		<div class="grid_6 ">
		<select name="traffic.projectCategory.id" id="projectCategory" class='form-txt '  <s:if test='"view".equals(mode)'>disabled</s:if>>
		   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_PROJECT" showInternalId="true"  defaultValue="${traffic.projectCategory.id}" />
			</select>
		</div>
		<div class='clearLine'>&nbsp;</div>
			<div id="_other">
			<div class="grid_5 lable-right">
				<label class="form-lbl">其它：</label>
				</div>
			<div class="grid_18 heightAuto">
				<textarea  id="otherContent" name="traffic.otherContent"  maxlength="50" class='form-txt' >${traffic.otherContent}</textarea>
			</div>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div id="_tra">
			
			<div class="grid_5 lable-right">
				<label class="form-lbl">客运类别：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="traffic.projectSubCategory.id" id="projectSubCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_PASSTYPE"  showInternalId="true"  	 defaultValue="${traffic.projectSubCategory.id}"/>
				</select>
			</div>
			<div id="_line">
				<div class="grid_5 lable-right">
					<label class="form-lbl">班线客运：</label>
			 	</div>
				<div class="grid_6 ">
				<select name="traffic.passengerCategory.id" id="passengerCategory" class='form-txt '  <s:if test='"view".equals(mode)'>disabled</s:if>>
				   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_PASSENGER"  defaultValue="${traffic.passengerCategory.id}" />
					</select>
				</div>
			</div>
			<div id="_passengerManageCategory">
				<div class="grid_5 lable-right">
					
					<label class="form-lbl">客运站类型：</label>
			 	</div>
				<div class="grid_6 ">
				<select name="traffic.passengerManageCategory.id" id="passengerManageCategory" class='form-txt'  <s:if test='"view".equals(mode)'>disabled</s:if>>
				   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_PASSENGER_MANAGE"  defaultValue="${traffic.passengerManageCategory.id}" />
					</select>
				</div>
			</div>
			<div id="_publicTransportCategory">
				<div class="grid_5 lable-right">
					<label class="form-lbl">城市公共交通：</label>
			 	</div>
				<div class="grid_6 ">
					<select name="traffic.publicTransportCategory.id" id="publicTransportCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
				   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_PUBLIC_TRANSPORT" showInternalId="true"  	 defaultValue="${traffic.publicTransportCategory.id}"/>
					</select>
				</div>
			</div>
			<div class="grid_5 lable-right">
				<label class="form-lbl">项目内容：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="traffic.contentCategory.id" id="contentCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_SECURITY_SERVICE" showInternalId="true"  	 defaultValue="${traffic.contentCategory.id}"/>
				</select>
			</div>
			
			<div id="_level">
				<div class="grid_5 lable-right">
					<label class="form-lbl">客运等级：</label>
			 	</div>
				<div class="grid_6 ">
					<select name="traffic.passengerLevelCategory.id" id="passengerLevelCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
				   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_LEVEL"  	 defaultValue="${traffic.passengerLevelCategory.id}"/>
					</select>
				</div>
			</div>
			
			<div class='clearLine'>&nbsp;</div>
			<div id="_remark">
				<div class="grid_5 lable-right">
				<label class="form-lbl">备注：</label>
				</div>
				<div class="grid_17">
					<input type="text"  name="traffic.remark" id="remark"  maxlength="60" value="${traffic.remark}" 
						class='form-txt' />
				</div>
				<div class='clearLine'>&nbsp;</div>
			</div>
		</div>
		
		
		
		<div id="_road">
			<div class="grid_5 lable-right">
				<label class="form-lbl">道路类别：</label>
		 	</div>
			<div class="grid_6 ">
				<select name="traffic.roadCategory.id" id="roadCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_ROAD"  	 defaultValue="${traffic.roadCategory.id}"/>
				</select>
			</div>
			<div class="grid_5 lable-right">
				<em class="form-req"></em>
				<label class="form-lbl">路面类型：</label>
		 	</div>
			<div class="grid_6 ">
			<select name="traffic.roadSurfaceCategory.id" id="roadSurfaceCategory" class='form-txt'  <s:if test='"view".equals(mode)'>disabled</s:if>>
			   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_ROADSURFACE"  defaultValue="${traffic.roadSurfaceCategory.id}" />
				</select>
			</div>
			
			<div class='clearLine'>&nbsp;</div>
		</div>
		
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设起点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="traffic.fromAddress" id="fromAddress"  maxlength="60" value="${traffic.fromAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">建设讫点：</label>
			</div>
		<div class="grid_17">
			<input type="text"  name="traffic.toAddress" id="toAddress"  maxlength="60" value="${traffic.toAddress}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div id="_bri">
			<div class="grid_5 lable-right">
				<label class="form-lbl">桥长：</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="traffic.rodeLength" id="rodeLength"  maxlength="20" value="${traffic.rodeLength}" 
					class='form-txt' />
			</div>
			
	 		<div class="grid_5 lable-right">
				<label class="form-lbl">桥宽：</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="traffic.rodeWide" id="rodeWide"  maxlength="20" value="${traffic.rodeWide}" 
					class='form-txt' />
			</div>
				
			<div class='clearLine'>&nbsp;</div>
		</div>
		<div id="_roadAndSec">
	 		<div class="grid_5 lable-right">
				<label class="form-lbl">里程(公里)：</label>
				</div>
			<div class="grid_6">
				<input type="text"  name="traffic.mileage" id="mileage"  maxlength="20" value="${traffic.mileage}" 
					class='form-txt' />
			</div>
			<div id="_wide">
				<div class="grid_5 lable-right">
					<label class="form-lbl">宽：</label>
					</div>
				<div class="grid_6">
					<input type="text"  name="traffic.wide" id="wide"  maxlength="20" value="${traffic.wide}" 
						class='form-txt' />
				</div>
			</div>
			<div id="_sec">
				<div class="grid_5 lable-right">
					<label class="form-lbl">安保设施类型：</label>
			 	</div>
				<div class="grid_6 ">
					<select name="traffic.securityCategory.id" id="securityCategory" class='form-txt' <s:if test='"view".equals(mode)'>disabled</s:if>>
				   		<pop:OptionTag name="@com.tianque.plugin.account.property.PropertyTypes@TRAFFIC_SECURITY"  	 defaultValue="${traffic.securityCategory.id}"/>
					</select>
				</div>
			</div>	
			<div class='clearLine'>&nbsp;</div>
		</div>
 		<div class="grid_5 lable-right">
			<label class="form-lbl">计划资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="traffic.plannedInvestment" id="plannedInvestment"  maxlength="20" value="${traffic.plannedInvestment}" 
				class='form-txt' />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_5 lable-right">
			<label class="form-lbl">自筹资金(万元):</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="traffic.selfFund" id="selfFund"  maxlength="20" value="${traffic.selfFund}" 
				class='form-txt' />
		</div>
		<div class="grid_5 lable-right">
			<label class="form-lbl">缺口资金(万元)：</label>
			</div>
		<div class="grid_6">
			<input type="text"  name="traffic.gapFund" id="gapFund"  maxlength="20" value="${traffic.gapFund}" 
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
						if($('#trafficId').val()=="")
							$.messageBox({message:"交通研究整理信息新增成功!"});
						else 
							$.messageBox({message:"交通研究整理信息修改成功!"});
						//$("#peopleaspirationsDialog").dialog("close");
						//onOrgChanged(userOrgId,isGrid());
//						$("#issueList").trigger("reloadGrid");
						$('#trafficId').val(data.id);
						$("#maintainForm1").attr("action","${path}/threeRecords/traffic/updateTraffic.action");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"traffic.beneficiaryNumber":{
				digits:true,
				range:[1,9999999999]
			},"traffic.mileage":{
				number:true,
				validatorFloat:true
			},"traffic.wide":{
				number:true,
				validatorFloat:true
			}
			,"traffic.rodeLength":{
				number:true,
				validatorFloat:true
			},"traffic.rodeWide":{
				number:true,
				validatorFloat:true
			},"traffic.plannedInvestment":{
				number:true,
				validatorFloat:true
			}
			,"traffic.selfFund":{
				number:true,
				validatorFloat:true
			},"traffic.gapFund":{
				number:true,
				validatorFloat:true
			},"traffic.projectCategory.id":{
				required:true
			},"traffic.buildType.id":{
				required:true
			}
		},
		messages:{
			"traffic.beneficiaryNumber":{
				digits:'受益人口只能输入1到1,9999999999之间的整数',
				range:$.format('受益人口只能输入{0}到{1}之间的整数')
			}
			,"traffic.mileage":{
				number:'里程只能输入0到9999999999之间的数',
				validatorFloat:$.format('里程只能输入0到9999999999之间的数,小数保留4位有效数')
			}
			,"traffic.wide":{
				number:'宽只能输入0到9999999999之间的数',
				validatorFloat:$.format('宽只能输入0到9999999999之间的数,小数保留4位有效数')
			}
			,"traffic.rodeLength":{
				number:'桥长只能输入0到9999999999之间的数',
				validatorFloat:$.format('桥长只能输入0到9999999999之间的数,小数保留4位有效数')
			},"traffic.rodeWide":{
				number:'桥宽只能输入0到9999999999之间的数',
				validatorFloat:$.format('桥宽只能输入0到9999999999之间的数,小数保留4位有效数')
			},"traffic.plannedInvestment":{
				number:'计划投资只能输入0到9999999999之间的数',
				validatorFloat:'计划投资只能输入0到9999999999之间的数,小数保留4位有效数'
			}
			,"traffic.selfFund":{
				number:'自筹资金只能输入0到9999999999之间的数',
				validatorFloat:'自筹资金只能输入0到9999999999之间的数,小数保留4位有效数'
			},"traffic.gapFund":{
				number:'缺口资金只能输入0到9999999999之间的数',
				validatorFloat:'缺口资金只能输入0到9999999999之间的数,小数保留4位有效数'
			}
			,
			"traffic.projectCategory.id":{
				required:"请选择项目类别"
			},
			"traffic.buildType.id":{
				required:"请选择建设性质"
			}
			
		},
		ignore:':hidden'
	});
<s:if test='"add".equals(mode)'>
    $("#maintainForm1").attr("action","${path}/threeRecords/traffic/addTraffic.action");
</s:if>
<s:if test='"edit".equals(mode)'>
	if($("#trafficId").val()=="")
		 $("#maintainForm1").attr("action","${path}/threeRecords/traffic/addTraffic.action");
	else
   		 $("#maintainForm1").attr("action","${path}/threeRecords/traffic/updateTraffic.action");
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
$("#_bri").hide();
$("#_road").hide();
$("#_roadAndSec").hide();
$("#_tra").hide();
$("#_line").hide();
$("#_passengerManageCategory").hide();
$("#_publicTransportCategory").hide();
$("#_level").hide();
$("#_remark").hide();
$("#_wide").hide();
$("#_sec").hide();
$("#projectCategory").change(function(){
	$("#_other").hide();
	$("#_bri").hide();
	$("#_road").hide();
	$("#_roadAndSec").hide();
	$("#_sec").hide();
	$("#_wide").hide();
	$("#_tra").hide();
	$("#_level").hide();
	$("#_remark").hide();


	$("#rodeLength").val("");
	$("#rodeWide").val("");
	$("#mileage").val("");
	$("#wide").val("");
	$("#securityCategory").val("");
	
	$("#projectSubCategory").val("");
	$("#passengerCategory").val("");
	$("#passengerManageCategory").val("");
	$("#publicTransportCategory").val("");
	$("#passengerLevelCategory").val("");
	$("#remark").val("");
	$("#roadCategory").val("");
	$("#roadSurfaceCategory").val("");
	
	if($(this).children('option:selected').attr("internalid")==7){
		$("#_other").show();
	}else if($(this).children('option:selected').attr("internalid")==3||$(this).children('option:selected').attr("internalid")==4){
		$("#otherContent").val("");
		$("#_bri").show();
	}else if($(this).children('option:selected').attr("internalid")==1||$(this).children('option:selected').attr("internalid")==2){
		$("#otherContent").val("");
		$("#_road").show();
		$("#_roadAndSec").show();
		$("#_wide").show();
	}else if($(this).children('option:selected').attr("internalid")==5){
		$("#otherContent").val("");
		$("#_road").show();
		$("#_roadAndSec").show();
		$("#_sec").show();
	}else if($(this).children('option:selected').attr("internalid")==6){
		$("#otherContent").val("");
		$("#_tra").show();
	}
})
if($("#projectCategory").children('option:selected').attr("internalid")==7){
	$("#_other").show();
}else if($("#projectCategory").children('option:selected').attr("internalid")==3||$("#projectCategory").children('option:selected').attr("internalid")==4){
	$("#_bri").show();
}else if($("#projectCategory").children('option:selected').attr("internalid")==1||$("#projectCategory").children('option:selected').attr("internalid")==2){
	$("#_road").show();
	$("#_roadAndSec").show();
	$("#_wide").show();
}else if($("#projectCategory").children('option:selected').attr("internalid")==5){
	$("#_road").show();
	$("#_roadAndSec").show();
	$("#_sec").show();
}else if($("#projectCategory").children('option:selected').attr("internalid")==6){
	$("#_tra").show();
}
if($("#projectSubCategory").children('option:selected').attr("internalid")==1){
	$("#_line").show();
}else if($("#projectSubCategory").children('option:selected').attr("internalid")==2){
	 $("#_publicTransportCategory").show();
}else if($("#projectSubCategory").children('option:selected').attr("internalid")==3||$("#projectSubCategory").children('option:selected').attr("internalid")==4){
	$("#_remark").show();
	$("#_passengerManageCategory").show();
	$("#_level").show();	
}


$("#projectSubCategory").change(function(){
	$("#_line").hide();
	$("#_level").hide();
	$("#_remark").hide();
	$("#_passengerManageCategory").hide();
	$("#_publicTransportCategory").hide();
	$("#contentCategory option").each(function(i){
		if(i!=0){
		  if($(this).attr("internalid")!=0)$(this).hide();
		}
	})
	$("#contentCategory").val("");
	 if($(this).children('option:selected').attr("internalid")==1){
		 $("#contentCategory option").each(function(){
			if($(this).attr("internalid")==1)$(this).show();
		})
		$("#_line").show();
	}if($(this).children('option:selected').attr("internalid")==3||$(this).children('option:selected').attr("internalid")==4){
		 $("#contentCategory option").each(function(){
				if($(this).attr("internalid")==4)$(this).show();
			})
		$("#_remark").show();	
		$("#_level").show();
		$("#_passengerManageCategory").show();
			
	}if($(this).children('option:selected').attr("internalid")==2){
		 $("#_publicTransportCategory").show();
	}
})
$("#publicTransportCategory").change(function(){
	$("#contentCategory option").each(function(i){
		if(i!=0){
		  if($(this).attr("internalid")!=0)$(this).hide();
		}
	})
	$("#contentCategory").val("");
	if($(this).children('option:selected').attr("internalid")==1){
		 $("#contentCategory option").each(function(){
			if($(this).attr("internalid")==2)$(this).show();
		})
	}else if($(this).children('option:selected').attr("internalid")==2){
		 $("#contentCategory option").each(function(){
			if($(this).attr("internalid")==3)$(this).show();
		})
	}
})



})

</script>


