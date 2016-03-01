<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script>
	$("#portlet-task-publicNotice").find("li").hover(function(){
		$(this).css("background","#f2f2f2");
		$(this).find(".lookNew").addClass("btnHover")	
	},function(){
		$(this).css("background","#fff"); 
		$(this).find(".lookNew").removeClass("btnHover")	
	})
</script>
<s:action name="getMyAttention" var="getMyAttention"
	namespace="/workBence/tableConfigManage" executeResult="false"></s:action>
<div id="right">
<s:if test="'super'.equals(#getMyAttention.personalWorkBenchType)">
	<div class="portlet-notice portlet">
		<div class="portlet-header">
			<h3>待办提醒</h3>
		</div>
		<div class="portlet-cont">
			<pop:JugePermissionTag ename="needIssueListManagement,checkPendingIssueListManagement,verificationIssueListManagement,checkGradeIssueListManagement">
				<div class="taskevent tasklist issue" id="issueNeedOfSuper"></div>
			</pop:JugePermissionTag>
			<div class="posa noIssueOfSuper" id="noIssueOfSuper" style="">
				<div class="warnspace">暂无权限</div>
			</div>
			
			<pop:JugePermissionTag ename="pmInboxManagement">
			<div class="platinfor tasklist pmInbox">
				<s:action namespace="/interactive/inboxPlatformMessageManage"
					name="findUnreadInboxPlatformMessage" var="getMessageOfUnread"
					executeResult="false" ignoreContextParams="true">
					<s:param name="rows">5</s:param>
					<s:param name="sidx">id</s:param>
					<s:param name="sord">desc</s:param>
					<s:param name="source">super</s:param>
				</s:action>
				<div class="task_title">
					平台消息（<span class="num"> <c:choose>
							<c:when test="${getMessageOfUnread.gridPage.records >0}">
							${getMessageOfUnread.gridPage.records}
						</c:when>
							<c:otherwise>
						0
						</c:otherwise>
						</c:choose>
					</span>）
				</div>
				<c:choose>
					<c:when test="${not empty getMessageOfUnread.gridPage.rows}">
						<ul id="portlet-task-publicNotice">
							<c:forEach items="${getMessageOfUnread.gridPage.rows}"
								var="messageOfUnread">
								<li
									style="background-color: rgb(255, 255, 255); background-position: initial initial; background-repeat: initial initial;"
									class="isNew messageOfUnread">
									<span class="balckdot"></span> 
									<c:choose>
										<c:when test="${fn:length(messageOfUnread.title) > 18}">
											<c:out
												value="${fn:substring(messageOfUnread.title, 0, 18)}......" />
										</c:when>
										<c:otherwise>
											<c:out value="${messageOfUnread.title}" />
										</c:otherwise>
									</c:choose>
								</li>
							</c:forEach>
						</ul>
					</c:when>
					<c:otherwise>
						<div class="posa">
							<div class="warnspace">暂无平台消息</div>
						</div>
					</c:otherwise>
				</c:choose>
				<div class="showMore cf">
					<a href="javascript:;" id="interactionManagement">更多&gt;&gt;</a>
				</div>
			</div>
			</pop:JugePermissionTag>
			<div class="posa noIssueOfSuper" id="noMessageOfSuper" style="">
				<div class="warnspace">暂无权限</div>
			</div>
		</div>
	</div>
