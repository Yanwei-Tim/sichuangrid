<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"></s:param>
</s:action>
<div id="maintainDlg_judgmentAnalysis"></div>
<script type="text/javascript">

function alterTheTitle(){
	var orgName = $("#provinceName").val();
	var initCountry = '<s:property value="#getTitleProvinceName.initCountry" />';
	var userOrganizationDepartmentNo = '<s:property value="#getLoginOrg.organization.departmentNo" />';
	if(initCountry){
		//判断userOrganizationDepartmentNo的长度,当长度大于2的时候证明为浙江省组组织机构下的用户登录
		if(userOrganizationDepartmentNo.length > 2){
			$("#navigation-title").text(orgName + $("#navigation-title").text());
		}
	}else{
		//
		var rootDepartmentNo = '<s:property value="#getTitleProvinceName.organization.departmentNo" />';
		if(userOrganizationDepartmentNo < rootDepartmentNo){
			//在前面加上浙江省
			$("#navigation-title").text(orgName + $("#navigation-title").text());
		}
	}
}
function reloadMessageCount() {
	$(".messageButton").trigger('click');
	$(".message").trigger('click');
}

var highLevel=<s:property value="#getLoginOrg.organization.orgLevel.internalId>@com.tianque.domain.property.OrganizationLevel@DISTRICT"/>;
var middleLevel=<s:property value="#getLoginOrg.organization.orgLevel.internalId<=@com.tianque.domain.property.OrganizationLevel@DISTRICT&&#getLoginOrg.organization.orgLevel.internalId>@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>;
var lowLevel=<s:property value="#getLoginOrg.organization.orgLevel.internalId<=@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>;
function showPageByTopMenu(topMenu){
	if(topMenu=='index'){
		$("#breadcrumbTrail").empty();
	}else{
		$("#breadcrumbTrail").empty().html('<div class="thisCrumbs" id="thisCrumbs">	<div class="orgSelectComponent" id="orgSelectComponentBtn"><strong>当前辖区：</strong><a href="javascript:;" class="globalOrgBtnMod" id="globalOrgBtnMod"><span id="globalOrgBtn"></span></a></div> </div>');
		$("#globalOrgBtnMod").unbind("click").bind("click",function(){
			$("#globalOrgBox").createDialog({
				url:'${path}/sysadmin/orgManage/orgSelectComponent.action?id='+$.getCurrentOrgId(),
				width:300,
				position: { my: "left top", at: "left bottom", of: $("#orgSelectComponentBtn") },
				height:'auto',
				title:'辖区选择',
				create:function(event,ui){
					$(event.target).closest(".ui-dialog").attr("id","globalOrgBoxDlg");
				},
				open:function(event,ui){
					$("#orgSelectComponentBtn").addClass("cur");
				},
				close:function(){
					$("#orgSelectComponentBtn").removeClass("cur");
				},
				modal:false,
				buttons:false
			});
		})
	}
	var menuType;
	if(topMenu.indexOf("-")!=-1){
		menuType=topMenu.substr(topMenu.indexOf("-")+1);
		topMenu=topMenu.substring(0,topMenu.length-menuType.length-1);
	}
	
	
	if(topMenu=='' || topMenu==window.location.href){
		topMenu="gridManageSystem";
	}
	var selectedOrgId="<s:property value='#parameters.selectedOrgId'/>";
	var typeName=$(".ui-tabs-selected").text();
	$("#contentDiv").empty();
	try
	{
		$.selectMenu(topMenu+"-menu");
		if(topMenu=='index'){
			//获取切换模式
			var workBenchMainparam="";
			if(isSwitchover){
				workBenchMainparam="?switchover=0";
			}
			$(".ui-layout-center").html('<div id="msg-tips-index" class="tipsbox"><p id="loading" class="loadbox"><img src="${resource_path}/resource/workBench/images/loading_comment.gif" alt="loading">正在获取信息，请稍候...</p></div>')
			.load("/login/judgmentAnalysis/workBenchMain.jsp"+workBenchMainparam);
		}else{
			$(".ui-layout-west").show().load("/login/judgmentAnalysis/left.jsp?flag=show&urlflag="+menuType,function(){
				$(".slideResizer").show();
			}); 
		}
	}
	catch(err)
	{
		$(".dialog_loading").hide();
		$.messageBox({message:'系统出错，请刷新页面重试',level:'error'});
		throw new Error(err);
	}
}
function baseLoad(that,baseUrl,leaderUrl){
	$("#thisCrumbs").hide();
	if(typeof orgCallbackFun=="function"){
		orgCallbackFun=null;
	}
	orgCallbackFun=null;
	if(!isDistrictDownOrganization() && leaderUrl != undefined && leaderUrl !=''){
		asyncOpen(that, leaderUrl);
	}else{
		asyncOpen(that, baseUrl);
	}
}
$(function(){
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
		},300);
	},function(){
		clearTimeout(window._menuShowTimer);
		window._menuHideTimer=setTimeout(function(){
			$(".sysMenuList").fadeOut(200);
			$(".message-tip").removeAttr("style");
		},300);
	});
	$(".sysMenuList a").click(function(){
		var rel=$(this).attr("rel");
		rel && showPageByTopMenu(rel);
	});

	$("#menu a").click(function(){
		$("#menu a").css({'color':'#ffffff'});
		$(this).css({'color':'#FFEB00'});
		var rel=$(this).attr("rel");
		rel && showPageByTopMenu(rel);
	});

	$("#otherMenuItems ul").on('click','a',function(){
		$("#otherMenuItems ul a").css({'color':'#ffffff'});
		$(this).css({'color':'#FFEB00'});
		
		var rel=$(this).attr("rel");
		rel && showPageByTopMenu(rel);
	});
	
	$("#ql_home").click(function(){
		$("#menu a").css({'color':'#ffffff'});
		$("#otherMenuItems ul a").css({'color':'#ffffff'});
	});
	
	$("#message").bind('click',function(){
		if( !( $(".message-tip")[0]) ){
		}else{

			$(".message-tip").remove();
		}
		
	})
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
			if(time==undefined){time=60000};
			Interval=setInterval(function(){
			$.ajax({
				url:'${path}/sysadmin/userMessage/findUserMessagesAndProclamation.action',
				success:function(data){
					for(var i=0;i<myArray.length;i++){
						var thisFun=myArray[i];
						thisFun.process(eval("data."+thisFun.sign));
					}
				}
			});
			},time);
		};
		this.stop=function(){
			clearInterval(Interval);
		};
		return this;
	}

	var supervisor=new componentManager();
	supervisor.add(new announcementFun());
	supervisor.start();

	$('#exit').click(function(){
		var url=$(this).attr("href");
		$.confirm({
			title:"确认退出",
			message:"确定要退出吗?",
			okFunc: function() {
				var announcementName="announcement";
				var vlue = $.cookie(announcementName);
				if(vlue!=null){
					$.cookie(announcementName,null, { path: '/', expires: 10 });
				}
				function goto2(){
				        if(document.all){
				                document.write("<a id='goa' href='"+url+"' style='display:none;'>a</a>");
				                document.getElementById("goa").click();
				        }else{
				                window.location=url;
				        }
				}
				goto2();
			}
		});
		return false;
	});

	var isAdminForJudgeModifyRole = '<s:property value="@com.tianque.core.util.ThreadVariable@getUser().isAdmin()"/>';
	var UserNameForJudgeModifyRole = '<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getUserName()"/>';
		if(UserNameForJudgeModifyRole == "admin"||isAdminForJudgeModifyRole=='true'||$.cookie('oldSid')!=null){
			$("#modifyRoleLiMain").show();
		}
	<s:if test="@com.tianque.core.util.ThreadVariable@getUser().previousLoginTime!=null">
	var loginTime = '<s:date name="@com.tianque.core.util.ThreadVariable@getUser().previousLoginTime" format="yyyy-MM-dd" />';
	var loginYear = loginTime.substring(0,loginTime.indexOf ('-'));
	var loginMonth = loginTime.substring(5,loginTime.lastIndexOf ('-'));
	var loginDate = loginTime.substring(loginTime.length,loginTime.lastIndexOf ('-')+1);
	var currentTime = new Date();
	var curYear = currentTime.getFullYear();
	var curMonth = currentTime.getMonth()+1;
	var curDate = currentTime.getDate();
	var day = ((Date.parse(curMonth+'/'+curDate+'/'+curYear)- Date.parse(loginMonth+'/'+loginDate+'/'+loginYear))/86400000);

	var info = "<li>上次登录时间是:<br/>";
	info+= '<s:date name="@com.tianque.core.util.ThreadVariable@getUser().previousLoginTime" format="yyyy-MM-dd HH:mm:ss" />' ;
	info+= "</li>";
	info+= " <li>上次登录IP是:<br/>";
	info+= '<s:property value="@com.tianque.core.util.ThreadVariable@getUser().previousLoginIp" />';
	info+= "</li>";

	if(day>0){
		info+= "<li>您已有"+day+"天没有登录系统</li>";
	}
	info+= "<li>当前用户所在的组织机构是:<br/>";
	info+= '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().fullOrgName" />';
	info+= "</li>";
	</s:if>
	<s:else>
	var info = "<li>这是您第一次登陆系统</li>";
	info+= "<li>当前用户所在的组织机构是:<br/>";
	info+= '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="@com.tianque.core.util.ThreadVariable@getOrganization().fullOrgName" />';
	info+= "</li>";
	</s:else>
	$("#personelInfo").hover(
			function () {
				$("#personelInfo").dialogtip({
					content:info
				});
				$("#personelInfo").poshytip('show');
			},
			function () {
			    $("#personelInfo").poshytip('hide');
			}
		);

		$("#modifyRoles").click(function(){
			$("#maintainDlg_judgmentAnalysis").createDialog({
				width: 360,
				height:'auto',
				title:'更改角色',
				url:'${path}/modifyRoleDlg.jsp',
				buttons: {
					"确定" : function(event){
						$("#modifyRoleForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
	    $("#loopSkinCont a").click(function(){
	    	var thisColor=$(this).attr("value");
	        $.jStorage.set("skinColor",thisColor);
	        if($.browser.msie && $.browser.version<='8.0'){
	        	document.location.reload();
	        }else{
	        	$("#switchSkin").attr("href","/resource/system/css/switchSkin-"+thisColor+".css");
	        }
	    });


	    function bigStyle(size){
		    $.jStorage.set("bigFontStyle",size);
	        $("#bigFontStyle").remove();
			$("head").append('<style id="bigFontStyle">*{font-size:'+size+'px !important;}</style>');
		}
		bigStyle($.jStorage.get("bigFontStyle"));

	    $("#loopTypeFaceCont a").click(function(){
		    var size=$(this).attr("size");
	    	bigStyle(size);
	    });

		function MultiLevelMenu(){
			var self=$(".cont");
			var timer;
			function displayMultiLevelMenu(){
				self.find("ul li").hover(function(){
					$(this).find("ul:first").stop(true,true).slideDown(150);
					$(this).find("#functionInfo").addClass("backCur");
					$(".message-tip").css("display","none");

				},function(){
					$(this).find("ul:first").slideUp(150);
					$(this).find("#functionInfo").removeClass("backCur");
					$(".message-tip").css("display","block");
				});

				$(".functionCont li").each(function(){
					$(this).find("li").click(function(){
						$(this).addClass("funcCurrent").siblings().removeClass("funcCurrent");
						$(this).parent().parent().addClass("funcCurrent").siblings().removeClass("funcCurrent");
					});
				});

			}
			displayMultiLevelMenu();
	    }
	    MultiLevelMenu();
	
	//下拉菜单
	$.navDropdownBtn();

	$("#showVersion").click(function(){
		$.versionIntroduction();
	})
	$.getCurrentOrgId=function(){
		return $("#currentOrgId").val();
	}
	$.getCurrentOrgName=function(){
		return $("#currentOrgId").attr("text");
	}
});
	function isGrid(){
		return $("#currentOrgId").attr("orgLevelInternalId") == <s:property value="@com.tianque.domain.property.OrganizationLevel@GRID"/>;
	}
	function isCountryDownOrganization(){
		return $("#currentOrgId").attr("orgLevelInternalId") <= <s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>;
	}
	function isDistrictDownOrganization(){
		return $("#currentOrgId").attr("orgLevelInternalId") < <s:property value="@com.tianque.domain.property.OrganizationLevel@DISTRICT"/>;
	}
	function isTownDownOrganization(){
		return $("#currentOrgId").attr("orgLevelInternalId") < <s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>;
	}
	function isVillage(){
		return $("#currentOrgId").attr("orgLevelInternalId") == <s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>;
	}
	function isTown(){
		return $("#currentOrgId").attr("orgLevelInternalId") == <s:property value="@com.tianque.domain.property.OrganizationLevel@TOWN"/>;
	}
	function isDistrict(){
		return $("#currentOrgId").attr("orgLevelInternalId") == <s:property value="@com.tianque.domain.property.OrganizationLevel@DISTRICT"/>;
	}
	function isCity(){
		return $("#currentOrgId").attr("orgLevelInternalId") == <s:property value="@com.tianque.domain.property.OrganizationLevel@CITY"/>;
	}
	function isProvince(){
		return $("#currentOrgId").attr("orgLevelInternalId") == <s:property value="@com.tianque.domain.property.OrganizationLevel@PROVINCE"/>;
	}
	function isCountry(){
		return $("#currentOrgId").attr("orgLevelInternalId") == <s:property value="@com.tianque.domain.property.OrganizationLevel@COUNTRY"/>;
	}
</script>