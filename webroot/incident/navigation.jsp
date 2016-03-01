<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:include page="/includes/baseInclude.jsp" />
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<s:action name="getTitleProvinceName" var="getTitleProvinceName" executeResult="false" namespace="/sysadmin/orgManage" />
<script type="text/javascript">

function getMessageByUser(){
	var messageOption={
		url:'${path}/sysadmin/userMessage/findUserMessages.action'
			,notAcceptNote:{title:"未受理短信",link:"javascript:void(0)"
				<pop:JugePermissionTag ename="smsManagement">,limits:true </pop:JugePermissionTag>}
			,notAcceptOnlineAppeals:{title:"未阅读平台消息",link:"javascript:void(0)"
				<pop:JugePermissionTag ename="pmManagement">,limits:true</pop:JugePermissionTag>}
			,backlog:{title:"待办事项",link:"javascript:void(0)"
				<pop:JugePermissionTag ename="serviceWork">,limits:true</pop:JugePermissionTag>}
			,sessionTimeout: <s:property value='@com.tianque.core.util.GridProperties@SESSION_TIME_OUT'/>
		}
	$("body").messageTip(messageOption);
}
$(document).ready(function(){
	$.topmenu();
	$("#bigSize").click(function(){
		$("head").append('<style id="bigStyle">body,.x-tree-node,.ui-jqgrid{font:normal 14px arial,tahoma,helvetica,sans-serif}</style>');
	})
	function messageFun(){//消息弹出组件
		this.process=function(msgNum){
			var messageOption={
				notAcceptNote:{title:"未受理短信",link:"javascript:void(0)"
					<pop:JugePermissionTag ename="smsManagement">,limits:true </pop:JugePermissionTag>}
				,notAcceptOnlineAppeals:{title:"未阅读平台消息",link:"javascript:void(0)"
					<pop:JugePermissionTag ename="pmManagement">,limits:true</pop:JugePermissionTag>}
				,backlog:{title:"待办事项",link:"javascript:void(0)"
					<pop:JugePermissionTag ename="serviceWork">,limits:true</pop:JugePermissionTag>}
				,sessionTimeout: <s:property value='@com.tianque.core.util.GridProperties@SESSION_TIME_OUT'/>
				,data:msgNum
			}
			$("body").messageTip(messageOption);
		};
		this.sign="msgNum";
	}
	function announcementFun(){//公告组件
		this.process=function(data){
			if( data!=null && data.id!=null ){
				$.announcement({content:data.content,dataId:data.id,display:data.display});
			}
		};
		this.sign="proclamation";
	}

	function componentManager(){//组件管理器
		var myArray=new Array();
		var Interval;
		this.add=function(fn){
			myArray[myArray.length]=fn;
		}
		this.start=function(time){
			if(time==undefined){time=30000};
		};
		this.stop=function(){
			clearInterval(Interval);
		};
		return this;
	}

	var supervisor=new componentManager();
	supervisor.add(new messageFun());
	supervisor.add(new announcementFun());
	supervisor.start();

	function messagePop(){
		$(".message-tip").remove();
		$.ajax({
			url:"${path}/sysadmin/userMessage/findUserMessages.action",
			success:function(data){
				var messageNum = 0;
				if(data&&data.myNeedDoNum){
					messageNum=messageNum+data.myNeedDoNum;
				}
				if(data&&data.personnelMessageNum){
					messageNum=messageNum+data.personnelMessageNum;
				}
				if(data&&data.smsReceivedBoxNum){
					messageNum=messageNum+data.smsReceivedBoxNum;
				}
		        $("#msg").html(messageNum);
		        if(messageNum>0){
		        	$.cookie("messageTip",true, { path: '/', expires: 10 });
		        }
				$("body").messageTip({
					url:'${path}/sysadmin/userMessage/findUserMessages.action'
					,notAcceptNote:{title:"未受理短信",link:"javascript:void(0)"
						<pop:JugePermissionTag ename="smsManagement">,limits:true </pop:JugePermissionTag>}
					,notAcceptOnlineAppeals:{title:"未阅读平台消息",link:"javascript:void(0)"
						<pop:JugePermissionTag ename="pmManagement">,limits:true</pop:JugePermissionTag>}
					,backlog:{title:"待办事项",link:"javascript:void(0)"
						<pop:JugePermissionTag ename="serviceWork">,limits:true</pop:JugePermissionTag>}
					,data:data
				});
			}
		});
	}
	$(".message").click(function(){
		messagePop();
	});

	messagePop();
	//setInterval("getMessageByUser()",30000);

	$("#header-right > ul > li").hoverChange("hover");
	$("#huanfu").hoverDisplay("dl");
	$("#skin dd").click(function(){
		$.switchSkin(this.id);
	});
	///////////////
	<s:if test="#loginAction.user.previousLoginTime!=null">
		var info = "<li>上次登录时间是:<br/>";
		info+= '<s:date name="#loginAction.user.previousLoginTime" format="yyyy-MM-dd HH:mm:ss" />' ;
		info+= "</li>";
		info+= " <li>上次登录IP是:<br/>";
		info+= '<s:property value="#loginAction.user.previousLoginIp" />';
		info+= "</li>";

		info+= "<li>当前用户所在的组织机构是:<br/>";
		info+= '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#loginAction.user.organization.orgName" />';
		info+= "</li>";
	</s:if>
	<s:else>
		var info = "<li>这是您第一次登陆系统</li>";
		info+= "<li>当前用户所在的组织机构是:<br/>";
		info+= '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="#loginAction.user.organization.orgName" />';
		info+= "</li>";
	</s:else>
	$("#welcomeInfo").hover(
	     function () {
	      $("#welcomeInfo").dialogtip({
	    		content:info
	    	});
	      $("#welcomeInfo").poshytip('show');
	     },
	     function () {
	        $("#welcomeInfo").poshytip('hide');
	     }
	);
	//alterTheTitle();
	$('#exit').bind({
	    click: function() {
		var announcementName="announcement";
		var vlue = $.cookie(announcementName);
		if(vlue!=null){
			$.cookie(announcementName,null, { path: '/', expires: 10 });
		}
	   }
	});
	$(".view").hover(function(){
		$(this).addClass("cur");
		$("#viewSelect").slideDown();
	},function(){
		$(this).removeClass("cur");
		$("#viewSelect").hide();
	})
});
function alterTheTitle(){
	var orgName = $("#provinceName").val();
	var initCountry = '<s:property value="#getTitleProvinceName.initCountry" />';
	var userOrganizationDepartmentNo = '<s:property value="#getLoginOrg.organization.departmentNo" />';
	if(initCountry){
		//判断userOrganizationDepartmentNo的长度,当长度大于2的时候证明为浙江省组组织机构下的用户登录
		if(userOrganizationDepartmentNo.length > 2){
			$("#navigation-title").text(orgName + $("#navigation-title").text())
		}
	}else{
		//
		var rootDepartmentNo = '<s:property value="#getTitleProvinceName.organization.departmentNo" />';
		if(userOrganizationDepartmentNo < rootDepartmentNo){
			//在前面加上浙江省
			$("#navigation-title").text(orgName + $("#navigation-title").text())
		}
	}
}
/* function reloadMessageCount() {
	$(".messageButton").trigger('click');
	$(".message").trigger('click');
} */

