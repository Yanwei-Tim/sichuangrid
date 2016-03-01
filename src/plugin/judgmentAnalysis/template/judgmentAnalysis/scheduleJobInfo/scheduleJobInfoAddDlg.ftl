<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>


<div title="任务管理" class="container container_24">

	<form id="scheduleJobInfoForm" method="post"
		action="${path}/judgmentAnalysis/scheduleJobInfoManage/<@s.if test="'edit'.equals(mode)">editScheduleJobInfo.action</@s.if><@s.else>addScheduleJobInfo.action</@s.else>"
	>
		<@pop.token />
		<input type="hidden" name="scheduleJobInfo.id" value="${(scheduleJobInfo.id)!}"/>
	 
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">执行顺序：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="scheduleJobInfo.ordered" maxlength="50" value="${(scheduleJobInfo.ordered)!}"
				class="form-txt {required:true,exculdeParticalChar:true,messages:{required:'请输入执行顺序'}}" 
				<@s.if test='"view".equals(mode)'> readonly </@s.if>
				/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">批量数：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="scheduleJobInfo.batchNum" maxlength="50" value="${(scheduleJobInfo.batchNum)!}"
				class="form-txt {required:true,exculdeParticalChar:true,messages:{required:'批量数'}}"
				<@s.if test='"view".equals(mode)'> readonly </@s.if>
				 />
		</div>
		
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">执行类型：</label>
		</div>
		<div class="grid_8">
		   <select id="jobInfo_type" name="scheduleJobInfo.type"  class="form-txt {required:true,messages:{required:'执行类型'}}" 
		   <@s.if test='"view".equals(mode)'> disabled </@s.if>
		    >			  
			   <option value="0" <@s.if test='scheduleJobInfo.type==0'> selected </@s.if> >SQL</option>
			   <option value="1" <@s.if test='scheduleJobInfo.type==1'> selected </@s.if> >JAVA</option>				 
			</select>					 
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">sql类型：</label>
		</div>
		<div class="grid_8">
		
		  <select id="jobInfo_sqlType" name="scheduleJobInfo.sqlType" class="form-txt {required:true,messages:{required:'sql类型'}}" 
		  <@s.if test='"view".equals(mode)'> disabled </@s.if>
		  >			 
			   <option value="1" <@s.if test='scheduleJobInfo.sqlType==1'> selected </@s.if>>新增</option>
			   <option value="2" <@s.if test='scheduleJobInfo.sqlType==2'> selected </@s.if> >查询</option>	
			   <option value="3" <@s.if test='scheduleJobInfo.sqlType==3'> selected </@s.if> >修改</option>
			   <option value="4" <@s.if test='scheduleJobInfo.sqlType==4'> selected </@s.if> >删除</option>			 
			</select>
			
		 </div>
		
		<div class="grid_4 lable-right">
			<em class="form-req"></em>
			<label class="form-lbl">参数：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="scheduleJobInfo.params" maxlength="50" value="${(scheduleJobInfo.params)!}"
				class="form-txt" 
				<@s.if test='"view".equals(mode)'> readonly </@s.if>
				/>
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">是否保存：</label>
		</div>
		<div class="grid_8">
		
			  <select id="jobInfo_saveFlag" name="scheduleJobInfo.saveFlag" class="form-txt {required:true,messages:{required:'是否保存'}}" 
			     <@s.if test='"view".equals(mode)'> disabled </@s.if>
			  >			 
			   <option value="1" <@s.if test='scheduleJobInfo.saveFlag==1'> selected </@s.if> >是</option>
			   <option value="0" <@s.if test='scheduleJobInfo.saveFlag==0'> selected </@s.if> >否</option>				 
			</select>
					 
		</div>
 	
		<div class="grid_4 lable-right">
			<em class="form-req">*</em>
			<label class="form-lbl">任务：</label>
		</div>
		<div class="grid_8">
			<input type="hidden" name="scheduleJobInfo.scheduleJob.id" id="scheduleJobId" value="${(scheduleJobInfo.scheduleJob.id)!}"/>
			<input type="text"  id="jobSelect" readonly="readonly"  
			<@s.if test="!'add'.equals(mode)">value="${(scheduleJobInfo.scheduleJob.name)!}"</@s.if>
		    class="form-txt hasDatepicker {required:true,messages:{required:'请选择任务'}}" 
		    <@s.if test='"view".equals(mode)'> disabled </@s.if>
		    />
		</div>
		<div class="grid_4 lable-right">
			<em class="form-req"></em>
			<label class="form-lbl">组织机构分组：</label>
		</div>
		<div class="grid_8">
			<input type="text" name="scheduleJobInfo.groupName" maxlength="50" value="${(scheduleJobInfo.groupName)!}"
				class="form-txt" 
				<@s.if test='"view".equals(mode)'> readonly </@s.if>
				/>
		</div>
		<div class='clearLine'></div>
		<div class="grid_4 lable-right">
			<label class="form-lbl">执行：</label>
		</div>
		<div class="grid_20 heightAuto">
			<textarea class="form-txt " style="height: 100px" maxlength="1000" name="scheduleJobInfo.executor" 
			<@s.if test='"view".equals(mode)'> readonly </@s.if>
			>${(scheduleJobInfo.executor)!}</textarea>
		</div>
		
	</form>
</div>



<script type="text/javascript">

$(function(){

    //表单验证 初始
	$('#scheduleJobInfoForm').formValidate({
	
		promptPosition : 'bottomLeft',
		submitHandler : function(form) {
		
			$(form).ajaxSubmit( {
				success : function(data) {
					if (typeof data != 'object') {
						$.messageBox( {
							level : 'error',
							message : data
						});
						return;
					}
					var mode = '${mode}';
					if('edit'==mode){
					   $('#scheduleJobInfoList').setRowData(data.id,data);//设置行数据
					   $('#scheduleJobInfoList').setSelection(data.id);//选中
					   $.messageBox({message:'成功编辑任务信息!'});
					}else{
						$('#scheduleJobInfoList').addRowData(data.id, data, 'first');//首行添加一行
						$.messageBox( {message : '成功保存任务信息!'});
					}
					$('#scheduleJobInfoDialog').dialog('close');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert('提交错误');
				}
			});
		}
	});



	   //任务选择
		$('#jobSelect').click(function(){
			$('#scheduleJobSelectDialog').createDialog({
				width:600,
				height:500,
				title:'选择任务',
				url:'${path}/judgmentAnalysis/scheduleJobInfo/scheduleJobSelectDlg.ftl',
				buttons:{
					'确定':function(){
						var id = $('#scheduleJobList').getSelectedRowId();				 					 
						if(id==null || id==0){
							$.messageBox({
								level:'warn',
								message:'请选择行'
							});
							return;
						}
						var scheduleJob =  $('#scheduleJobList').getRowData(id);
						$('#scheduleJobId').val(scheduleJob.id);
						$('#jobSelect').attr('scheduleJobId',scheduleJob.id).val(scheduleJob.name);
						$(this).dialog('close');
					},
					'关闭':function(){$(this).dialog('close');}
				}
			});
		});




});



</script>