</s:if>
<s:if
	test="'lower'.equals(#getMyAttention.personalWorkBenchType) || 'middle'.equals(#getMyAttention.personalWorkBenchType)">
	<div class="portlet-notice portlet">
		<div class="portlet-header">
			<h3>通知通报</h3>
		</div>
		<pop:JugePermissionTag ename="publicNoticeManagement">
		<s:action namespace="/sysadmin/publicNoticeManage"
			name="publicNoticeOfBenchList" var="getPublicNotice"
			executeResult="false" ignoreContextParams="true">
			<s:param name="rows">5</s:param>
			<s:param name="sord">desc</s:param>
			<s:param name="sidx">editorDate</s:param>
		</s:action>
		<div class="portlet-cont publicNotice">
			<div class="noticeList">
				<div class="stick">
					<c:choose>
						<c:when
							test="${not empty getPublicNotice.publicNoticeBenchVo && not empty getPublicNotice.publicNoticeBenchVo.publicNotice && not empty getPublicNotice.publicNoticeBenchVo.publicNotice.id}">
							<a href="javascript:;" class="title"
								title="${getPublicNotice.publicNoticeBenchVo.publicNotice.publicNoticeTitle}">
								<c:choose>
									<c:when
										test="${fn:length(getPublicNotice.publicNoticeBenchVo.publicNotice.publicNoticeTitle) > 14}">
										<c:out
											value="${fn:substring(getPublicNotice.publicNoticeBenchVo.publicNotice.publicNoticeTitle, 0, 14)}" />
									</c:when>
									<c:otherwise>
										<c:out
											value="${getPublicNotice.publicNoticeBenchVo.publicNotice.publicNoticeTitle}" />
									</c:otherwise>
								</c:choose> </a>
							<p class="abstract">
								<c:choose>
									<c:when
										test="${fn:length(getPublicNotice.publicNoticeBenchVo.publicNotice.publicNoticeMatter) > 28}">
										<c:out
											value="${fn:substring(getPublicNotice.publicNoticeBenchVo.publicNotice.publicNoticeMatter, 0, 28)}......" />
									</c:when>
									<c:otherwise>
										<c:out
											value="${getPublicNotice.publicNoticeBenchVo.publicNotice.publicNoticeMatter}" />
									</c:otherwise>
								</c:choose>
								<a href="javascript:;"
									onclick="showPublicNoticeRecord(${getPublicNotice.publicNoticeBenchVo.publicNotice.id})"
									class="readmore">点击查看&gt;&gt;</a>
							</p>
						</c:when>
						<c:otherwise>
							<div class="warnspace">暂无通知通告</div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="scrollNews">
					<c:choose>
						<c:when
							test="${not empty getPublicNotice.publicNoticeBenchVo.publicNoticeList && fn:length(getPublicNotice.publicNoticeBenchVo.publicNoticeList)>0 }">
							<ul style="margin-top: 0px;" id="portlet-task-publicNotice">
								<c:forEach items="${getPublicNotice.publicNoticeBenchVo.publicNoticeList}" var="publicNotice">
								<li style="background-color: rgb(255, 255, 255); background-position: initial initial; background-repeat: initial initial;"
									onclick="showPublicNoticeRecord(${publicNotice.id})" title="${publicNotice.publicNoticeTitle}">
									<span>
									<c:choose>
										<c:when test="${fn:length(publicNotice.publicNoticeTitle) > 15}">
										<c:out value="${fn:substring(publicNotice.publicNoticeTitle, 0, 15)}" />
										</c:when>
										<c:otherwise>
											<c:out value="${publicNotice.publicNoticeTitle}" />
										</c:otherwise>
									</c:choose>
									</span>
									<span class="date"><fmt:formatDate value="${publicNotice.editorDate}" type="date"/></span>
								</li>
								</c:forEach>
							</ul>
					   </c:when>
						<c:otherwise>
							<div class="warnspace">暂无未读通告</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
		</pop:JugePermissionTag>
		<div class="posa noIssueOfSuper" id="noMessageOfSuper" style="">
			<div class="warnspace">暂无权限</div>
		</div>
	</div>
	<div class="portlet-sign portlet">
		<div class="portlet-header">
			<h3>登录统计</h3>
		</div>
		<s:action namespace="/sysadmin/workCalendarManger"
			name="findLoginOfCurrentWeek" var="loginOfWeek" executeResult="false"
			ignoreContextParams="true">
		</s:action>
		<div class="portlet-cont">
			<c:forEach items="${loginOfWeek.loginOfCurrentWeek}" var="row">
				<div
					<c:choose>
						<c:when test="${row.holiday == 1||row.holiday == 7 }">class="saturday weekly cf"</c:when>
						<c:otherwise>class="monday weekly cf"</c:otherwise></c:choose>>
					<span class="week">${row.weekday}</span> 
					<span <c:choose><c:when test="${row.isLogin == 0}">class="sign nosign fr"</c:when>
							<c:when test="${row.isLogin == 1}">class="sign fr"</c:when><c:otherwise>class="fr"</c:otherwise></c:choose>>
					</span>
				</div>
			</c:forEach>
		</div>
	</div>
	<div id="publicNoticeRecordDlg"></div>
	<s:bean name="java.util.Date" var="date" />