var highLevel=<s:property value="#getLoginOrg.organization.orgLevel.internalId>@com.tianque.domain.property.OrganizationLevel@DISTRICT"/>;
var middleLevel=<s:property value="#getLoginOrg.organization.orgLevel.internalId<=@com.tianque.domain.property.OrganizationLevel@DISTRICT&&#getLoginOrg.organization.orgLevel.internalId>@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>;
var lowLevel=<s:property value="#getLoginOrg.organization.orgLevel.internalId<=@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>;
function menuBoxShow(){
	$(".accordingMenuBox").show();
}
function menuBoxHide(){
	$(".ui-accordion").hide();
}
<!--//这个方法是干嘛用的-->
function treeBoxShow(){
	$(".ui-layout-west>#incidentypeTree-top").appendTo("#recover");
	$(".ui-layout-west>#incidentTypeTree-select").appendTo("#recover");
	$(".ui-layout-west>#currentIncidentTypeId:first").appendTo("#recover");
	menuBoxHide();
	if($("#recover>#incidentTypeTree-select").length==0){
		$(".ui-layout-west").load("/incident/incidentSystemTree.jsp?selectedIncidentId=<s:property value='#parameters.selectedIncidentId'/>");
	}
	else{
		$("#recover>#incidentypeTree-top").appendTo(".ui-layout-west");
		$("#recover>#incidentypeTree-select").appendTo(".ui-layout-west");
		$("#recover>#currentIncidentTypeId:first").appendTo(".ui-layout-west");
		$("#recover").empty();
		$(".ui-layout-west #incidentypeTree-select .x-tree-selected:first").click;
		$(".ui-layout-west>#incidentypeTree-select").height($(".ui-layout-west").outerHeight()-$("#orgTree-top").outerHeight());
	}
}
<!--读取树-->
function baseTreeShow(isBasicInformation,afterLoad){
	menuBoxHide();
	if(isBasicInformation == null || isBasicInformation == ''){
		isBasicInformation = false;
	}
	$(".ui-layout-west").load("${path}/incident/extTreeForIncident/incidentSystemTree.jsp?selectedIncidentId=<s:property value='#parameters.selectedIncidentId'/>&isBasicInformation="+isBasicInformation,function(){
		function afterChangNode(node){
			if($("#contentDiv").data("loadFirst")=="false"){
				afterLoad();
			};
			$("#contentDiv").data("loadFirst","true");
		}
		$.addClick(tree,afterChangNode);
		
	});
}
function plaitSchemeTreeShow(isBasicInformation,afterLoad){
	menuBoxHide();
	if(isBasicInformation == null || isBasicInformation == ''){
		isBasicInformation = false;
	}
	$(".ui-layout-west").load("${path}/incident/extTreeForIncident/plaitSchemeTree.jsp?selectedIncidentId=<s:property value='#parameters.selectedIncidentId'/>&isBasicInformation="+isBasicInformation,function(){
		function afterChangNode(node){
			if($("#contentDiv").data("loadFirst")=="false"){
				afterLoad();
			};
			$("#contentDiv").data("loadFirst","true");
		}
		$.addClick(tree,afterChangNode);
		
	});
}


