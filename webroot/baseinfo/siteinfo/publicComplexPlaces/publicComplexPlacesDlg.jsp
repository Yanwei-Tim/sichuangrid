<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24" >
	<div id="publicComplexPlaces" class="container container_24">
		<form id="maintainForm" method="post"action="${path}/baseinfo/publicComplexPlacesManage/maintainPublicComplexPlaces.action"  >
			<div id="perUuid"></div>
			<s:if test='"add".equals(mode)'>
				<pop:token/>
			</s:if>
			<input type="hidden" name="mode" id="mode" value="${mode}" />
			<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
			<input type="hidden" name="location.id" id="locationId" value="${location.id}" />
			<input type="hidden" name="location.gisInfo.buildingId" id="houseInfoBuildingId" value="${location.gisInfo.buildingId}">
			<input type="hidden" name="location.gisInfo.centerX" id="houseInfoCenterX" value="${location.gisInfo.centerX}">
			<input type="hidden" name="location.gisInfo.centerY" id="houseInfoCenterY" value="${location.gisInfo.centerY}">
			<input id="organizationId"	type="hidden" name="location.organization.id" value="${location.organization.id }" />
			<input id="ajaxUrl" type="hidden" name="ajaxUrl" value="<s:property value="@com.tianque.controller.BaseInfoActionDefine@getAction(ajaxUrl)"/>" />
			<input id="_imgUrl" name="location.imgUrl" type="hidden" value="${location.imgUrl}"/>
			<s:if test="#parameters.fromlocationType[0]">
				<span style="font-weight: bold; margin-left:20px; ">公共复杂场所</span>
				<div class='clearLine'>&nbsp;</div>
			</s:if>
			<div class="grid_4 lable-right">
			   	<em class="form-req">*</em>
	   			<label class="form-lb1">所属网格：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="orgName"  name="location.organization.orgName"  readonly value="${location.organization.orgName}" style="width: 99%"	class="form-txt" />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
			   	<em class="form-req">*</em>
	   			<label class="form-lb1">场所名称：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="placeName"  name="location.placeName"   value="${location.placeName}" style="width: 99%"  maxlength="30" title="请输入由字母、数字、和中文组成的场所名称"	class="form-txt" />
   			</div>
   			<div class="grid_4 lable-right">
   				<em class="form-req">*</em>
	   			<label class="form-lb1">场所地址：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="placeAddress"  name="location.placeAddress"   value="${location.placeAddress}" style="width: 99%"  maxlength="50" title="请输入由字母、数字、和中文组成的场所地址"	class="form-txt" />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">负责人：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.manager" id="manager" maxlength="20"  value="${location.manager }" title="请输入负责人名字、如:张三" class=" form-txt" />
	   		</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">负责人电话：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" name="location.managerTelephone" maxlength="15"  id="managerTelephone" maxlength="20"  value="${location.managerTelephone }" 
				class="form-txt  dialogtip" title="请输入由数字和-组成的联系电话!例如：0577-88888888"/>
	   		</div>
   			<div class='clearLine'>&nbsp;</div>

   			<div class="grid_4 lable-right">
   				<label class="form-lb1">负责人手机：</label>
	   		</div>
	   		<div class="grid_8">
			<input type="text" name="location.managerMobile"  maxlength="11" id="managerMobile"  value="${location.managerMobile}"
				class="form-txt  dialogtip" title="请输入11位以1开头的手机号码!例如：13988888888"/>
			</div>
			<div class="grid_4 lable-right">
				<label class="form-lbl">关注程度：</label>
			</div>
			<div class="grid_8">
				<select name="location.attentionExtent.id" id="location-attentionExtent" class="form-txt"
					<s:if test='"view".equals(mode)'>disabled='true'</s:if> >
					<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@ATTENTION_EXTENT" defaultValue="${location.attentionExtent.id}" />
				</select>
			</div>
   			<div class='clearLine'>&nbsp;</div>
	   		<div class="grid_4 lable-right">
   				<label class="form-lb1">场所类型：</label>
	   		</div>
	   		<div class="grid_8">
			   <select name="location.type.id" id="type" class="form-select form-txt" >
			   		<pop:PropertyDictReleationSelectTag name="@com.tianque.domain.property.PropertyTypes@PUBLICCOMPLEXPLACES_TYPE" defaultValue="${location.type.id}" reletionId="detailedType"
		   		reletionName="@com.tianque.domain.property.PropertyTypes@PUBLICCOMPLEXPLACES_DETAILEDTYPE" id="type" />
				</select>
			</div>
			<div class="grid_4 lable-right">
   				<label class="form-lb1">场所细类：</label>
	   		</div>
	   		<div class="grid_8">
			   <select name="location.detailedType.id" id="detailedType" class="form-txt" >
			   		<%-- <pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PUBLICPLACE_CLOAKROOM" defaultValue="${location.detailedType.id}" /> --%>
				</select>
			</div>
   			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
   				<label class="form-lb1">存在隐患：</label>
	   		</div>
	   		<div class="grid_20">
   				<textarea rows="2" name="location.hiddenCases" id="hiddenCases" class="form-txt" style="width: 99%">${location.hiddenCases }</textarea>
   			</div>
   			<div class="grid_24 lable-right"></div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">隐患整改情况：</label>
	   		</div>
	   		<div class="grid_20">
   				<textarea rows="2" name="location.hiddenRectification" id="hiddenRectification" class="form-txt" style="width: 99%">${location.hiddenRectification }</textarea>
   			</div>
   			<div class="grid_24 lable-right"></div>
   			<div class='clearLine'>&nbsp;</div>
			<div class="grid_4 lable-right">
   				<label class="form-lb1">备注：</label>
	   		</div>
	   		<div class="grid_20">
   				<textarea rows="3" name="location.remark" id="remark" class="form-txt" style="width: 99%">${location.remark }</textarea>
   			</div>
   		    <input name="isSubmit" id="isSubmit" type="hidden" value="" />
		</form>
  	</div>
