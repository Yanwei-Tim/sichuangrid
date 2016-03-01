<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<table class="tablelist">

   <tr style="display: none;">
   		<td>
   			<s:iterator value="issueTypeNames" var="issueTypeName" status="st">
				<s:if test="issueTypes[#issueTypeName.typeName].size()>0">
					<div class="grid_${issueTypeName.titleWidth} multipeSelect">
					  	<s:if test="!#issueTypeName.typeName.equals('一站审批')">
							<input id="issueTypeSelector${st.index}" name="" type="checkbox" class="category" <s:if test="issueHasTypeDomainName.contains(#issueTypeName.typeName)">checked</s:if> />
							<label class="form-check-text" for="issueTypeSelector${st.index}">${issueTypeName.typeName}</label>
						</s:if>
						<ul  class="zc-sf" >
							<li class="top"><p>请选择</p><span class="close" style="color:#F00">close</span></li>
							<s:iterator value="issueTypes[#issueTypeName.typeName]" var="type" >
								<li><input name="selectedTypes" type="checkbox" value='<s:property value="id"/>' <s:if test="issue.issueTypes.contains(#type)">checked</s:if> />
						 		<label><s:property value="issueTypeName"/></label></li>
							</s:iterator>
							<div class="clear"></div>
						</ul>
					</div>
				</s:if>
			</s:iterator>
   		<td>
	</tr>
	<tr>
		<td class="title"><label>事件名称：</label></td>
		<td class="content"><span>${issue.subject}</span></td>
		<td class="title"><label>发生网格：</label></td>
		<td class="content">${issue.occurOrg.orgName}</td>
	</tr>
	<tr>
		<td class="title"><label>发生地点：</label></td>
		<td class="content"><span>${issue.occurLocation}</span></td>
		<td class="title"><label>发生时间：</label></td>
		<td class="content">
		<span><s:date name="issue.occurDate" format="yyyy-MM-dd HH:mm:ss"/></span></td>
	</tr>
	<tr>	
		<td class="title"><label>是否重大事件：</label></td>
		<td class="content"><s:if test="issue.important">是</s:if><s:else>否</s:else></td>	
		<td class="title"><label>是否紧急事件：</label></td>
		<td class="content"><s:if test="issue.isEmergency">是</s:if><s:else>否</s:else></td>
	</tr>
	<tr>
		<td class="title"><label>事件规模：</label></td>
		<td class="content"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" defaultValue="${issue.issueKind.id}" /></td>
		<td class="title"><label>涉及人数：</label></td>
		<td class="content">${issue.relatePeopleCount}</td>
	</tr>	
	<tr>
		<td class="title"><label>主要当事人：</label></td>
		<td class="content" colspan="3">${issue.mainCharacters}</td>
	</tr>
	<tr>
		<td class="title"><label>事件类型：</label></td>
		<td class="content" colspan="3" id="issueTypeDescription"></td>
	</tr>
	<tr>
		<td class="title">事件简述：</td>
		<td  colspan="3" class="content">${issue.issueContent}</td>	
	</tr>
	<s:if test="issueAttachFiles!=null && issueAttachFiles.size > 0">
	<tr>
	 <td class="title">附件</td>
	 <td class="content" colspan="3">
		 <s:iterator value="issueAttachFiles">
			<a href="/issues/issueManage/downLoadAttachFile.action?keyId=<s:property value="id"/>" >
				<img  src="${resource_path}/resource/images/fujian.jpg"/><s:property value="fileName"/>
			</a>
		</s:iterator>
	 </td>
	</tr>
	</s:if>

</table>
<script type="text/javascript">
$(function(){
	var typeDesc="";
	$(":input[id^=issueTypeSelector]").each(function(index,value){
		typeDesc=typeDesc+"<strong>"+$("[for="+value.id+"]").first().html()+"</strong>:"+$.trim($(value).getTypeSelectLabels())+"<br>";
	});
	$("#issueTypeDescription").html(typeDesc);
});
	
</script>

