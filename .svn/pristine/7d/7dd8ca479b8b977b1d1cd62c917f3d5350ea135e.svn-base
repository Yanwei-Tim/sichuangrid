<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="dialog-form"  class="container container_24">
	<form id="editOrganizationform" method="post" action="${path}/baseinfo/partyOrgInfoManage/addPartyOrgInfo.action">
		<input type="hidden" name="orgId" class="form-txt" id="orgIds" value="${orgId}" />
		<input type="hidden" class="form-txt" name="partyOrgInfo.id" id="id" value="${partyOrgInfo.id}" />

	    <div class="grid_4 lable-right">
	    	<em class="form-req">*</em>
			<label >所属网格：</label>
		</div>
		<div class="grid_20">
			<input type="text"  id="commonPopulationOrgName"  name="partyOrgInfo.organization.orgName"  readonly value="${partyOrgInfo.organization.orgName}" style="width: 99%"	class="form-txt" />
		</div>
		<div style="clear:both"></div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label>党支部名称：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="partyOrgInfo.partyBranchName" id="partyBranchName"  class="form-txt" value="${partyOrgInfo.partyBranchName}" maxlength="50" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label>党支部成立时间：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="partyOrgInfo.establishedDate" id="establishedDate"  class="form-txt" value="<s:date name="partyOrgInfo.establishedDate" format="yyyy-MM-dd"/>" maxlength="50" readonly/>
		</div>
		<div style="clear:both"></div>

		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label>党支部书记：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="partyOrgInfo.partyBranchSecretary" id="partyBranchSecretary"  class="form-txt" value="${partyOrgInfo.partyBranchSecretary}" maxlength="20"/>
		</div>
		<div class="grid_4 lable-right">
			<label>身份证号码：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="partyOrgInfo.idCardNo" id="idCardNo" maxlength="18" value="${partyOrgInfo.idCardNo}" title="请输入15或18位由数字或字母X组成的身份证号码"  style="width: 99%"  class="  form-txt" />
		</div>
		<div style="clear:both"></div>

		<div class="grid_4 lable-right">
			<label>联系手机：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="partyOrgInfo.mobileNumber" id="mobileNumber" maxlength="11" value="${partyOrgInfo.mobileNumber}" title="请输入11位以1开头的联系手机  例如：13988888888" class="form-txt" />
		</div>
		<div class="grid_4 lable-right">
			<label>联系电话：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="partyOrgInfo.telephone" id="telephone" maxlength="20"  value="${partyOrgInfo.telephone}" title="请输入由数字和-组成的联系电话,例如：0577-88888888" class=" form-txt" />
		</div>
		<div style="clear:both"></div>

		<div class="grid_4 lable-right">
			<label>党员人数：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="partyOrgInfo.partyNumbers" id="partyNumbers"  class="form-txt" value="${partyOrgInfo.partyNumbers}" maxlength="9" />
		</div>
		<div class="grid_4 lable-right">
			<label>党组织地址：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="partyOrgInfo.partyBranchAddr" id="partyBranchAddr"  class="form-txt" value="${partyOrgInfo.partyBranchAddr}" maxlength="50"  />
		</div>
		<div style="clear:both"></div>
		<div id="editPartyOrgDialog"></div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){

	$("#editOrganizationform").formValidate({
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
	                $.messageBox({message:"编辑党组织信息成功！"});
	                loadOrganization(data);
	                $("#editPartyOrgDialog").dialog("close");
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
  	      			alert("提交数据时发生错误");
	   		    }
			});
		},
		rules:{
			"partyOrgInfo.partyBranchName":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:50
			},
			"partyOrgInfo.partyBranchSecretary":{
				required:true,
				exculdeParticalChar:true,
				minlength:2,
				maxlength:20
			},
			"partyOrgInfo.establishedDate":{
				required:true
			},
			"partyOrgInfo.idCardNo":{
				idCard:true
			},
			"partyOrgInfo.mobileNumber":{
				mobile:true
			},
			"partyOrgInfo.telephone":{
				telephone:true
			},
			"partyOrgInfo.partyNumbers":{
				positiveInteger:true
			},
			"partyOrgInfo.partyBranchAddr":{
				minlength:2,
				maxlength:50
			}
		},
		messages:{
			"partyOrgInfo.partyBranchName":{
				required:"请先输入党支部名称!",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("党支部名称至少需要输入{0}个字符"),
				maxlength:$.format("党支部名称最多需要输入{0}个字符")
			},
			"partyOrgInfo.partyBranchSecretary":{
				required:"请先输入党支部书记!",
				exculdeParticalChar:"不能输入非法字符",
				minlength:$.format("党支部书记名称至少需要输入{0}个字符"),
				maxlength:$.format("党支部书记名称最多需要输入{0}个字符")
			},
			"partyOrgInfo.establishedDate":{
				required:"请先输入党支部成立时间!"
			},
			"partyOrgInfo.idCardNo":{
				idCard:$.format("请输入一个合法的身份证号码")
			},
			"partyOrgInfo.mobileNumber":{
				mobile:"手机号码输入只能是以1开头的11位数字"
			},
			"partyOrgInfo.telephone":{
				telephone:$.format("固定电话不合法，只能输数字和横杠(-)")
			},
			"partyOrgInfo.partyNumbers":{
				positiveInteger:"党员人数为正整数"
			},
			"partyOrgInfo.partyBranchAddr":{
				minlength:$.format("党组织地址至少需要输入{0}个字符"),
				maxlength:$.format("党组织地址最多需要输入{0}个字符")
			}
		}
	});

	$('#establishedDate').datePicker({
		yearRange:'1900:2030',
		dateFormat:'yy-mm-dd',
    	maxDate:'+0d',
       	onSelect:function(dateText, inst) {
			if(dateText!=null&&dateText!=''){
				var dateUnit=dateText.split('-');
				var date=new Date(dateUnit[0],dateUnit[1]-1,dateUnit[2]);
				//$("#establishedDate").datepicker("option", "minDate",date);
			}
		}
	});
});

function loadOrganization(data) {
	$("#branchName_label").text(data.partyBranchName);
	$("#establishedDate_label").text(data.establishedDate);
	$("#branchSecretary_label").text(data.partyBranchSecretary);
	$("#idCardNo_label").text(data.idCardNo==null ? "" :data.idCardNo);
	$("#mobileNumber_label").text(data.mobileNumber==null ? "" : data.mobileNumber);
	$("#telephone_label").text(data.telephone==null ? "" :data.telephone);
	$("#numbers_label").text(data.partyNumbers==null ? "" :data.partyNumbers);
	$("#branchAddr_label").text(data.partyBranchAddr==null ? "" : data.partyBranchAddr);
}
</script>