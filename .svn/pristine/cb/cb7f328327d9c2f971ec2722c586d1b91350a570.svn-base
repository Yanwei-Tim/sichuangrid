<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.issueCttLeft{width:50%;float:left;}
.issueCttRight{width:49%;float:right;background:none;border:0;}
.processRecord{margin-top:5px;font:bold 12px/20px "";}
h3.processRecord{color:#000;font-weight:bold!important;}
.issueContentInfo{padding:0;border:0;}
.recordContent,.tableBox,.issuePjCtt{background:#fff;border:1px solid #ccc;border-top:0;}
.recordContent{position:relative;}
.issuePjCtt{padding:10px;}
.tableBox{padding:10px;}
.star{display:inline-block;*display:inline;*zoom:1;vertical-align:middle;height:23px;background:url(/resource/system/images/incident/star.png) no-repeat;}
.star.star1{width:20px;}
.star.star2{width:40px;}
.star.star3{width:60px;}
.star.star4{width:80px;}
.star.star5{width:100px;}
.issueC{font:12px/20px "";}
.issueRight .smallText{padding-left:10px;}
.issueRight .recordList .recordData{height:auto;overflow:hidden;}
.issueRight .recordData .place{width: 360px;padding: 0;}
.issueRight .recordData .handle{position:static;float:right;width: 92px;line-height: 27px;padding-right: 0;}
.issueTitle .pmState, .state, .issueRight .recordData .time{background:none;padding-left:0;}
.viewImage {
background: url(/resource/system/images/issue/images_dw_search.gif) no-repeat;
display: inline-block;
width: 72px;
height: 20px;
vertical-align: middle;
}
.ui-state-disabled, .ui-widget-content .ui-state-disabled, .ui-widget-header .ui-state-disabled{
	opacity: 1;
	filter: Alpha(Opacity=100);
	background-image: none;
}
.newTableList{border-top:1px solid #ccc;border-right: 1px solid #ccc;background:#EFEFF0;width:100%;}
.newTableList td{height:24px;line-height:24px;padding:0 0 0 10px;border:1px solid #ccc;border-top:none;border-right:none;border-collapse:collapse;word-break:break-all; word-warp:break-word;}
.newTableList .title{width:16%;font:bold 12px/20px ""; color:#000; padding:0 0 0 5px;}
.newTableList .text{width:25%;}
.newTableList .contable{width:43%;}
</style>
 
<ul style="display:none;" id="replysTable">
		<s:iterator status="index" value="#request.replys" >
			<li id="2012-12-11">
				<dl class="recordData clearfix">
					<dd class="time"><fmt:formatDate value="${replyDate}" type="date" pattern="yy年MM月dd日" /></dd>
					<dd class="place">
					    【<s:property value="sourceOrg.orgName"/>】&nbsp;&nbsp;&nbsp;&nbsp;
					 <s:property value="name"/> 回复
					 </br>
					 <a class='replyFormExport' name='replyForm' data='${id}'>下载回复单</a>
					 <a class='clickAuto'  dataType='回复单'><span  id='_r${id}' class="awindowopen" ></span></a>
					</dd>
				</dl>
				<c:if test="${(handleContent != null && not empty handleContent)}">
	                <div class="smallText" style="word-wrap:break-word;overflow:hidden;">
							<span>回复内容摘要:</span><span>${handleContent }</span>
					</div>
				</c:if>
				 <c:if test="${fn:length(attachFiles) > 0}">
                   	<div class="smallText" id="gallery<s:property value="id"/>">
						<span class="filetype-clip"></span>附件：
						<c:forEach items="${attachFiles}" var="issueAttachFile">
						<!--<c:choose>
						    <c:when test="${fn:endsWith(fn:toLowerCase(issueAttachFile.fileActualUrl),'.jpg')||fn:endsWith(fn:toLowerCase(issueAttachFile.fileActualUrl),'.jpge')||fn:endsWith(fn:toLowerCase(issueAttachFile.fileActualUrl),'.png')||fn:endsWith(fn:toLowerCase(issueAttachFile.fileActualUrl),'.bmp') ||fn:endsWith(fn:toLowerCase(issueAttachFile.fileActualUrl),'.gif')}">
						     	<a data-gallery="gallery"  class="view" href="${issueAttachFile.fileActualUrl }" title="${issueAttachFile.fileName}">${issueAttachFile.fileName}</a>;
                        		<div class="clear"></div>
						    </c:when>
						    <c:otherwise>
						    	<a href="${path }/threeRecordsIssues/issueManage/downLoadAttachFile.action?keyId=${issueAttachFile.id}" >${issueAttachFile.fileName}</a>;
						    </c:otherwise>
						</c:choose>-->
						<a href="${path }/threeRecordsIssues/issueManage/downLoadAttachFile.action?keyId=${issueAttachFile.id}" >${issueAttachFile.fileName}</a>;
                        </c:forEach>
                      	</div>
		           </c:if>
			</li>
		</s:iterator>
</ul>
<script type="text/javascript">
$(".replyFormExport").each(function(){
		$(this).click(function(){
			var id = $(this).attr('data');
			var actionName=$(this).attr('name');
			$.ajax({
				url: "${path}/threeRecords/"+actionName+"/dispatch.action?mode=print",
				data:{
					"id":id
				},
				success:function(responseData){
					$('#_r'+id).html(responseData);
					$('#_r'+id).hide();
					$('#_r'+id).trigger("click");
				}
			});
		})
	});
</script>
