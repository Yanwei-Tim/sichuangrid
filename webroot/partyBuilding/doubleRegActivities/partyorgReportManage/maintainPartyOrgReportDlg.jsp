<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="dialog-form" title="党组织报到" class="container container_24">
	
	<form id="maintainForm" method="post" action="">
		<input type="hidden" name="mode" id="mode" value="${mode}" />
		<input type="hidden" id="id" name="partyOrgReport.id" value="${partyOrgReport.id}"/>
		<input type="hidden" id="organizationId" name="partyOrgReport.organization.id" value="${partyOrgReport.organization.id}"/>
		<input type="hidden" id="orgInternalCode" name="partyOrgReport.orgInternalCode" value="${partyOrgReport.orgInternalCode}"/>
		<input type="hidden" name="partyOrgReport.type" id="type" <c:choose><c:when test='${mode=="add"}'>value="${type}"</c:when><c:otherwise>value="${partyOrgReport.type}"</c:otherwise></c:choose>/>
		<input type="hidden"  id="claimServiceProjectName" value="${partyOrgReport.claimServiceProjectName}" />
	 	
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
 			<label class="form-lb1">所属网格：</label>
 		</div>
 		<div class="grid_18">
 			<input type="text"  id="partyOrgReportOrgName"  name="partyOrgReport.organization.orgName"  readonly  value="${partyOrgReport.organization.orgName}" style="width: 99%"	class="form-txt" />
 		</div>
 		<div class='clearLine'>&nbsp;</div>
	 	
	 	<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">党组织类别：</label>
	 	</div>
		<div class="grid_7">
			<select name="partyOrgReport.partyOrgType.id" id="partyOrgType" class='form-txt'>
		   		<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@PARTYORGTYPE"  defaultValue="${partyOrgReport.partyOrgType.id}" />
			</select>
		</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">单位名称：</label>
	 	</div>
		<div class="grid_7" id="nameDiv">
			<input type="text"  name="partyOrgReport.name"  id="name"  maxlength="20" value="${partyOrgReport.name}"
				class='form-txt {required:true,maxlength:true,exsistedName:true,messages:{required:"请输入单位名称",maxlength:$.format("单位名称最多需要输入{0}个字符"),exsistedName:function(){return nameData;}}}' />
		</div>
		
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
 			<label class="form-lb1">地址：</label>
 		</div>
 		<div class="grid_18">
 			<input type="text"  name="partyOrgReport.address" id="address"  maxlength="100" class="form-txt" value="${partyOrgReport.address}" style="width: 99%"/>
 		</div>
 		<div class='clearLine'>&nbsp;</div>
		
		<div class="grid_4 lable-right">
			<label class="form-lbl">负责人：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="partyOrgReport.contractor" id="contractor"  maxlength="20" class="form-txt" value="${partyOrgReport.contractor}"/>
		</div>
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">负责人联系电话：</label>
	 	</div>
		<div class="grid_7">
			<input type="text" name="partyOrgReport.telephone" id="telephone"  maxlength="13" class="form-txt" value="${partyOrgReport.telephone}" title="请输入由数字和-组成的联系电话 例如：0577-88888888"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		
		
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">认领服务项目：</label>
	 	</div>
		<div class="grid_7" style="width: 480px;height: 100px;">
			
			<input type="hidden" name="partyOrgReport.claimServiceProjectIds" id="claimServiceProjectIds"  maxlength="" class="form-txt" value="${partyOrgReport.claimServiceProjectIds}"/>
			<textarea readonly rows="5" cols="30" id="claimProjectName" class="form-txt" style="width: 474px;"  ></textarea>
			
		</div>
			<input type="button" id="change" value="选择" style="margin-left: 10px;margin-top: 13px;width: 50px;height: 25px;text-align: center;"/>
			<div style="margin-top: 10px;" class='clearLine'>&nbsp;</div>
			<div class="content" style="width: 100%;height: 10px;padding-top: 10px;">
			</div>
	 	<div class="grid_4 lable-right">
			<label class="form-lbl">备注：</label>
	 	</div>
		<div class="grid_7" style="width: 480px;height: 100px;">
			<textarea rows="5" cols="50" name="partyOrgReport.remark" id="remark"  maxlength="200" class="form-txt" >${partyOrgReport.remark}</textarea>
		</div>
	</form>
