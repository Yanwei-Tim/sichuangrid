<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<jsp:include page="/includes/baseInclude.jsp" />
<%
	request.setAttribute("baseInfoType",com.tianque.partyBuilding.baseInfos.constant.BaseInfoType.ORGANSPARTY);
%>
<div class="gridDiv" style="overflow:hidden;overflow-y:auto;position:relative;">
	<pop:JugePermissionTag ename="updateOrgansPartyMembers">
	<input type="hidden" value="true" id="updatePartyWorkMembersP"/>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="deleteOrgansPartyMembers">
	<input type="hidden" value="true" id="deletePartyWorkMembersP"/>
	</pop:JugePermissionTag>	
	<pop:JugePermissionTag ename="sortOrgansPartyMembers">
	<input type="hidden" value="true" id="sortPartyWorkMembersP"/>
	</pop:JugePermissionTag>
	
	<div class="mapinfo">
		<div class="map">
			<pop:JugePermissionTag ename="editOrgansPartyCase">
				<div class="mapEdit">
					<a href="javascript:;" id="zoomin"><span class="exitButton"></span>编辑</a>
					<input type="hidden" value="" id="basicCaseId"/>
				</div>
			</pop:JugePermissionTag>
			
			<div id="mapSlideBox" class="mapSlideBox">
					<div class="hd">
						<ul><li></li><li></li><li></li><li></li><li></li></ul>
					</div>
					<div class="bd">
						<ul>
							<li><a href="javascript:;"><img src="${resource_path}/resource/images/nopic.jpg" id="image_0" /></a></li>
							<li><a href="javascript:;"><img src="${resource_path}/resource/images/nopic.jpg" id="image_1" /></a></li>
							<li><a href="javascript:;"><img src="${resource_path}/resource/images/nopic.jpg" id="image_2" /></a></li>
							<li><a href="javascript:;"><img src="${resource_path}/resource/images/nopic.jpg" id="image_3" /></a></li>
							<li><a href="javascript:;"><img src="${resource_path}/resource/images/nopic.jpg" id="image_4" /></a></li>
						</ul>
					</div>
				</div>	
		</div>
	</div>
	<div class="gridPanel villagePanel">
		<h1 class="infonav-title"><span class="case_title"></span>机关党建情况
			<pop:JugePermissionTag ename="editOrgansPartyCase">
				<div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="editBasicCase"><span>编辑</span></a></div>
			</pop:JugePermissionTag>
		</h1>
		<div class="accdContent ui-widget-border">
			 <p class="natureGeography" id="maintainBaseCase_p" style="word-break: break-all;"></p>
		</div>
	</div>
	<div class="clear"></div>
	<div id="bs-accordion">
		<div>
			<h1 class="infonav-title" id="leaderGroupHeader"><span class="member_title">党工委成员</span>
				<pop:JugePermissionTag ename="addOrgansPartyMembers">
					<div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="add"><span>新增</span></a></div>
				</pop:JugePermissionTag>
			</h1>
			<div class="accdContent ui-widget-border">
				<ul class="leaderGroup" id="leaderGroup">
				</ul>
			</div>
		</div>
	</div>
</div>
<div id="basicCaseDialog"></div>
<div id="basicCasePhotosDialog"></div>
<div id="partyworkMembersDialog"></div>
<jsp:include page="${path}/partyBuilding/baseInfos/basicCaseListFunc.jsp">
	<jsp:param name="baseInfoType" value="${baseInfoType}" />
</jsp:include>