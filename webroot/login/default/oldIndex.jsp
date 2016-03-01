<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.tianque.core.globalSetting.service.GlobalSettingService"%>
<%@page import="com.tianque.core.globalSetting.util.GlobalSetting"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%
GlobalSettingService globalSettingService = (GlobalSettingService)WebApplicationContextUtils.getWebApplicationContext(this.getServletContext()).getBean("globalSettingService");
String sysTitle = globalSettingService.getGlobalValue(GlobalSetting.SYS_TITLE);
String sysBottomPage = globalSettingService.getGlobalValue(GlobalSetting.SYS_BOTTOM_PAGE);
String logoImage = globalSettingService.getGlobalValue(GlobalSetting.LOGO_IMAGE);

request.setAttribute("logoImage",logoImage);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title><%=sysTitle %></title>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<jsp:include page="/includes/baseInclude.jsp" />
<link href="${resource_path}/resource/external/ui/jquery-ui-1.8.17.custom.css" rel="stylesheet" type="text/css"/>
<link href="${resource_path}/resource/external/ui/default.css" rel="stylesheet" type="text/css" id="cssfile"/>
<link type="text/css" href="${resource_path}/resource/external/poshytip/tip-error/tip-error.css" rel="stylesheet" />
<link href="${resource_path}/resource/external/poshytip/tip-yellowsimple/tip-yellowsimple.css" rel="stylesheet" type="text/css"/>
<link href="${resource_path}/resource/css/formgrid.css" rel="stylesheet" type="text/css"/>
<link href="${resource_path}/resource/external/jGrowl/jquery.jgrowl.css" rel="stylesheet" type="text/css"/>
<link href="${resource_path}/resource/external/jqGrid/css/ui.jqgrid.css" rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" href="${resource_path}/resource/images/favicon.ico" type="image/x-icon"/>
<link type="text/css" href="${resource_path}/resource/css/reset.css" rel="stylesheet" />

<script type="text/javascript" src="${resource_path}/resource/external/jquery.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/ui/jquery-ui-1.8.17.custom.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/ui/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/init.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.form.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jsValidate/jquery.validate.min.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jGrowl/jquery.jgrowl_minimized.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/poshytip/jquery.poshytip.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jquery.cookie.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jqGrid/js/grid.locale-cn.js"></script>
<script type="text/javascript" src="${resource_path}/resource/external/jqGrid/js/jquery.jqGrid.min.js"></script>
<script src="${resource_path}/resource/external/slideView.1.2.js" type="text/javascript"></script>

