<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>

<div id="dialog-form" title="出租房信息维护" class="container container_24">
	<div id="houseInfo" class="container container_24">
		<form id="maintainForm" method="post" action="${path}/plugin/dataManage/${tempClassName}Manage/updateTempBusiness.action" >
			<@pop.token />
			<input id="previousOrNextOrSave" type="hidden" name="previousOrNextOrSave" value="" />
			<input type="hidden" id="organizationId" name="houseInfo.organization.id" value="${(houseInfo.organization.id)!}"/>
			<input type="hidden" name="houseInfo.houseCode" value="${(houseInfo.houseCode)!}" />
			<input type="hidden" name="houseInfo.houseId" value="${(houseInfo.houseId)!}" />
			<input id="_imgUrl" name="houseInfo.imgUrl" type="hidden" value="${(houseInfo.imgUrl)!}"/>
			<input id="orgInternalCode"	type="hidden" name="houseInfo.orgInternalCode" value="${(houseInfo.orgInternalCode)!}" />
			<#include "commonRentalHouseDlg.ftl"/>
		</form>
	</div>
</div>

<script type="text/javascript">



$(document).ready(function(){
	
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
	        	 $("#<@s.property value='#parameters.dailogName[0]'/>").proccessSuccess(data.id,data);
	        	 $("#<@s.property value='#parameters.dailogName[0]'/>").proccessTypeSuccess(data.houseType,data);
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
		},
		"houseInfo.rentMonth":{
			isInteger:true
		}
	},
	messages:{
		"houseInfo.rentalPerson":{
			required:"出租人信息为必填"
		},
		"houseInfo.rentalType.id":{
			required:"请选择出租房类别"
		},
		"houseInfo.limitPersons":{
			isTooLongAndInteger:"限住人数必须为小于6位数的正整数"
		},
		"houseInfo.rentMonth":{
			isInteger:"月租金必须为非负数,且小数位数不超过两位"
		}
	}
});

});

</script>