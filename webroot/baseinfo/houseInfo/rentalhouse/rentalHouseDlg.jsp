<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<div id="dialog-form" title="出租房信息维护" class="container container_24">
	<div id="houseInfo" class="container container_24">
		<form id="maintainForm" method="post" action="" >
		<pop:token />
			<input type="hidden" id="organizationId" name="houseInfo.organization.id" value="${houseInfo.organization.id}"/>
			<input type="hidden" name="houseInfo.houseCode" value="${houseInfo.houseCode}" />
			<input type="hidden" name="houseInfo.houseId" value="${houseInfo.houseId}" />
			<input id="_imgUrl" name="houseInfo.imgUrl" type="hidden" value="${houseInfo.imgUrl}"/>
			<input type="hidden" name="operateSource" value="${operateSource}" />
			<input id="orgInternalCode"	type="hidden" name="houseInfo.orgInternalCode" value="${houseInfo.orgInternalCode}" />
			<input type="hidden" name="dailogName" value='<s:property value="#parameters.dailogName[0]"/>'/>
			<jsp:include page="/baseinfo/houseInfo/rentalhouse/commonRentalHouseDlg.jsp"></jsp:include>
		</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	<%--添加表单验证的方法，判断是否为正整数 --%>
	jQuery.validator.addMethod("isInteger", function(value, element) {
		   var aint=parseInt(value);
		   if(value.indexOf(".")>0){
		   		var decimal = value.split(".")[1];
		   		if(value==""||(aint>=0 && decimal.length<3)){
			    	return true;
			    }
			    else{
			    	return false;
			    }
			}
		   else{
			   if(value==""||aint>=0){
			    	return true;
			    }
			    else{
			    	return false;
			    }
		   }
		  });
	
	<%--添加表单验证的方法，判断输入数字长度是否不大于6位数 --%>
	jQuery.validator.addMethod("isTooLongAndInteger", function(value, element) {
		   var aint=parseInt(value);
		    if(value==""||(aint>=0 && (aint+"")==value&&value.length<7)){
		    	return true;
		    }
		    else{
		    	return false;
		    }
		  });

	$("#maintainForm").formValidate({
		submitHandler:function(form) {
		$("#_imgUrl").val($("#_imgUrl").val());
		businessHouseToCurrentAddress();
        $(form).ajaxSubmit({
            async : false,
			success: function(data){
	        	if(!data.id){
					$.messageBox({
						message:data,
						level: "error"
					});
					return;
				}
	        	 $("#<s:property value='#parameters.dailogName[0]'/>").proccessSuccess(data.encryptId,data);
	        	 $("#<s:property value='#parameters.dailogName[0]'/>").proccessTypeSuccess(data.houseType,data);
      	   },
      	   error:function(XMLHttpRequest, textStatus, errorThrown){
      	      alert("提交错误");
      	   }
      	});
	},
	rules:{
		"houseInfo.rentalPerson":{
			required:true
		},
		"houseInfo.rentalType.id":{
			required:true
		},
		"houseInfo.limitPersons":{
			isTooLongAndInteger:true
		}
	},
	messages:{
		"houseInfo.rentalPerson":{
			required:"房主姓名为必填"
		},
		"houseInfo.rentalType.id":{
			required:"请选择出租房类别"
		},
		"houseInfo.limitPersons":{
			isTooLongAndInteger:"限住人数必须为小于6位数的正整数"
		}
	}
});

$("#maintainForm").attr("action","${path}/baseinfo/rentalHouseManage/maintainRentalHouse.action");
<c:if test='${mode=="add"}'>
	$.ajax({
		async: false,
		url: "${path }/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#organizationId").val()
		},
		success:function(responseData){
			$("#houseInfoOrgName").val(responseData);
		}
	});
</c:if>
});

</script>