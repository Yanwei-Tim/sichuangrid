<!DOCTYPE html>
<html>
<head>
	<#assign language="java" pageEncoding="UTF-8">
	<#assign pop=JspTaglibs["/WEB-INF/taglib/pop-taglib.tld"]>
	<#assign s=JspTaglibs["/WEB-INF/taglib/struts-tags.tld"]>
	<title>十户联防运营管理系统</title>
	<@s.include value="/includes/baseInclude.jsp" />
	<@s.include value="/includes/jsInclude.jsp" />
	<@s.include value="/uba/includes.jsp" />
	<@s.include value="/includes/baseJs.jsp" />
</head>
<body>
	<input type="hidden" id="isSwitchover" value="<@s.property value='#parameters.isSwitchover'/>">
	<div id="loadingMain" style="top: 20%; width:300px; height: 100px; left: 40%; position: absolute;">
		<div id="mainProgressbar" style="height: 30px;"></div>
		<div id="mainProgressbarText" style="color:#000; text-align: center; height:20px; line-height:20px;top: 6px; width:100%; font-size: 12px; left: 10px; position: absolute;">正在准备数据，请稍后...</div>
	</div>
	<script type="text/javascript">
			$("#mainProgressbar").progressbar({value: 100});
			$("#mainProgressbarText").css({'color':'#fff'}).text('加载完成！跳转中...');
	</script>
	<div class="ui-layout-north">
		<input type="hidden" id="CURRENT_SYSTEM_NAME" user="<@s.property value="@com.tianque.core.util.ThreadVariable@getUser().getUserName()"/>" orgName="<@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgName()"/>" value="<@s.property value="@com.tianque.core.util.GridProperties@CURRENT_PROJECT" />">
		<input type="hidden" id="currentOrgId"
		 <@s.if test="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgType().getInternalId()==@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG">
		 	 orglevelinternalid="<@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().getParentOrg().getOrgLevel().getInternalId()" />" 
			 text="<@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().getParentOrg().getOrgName()" />" 
			 leafNum="<@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().getParentOrg().getSubCount()" />" 
			 value='<@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().getParentOrg().getId()"/>' />
		 </@s.if>
		 <@s.else>
		 	orglevelinternalid="<@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgLevel().getInternalId()" />" 
		 	text="<@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().getOrgName()" />" 
			leafNum="<@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().getSubCount()" />" 
		 	value='<@s.property value="@com.tianque.core.util.ThreadVariable@getOrganization().getId()"/>' 
		 </@s.else>
		 />
		 <div class="systemHeader">
			<div class="logo-container">
				<div style="padding-top: 10px; padding-left: 0px;font-size: 25px !important; color: #FFFFFF; font-weight: bold; dispalay: none;" name="BASE_SYSTEM_HEAD_DIV" id="_headedTitleDiv"></div><div></div>
				<input type="hidden" id="xcLogo" value="<@s.property value='@com.tianque.core.util.ThreadVariable@getOrganization().getOrgName()'/>">
			</div>
		    <div class="login-window" style="right: 0px;">
		    	<@s.if test="null!=@com.tianque.core.util.ThreadVariable@getUser().getHeaderUrl()">
		    		<div class="pic" id="settingInfoPic" title="个人设置"><img src="<@s.property value="@com.tianque.core.util.ThreadVariable@getUser().getHeaderUrl()"/>" alt="" /></div>
		    	</@s.if>
		    	<@s.else>
			        <div class="pic" id="settingInfoPic" title="个人设置"><img src="${resource_path }/resource/system/images/blank.png" alt="" /></div>
		    	</@s.else>
		        <div class="cont" id="settingInfoCont">
		            <div class="personal"><span id="personelInfo">欢迎您！<@s.property value="@com.tianque.core.util.ThreadVariable@getUser().getName()"/></span><span id="modifyRoleLi" style = "display:none"></span>
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
		            			<li class="func"><strong class="setC fontC"></strong><span>字号设置</span>
		            				<ul class="list hidden" id="loopTypeFaceCont">
		            					<li><a size="12" href="javascript:;">小字号</a></li>
		            					<li><a size="14" href="javascript:;">大字号</a></li>
		            				</ul>
		            			</li>
		            		</ul>
		            	</li>
					</ul>
		        </div>
		    </div>
		    <ul class="header-right-toolMenu">
				<li title="主页"><a href="#index" onclick="showPageByTopMenu('tenHouseholdsJointManagement')" class="user-config" id="config"></a></li>
			</ul>
		    <div class="switchSkin_red_rightBg"></div>
		</div>
		<div id="shouldLogin"></div>
		<div id="settingDialog"></div>
		<div id="maintainDlg"></div>
	</div>
	
	<div class="ui-layout-west"></div>
	<div class="ui-layout-center">
		<div class="slideResizer"><div class="slideToggler" title="缩进"></div></div>
		<@s.include value="/includes/breadcrumbTrail.jsp" />
		<div class="content">
			<div id="loading" style="display: none;color:#999;text-align:center;height:32px;line-height:32px;margin-top:200px;"><img
				src="${resource_path}/resource/images/loading.gif" alt="加载中..." style="vertical-align:middle;margin-right:5px;" />加载中，请稍候...</div>
			<div id="contentDiv"></div>
		</div>
	</div>
	<div class="ui-layout-south">
		<div id="bottom">
			<@s.include value="/includes/footer.jsp" />
		</div>
	</div>
	<div id="jGrowl"></div>
	<div id="baseLine"></div>
	<div id="globalOrgBox"></div>
