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
 
<ul style="display:none;" id="feedbackTable">
		<s:iterator status="index" value="#request.feedbacks" >
			<li id="2012-12-11">
				<dl class="recordData clearfix">
					<dd class="time">${index.index+1}、<fmt:formatDate value="${createDate}" type="date" pattern="yy年MM月dd日   HH:mm" /></dd>
					<dd class="place">
					    【<s:property value="evaluateUser"/>】&nbsp;&nbsp;&nbsp;&nbsp;
					  <s:if test="feedBackType==@com.tianque.plugin.account.constants.CompleteType@TYPE_VERBAL">口头</s:if> 
					  <s:if test="feedBackType==@com.tianque.plugin.account.constants.CompleteType@TYPE_PHONE">电话</s:if> 
					  <s:if test="feedBackType==@com.tianque.plugin.account.constants.CompleteType@TYPE_WRITTEN">书面</s:if> 
					  &nbsp;&nbsp;&nbsp;&nbsp;
					  <s:if test="feedBackOpinion==@com.tianque.plugin.account.constants.CompleteType@OPINION_SATISFACTION">满意</s:if>
					  <s:if test="feedBackOpinion==@com.tianque.plugin.account.constants.CompleteType@OPINION_RUDIMENTARY_SATISFACTION">基本满意</s:if>
					  <s:if test="feedBackOpinion==@com.tianque.plugin.account.constants.CompleteType@OPINION_NOT_SATISFACTION">不满意</s:if> 
					  <s:if test="feedBackOpinion==@com.tianque.plugin.account.constants.CompleteType@OPINION_OTHER">其他</s:if>
					</dd>
				</dl>
				<c:if test="${(remark != null && not empty remark)}">
	                      <div class="smallText" style="word-wrap:break-word;overflow:hidden;">
							<span>备注:</span><span>${remark }</span>
					</div>
				</c:if>
			</li>
		</s:iterator>
</ul>