</s:if>
</div>
<script>
	var dialogWidth = 500;
	var dialogHeight = 300;
	function showPublicNoticeRecord(publicNoticeId) {
		$("#publicNoticeRecordDlg")
				.createDialog(
						{
							width : dialogWidth,
							width : 800,
							height : 500,
							title : "查看通知通报信息",
							url : "${path}/sysadmin/publicNoticeManage/dispatch.action?mode=readReceive&publicNotice.id="
									+ publicNoticeId,
							buttons : {
								"关闭" : function() {
									$(this).dialog("close");
								}
							}
						});
	}
	
	
	$(".portlet-cont").on("click",".issueOfNeed",function(){		
		$("#serviceWorkMenu").attr("href","/module.jsp#serviceWork");
		/*
		$(".ui-layout-center").css({
			width:document.documentElement.clientWidth-$(".ui-layout-west").width()-10
		})
		*/
		$(".ui-layout-west").show().load("/issue/issueLeftMenuList.jsp",function(){
			$("#thisCrumbs,.slideResizer").show();
		});
	});
	$(".portlet-cont").on("click","#serviceWorkMenu",function(){		
		$("#serviceWorkMenu").attr("href","/module.jsp#serviceWork");
		/*
		$(".ui-layout-center").css({
			width:document.documentElement.clientWidth-$(".ui-layout-west").width()-10
		})
		*/
		$(".ui-layout-west").show().load("/issue/issueLeftMenuList.jsp",function(){
			$("#thisCrumbs,.slideResizer").show();
		});
	});
	
	$(".messageOfUnread").click(function(){
		$("#interactionManagement").attr("href","/module.jsp#interactionManagement");
		/*
		$(".ui-layout-center").css({
			width:document.documentElement.clientWidth-$(".ui-layout-west").width()-10
		})
		*/
		$(".ui-layout-west").show().load("/sysadmin/menuManage/getLeftMenuListToJson.action?ename=interactionManagement",function(){
			$("#thisCrumbs,.slideResizer").show();
		});
	});
	$("#interactionManagement").click(function(){
		$("#interactionManagement").attr("href","/module.jsp#interactionManagement");
		/*
		$(".ui-layout-center").css({
			width:document.documentElement.clientWidth-$(".ui-layout-west").width()-10
		})
		*/
		$(".ui-layout-west").show().load("/sysadmin/menuManage/getLeftMenuListToJson.action?ename=interactionManagement",function(){
			$("#thisCrumbs,.slideResizer").show();
		});
	});
	
	function findIssueNeedDoOfSuper(){
		$.ajax({
			url:'${path}/issues/issueManage/findIssueNeedDo.action',
			data:{
				"keyId":<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"/>,
				"viewType":"need",
				"rows":5,
				"leaderView":1,
				"sidx":"issueId",
				"sord":"desc",
				"source":"super"
			},
			success:function(data){
				$("#issueNeedOfSuper").html(data);
			}
		});
	}
	
	$(document).ready(function(){
		findIssueNeedDoOfSuper();
		
		function shortcutList(){
			$('#right .issue').each(function(i,d){
				$(this).next().hide();
			})
			
			$('#right .publicNotice').each(function(i,d){
				$(this).next().hide();
			})
			
			$('#right .pmInbox').each(function(i,d){
				$(this).next().hide();
			})
			/*
			if($('.tasklist').find('li').length <= 0){
				$(".noIssueOfSuper").show();
			}
			*/
		}
		shortcutList();
	});
</script>