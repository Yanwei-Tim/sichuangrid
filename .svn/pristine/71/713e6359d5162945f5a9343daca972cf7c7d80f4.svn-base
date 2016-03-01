<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div class="container container_24" >
	<div id="scenicSpot" class="container container_24">
		<form id="maintainForm" method="post" action="${path}/baseinfo/scenicSpotManage/addScenicSpot.action">
			<div id="perUuid"></div>
			<s:if test='"add".equals(mode)'>
				<pop:token/>
			</s:if>
			<input type="hidden" name="mode" id="mode" value="${mode}" />
			<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
			<input type="hidden" name="location.id" id="locationId" value="${location.id}" />
			<input id="organizationId"	type="hidden" name="location.organization.id" value="${location.organization.id }" />
			<input id="ajaxUrl" type="hidden" name="ajaxUrl" value="<s:property value="@com.tianque.controller.BaseInfoActionDefine@getAction(ajaxUrl)"/>" />
			<input id="_imgUrl" name="location.imgUrl" type="hidden" value="${location.imgUrl}"/>
			<s:if test="#parameters.fromlocationType[0]">
				<span style="font-weight: bold; margin-left:20px; ">旅游景点</span>
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
	   			<label class="form-lb1">景点名称：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="name"  name="location.name" value="${location.name}" style="width: 99%"  maxlength="30" title="景点名称可以输入字母、数字、和中文"
   				class="form-txt " />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
	   			<label class="form-lb1">景点地址：</label>
   			</div>
   			<div class="grid_20">
   				<input type="text"  id="address"  name="location.address" value="${location.address}" style="width: 99%"  maxlength="50" class="form-txt " />
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">景点等级：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" id="grade" name="location.grade" maxlength="20"  value="${location.grade }" class="form-txt"/>
	   		</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">景点电话：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" id="telephone" name="location.telephone" maxlength="15"  value="${location.telephone }" class="form-txt"/>
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">景点负责人：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" id="personLiable" name="location.personLiable" maxlength="15"  value="${location.personLiable }" class="form-txt"/>
	   		</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">景点负责人电话：</label>
	   		</div>
	   		<div class="grid_8">
	   			<input type="text" id="personLiableTelephone" name="location.personLiableTelephone" maxlength="15"  value="${location.personLiableTelephone }" class="form-txt"/>
	   		</div>
	   		<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">景点介绍：</label>
	   		</div>
	   		<div class="grid_20" style="height:145px">
   				<textarea rows="6" id="introduction" name="location.introduction" maxlength="500" class="form-txt" style="width: 99%">${location.introduction }</textarea>
   			</div>
   			<div class='clearLine'>&nbsp;</div>
   			<div class="grid_4 lable-right">
   				<label class="form-lb1">备注：</label>
	   		</div>
	   		<div class="grid_20">
   				<textarea rows="4" id="remark" maxlength="300"  name="location.remark" class="form-txt" style="width: 99%">${location.remark }</textarea>
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

<s:if test='"edit".equals(mode)'>
	$("#maintainForm").attr("action","${path}/baseinfo/scenicSpotManage/updateScenicSpot.action");
</s:if>

if(null!=$("#_imgUrl").val() && $("#_imgUrl").val()!=""){
	$("#headerImg").attr("src",$("#_imgUrl").val()+"?r="+Math.random());
	$(".shadow").show();
}

if(""!=$("#_imgUrl").val() && typeof($("#_imgUrl").val())!="undefined"){
	$("#img").attr("src","${path }/"+$("#_imgUrl").val());
};

jQuery.validator.addMethod("exsistedScenicSpotName", function(value, element){
	var flag =true;
	if(value==null||value==undefined||value==""){return true}
	$.ajax({
		async: false ,
		url:$('#ajaxUrl').val(),
	   	data:{
			"location.name":$('#name').val(),
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

jQuery.validator.addMethod("decimal", function(value, element) {
    var decimal = /^-?\d+(\.\d{1,2})?$/;
    if (value==""){
	     return true;
	 }
    if(value>=0){
    	return this.optional(element) || (decimal.test(value));
    }
    return false;
});

$(document).ready(function(){
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
			$("#_imgUrl").val($("#imgUrl").val());
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
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					alert("提交错误");
				}
			});
		},
		rules:{
			"location.name":{
				required:true,
				exsistedScenicSpotName:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:30
			},
			"location.address":{
				exculdeParticalChar:true,
				maxlength:50
			},
			"location.telephone":{
				telephone:true
			},
			"location.personLiable":{
				exculdeParticalChar:true,
				maxlength:20
			},
			"location.personLiableTelephone":{
				telephone:true
			},
			"location.introduction":{
				maxlength:900
			},
			"location.remark":{
				maxlength:300
			}

		},
		messages:{
			"location.name":{
				required:"请输入景点名称",
				exsistedScenicSpotName:"景点名称已存在，请重新输入",
				exculdeParticalChar:"景点名称只能输入字母，数字和中文字符",
				minlength:$.format("景点名称至少需要输入{0}个字符"),
				maxlength:$.format("景点名称最多只能输入{0}个字符")
			},
			"location.address":{
				exculdeParticalChar:"景点地址只能输入字母，数字和中文字符",
				maxlength:$.format("景点地址最多只能输入{0}个字符")
			},
			"location.telephone":{
				telephone:$.format("景点固定电话只能输数字和横杠(-)")
			},
			"location.personLiable":{
				exculdeParticalChar:"景点负责人只能输入字母，数字和中文字符",
				maxlength:$.format("景点负责人最多只能输入{0}个字符")
			},
			"location.personLiableTelephone":{
				telephone:$.format("负责人固定电话只能输数字和横杠(-)")
			},
			"location.introduction":{
				maxlength:$.format("景点介绍最多只能输入{0}个字符")
			},
			"location.remark":{
				maxlength:$.format("备注最多只能输入{0}个字符")
			}
		}
	});
});

</script>