<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.tianque.core.util.ThreadVariable"%>
<%
	Long orgid = ThreadVariable.getUser().getOrganization().getId();
	request.setAttribute("orgid", orgid);
%>
<script>
	$("#portlet-task-issue").find("li").hover(function(){
		$(this).css("background","#f2f2f2");
		$(this).find(".lookNew").addClass("btnHover")	
	},function(){
		$(this).css("background","#fff");
		$(this).find(".lookNew").removeClass("btnHover")	
	})
</script>
<div class="task_title">
	待办事项（ <span class="num"> <c:choose>
			<c:when test="${gridPage.records >0}">
						${gridPage.records}
					</c:when>
			<c:otherwise>
					0
					</c:otherwise>
		</c:choose> </span>）
</div>
<c:choose>
	<c:when test="${gridPage.records >0}">
		<ul id="portlet-task-issue">
			<c:forEach items="${gridPage.rows}" var="issueOfNeed">
				<li class="cf"
					style="background-color: rgb(255, 255, 255); background-position: initial initial; background-repeat: initial initial;">
					<span class="balckdot"></span> <a href="javascript:;"
					onclick="showIssueMyNeed(${issueOfNeed.issueId})" class="isNew"><span>
							<c:choose>
								<c:when test="${fn:length(issueOfNeed.subject) >35}">
									<c:out
										value="${fn:substring(issueOfNeed.subject, 0, 35)}......" />
								</c:when>
								<c:otherwise>
									<c:out value="${issueOfNeed.subject}" />
								</c:otherwise>
							</c:choose> </span> </a> <a href="javascript:;" class="lookNew" id="deal"
					issueId="${issueOfNeed.issueId}"
					ref="${issueOfNeed.encryptIdByIssueStepId}"
					sef="${issueOfNeed.encryptIdByIssueId}" target="_parent"><span>马上处理</span>
				</a>
					<div class="lookhidearea">
						<c:choose>
							<c:when test="${issueOfNeed.dealState==110}">
								<pop:JugePermissionTag ename="conceptNeedIssue">
									<a href="javascript:;" class="normalDeal"
										onclick="simpleDealIssue(${issueOfNeed.issueStepId},<s:property value="@com.tianque.issue.state.IssueOperate@CONCEPT.code"/>)">受理</a>
								</pop:JugePermissionTag>
								<pop:JugePermissionTag ename="conceptNeedIssue" hasPermission="true">
									<a href="javascript:;"><span>暂无权限</span>	</a>
								</pop:JugePermissionTag>
							</c:when>
							<c:when
								test="${issueOfNeed.dealState==120 && issueOfNeed.delayState==1 &&issueOfNeed.targeOrg.id != orgid}">
								<pop:JugePermissionTag ename="auditDelayCheckPendingIssue">
									<a href="javascript:;" class="normalDeal"
										onclick="auditDelay(${issueOfNeed.issueStepId})">审核</a>
								</pop:JugePermissionTag>
								<pop:JugePermissionTag ename="auditDelayCheckPendingIssue" hasPermission="true">
									<a href="javascript:;"><span>暂无权限</span>	</a>
								</pop:JugePermissionTag>
							</c:when>
							<c:when test="${issueOfNeed.dealState==140}">
								<pop:JugePermissionTag ename="readNeedIssue">
									<a href="javascript:;" class="normalDeal"
										onclick="readIssue(${issueOfNeed.issueId},${issueOfNeed.issueStepId},<s:property value="@com.tianque.issue.state.IssueOperate@READ.code"/>)">阅读</a>
								</pop:JugePermissionTag>
								<pop:JugePermissionTag ename="readNeedIssue" hasPermission="true">
									<a href="javascript:;"><span>暂无权限</span>	</a>
								</pop:JugePermissionTag>
							</c:when>
							<c:when test="${issueOfNeed.dealState==800}">
								<pop:JugePermissionTag ename="evaluateVerificationIssue">
									<a href="javascript:;" class="normalDeal verify"
										ref="${issueOfNeed.encryptIdByIssueStepId}"
										sef="${issueOfNeed.encryptIdByIssueId}">验证</a>
								</pop:JugePermissionTag>
								<pop:JugePermissionTag ename="evaluateVerificationIssue" hasPermission="true">
									<a href="javascript:;"><span>暂无权限</span>	</a>
								</pop:JugePermissionTag>
							</c:when>
							<c:when test="${issueOfNeed.dealState==1000}">
								<pop:JugePermissionTag ename="gradeCheckGradeIssue">
									<a href="javascript:;" class="normalDeal grade"
										sef="${issueOfNeed.encryptIdByIssueId}">评分</a>
								</pop:JugePermissionTag>
								<pop:JugePermissionTag ename="gradeCheckGradeIssue" hasPermission="true">
									<a href="javascript:;"><span>暂无权限</span>	</a>
								</pop:JugePermissionTag>
							</c:when>
							<c:otherwise>
								<pop:JugePermissionTag ename="dealNeedIssue">
								<s:action namespace="/issues/issueManage"
									name="dispatchUrgentLevelOfWorkBench" var="getUrgentLevels"
									executeResult="false" ignoreContextParams="true">
									<s:param name="keyId">${issueOfNeed.encryptIdByIssueStepId}</s:param>
								</s:action>
									<a href="javascript:;" class="normalDeal"
										ref="${issueOfNeed.encryptIdByIssueStepId}"
										sef="${issueOfNeed.encryptIdByIssueId}">办理</a>
									<c:if test="${fn:length(getUrgentLevels.urgentLevels)>0}">
										<a href="javascript:;" class="overDeal">越级办理</a>
										<div class="hidelevarea">
											<c:forEach items="${getUrgentLevels.urgentLevels}"
												var="urgentLevel">
												<a href="javascript:;" ski="false" skId="${urgentLevel.id}"
													ref="${issueOfNeed.encryptIdByIssueStepId}"
													sef="${issueOfNeed.encryptIdByIssueId}">${urgentLevel.displayName}</a>
											</c:forEach>
										</div>
									</c:if>
								</pop:JugePermissionTag>
								<pop:JugePermissionTag ename="dealNeedIssue" hasPermission="true">
									<a href="javascript:;"><span>暂无权限</span>	</a>
								</pop:JugePermissionTag>
							</c:otherwise>
						</c:choose>

					</div> <span class="date"><fmt:formatDate
							value="${issueOfNeed.dealTime}" type="both" /> </span>
				</li>
			</c:forEach>
		</ul>
		<div class="showMore cf">
			<pop:JugePermissionTag ename="serviceWork">
				<a href="javascript:;" id="serviceWorkMenu">更多&gt;&gt;</a>
			</pop:JugePermissionTag>
		</div>
	</c:when>
	<c:otherwise>
		<div class="posa">
			<div class="warnspace">暂无待办事件</div>
		</div>
	</c:otherwise>
