<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.tianque.core.globalSetting.service.GlobalSettingService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="com.tianque.core.globalSetting.util.GlobalSetting"%>
<%
GlobalSettingService globalSettingService = (GlobalSettingService)WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("globalSettingService");
String workbenchAppQRcode = globalSettingService.getGlobalValue(GlobalSetting.WORKBENCH_APP_QR_CODE);
String workbenchApkPath = globalSettingService.getGlobalValue(GlobalSetting.WORKBENCH_APK_PATH);
if (workbenchAppQRcode != null) {
	workbenchAppQRcode = workbenchAppQRcode.replace("\\","/");
}
request.setAttribute("workbenchAppQRcodePath",workbenchAppQRcode);
request.setAttribute("workbenchApkPath",workbenchApkPath);


String url=request.getScheme()+"://"; 
url+=request.getHeader("host"); 
request.setAttribute("indexPath",url);
%>
<script type="text/javascript" src="${resource_path}/resource/external/layer/layer.js"></script>
<div class="systemHeader">
	<div class="logo-container">
		<!-- <img src="${resource_path}/resource/system/images/logo.png" /> -->
		<div style="padding-top: 10px; padding-left: 0px;font-size: 25px !important; color: #FFFFFF; font-weight: bold; dispalay: none;" name="BASE_SYSTEM_HEAD_DIV" id="_headedTitleDiv"></div><div></div>
		<input type="hidden" id="xcLogo" value="<s:property value='@com.tianque.core.util.ThreadVariable@getOrganization().getOrgName()'/>">
	</div>
	<div class="product-menu" id="product-menu" name="BASE_SYSTEM_HEAD_DIV" style="display: none;">
		<div id="topMenu" class="sysMenu">
			<a href="javascript:;">导航切换</a>
			<dl class="sysMenuList cf" id="sysMenuList"></dl>
		</div>
	</div>
    <div class="login-window">
    	<s:if test="null!=@com.tianque.core.util.ThreadVariable@getUser().getHeaderUrl()">
    		<div class="pic" id="settingInfoPic" title="个人设置"><img src="<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getHeaderUrl()"/>" alt="" /></div>
    	</s:if>
    	<s:else>
	        <div class="pic" id="settingInfoPic" title="个人设置"><img src="${resource_path}/resource/system/images/blank.png" alt="" /></div>
    	</s:else>
        <div class="cont" id="settingInfoCont">
            <div class="personal"><span id="personelInfo">欢迎您！<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getName()"/></span><span id="modifyRoleLi" style = "display:none"><a id="modifyRole" href="javascript:;">[用户切换]</a></span>
            </div>
            <ul class="switchFunction">
            	<li class="fli" title="个人设置"><span id="settingInfo">个人设置</span></li>
            	<li id="functionContList" class="fli"><span id="functionInfo" class="functionInfo">风格设置</span>
            		<ul class="functionCont hidden">
            			<li class="func"><strong class="setC skinC"></strong><span>换肤</span>
            				<ul class="list hidden" id="loopSkinCont">
            					<li><a value="blue" href="javascript:;"><span class="blue-color"></span>商务蓝</a></li>
            					<li><a value="red" href="javascript:;"><span class="red-color"></span>中国红</a></li>
            					<li><a value="green" href="javascript:;"><span class="green-color"></span>草原绿</a></li>
            				</ul>
            			</li>
            			<%-- <li class="func"><strong class="setC fontC"></strong><span>字号设置</span>
            				<ul class="list hidden" id="loopTypeFaceCont">
            					<li><a size="12" href="javascript:;">小字号</a></li>
            					<li><a size="14" href="javascript:;">大字号</a></li>
            				</ul>
            			</li> --%>
            		</ul>
            	</li>
			</ul>
        </div>
    </div>
    <ul class="header-right-toolMenu">
		<li id="message" title="消息"><strong ></strong><a href="javascript:;" class="topMessage"><span id="msg"><s:property value="unReadCount"/></span></a></li>
		<li title="主页"><a href="/module.jsp#index" onclick="showPageByTopMenu('index')" class="user-config" id="config"></a></li>
		<c:if test="${workbenchAppQRcodePath != null && workbenchAppQRcodePath != ''}">
			<li>
	    		<div title="手机APP二维码"  >
	    			<a href="javascript:void(0);" style="background:none; ">
	   					<img id="workbenchAppQRcodeDiv" alt="手机APP二维码" style="z-index: 100;display: block;margin: 0 0 0 0;border: none;" src="resource/workBench/images/mobile.png" height="24px" width="15px">
	   				</a>
	    		</div>
	    	</li>
    	</c:if>
		<li title="退出"><a id="exit" href="${path}/sessionManage/logout.action?isIndexJsp=true&indexPath=<s:property value="#parameters.indexPath[0]"/>" class="sys-exit"></a></li>
	</ul>
    <div class="switchSkin_red_rightBg"></div>
