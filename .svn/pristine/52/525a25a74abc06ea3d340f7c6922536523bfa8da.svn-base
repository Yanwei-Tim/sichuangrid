<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<style>
	#nav>a{float:left;margin-right:5px;display:inline;}
</style>
<div class="ui-corner-all nav-buttons" id="nav">
	<pop:JugePermissionTag ename="add${modulType}Issue">
		<a id="add" href="javascript:;"><span>新增</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="update${modulType}Issue">
		<a id="update" href="javascript:void(0)"><span>修改</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="delete${modulType}Issue">
		<a id="delete" href="javascript:void(0)"><span>删除</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="search${modulType}Issue">
		<a id="search" href="javascript:void(0)"><span>查询</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="view${modulType}Issue">
		<a id="view" href="javascript:void(0)"><span>查看</span></a>
	</pop:JugePermissionTag>
		<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	<pop:JugePermissionTag ename="overseeNeedIssue">
		<a id="oversee" href="javascript:;" class="nav-dropdownBtn"><span>督办</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		<div class="nav-sub-buttons">
			<pop:JugePermissionTag ename="normalIssue">
				<a id="normalIssue" href="javascript:void(0)"><span>普通督办</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="yellowCardIssue">
				<a id="yellowCardIssue" href="javascript:void(0)"><span>黄牌督办</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="redCardIssue">
				<a id="redCardIssue" href="javascript:void(0)"><span>红牌督办</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="cancleSuperviseIssue">
				<a id="cancleSupervise" href="javascript:void(0)"><span>取消督办</span></a>
			</pop:JugePermissionTag>
		</div>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="delayNeedIssue">
		<a id="delay" href="javascript:;" class="nav-dropdownBtn hidden"><span>延期</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		<div class="nav-sub-buttons">
			<pop:JugePermissionTag ename="applyDelayNeedIssue">
				<a id="applyDelay" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>申请延期</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="viewDelayNeedIssue">
				<a id="viewDelay" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>延期详情</span></a>
			</pop:JugePermissionTag>
		</div>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="urgentNeedIssue,cancelUrgentNeedIssue">
		<a id="urgent" href="javascript:void(0)"><span>加急</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="commandNeedIssue">
		<a id="command" href="javascript:void(0)"><span>领导批示</span></a>
	</pop:JugePermissionTag>
	<!--
		<pop:JugePermissionTag ename="historicalIssue">
			<a id="historicalIssue" href="javascript:void(0)"><span>历史遗留</span></a>
		</pop:JugePermissionTag>
	-->
	<!--  
 	<pop:JugePermissionTag ename="evaluateVerificationIssue">
		<a id="evaluate" href="javascript:void(0)"><span>验证</span></a>
	</pop:JugePermissionTag>
	-->
	<!-- 
	<pop:JugePermissionTag ename="top${modulType}Issue">
		<a id="topIssue" href="javascript:void(0)"><span>置顶</span></a>
	</pop:JugePermissionTag>
	 -->
	<pop:JugePermissionTag ename="publicltyCassDoneIssue">
		<a id="publicltyCass" href="javascript:void(0)"><span>设为宣传案例</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="cancelPublicltyCassDoneIssue">
		<a id="cancelPublicltyCass" href="javascript:void(0)"><span>取消宣传案例</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="print${modulType}Issue">
		<a id="printIssue" href="javascript:void(0)"><span>打印</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="viewProcess${modulType}Issue">
		<a id="viewProcess" href="javascript:void(0)"><span>展示</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="auditDelayCheckPendingIssue">
		<a id="auditDelay" href="javascript:void(0)"><span>审核延期</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="delayDetailsCheckPendingIssue">
		<a id="delayDetails" href="javascript:void(0)"><span>延期详情</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="gradeCheckGradeIssue">
		<a href="javascript:void(0)" id="grade" style="display: none"><span>评分</span></a> 
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="gradeHistory${modulType}Issue">
		<a href="javascript:void(0)" id="gradeHistory"><span>评分详情</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="readNeedIssue">
		<a href="javascript:void(0)" class="jqsubgridButton" id="readTop" style="display: none"><span class="yijian">阅读</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="conceptNeedIssue">
		<a href="javascript:void(0)" class="jqsubgridButton" id="conceptTop" style="display: none"><span class="yijian">受理</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="dealNeedIssue,evaluateVerificationIssue">
		<a id="dealIssue" href="javascript:;" class="nav-dropdownBtn hidden"><span>办理</span><b class="nav-dropdownBtn-arr"><b class="nav-ico-dArr"></b></b></a>
		<div class="nav-sub-buttons">
			<a id="dealTop" href="javascript:void(0)"><span><strong class="ui-ico-dr"></strong>办理</span></a>
			<div id="skipDealBox" style="position:relative;">
				<a id="skipDeal" href="javascript:void(0)"><span><strong class="ui-ico-dc"></strong>越级办理</span></a>
				<div class ='loadPage' style="position:absolute; left:100px; top:0; background: #fff; display:none;border: 1px solid #999;border-left:0 none;"></div>
			</div>
		</div>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="toChangeIntoThreeRecords">
		<a href="javascript:void(0)" id="toChangeIntoThreeRecords"><span>转为三本台账</span></a>
	</pop:JugePermissionTag>
	<!--<a href="javascript:void(0)" class="jqsubgridButton" id="reportToTop" style="display: none"><span class="yijian">上报指挥中心</span></a>-->
	<div id="searchDiv" style="display:inline-block;*display:inline;zoom:1;overflow:hidden;height:26px;"> 
		<!-- 
		<div style="display:inline-block;*display:inline;zoom:1;overflow:hidden;">
			<input type="text" id="fastSearchIssueOrg"  style="color: grey;width: 180px;" />
		</div>
		 -->
		 <div class="btnbanner btnbannerData">
			<div class="ui-widget autosearch">
			<input class="" type="text" value="" id="fastSearchIssueOrg" maxlength="18" class="searchtxt" style="width:180px;height: 22px;" onblur="value=(this.value=='')?'':this.value;" onfocus="value=(this.value=='')?'':this.value;"/>
			<button id="refreshSearchKey" class="ui-icon ui-icon-refresh searchbtnico"></button>
		</div>
		</div>
		<a href="javascript:;" id="searchOrgTree"><span>快捷筛选</span></a>
	</div>
	<select onchange="onOrgChanged(this.value)" id="_statusTypeSelect" class="form-txt" style="display: none; width: 70px; float: right;">
		<option value="" selected>全部</option><option value="1">已评分</option><option value="0">未评分</option>
	</select>
	<select onchange="onOrgChanged(this.value)" id="_typeSelect" class="form-txt" style="display: none;width: 85px; float: right;">
		<option value="0" selected>近期已办</option><option value="1">历史已办</option>
	</select>
	<select onchange="onOrgChanged(this.value)" id="_submitTypeSelect" class="form-txt" style="display: none;width: 75px; float: right;">
		<option value="0" selected>近期上报</option><option value="1">历史上报</option>
	</select>
	<select onchange="onOrgChanged(this.value)" id="_skipTypeSelect" class="form-txt" style="display: none;width: 75px; float: right;">
		<option value="0" selected>近期越级</option><option value="1">历史越级</option>
	</select>
	<select onchange="onOrgChanged(this.value)" id="_assignTypeSelect" class="form-txt" style="display: none;width: 100px; float: right;">
		<option value="0" selected>近期上级交办</option><option value="1">历史上级交办</option>
	</select>
	<select onchange="onOrgChanged(this.value)" id="_timeOutTypeSelect" class="form-txt" style="display: none;width: 85px; float: right;">
		<option value="0" selected>全部</option><option value="<s:property value='@com.tianque.issue.state.IssueState@RED_CARD_SUPERVISE'/>">红牌事件</option><option value="<s:property value='@com.tianque.issue.state.IssueState@YELLOW_CARD_SUPERVISE'/>">黄牌事件</option>
	</select>