</div>
<script type="text/javascript">
var nameData;
$(document).ready(function(){
	
	$("#claimProjectName").val($("#claimServiceProjectName").val());
	
	//添加认领服务项目
	$("#change").click(function(){
		var orgid = $("#organizationId").val();
		$("#claimProjectNameDialog").createDialog({
			title:"添加认领服务项目",
			width: 600,
			height: 300,
			url:'${path}/partyBuilding/doubleRegActivities/partyorgReportManage/addClaimProjectDlg.jsp?mode=addClaimProject&organization.id='+orgid+'&dailogName=claimProjectNameDialog',
			buttons: {
				"确定" : function(){
					  $(this).dialog("close");
				}
			}
		});
	});
	
	
	jQuery.validator.addMethod("exsistedName", function(value, element){
		var value=$('#name').val();
		if(value==null||value==undefined||value==""){return true}
		$.ajax({
			async: false ,
			url:"${path}/partyorgReportManage/hasDuplicateName.action",
		   	data:{
				"partyOrgReport.name":$('#name').val(),
				"partyOrgReport.organization.id":$('#organizationId').val(),
				"partyOrgReport.id":$('#id').val()
	        },
			success:function(responseData){
				nameData = responseData;
			}
		});
		if(!(nameData==null||nameData=="")){
			return false;
		}
		return true;
	});
	
	$("#maintainForm").formValidate({
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
					if(data.organization){
						data["organization.orgName"]=data.organization.orgName;
					}
					<c:if test='${mode=="edit"}'>
						onOrgChanged(getCurrentOrgId(),isGrid());
				    	$.messageBox({message:"党组织报到修改成功!"});
				    	$("#partyorgReportDialog").dialog("close");
					</c:if>
					<c:if test='${mode=="add"}'>
						onOrgChanged(getCurrentOrgId(),isGrid());
						$.messageBox({message:"党组织报到新增成功!"});
						$("#partyorgReportDialog").dialog("close");
					</c:if>
	      	   	},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	    	alert("提交数据时发生错误");
	      	   	}
	      	});
		},
		rules:{
			"partyOrgReport.partyOrgType.id":{
				required:true
			},
			"partyOrgReport.contractor":{
				minlength:2,
				maxlength:20
			},
			"partyOrgReport.telephone":{
				telephone:true
			},
			"partyOrgReport.address":{
				minlength:0,
				maxlength:100
			}
				
		},
		messages:{
			"partyOrgReport.partyOrgType.id":{
				required:"请选择党组织类别"
			},
			"partyOrgReport.contractor":{
				minlength:$.format("负责人至少需要输入{0}个字符"),
				maxlength:$.format("负责人最多需要输入{0}个字符")
			},
			"partyOrgReport.telephone":{
				telephone:"负责人联系电话不合法，只能输数字和横杠(-)"
			},
			"partyOrgReport.address":{
				minlength:$.format("地址至少需要输入{0}个字符"),
				maxlength:$.format("地址最多需要输入{0}个字符")
			}
		},
		ignore:':hidden'
	});
	<c:if test='${mode=="add"}'>
	    $("#maintainForm").attr("action","${path}/partyorgReportManage/addPartyorgReport.action");
	</c:if>
	<c:if test='${mode=="edit"}'>
	    $("#maintainForm").attr("action","${path}/partyorgReportManage/updatePartyorgReport.action");
	</c:if>
	<c:if test='${mode=="add"}'>
		$.ajax({
			async: false,
			url: "${path}/sysadmin/orgManage/getOrgRelativePath.action",
			data:{
				"organization.id":$("#organizationId").val()
			},
			success:function(responseData){
				$("#partyOrgReportOrgName").val(responseData);
				$("#partyOrgName").val(responseData);
			}
		});
	</c:if>
});


</script>


