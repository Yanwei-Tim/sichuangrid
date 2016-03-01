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
<input type="hidden" id="foodSaftyId" value="${foodSafty.id}"/>
<table class="tablelist" id="infoList">
<tr>
		 <td class="title"><label>所属网格</label></td>
		 <td class="content" colspan="3"><span>${foodSafty.organization.orgName }</span></td>
</tr>
<tr>
		 <td class="title"><label>时间</label></td>
		 <td class="content" colspan="3"><span><s:date name="foodSafty.inputTime" format="yyyy-MM-dd HH:mm:ss"/></span></td>
</tr>
<tr>
		 <td class="title"><label>地点</label></td>
		 <td class="content" colspan="3"><span>${foodSafty.address}</span></td>
</tr>
<tr>
		 <td class="title"><label>类别</label></td>
		 <td class="content" colspan="3">
		 <span><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@FOOD_SAFTY_CATEGORY" defaultValue="${foodSafty.category.id}" /></span>
		 </td>
</tr>
<tr>
		 <td class="title"><label>5桌以上申报</label></td>
		 <td class="content" colspan="3"><span><s:if test='foodSafty.declaration!=null&&foodSafty.declaration==1'>是</s:if><s:else>否</s:else></span></td>
</tr>
<tr>
		 <td class="title"><label>参与人员</label></td>
		 <td class="content" colspan="3"><span>${foodSafty.personnel}</span></td>
</tr>
<tr>
		 <td class="title"><label>情况描述</label></td>
		 <td class="content" colspan="3"><span>${foodSafty.details}</span></td>
</tr>
<tr>
		 <td class="title"><label>备注</label></td>
		 <td class="content" colspan="3"><span>${foodSafty.remake}</span></td>
</tr>
<tr>
		 <td class="title"><label>网格员姓名</label></td>
		 <td class="content" colspan="3"><span>${foodSafty.createUser}</span></td>
</tr>
<tr>
		 <td class="title"><label>网格员电话</label></td>
		 <td class="content" colspan="3"><span>${foodSafty.telephone}</span></td>
</tr>
<c:if test="${foodSafty.isSign==1 }">
	<tr>
			 <td class="title"><label>签收人</label></td>
			 <td class="content" colspan="3"><span>${foodSafty.signPeople}</span></td>
	</tr>
	<tr>
			 <td class="title"><label>签收日期</label></td>
			 <td class="content" colspan="3"><span><s:date name="foodSafty.signDate" format="yyyy-MM-dd HH:mm:ss"/></span></td>
	</tr>
	<tr>
			 <td class="title"><label>签收意见</label></td>
			 <td class="content" colspan="3"><span>${foodSafty.signContent}</span></td>
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
	    <s:if test="foodSafty.photos!=null && foodSafty.photos.size > 0">
				<s:iterator value="foodSafty.photos" var="att">
				 fileNames += "<a href='${path}/serviceList/foodSaftyManage/downLoadAttachFile.action?attachFileId=${att.id}'>${att.name}</a><br/>";
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
			$.get(PATH+"/serviceList/foodSaftyManage/getReplyList.action",{'sidx':'replydate','sord':'asc','reply.serviceId':$("#foodSaftyId").val()},function(data){
				$("#showTaskListReply").html(data);
			});
		}
	});
});
</script>

