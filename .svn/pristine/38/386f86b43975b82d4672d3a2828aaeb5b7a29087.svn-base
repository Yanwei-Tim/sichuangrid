<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div id="tabList">
	<ul>
		<li><a  href="javascript:;" id="infoTab">信息</a> </li>
		<li><a  href="javascript:;" id="replyInfos">回复列表</a> </li>
	</ul>
</div>
<input type="hidden" id="otherSituationManageId" value="${otherSituationManage.id }"/>
<table class="tablelist" id="infoList">
<tr>
		 <td class="title"><label>所属网格</label></td>
		 <td class="content" colspan="3"><span>${otherSituationManage.organization.orgName }</span></td>
</tr>
<tr>
		 <td class="title"><label>时间</label></td>
		 <td class="content" colspan="3"><span><s:date name="otherSituationManage.inputTime" format="yyyy-MM-dd HH:mm:ss"/></span></td>
</tr>
<tr>
		 <td class="title"><label>地点</label></td>
		 <td class="content" colspan="3"><span>${otherSituationManage.address}</span></td>
</tr>
<tr>
		 <td class="title"><label>详细情况</label></td>
		 <td class="content" colspan="3"><span>${otherSituationManage.details}</span></td>
</tr>
<tr>
		 <td class="title"><label>备注</label></td>
		 <td class="content" colspan="3"><span>${otherSituationManage.remake}</span></td>
</tr>
<tr>
		 <td class="title"><label>网格员姓名</label></td>
		 <td class="content" colspan="3"><span>${otherSituationManage.createUser}</span></td>
</tr>
<tr>
		 <td class="title"><label>网格员电话</label></td>
		 <td class="content" colspan="3"><span>${otherSituationManage.telephone}</span></td>
</tr>
<c:if test="${otherSituationManage.isSign==1 }">
	<tr>
			 <td class="title"><label>签收人</label></td>
			 <td class="content" colspan="3"><span>${otherSituationManage.signPeople}</span></td>
	</tr>
	<tr>
			 <td class="title"><label>签收日期</label></td>
			 <td class="content" colspan="3"><span><s:date name="otherSituationManage.signDate" format="yyyy-MM-dd HH:mm:ss"/></span></td>
	</tr>
	<tr>
			 <td class="title"><label>签收意见</label></td>
			 <td class="content" colspan="3"><span>${otherSituationManage.signContent}</span></td>
	</tr>
</c:if>
<tr id="fatesonid">
		<td class="title"><label>附件上传</label></td>
		<td class="content" colspan="3">
		    <div id="custom-queue"></div>
		</td>
	</tr>
</table>

<div id="showTaskListReply"></div>
<script type="text/javascript">
$(document).ready(function(){
	var  fileNames="";
	    <s:if test="otherSituationManage.photos!=null && otherSituationManage.photos.size > 0">
				<s:iterator value="otherSituationManage.photos" var="att">
				 fileNames += "<a href='${path}/serviceList/otherSituationManageManage/downLoadAttachFile.action?attachFileId=${att.id}'>${att.name}</a><br/>";
				</s:iterator>
			</s:if>
			<s:else>
			$("#fatesonid").hide();  
			</s:else>
	$("#custom-queue").html(fileNames);
	
	$( "#tabList").tabs({ selected: 0});
	$("#infoTab").click(function(){
		$("#infoList").show();
		$("#showTaskListReply").hide();
	});
	$("#replyInfos").click(function(){
		$("#infoList").hide();
		$("#showTaskListReply").show();
		if($("#showTaskListReply").html()==""){
			$.get(PATH+"/serviceList/otherSituationManageManage/getReplyList.action",{'mode':'viewReplyList','sidx':'replydate','sord':'asc','reply.serviceId':$("#otherSituationManageId").val()},function(data){
				$("#showTaskListReply").html(data);
			});
		}
	});
});
</script>

