<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div style="display: none;">
mode:<input id="mode" type="hidden" name="mode" value="${mode! }" /> 
orgId:<input id="serviceRecordOrgId" type="hidden" name="serviceRecord.organization.id" value="<@s.property value="@com.tianque.util.ThreadVariable@getOrganization().id"/>" />
teamId:<input type="hidden" name="serviceTeam.id" value="${(serviceRecordVo.id)! }" /> 
</div>
<table class="tablelist">
	<tr>
		<td class="title"><label>服务时间：</label></td>
		 <td class="content" colspan="3"><span><@s.date name="serviceRecordVo.occurDate" format="yyyy-MM-dd" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>记录新增时间：</label></td>
		 <td class="content" colspan="3"><span><@s.date name="serviceRecordVo.createDate" format="yyyy-MM-dd HH:mm:ss" /></span></td>
	</tr>
	<tr>
		<td class="title"><label>服务地点：</label></td>
		<td class="content" colspan="3"><span>${(serviceRecordVo.occurPlace)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>记录所属人：</label></td>
		<td class="content" colspan="3"><span>${(serviceRecordVo.serviceMembers)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>服务参与人：</label></td>
		<td class="content" colspan="3"><span>${(serviceRecordVo.serviceJoiners)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>服务对象：</label></td>
		<td class="content" colspan="3"><span>${(serviceRecordVo.serviceObjects)!}</span></td>
	</tr>
	<@s.if test="showRecordType==1">
		<td class="title"><label>记录类型：</label></td>
		<@s.if test="${(serviceRecordVo.recordType)!}==0">
			<td class="content" colspan="3"><span>排查类</span></td>
		</@s.if>
		<@s.else>
			<td class="content" colspan="3"><span>整改类</span></td>
		</@s.else>
	</@s.if>
	<tr>
		<td class="title"><label>服务内容：</label></td>
		<td class="content" colspan="3"><span>${(serviceRecordVo.serviceContent)!}</span></td>
	</tr>
	
	
	<tr id="fatesonid">
		<td class="title"><label>附件上传：</label></td>
		<td class="content" colspan="3">
		    <div id="custom-queue"></div>
		</td>
	</tr>
	
	 
</table>

<script type="text/javascript">
	$(document).ready(function(){
	var  fileNames="";
	    <@s.if test="serviceRecordVo.serviceRecordAttachments!=null && serviceRecordVo.serviceRecordAttachments.size > 0">
				<@s.iterator value="serviceRecordVo.serviceRecordAttachments" var="att">
				 fileNames+=  "<a href='${path}/plugin/serviceTeam/serviceRecord/downloadServiceRecordAttachment.action?attachmentId=${att.id}'>${att.fileName}</a><br/>";
				</@s.iterator>
			</@s.if>
			<@s.else>
			$("#fatesonid").hide();  
			</@s.else>
	$("#custom-queue").html(fileNames);		
	});
</script>


