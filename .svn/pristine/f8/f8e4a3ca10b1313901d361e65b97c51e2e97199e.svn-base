<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
	#maintainForm .itemBoxData{border: 1px solid rgb(211, 207, 207);margin-top: 5px;}
	#maintainForm .addItem{color:blue;padding:0 0 0 5px;}
	#maintainForm .delItem{padding:0 0px;color:#f60;}
</style>
<div id="dialog-form" title="街道社区党组织表" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="partyOrgId" name="townPartyOrg.id" value="${townPartyOrg.id}"/>
		<input type="hidden" id="organizationId" name="townPartyOrg.organization.id" value="${townPartyOrg.organization.id}"/>
		<input type="hidden" name="townPartyOrg.orgInternalCode" value="${townPartyOrg.organization.orgInternalCode}"/>
	 	
		<div class="grid_4 lable-right">
 			<label class="form-lb1">所属网格：</label>
 		</div>
 		<div class="grid_18">
 			<input type="text"  id="townPartyOrgOrgName"  name="townPartyOrg.organization.orgName"  readonly  value="${townPartyOrg.organization.orgName}" style="width: 99%" class="form-txt" />
 		</div>
 		<div class='clearLine'>&nbsp;</div>
 		
	 	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">党组织名称：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="townPartyOrg.name" id="name"  maxlength="30" class="form-txt {required:true,exsistedTownPartyOrg:true,messages:{required:'请输入党组织名称',exsistedTownPartyOrg:'此名称在系统中已经存在'}}" value="${townPartyOrg.name}"/>
			<input type="hidden" name="oldPartyOrg" id="oldPartyOrg"  maxlength="30" class="form-txt" value="${townPartyOrg.name}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">党组织类型：</label>
	 	</div>
		<div class="grid_7">
			<select name="townPartyOrg.type.id" id="partyOrgType" class='form-txt {required:true,messages:{required:"请选择党组织类型"}}'>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE"  defaultValue="${townPartyOrg.type.id}" />
			</select>
		</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">成立时间：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="townPartyOrg.foundDate" id="foundDate" readonly maxlength="" class="form-txt" value="<fmt:formatDate value="${townPartyOrg.foundDate}" type="date" pattern="yyyy-MM-dd" />"/>
		</div>
	 	<div class='clearLine'>&nbsp;</div>
	 	
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">党支部书记：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="townPartyOrg.secretary" id="secretary"  maxlength="30" class="form-txt" value="${townPartyOrg.secretary}"/>
		</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">身份证号码：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="townPartyOrg.idCardNo" id="idCardNo"  maxlength="18" value="${townPartyOrg.idCardNo}"
				class="form-txt {idCard:true,messages:{idCard:'请输入一个合法的身份证号码'}}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">联系手机：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="townPartyOrg.mobileNumber" id="mobileNumber"  maxlength="11" value="${townPartyOrg.mobileNumber}"
				class="form-txt {mobile:true,messages:{mobile:'联系手机输入只能是以1开头的11位数字'}}"/>
		</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">联系电话：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="townPartyOrg.telephone" id="telephone"  maxlength="15" value="${townPartyOrg.telephone}"
				class="form-txt {telephone:true,messages:{telephone:'固话输入只能包含数字和-'}}" />
		</div>
		<div class='clearLine'>&nbsp;</div>
	 	
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">党组织地址：</label>
	 	</div>
		<div class="grid_18">
			<input type="text" name="townPartyOrg.address" id="address"  maxlength="30" class="form-txt" value="${townPartyOrg.address}"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">班子成员：</label>
	 	</div>
	 	<div class="grid_18 lable-right"><a href="javascript:;" class="addItem">+班子成员</a></div>
	 	<div class='clearLine'>&nbsp;</div>
	 	
	 	<ul class="hidden"  id="itemMould" title="支部成员模板">
  			<li style="clear:both;">
  				<input type="hidden" name="townPartyOrg.members[{num}].orgId" value="${townPartyOrg.organization.id}"/>
				<input type="hidden" name="townPartyOrg.members[{num}].orgInternalCode" value="${townPartyOrg.organization.orgInternalCode}"/>
   				<div class="grid_3 lable-right">
   					<em class="form-req">*</em>
    				<label class="form-lbl">姓名：</label>
				</div>
   				<div class="grid_4">
    				<input type="text" maxlength='30' name="townPartyOrg.members[{num}].name" 
    					class="form-txt {required:true,messages:{required:'请输入姓名'}}" />
   				</div>
   				<div class="grid_3 lable-right">联系手机：</div>
   				<div class="grid_4">
    				<input type="text" maxlength='11' name="townPartyOrg.members[{num}].mobileNumber" 
    					class="form-txt {mobile:true,messages:{mobile:'联系手机输入只能是以1开头的11位数字'}}" />
   				</div>
   				<div class="grid_3 lable-right">固话：</div>
   				<div class="grid_6">
    				<input type="text" maxlength='15'  name="townPartyOrg.members[{num}].telephone" 
    					class="form-txt {telephone:true,messages:{telephone:'固话输入只能包含数字和-'}}" />
   				</div>
   				<div class="grid_1 lable-right"><a href="javascript:;" class="delItem">删除</a></div>
   			</li>
   		</ul>
   		<div class="grid_4 lable-right">
	 	</div>
		<div class="grid_18 form-left heightAuto itemBox itemBoxData" >
   			<ul>
	   			<c:if test='${townPartyOrg.members!=null}'>
		   			<s:iterator value="townPartyOrg.members" status="sta" var="member">
		    			<li style="clear:both">
		    				<input type="hidden" name="townPartyOrg.members[${sta.index }].orgId" value="${member.orgId}"/>
							<input type="hidden" name="townPartyOrg.members[${sta.index }].orgInternalCode" value="${member.orgInternalCode}"/>
		    				<div class="grid_3 lable-right">
		    					<em class="form-req">*</em>
		    					<label class="form-lbl">姓名：</label>
		    				</div>
		    				<div class="grid_4">
			    				<input type="text" name="townPartyOrg.members[${sta.index }].name" value="${member.name }" maxlength='30' 
			    				class="form-txt {required:true,messages:{required:'请输入姓名'}}" />
		    				</div>
		    				<div class="grid_3 lable-right">联系手机：</div>
		    				<div class="grid_4">
			    				<input type="text" name="townPartyOrg.members[${sta.index }].mobileNumber" value="${member.mobileNumber }" maxlength='11'
			    				class="form-txt {mobile:true,messages:{mobile:'联系手机输入只能是以1开头的11位数字'}}" />
		    				</div>
		    				<div class="grid_3 lable-right">固话：</div>
		    				<div class="grid_6">
			    				<input type="text" name="townPartyOrg.members[${sta.index }].telephone" value="${member.telephone }" maxlength='15'
			    				class="form-txt {telephone:true,messages:{telephone:'固话输入只能包含数字和-'}}" />
		    				</div>
		    				<div class="grid_1 lable-right"><a href="javascript:;" class="delItem">删除</a></div>
		    			</li>
		    		</s:iterator>
	    		</c:if>
   			</ul>
   		</div>
	</form>
	