</div>
<script type="text/javascript">
var orgTree;
var issueButtons={
		show:function(type){
			$("#skipDealBox").show();
			return issueButtons[type]();
		},
		myneed:function(){//我的待办
			<s:if test="#parameters.sourceType[0] == @com.tianque.domain.property.IssueSourceType@WECHAT_INPUT">
				this.showButtons(["修改","删除","查询","查看","刷新","延期","打印","阅读","受理","办理","展示"]);
			</s:if>
			<s:elseif test="#getFullOrgById.organization.orgLevel.internalId == @com.tianque.domain.property.OrganizationLevel@COUNTRY">
				this.showButtons(["修改","删除","查询","查看","刷新","延期","置顶","打印","阅读","受理","办理","展示"]);
			</s:elseif>
			<s:else>
				this.showButtons(["新增","修改","删除","查询","查看","刷新","延期","置顶","打印","阅读","受理","办理","展示","转为三本台账"]);
			</s:else>
		},
		mycheckPending:function(){
			this.showButtons(["查看","刷新","审核延期","延期详情"]);
		},
		myfollow:function(){
			<s:if test="#parameters.sourceType[0] == @com.tianque.domain.property.IssueSourceType@WECHAT_INPUT">
				this.showButtons(["查询","查看","刷新","展示"]);
			</s:if>
			<s:else>
				this.showButtons(["查询","查看","刷新","置顶","展示"]);
			</s:else>
		},
		mycheckGrade:function(){
			this.showButtons(["删除","查询","查看","刷新","评分","评分详情","置顶","展示"]);
		},
		mydone:function(){
			<s:if test="#parameters.sourceType[0] == @com.tianque.domain.property.IssueSourceType@WECHAT_INPUT">
				this.showButtons(["查询","查看","刷新","展示"]);
			</s:if>
			<s:else>
				this.showButtons(["查询","查看","刷新","置顶","展示"]);
			</s:else>
		},
		mycompleted:function(){
			<s:if test="#parameters.sourceType[0] == @com.tianque.domain.property.IssueSourceType@WECHAT_INPUT">
				this.showButtons(["删除","查询","查看","刷新","转为台帐","评分详情","展示","设为宣传案例","打印"]);
			</s:if>
			<s:else>
				this.showButtons(["删除","查询","查看","刷新","置顶","转为台帐","评分详情","展示","设为宣传案例","打印"]);
			</s:else>
		},
		myverification:function(){
			$("#skipDealBox").hide();
			<s:if test="#parameters.sourceType[0] == @com.tianque.domain.property.IssueSourceType@WECHAT_INPUT">
				this.showButtons(["删除","查询","查看","刷新","办理","展示"]);
			</s:if>
			<s:else>
			this.showButtons(["删除","查询","查看","刷新","办理","置顶","展示"]);
			</s:else>
		},
		mysubmit:function(){
			<s:if test="#parameters.sourceType[0] == @com.tianque.domain.property.IssueSourceType@WECHAT_INPUT">
				this.showButtons(["查询","查看","刷新","展示"]);
			</s:if>
			<s:else>
				this.showButtons(["查询","查看","刷新","置顶","展示"]);
			</s:else>
		},
		myassign:function(){
			<s:if test="#parameters.sourceType[0] == @com.tianque.domain.property.IssueSourceType@WECHAT_INPUT">
				this.showButtons(["查询","查看","刷新","展示"]);
			</s:if>
			<s:else>
				this.showButtons(["查询","查看","刷新","置顶","展示"]);
			</s:else>
		},
		mypublicltyCassDone:function(){
			<s:if test="#parameters.sourceType[0] == @com.tianque.domain.property.IssueSourceType@WECHAT_INPUT">
				this.showButtons(["查询","查看","刷新","取消宣传案例"]);
			</s:if>
			<s:else>
				this.showButtons(["查询","查看","刷新","取消宣传案例"]);
			</s:else>
		},
		mytimeOut:function(){
			<s:if test="#parameters.sourceType[0] == @com.tianque.domain.property.IssueSourceType@WECHAT_INPUT">
				this.showButtons(["查询","查看","刷新"]);
			</s:if>
			<s:else>
				this.showButtons(["查询","查看","刷新"]);
			</s:else>
		},
		need:function(){//下辖待办
			this.showButtons(["修改","删除","查询","查看","刷新","督办","加急","领导批示"]);
		},
		checkPending:function(){
			this.showButtons(["查询","查看","刷新","延期详情"]);
		},
		verification:function(){
			this.showButtons(["查询","查看","刷新"]);
		},
		checkGrade:function(){
			this.showButtons(["查询","查看","刷新"]);
		},
		follow:function(){
			this.showButtons(["查询","查看","刷新"]);
		},
		done:function(){
			this.showButtons(["查询","查看","刷新"]);
		},
		completed:function(){
			this.showButtons(["删除","查询","查看","刷新","设为宣传案例","打印"]);
		},
		submit:function(){
			this.showButtons(["查询","查看","刷新"]);
		},
		assign:function(){
			this.showButtons(["查询","查看","刷新"]);
		},
		myskip:function(){
			<s:if test="#parameters.sourceType[0] == @com.tianque.domain.property.IssueSourceType@WECHAT_INPUT">
				this.showButtons(["查询","查看","刷新"]);
			</s:if>
			<s:else>
				this.showButtons(["查询","查看","置顶","刷新"]);
			</s:else>
		},
		skip:function(){
			this.showButtons(["查询","查看","刷新"]);
		},
		publicltyCassDone:function(){
			this.showButtons(["查询","查看","刷新","取消宣传案例"]);
		},
		timeOut:function(){
			this.showButtons(["查询","查看","刷新"]);
		},
		showButtons:function(showBtns){
			$(".nav-buttons>a").each(function(){
				if(!showBtns.contains($(this).text())){
					$(this).remove();
				}else{
					$(this).show();
				}
			});
			//已办结和待评分页签，添加“已评分和未评分“的选择
			if("completed" == $("#jurisdictionsViewType").val()||"checkGrade" == $("#jurisdictionsViewType").val()){
				$("#_statusTypeSelect").show();
			}
			//已办页签，添加”近期已办，历史已办“的选择
			if("done" == $("#jurisdictionsViewType").val()&&"<s:property value='#request.isTqsearchOpen'/>"=="false"){
				$("#_typeSelect").show();
			}
			//上报页签，添加”近期和历史上报“的选择
			if("submit" == $("#jurisdictionsViewType").val()&&"<s:property value='#request.isTqsearchOpen'/>"=="false"){
				$("#_submitTypeSelect").show();
			}
			//上级交办页签，添加”近期和历史上级交办 “的选择
			if("assign" == $("#jurisdictionsViewType").val()&&"<s:property value='#request.isTqsearchOpen'/>"=="false"){
				$("#_assignTypeSelect").show();
			}
			//越级页签，添加”近期和历史越级“的选择
			if("skip" == $("#jurisdictionsViewType").val()&&"<s:property value='#request.isTqsearchOpen'/>"=="false"){
				$("#_skipTypeSelect").show();
			}
			if("timeOut" == $("#jurisdictionsViewType").val()){
				$("#_timeOutTypeSelect").show();
			}
			
			
		}
	};
	