<script type='text/javascript'>
	$("#loadingMain").remove();
	showPageByTopMenu("tenHouseholdsJointManagement");
	function showPageByTopMenu(topMenu){
		var menuType="indexCondition";
		if(topMenu.indexOf("-")!=-1){
			menuType=topMenu.substr(topMenu.indexOf("-")+1);
			topMenu=topMenu.substring(0,topMenu.length-menuType.length-1);
		}
		topMenu="tenHouseholdsJointManagement";
		var typeName=$(".ui-tabs-selected").text();
		$("#contentDiv").empty();
		try{
			$.selectMenu(topMenu+"-menu");
			$(".ui-layout-center").css({
				width:document.documentElement.clientWidth-$(".ui-layout-west").width()-10
			})
			$(".ui-layout-west").show().load("/sysadmin/menuManage/getLeftMenuListToJson.action?ename="+topMenu+"&urlflag="+menuType,function(){
				$("#thisCrumbs,.slideResizer").show();
				$("#leftMenuList #indexCondition").hide();
			});
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
		$.ajax({
			url:"${path}/sysadmin/userMessage/findUserMessages.action",
			success:function(data){
				if(data.messageNum>99){
					$("#msgNew").html("99+");
				}else{
					$("#msgNew").html(data.messageNum);
				}
			}
		});
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
				$("#_headedTitleDiv").html("十户联防运营管理系统");
			});
		} // secondaryCallback
		
		var getHash= function(window) {
	        var match = (window || this).location.href.match(/#(.*)$/);
	        return match ? match[1] : '';
	    }
		var localhostHash=getHash(window);
		showPageByTopMenu(localhostHash);
		$(".sysMenuList a").click(function(){
			var rel=$(this).attr("rel");
			if(rel!=''){
				showPageByTopMenu(rel);
			}
			$('#sysMenuList').fadeOut(200);
		});
	});
	setInterval("getReceiveBoxConditionNum()",30000);
	function getReceiveBoxConditionNum(){
		$.ajax({
			url:"${path}/tenHouseholdsJoint/receiveMsgInfo/getReceiveBoxConditionNum.action",
			success:function(data){
				if(data>0){
					$("#receiveBoxNum").remove();
					$("#thisCrumbs").append('<div id="receiveBoxNum" style="position: absolute;top: 10px;right: 11px;"><@pop.JugePermissionTag ename="receiveBoxCondition"><a href="javascript:" onclick="jumpToReceiveBoxCondition()"><font color="red">收到新的警情信息，请注意查看！！</font></a></@pop.JugePermissionTag></div>');
				}else{
					$("#receiveBoxNum").remove();
				}
			}
		});
	}
	function jumpToReceiveBoxCondition(){
		$("#receiveBoxCondition").click();
	}
</script>
</body>
</html>