</div>

<script type="text/javascript">

<s:if test='"add".equals(mode)'>
$("#organizationId").val($("#currentOrgId").val());
$.ajax({
	async: false,
	url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
	data:{
		"organization.id" : $("#currentOrgId").val()
	},
	success:function(responseData){
		$("#orgName").val(responseData);
	}
});
</s:if>
if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
	$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	$(".shadow").show();
}
if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
	$("#img").attr("src","${path }/"+$("#_imgUrl").val());
};
$(document).ready(function(){
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
		$("#_imgUrl").val($("#imgUrl").val());
			$(form).ajaxSubmit({
				async : false,
				success:function(data){
					if(!data.id){
           	 			$.messageBox({
							evel: "error"
			 			});
            			return;
					}
					 $("#<s:property value='#parameters.dailogName[0]'/>").proccessSuccess(data.id,data);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			"location.placeName":{
				required:true,
				exsistedPlaceName:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:30
			},
			"location.placeAddress":{
				required: true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:50
			},
			"location.manager":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:10
			},
			"location.hiddenCases":{
				maxlength:100
			},
			"location.hiddenRectification":{
				maxlength:100
			},
			"location.managerTelephone":{
				telephone : true
			},
			"location.managerMobile":{
				digits:true,
				isMobileNumber:true,
				minlength:11
			},
			"location.remark":{
				maxlength:300
				}
		},
		messages:{
			"location.placeName":{
				required:"请输入公共复杂场所名称",
				exsistedPlaceName:"公共复杂场所名称已存在，请重新输入",
				exculdeParticalChar:"公共复杂场所名称只能输入字母，数字和中文字符",
				minlength:$.format("公共复杂场所名称至少需要输入{0}个字符"),
				maxlength:$.format("公共复杂场所名称最多只能输入{0}个字符")
				},
			"location.placeAddress":{
					required: "请输入公共复杂场所地址!",
					exculdeParticalChar:"公共复杂场所地址只能输入字母，数字和中文字符",
					minlength:$.format("公共复杂场所地址至少需要输入{0}个字符"),
					maxlength:$.format("公共复杂场所地址最多只能输入{0}个字符")
			},
			"location.manager":{
				exculdeParticalChar:"负责人只能输入字母，数字和中文字符",
				minlength:$.format("负责人至少需要输入{0}个字符"),
				maxlength:$.format("负责人最多只能输入{0}个字符")
			},
			"location.hiddenCases":{
				maxlength:$.format("存在隐患最多只能输入{0}个字符")
			},
			"location.hiddenRectification":{
				maxlength:$.format("隐患整改情况最多只能输入{0}个字符")
			},
			"location.managerTelephone":{
				telephone:"联系电话不合法，只能输数字和横杠(-)"
			},
			"location.managerMobile":{
			    digits:"手机只能输入数字字符",
			    isMobileNumber :"手机号码只能输入以1开头的11为数字!",
				minlength:$.format("手机只能输入11位数字字符")
			},
			"location.remark":{
				maxlength:$.format("备注最多只能输入{0}个字符")
			}
		}
	});
	$("#detailedType").val('<s:property value="location.detailedType.id"/>');
});
//手机11位
jQuery.validator.addMethod("isMobileNumber", function(value, element){
	if(value==null||value==undefined||value=="" ){return true};
	var patrn=/^1[0-9]\d{9}$/;
	if (!patrn.exec(value.replace(/[ ]/g,""))) return false
	return true
});
jQuery.validator.addMethod("exsistedPlaceName", function(value, element){
	var flag =true;
	if(value==null||value==undefined||value==""){return true}
	$.ajax({
		async: false ,
		url:$('#ajaxUrl').val(),
	   	data:{
			"location.placeName":$('#placeName').val(),
			"organizationId":$('#organizationId').val(),
			"mode":$('#mode').val(),
			"location.id":$('#locationId').val()
        },
		success:function(responseData){
			flag = !eval(responseData);
		}
	});
	return flag;
});


</script>