function showPageByTopMenu(topMenu){
	var menuType;
	if(topMenu.indexOf("-")!=-1){
		menuType=topMenu.substr(topMenu.indexOf("-")+1);
		topMenu=topMenu.substring(0,topMenu.length-menuType.length-1);
		if(typeof(eval("incidentMenuFunction."+topMenu))!='function'){
			topMenu="predictionScheme";
		}
	}
	if(topMenu=='' || topMenu==window.location.href){
		topMenu="predictionScheme";
	}
	$("#contentDiv").data("loadFirst","false");
/**
 * selectedOrgId 变量是怎么传递的? 
 */
	var selectedOrgId="<s:property value='#parameters.selectedOrgId'/>";
	var typeName=$(".ui-tabs-selected").text();
	$.dialogLoading("open");
	$(".subnav").empty();
	$(".path").empty();
	$("#contentDiv").empty();
	$("#baseLine").nextAll(":not(.ui-autocomplete)").remove();
	$("#baseLine").nextAll(":hidden:not(.ui-autocomplete)").remove();
	try
	{
		eval("incidentMenuFunction."+topMenu+"(selectedOrgId,menuType);");
	}
	catch(err)
	{
		$(".dialog_loading").hide();
		$.messageBox({message:'系统出错，请刷新页面重试',level:'error'});
		throw new Error(err);
	}
}
function getBaseInfoDataViewa(urlflag,charttype){
	$(".path").children().remove();
	$(".subnav").children().remove();
	$(".path").hide();
	$(".submenu").show();
	$(".subnav").load("/baseinfo/middleLevelSideBar.jsp?charttype="+charttype+"&urlflag="+urlflag);
}
function loadFromSelectChart(type,menu){
	var urlflag=menu;
	var charttype;
	if(type=="keyPlace"){
		charttype = 2;
	}else if(type=="keyPopulation"){
		charttype=1;
	}
	getBaseInfoDataViewa(urlflag,charttype);
}
$(function() {
	var urlNum=window.location.href.lastIndexOf('#');
	var url=window.location.href.substr(urlNum+1);
	showPageByTopMenu(url);
	$("#date").dateWeek();
	$("body").prepend('<div id="recover"></div>');
	$("#bigSize").toggle(function(){
		if(!$("#bigStyle")[0]){
			$("head").append('<style id="bigStyle">body *{font-size:14px}</style>');
		}
		$("#bigSize").html("街道社区");
	},function(){
		if($("#bigStyle")[0]){
			$("#bigStyle").remove();
		}
		$("#bigSize").html("指挥中心");
	})
	/*$("#pages").hover(function(e){
		var that=this;
		$(document).unbind("mouseover").mouseover(function(e){
			var $target=$(e.target);
			if($target==$(that)){
				
			}
		})
		if(!$("#pagesCtt")[0]){
			$("<div />").attr("id","pagesCtt").appendTo("body").load("/sysadmin/menuManage/getLowLevelBaseInfoMenuListByPageList.action?ename=basicInformation",function(){
				$("#pagesCtt").show();
			});
		}else{
			$("#pagesCtt").show();
		}
	},function(){
		//$("#pagesCtt").hide();
	})*/
});
</script>
<div id="header">
	<div id="header-top">
		<div id="header-left"></div>
		<div id="header-center"><b><span id="navigation-title"><s:property value="#getLoginOrg.organization.orgName"/>${sysHeaderPage}</span></b><strong>Beta 2.2</strong>当前用户：<SPAN id="welcomeInfo" name="welcomeInfo"><s:property value="#loginAction.user.name" /></SPAN><span id="date"></span>
		
		<ul id="font">
			<!--<li class="pages" id="pages"><a href="javascript:;">网站导航</a></li>-->
			<pop:JugePermissionTag ename="gisSystem">
			<li class="view">
				<span><strong class="view-ico1"></strong>视图切换</span>
					<dl id="viewSelect">
						<dd><a href="${path}/gis3D/gisView.jsp" target="_blank">GIS视图</a></dd>
						<dd><a href="javascript:;" id="bigSize">指挥中心</a></dd>
