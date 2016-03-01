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
							<dd class="time"><s:date name="operateTime" format="yyyy年MM月dd日" /></dd>
							<dd class="place" style="word-wrap:break-word;">
							<strong>
								<c:if test="${dealOrg.orgName == '中国' || dealUserName=='超级管理员' || dealUserName == 'admin' }">系统消息</c:if>
								<c:if test="${dealOrg.orgName != '中国' && dealUserName!='超级管理员' && dealUserName != 'admin' }">${dealOrg.orgName}&nbsp;&nbsp;${dealUserName }</c:if>
							</strong>
							<span><s:property value="dealDescription"/></span></dd>
						</dl>
						<c:if test="${(content != null && not empty content) || (issueLogAttachFilesMap[id] != null && fn:length(issueLogAttachFilesMap[id])>0 )}">
	                        <div class="smallText">
									<strong>工作措施:</strong><span>${content }</span>
							</div>
	                        <c:if test="${issueLogAttachFiles[id]!=null && fn:length(issueLogAttachFiles[id]) > 0}">
		                    	<div class="smallText">
									<span class="filetype-clip"></span>附件：
								<c:forEach items="${issueLogAttachFiles[id]}" var="issueLogAttachFiles">
		                            <a href="${path }/threeRecordsIssues/issueManage/downLoadAttachFile.action?keyId=${issueLogAttachFiles.id}" >${issueLogAttachFiles.fileName}</a>;
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