</div>
<script type="text/javascript">

jQuery.validator.addMethod("exsistedTownPartyOrg", function(value, element){
	var result = true;
	if(value==null||value==undefined||value==""){return true}
	$.ajax({
		async: false ,
		url:"${path}/townPartyOrgManage/hasDuplicateTownPartyOrg.action",
	   	data:{
			"searchVo.orgId":$("#organizationId").val(),
			"searchVo.name":value,
			"searchVo.id":$("#partyOrgId").val()
        },
		success:function(data){
        	if(data!=null && data==true){
        		result = false;
        	}
		}
	});
	return result;
});
$(document).ready(function(){
	$('#foundDate').datePicker({
		yearRange: '1900:2030',
		dateFormat: 'yy-mm-dd',
        maxDate:'+0d'});
	$("#maintainForm").formValidate({
		submitHandler: function(form) {
	        $(form).ajaxSubmit({
				success: function(data){
		        	if(!data.id){
						$.messageBox({level: "error",message:data});
						return;
					}
					if("edit"==$("#mode").val()){
						//$("#townPartyOrgList").setRowData(data.id,data);
				    	$.messageBox({message:"街道社区党组织表修改成功!"});
					}else if("add"==$("#mode").val()){
						//$("#townPartyOrgList").addRowData(data.id,data,"first");
						$.messageBox({message:"街道社区党组织表新增成功!"});
					}
					$("#townPartyOrgList").trigger("reloadGrid");
					$("#townPartyOrgDialog").dialog("close");
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
		},
		messages:{
		},
		ignore:':hidden'
	});

	$("#maintainForm").delegate(".delItem","click",function(){
		$(this).parents("li").remove();
		var size = $(".itemBox li").size();
		if(size<1){
			$(".itemBox").removeClass("itemBoxData");
		}
	})
	$("#maintainForm").delegate(".addItem","click",appendPartyOrgMember);
	function appendPartyOrgMember(){
		var size = $(".itemBox li").size();
		var liMould = $("ul#itemMould").html().replace(/{num}/g,size);
		$(".itemBox ul").append(liMould);
		$(".itemBox").addClass("itemBoxData");
	}
	
	$.ajax({
		async: false,
		url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
		data:{
			"organization.id":$("#organizationId").val()
		},
		success:function(responseData){
			$("#townPartyOrgOrgName").val(responseData);
		}
	});
<c:if test='${mode=="add"}'>
    $("#maintainForm").attr("action","${path}/townPartyOrgManage/addTownPartyOrg.action");
</c:if>
<c:if test='${mode=="edit"}'>
    $("#maintainForm").attr("action","${path}/townPartyOrgManage/updateTownPartyOrg.action");
</c:if>
<c:if test='${mode=="view"}'>
	$("#maintainForm").find("input,select,textarea").attr("disabled","disabled");
	$("#maintainForm").find(".addItem,.delItem").hide()
</c:if>
<c:if test='${townPartyOrg==null || townPartyOrg.members==null}'>
	appendPartyOrgMember();
</c:if>

});

</script>


