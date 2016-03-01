<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>	
<div id="taskPloyDlgForm" title="策略管理" class="container container_24">
	<form id="maintainForm" method="post"
		action="${path}/task/taskPloyManage/<s:if test="'edit'.equals(mode)">updateTaskPloy.action</s:if><s:else>addTaskPloy.action</s:else>">
		<s:token />
		<input type="hidden" name="taskPloy.id" value="${taskPloy.id}"/>
		<div class="grid_3 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">中文名称：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="taskPloy.cname" maxlength="50" value="${taskPloy.cname}"
			class="form-txt {required:true,exculdeParticalChar:true,messages:{required:'请输入策略中文名称',exculdeParticalChar:'不能输入非法字符'}}" />
		</div>
		<div class="grid_3 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">英文名称：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="taskPloy.ename"
				maxlength="50" value="${taskPloy.ename}"
				class="form-txt {required:true,exculdeParticalChar:true,lettersonly:true,messages:{required:'请输入策略英文名称',lettersonly:'只能是字母',exculdeParticalChar:'不能输入非法字符'}}" />
		</div>
		<div class="grid_3 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">类型：</label>
		</div>
		<div class="grid_8">
			<select name="taskPloy.type.id" class="form-txt {required:true,messages:{required:'请选择策略类型'}}" >
				<pop:OptionTag name="@com.tianque.domain.property.PropertyTypes@TASKPLOY_TYPE"  defaultValue="${taskPloy.type.id}"/>
			</select>
		</div>
		<div class='clearLine'></div>
		<div class="grid_3 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">策略代码：</label>
		</div>
		<div class="grid_20 heightAuto">
			<textarea class="form-txt {required:true,messages:{required:'请输入策略代码'}}" style="height: 85px" name="taskPloy.code" >${taskPloy.code}</textarea>
		</div>
		<div class='clearLine'></div>
		<div class="grid_3 lable-right">
			<label class="form-lbl">策略描述：</label>
		</div>
		<div class="grid_20 heightAuto">
			<textarea class="form-txt " style="height: 85px" name="taskPloy.description" maxlength="100">${taskPloy.description}</textarea>
		</div>
	</form>
</div>

<script type="text/javascript">
$(function(){
	//初始表单验证
	$('#maintainForm').formValidate({
		promptPosition : 'bottomLeft',
		submitHandler : function(form) {
			$(form).ajaxSubmit( {
				success : function(data) {
					if (data) {
						var mode = '${mode}';
						if('edit' == mode){
						   $.messageBox({message:'成功编辑策略信息!'});
						}else{
							$.messageBox( {
								message : '成功保存策略信息!'
							});
						}
						$("#taskPloyList").trigger("reloadGrid");
						$('#taskPloyDialog').dialog('close');
					} else {
						$.messageBox( {
							level : 'error',
							message : data
						});
						return;
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("提交错误");
				}
			});
		},
		rules:{
		},
		messages:{
		}
	});
});
	
</script>