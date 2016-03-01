<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="newEconomicOrganizations" class="container container_24" style="width:670px;">

	<form
		action="${path}/baseinfo/newEconomicOrganizationsManage/saveNewEconomicOrganizations.action"
		method="post" id="maintainForm">
		<pop:token />
		<c:if test='${mode=="add"}'>
			<pop:token/>
		</c:if>
		<div id="perUuid"></div>
		<input id="mode" type="hidden" name="mode" value="${mode}" />
		<input id="_imgUrl" name="newEconomicOrganizations.imgUrl" type="hidden" value="${newEconomicOrganizations.imgUrl}"/>
		<input id="newEconomicOrganizationsID" type="hidden" name="newEconomicOrganizations.id" value="${newEconomicOrganizations.id }">
		<input id="orgId" type="hidden" name="newEconomicOrganizations.organization.id" value="${organizationId}" />
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
		<em class="form-req">*</em>
			<label class="form-lb1">所属网格：</label>
		</div>
		<div class="grid_18">
			<input type="text" id="commonOrgName" name="newEconomicOrganizations.organization.orgName" value="${newEconomicOrganizations.organization.orgName}" style="width: 99%" class="form-txt" readonly  />
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">名称：</label>
		</div>
		<div class="grid_18">                  
			<input type="text" id="name" name="newEconomicOrganizations.name"
				value="${newEconomicOrganizations.name}" style="width: 99%"
				class="form-txt" maxlength="20"/>
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">住所：</label>
		</div>
		<div class="grid_18">
			<input type="text" id="residence"
				name="newEconomicOrganizations.residence"
				value="${newEconomicOrganizations.residence}" style="width: 99%"
				class="form-txt" maxlength="50"/>
		</div>
		<div class='clearLine'>&nbsp;</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1">营业执照号码：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="newEconomicOrganizations.licenseNumber"
				id="licenseNumber" value="${newEconomicOrganizations.licenseNumber}"
				class='form-txt' maxlength="20"/>
		</div>

		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em><label class="form-lb1">有效期：</label>
		</div>
		<div class="grid_3">
			<input type="text" name="newEconomicOrganizations.validityStartDate"
				id="validityStartDate" readonly
				value='<fmt:formatDate value="${newEconomicOrganizations.validityStartDate}" type="date" pattern="yyyy-MM-dd" />'
				class="form-txt" />
		</div>
		<div class="grid_1 lable-right">&nbsp;至：</div>
		<div class="grid_3">
			<input type="text" name="newEconomicOrganizations.validityEndDate"
				id="validityEndDate" readonly
				value='<fmt:formatDate value="${newEconomicOrganizations.validityEndDate}" type="date" pattern="yyyy-MM-dd" />'
				class="form-txt" />
		</div>


		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em><label class="form-lb1">类别：</label>
		</div>
		<div class="grid_7">
			<select name="newEconomicOrganizations.style.id" id="style"
				class="form-txt">
				<pop:OptionTag
					name="@com.tianque.domain.property.PropertyTypes@NEWECONOMICORGANIZATIONS_STYLE"
					defaultValue="${newEconomicOrganizations.style.id}" />
			</select>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*&nbsp;</em><label class="form-lb1">负责人：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="newEconomicOrganizations.chief"
				id="chief" maxlength="15"
				value="${newEconomicOrganizations.chief}" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">联系手机：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="newEconomicOrganizations.mobileNumber"
				id="mobileNumber" maxlength="11"
				value="${newEconomicOrganizations.mobileNumber}" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">固定电话：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="newEconomicOrganizations.telephone"
				id="telephone" maxlength="20"
				value="${newEconomicOrganizations.telephone }" class="form-txt" />
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lb1">传真号码：</label>
		</div>
		<div class="grid_7">
			<input type="text" name="newEconomicOrganizations.foxNumber"
				id="foxNumber" maxlength="20"
				value="${newEconomicOrganizations.foxNumber}" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">面积：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="newEconomicOrganizations.area"
				id="area" 
				value="${newEconomicOrganizations.areaStringValue}" class="form-txt" maxlength="9"/>
		</div>
		<div class="grid_1"><font size="1">m</font><font size="1"><sup>2</sup></font> </div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lb1">从业人数：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="newEconomicOrganizations.employeeNumber"
				id="employeeNumber" 
				value="${newEconomicOrganizations.employeeNumber}" class="form-txt" style="text-align:right;" maxlength="9"/>
		</div>
		<div class="grid_1 lable-right">
  			<label class="form-lbl">个</label>
  		</div>
		<div class="grid_4 lable-right">
			<label class="form-lb1">党员人数：</label>
		</div>
		<div class="grid_6">
			<input type="text" name="newEconomicOrganizations.partyMemberNumber"
				id="partyMemberNumber" 
				value="${newEconomicOrganizations.partyMemberNumber}" class="form-txt" style="text-align:right;" maxlength="9"/>
		</div>
		<div class="grid_1 lable-right">
  			<label class="form-lbl">个</label>
  		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
   			<label class="form-lb1">简介：</label>
   		</div>
   		<div class="grid_18">
   			<textarea  name="newEconomicOrganizations.introduction" id="introduction" class="form-txt" style="height: 3em;">${newEconomicOrganizations.introduction }</textarea>
   		</div>
   		<div class="clearLine"></div>
        <br/>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">荣誉：</label>
   		</div>
   		<div class="grid_18">
   			<textarea name="newEconomicOrganizations.honor" id="honor" class="form-txt" style="height: 3em;">${newEconomicOrganizations.honor }</textarea>
   		</div>
		<div class="clearLine"></div>
        <br/>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">备注：</label>
   		</div>
   		<div class="grid_18">
   			<textarea rows="3" name="newEconomicOrganizations.remark" id="remark" class="form-txt">${newEconomicOrganizations.remark}</textarea>
   		</div>
   		<input name="isSubmit" id="isSubmit" type="hidden" value="" />
	</form>
