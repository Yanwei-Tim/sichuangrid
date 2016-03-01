<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<s:action name="getMyIssueCount" var="getMyIssueCount" executeResult="false" namespace="/issue/issueManage">
    <s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<ul class="tabnav">
	<pop:JugePermissionTag ename="myIssueListManagement,jurisdictionsIssueMenuManagement">
		<li id="issueMenu"><span>事件处理</span></li>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="myIssueReportListManagement">
		<li><span><s:property value="#request.name"/></span></li>
	</pop:JugePermissionTag>
          <li style="color: black"><a href="javascript:void(0)" style="color: black" onclick="asyncMiddleLevelOpen(this,'${path}/issue/issueManage/processView.jsp')"><span>事件处理流程</span></a></li>
</ul>
<div class="tabbox">
	<pop:JugePermissionTag ename="jurisdictionsIssueMenuManagement,myIssueMenuManagement">
		<ul class="subnavbar">
		<pop:JugePermissionTag ename="unDoMyIssueListManagement">
			<li id="mineLi1"><a id="mineA1" href="javascript:void(0)"
				onclick="asyncMiddleLevelOpen(this,'${path}/issue/issueManage/issueList.jsp?marker=1')"><span>待办事项</span>(<font color="red" id="myIssueCount"><s:property value="#getMyIssueCount.issueCount"/></font>)</a></li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="doneMyIssueListManagement">
			<li id="mineLi2"><a href="javascript:void(0)" onclick="asyncMiddleLevelOpen(this,'${path}/issue/issueManage/issueDoneList.jsp')"><span>已办事项</span></a></li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="unDoJurisdictionsIssueListManagement">
			<li id="administerLi1"><a id="administerA1" href="javascript:void(0)"
				onclick="asyncMiddleLevelOpen(this,'${path}/issue/issueManage/jurisdictionsIssueList.jsp')"><span>待办事项</span></a></li>
		</pop:JugePermissionTag>
		<pop:JugePermissionTag ename="doneJurisdictionsIssueListManagement">
			<li id="administerLi2"><a href="javascript:void(0)" onclick="asyncMiddleLevelOpen(this,'${path}/issue/issueManage/jurisdictionsDoneIssueList.jsp')"><span>已办结事项</span></a></li>
		</pop:JugePermissionTag>
		</ul>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="jurisdictionsIssueReportListManagement,myIssueReportListManagement">
		<ul class="subnavbar">
			<pop:JugePermissionTag
				ename="testIndividuallyContradictionMyIssueManagement">
				<li id="contradictionMine"><a href="javascript:void(0)" id="contradiction"
					onclick="asyncMiddleLevelOpen(this,'${path}/issue/testIndividuallyNew/preTestIndividuallyList.action?testIndividuallyType=contradiction&requestType=mine')"><span><s:property value="#request.name"/></span></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="testIndividuallyHiddenTroubleJurisdictionsIssueManagement">
				<li id="specialisationNewMine"><a href="javascript:void(0)"
					onclick="asyncMiddleLevelOpen(this,'${path}/issue/issueSecuritytroubleManage/preIssueSecurityTroubleList.action?requestType=mine')"><span>治安,安全隐患</span></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="peopleLiveServiceMyIssueManagement">
				<li id="peopleLiveServiceMine"><a href="javascript:void(0)"
					onclick="asyncMiddleLevelOpen(this,'${path}/issue/peopleLiveService/prePeopleLiveServiceList.action?requestType=mine')"><span><s:property value="#request.name"/></span></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag
                      ename="testIndividuallyContradictionJurisdictionsIssueManagement">
                      <li id="contradictionAdminister"><a href="javascript:void(0)" id="contradiction"
                        onclick="asyncMiddleLevelOpen(this,'${path}/issue/testIndividuallyNew/preTestIndividuallyList.action?testIndividuallyType=contradiction&requestType=administer')"><span><s:property value="#request.name"/></span></a></li>
                  </pop:JugePermissionTag>
                  <pop:JugePermissionTag ename="testIndividuallyHiddenTroubleMyIssueManagement">
                      <li id="specialisationNewAdminister"><a href="javascript:void(0)"
                          onclick="asyncMiddleLevelOpen(this,'${path}/issue/issueSecuritytroubleManage/preIssueSecurityTroubleList.action?requestType=administer')"><span>治安,安全隐患</span></a></li>
                  </pop:JugePermissionTag>
                  <pop:JugePermissionTag ename="peopleLiveServiceJurisdictionsIssueManagement">
                      <li id="peopleLiveServiceAdminister"><a href="javascript:void(0)"
                          onclick="asyncMiddleLevelOpen(this,'${path}/issue/peopleLiveService/prePeopleLiveServiceList.action?requestType=administer')"><span><s:property value="#request.name"/></span></a></li>
                  </pop:JugePermissionTag>
		</ul>
	</pop:JugePermissionTag>
	<ul class="subnavbar">
		<li ><a href="javascript:void(0)" onclick="asyncMiddleLevelOpen(this,'${path}/issue/issueManage/processView.jsp')"><span>事件处理流程</span></a></li>
	</ul>
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
	    $("a:visible:first",$($(".tabbox>ul")[boxindex])).click();
	});
	$(".ui-layout-center").click(function() {
	    if($(".popMenu").attr("showMenu")=="true"){
	        _box.eq(boxindex).children(".more").click();
	    }
	});

	/* 二级菜单 hover 效果 */
	$.fn.tabHoverDelay=function(options){
		var i;
		var self=$(this);
		var defaults = {
            hoverDuring: 100,
            outDuring: 100,
            hoverEvent: function(){
				i = self.prevAll().length;
				$(".tabnav li").eq(i).addClass("hover").siblings().removeClass("hover");
				$(".tabbox ul").eq(i).show().siblings(".tabbox ul").hide();
            },
            outEvent: function(){
            	$(".tabnav li").eq(i).addClass("hover").siblings().removeClass("hover");
				$(".tabbox ul").eq(i).show().siblings(".tabbox ul").hide();
            },
            mouseoutFunc:function(){
            	var clickIndex=$(".tabbox ul li a.click:first").parent().parent().index(".tabbox ul");
				$(".tabnav li").eq(clickIndex).addClass("hover").siblings().removeClass("hover");
				$(".tabbox ul").eq(clickIndex).show().siblings(".tabbox ul").hide();
	        }
	    };
		var sets = $.extend(defaults,options || {});
		var hoverTimer, outTimer,mouseoutTimer;
		self.hover(function(){
               clearTimeout(outTimer);
               hoverTimer = setTimeout(sets.hoverEvent, sets.hoverDuring);
           },function(){
               clearTimeout(hoverTimer);
               outTimer = setTimeout(sets.outEvent, sets.outDuring);
        });
		$(".tabnav").unbind();
		$(".tabbox").unbind();
		$(".tabnav").hover(function(){
			clearTimeout(mouseoutTimer);
		},function(){
			mouseoutTimer=setTimeout(sets.mouseoutFunc,5000);
		});
		$(".tabbox").hover(function(){
			clearTimeout(mouseoutTimer);
		},function(){
			mouseoutTimer=setTimeout(sets.mouseoutFunc,5000);
		});
	}
	$(".tabnav li").each(function(){
		$(this).tabHoverDelay();
	})

	$(".submenu").hide();
	mineMenu();
});
function refreshMyIssueCount(){
	$.ajax({
		url:"${path}/issue/issueManage/getMyIssueCount.action",
		data:{
			"organization.id":$("#currentOrgId").val()
		},
		success:function(data){
		    $("#myIssueCount").html(data);
        }
	});
}
</script>