</c:choose> 
	<script type='text/javascript'>
$(document).ready(function(){
	
	$('.showMore a').click(function(){
		$(this).closest('.workbenchMain').hide();
		$('.ui-layout-west').empty();
	})
	 $('.lookNew').click(function(){
		$(this).next().show();	
		$(this).hide();
		 $(this).next().find('a').unbind('click').bind("click",function(){ 
			var that = $(this)
			dealIssue(that)
			$(this).parent().prev().show();
			$(this).parent().hide();
		}) 
	}) 
	 $('.overDeal').mouseover(function(){
		 $(this).next().show();
		 
		 $(this).next().find('a').unbind('click').bind("click",function(){ 
			var that = $(this);
			$.ajax({
				url:'${path}/issues/issueManage/updateIssueStepUrgentLevelForCache.action',
				data:{
					"issueStep.id":that.attr("ref"),
					"issueStep.emergencyLevel.id":that.attr("skId")
				},
				success:function(data){
					if("true"==data||true==data){
						that.attr("ski",true);
						dealIssue(that);
					}	
				}
			});
			
			$(this).parent().parent().prev().show();
			$(this).parent().parent().hide()
		 })
		 $(this).next().mouseleave(function(){
			 $(this).hide();
			 $(this).parent().hide();
			 $(this).parent().prev().show();
		 })
	 }) 
	 $('.lookhidearea').mouseleave(function(){
		 $(this).hide();
		 $(this).prev().show();
	 }) 
	 
	 //验证
	$('.lookNew').on('click','.verify',function(){
		dealIssue($(this));
	});
	 
	 //评分
		$('.lookhidearea').on('click','.grade',function(){
			grade($(this).attr("sef"));
		});
	 
	$("#serviceWorkMenu").click(function(){
		$("#serviceWorkMenu").attr("href","/module.jsp#serviceWork");
		$(".ui-layout-center").css({
			width:document.documentElement.clientWidth-$(".ui-layout-west").width()-10
		})
		$(".ui-layout-west").show().load("/issue/issueLeftMenuList.jsp",function(){
			$("#thisCrumbs,.slideResizer").show();
		});
	});
	
});
</script>