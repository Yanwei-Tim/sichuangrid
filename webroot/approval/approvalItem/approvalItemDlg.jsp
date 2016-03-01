<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/PopGrid-taglib" prefix="pop"%>
<%@ include file="/includes/baseInclude.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
.tablelist .title{width:11%;}
.tablelist .content{width:17%;}
</style>
<table width="200" class="tablelist">
  <tr>
    <td class="title"><label>事项类别</label></td>
    <td class="content"><span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ITEM_MATTER_KIND" defaultValue="${item.matterKind.id}" /></span></td>
    <td class="title"><label>事项编号</label></td>
    <td class="content" colspan="1"><span>${item.itemNumber}</span></td>
    <td class="title"><label>事项名称</label></td>
    <td class="content" colspan="3"><span>${item.itemName}</span></td>
  </tr>
  <tr>
    <td class="title"><label>承诺时限</label></td>
    <td class="content" width=""><span>${item.promiseTime}</span></td>
    <td class="title"><label>法定时限</label></td>
    <td class="content"><span>${item.legalTime}</span></td>
    <td class="title"><label>是否收费项目</label></td>
    <td class="content"><span><s:if test="item.Fees==true">是</s:if><s:else>否</s:else></span></td>
    <td class="title"><label>收费标准</label></td>
    <td class="content" ><span>${item.standardFees }</span></td>
   </tr>
  <tr>
    <td class="title"><label>备注</label></td>
    <td class="content" colspan="7" ><div style="height:55px;overflow-y:auto;"><span>${item.remark}</span></div></td>
  </tr>
  <tr>
    <td class="title"><label>法定依据</label></td>
    <td class="content" colspan="7" ><div style="height:55px;overflow-y:auto;"><span>${item.legalBasis}</span></div></td>
  </tr>
  <tr>
    <td class="title"><label>办事指南</label></td>
    <td class="content" colspan="7" ><div style="height:50px;overflow-y:auto;"><span>${item.lawGuide}</span></div></td>
  </tr>
  <tr>
    <td class="title"><label>附件</label></td>
    <td class="content" colspan="7" >
	    <div style="height:60px;overflow-y:auto;"> 
			<c:forEach  items="${itemAttachmentList }" var="itemAttachment" >
				<a href="${path }/approval/itemManage/downLoadActualFile.action?itemAttachmentId=${id}"><img  src="${resource_path}/resource/images/fujian.jpg"/><span>${itemAttachment.fileName }</span></a><br>
			</c:forEach >
		</div>
	</td>
  </tr>
</table>

<div id="approvalItem" class="container container_24">
	<form action="" method="post" id="maintainForm">
		<input type="hidden" id="approvalOrgId" value="${approvalItem.organization.id}">
		<input type="hidden" id="orgId" value="${organization.id}">
		<input type="hidden" name="approvalItem.id" value="${approvalItem.id }"/>
		<input type="hidden" name="approvalItem.approvalNumber" value="${approvalItem.approvalNumber }"/>
		<input type="hidden" name="approvalItem.item.id" value="${item.id }"/>
		<s:if test="approvalItem.organization.id != null">
			<input type="hidden" id="approvalItemOrgId" name="approvalItem.organization.id" value="${approvalItem.organization.id}"/>
		</s:if>
		<s:else>
			<input type="hidden" id="approvalItemOrgId" name="approvalItem.organization.id" value="${organization.id}"/>
		</s:else>
		<s:if test="approvalItem.status == @com.tianque.approval.domain.property.ApprovalItemStatus@AUDIT_DOES_NOT_PASS">
			<input type="hidden" name="approvalItem.status" id="status" value='<s:property value="@com.tianque.approval.domain.property.ApprovalItemStatus@PENDING_REVIEW"/>' />
		</s:if>
		<s:else>
			<input type="hidden" name="approvalItem.status" id="status" value="${approvalItem.status }" />
		</s:else>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">申请人名称：</label>
		</div>
		<div class="grid_3">                  
			<input type="text" id="name" name="approvalItem.name" value="${approvalItem.name }" style="width: 99%" 
				class='form-txt {required:true,exculdeParticalChar:true,minlength:2,maxlength:20,messages:{required:"请输入申请人名称",exculdeParticalChar:"不能输入非法字符",minlength:$.format("申请人名称至少需要输入{0}个字符"),maxlength:$.format("申请人名称最多需要输入{0}个字符")}}' maxlength="20"/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em> <label class="form-lb1">身份证号码：</label>
		</div>
		<div class="grid_4">
			<input type="text" id="itemName" name="approvalItem.idCardNo" value="${approvalItem.idCardNo }" style="width: 99%" 
				class='form-txt {required:true,idCard:true,messages:{required:"请输入身份证号码",idCard:"请输入一个合法的身份证号码"}}' maxlength="18"/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1">移动电话：</label>
		</div>
		<div class="grid_3">
			<input type="text" name="approvalItem.mobileNumber" id="mobileNumber" value="${approvalItem.mobileNumber }" class='form-txt {required:true,mobile:true,messages:{required:"请输入移动电话",mobile:"移动电话输入只能是以1开头的11位数字"}}' maxlength="11"/>
		</div>
		<div class='clearLine'>&nbsp;</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em><label class="form-lb1">常住地址：</label>
		</div>
		<div class="grid_11">
			<input type="text" name="approvalItem.currentAddress" id="currentAddress" value='${approvalItem.currentAddress }' style="width: 99%" class='form-txt {required:true,messages:{required:"请输入常住地址"}}' maxlength="100"/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
  			<label class="form-lbl">办理网格：</label>
  		</div>
   		<div class="grid_3 form-left">
			<input type="text" id="issueOccurOrgSelector" name="selectOrgName" value="" class="form-txt" />
   		</div>
		<div class='clearLine'>&nbsp;</div>
   		<div class="grid_4 lable-right">
   			<label class="form-lb1">备注：</label>
   		</div>
   		<div class="grid_18 heightAuto">
   			<textarea rows="5" cols="77" name="approvalItem.remark" id="remark" class="form-txt {isLawful:true,messages:{isLawful:'您输入了非法脚本，请重新输入'}}" style="width: 99%">${approvalItem.remark }</textarea>
   		</div>
   		<select id="attachFileNames" name="attachFiles" multiple="multiple" style="width:200px;display:none"></select>
	</form>
	<div style="margin-left:125px;">
		<div  id="custom-queue" style="width: 560px; height:70px;overflow-y: auto;overflow-x: hidden;border:1px solid #7F9DB9"></div>
		<input id="custom_file_upload" name="uploadFile" type="file" />
	</div>