</div>
<div id="shouldLogin"></div>
<div id="settingDialog"></div>
<div id="maintainDlg"></div>
<c:if test="${workbenchAppQRcodePath != null && workbenchAppQRcodePath != ''}">
	<script type='text/javascript'>
		$(".login-window").css({ "right": 138});
		$('#workbenchAppQRcodeDiv').on('click', function(){
			layer.open({
			    type: 1,
			    area: ['601px', '406px'],
			    //shade: false,
			    title: false, //不显示标题
			    content: '<div style="width:600px;height:400px"><div style="background-image:url(${path}/resource/workBench/images/workbenchAppQRcode_background_img.png);background-repeat:repeat-x;margin:3px"><div style="float:left;padding-top:25px"><img alt="社区e通" src="${path}/resource/workBench/images/mobile_app.png" width="226px" height="364px"></div><div style="float:left;padding-top:58px;margin-left:10px"><div><span style="font-size:36px;color:#FFF;font-family:arial,sans-serif">社区e通</span></div><div style="padding-top:20px;line-height:45px"><a href="${workbenchApkPath}"><img id="apkDownload" src="${path}/resource/workBench/images/apk_download_0.png" onmouseover="replaceImgRSC(this,&quot;${path}/resource/workBench/images/apk_download_1.png&quot;)" onmouseout="replaceImgRSC(this,&quot;${path}/resource/workBench/images/apk_download_0.png&quot;)" width="302px" height="47px"></a><p style="font-size:14px;color:#666">下载apk安装包到本地，使用第三方工具进行安装</p></div><div style="padding-top:15px;line-height:30px"><div style="float:left"><img src="${workbenchAppQRcodePath}" width="135px" height="135px"></div><div style="float:left;padding-top:100px;padding-left:10px;font-size:14px;color:#666">扫描二维码获取APP</div></div></div><div style="clear:both"></div></div></div>', //捕获的元素
			    
			});
		});
		function replaceImgRSC(ele,path){
			$(ele).attr("src",path);
		}
	</script>
</c:if>