</div>

<script type="text/javascript">
$('#validityStartDate').datePicker({
		yearRange : '1900:2030',
		dateFormat : 'yy-mm-dd',
		maxDate:'+0d'
		
		});
$('#validityEndDate').datePicker({
	yearRange : '1900:2030',
	dateFormat : 'yy-mm-dd',
	minDate:'+1d'
	
	});

$(document).ready(function(){
	<c:if test='${mode=="add"}'>
	$.ajax({
		async: false,
		url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id" : $("#currentOrgId").val()
		},
		success:function(responseData){
			$("#commonOrgName").val(responseData);
		}
	});
	</c:if>
	
	if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
		$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
		$(".shadow").show();
	}
	if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
		$("#img").attr("src","${path }/"+$("#_imgUrl").val());
	};
	
	jQuery.validator.addMethod("peopleNumber", function(value, element) {   
		var stature = /^[0-9]*[1-9][0-9]*$/;
		return this.optional(element) || (value <= 999999999 && stature.test(value));       
	});
	
	jQuery.validator.addMethod("partyMemberNumber", function(value, element){
		if(value==null||value==undefined||value==""){return true}
		var employeeNumber = $("#employeeNumber").val();
		if(employeeNumber==null||employeeNumber==undefined||employeeNumber==""){return false;}
		if(value>eval(employeeNumber)){
			return false;
		}else{
			return true;
		}
	});
	jQuery.validator.addMethod("exsistedName", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:"${path}/baseinfo/newEconomicOrganizationsManage/hasDuplicateNewEconomicOrganizations.action",
		   	data:{
				"newEconomicOrganizations.name":$('#name').val(),
				"organizationId":$('#orgId').val(),
				"mode":$('#mode').val(),
				"newEconomicOrganizations.id":$('#newEconomicOrganizationsID').val()
	        },
			success:function(responseData){
				flag = !eval(responseData);
			}
		});
		return flag;
	});
	jQuery.validator.addMethod("exsistedLicenseNumber", function(value, element){
		var flag =true;
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:"${path}/baseinfo/newEconomicOrganizationsManage/hasDuplicateNewEconomicOrganizations.action",
		   	data:{
				"newEconomicOrganizations.licenseNumber":$("#licenseNumber").val(),
				"organizationId":$('#orgId').val(),
				"mode":$('#mode').val(),
				"newEconomicOrganizations.id":$('#newEconomicOrganizationsID').val()
	        },
			success:function(responseData){
				if(responseData!=true && responseData!=false){
					$.messageBox({
						message:responseData,
						level: "error"
		 			});
        			return false;
				}
				flag = !eval(responseData);
			}
		});
		return flag;
	});
	
	
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
		 $("#_imgUrl").val($("#imgUrl").val());
		 $(form).ajaxSubmit({
			 async : false,
			 success : function(data) {
				if (!data.id) {
					$.messageBox({
						message:data,
						level: "error"
		 			});
        			return;
				}
				 $("#<s:property value='#parameters.dailogName[0]'/>").proccessSuccess(data.id,data);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("提交错误");
			}
			});
		},
		rules:{
			"newEconomicOrganizations.name":{
				required:true,
				isLawful:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20,
				exsistedName:true
			},
			"newEconomicOrganizations.residence":{
				required:true
			},
			"newEconomicOrganizations.licenseNumber":{
				required:true,
				exculdeParticalChar:true,
				exsistedLicenseNumber:true,
				isLawful:true
			},
			"newEconomicOrganizations.validityStartDate":{
				required:true
			},
			"newEconomicOrganizations.validityEndDate":{
				required:true
			},
			"newEconomicOrganizations.style.id":{
				required:true
			},
			"newEconomicOrganizations.chief":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"newEconomicOrganizations.telephone":{
				telephone:true
			},
			"newEconomicOrganizations.foxNumber":{
				telephone:true
			},
			"newEconomicOrganizations.mobileNumber":{
				mobile:true
			},
			"newEconomicOrganizations.area":{
				number:true,
				min:0,
				max:999999999
			},
			"newEconomicOrganizations.employeeNumber":{
				peopleNumber:true,
				number:true,
				min:1,
				max:999999999
			},
			"newEconomicOrganizations.partyMemberNumber":{
				peopleNumber:true,
				partyMemberNumber:true,
				number:true,
				min:1,
				max:999999999
			},
			"newEconomicOrganizations.introduction":{
				maxlength:300
			},
			"newEconomicOrganizations.honor":{
				maxlength:300
			},
			"newEconomicOrganizations.remark":{
				maxlength:300
			}
		},
		messages:{
			"newEconomicOrganizations.name":{
				required:"名称不能为空",
				exculdeParticalChar:"名称不能存在非法字符",
				minlength:$.format("名称至少需要输入{0}个字符"),
				maxlength:$.format("名称最多需要输入{0}个字符"),
				exsistedName:"名称已存在",
				isLawful:"您输入了非法字符，请重新输入"
			},
			"newEconomicOrganizations.residence":{
				required:"住所不能为空"
			},
			"newEconomicOrganizations.licenseNumber":{
				required:"营业执照号码不能为空",
				exculdeParticalChar:"营业执照号码不能存在非法字符",
				exsistedLicenseNumber:"营业执照号码已存在",
				isLawful:"您输入了非法字符，请重新输入"
			},
			"newEconomicOrganizations.validityStartDate":{
				required:"有效期开始日期不能为空"
			},
			"newEconomicOrganizations.validityEndDate":{
				required:"有效期结束日期不能为空"
			},
			"newEconomicOrganizations.style.id":{
				required:"类别不能为空"
			},
			"newEconomicOrganizations.chief":{
				required:"负责人不能为空",
				exculdeParticalChar:"负责人不能存在非法字符",
				minlength:$.format("负责人至少需要输入{0}个字符"),
				maxlength:$.format("负责人最多需要输入{0}个字符")
			},
			"newEconomicOrganizations.telephone":{
				telephone:$.format("固定电话不合法，只能输数字和横杠(-)")
			},
			"newEconomicOrganizations.foxNumber":{
				telephone:$.format("传真输入不合法，只能输数字和横杠(-)")
			},
			"newEconomicOrganizations.mobileNumber":{
				mobile:"手机号码输入只能是以1开头的11位数字"
			},
			"newEconomicOrganizations.area":{
				number: '面积需要输入非负数',
				min: '面积需要输入非负数',
				max: '面积最大输入999999999'
			},
			"newEconomicOrganizations.employeeNumber":{
				peopleNumber:'从业人数需要输入正整数',
				number: '从业人数需要输入正数',
				min: '从业人数需要输入正数',
				max: '从业人数最大输入999999999'
			},
			"newEconomicOrganizations.partyMemberNumber":{
				peopleNumber:'党员人数需要输入正整数',
				partyMemberNumber:"党员人数不能大于从业人数",
				number: '党员人数需要输入正数',
				min: '党员人数需要输入正数',
				max: '党员人数输入999999999'
			},
			"newEconomicOrganizations.introduction":{
				maxlength:"简介最多需要输入300个字符"
			},
			"newEconomicOrganizations.honor":{
				maxlength:"荣誉最多需要输入300个字符"
			},
			"newEconomicOrganizations.remark":{
				maxlength:"备注最多需要输入300个字符"
			}
		}
	
	});
	function emptyObject(){
		$("#name").val("");
		$("#residence").val("");
		$("#licenseNumber").val("");
		$("#validityStartDate").val("");
		$("#validityEndDate").val("");
		$("#style").val("");
		$("#chief").val("");
		$("#foxNumber").val("");
		$("#area").val("");
		$("#employeeNumber").val("");
		$("#partyMemberNumber").val("");
		$("#telephone").val("");
		$("#mobileNumber").val("");
		$("#introduction").val("");
		$("#honor").val("");
		$("#remark").val("");
	}

});

</script>