<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<ul class="tabnav">
	<pop:JugePermissionTag ename="orgPerformanceAppraisal">
		<li><span>部门绩效考核</span></li>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="issueStatusStatistics">
		<li><span>事件处理状态统计</span></li>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="issueTypeStatistics">
		<li><span>事件处理类别统计</span></li>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="contradictionInvestigation">
        <li><span>矛盾纠纷排查调处表</span></li>
    </pop:JugePermissionTag>
	<pop:JugePermissionTag ename="baseDataStatistics">
        <li><span>基础数据研判分析</span></li>
    </pop:JugePermissionTag>
	<pop:JugePermissionTag ename="superviseIssueList">
        <li><span>督办列表</span></li>
    </pop:JugePermissionTag>
</ul>
<div class="tabbox">
    <pop:JugePermissionTag ename="orgPerformanceAppraisal">
        <ul class="subnavbar">
            <pop:JugePermissionTag ename="statAdministrativeRegradedPoint">
			     <li><a href="javascript:void(0)"
				    onclick="asyncMiddleLevelOpen(this,'${path}/statAnalyse/statRegradedPointManage/dispatch.action?internalId=<s:property value="@com.tianque.domain.property.OrganizationType@ADMINISTRATIVE_REGION"/>')"><span>行政部门绩效考核</span></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="statFunctionalRegradedPoint">
			     <li><a href="javascript:void(0)"
                    onclick="asyncMiddleLevelOpen(this,'${path}/statAnalyse/statRegradedPointManage/dispatch.action?internalId=<s:property value="@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG"/>')"><span>职能部门绩效考核</span></a></li>
            </pop:JugePermissionTag>
	   </ul> 
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="peopleInformation">
        <ul class="subnavbar">
            <pop:JugePermissionTag ename="issueStatusStat">
			     <li><a href="javascript:void(0)"
				    onclick="asyncMiddleLevelOpen(this,'${path}/stat/statAnalyseIssueStatueManage/dispatch.action?mode=initDate')"><span>事件处理状态统计</span></a></li>
	        </pop:JugePermissionTag>
	    </ul> 
	</pop:JugePermissionTag>
    <pop:JugePermissionTag ename="locationInformation">
        <ul class="subnavbar">
            <pop:JugePermissionTag ename="issueTypeStat">
			     <li><a href="javascript:void(0)"
				    onclick="asyncMiddleLevelOpen(this,'${path}/issueTypeStanal/issueTypeStanalManage/dispatch.action?mode=initDate')"><span>事件处理类别统计</span></a></li>
	        </pop:JugePermissionTag>
	    </ul> 
	</pop:JugePermissionTag>
    <pop:JugePermissionTag ename="contradictionInvestigation">
        <ul class="subnavbar">
            <pop:JugePermissionTag ename="issueInvestigationMediate">
			     <li><a href="javascript:void(0)"
				    onclick="asyncMiddleLevelOpen(this,'${path}/issueInvestigationMediate/issueInvestigationMediateManage/dispatch.action','normalState')"><span>矛盾纠纷排查调处表</span></a></li>
	        </pop:JugePermissionTag>
	    </ul> 
    </pop:JugePermissionTag>
    <pop:JugePermissionTag ename="baseDataStatistics">
        <ul class="subnavbar">
            <pop:JugePermissionTag ename="importantPersonelStat">
			     <li><a href="javascript:void(0)"
				    onclick="asyncMiddleLevelOpen(this,'${path}/baseInfoStatistics/dispatch.action?mode=personnel')"><span>特殊人群</span></a></li>
			</pop:JugePermissionTag>
            <pop:JugePermissionTag ename="importantLocaltionStat">
			     <li><a href="javascript:void(0)"
				    onclick="asyncMiddleLevelOpen(this,'${path}/baseInfoStatistics/dispatch.action?mode=place')"><span>重点场所</span></a></li>
	        </pop:JugePermissionTag>
	    </ul> 
    </pop:JugePermissionTag>
    <pop:JugePermissionTag ename="superviseIssueList">
        <ul class="subnavbar">
			<li><a href="javascript:void(0)"
				onclick="asyncMiddleLevelOpen(this,'${path}/issue/issueManage/dispatch.action?mode=superviseList')"><span>督办列表</span></a></li>
	    </ul> 
	</pop:JugePermissionTag>
</div>
<script>

	$(function() {
		var _box=$('.tabbox ul');
		var _hover='hover';
		var objwidth=0;
		function showBoxMenu(i,n){
			objwidth=objwidth+$(n).innerWidth();
			if(objwidth>screen.width-265){
				$(n).after('<li class="more"><img src="'+RESOURCE_PATH+'/resource/system/images/popmenu2.gif" /></li>');
				$(".popMenu").attr("showMenu","false");
				$(".more").toggle(
						function () {
							$(this).html('<img src="'+RESOURCE_PATH+'/resource/system/images/popmenu1.gif" />');
							var _index=$(this).parent().index();
							$(".popMenu").children().hide();
							$(".test"+_index,$(".popMenu")).show();
							$(".popMenu").attr("showMenu","true");
							$(".ceng").show();
						},
						function () {
							$(this).html('<img src="'+RESOURCE_PATH+'/resource/system/images/popmenu2.gif" />');
							var _index=$(this).parent().index();
							$(".ceng").hide();
							$(".popMenu").children().hide();
							$(".popMenu").attr("showMenu","false");
						}
				);
				return false;
			};
		};
		
		$(".subnavbar li>a").each(function(){
			var subWidth=$(this).innerWidth()+15;
			$(this).parent().css("width",subWidth);		
		});
		
		var _tab=$('ul.tabnav>li');
		var boxindex=0;
		_tab.click(function(){
			var _index=_tab.index(this);
			boxindex=_index;
			$(this).addClass(_hover).siblings().removeClass(_hover);
			_box.eq(_index).show().siblings().hide();
			//前一次又焦点的box
			if(!$(".test"+_index).attr("afterProccess")){
				var currentBox = _box.eq(_index);
				$.each(currentBox.children(),showBoxMenu);
				objwidth=0;
				$(".popMenu").append($("<div class='test"+_index+"'/>").append($(".more",currentBox).nextAll()));
				$(".test"+_index).attr("afterProccess",true);
			}
			$("a:first",$($(".tabbox>ul")[boxindex])).click();
		});
		$(".ui-layout-center").click(function() {
			if($(".popMenu").attr("showMenu")=="true"){
				_box.eq(boxindex).children(".more").click();
			}
		});
		$(_tab[0]).click();
	});
</script>