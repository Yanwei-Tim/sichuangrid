<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ include file="/includes/baseInclude.jsp"%>

<div id="dialog-form" title="我的群组维护" class="container container_24">
	<c:if test='${mode!="view"}'>
	    <form id="maintainForm" method="post" action="" >
	    <pop:token/>
	</c:if>
		<div class="container container_24">
			<input id="mode" type="hidden" name="mode" value="${mode}" />
			<input type="hidden" name="myGroup.id" value="${myGroup.id}" />
		    <div class="grid_4 lable-right">
		    <em class="form-req">*</em>
				<label >群组名称：</label>
			</div>
			<div class="grid_19">
				<input type="text" name="myGroup.name" class="form-txt" maxlength="20" title="群组的名称，请您输入 2-20个字符"
					<c:if test='${mode=="view"}'>disabled='true'</c:if> id="myGroupName" value="${myGroup.name}"/>
			</div>
			<c:if test='${mode!="view"}'>
				<div class="grid_1"></div>
			</c:if>
			<div style="clear:both"></div>
			<div class="grid_4 lable-right">
				<label >群组描述：</label>
			</div>
			<div style="display:inline;float:left;height:160px;line-height:160px;width:77%;">
				<textarea name="myGroup.remark" style="height:145px;" <c:if test='${mode=="view"}'>disabled='true'</c:if>
				 class="form-txt">${myGroup.remark}</textarea>
			</div>
		</div>

<c:if test='${mode!="view"}'>
	</form>
</c:if>
</div>
<script type="text/javascript">
$(document).ready(function(){
	<c:if test='${mode!="view"}'>

		$("#maintainForm").formValidate({
			promptPosition: "bottomLeft",
			submitHandler: function(form) {
				doAjaxSubmit();
			},
			rules:{
				"myGroup.name":{
					required:true,
					exculdeParticalChar:true,
					minlength:2,
					maxlength:20
				},
				"myGroup.remark":{
					maxlength:200
				}
			},
			messages:{
				"myGroup.name":{
					required:"请输入群组名称",
					exculdeParticalChar:"不能输入特殊字符",
					minlength:$.format("名称最少需要输入{0}个字符"),
					maxlength:$.format("名称最多只能输入{0}个字符")
				},
				"myGroup.remark":{
					maxlength:$.format("备注最多只能输入{0}个字符")
				}
			}
		});
	</c:if>

	<c:if test='${mode=="add"}'>
		$("#maintainForm").attr("action","${path}/contact/myGroupManage/addMyGroup.action");
	</c:if>
	<c:if test='${mode=="edit"}'>
		$("#maintainForm").attr("action","${path}/contact/myGroupManage/updateMyGroup.action");
	</c:if>
});

function doAjaxSubmit(){
	$("#maintainForm").ajaxSubmit({
        success: function(data){
                if(!data.id){
               	 $.messageBox({message:data,level: "error"});
                	return;
                }
   	   		 	<c:if test='${mode=="add"}'>
			    	$.messageBox({message:"成功保存我的群组信息!"});
			     </c:if>
			     <c:if test='${mode=="edit"}'>
				    $.messageBox({message:"成功保存我的群组修改信息!"});
			     </c:if>
			     $("#myGroupList").trigger("reloadGrid");
			     $("#maintainMyGroupDlg").dialog("close");
 	   },
 	   error: function(XMLHttpRequest, textStatus, errorThrown){
 	     $.messageBox({message:"提交错误",level: "error"	});
 	   }
 	});
}

function doNothing(){}

</script>