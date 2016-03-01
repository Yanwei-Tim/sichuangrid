<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>

<div class="issueTitle">
	<dl>
		<dt class="title"><label><input type="checkbox"/>&nbsp;事项名称</label></dt>
		<dt class="state"><a href="javascript:;">状态</a></dt>
	</dl>
</div>
<div class="issueList">
	<ul>
	<s:iterator value="#request.gridPage.rows">
		<li class="issue" id='${id }' approvalNumber='${approvalNumber }'>
			<div class="title clearfix">
				<div class="choiceCurC">
					<label><input type="checkbox"/>&nbsp;
							<span class="handle handleE"></span>
					</label>
				</div>
				<a href="javascript:;">${itemName }</a>
			</div>
			<div>
				<div class="showTime">
					${createDate }
				</div>
				<div class="show">
					<a href="javascript:;" class="handleLink">
						<s:if test="status == @com.tianque.approval.domain.property.ApprovalItemStatus@IN_PROCESS">
							办理中
						</s:if>
						<s:elseif test="status == @com.tianque.approval.domain.property.ApprovalItemStatus@PENDING_REVIEW">
							待审核
						</s:elseif>
						<s:elseif test="status == @com.tianque.approval.domain.property.ApprovalItemStatus@AUDIT_DOES_NOT_PASS">
							审核未通过
						</s:elseif>
						<s:elseif test="status == @com.tianque.approval.domain.property.ApprovalItemStatus@HAVE_GONE_THROUGH">
							已办结
						</s:elseif>
					</a>
				</div>
			</div>
		</li>
	</s:iterator>
	</ul>
	<input id="records" type="hidden" value="<s:property value='#request.gridPage.records'/>">	
</div>

<script>
	$(function(){
		issueRightHeight();
	})
</script>