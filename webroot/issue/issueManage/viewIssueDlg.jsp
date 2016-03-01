<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- issue/issueManage/viewIssueDlg.jsp -->
<table class="tablelist">
   <tr style="display: none;">
   		<td colspan="4">
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
   		</td>
	</tr>
	<tr>
		<!--  <td class="title"><label>服务单号：</label></td>
		<td class="content"><span>${issue.serialNumber}&nbsp;</span></td>
		-->
		<td class="title"><label>事件名称：</label></td>
		<td class="content" colspan="3" ><span>${issue.subject}&nbsp;</span></td>
	</tr>
	<tr>
		<td class="title"><label>来源人：</label></td>
		<td class="content"><span>${issue.sourcePerson}</span></td>
		<td class="title"><label>来源方式：</label></td>
		<td class="content"><span>${issue.sourceKind}</span></td>
	</tr>
	<tr>
		<td class="title"><label>来源人手机号：</label></td>
		<td class="content"><span>${issue.sourceMobile}</span></td>
		<td class="title"><label>发生网格：</label></td>
		<td class="content" >${issue.occurOrg.orgName}</td>
	</tr>
	<tr>
		<td class="title"><label>发生地点：</label></td>
		<td class="content"><span>${issue.occurLocation}&nbsp;</span></td>
		<td class="title"><label>发生时间：</label></td>
		<td class="content"><span>${issue.occurDateString}&nbsp;</span></td>
	</tr>
	<tr>	
		<td class="title"><label>是否重大事件：</label></td>
		<td class="content"><c:choose><c:when test="${issue.important == true }">是</c:when><c:otherwise>否</c:otherwise></c:choose></td>	
		<td class="title"><label>是否紧急事件：</label></td>
		<td class="content"><c:choose><c:when test="${issue.isEmergency == true }">是</c:when><c:otherwise>否</c:otherwise></c:choose></td>
	</tr>
	<tr>
		<td class="title"><label>事件规模：</label></td>
		<td class="content"><pop:PropertyDictViewTag name="@com.tianque.domain.property.PropertyTypes@ISSUE_KIND" defaultValue="${issue.issueKind.id}" />&nbsp;</td>
		<td class="title"><label>涉及人数：</label></td>
		<td class="content">${issue.relatePeopleCount}&nbsp;</td>
	</tr>	
	<tr>
		<td class="title"><label>主要当事人：</label></td>
		<td class="content" colspan="3">${issue.mainCharacters}&nbsp;</td>
	</tr>
	<tr>
		<td class="title"><label>特殊人群：</label></td>
		<td class="content" colspan="3">
			<s:iterator id="person" value="relatePersonMap">
	    		<s:iterator value="#person.value" status="s">
	    			<s:property value='name'/>； 
	   			</s:iterator>
    		</s:iterator>
		</td>
	</tr>
	<tr>
		<td class="title"><label>事件类型：</label></td>
		<c:choose>
		    <c:when test="${issue.issueType==true}">
		     	<td class="content" colspan="3" >服务审批&nbsp;</td>
		    </c:when>
		    <c:otherwise>
		    	<td class="content" colspan="3" id="issueTypeDescription">&nbsp;</td>
		    </c:otherwise>
		</c:choose>
	</tr>
	<tr>
		<td class="title">事件简述：</td>
		<td  colspan="3" class="content">${issue.issueContent}&nbsp;</td>	
	</tr>
		<tr>
		<td class="title">处理记录：</td>
		
<div class="dealRecord recordList clearfix">
		<div class="recordContent clearfix">
			<ul style="display:block;" id="issueTable">
				<s:subset source="issueDealLogs" start="0">
				<td colspan="3"  >
				<div style="height: 70px;border: 1px;overflow-x:hidden;overflow-y:auto;">
				<div style="width: 90%">
					<s:iterator status="index">
						<li id="2012-12-11">
								<label ><s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" /></label>
								<label ><s:property value="dealOrg.orgName"/>&nbsp;&nbsp;&nbsp;&nbsp;<c:choose><c:when test="${dealUserName == 'admin' }">系统消息</c:when><c:otherwise><s:property value="dealUserName"/></c:otherwise></c:choose></label>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<label ><s:property value="dealDescription"/></label>
							<c:if test="${(content != null && not empty content) || (issueLogAttachFilesMap[id] != null && fn:length(issueLogAttachFilesMap[id])>0 )}">
		                        <div class="smallText" style="word-wrap:break-word;overflow:hidden;">
										<span>办理意见:</span><span>${content}</span>
								</div>
		                        <c:if test="${issueLogAttachFiles[id]!=null && fn:length(issueLogAttachFiles[id]) > 0}">
			                    	<div class="smallText">
										<span class="filetype-clip"></span>附件
										<c:forEach items="${issueLogAttachFiles[id]}" var="issueLogAttachFiles">
				                            <a href="${path }/issues/issueManage/downLoadAttachFile.action?keyId=${issueLogAttachFiles.id}" >${issueLogAttachFiles.fileName }</a>;
				                        </c:forEach>
		                        	</div>
	                   			</c:if>
                    		</c:if>
                    		
						</li>
					</s:iterator>
					</div>
				</div>	
					</td>
				</s:subset>
			</ul>
		</div>
