<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
<@s.include value="/includes/baseInclude.jsp"/>
<div id="tabList">
	<ul>
		<li><a  href="javascript:;" id="infoTab">信息</a> </li>
		<li><a  href="javascript:;" id="taskListReplyTab">回复列表</a> </li>
	</ul>
</div>
<table class="tablelist" id="infoList">
	<tr>
		<td class="title"><label>所属网格</label></td>
		<td class="content" colspan="3"><span>${(termerRecord.organization.fullOrgName)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>姓名</label></td>
		 <td class="content" colspan="3"><span>${(termerRecord.name)!}</span></td>
	</tr>
	<tr>
		 <td class="title"><label>身份证号码</label></td>
		 <td class="content" colspan="3"><span>${(termerRecord.idCard)!}</span></td>
	</tr>
	<tr>
		 <td class="title"><label>电话号码</label></td>
		 <td class="content" colspan="3"><span>${(termerRecord.phone)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>地点</label></td>
		<td class="content" colspan="3"><span>${(termerRecord.address)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>时间</label></td>
		<td class="content" colspan="3"><span><@s.date name="termerRecord.recordDate" format="yyyy-MM-dd HH:mm:ss" /></span></td>
	</tr>
	<tr>
		 <td class="title"><label>帮扶人员</label></td>
		 <td class="content" colspan="3"><span>${(termerRecord.helpPeople)!}</span></td>
	</tr>
	<tr>
		 <td class="title"><label>有无异常</label></td>
		 <td class="content" colspan="3"><span><#if termerRecord.hasException == 1>有<#else>无</#if></span></td>
	</tr>
	<tr>
		<td class="title"><label>异常情况</label></td>
		<td class="content" colspan="3"><span>${(termerRecord.exceptionSituationInfo)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>网格员联系电话</label></td>
		<td class="content" colspan="3"><span>${(termerRecord.gridMemberPhone)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>签收人</label></td>
		<td class="content" colspan="3"><span>${(termerRecord.signMemberName)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>签收时间</label></td>
		<td class="content" colspan="3"><span><@s.if test='termerRecord.status == 1'><@s.date name="termerRecord.signDate" format="yyyy-MM-dd HH:mm:ss" /></@s.if></span></td>
	</tr>
	<tr>
		<td class="title"><label>签收意见</label></td>
		<td class="content" colspan="3"><span>${(termerRecord.attitude)!}</span></td>
	</tr>
	<tr>
		<td class="title"><label>备注</label></td>
		<td class="content" colspan="3"><span>${(termerRecord.mark)!}</span></td>
	</tr>
	
 	<tr id="fatesonid">
		<td class="title"><label>附件上传：</label></td>
		<td class="content" colspan="3">
		    <div id="custom-queue"></div>
		</td>
	</tr>
</table>
<div id="showTaskListReply"></div>
<script type="text/javascript">
	$(document).ready(function(){
		var  fileNames="";
		    <@s.if test="termerRecord.taskListAttachFiles!=null && termerRecord.taskListAttachFiles.size > 0">
					<@s.iterator value="termerRecord.taskListAttachFiles" var="att">
					 fileNames += "<a href='${path}/plugin/taskListManage/common/downLoadAttachFile.action?attachFileId=${(att.id)!}'>${(att.fileName)!}</a><br/>";
					</@s.iterator>
				</@s.if>
				<@s.else>
				$("#fatesonid").hide();  
				</@s.else>
		$("#custom-queue").html(fileNames);		
		
		$( "#tabList" ).tabs({ selected: 0});
		$("#infoTab").click(function(){
			$("#infoList").show();
			$("#showTaskListReply").hide();
		});
		$("#taskListReplyTab").click(function(){
			$("#infoList").hide();
			$("#showTaskListReply").show();
			if($("#showTaskListReply").html()==""){
				$.get(PATH+"/plugin/taskListManage/common/taskListReplyListDlg.action",{'taskListReply.moduleKey':'reply_termerRecord','taskListReply.taskId':'${id}'},function(data){
					$("#showTaskListReply").html(data);
				});
			}
		});
	});
</script>
