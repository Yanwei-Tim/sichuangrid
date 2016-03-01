<%@	page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
#pager{position:relative;}
</style>
<div class="issueTitle">
	<dl>
	    <dt class="ch"><input type="checkbox" id="checkedAll" onclick="checkSystemAll()"/></dt>
		<dt class="title"><label>&nbsp;标题</label></dt>
		<dt class="state pmState"><a href="javascript:;">阅读状态</a></dt>
	</dl>
</div>
<div class="issueList" id="systemIssueList">
	<ul>
		<c:forEach  items="${gridPage.rows }" var="pm">
			<li class="issue" id='${pm.id}'  sendtype='${pm.sendType}'>
				<div class="title clearfix">
					<div class="choiceCurC">
						<label>
						<input class="box" type="checkbox" name="pm.id" value="${pm.id }"/>
							<c:if test="${pm.pmAttachFiles!=null&&fn:length(pm.pmAttachFiles)>0}">
								<span class="handle fujian"></span>
							</c:if>
						</label>
					</div>
					<div class="issueTitle1" style="height: 50px;">
					<a href="javascript:;">${pm.title}</a>
					<div class="show pmSendState"><a href="javascript:;" class="handleLink">${pm.readState}</a></div>
					</div>
				</div>
				<div>
					<div class="showTime"><c:choose><c:when test="${pm.sender.userName=='admin' }">&nbsp;系统消息&nbsp;</c:when><c:otherwise>&nbsp;${pm.sender.name}&nbsp;</c:otherwise></c:choose></div>
					<div class="show sendDate"><fmt:formatDate value="${pm.sendDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss" /></div>
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
$(document).ready(function() {
	$(".box").click(function(){
		var checkNum = 0;
		$(".box").each(function(){
			if($(this).attr("checked")=="checked"){
				checkNum=(checkNum-0)+1;
			}
		});
		if(checkNum>1){
			$("#inboxPlatformMessageDetail").hide();
		}else{
			$("#inboxPlatformMessageDetail").show();
		}
	});
});

$(function(){
	issueRightHeight();
	formatPmReadState();
	$(".issueProgram_Tab_show").css({
		height:$("#issHeight").height()-$("#pager").height()
	})
	$(window).resize(function(){
		$(".issueLeft").css({
			height:$("#issHeight").height()-$("#pager").height()
		})
	})
});
function formatPmReadState(){
	$(".issueList li a.handleLink").each(function(){
		if($(this).html()==1){
			$(this).html("已阅读");
		}else if($(this).html()==0){
			$(this).html("未阅读");
			$(this).closest("li").addClass("unreadTip");
		}
	});	
}

function checkSystemAll(){
		if($("#checkedAll").attr("checked")){
			$(".box").attr("checked",true);
			$("#inboxPlatformMessageDetail").hide();
		}else{
			$(".box").attr("checked",false);
			$(".issueList li.current .box").attr("checked",true);
			$("#inboxPlatformMessageDetail").show();
		}
}

 
</script>