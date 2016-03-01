TQ.issueViewTabList = function (dfop){
	$(function() {
		var isAuditDelay= dfop.isAuditDelay;
		var isMyIssue = $("#orgMenuList a").eq(0).hasClass("cur");
		$("#isMyIssue").val(isMyIssue);
		if(isAuditDelay!='' && isAuditDelay!=null){
			$( "#tabList" ).tabs({ selected: isAuditDelay ,beforeLoad:function(){
				$( "#tabList" ).find(".ui-tabs-panel").empty();
			},beforeActivate : function(event, ui) {
				beforeTabsActiveFun(event,ui);
			}});
		}else{
			$( "#tabList" ).tabs({ selected: 0 ,beforeLoad:function(){
				$( "#tabList" ).find(".ui-tabs-panel").empty();
			},beforeActivate : function(event, ui) {
				beforeTabsActiveFun(event,ui);
			}});
		}
		refreshIssueCounts();
	});
	//刷新页签统计数量
	function refreshIssueCounts(){
		var orgLevelInternalId = dfop.orgLevelInternalId;
		var functionalOrgType = dfop.functionalOrgType;
		$.ajax({
			async : true,
			url : PATH+"/issues/issueApplyDelay/getJurisdictionsAuditDelayCount.action",
			data :{"orgLevelInternalId":orgLevelInternalId,"orgId":USER_ORG_ID,"functionalOrgType":functionalOrgType},
			success : function(data) {
				if(!isNaN(data)){
					$("#myAuditDelayCount").html(data);
				}else{
					$("#myAuditDelayCount").html(0);
					$.messageBox({level:'warn',message:"获取下辖待审核数量出错"});
				}
			}
		 });
		$.ajax({
			async : true,
			url : PATH+"/issues/issueManage/getJurisdictionsGradeDelayCount.action",
			data :{"orgLevelInternalId":orgLevelInternalId,"keyId":USER_ORG_ID,"functionalOrgType":functionalOrgType},
			success : function(data) {
				if(!isNaN(data)){
					$("#myGradeDelayCount").html(data);
				}else{
					$("#myGradeDelayCount").html(0);
					$.messageBox({level:'warn',message:"获取下辖待评分数量出错"});
				}
			}
		 });
		 $.ajax({
			async : true,
			url : PATH+"/issues/issueManage/getJurisdictionsVerificationCount.action",
			data :{"orgLevelInternalId":orgLevelInternalId,"keyId":USER_ORG_ID,"functionalOrgType":functionalOrgType},
			success : function(data) {
				if(!isNaN(data)){
					$("#myVerificationCount").html(data);
				}else{
					$("#myVerificationCount").html(0);
					$.messageBox({level:'warn',message:"获取下辖待验证数量出错"});
				}
			}
		 });
	}

	function beforeTabsActiveFun(event,ui){
		$("#orgMenuList li>a").each(function(){
			var href=$(this).attr('baseUrl');
			var indexOf=href.indexOf("\?");
			if(indexOf>0){
				indexOf=href.indexOf("isAuditDelay");
				if(indexOf>0){
					var hrefArr=href.split("isAuditDelay");
					href=hrefArr[0];
					if(hrefArr[1].indexOf("\&")>0){
						href+="isAuditDelay="+ui.newTab.index();
						href+=hrefArr[1].substring(hrefArr[1].indexOf("\&"));
					}else{
						href+="isAuditDelay="+ui.newTab.index();
					}
				}else{
					href+="&isAuditDelay="+ui.newTab.index();
				}
			}
			else
				href+="?isAuditDelay="+ui.newTab.index();
			$(this).attr('baseUrl',href);
		});	
		
		$("#orgMenuList dl>a").each(function(){
			var href=$(this).attr('baseUrl');
			var indexOf=href.indexOf("\?");
			if(indexOf>0){
				indexOf=href.indexOf("isAuditDelay");
				if(indexOf>0){
					var hrefArr=href.split("isAuditDelay");
					href=hrefArr[0];
					if(hrefArr[1].indexOf("\&")>0){
						href+="isAuditDelay="+ui.newTab.index();
						href+=hrefArr[1].substring(hrefArr[1].indexOf("\&"));
					}else{
						href+="isAuditDelay="+ui.newTab.index();
					}
				}else{
					href+="&isAuditDelay="+ui.newTab.index();
				}
			}
			else
				href+="?isAuditDelay="+ui.newTab.index();
			$(this).attr('baseUrl',href);
		});		
	}
}