</div>


<script type="text/javascript">
$(document).ready(function(){
	$('#remark').richImg();
	$('#custom_file_upload').uploadFile({
		queueID : 'custom-queue',
		selectInputId : "attachFileNames"});

	$("#maintainForm").formValidate({
		submitHandler: function(form) {
			$(form).ajaxSubmit({
         		success: function(data){
					if(data==null || !data.id){
	               	 	$.messageBox({message:data,level: "error"});
                		return;
                	}
                	if("${mode}" == "add"){
						 $.messageBox({message:"申请事项信息新增成功!"});
						 if($("#status").val()=='<s:property value="@com.tianque.approval.domain.property.ApprovalItemStatus@IN_PROCESS"/>'){
								$("#itemToIssueDlg").createDialog({
				        			width:880,
				        			height:520,
				        			title:'转入事件',
				        			url:"${path}/issues/issueManage/approvalItemToIssueDlg.action?approvalItem.id="+data.id,
				        			buttons: {
				        				"确定" : function(event){
				        					$("#issueMaintainForm").attr("action","${path}/approval/approvalItemManage/approvalItemToIssue.action");
				        					$("#issueMaintainForm").submit();
				        				},
				        				"关闭" : function(event){
				        					$(this).dialog("close");
				        				}
				        			}
				        		});
								
							}
                	}else{
                		$("#didNotPassList").delRowData(data.id);
    					 $.messageBox({message:"申请事项信息修改成功!"});
                	}
					 $("#itemDialog").dialog("close");
	  	   		},
	      	   	error: function(XMLHttpRequest, textStatus, errorThrown){
	      	      	alert("提交错误");
	      	   	}
      	  	});
		},
		rules:{
			"selectOrgName":{
				required:true,
				orgLevelLessEqual:function(){
						return [getOccurOrgId(),<s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>];
					}
			}
		},
		messages:{
			"selectOrgName":{
				required:"请选择发生网格",
				orgLevelLessEqual:"发生网格不能选择乡镇（街道）以上级别"
			}
		}
	});

	<s:if test="approvalItemFileList!=null && approvalItemFileList.size > 0">
	    <s:iterator value="approvalItemFileList">
		    $("#custom-queue").addUploadFileValue({
			     id:"<s:property value='id'/>",
			     filename:"<s:property value='fileName'/>",
			     link:"${path }/approval/itemManage/downLoadActualFile.action?itemAttachmentId=${id}",   	     	 
				 onRemove:function(){removeAttach("<s:property value='fileName' escape='false'/>")}
		     });
		    $("<option>").attr("id","<s:property value='id'/>").val("<s:property value='id'/>,<s:property value='fileName' escape='false'/>").html('<s:property value='fileName' escape='false'/>').attr("selected",true).appendTo($("#attachFileNames"));
	    </s:iterator>
	</s:if>

	<s:if test='"add".equals(mode)'>
		$("#maintainForm").attr("action","${path}/approval/approvalItemManage/addApprovalItem.action");
	</s:if>
	<s:else>
		$("#maintainForm").attr("action","${path}/approval/approvalItemManage/updateApprovalItem.action");
	</s:else>

	initOccurOrgSelector();
});

function removeAttach(fileName){
	$("input[name='file']").removeAttr("disabled");
	$("#attachFileNames").find("option:contains("+fileName+")").remove();
}

$("#isFees").click(function(event){
	if($("#isFees").attr("checked")){
		$("#standardFees").removeAttr("readonly");
	}else{
		 $("#standardFees").attr("readonly", true);
		 $("#standardFees").val("");
	}
});

function initOccurOrgSelector(){
	var orgId=$("#orgId").val();
	var approvalOrgId=$("#approvalOrgId").val();
	var tree=$("#issueOccurOrgSelector").treeSelect({
		inputName:"approvalItem.organization.id",
		onSelect:occurOrgChanged
	});
	if(approvalOrgId != ""&&approvalOrgId!=null){
		$.setTreeValue("${approvalItem.organization.id}",tree); 
	}else{
		$.setTreeValue("${organization.id}",tree); 
	} 
}
function  getOccurOrgId(){
	return $("#approvalItemOrgId").val();
} 

function occurOrgChanged(){
}
</script>