<script type="text/javascript" src="${resource_path}/resource/system/js/dialog.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/formValidate.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/gridUtil.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/widget.js"></script>
<script type="text/javascript" src="${resource_path}/resource/system/js/idCheckUtil.js"></script>
<script>
	$(function(){
		var isAdminForJudgeModifyRole = '<s:property value="#loginAction.user.admin"/>';
		var UserNameForJudgeModifyRole = '<s:property value="#loginAction.user.userName"/>';
			if(UserNameForJudgeModifyRole == "admin"||isAdminForJudgeModifyRole=='true'||$.cookie('oldSid')!=null){
				$("#modifyRoleLi").show();
			}

		$(window).scroll(function(){
			$("#bottom").css("bottom","0px");
		})
		$("#slideshow").slideView({
			loop: false,
			speed: 0,
			slideBy:5,
			easeOut: "swing",
			easeIn: "swing",
			easeThumb: "swing"
		});

		isShowView();

		$(".slidebar").appendTo("#slides");

		$("#modifyPasswordButton").click(function(){
			$("#maintainDlg").createDialog({
				width: 400,
				height: 220,
				title:'修改密码',
				url:'${path }/passwordUpdateDlg.jsp',
				buttons: {
					"保存" : function(event){
				        $("#firstUpdatePassForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});

		$("#modifyRole").click(function(){
			$("#maintainDlg").createDialog({
				width: 360,
				height: 180,
				title:'更改角色',
				url:'${path}/modifyRoleDlg.jsp',
				buttons: {
					"保存" : function(event){
						$("#modifyRoleForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});


		$("#modifyUserInfoButton").click(function(){
			$("#maintainDlg").createDialog({
				width: 520,
				height: 220,
				title:'修改个人信息',
				url:'${path}/sysadmin/userManage/toPersonalDetailsUpdate.action',
				buttons: {
					"保存" : function(event){
				        $("#updateDetailsForm").submit();
				        $.messageBox({ message:"修改个人信息成功!"});
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
		$("#exitNew").bind({
			click:function(){
				var announcementName="announcement";
				var vlue = $.cookie(announcementName);
				if(vlue!=null){
					$.cookie(announcementName,null, { path: '/', expires: 10 });
				}
			}
		});
	});

		function gotoMonitorPage(){
			var orgInternalCode = '<s:property value="#getLoginOrg.organization.orgInternalCode"/>';
			var href='${path}/videointegration/videologin.jsp';
			if(orgInternalCode.substring(0,5)=='1.1.1')
				href='http://zjmegaeyes.zjbnet.com/hbs/index.jsp';
			window.open(href);
		}
</script>
<script type="text/javascript">
function getSimpleName(){
	var orgInternalCode = '<s:property value="#getLoginOrg.organization.orgInternalCode"/>';
    var orgInternalCodeLength = orgInternalCode.length;
    if( orgInternalCodeLength<=6 ){
        var simpleName = '<s:property value="#getLoginOrg.organization.simplePinyin"/>';
         document.getElementById("mapName").href="${resource_path}/index-grs-"+simpleName+".jsp";
    }
}

function isShowView(){
	var orgInternalCode = '<s:property value="#getLoginOrg.organization.orgInternalCode"/>';
    var orgInternalCodeLength = orgInternalCode.length;
    if( orgInternalCodeLength>6 ){
    	$("#viewDiv").hide();
    }
}

</script>
<style>
html{overflow-x:hidden;}
body{background:#5596D7;width:100%;height:100%;margin:0 auto;text-align:center;}
#header{background:url(${resource_path}/resource/images/index/header-bg.jpg) repeat-x;height:64px;width:100%;}
#logo{<s:if test='null!=#request["logoImage"] && !"".equals(#request["logoImage"])'>background:url(<%=logoImage.replace("\\","/")%>)</s:if><s:else>background:url(${resource_path}/resource/images/login/base/login-logo.jpg)</s:else> no-repeat left;float:left;width:416px;height:64px;margin-left:4px;display:inline;}
#login-right{position:absolute;top:0px;right:10px;width:450px;height:64px;}
#login-right li{float:left;line-height:30px;margin:11px 10px 0 0;width:100px;height:37px;display:inline;}
#login-right li a{color:#fff;text-align:left;float:left;text-indent:4px;nofocus:expression(this.onFocus=this.blur());_text-indent:0px;display:block;width:100px;height:37px;}
#login-right li a:hover{background:url(${resource_path}/resource/images/index/index-menu-bg.png) no-repeat;}
#login-right li strong{background-image:url(${resource_path}/resource/images/index/indexico.gif);display:inline-block;float:left;width:16px;height:16px;margin:8px 0 0 15px;}
.geren{background-position:0 -21px;}
.password{background-position:-20px -1px;}
.repassword{background-position:0 -42px;}
.anjian{background-position:-62px 0;}
.system{background-position:-84px 0;}
.exit{background-position:0 -65px;}
.buttonlist{width:145px;height:30px;position:absolute;top:70px;right:10px;}
.buttonlist a{display:block;float:left;border:1px solid #999;color:#000;width:70px;height:21px;line-height:21px;background:url(resource/images/index-button.jpg) no-repeat;text-shadow: 2px 2px 5px #ffffff;Filter: dropshadow(color=#ffffff,offx=1,offy=1);}
.buttonlist a:hover{border:1px solid #333;font-weight:bold;}
.buttonlist a.selected{border:1px solid #333;font-weight:bold;}
.container{position:absolute;top:0px;left:0px;z-index:1111;}
#bottom{background:#2164A4;height:28px;position:absolute;bottom:0px;left:0px;width:100%;color:#fff;}
#bottom p{line-height:28px;text-align:center;}
#slides{margin:0 auto;width:600px;margin-top:40px;position:relative;height:400px;}
#slideshow {margin-top:0;position: relative;padding: 0;list-style: none;width: 526px;height:259px;margin-left:30px;overflow:hidden;}
#slideshow img {border: none;}
.arrow {width: 40px;height: 40px;display: block;position: absolute;top: 40px;text-indent: -9999px;overflow: hidden;cursor: pointer;}

.arrowL {background:url(${resource_path}/resource/images/index/sj-right.png) no-repeat;z-index:10;width:28px;height:46px;display:block;left:-30px;text-indent:-100000px;nofocus:expression(this.onFocus=this.blur())}
.arrowR {background:url(${resource_path}/resource/images/index/sj-left.png) no-repeat;z-index:10;width:28px;height:46px;display:block;right:-30px;text-indent:-100000px;nofocus:expression(this.onFocus=this.blur())}
.arrowL {_background:url(${resource_path}/resource/images/index/sj-right.gif) no-repeat;}
.arrowR {_background:url(${resource_path}/resource/images/index/sj-left.gif) no-repeat;}
.slideView, .thumbList {margin: 0;padding: 0;list-style: none;}
.slidebar {position: absolute;width: 600px;height: 120px;top: 280px;left: 0px;z-index: 8;*padding-bottom:50px;}
.wrap {width: 600px;height: 120px;position: absolute;left: 0px;overflow: hidden;z-index: 6;top:0px;}
.slideView {height: auto;position: relative;top: 0px;left: 0px;overflow: hidden;z-index: 0;}
.slideView li {width: 100%;height:259px;/* the height of the slideshow, change if necessary */float: left;margin: 0;display: inline;}
.slideView li a {display: block;width: 100%;height: 100%;}
#slideshow .desc {text-align:left;line-height:22px;padding-right:30px;margin: 0;width: 290px;   /* the width of the desccription, change if necessary */position: absolute;top: 100px;right: 20px;color: #073C68;overflow: hidden;z-index: 6;font-size: 12px;}
.thumbList {position: absolute;top: 0px;left: 0px;margin-top: 12px;width:600px;overflow:hidden;z-index:999;}
.thumbList li {	width: 120px;height: 120px;display: inline;margin: 0;float: left;}
.thumbList li a {display: block;width: 120px;height: 120px;}
a.item1{background:url(${resource_path}/resource/images/index/jcxx.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/jcxx.gif) no-repeat;display: block;width: 120px;height: 120px;}
a.item2{background:url(${resource_path}/resource/images/index/gztz.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/gztz.gif) no-repeat;display: block;width: 120px;height: 120px;}
a.item3{background:url(${resource_path}/resource/images/index/fwbs.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/fwbs.gif) no-repeat;display: block;width: 120px;height: 120px;}
a.item4{background:url(${resource_path}/resource/images/index/tjfx.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/ypfx.gif) no-repeat;display: block;width: 120px;height: 120px;}
a.item5{background:url(${resource_path}/resource/images/index/jdkh.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/jdkh.gif) no-repeat;display: block;width: 120px;height: 120px;}
a.item6{background:url(${resource_path}/resource/images/index/spjc.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/spjc.gif) no-repeat;display: block;width: 120px;height: 120px;}
a.item7{background:url(${resource_path}/resource/images/index/ycjl.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/ycjl.gif) no-repeat;display: block;width: 120px;height: 120px;}
a.item8{background:url(${resource_path}/resource/images/index/xtgl.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/xtgl.gif) no-repeat;display: block;width: 120px;height: 120px;}
a.item9{background:url(${resource_path}/resource/images/index/szcg.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/szcg.gif) no-repeat;display: block;width: 120px;height: 120px;}
a.item10{background:url(${resource_path}/resource/images/index/djxx.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/djxx.gif) no-repeat;display: block;width: 120px;height: 120px;}
a.item11{background:url(${resource_path}/resource/images/index/hjzx.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/hjzx.gif) no-repeat;display: block;width: 120px;height: 120px;}
a.item12{background:url(${resource_path}/resource/images/index/yjzh.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/yjzh.gif) no-repeat;display: block;width: 120px;height: 120px;}
a.item13{background:url(${resource_path}/resource/images/index/book.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/yjzh.gif) no-repeat;display: block;width: 120px;height: 120px;}
a.item1:hover{background:url(${resource_path}/resource/images/index/jcxx_current.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/jcxx.gif) no-repeat;}
a.item2:hover{background:url(${resource_path}/resource/images/index/gztz_current.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/gztz.gif) no-repeat;}
a.item3:hover{background:url(${resource_path}/resource/images/index/fwbs_current.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/fwbs.gif) no-repeat;}
a.item4:hover{background:url(${resource_path}/resource/images/index/tjfx_current.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/ypfx.gif) no-repeat;}
a.item5:hover{background:url(${resource_path}/resource/images/index/jdkh_current.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/jdkh.gif) no-repeat;}
a.item6:hover{background:url(${resource_path}/resource/images/index/spjc_current.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/spjc.gif) no-repeat;}
a.item7:hover{background:url(${resource_path}/resource/images/index/ycjl_current.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/ycjl.gif) no-repeat;}
a.item8:hover{background:url(${resource_path}/resource/images/index/xtgl_current.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/xtgl.gif) no-repeat;}
a.item9:hover{background:url(${resource_path}/resource/images/index/szcg_current.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/szcg.gif) no-repeat;}
a.item10:hover{background:url(${resource_path}/resource/images/index/djxx_current.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/djxx.gif) no-repeat;}
a.item11:hover{background:url(${resource_path}/resource/images/index/hjzx_current.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/hjzx.gif) no-repeat;}
a.item12:hover{background:url(${resource_path}/resource/images/index/yjzh_current.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/yjzh.gif) no-repeat;}
a.item13:hover{background:url(${resource_path}/resource/images/index/book_current.png) no-repeat;_background:url(${resource_path}/resource/images/index/ie6/yjzh.gif) no-repeat;}
#slideshow .thumbList li a:hover {}
.errorInput{background:#fee;border-color:#DB0027;}
</style>
</head>
<body>
<div id="header">
	<div id="logo"></div>
    <div id="login-right">
    	<ul>
	        <li id ="modifyRoleLi" style = "display:none"><a href="javascript:void(0)" id="modifyRole"><strong  class="ggjs"></strong>更改角色</a></li>
    		<!--
    		 <li><a href="http://192.168.1.245/fileManage/index.html" target="_blank"><strong  class="geren"></strong>资料库</a></li>
    		 -->
    		<li><a href="javascript:void(0)" id="modifyUserInfoButton"><strong  class="geren"></strong>个人信息</a></li>
       		<li><a href="javascript:void(0)" id="modifyPasswordButton"><strong  class="repassword"></strong>修改密码</a></li>
	        <li><a id="exitNew" href="${path}/sessionManage/logout.action?isIndexJsp=true"><strong  class="exit"></strong>退出系统</a></li>
        </ul>
    </div>
</div>



<div class="buttonlist" id="viewDiv" >
<!--
	<a href="${resource_path}/index-grs-zjs.jsp"  id="mapName" onclick="getSimpleName()">GIS视图</a>
	<a href="${resource_path}/index.jsp" class="selected">模块视图</a>
	 -->
</div>

<div id="maintainDlg"></div>


<div id="slides">
		<div id="slideshow">
			<ul class="slideView">
			<pop:JugePermissionTag ename="basicInformation">
			<li id="1"><a href="${path}/module.jsp#basicInformation"><img src="${resource_path}/resource/images/index/jibenxinxi.png" alt="依托综治工作中心（站）和基层群防群治组织收集，建立基层综治信息数据库，为系统其他功能模块提供数据支持，内容包括重点人员、重点场所、规上企业、社会组织及辖区情况等基础数据。" /></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="digitalCityManagement">
				<li id="9"><a href="${path}/module.jsp#digitalCity"><img src="${resource_path}/resource/images/index/shuzichengguan.png" alt="用信息化手段和移动通信技术手段来处理、分析和管理整个城市的所有城管部件和城管事件信息，促进城市管理的现代化的信息化。" /></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="city_party_construction_Management">
			<li id="10"><a href="${path}/partyBuilding/index.jsp?organizationId=<s:property value='#loginAction.user.organization.id'/>" target="_blank"><img src="${resource_path}/resource/images/index/dangjianxinxi.png" alt="加强党的基层组织和党员干部队伍建设为基础，保持党同人民群众的血肉联系为核心，以建设高素质干部队伍，全面加强和改进党的思想、组织、作风和制度建设。" /></a></li>
			</pop:JugePermissionTag>

			<pop:JugePermissionTag ename="dailyLogManage">
			<li id="2"><a href="${path}/module.jsp#workingRecordMenu"><img src="${resource_path}/resource/images/index/gongzuotaizhang.png" alt="记录各级综治办及职能部门的日常工作内容，包括年度工作台账和工作日志。年度工作台账包括会议类、文件类、活动类、矛盾纠纷排查、治安乱点整治、治安重点排查情况等；工作日志记录了工作人员的实际日常工作情况及工作感想等 。" /></a></li>
			</pop:JugePermissionTag>

			<pop:JugePermissionTag ename="serviceWork">
			<li id="3"><a href="${path}/module.jsp#issue"><img src="${resource_path}/resource/images/index/fuwubanshi.png" alt="通过多种渠道获取并记录群众的各方面反应和诉求，按照职能对事件进行分流，由相关部门和人员进行处理解决，全程记录每件事件的受理、处理、办结及反馈评价全过程，增加督办功能，督促下级更好的开展工作。" /></a></li>
			</pop:JugePermissionTag>

			<pop:JugePermissionTag ename="statAnalyseManage">
			<li id="4"><a href="${path}/module.jsp#statAnalyse"><img src="${resource_path}/resource/images/index/tongjifenxi.png" alt="提供一个支持动态多维分析的数据平台，对各种数据进行自定义分析、统计，以表格、图形等多种形式直观展现结果，为领导做决策提供参考依据。" /></a></li>
			</pop:JugePermissionTag>

			<pop:JugePermissionTag ename="evaluateManagement">
			<li id="5"><a href="${path}/module.jsp#evaluate"><img src="${resource_path}/resource/images/index/jiandukaohe.png" alt="以手工打分和系统自动打分相结合模式，根据考核办法编写考核细则及设置考核结果类型。在所属机构自评的基础上，对其进行审查审核，以反映各所属机构的工作成绩，并能查看所属区域的考核情况。" /></a></li>
			</pop:JugePermissionTag>

			<pop:JugePermissionTag ename="callCenterManagement">
			<li id="11"><a href="${path}/module.jsp#callCenter"><img src="${resource_path}/resource/images/index/hujiaozhongxin.png" alt="促进群众和政府之间的沟通，建立起政府与群众的沟通桥梁，能及时为有需求的群众提供帮助和服务，提升群众对政府的满意度。" /></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="peopleLog">
			<li id="13"><a href="${path}/module.jsp#peopleLog"><img src="${resource_path}/resource/images/index/book.png" alt="民情日志而已。" /></a></li>
        	</pop:JugePermissionTag>
        	<pop:JugePermissionTag ename="systemManagement">

        	<li id="12"><a href="${path}/incident/index.jsp"><img src="${resource_path}/resource/images/index/yingjizhihui.png" alt="突发事件的事前预防、事发应对、事中处置和善后管理。保障公众生命财产安全；促进社会和谐健康发展。" /></a></li>

			<li id="8"><a href="${path}/module.jsp#systemManagement"><img src="${resource_path}/resource/images/index/xitongguanli.png" alt="管理和维护使用系统的部门、帐号，严格对权限进行分配管理，并提供系统日志、在线用户等各项系统的配置管理功能。" /></a></li>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="integrativeQueryManagement">
			<li id="11"><a href="${path}/module.jsp#integrativeQuery"><img src="${resource_path}/resource/images/index/hujiaozhongxin.png" alt="综合查询。" /></a></li>
			</pop:JugePermissionTag>
			
			</ul>
		</div>
</div>
<div id="bottom">
	<div id="bottom">
		<p><%=sysBottomPage %></p>
	</div>
</div>
<!--[if IE 6]>
	<script type="text/javascript" src="resource/js/DD_belatedPNG.js" ></script>
	<script type="text/javascript">
	DD_belatedPNG.fix('#login-right li,#login-right strong,.prev,.next,.slideView img,.#login-right li a:hover');
	</script>
<![endif]-->

	<div id="jGrowl"></div>
</body>
</html>