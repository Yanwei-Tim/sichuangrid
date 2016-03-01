<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:action name="ajaxOrganization" var="getLoginOrg" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()"></s:param>
</s:action>
<script type="text/javascript">
function getMessageByUser(show){
	$.ajax({
		url:"${path}/sysadmin/userMessage/findUserMessages.action",
		success:function(data){
			var thisData={
				url:'${path}/sysadmin/userMessage/findUserMessages.action'
				//,notAcceptNote:{title:"未受理短信",link:"javascript:void(0)"
				//	<pop:JugePermissionTag ename="smsManagement">,limits:true </pop:JugePermissionTag>}
				<pop:JugePermissionTag ename="pmInboxManagement">
				,notAcceptOnlineAppeals:{title:"未阅读平台消息",link:"#interactionManagement-pmInboxManagement",limits:true}
				</pop:JugePermissionTag>
				<pop:JugePermissionTag ename="needIssueListManagement,checkPendingIssueListManagement,verificationIssueListManagement,checkGradeIssueListManagement">
				,backlog:{title:"待办事项",link:"#serviceWork-myIssueListManagement",limits:true}
				</pop:JugePermissionTag>
				//,myAuditDelay:{title:"待审核事项",link:"#serviceWork-myIssueListManagement"
				//	<pop:JugePermissionTag ename="serviceWork">,limits:true</pop:JugePermissionTag>}
				,data:data
			}
			if(data.messageNum>99){
				$("#msgNew").html("99+");
			}else{
				$("#msgNew").html(data.messageNum);
			}
			$("#message").workBenchMessageTip(thisData,show);
		}
	});
}
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
function reloadMessageCount() {
	$(".messageButton").trigger('click');
	$(".message").trigger('click');
}

//var highLevel=<s:property value="#getLoginOrg.organization.orgLevel.internalId>@com.tianque.domain.property.OrganizationLevel@DISTRICT"/>;
//var middleLevel=<s:property value="#getLoginOrg.organization.orgLevel.internalId<=@com.tianque.domain.property.OrganizationLevel@DISTRICT&&#getLoginOrg.organization.orgLevel.internalId>@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>;
//var lowLevel=<s:property value="#getLoginOrg.organization.orgLevel.internalId<=@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>;

function baseLoad(that,baseUrl,leaderUrl,gridUrl){
	//通过that截取处理后得到当前模块名称,然后通过模块名称+LeaderView去查询权限
	var moduleName = $("#accordion .cur").attr("id");//得到当前模块的ename
	moduleName = getModuleName(moduleName);//将得到的name处理
	if(moduleName!=undefined && moduleName!="" && moduleName.length!=0){
		//判断当前网格层级 isGrid()网格层级  isCountryDownOrganization()社区层级   isVillage() 村级 只有以上三个层级才做判断
		if(isGrid()){//如果当前层级是网格层级
			moduleName = moduleName.toUpperCase()+"GridLeaderView";
		}else if(isCountryDownOrganization()){
			moduleName = moduleName.toUpperCase()+"VillageLeaderView";
		}else if(isDistrictDownOrganization()){
			moduleName = moduleName.toUpperCase()+"TownLeaderView";
		}
	}else{
		moduleName="";
	}
	
	$.ajax({
		url:'${path}/sysadmin/permissionManage/findUserAllPermissionByUserIdAndPermissionEname.action?name='+moduleName,
		async:true,
		success:function(data){
			if(data){
				if(leaderUrl != undefined && leaderUrl !=''){
					if(isGrid() && gridUrl!=undefined && gridUrl!=''){
						asyncOpen(that,gridUrl);//加载网格层级路径
					}else{
						asyncOpen(that, leaderUrl);
					}
				}else{
					asyncOpen(that, baseUrl);
				}
			}else{
				if(!isDistrictDownOrganization() && leaderUrl != undefined && leaderUrl !=''){
					asyncOpen(that, leaderUrl);
				}else{
					asyncOpen(that, baseUrl);
				}
			}
		}
	});
}

function getModuleName(plateName){
	if(plateName==undefined || plateName=="" || plateName.length==0){
		return;
	}
	var moduleName = "";
	if (plateName.toString().endWith("Management")) {
		moduleName =  plateName.substring(0,
				plateName.indexOf("Management"));
	} else {
		moduleName =  plateName;
	}
	return moduleName;
}

