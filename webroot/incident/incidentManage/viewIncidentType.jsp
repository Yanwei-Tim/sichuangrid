<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<jsp:include page="/includes/baseInclude.jsp" />

	<div class="system_event cf"><strong class="eventDefine">事件名称:</strong><span id="viewTypeName"></span></div>
	<div class="system_event cf"><strong class="eventDefine">事件定义:</strong><span id="viewTypeDescription"></span></div>
	<div class="system_event cf"><strong class="eventDefine">是否分级:</strong>&nbsp;&nbsp;<input type="checkbox" name="incidentType.degreed" id="viewTypeDegreed" value="true" <s:if test='true==incidentType.degreed'>checked="checked"</s:if> disabled="disabled" /></div>
	
	
	<div class="system_baseGrade">
		<h3 class="baseGradeTitle">分级依据:</h3>
		<div class="baseGradeMain">
			<div class="gradeS">
				<h3 class="title title_I">I 级(特别重大)<span class="Gradestar Gradestar_I"></span>${incidentDealPlan.endRule}</h3>
				<div class="content">
					<p class="text"><span id="viewTyperule_0"></span></p>
				</div>
			</div>
			<div class="gradeS">
				<h3 class="title title_II">II 级(特别重大)<span class="Gradestar Gradestar_II"></span>${incidentDealTeam.composition}</h3>
				<div class="content">
					<p class="text"><span id="viewTyperule_1"></span></p>
				</div>
			</div>
			<div class="gradeS">
				<h3 class="title title_III">III 级(特别重大)<span class="Gradestar Gradestar_III"></span>${incidentDealPlan.endRule}</h3>
				<div class="content">
					<p class="text"><span id="viewTyperule_2"></span></p>
				</div>
			</div>
			<div class="gradeS">
				<h3 class="title title_V">IV 级(特别重大)<span class="Gradestar Gradestar_V"></span>${incidentDealTeam.composition}</h3>
				<div class="content" >
				<p class="text"><span id="viewTyperule_3"></span></p>
				</div>
			</div>
		</div>
	</div>
	
<script type="text/javascript">

	/*$(function (){

		var processHeight=function(){
			var timer;
			function getHeight(){
				var bodyHeight=document.documentElement.clientHeight;
				var mainManagementHeight=$(".system_management").height(bodyHeight-215);
				var mainHeight=$(".system_baseGrade .baseGradeMain").height(bodyHeight-345);
			}
			getHeight();
			$(window).resize(function(){
				clearTimeout(timer);
				timer=setTimeout(function(){getHeight()},1);
			})
		}()
		})*/

</script>