<!--					<dd><a href="${path}/desktop/index.jsp">桌面视图</a></dd>-->
<!--					<dd><a href="${path}/edushiGis/gisView.jsp">GIS视图</a></dd>-->
					</dl>
			</li>
			<li class="line"></li>
			</pop:JugePermissionTag>
			<li class="home"><a id="index-link" href="${path}<s:property value="#parameters.indexPath[0]"/>/index.jsp?isIndexJsp=true"><span>主页</span></a></li>
			<li id="huanfu" class="huanfu"><a href="javascript:void(0)"><span>换肤</span></a>
				<dl id="skin" style="display: none;">
					<dd id="default" class="default">系统默认</dd>
					<dd id="green" class="green">青草绿</dd>
					<dd id="blue" class="blue">天际蓝</dd>
				</dl>
			</li>
			<li class="exit"><a id="exit" href="${path}/sessionManage/logout.action?isIndexJsp=true&indexPath=<s:property value="#parameters.indexPath[0]"/>"><span>退出</span></a></li>
		</ul>
		</div>
		<div id="header-right"></div>
	</div>
	<div id="header-bottom">
		<div id="menu">
			<ul>
				<li><a hidefocus id="predictionScheme-menu" href="#predictionScheme" onclick="javascript:showPageByTopMenu('predictionScheme');"><strong class="predictionScheme"></strong><span class="basicInformation">预案制定</span></a></li>
				<li><a hidefocus id="emergencyDisposal-menu" href="#emergencyDisposal" onclick="javascript:showPageByTopMenu('emergencyDisposal');"><strong class="emergencyDisposal"></strong><span>应急处置</span></a></li>
				<li><a hidefocus id="incidentManagement-menu" href="#incidentManagement" onclick="javascript:showPageByTopMenu('incidentManagement');"><strong class="system"></strong><span class="systemManagement">系统管理</span></a></li>
			</ul>
		</div>
		<div id="header-bottom-right">
			<ul>
				<li class="message"><strong class="nomessages"></strong><a href="javascript:void(0)">消息(<font color="red" id="msg"><s:property value="unReadCount"/></font>)</a></li>
				<li class="header_help"><a href="${path }/manual/index.html" target="_blank">帮助</a></li>
				<li class="liuyan"><a href="javascript:void(0)" title="还未开发">留言</a></li>
				<li class="fankui"><a href="http://172.17.229.227" target="_blank">论坛</a></li>
			</ul>
			<input type="hidden" id="provinceName" value='<s:property value="#getTitleProvinceName.organization.orgName" />' />
		</div>
	</div>
</div>
<div id="shouldLogin"></div>
<!--[if IE 6]>
	<script type="text/javascript">
		$(function(){
			DD_belatedPNG.fix('#menu li strong,#top-submenu li strong');
		})
	</script>
<![endif]-->