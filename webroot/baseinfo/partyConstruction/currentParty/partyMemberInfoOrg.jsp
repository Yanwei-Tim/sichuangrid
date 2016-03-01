<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

<div id="partyMemberInfo" class="container container_24">
	<form id="maintainForm" method="post" action="${path}/baseinfo/partyMemberInfoManage/addPartyMemberInfo.action" >
		<div id="perUuid"></div>
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" name="modeType" id="modeType" value="${modeType}" />
		<input type="hidden" name="cacheId.id"  value="${cacheId.id}" />
		<input type="hidden" name="population.id" value="${population.id}" />
		<input id="_imgUrl" name="population.imgUrl" type="hidden" value="${population.imgUrl}"/>
		<jsp:include page="/baseinfo/partyConstruction/currentParty/partyMemberInfo.jsp"></jsp:include>
		<input name="isSubmit" id="isSubmit" type="hidden" value="">
	</form>
</div>

<script type="text/javascript">
$(document).ready(function(){
	$("#maintainForm").formValidate({
		promptPosition: "bottomLeft",
		submitHandler: function(form){
			$(form).ajaxSubmit({
				success:function(data){
					if(!data.id){
           	 			$.messageBox({
							evel: "error",
							message:data
			 			});
            			return;
					}
	                if("add"==$("#mode").val()){
	                	 $.messageBox({message:"党员信息新增成功！"});
	    				 $("#partyMemberInfoList").addRowData(data.id,data,"first");
	                	 $("#partyMemberInfoList").trigger("reloadGrid");
	    		         doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
	                }
	                if("edit"==$("#mode").val()){
	                	 $("#partyMemberInfoList").setRowData(data.id,data);
	                	 if(data.death){
	                			if($("#isLock").val()=='0') {
	                				$("#partyMemberInfoList").delRowData(data.id);
								}else {
									$("td[aria-describedby='partyMemberInfoList_name']").css('color','red');
									$("td[aria-describedby='partyMemberInfoList_idCardNo']").css('color','#778899');
									$("#"+data.id+"").css('color','#778899');
								}
							}
	                	 $.messageBox({message:"党员信息修改成功！"});
	    				 doAction("<s:property value='#parameters.dailogName[0]'/>",data.id);
	                }
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			"population.partyOrgInfo.id":{
				required:true
			},
			"population.joinPartyBranch":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:140
			},
			"population.partyPosition":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:48
			},
			"population.rewardsPunishment":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:350
			},
			"population.expertise":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:380
			},
			"population.flowPartyBranch":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:140
			},
			"population.partyBranchContacts":{
				exculdeParticalChar:true,
				minlength:2,
				maxlength:8
			},
			"population.branchMobileNumber":{
				mobile:true
			},
			"population.branchTelephone":{
				telephone:true
			},
			"population.becomesDate":{
				isBecomesDate:true
			},
			"population.joinPartyBranchDate":{
				isJoinPartyDate:true
			},
			"population.flowPartyBranchDate":{
				isFlowPartyBranchDate:true
			}
		},
		messages:{
			"population.partyOrgInfo.id":{
				required:"所属党支部不能为空"
			},
			"population.joinPartyBranch":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("入党时所在党支部至少需要输入{0}个字符"),
				maxlength:$.format("入党时所在党支部最多需要输入{0}个字符")
			},
			"population.partyPosition":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("党内主要职务至少需要输入{0}个字符"),
				maxlength:$.format("党内主要职务最多需要输入{0}个字符")
			},
			"population.rewardsPunishment":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("奖惩情况至少需要输入{0}个字符"),
				maxlength:$.format("奖惩情况最多需要输入{0}个字符")
			},
			"population.expertise":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("专长至少需要输入{0}个字符"),
				maxlength:$.format("专长最多需要输入{0}个字符")
			},
			"population.flowPartyBranch":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("流入地党支部至少需要输入{0}个字符"),
				maxlength:$.format("流入地党支部最多需要输入{0}个字符")
			},
			"population.partyBranchContacts":{
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("党支部联系人至少需要输入{0}个字符"),
				maxlength:$.format("党支部联系人最多需要输入{0}个字符")
			},
			"population.branchMobileNumber":{
				mobile:"手机号码输入只能是以1开头的11位数字"
			},
			"population.branchTelephone":{
				telephone:$.format("固定电话不合法，只能输数字和横杠(-)")
			},
			"population.becomesDate":{
				isBecomesDate:"入党时间不能大于转正时间"
			},
			"population.joinPartyBranchDate":{
				isJoinPartyDate:"进入党支部时间不能小于党支部成立时间或小于入党时间"
			},
			"population.flowPartyBranchDate":{
				isFlowPartyBranchDate:"入党时间不能大于流入时间"
			}
		}
	});
});

jQuery.validator.addMethod("isBecomesDate", function(value, element){
	if($('#joinPartyDate').val()!=null&&$('#joinPartyDate').val()!=""
			&& $('#becomesDate').val()!=null&&$('#becomesDate').val()!=""){
		if($('#joinPartyDate').val() > $('#becomesDate').val()){
	           return false;
			}else {
				return true;
		}
	}
	return true;
});

jQuery.validator.addMethod("isJoinPartyDate", function(value, element){
	if(!value){
		return true;
	}
	if(value < $('#establishedDate').val()){
        return false;
	}
	if($('#joinPartyDate').val() > value){
	           return false;
		}
	
	return true;
});

jQuery.validator.addMethod("isFlowPartyBranchDate", function(value, element){
	if($('#flowPartyBranchDate').val()!=null&&$('#flowPartyBranchDate').val()!=""
			&& $('#joinPartyDate').val()!=null&&$('#joinPartyDate').val()!=""){
		if($('#joinPartyDate').val() > $('#flowPartyBranchDate').val()){
	           return false;
			}else {
				return true;
		}
	}
	return true;
});
</script>