<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="topLoop" id="informLoop">
<div class="bd">
	<ul>
		<c:forEach items="${notices }" var="obj">
			<li>
				<span>
				(<fmt:formatDate value="${obj.editorDate}" type="date" pattern="yyyy-MM-dd" />)</span>
				<a href="javascript:void(0)" onclick="dlgOpen(${obj.id})" title="${obj.publicNoticeTitle}">
					${obj.publicNoticeTitle}
					<c:if test='${obj.isAttachment }'><img src="${resource_path}/resource/images/fujian.jpg" style="" />
					</c:if>
				</a>
			</li>
		</c:forEach>
	</ul>
</div>
</div>
<div id="noticeDlg"></div>
<script type="text/javascript">
$("#informLoop").slide({
	mainCell:".bd ul",
	effect:"topLoop",
	autoPlay:true,
	vis:1,
	scroll:1,
	trigger:"click"
});
function dlgOpen(id) {
	
	$("#noticeDlg").createDialog({
		width:800,
		height:480,
		title:"查看通知通告信息",
		url:"${path}/workBench/noticeManage/dispatchOperate.action?publicNotice.id="+id,
		buttons: {
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});

}
</script>