function exportWord(){
	if(!hasRowSelected()){
		$.messageBox({level:'warn',message:"没有可作打印的数据！"});
		return ;
	}
	var selectedIssue = getSelectedData();
	var source= $("#jurisdictionsViewType").val();
	this.location=PATH+"/issues/issueManage/viewSubDetail.action?mode=printWord&source="+source+"&keyId="+selectedIssue.encryptIdByIssueStepId;
}

function callback(){
    var dfop = {
       	wechatInput:'<s:property value="@com.tianque.domain.property.IssueSourceType@WECHAT_INPUT"/>',
       	sourceType:'<s:property value="#parameters.sourceType[0]"/>',
       	orgLevel:'<s:property value="#getFullOrgById.organization.orgLevel.internalId"/>',
       	orgTypeInternal:'<s:property value="@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG"/>',
       	normalSupervise:'<s:property value="@com.tianque.issue.state.IssueState@NORMAL_SUPERVISE"/>',
       	normalSuperviseCode:'<s:property value="@com.tianque.issue.state.IssueOperate@NORMAL_SUPERVISE.code"/>',
       	yellowCardSup:'<s:property value="@com.tianque.issue.state.IssueState@YELLOW_CARD_SUPERVISE"/>',
       	yellowSupCode:'<s:property value="@com.tianque.issue.state.IssueOperate@YELLOW_SUPERVISE.code"/>',
       	redCardSup:'<s:property value="@com.tianque.issue.state.IssueState@RED_CARD_SUPERVISE"/>',
       	redSupCode:'<s:property value="@com.tianque.issue.state.IssueOperate@RED_SUPERVISE.code"/>',
       	noSup:'<s:property value="@com.tianque.issue.state.IssueState@NO_SUPERVISE"/>',
       	cancelSupCode:'<s:property value="@com.tianque.issue.state.IssueOperate@CANCEL_SUPERVISE.code"/>',
       	token: '<pop:token ajax="true" />',
       	instructCode:'<s:property value="@com.tianque.issue.state.IssueOperate@INSTRUCT.code"/>',
       	historicCode:'<s:property value="@com.tianque.issue.state.IssueOperate@HISTORIC.code"/>',
       	done:'<s:property value="@com.tianque.issue.constants.IssueTag@DONE_ISSUE"/>',
       	needdo:'<s:property value="@com.tianque.issue.constants.IssueTag@NEEDDO_ISSUE"/>',
       	completed:'<s:property value="@com.tianque.issue.constants.IssueTag@COMPLETED_ISSUE"/>',
       	submit:'<s:property value="@com.tianque.issue.constants.IssueTag@SUBMIT_ISSUE"/>',
       	assign:'<s:property value="@com.tianque.issue.constants.IssueTag@ASSIGN_ISSUE"/>',
       	skip:'<s:property value="@com.tianque.issue.constants.IssueTag@SKIP_ISSUE"/>',
       	veritfication:'<s:property value="@com.tianque.issue.constants.IssueTag@VERIFICATION_ISSUE"/>',
       	checkGrade:'<s:property value="@com.tianque.issue.constants.IssueTag@CHECKGRADE_ISSUE"/>',
       	urgent:'<s:property value="@com.tianque.issue.state.IssueOperate@URGENT.code"/>',
       	cancelUrgentCode:'<s:property value="@com.tianque.issue.state.IssueOperate@CANCEL_URGENT.code"/>',
       	orgLevelInternalId:'<s:property value="#parameters.orgLevel"/>',
       	readCode:'<s:property value="@com.tianque.issue.state.IssueOperate@READ.code"/>',
       	conceptCode:'<s:property value="@com.tianque.issue.state.IssueOperate@CONCEPT.code"/>',
       	reportCode:'<s:property value="@com.tianque.issue.state.IssueOperate@REPORT_TO.code"/>'
    }
    TQ.issueForViewButtons(dfop);
}

$.cacheScript({
    url:'/resource/getScript/issue/issueManage/issueForViewButtons.js',
    callback: callback
})
</script>