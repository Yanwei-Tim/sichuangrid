<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/includes/baseInclude.jsp"%>	
<div id="taskDlgForm" title="任务管理" class="container container_24">
	<form id="maintainForm" method="post"
		action="${path}/task/taskManage/<s:if test="'edit'.equals(mode)">updateTask.action</s:if><s:else>addTask.action</s:else>">
		<input type="hidden" name="task.id" value="${task.id}"/>
		<input type="hidden" name="task.closed" value="0"/>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">任务名称：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="task.name" maxlength="50" value="${task.name}"
				class="form-txt {required:true,exculdeParticalChar:true,lettersonly:true,messages:{required:'请输入任务名称',lettersonly:'只能是字母',exculdeParticalChar:'不能输入非法字符'}}" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">任务组：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="task.taskGroup" maxlength="50" value="${task.taskGroup}"
				class="form-txt {required:true,exculdeParticalChar:true,lettersonly:true,messages:{required:'请输入任务组名称',lettersonly:'只能是字母',exculdeParticalChar:'不能输入非法字符'}}" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">策略：</label>
		</div>
		<div class="grid_8">
			<input type="hidden" name="task.taskPloy.id" id="ployId" value="${task.taskPloy.id}"/>
			<input type="text"  id="ploySelect" readonly="readonly"  <s:if test="'edit'.equals(mode)">value="${task.taskPloy.cname}(${task.taskPloy.ename})"</s:if>
				class="form-txt hasDatepicker {required:true,messages:{required:'请选择策略'}}" />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">时间：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="task.config" maxlength="50" value="${task.config}"
				class="form-txt {required:true,messages:{required:'请输入时间配置'}}" />
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">任务描述：</label>
		</div>
		<div class="grid_20 heightAuto">
			<textarea class="form-txt " style="height: 100px" maxlength="100" name="task.description" >${task.description}</textarea>
		</div>
	</form>
</div>

<script type="text/javascript">
$(function(){
	
	//表单验证 初始
	$('#maintainForm').formValidate({
		promptPosition : 'bottomLeft',
		submitHandler : function(form) {
			$(form).ajaxSubmit( {
				success : function(data) {
					if(data) {
						var mode = '${mode}';
						if('edit'==mode){
						   $.messageBox({message:'成功编辑任务信息!'});
						}else{
							$.messageBox( {message : '成功保存任务信息!'});
						}
						$("#taskList").trigger("reloadGrid");
						$('#taskDialog').dialog('close');
					} else {
						$.messageBox( {
							level : 'error',
							message : data
						});
						return;
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('提交错误');
				}
			});
		},
		rules:{
		},
		messages:{
		}
	});
	
	//策略选择
	$('#ploySelect').click(function(){
		$('#taskPloySelectDialog').createDialog({
			width:600,
			height:500,
			title:'选择策略',
			url:'${path}/task/taskPloySelect.jsp',
			buttons:{
				'确定':function(){
					var id = $('#taskPloyList').getSelectedRowId();
					if(null == id || "" == id){
						$.messageBox({
							level:'warn',
							message:'请选择行'
						});
						return;
					}
					var taskPloy =  $('#taskPloyList').getRowData(id);
					$('#ployId').val(taskPloy.id);
					$('#ploySelect').attr('ployId',taskPloy.id).val(taskPloy.cname+'('+taskPloy.ename+')');
					$(this).dialog('close');
				},
				'关闭':function(){$(this).dialog('close');}
			}
		});
	});
});
	
</script>