<script id="topMenuTpl" type="text/html">
	    {{each list as value i}}
			{{if value.ename=="gisManagement"}}
				<dd>
					<a href="/module.jsp#map" 
					rel="map" id="map-menu" 
					style="white-space: nowrap;">
					<strong class="gisManageSystem">
					</strong>{{value.name}}</a>
				</dd>
			{{else if value.ename=="mediationManagement"}}
				<dd>
					<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().startsWith('Dzfw')&&@com.tianque.core.util.ThreadVariable@getUser().getUserName().endsWith('@sg')">
					<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('Dzfw009@sg')||@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('Dzfw004@sg')">
					<a target="_blank" href="http://10.0.188.18/Default.aspx?zid=37560" 
					rel="" id="mediation-menu" style="white-space: nowrap;">
					<strong class="mediationManagement"></strong>{{value.name}}</a>					
					</s:if>
					<s:else>
					<a target="_blank" href="http://10.0.188.18/Default.aspx?zid=47816" 
					rel="" id="mediation-menu" 	style="white-space: nowrap;">
					<strong class="mediationManagement"></strong>{{value.name}}</a>
					</s:else>
					</s:if>
					<s:else>
					<a target="_blank" href="http://10.0.188.18" 
					rel="" id="mediation-menu" 
					style="white-space: nowrap;">
					<strong class="mediationManagement">
					</strong>{{value.name}}</a>
					</s:else>
				</dd>
			{{else if value.ename=="unifiedSearchManagement"}}
				<dd>
					<a target="_blank" href="/baseinfo/tqSearch/dispatch.action?mode=index" 
					rel="" id="unifiedSearch-menu" 
					style="white-space: nowrap;">
					<strong class="unifiedSearchManagement" style="background: url(/resource/tqSearch/img/tqSearch.png) no-repeat;background-size:57px;">
					</strong>{{value.name}}</a>
				</dd>
			{{else if value.ename=="judgmentAnalysisManage"}}
				<s:if test="@com.tianque.core.util.ThreadVariable@getUser().getUserName().equals('longzhendong')">
				<dd>
						<a href="/module.jsp#{{value.ename}}"
						rel="{{value.ename}}"
						id="{{value.ename}}-menu"
						style="white-space: nowrap;">
						<strong class="{{value.ename}}"></strong>
						{{value.name}}</a>
					</dd>
				</s:if>
			{{else if value.ename=="judgmentAnalysisViewManage"}}
				<dd>
					<a target="_blank" href="/login/judgmentAnalysis/module.jsp" 
					rel="" id="judgmentAnalysisView-menu" 
					style="white-space: nowrap;">
					<strong class="judgmentAnalysisViewManage" style="background: url(/resource/system/images/supAreaStat.png) no-repeat;background-size:57px;">
					</strong>{{value.name}}</a>
				</dd>
			{{else if value.ename=="tenHouseholdsJointManagement"}}
				<dd>
					<a target="_blank" href="/tenHouseholdsJoint/index.ftl" 
					rel="" id="tenHouseholdsJoint-menu" 
					style="white-space: nowrap;">
					<strong class="tenHouseholdsJointManagement">
					</strong>{{value.name}}</a>
				</dd>
			{{else}}
				{{if value.ename!="approvalManageSystem" && value.ename!="incidentSystem" && value.ename!="commandCenterManagement"}}
					<dd>
						<a href="/module.jsp#{{value.ename}}"
						rel="{{value.ename}}"
						id="{{value.ename}}-menu"
						style="white-space: nowrap;">
						<strong class="{{value.ename}}"></strong>
						{{value.name}}</a>
					</dd>
				{{/if}}
			{{/if}}
		{{/each}}
</script>
<!--[if IE 6]>
	<script type="text/javascript">
		$(function(){
			DD_belatedPNG.fix('.login-window img,.logo-container img,#menu li strong,#top-submenu li strong,.header-right-toolMenu a,dl.sysMenuList dd a strong,.switchFunction #functionInfo');
			$(".sysMenuList").bgiframe();
		})
	</script>
<![endif]-->

