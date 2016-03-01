<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<div class="issueTitle">
	<dl>
		<dt class="ch"><input type="checkbox" id="checkedAll"/></dt>
		<dt class="title"><label>&nbsp;	标题</label></dt>
		<c:choose>
		<c:when test="${'draft'==mode}">
		<dt class="state pmState"><a href="javascript:;">保存时间</a></dt>
		</c:when>
		<c:otherwise>
		<dt class="state pmState"><a href="javascript:;">发送时间</a></dt>
		</c:otherwise>
		</c:choose>
	</dl>
</div>
<div class="issueList">
	<ul>
		<c:forEach  items="${gridPage.rows }" var="pm">
			<li class="issue" id='${pm.id}' >
				<div class="title clearfix">
					<div class="choiceCurC">
						<div>
							<input class="box" type="checkbox" name="pm.id" value="${pm.id }"/>
							<c:if test="${pm.pmAttachFiles!=null && fn:length(pm.pmAttachFiles)>0 }">
								<span class="handle fujian"></span>
							</c:if>
						</div>
					</div>
					<div class="issueTitle1" style="height: 50px;">
						<div class="title">
							<a href="javascript:;">${pm.title}</a>
						</div>
						<div>
							<div class="showTime showReceiverNames">${pm.sender.name}</div>
							<div class="show sendDate">
								<fmt:formatDate value="${pm.sendDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss" />
							</div>
						</div>
					</div>

				</div>
				
				<span class="arrow"></span>
			</li>
		</c:forEach>
	</ul>
 	<input id="records" type="hidden" value="<s:property value='#request.gridPage.records'/>">
 	<input id="page" type="hidden" value="<s:property value='#request.gridPage.page'/>">
 	<input id="total" type="hidden" value="<s:property value='#request.gridPage.total'/>">
</div>
<script type="text/javascript">
var mode ="${mode}";
	$(function(){
		issueRightHeight();
	})
	$(document).ready(function() {
		$(".box").click(function(){
			var checkNum = 0;
			$(".box").each(function(){
				if($(this).attr("checked")=="checked"){
					checkNum=(checkNum-0)+1;
				}
			});
			if(checkNum>1){
				if(mode=="draft"){
					$("#draftboxPlatformMessageDetail").hide();
				}else{
					$("#platformMessageDetail").hide();
				}
			}else{
				if(mode=="draft"){
					$("#draftboxPlatformMessageDetail").show();
				}else{
					$("#platformMessageDetail").show();
				}
				
			}
		});
		
		$("#checkedAll").click(function(){
			if($("#checkedAll").attr("checked")){
				$(".box").attr("checked",true);
				if(mode=="draft"){
					$("#draftboxPlatformMessageDetail").hide();
				}else{
					$("#platformMessageDetail").hide();
				}
			}else{
				$(".box").attr("checked",false);
				$(".issueList li.current .box").attr("checked",true);
				if(mode=="draft"){
					$("#draftboxPlatformMessageDetail").show();
				}else{
					$("#platformMessageDetail").show();
				}
			}
		});
		
		
	});
	
</script>