$(function(){


	function messageFun(){//消息弹出组件
		this.process=function(msgNum){
			var messageOption={
				notAcceptNote:{title:"未受理短信",link:"javascript:void(0)"
					<pop:JugePermissionTag ename="smsManagement">,limits:true </pop:JugePermissionTag>}
				,notAcceptOnlineAppeals:{title:"未阅读平台消息",link:"#interactionManagement-pmInboxManagement"
					<pop:JugePermissionTag ename="pmManagement">,limits:true</pop:JugePermissionTag>}
				,backlog:{title:"待办事项",link:"#serviceWork-myIssueListManagement"
					<pop:JugePermissionTag ename="serviceWork">,limits:true</pop:JugePermissionTag>}
				,sessionTimeout: <s:property value='@com.tianque.core.util.GridProperties@SESSION_TIME_OUT'/>
				,data:msgNum
			}
			$("#message").messageTip(messageOption);
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
			if(time==undefined){time=90000};
			Interval=setInterval(function(){
			$.ajax({
				url:'${path}/sysadmin/userMessage/findUserMessagesAndProclamation.action',
				success:function(data){
					for(var i=0;i<myArray.length;i++){
						var thisFun=myArray[i];
						thisFun.process(eval("data."+thisFun.sign));
					}
				}
			})
			},time);
		};
		this.stop=function(){
			clearInterval(Interval);
		};
		return this;
	}

	var supervisor=new componentManager();
	//supervisor.add(new messageFun());
	supervisor.add(new announcementFun());
	supervisor.start();  //fateson 方便测试 注释起来   
	//setInterval("getMessageByUser()",300000);
	$("#message").click(function(){
		getMessageByUser('show');
	});
	$('body').on('mouseleave','.message-tip',function(){
		$(this).remove();
	})

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
				document.location.href=url;
			}
		});
		return false;
	});

	var isAdminForJudgeModifyRole = '<s:property value="@com.tianque.core.util.ThreadVariable@getUser().isAdmin()"/>';
	var UserNameForJudgeModifyRole = '<s:property value="@com.tianque.core.util.ThreadVariable@getUser().getUserName()"/>';
		if(UserNameForJudgeModifyRole == "admin"||isAdminForJudgeModifyRole=='true'||$.cookie('oldSid')!=null){
			$("#modifyRoleLi").show();
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
	// info+= " <li>上次登录IP是:<br/>";
	//info+= '<s:property value="@com.tianque.core.util.ThreadVariable@getUser().previousLoginIp" />'; 
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

		$("#settingInfoPic,#settingInfo").click(function(){
			$("#settingDialog").createDialog({
				width:600,
				height:'auto',
				title:"设置",
				url:"${path}/workBench/setting.jsp",
				buttons: {
					"保存" : function(event){
					var selected=$( "#tabs").tabs('option', 'selected');
						if(selected==0){
							$("#updateDetailsForm").submit();
						}else if(selected==1){
							$("#firstUpdatePassForm").submit();
						}else if(selected==2){
							$("#userHasPlatformMessageTypeForm").submit();
						}
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
				height:200,
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
	        if($.browser.msie && $.browser.version<'8.0'){
	        	document.location.reload();
	        }else{
	        	$("#switchSkin").attr("href","/resource/system/css/switchSkin-"+thisColor+".css");
	        }
	    })


	    function bigStyle(size){
		    $.jStorage.set("bigFontStyle",size);
	        $("#bigFontStyle").remove();
			$("head").append('<style id="bigFontStyle">*{font-size:'+size+'px !important;}</style>');
		}
		//bigStyle($.jStorage.get("bigFontStyle"));

	    $("#loopTypeFaceCont a").click(function(){
		    var size=$(this).attr("size");
	    	bigStyle(size);
	    });

		function MultiLevelMenu(){
			var self=$("#settingInfoCont");

            self.find("ul li").hover(function(){
                $(this).find("ul:first").show().end()
                    .find("#functionInfo").addClass("backCur");
					$(".message-tip").css("display","none");

            },function(){
                $(this).find("ul:first").hide().end()
                    .find("#functionInfo").removeClass("backCur");
            }).end()
            .find(".functionCont li").each(function(){
                $(this).find("li").click(function(){
                    $(this).addClass("funcCurrent").siblings().removeClass("funcCurrent");
                    $(this).parent().parent().addClass("funcCurrent").siblings().removeClass("funcCurrent");
                });
            })
	    }
	    MultiLevelMenu();
	
	//下拉菜单
	$.navDropdownBtn();

	$("#showVersion").click(function(){
		$.versionIntroduction();
	})
})
	$.getCurrentOrgId=function(){
		return $("#currentOrgId").val();
	}
	$.getCurrentOrgName=function(){
		return $("#currentOrgId").attr("text");
	}
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