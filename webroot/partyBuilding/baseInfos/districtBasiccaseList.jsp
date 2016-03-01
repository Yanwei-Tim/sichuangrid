<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/PopGrid-taglib" prefix="pop" %>
<%@ include file="/includes/baseInclude.jsp"%>
<style>
	.accordionPanel{float:left;width:49%;margin:10px 10px 0 0;}
	.accordionPanel .accdContent{padding:10px;}
	.accordionPanelBox{overflow:hidden;}
	.accordionPanelBox .accordionPanelInnerBox{width:110%;}
	.accdContent p{word-break: break-all;}
	
</style>
<div class="gridDiv" style="overflow:hidden;overflow-y:auto;position:relative;">
	<div class="gridOrgSelect">
		<div class="btnbanner">
			<jsp:include page="${path}/partyBuilding/baseInfos/orgSelectBasicCase.jsp?plateName=party"/>
		</div>
	</div>	
	
	<div class="mapinfo">
		<div class="map">
		
			<pop:JugePermissionTag ename="editCase">
				<div class="mapEdit">
					<a href="javascript:;" id="zoomin"><span class="exitButton"></span>编辑</a>
					<input type="hidden" value="" id="caseId"/>
<%-- 					<a href="javascript:;" id="zoomDelete"><span class="deleteButton"></span>清除</a> --%>
				</div>
			</pop:JugePermissionTag>
			 
			<div id="mapSlideBox" class="mapSlideBox">
				<div class="hd">
					<ul><li></li><li></li><li></li><li></li><li></li></ul>
				</div>
				<div class="bd">
					<ul class="case_images">
						<li><a href="javascript:;"><img src="${resource_path}/resource/images/nopic.jpg" id="image_0" /></a></li>
						<li><a href="javascript:;"><img src="${resource_path}/resource/images/nopic.jpg" id="image_1" /></a></li>
						<li><a href="javascript:;"><img src="${resource_path}/resource/images/nopic.jpg" id="image_2"/></a></li>
						<li><a href="javascript:;"><img src="${resource_path}/resource/images/nopic.jpg" id="image_3"/></a></li>
						<li><a href="javascript:;"><img src="${resource_path}/resource/images/nopic.jpg" id="image_4"/></a></li>
					</ul>
					
				</div>
			</div>
		</div>
	</div>
	<div class="gridPanel villagePanel">
		<h1 class="infonav-title">全区党组织及党员情况<pop:JugePermissionTag ename="editCase"><div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="editPartyOrgsAndMembersCase"><span>编辑</span></a></div></pop:JugePermissionTag></h1>
		<div class="accdContent ui-widget-border">
			 <p class="natureGeography" id="maintainPartyOrgsAndMembers_p"></p>
		</div>
	</div>
	<div class="clear"></div>
	<div class="accordionPanelBox">
		<div class="accordionPanelInnerBox">
			<div class="accordionPanel">
				<h1 class="infonav-title">机关党建情况<pop:JugePermissionTag ename="editCase"><div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="editAdministrativeCases"><span>编辑</span></a></div></pop:JugePermissionTag></h1>
				<div class="accdContent ui-widget-border">
					<p class="administrativeCases" id="administrativeCases_p"></p>
				</div>
			</div>
			<div class="accordionPanel">
				<h1 class="infonav-title">街道党建情况<pop:JugePermissionTag ename="editCase"><div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="editStreetPartySituation"><span>编辑</span></a></div></pop:JugePermissionTag></h1>
				<div class="accdContent ui-widget-border">
					<p class="streetPartySituation" id="streetPartySituation_p"></p>
				</div>
			</div>
			<div class="accordionPanel">
				<h1 class="infonav-title">系统党建情况<pop:JugePermissionTag ename="editCase"><div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="editSystemConstruction"><span>编辑</span></a></div></pop:JugePermissionTag></h1>
				<div class="accdContent ui-widget-border">
					<p class="systemConstruction" id="systemConstruction_p"></p>
				</div>
			</div>
			<div class="accordionPanel">
				<h1 class="infonav-title">区域党建情况<pop:JugePermissionTag ename="editCase"><div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="editRegionalPartySituation"><span>编辑</span></a></div></pop:JugePermissionTag></h1>
				<div class="accdContent ui-widget-border">
					<p class="regionalPartySituation" id="regionalPartySituation_p"></p>
				</div>
			</div>
			<div class="accordionPanel">
				<h1 class="infonav-title">志愿者情况<pop:JugePermissionTag ename="editCase"><div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="editVolunteersSituation"><span>编辑</span></a></div></pop:JugePermissionTag></h1>
				<div class="accdContent ui-widget-border">
					<p class="volunteersSituation" id="volunteersSituation_p"></p>
				</div>
			</div>
			<div class="accordionPanel">
				<h1 class="infonav-title">双报到情况<pop:JugePermissionTag ename="editCase"><div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="eidtDoubleRegistrationSituation"><span>编辑</span></a></div></pop:JugePermissionTag></h1>
				<div class="accdContent ui-widget-border">
					<p class="doubleRegistrationSituation" id="doubleRegistrationSituation_p"></p>
				</div>
			</div>
		</div>
	</div>
	<!-- 
	<div id="bs-accordion">
		<div>
			<h1 class="infonav-title">辖区领导班子介绍<pop:JugePermissionTag ename="addLeaderTeam"><div class="leaderGroup-buttons"><a href="javascript:;" class="base-button" id="add"><span>编辑</span></a></div></pop:JugePermissionTag></h1>
			<div class="accdContent ui-widget-border">
				<ul class="leaderGroup" id="leaderGroup">
				
				</ul>
			</div>
		</div>
	</div>
	 -->
</div>
<div id="districtBasicCaseDialog"></div>
<div id="photosDialog"></div>
<script type="text/javascript">
$(".accordionPanel").width($(".ui-layout-center").width()/2-8);
$("#mapSlideBox").slide({mainCell:".bd ul",autoPlay:false,effect:"left"});
var positionPanel=function(){
	var portletNum=parseInt((document.documentElement.clientWidth-260)/350);
	var sortTableWidth=(document.documentElement.clientWidth-260)/portletNum;
	var sortTable=$("#leaderGroup li");
	sortTable.each(function(i){
		$(this).width(sortTableWidth-15);
	});
	$("#leaderGroup").height("auto").height($("#leaderGroup").height());
}
function callback(){
    var dfop = {
    	path:'${path}'
    }
    TQ.districtBasiccaseList(dfop);
    
}

$.cacheScript({
    url:'/resource/getScript/partyBuilding/baseInfos/districtBasiccaseList.js',
    callback: callback
})
</script>