<script type='text/javascript'>
	$("#loadingMain").remove();
	function showPageByTopMenu(topMenu){
		var menuType;
		if(topMenu.indexOf("-")!=-1){
			menuType=topMenu.substr(topMenu.indexOf("-")+1);
			topMenu=topMenu.substring(0,topMenu.length-menuType.length-1);
		}
		if(topMenu=='' || topMenu==window.location.href){
			topMenu="index";
		}
		var selectedOrgId="<s:property value='#parameters.selectedOrgId'/>";
		var typeName=$(".ui-tabs-selected").text();
		$("#contentDiv").empty();
		try{
			$.selectMenu(topMenu+"-menu");
			if(topMenu=='index'){
				$(".ui-layout-west,#thisCrumbs,.slideResizer").hide();
				$(".ui-layout-center").css({
					width:document.documentElement.clientWidth-10
				})
				.find("#contentDiv").html('<div id="msg-tips-index" class="tipsbox"><p id="loading" class="loadbox"><img src="${resource_path}/resource/workBench/images/loading_comment.gif" alt="loading">正在获取信息，请稍候...</p></div>')
				.load("/workBench/workBenchMain.jsp");
			}else if(topMenu=='map'){
				$(".ui-layout-west,#thisCrumbs,.slideResizer").hide();
				document.title = $("#map-menu").text();
				$(".ui-layout-center").css({
					width:document.documentElement.clientWidth-10
				})
				.find("#contentDiv").css("padding-left",0)
				.html('<div id="msg-tips-index" class="tipsbox"><p id="loading" class="loadbox"><img src="${resource_path}/resource/workBench/images/loading_comment.gif" alt="loading">正在获取信息，请稍候...</p></div>')
				.load("/openLayersMap/index.jsp");
				
			}else if(topMenu=='serviceWork'){
				$(".ui-layout-center").css({
					width:document.documentElement.clientWidth-$(".ui-layout-west").width()-10
				})
				if(menuType=='myAuditDelayListManagement'){//如果是待审核事项，就传一个标记以定位到带审核事项列表
					$(".ui-layout-west").show().load("/issue/issueLeftMenuList.jsp?isAuditDelay=1",function(){
						$("#thisCrumbs,.slideResizer").show();
					});
				}else if (menuType=='myIssueListManagementVerification'){
					$(".ui-layout-west").show().load("/issue/issueLeftMenuList.jsp?isAuditDelay=2",function(){
						$("#thisCrumbs,.slideResizer").show();
					});
				}else if (menuType=='myIssueListManagementCheckGrade'){
					$(".ui-layout-west").show().load("/issue/issueLeftMenuList.jsp?isAuditDelay=3",function(){
						$("#thisCrumbs,.slideResizer").show();
					});
				}else{
					$(".ui-layout-west").show().load("/issue/issueLeftMenuList.jsp",function(){
						$("#thisCrumbs,.slideResizer").show();
					});
				}
			}else{
				$(".ui-layout-center").css({
					width:document.documentElement.clientWidth-$(".ui-layout-west").width()-10
				})
				$(".ui-layout-west").show().load("/sysadmin/menuManage/getLeftMenuListToJson.action?ename="+topMenu+"&urlflag="+menuType,function(){
					$("#thisCrumbs,.slideResizer").show();
				});
			}
		}
		catch(err){
			$(".dialog_loading").hide();
			$.messageBox({message:'系统出错，请刷新页面重试',level:'error'});
			throw new Error(err);
		}
	}
	
	$(function (){
		$.ajax({
			async: true,
			url: PATH+"/sessionManage/isXiCangLogo.action",
			success:function(data){
				callback(data)
			}
		});
//页面中未找到msgNew元素，是否已废弃？ 定时有平台消息扫描，去掉此处消息查询 by wangxiaohu 20150508 登录优化
// 		$.ajax({
// 			url:"${path}/sysadmin/userMessage/findUserMessages.action",
// 			success:function(data){
// 				if(data.messageNum>99){
// 					$("#msgNew").html("99+");
// 				}else{
// 					$("#msgNew").html(data.messageNum);
// 				}
// 			}
// 		});
		var isSwitchover=$("#isSwitchover").val();
		if(isSwitchover==null){
		   document.getElementById('modifyRole').style.display ="none";
		} 
		function callback(bol){
			if(!bol){
				$.ajax({
					async: true,
					url: PATH+"/sessionManage/isWuShengLogo.action",
					success:function(responseData){
						secondaryCallback(bol,responseData)
						
					}
				});
			}else{
				secondaryCallback(bol,false);
			}
		} // callback
		
		function secondaryCallback(bol,wushengbol){
			$.post(PATH+'/sysadmin/orgManage/getUserCityOrganizationName.action', function (data){
				if(bol){
					$("#_headedTitleDiv").html("西昌市网格化服务管理信息系统");
				}else if(wushengbol){
					$("#_headedTitleDiv").html("武胜县网格化服务管理信息系统");
				}else if(data == 'city'){
					$("#_headedTitleDiv").html("网格化服务管理信息系统");
				}else{
					$("#_headedTitleDiv").html("网格化服务管理信息系统");
				}
				
				$("#_headedTitleDiv,#product-menu").fadeIn(200);
			});
		} // secondaryCallback
		
		

		var json = <s:if test="menuJson == null">[]</s:if><s:else>${menuJson}</s:else>;
		
		var html = template("topMenuTpl", {'list':json});
		$("#sysMenuList")["append"](html);
	        
		var getHash= function(window) {
	        var match = (window || this).location.href.match(/#(.*)$/);
	        return match ? match[1] : '';
	    }
		var localhostHash=getHash(window);
		showPageByTopMenu(localhostHash);
		$(".product-menu .sysMenu").hover(function(){
			clearTimeout(window._menuHideTimer);
			window._menuShowTimer=setTimeout(function(){
				$(".sysMenuList").fadeIn(200);
				$(".message-tip").css("z-index",0);
			},300)
		},function(){
			clearTimeout(window._menuShowTimer);
			window._menuHideTimer=setTimeout(function(){
				$(".sysMenuList").fadeOut(200);
				$(".message-tip").removeAttr("style");
			},300);
		});
		$(".sysMenuList a").click(function(){
			var rel=$(this).attr("rel");
			if(rel!=''){
				showPageByTopMenu(rel);
			}
			$('#sysMenuList').fadeOut(200);
		});
	});

</script>