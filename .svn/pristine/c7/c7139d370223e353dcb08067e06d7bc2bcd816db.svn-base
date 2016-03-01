<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/> 

<form action="${path}/plugin/taskListManage/common/signRecord.action" method="post" id="maintainForm">
	<@pop.token />
<div class="container container_24">
	<input type="hidden" name="mode" id="mode" value="${(mode)!}" />
	<input type="hidden" name="druggyTask.organization.id" id="orgId" value="${(druggyTask.organization.id)!}"/>
	<input type="hidden" name="receipt.id" id="druggyTaskId" value="${(druggyTask.id)!}" />
	<input type="hidden" name="receipt.objectType" id="objectType" value="<@s.property value='@com.tianque.plugin.taskList.constants.Constants@DRUGGYTASK_KEY' />"/>
	<div class="grid_4 lable-right">
		<em class="form-req">*</em>
		<label class="form-lb1>">姓名：</label>
	</div>
	<div class="grid_7">
		<input type="text"  id="name"  name="druggyTask.name"   value="${(druggyTask.name)!}" readonly  class="form-txt" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">身份证号码：</label>
	</div>
	<div class="grid_7">
		<input name="druggyTask.idCard" value="${(druggyTask.idCard)!}"  readOnly class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>
		
	<div class="grid_4 lable-right">
		<label class="form-lbl">电话号码：</label>
	</div>
	<div class="grid_7">
		<input name="druggyTask.phone" value="${(druggyTask.phone)!}" readOnly class="form-txt" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">时间：</label>
	</div>
	<div class="grid_7">
	    <input type="text" name="druggyTask.time" id="time"  value="<@s.date name="druggyTask.time" format="yyyy-MM-dd HH:mm:ss" />" class="form-txt" readonly/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">地点：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="druggyTask.place"  id="place" value="${(druggyTask.place)!}" style="width:99%" <@s.if test='"sign".equals(mode)'>readonly</@s.if> class="form-txt"/>
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">家属联系电话：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="druggyTask.familyTel"  id="place" value="${(druggyTask.familyTel)!}" style="width:99%" <@s.if test='"sign".equals(mode)'>readonly</@s.if> class="form-txt"/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">帮扶人员：</label>
	</div>
	<div class="grid_19 heightAuto" style="margin-top:5px;">
		<input type="text" id="helpPeople"  value="${(druggyTask.helpPeople)!}" class='form-txt' <@s.if test='"sign".equals(mode)'>readonly</@s.if>/>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">所属网格：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="druggyTask.organization.fullOrgName" id="orgname"  value="${(druggyTask.organization.fullOrgName) ! }" <@s.if test='"sign".equals(mode)'>readonly</@s.if> class="form-txt" />
	</div>
	<div class="grid_5 lable-right">
		<label class="form-lbl">网格员联系电话：</label>
	</div>
	<div class="grid_7">
		<input type="text" name="druggyTask.reporterTel" id="reporterTel"  value="${(druggyTask.reporterTel) ! }" <@s.if test='"sign".equals(mode)'>readonly</@s.if> class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">有无异常：</label>
	</div>
	<div class="grid_19">
		<@s.if test="druggyTask.hasException==1">有</@s.if> 
		<@s.if test="druggyTask.hasException==0">无</@s.if> 
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">异常详情：</label>
	</div>
	<div class="grid_19">
		<input type="text"  id="exception"  name="druggyTask.exception"   value="${(druggyTask.exception)!}" readonly  class="form-txt" />
	</div>
	
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">备注：</label>
	</div>
	<div class="grid_19">
		<input type="text" name="druggyTask.remark" id="remark"  value="${(druggyTask.remark) ! }" <@s.if test='"sign".equals(mode)'>readonly</@s.if> class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">签收人：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="signMemberName"  name="receipt.signMemberName" value='<@s.property value="@com.tianque.core.util.ThreadVariable@getUser().getName()"/>' <@s.if test='"sign".equals(mode)'>readonly</@s.if> class="form-txt" />	
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">签收时间：</label>
	</div>
	<div class="grid_19">
		<input type="text" id="signDate" name="druggyTask.signDate" value="<@s.date name="druggyTask.signDate" format="yyyy-MM-dd HH:mm:ss" />"  readonly class="form-txt" />
	</div>
	<div class='clearLine'>&nbsp;</div>
	
	<div class="grid_4 lable-right">
		<label class="form-lbl">签收意见：</label>
	</div>
	<div class="grid_19 heightAuto">
	      <textarea rows="4" name="receipt.attitude"  maxlength="200" cols="" class="form-txt"></textarea>
	</div>
	<div class='clearLine'>&nbsp;</div>
	<div class="grid_4 lable-right">
		<label class="form-lbl">附件上传：</label>
	</div>
	<div class="grid_19" id="custom-queue" colspan="3" >
	</div>
</div>	
</form>

<script type="text/javascript">
$(document).ready(function(){
	$("#maintainForm").formValidate({
		promptPosition:"bottomLeft",
		submitHandler: function(form){
			 $("#maintainForm").ajaxSubmit({
					success : function(data) {
						if("sign"=="${mode}"){
							$.messageBox({message:"签收成功！"});
						}
						$("#druggyTaskDialog").dialog('close');
						$("#druggyTaskList").trigger("reloadGrid");
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("提交错误");
					}
			 });
		},
	});
	var  fileNames="";
	    <@s.if test="druggyTask.annexFiles!=null && druggyTask.annexFiles.size > 0">
				<@s.iterator value="druggyTask.annexFiles" var="att">
				 fileNames += "<a href='${path}/plugin/taskListManage/common/downLoadAttachFile.action?attachFileId=${(att.id)!}'>${(att.fileName)!}</a><br/>";
				</@s.iterator>
			</@s.if>
			<@s.else>
			$("#fatesonid").hide();  
			</@s.else>
	$("#custom-queue").html(fileNames);	
});
	
</script>