</div>
	
	</tr>
	<c:if test="${issueAttachFiles!=null && fn:length(issueAttachFiles) > 0}">
	<tr>
	 <td class="title">附件</td>
	 <td class="content" colspan="3">
	 	<c:forEach items="${issueAttachFiles}" var="issueAttachFile">
            <a href="/issues/issueManage/downLoadAttachFile.action?keyId=${issueAttachFile.id}" ><img  src="${resource_path}/resource/images/fujian.jpg"/>${issueAttachFile.fileName }</a>
            <div class="clear"></div>
        </c:forEach>
	 </td>
	</tr>
	</c:if>

</table>
<script type="text/javascript">
$(function(){
	//显示所有事件类型。
// 	var typeDesc="";
// 	$(":input[id^=issueTypeSelector]").each(function(index,value){
// 		typeDesc=typeDesc+"<strong>"+$("[for="+value.id+"]").first().html()+"</strong>:"+$.trim($(value).getTypeSelectLabels())+"<br>";
// 	});
// 	$("#issueTypeDescription").html(typeDesc);
});
$(document).ready(function(){
	<c:if test="${issueEvaluate.id!=null}">
		$('#starView').raty({
		 	readOnly:true,
			start:"${issueEvaluate.evaluateType}"
		 });
	</c:if>
	renderIssueType();
	setReadOperateVisiabled(false);
	setConcepteOperateVisiabled(false);
	setDealOperateVisiabled(false);
	setReportToOperateVisiabled(false);
	
	<s:if test="@com.tianque.issue.state.IssueOperate@COMPLETE in canDoList">
		setDealOperateVisiabled(true);
	</s:if>
    <s:elseif test="@com.tianque.issue.state.IssueOperate@ASSIGN in canDoList">
		setDealOperateVisiabled(true);
	</s:elseif>
    <s:elseif test="@com.tianque.issue.state.IssueOperate@GIVETO in canDoList">
		setDealOperateVisiabled(true);
	</s:elseif>
    <s:elseif test="@com.tianque.issue.state.IssueOperate@COMMENT in canDoList">
		setDealOperateVisiabled(true);
	</s:elseif>
    <s:elseif test="@com.tianque.issue.state.IssueOperate@SUBMIT in canDoList">
		setDealOperateVisiabled(true);
	</s:elseif>
	<s:elseif test="@com.tianque.issue.state.IssueOperate@BACK in canDoList">
		setDealOperateVisiabled(true);
	</s:elseif>

	<s:if test="@com.tianque.issue.state.IssueOperate@REPORT_TO in canDoList">
		setReportToOperateVisiabled(true);
	</s:if>
	<s:if test="@com.tianque.issue.state.IssueOperate@CONCEPT in canDoList ">
		setConcepteOperateVisiabled(true);
	</s:if>
	<s:if test="@com.tianque.issue.state.IssueOperate@READ in canDoList">
		setReadOperateVisiabled(true);
	</s:if>

	
	$("#issueTable").show();
	$("#issueGraph").hide();

	


	$(".processBtnList a").click(function(){
		$(this).addClass("cur").siblings().removeClass("cur");
	});
	$("#chartView").click(function(){
		$("#issueGraph").show();
		$("#issueTable").hide();
	});
	$("#textView").click(function(){
		$("#issueTable").show();
		$("#issueGraph").hide();
	});
});

function setUiVisiable(ui,visiable){
	if (visiable){
		ui.show();
	}else{
		ui.hide();
	}
}

function setReadOperateVisiabled(visiabled){
	setUiVisiable($("#readBottom"),visiabled);
}

function setConcepteOperateVisiabled(visiabled){
	setUiVisiable($("#conceptBottom"),visiabled);
}

function setDealOperateVisiabled(visiabled){
	setUiVisiable($("#dealBottom"),visiabled);
}

function setReportToOperateVisiabled(visiabled){
	setUiVisiable($("#reportToBottom"),visiabled);
}

function setGiveToOperateVisiabled(visiabled){
	setUiVisiable($("#giveToTop"),visiabled);
	setUiVisiable($("#giveToBottom"),visiabled);
}


function getTypeDescById(indexs,id){
	for (var index=0;index<indexs.length;index++){
		if (indexs[index]==id) return index;
	}
	return indexs.length;
}


function renderIssueType(){
	var typeDescs=new Array();
	var descIndexs=new Array();
	var typeDesc='';
	var actualIndex;
    <s:iterator value="issue.issueTypes" var="type" >
	    actualIndex=getTypeDescById(descIndexs,<s:property value="#type.issueTypeDomain.id"/>);
    	if (actualIndex==typeDescs.length){
    		descIndexs[actualIndex]=<s:property value="#type.issueTypeDomain.id"/>;
    		typeDescs[actualIndex]='<s:property value="#type.issueTypeDomain.domainName" escape="false"/>'+':';
        }
    	typeDescs[actualIndex]=typeDescs[actualIndex]+"<s:property value="#type.issueTypeName+','" escape="false"/>";
	</s:iterator>

	for (var index=0;index<typeDescs.length;index++){
		if (index>0){
			typeDesc=typeDesc+'<br>';
		}
		typeDesc=typeDesc+typeDescs[index];
	}
	$("#issueTypeDescription").html(typeDesc);
}

</script>

