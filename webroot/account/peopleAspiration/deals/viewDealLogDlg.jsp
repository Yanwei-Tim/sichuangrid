<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
.issueRight .smallText{padding-left:10px;font:bold 25px/27px ""; color:#000;}
.issueRight .recordList .recordData{height:auto;overflow:hidden;}
.issueRight .recordData .place{width: 360px;padding: 0;color:#5F5F5F; font-weight: normal;}
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

<div class="issueRight issueCttRight" style="overflow:hidden;overflow-y:auto;">
	<div class="dealRecord recordList clearfix">
		<h3 class="processRecord">处理记录
			<div class="processBtnList">
				<a href="javascript:;" class="cur" id="textView"><span class="text"></span>文字视图</a>
				<a href="javascript:;" id="chartView"><span class="chart"></span>图表视图</a>
				<a href="javascript:;" id=feedbacks><span class="text"></span>反馈列表</a>
			</div>
		</h3>
			<div class="recordContent clearfix">
				<ul style="display:none;" id="issueTable">
					<s:set name="issueLogAttachFiles" value="#request.peopleAspirations.issueLogAttachFiles"></s:set>
					<s:set name="issueLogAttachFilesMap" value="#request.peopleAspirations.issueLogAttachFilesMap"></s:set>
					<s:subset source="peopleAspirations.issueDealLogs" start="0">
						<s:iterator status="index" >
							<li id="2012-12-11">
								<dl class="recordData clearfix">
									<c:if test="${dealType == 5}"><img src='${resource_path }/resource/system/images/issue/icon_yLampForList.png'></img></c:if>
									<dd class="time"><fmt:formatDate value="${operateTime}" type="date" pattern="yy年MM月dd日" /></dd>
									<dd class="place" style="word-wrap:break-word;width:350px">
									<c:choose>
									    <c:when test="${dealUserName == 'admin' || dealUserName =='超级管理员'}">
									     	【系统消息】
									    </c:when>
									    <c:otherwise>
									    	【<s:property value="dealOrg.orgName"/>】&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="dealUserName"/>
									    </c:otherwise>
									</c:choose>
									<s:property value="dealDescription"/>
									</br>
									<s:if test="dealType == @com.tianque.plugin.account.state.ThreeRecordsIssueOperate@SUBMIT.code">
										<a class='reportFormExport' name='reportForm' data='${issueStep.id}'>下载呈报单</a>
										<a class='clickAuto'  dataType='呈报单'><span  id='_${issueStep.id}' class="awindowopen" ></span></a>
									</s:if>
									<s:elseif test="dealType == @com.tianque.plugin.account.state.ThreeRecordsIssueOperate@TURN.code">
										<a class='reportFormExport' name='turnForm' data='${issueStep.id}'>下载转办单</a>
										<a class='clickAuto'  dataType='转办单'><span  id='_${issueStep.id}' class="awindowopen" ></span></a>
									</s:elseif>
									<s:elseif test="dealType == @com.tianque.plugin.account.state.ThreeRecordsIssueOperate@DECLARE.code">
										<a class='reportFormExport' name='declareForm' data='${issueStep.id}'>下载申报单</a>
										<a class='clickAuto'  dataType='申报单'><span  id='_${issueStep.id}' class="awindowopen" ></span></a>
									</s:elseif>
									<s:elseif test="dealType == @com.tianque.plugin.account.state.ThreeRecordsIssueOperate@ASSIGN.code">
										<c:if test="${fn:endsWith(dealOrg.departmentNo,'1xw')||fn:endsWith(dealOrg.departmentNo,'1lx')}">
											<a class='reportFormExport' name='assignForm' data='${issueStep.id}'>下载交办单</a>
											<a class='clickAuto'  dataType='交办单'><span  id='_${issueStep.id}' class="awindowopen" ></span></a>
										</c:if>	
										
									</s:elseif>
									</dd>
								</dl>
								
								<c:if test="${(content != null && not empty content) || (issueLogAttachFilesMap[id] != null && fn:length(issueLogAttachFilesMap[id])>0 )}">
			                        <div class="smallText" style="word-wrap:break-word;overflow:hidden;">
											<span>工作措施:</span><span>${content }</span>
									</div>
			                        <c:if test="${issueLogAttachFiles[id]!=null && fn:length(issueLogAttachFiles[id]) > 0}">
				                    	<div class="smallText" id="gallery<s:property value="id"/>">
											<span class="filetype-clip"></span>附件：
											<c:forEach items="${issueLogAttachFiles[id]}" var="issueAttachFile">
											<c:choose>
											    <c:when test="${fn:endsWith(fn:toLowerCase(issueAttachFile.fileActualUrl),'.jpg')||fn:endsWith(fn:toLowerCase(issueAttachFile.fileActualUrl),'.jpge')||fn:endsWith(fn:toLowerCase(issueAttachFile.fileActualUrl),'.png')||fn:endsWith(fn:toLowerCase(issueAttachFile.fileActualUrl),'.bmp') ||fn:endsWith(fn:toLowerCase(issueAttachFile.fileActualUrl),'.gif')}">
											     	<a data-gallery="gallery"  class="view" href="${issueAttachFile.fileActualUrl }" title="${issueAttachFile.fileName}">${issueAttachFile.fileName}</a>;
					                        		<div class="clear"></div>
											    </c:when>
											    <c:otherwise>
											    	<a href="${path }/threeRecordsIssues/issueManage/downLoadAttachFile.action?keyId=${issueAttachFile.id}" >${issueAttachFile.fileName}</a>;
											    </c:otherwise>
											</c:choose>
					                        </c:forEach>
			                        	</div>
		                   			</c:if>
	                    		</c:if>
	                    		
							</li>
						</s:iterator>
					</s:subset>
			
				</ul>
			<div id="issueGraph" style="padding-top:45px;overflow: auto;width: 405px;height: 320px;">
			    <s:include value="/account/issueMap/raphael.jsp" ></s:include>
		    </div>
		    <s:set name="feedbacks" value="#request.peopleAspirations.feedbacks"></s:set> 
		    <s:include value="/account/peopleAspiration/viewFeedBacksDlg.jsp" ></s:include>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		$(".clickAuto").each(function(){
			var dataType=$(this).attr('dataType');
			$(this).click(function () {
				var result = "data:application/vnd.ms-excel,"+encodeURIComponent($(this).html() );
				this.href = result; this.download = dataType+".xls";
				return true;
			});
		})
		$("#issueTable").show();
		$("#issueGraph").hide();
		$(".processBtnList a").click(function(){
			$(this).addClass("cur").siblings().removeClass("cur");
		});
		$("#chartView").click(function(){
			$("#issueGraph").show();
			$("#issueTable").hide();
			$("#feedbackTable").hide();
		});
		$("#feedbacks").click(function(){
			$("#issueGraph").hide();
			$("#issueTable").hide();
			$("#feedbackTable").show();
			
		});
		
		$("#textView").click(function(){
			$("#issueTable").show();
			$("#issueGraph").hide();
			$("#feedbackTable").hide();
		});
		$('div .show').each(function(){
			if($(this).attr("id")){
			$(this).raty({
				readOnly:	true,
				start:		$(this).attr("id")
		   });
			}
		});
		$('#gallery').imagegallery({
	        show: "random",
	        zIndex:99999,
	        close:function(){
				$('#gallery').imagegallery('disable');
	        }
	    });
		$('#gallery').imagegallery('disable');
	    $("#viewImages").click(function(){
	    	$('#gallery').imagegallery('enable');
	    	if($('#gallery').find(".view").eq(0).html() == null) {
	    		$('#gallery').find("a:first")[0].click();
	    	} else {
	    		$('#gallery').find(".view").eq(0).click();
	    	}
	    });
	    
	});

</script>