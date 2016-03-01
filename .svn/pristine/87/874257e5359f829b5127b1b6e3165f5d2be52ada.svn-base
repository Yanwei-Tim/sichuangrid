<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<style>
.issueLeftMenuList{overflow-y:auto;}
.orgMenuList{padding:5px 0 5px 5px;}
.orgMenuList li{margin:0 5px 5px;}
.orgMenuList li a{display:block;background:url(/resource/system/images/newIssue/icon1.png) no-repeat;width:178px;height:34px;text-align:center;font:normal 16px/34px 'microsoft yahei' !important;}
.orgMenuList li a.cur{font-weight:bold !important;color:red;}
.orgMenuList li dl{display:none;border:1px solid #ccc;border-top:0;padding:5px 0;width:176px;max-height:150px;overflow:hidden;overflow-y:auto;}
.orgMenuList li dd a{display:block;background:none;text-align:left;text-indent:22px;font-size:14px !important;}

.issueLeftAccordion{border-top:1px solid #CACCCB;padding:10px 0 0 5px;}
.bottomMenu{}
.bottomMenu li{margin:0 5px 5px;}
.bottomMenu li a{display:block;margin:0 auto;background:url(/resource/system/images/newIssue/icon2.png) no-repeat;width:158px;height:31px;text-align:left;text-indent:7px;}
.bottomMenu li a span{font:normal 12px/31px 'microsoft yahei' !important;}
.bottomMenu li a.cur span{font-weight:bold !important;color:red;}
.bottomMenu li strong.issueicon{display:inline-block;*display:inline;*zoom:1;vertical-align:middle;margin-right:3px;width:22px;height:22px;background:url(/resource/system/images/newIssue/icon3.png) no-repeat;}
.bottomMenu li strong.issueiconhx{display:inline-block;*display:inline;*zoom:1;vertical-align:middle;margin-right:3px;width:22px;height:22px;background:url(/resource/system/images/newIssue/hx.png) no-repeat;}
.bottomMenu li strong.issueiconfl{display:inline-block;*display:inline;*zoom:1;vertical-align:middle;margin-right:3px;width:22px;height:22px;background:url(/resource/system/images/newIssue/fl.png) no-repeat;}
.bottomMenu li strong.issueiconrc{display:inline-block;*display:inline;*zoom:1;vertical-align:middle;margin-right:3px;width:22px;height:22px;background:url(/resource/system/images/newIssue/rc.png) no-repeat;}
.bottomMenu li strong.msfw{background-position:0 0;}
.bottomMenu li strong.mdjf{background-position:0 -32px;}
.bottomMenu li strong.zajk{background-position:0 -67px;}
.bottomMenu li strong.tsrq{background-position:0 -101px;}
.bottomMenu li strong.sqmy{background-position:-2px -134px;}
.bottomMenu li strong.zcfg{background-position:-1px -167px;}
.bottomMenu li strong.tfxsj{background-position:-3px -201px;}
.bottomMenu li strong.qt{background-position:-1px -237px;}

.issueTitle{background:url(/resource/system/images/newIssue/title_bg.png) repeat-x;border-bottom:1px solid #CACCCB;color:#333;height:40px;line-height:40px;text-align:center;font:normal 16px/40px 'microsoft yahei' !important;}

.issueLeftMenuListAcc{}
.issueLeftMenuListAcc h3.title{height:32px;line-height:32px;}
.issueLeftMenuListAcc h3.title a{display:block;margin-left:10px;text-indent:10px;height:32px;color:#fff;font:normal 16px/32px 'microsoft yahei' !important;}
.issueLeftMenuListAcc .accCtt{padding:5px 0;}
.issueLeftMenuListAcc .accCtt li a{display:block;text-indent:36px;height:28px;font:normal 14px/28px 'microsoft yahei' !important;}
#issueBtn{display:block;}
.common-menu-button{display:none;}
</style>
<div class="issueLeftMenuList issueMenu">
	<input type="hidden" name="issueOrgLevel" id="issueOrgLevel" />
	<input type="hidden" name="issueOrgId" id="issueOrgId" />
	<input type="hidden" id="jurisdictionsIssueType" />
	<!--  <h3 class="issueTitle">职能部门</h3> -->
	<div class="orgMenuList" id="orgMenuList">
		
		<s:if test="#getFullOrgById.organization.orgType.internalId==@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG">
			<ul>
				<li>
					<a href="javascript:;" orglevel="<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>" onclick="loadIssueType()" baseUrl="${path }/issue/issueManage/issueViewTabList.jsp?orgLevel=<s:property value='#getFullOrgById.organization.orgLevel.internalId'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>&functionalOrgType=<s:property value='#getFullOrgById.organization.functionalOrgType.id'/>"><s:property value='#getFullOrgById.organization.orgName'/></a>
				</li>
			</ul>
		</s:if>
		<s:if test="#getFullOrgById.organization.orgType.internalId==@com.tianque.domain.property.OrganizationType@ADMINISTRATIVE_REGION">
			<ul>
				<s:if test="#getFullOrgById.organization.orgLevel.internalId >= @com.tianque.domain.property.OrganizationLevel@PROVINCE">
				<li>
					<a href="javascript:;" orglevel="<s:property value='@com.tianque.domain.property.OrganizationLevel@PROVINCE'/>" onclick="loadIssueType()" baseUrl="${path }/issue/issueManage/issueViewTabList.jsp?orgLevel=<s:property value='@com.tianque.domain.property.OrganizationLevel@PROVINCE'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>">省级</a>
					<dl id="province"></dl>
				</li>
				</s:if>
				<s:if test="#getFullOrgById.organization.orgLevel.internalId >= @com.tianque.domain.property.OrganizationLevel@CITY">
				<li>
					<a href="javascript:;" orglevel="<s:property value='@com.tianque.domain.property.OrganizationLevel@CITY'/>" onclick="loadIssueType()" baseUrl="${path }/issue/issueManage/issueViewTabList.jsp?orgLevel=<s:property value='@com.tianque.domain.property.OrganizationLevel@CITY'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>">地市</a>
					<dl id="city"></dl>
				</li>
				</s:if>
				<s:if test="#getFullOrgById.organization.orgLevel.internalId >= @com.tianque.domain.property.OrganizationLevel@DISTRICT">
				<li>
					<a href="javascript:;" orglevel="<s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT'/>" onclick="loadThreeUniteWound()" baseUrl="${path }/issue/issueManage/issueViewTabList.jsp?orgLevel=<s:property value='@com.tianque.domain.property.OrganizationLevel@DISTRICT'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>">县（市、区）</a>
					<dl id="district"></dl>
				</li>
				</s:if>
				<s:if test="#getFullOrgById.organization.orgLevel.internalId >= @com.tianque.domain.property.OrganizationLevel@TOWN">
				<li><a href="javascript:;" orglevel="<s:property value='@com.tianque.domain.property.OrganizationLevel@TOWN'/>" onclick="loadThreeUniteWound()" baseUrl="${path }/issue/issueManage/issueViewTabList.jsp?orgLevel=<s:property value='@com.tianque.domain.property.OrganizationLevel@TOWN'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>">乡镇（街道）</a>
					<dl id="town"></dl>
				</li>
				</s:if>
				<s:if test="#getFullOrgById.organization.orgLevel.internalId >= @com.tianque.domain.property.OrganizationLevel@VILLAGE">
				<li><a href="javascript:;" orglevel="<s:property value='@com.tianque.domain.property.OrganizationLevel@VILLAGE'/>" onclick="loadThreeUniteWound()" baseUrl="${path }/issue/issueManage/issueViewTabList.jsp?orgLevel=<s:property value='@com.tianque.domain.property.OrganizationLevel@VILLAGE'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>">村（社区）</a>
					<dl id="village"></dl>
				</li>
				</s:if>
				<s:if test="#getFullOrgById.organization.orgLevel.internalId >= @com.tianque.domain.property.OrganizationLevel@GRID">
				<li><a href="javascript:;" orgId="<s:property value='#getFullOrgById.organization.id'/>" orglevel="<s:property value='@com.tianque.domain.property.OrganizationLevel@GRID'/>" onclick="loadIssueType()" baseUrl="${path }/issue/issueManage/issueViewTabList.jsp?orgLevel=<s:property value='@com.tianque.domain.property.OrganizationLevel@GRID'/>&keyId=<s:property value='#getFullOrgById.organization.id'/>">网格</a></li>
			</s:if>
		</ul>
		</s:if>
	</div>
	<div id="issueTypeAccordion" class="issueLeftAccordion">
		<div id="clickThreeUniteWound" class="bottomMenu">
			<ul>
				<li><a href="javascript:;" id="partFlow"  baseUrl="" ><strong class="issueiconfl msfw"></strong><span>事件分流处理</span></a></li>
				<li><a id="pacificUnionFounding" href="javascript:;"  onclick="reloadPacificUnionFoundingList('village')" ><strong class="issueiconhx msfw"></strong><span>平安和谐三联创</span></a></li>
				<li><a href="javascript:;"  baseUrl="${path }/issue/supervisionManagement/supervisionAdministration.jsp" ><strong class="issueiconrc msfw"></strong><span>日常监督管理</span></a></li>
			</ul>
		</div>
		<div id="clickIssueType" class="bottomMenu">
			<ul>
				<li><a href="javascript:;" onclick="reloadJurisdictionsList('socialConditions')" ><strong class="issueicon sqmy"></strong><span>社情民意收集</span></a></li>
				<li><a href="javascript:;" onclick="reloadJurisdictionsList('contradiction')"  ><strong class="issueicon msfw"></strong><span>民生服务</span></a></li>
				<li><a href="javascript:;" onclick="reloadJurisdictionsList('resolveTheContradictions')" ><strong class="issueicon mdjf"></strong><span>矛盾劝解</span></a></li>
				<li><a href="javascript:;" onclick="reloadJurisdictionsList('securityPrecautions')" ><strong class="issueicon zajk"></strong><span>参与治安防控</span></a></li>
				<li><a href="javascript:;" onclick="reloadJurisdictionsList('specialPopulations')" ><strong class="issueicon tsrq"></strong><span>参与特殊人群服务管理</span></a></li>
				<li><a href="javascript:;" onclick="reloadJurisdictionsList('policiesAndLaws')" ><strong class="issueicon zcfg"></strong><span>政策法律宣传</span></a></li>
				<li><a href="javascript:;" onclick="reloadJurisdictionsList('emergencies')" ><strong class="issueicon tfxsj"></strong><span>突发事件报告</span></a></li>
				<li><a href="javascript:;" onclick="reloadJurisdictionsList('otherManage')" ><strong class="issueicon qt"></strong><span>其它</span></a></li>
			</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
var orglevel = '<s:property value="#getFullOrgById.organization.orgLevel.internalId"/>';
var isAuditDelay = '<s:property value="#parameters.isAuditDelay"/>'
	$(function() {
		/*
		if(!$(".issueButton")[0]){
			var issueBtn=$('<a href="javascript:;" class="issueButton" id="issueBtn">开始办公</a>');
			var startWork=$('<a href="javascript:;" class="issueButton" id="startWork">返回上级</a>');
			issueBtn.unbind().click(function(){
				$(".ui-layout-west").show().load("/sysadmin/menuManage/getLeftMenuList.action?ename=serviceWork",function(){
					$("#thisCrumbs,.slideResizer").show();
					$(".issueButton").hide();
					$("#startWork").unbind().show().click(function(){
						$(".issueButton").hide();
						$("#issueBtn").show();
						showPageByTopMenu("serviceWork");
					})
				});
			})
			$(".ui-layout-center").append(issueBtn).append(startWork);
		}
		*/
		$("#orgMenuList li>a").click(function(){//层级点击事件
			reloadJurisdictionsList("");
			$(".bottomMenu li a").removeClass("cur");
			$("#issueTypeAccordion").data("selectedIssueType","");
			var baseUrl=$(this).attr("baseUrl");
			$("#orgMenuList li>a").removeClass("cur");
			$("#orgMenuList dl").hide().find("a").removeClass("cur");
			$(this).addClass("cur").next().show();
			if($(this).attr("orglevel") == 10){
				$("#partFlow span").html("事件处理");
				$("#pacificUnionFounding span").html("平安和谐社区");
			}else{
				$("#pacificUnionFounding span").html("平安和谐三联创");
				$("#partFlow span").html("事件分流处理");
			}
			if(baseUrl==undefined){
				return false;
			}
			loadDepartmentByOrgLevel($(this).next().attr("id"),$(this).attr("orglevel"));
			if(isAuditDelay!='' && isAuditDelay!=null){
				baseLoad(this,baseUrl+"&isAuditDelay="+isAuditDelay,"");
				isAuditDelay="0";
			}else{
				baseLoad(this,baseUrl,"");
			}
			$("#thisCrumbs").find(".curr").html("当前层级：");
		}).eq(0).click();
		
		
		/*$("#issueLeftMenuListAcc a").click(function(){//职能部门点击事件
			var issueOrgId=$("#issueOrgId").val();
			var baseUrl=$(this).attr("baseUrl")+"?orgId="+issueOrgId;
			$("#issueLeftMenuListAcc a").removeClass("cur");
			$(this).addClass("cur");
			if(baseUrl==undefined){
				return false;
			}
			baseLoad(this,baseUrl,"");
		})
		*/
	  $("#clickThreeUniteWound a").click(function(){
			var baseUrl=$(this).attr("baseUrl");
			var leaderUrl=$(this).attr("leaderUrl");

			$('#clickThreeUniteWound a').removeClass("cur");
			$(this).addClass("cur");
			if("partFlow"==$(this).attr("id")){//事件分流
				baseUrl=$("#orgMenuList li").find(".cur").attr("baseurl");
				leaderUrl="";
			}
			if(baseUrl==undefined && leaderUrl==undefined){
				return false;
			}
			baseLoad(this,baseUrl,leaderUrl);
			$("#thisCrumbs").find(".curr").html("当前类别：");
		});
	
        $("#clickIssueType a").click(function(){
			var baseUrl=$(this).attr("baseUrl");
			var leaderUrl=$(this).attr("leaderUrl");

			$('#clickIssueType a').removeClass("cur");
			$(this).addClass("cur");

			if(baseUrl==undefined && leaderUrl==undefined){
				return false;
			}
			baseLoad(this,baseUrl,leaderUrl);
			$("#thisCrumbs").find(".curr").html("当前类别：");
		});
		
		$.layout();
		function layoutFun(){
			clearTimeout(window._leftMenuLayoutTimer);
			var documentHeight=document.documentElement.clientHeight-$(".ui-layout-north").outerHeight()-$(".ui-layout-south").outerHeight();
			$(".ui-layout-west .issueLeftMenuList").height(documentHeight-$("#accordionMenuBDT").outerHeight(true));
		}
		layoutFun();
		$(window).resize(function(){
			window._leftMenuLayoutTimer=setTimeout(function(){
				layoutFun();
			},200);
		});
        $('#accordion a').eq(0).click().closest(".uiContBase:hidden").prev().click();
        
        var orglevel = '<s:property value="#getFullOrgById.organization.orgLevel.internalId"/>';
       /*  switch(orglevel){
        case '10':
        	fillFunOrg("village");
        	break;
        case '20':
        	fillFunOrg("town");
        	break;
        case '30':
        	fillFunOrg("city");
        	break;
        } */
	});
	
	function getSelectedTabsIndex(){
		var baseUrl=$("#orgMenuList li>a")[0].getAttribute('baseUrl');
		if(baseUrl.indexOf('isAuditDelay')>0){
			var urlArr=baseUrl.split('isAuditDelay');
			if(urlArr[1].indexOf('\&')>0){
				return urlArr[1].substring(urlArr[1].indexOf('='),urlArr[1].indexOf('\&'));
			}else{
				return urlArr[1].substring(urlArr[1].indexOf('=')+1);
			}
		}else
			return 0;
	}
	
	function fillFunOrg(level) {
		var selectTabIndex=getSelectedTabsIndex();
		$.ajax({
        	async:false,
			url:PATH+'/sysadmin/orgManage/findFunOrgsByParentOrgId.action?parentId='+'<s:property value="#loginAction.user.organization.id"/>',
			success:function(data){
				var html="";
				$.each(data,function(name,value){
					html += "<dd><a href='javascript:;' baseUrl='${path }/issue/issueManage/issueViewTabList.jsp?keyId="+name+"&isAuditDelay="+selectTabIndex+"'>"+value+"</a></dd>";
				});
		    	$("#"+level+"").empty().append(html);
			}
		});
	}
	function reloadJurisdictionsList(issueType){
		var statusType = $("#_searchStatusType").val();
		$("#issueTypeAccordion").data("selectedIssueType",issueType);
		$("#jurisdictionsIssueType").val(issueType);
		var searchIssueVo={};
		var pageData = $.extend(
				{
				"searchIssueVo.sortField":"issueId",
				"searchIssueVo.order":"desc",
				"searchIssueVo.statusType": statusType,
				"searchIssueVo.orgLevelInternalId":$("#jurisdictionsOrgLevel").val(),
				"searchIssueVo.functionalOrgType":$("#jurisdictionsFunctionalOrgType").val(),
				"searchIssueVo.leaderView":1,
				"searchIssueVo.searchOrgId":$("#selectNodeId").val(),
				"searchIssueVo.targeOrgId":$("#jurisdictionsKeyId").val(),
				"viewType":$("#jurisdictionsViewType").val(),
				"type":$("#jurisdictionsIssueType").val(),
				"statusType":$("#_searchStatusType").val(),
				"searchIssueVo.evaluateType":$("#_statusTypeSelect").val()
				},
				searchIssueVo);
		
		if(undefined==$("#issueList").attr("id")){return;}
		if("" == issueType){return;}
		$("#issueList").setGridParam({
			url:'/issues/searchIssueByLevelManage/findIssueForView.action',
			datatype: "json"
		});
		$("#issueList").setPostData(pageData);
		$("#issueList").trigger("reloadGrid");
	}
	
	function loadThreeUniteWound(){
		$("#clickThreeUniteWound").show();
		$("#clickIssueType").hide();
	}
	
	function loadIssueType(){
		$("#clickIssueType").show();
		$("#clickThreeUniteWound").hide();
	}
	
	function reloadPacificUnionFoundingList(orgLevel){
		if($(".cur").attr("orglevel")=='<s:property value="@com.tianque.domain.property.OrganizationLevel@VILLAGE"/>') {
			$("#pacificUnionFounding").attr("baseUrl","${path }/issue/pacificUnionFounding/pacificUnionFoundingVillageSearch.jsp");
		} else {
			$("#pacificUnionFounding").attr("baseUrl","${path }/issue/pacificUnionFounding/pacificUnionFoundingHighVillageSearch.jsp");
		}
	}
	
	function loadDepartmentByOrgLevel(level,orgLevel){
		var selectTabIndex=getSelectedTabsIndex();		
		$.ajax({
        	async:false,
			url:PATH+'/sysadmin/orgManage/findOrgsByOrgTypeAndOrgLevel.action?orgTypeInternalId=1&orgLevelInternalId='+orgLevel,
			success:function(data){
				var html="";
				for(var i=0;i<data.length;i++){
					if(data[i].functionalOrgType!=null){
						html += "<dd><a href='javascript:;' baseUrl='${path }/issue/issueManage/issueViewTabList.jsp?orgLevel="+orgLevel+"&functionalOrgType="+data[i].functionalOrgType.id+"&keyId="+<s:property value='#getFullOrgById.organization.id'/>+"&isAuditDelay="+selectTabIndex+"'>"+data[i].functionalOrgType.displayName+"</a></dd>";
					}
				}
				if(html==""){
					$("#"+level+"").hide();
				}else{
					$("#"+level+"").show();
					$("#"+level+"").empty().append(html);
				}
			}
		});
	 	$("#orgMenuList dl a").click(function(){//层级点击事件
			var baseUrl=$(this).attr("baseUrl");
			$("#orgMenuList dl a").removeClass("cur");
			$("#orgMenuList li>a").removeClass("cur");
			$(this).addClass("cur");
			if(baseUrl==undefined){
				return false;
			}
			baseLoad(this,baseUrl,"");
			$("#thisCrumbs").find(".curr").html("当前层级：").end();
			if(!$("#thisCrumbs").find(".orgLevel")[0]){
				$("#thisCrumbs").find(".crumbs_cur").append("<span class='orgLevel'></span>")
			}
			$("#thisCrumbs").find(".orgLevel").html("<span style='font-weight:normal;'>>></span>"+$(this).text());
		});
	}
</script>
