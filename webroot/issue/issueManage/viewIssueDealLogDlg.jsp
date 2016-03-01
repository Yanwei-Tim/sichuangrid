<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="viewRecordContent">
	<ul>
		<c:choose>
		    <c:when test="${issueDealLogs!=null&&fn:length(issueDealLogs)>0}">
			<s:subset source="issueDealLogs" start="0">
				<s:iterator status="index">
					<li id="2012-12-11">
						<dl class="clearfix">
							<dd class="time"><s:date name="dealTime" format="yyyy年MM月dd HH:mm:ss" /></dd>
							<dd class="place">
							<strong>
								<c:if test="${dealOrg.orgName == '中国' || dealUserName=='超级管理员' || dealUserName == 'admin' }">系统消息</c:if>
								<c:if test="${dealOrg.orgName != '中国' && dealUserName!='超级管理员' && dealUserName != 'admin' }">${dealOrg.orgName}&nbsp;&nbsp;${dealUserName }</c:if>
							</strong>
							<span><s:property value="dealDescription"/></span></dd>
						</dl>
						<c:if test="${not empty fourTeamsName}">
							<dd><strong>办理队伍：</strong><span >${fourTeamsName }</span>&nbsp;&nbsp;</dd>
						</c:if>
						<c:if test="${(content != null && not empty content) || (issueLogAttachFilesMap[id] != null && fn:length(issueLogAttachFilesMap[id])>0 )}">
	                        <div class="smallText">
									<strong>办理意见:</strong><span>${content }</span>
							</div>
	                        <c:if test="${issueLogAttachFiles[id]!=null && fn:length(issueLogAttachFiles[id]) > 0}">
		                    	<div class="smallText">
									<span class="filetype-clip"></span>附件：
								<c:forEach items="${issueLogAttachFiles[id]}" var="issueLogAttachFiles">
		                            <a href="${path }/issues/issueManage/downLoadAttachFile.action?keyId=${issueLogAttachFiles.id}" >${issueLogAttachFiles.fileName}</a>;
		                        </c:forEach>
	                        	</div>
	                  		</c:if>
	                  	</c:if>
					</li>
				</s:iterator>
			</s:subset>
		    </c:when>
		    <c:otherwise>
		    	<li>
					<dl class="recordData clearfix">
						<dd class="place">该部门还未处理该事件</dd>
					</dl>
				</li>
		    </c:otherwise>
		</c:choose>
	</ul>
</div>

<script type="text/javascript">
/*
$(document).ready(function(){
	<s:if test="issueEvaluate.id!=null">
		$('#starView').raty({
		 	readOnly:true,  
			start:"${issueEvaluate.evaluateType}"
		 });
	</s:if>
	bindOperationAction();
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

	renderIssueType();

	function bindOperationAction(){
		$("#readTop,#readBottom").click(function(event){
			simpleDealIssue(${keyId},<s:property value="@com.tianque.issue.state.IssueOperate@READ.code"/>);
		});
		$("#conceptTop,#conceptBottom").click(function(event){
			simpleDealIssue(${keyId},<s:property value="@com.tianque.issue.state.IssueOperate@CONCEPT.code"/>);
		});
		$("#dealTop,#dealBottom").click(function(event){
			dealIssue(${keyId});
		});
		$("#reportToTop,#reportToBottom").click(function(event){
			simpleDealIssue(${keyId},<s:property value="@com.tianque.issue.state.IssueOperate@REPORT_TO.code"/>);
		});
	}


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
	setUiVisiable($("#readTop"),visiabled);
	setUiVisiable($("#readBottom"),visiabled);
}

function setConcepteOperateVisiabled(visiabled){
	setUiVisiable($("#conceptTop"),visiabled);
	setUiVisiable($("#conceptBottom"),visiabled);
}

function setDealOperateVisiabled(visiabled){
	setUiVisiable($("#dealTop"),visiabled);
	setUiVisiable($("#dealBottom"),visiabled);
}

function setReportToOperateVisiabled(visiabled){
	setUiVisiable($("#reportToTop"),visiabled);
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
    	typeDescs[actualIndex]=typeDescs[actualIndex]+'<s:property value="#type.issueTypeName" escape="false"/>'+',';
	</s:iterator>
	
	for (var index=0;index<typeDescs.length;index++){
		if (index>0){
			typeDesc=typeDesc+'<br>';
		}
		typeDesc=typeDesc+typeDescs[index];	
	}
	$("#issueTypesLabel").html(typeDesc);
}

*/
</script>