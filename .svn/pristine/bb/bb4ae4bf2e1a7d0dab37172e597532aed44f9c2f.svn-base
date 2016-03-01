<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%> 

	<div id ="caselibraryStepsFeedbacks"  class="processBox">
		 <s:iterator  value="incidentStepList" var="incidentStep" status="st" >
			 <div class="content clearfix">
				 <s:set name="planContent" value="#incidentStep.planContent" scope="request"/>
				 <s:set name="executeDate" value="#incidentStep.executeDate" scope="request"/>
				<div class="minHeight detailC">
					<strong class="Content_title"> 处置详情:</strong>
					<div class="Content_title_feedBack" >
						<span id="viewStepsDate"> <s:date name="executeDate" format="yyyy-MM-dd HH:mm:ss" /></span>
						<span id="viewStepsName"> ${planContent}</span>
					</div>
				</div>
						<s:iterator value="#incidentStep.incidentStepFeedBackList" var ="incidentFeedBack">
							 <s:set name="feedBackDate" value="#incidentFeedBack.feedBackDate" scope="request"/>
							 <s:set name="feedBackContent" value="#incidentFeedBack.content" scope="request"/>
							 
				<div class="minHeight callbackC">
				<strong class="Content_text">反馈详情:</strong>
					<div class="Content_title_feedBack" >
						<span id="viewFeedbacksDate"><s:date name="feedBackDate" format="yyyy-MM-dd HH:mm:ss" /></span>
						<span id="viewFeedbacksName"> ${feedBackContent}</span>
					</div>
				</div></s:iterator>
			</div>
		</s:iterator>
	</div>
	<script>
		function processListHeight(){
			var timer;
			function Height(){
				var $height=document.documentElement.clientHeight||document.body.clientHeight;
				$(".processBox").height($height-310);
			}
			Height()
			$(window).resize(function(){
				clearTimeout(timer);
				timer=setInterval(function(){Height()},10);
			})
		}
		processListHeight();
	</script>
