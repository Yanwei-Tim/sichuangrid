<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.tianque.domain.property.OrganizationType,com.tianque.domain.Organization"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib"%>
<%@ include file="/includes/baseInclude.jsp"%>
<s:action name="getLoginInfo" var="loginAction" executeResult="false" namespace="/sessionManage" />
<s:action name="getFullOrgById" var="getFullOrgById" executeResult="false" namespace="/sysadmin/orgManage" >
	<s:param name="organization.id" value="#loginAction.user.organization.id"></s:param>
</s:action>
<%
Organization userOrg = ThreadVariable.getUser().getOrganization();
if (userOrg.getOrgType().getDisplayName().equals(
		OrganizationType.getInternalProperties().get(
				OrganizationType.FUNCTIONAL_ORG).getDisplayName())){
	request.setAttribute("isFun",true);
}else{
	request.setAttribute("isFun",false);
}
%>
<style>
	#nav>a{float:left;margin-right:5px;display:inline;}
</style>
<c:if test="${!isFun}">
<div class="btnbanner btnbannerData" style="float:left">
	<jsp:include page="/common/orgSelectedComponent.jsp"/>
	<div class="userState" id="fastSearchSelect">
		<select onchange="onOrgChanged()" id="seachValue" class="form-txt" style="display: none; width: 200px;">
			<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>"  selected="selected">本级</option>
			<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_DIRECTJURISDICTION"/>">直属下辖</option>
			<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_ALLURISDICTION"/>">下辖全部</option>
		</select>
	</div>
</div>
</c:if>
<div class="ui-corner-all nav-buttons" id="nav" style="float:left">
	<pop:JugePermissionTag ename="addIssue">
		<a id="add" href="javascript:;"><span>新增</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="updateIssue">
		<a id="update" href="javascript:void(0)"><span>修改</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="deleteIssue">
		<a id="delete" href="javascript:void(0)"><span>删除</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="partyMemberPioneerServiceTeamEventSearchIssue">
		<a id="search" href="javascript:void(0)"><span>查询</span></a>
	</pop:JugePermissionTag>
	<a id="view" href="javascript:void(0)"><span>查看</span></a>
	<a id="refresh" href="javascript:void(0)"><span>刷新</span></a>
	<pop:JugePermissionTag ename="printIssue">
		<a id="printIssue" href="javascript:void(0)"><span>打印</span></a>
	</pop:JugePermissionTag>
	<pop:JugePermissionTag ename="viewProcessJurisdictionsNeed">
		<a id="viewProcess" href="javascript:void(0)"><span>展示</span></a>
	</pop:JugePermissionTag>
	<input type="hidden" name="selectNodeId" id="selectNodeId" />
	<input type="hidden" name="selectnodeLevel" id="selectnodeLevel" />
	<input type="hidden" name="selectnodeType" id="selectnodeType" />
</div>
<div class="ui-corner-all nav-buttons">
	<select onchange="onStatusChanged(this.value)" id="party_doneTypeSelect" class="form-txt" style="display: none;width: 85px; float: right;">
		<option value="0" selected>近期数据</option><option value="1">历史数据</option>	
	</select>
</div>
<script type="text/javascript">
var orgTree;
var issueButtons={
		show:function(type){
			return issueButtons[type]();
		},
		myneed:function(){//我的待办
			<s:if test="#parameters.sourceType[0] == @com.tianque.domain.property.IssueSourceType@WECHAT_INPUT">
				this.showButtons(["修改","删除","查询","查看","刷新","延期","加急","打印","阅读","受理","办理","展示"]);
			</s:if>
			<s:elseif test="#getFullOrgById.organization.orgLevel.internalId == @com.tianque.domain.property.OrganizationLevel@COUNTRY">
				this.showButtons(["修改","删除","查询","查看","刷新","延期","加急","置顶","打印","阅读","受理","办理","展示"]);
			</s:elseif>
			<s:else>
				this.showButtons(["新增","修改","删除","查询","查看","刷新","延期","加急","置顶","打印","阅读","受理","办理","展示"]);
			</s:else>
		},
		mycheckPending:function(){
			this.showButtons(["查看","刷新","审核延期","延期详情"]);
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
				this.showButtons(["删除","查询","查看","刷新","转为台帐","打分","打分详情","验证","展示"]);
			</s:if>
			<s:else>
				this.showButtons(["删除","查询","查看","刷新","置顶","转为台帐","打分","打分详情","验证","展示"]);
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
		need:function(){//下辖待办
			this.showButtons(["修改","删除","查询","查看","刷新","督办","加急","领导批示"]);
		},
		done:function(){
			this.showButtons(["查询","查看","刷新"]);
		},
		completed:function(){
			this.showButtons(["删除","查询","查看","刷新"]);
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
		fourTeamsEvent:function(){
			this.showButtons(["查询","查看","刷新"]);
		},
		showButtons:function(showBtns){
			$(".nav-buttons>a").each(function(){
				if(!showBtns.contains($(this).text())){
					$(this).hide();
				}else{
					$(this).show();
				}
			});
			if("done" == $("#jurisdictionsViewType").val() || "completed" == $("#jurisdictionsViewType").val()){
				$("#party_doneTypeSelect").show();
			}else{
				$("#party_doneTypeSelect").hide();
			}
			$("#seachValue").show();
			if(isGrid()){
				$("#fastSearchSelect").hide();
			}
		}
	};
$(function(){
	 
	function initOccurOrgSelector(){
			var orgLevelId = $("#jurisdictionsOrgLevel").val();
			var orgFuntionalTypeId = $("#jurisdictionsFunctionalOrgType").val();	
			orgTree=$("#fastSearchIssueOrg").treeSelect({
				nodeUrl:'/sysadmin/orgManage/ajaxOrgsForExtTreeByLevel.action?orgFuntionalTypeId='+orgFuntionalTypeId+'&orgLevelInternalId='+orgLevelId,
				allOrg:false,
				isRootSelected:false,
				emptyText:'',
				rootId:$("#jurisdictionsKeyId").val()
			});
			orgTree.on("click",function(node,e) {
				if(node != null){
					var nodeId = node.attributes.id;
					var nodeName = node.attributes.name;
					var nodeLevel = node.attributes.orgLevelInternalId;
					var nodeType = node.attributes.orgTypeInternalId;
					$("#selectNodeId").val(nodeId);
					$("#selectnodeLevel").val(nodeLevel);
					$("#selectnodeType").val(nodeType);
					$("#fastSearchIssueOrg").val(nodeName);
				}
			});
		}
		//var sourceType = "<s:property value='#parameters.sourceType'/>";
		//if($("#orgScope").val()=='present' || sourceType == <s:property value='@com.tianque.domain.property.IssueSourceType@WECHAT_INPUT'/>){
		//	$("#searchDiv").css("display","none");
		//}else{
		//	initOccurOrgSelector();
		//}
		
		$("#searchOrgTree").click(function(){
			var orgLevelId = $("#jurisdictionsOrgLevel").val();
			var orgFuntionalTypeId = $("#jurisdictionsFunctionalOrgType").val();	
			var nodeLevel = $("#selectnodeLevel").val();	
			var nodeType = $("#selectnodeType").val();	
			if(orgFuntionalTypeId==""||orgFuntionalTypeId==null){
					if($("#selectNodeId").val()==""){
						$.messageBox({level:'warn',message:"请选择组织机构！"});
						return;
					}
					searchIsssueByOrgId();
			}else{
				var orgTypeInternal='<s:property value='@com.tianque.domain.property.OrganizationType@FUNCTIONAL_ORG'/>';
					if($("#selectNodeId").val()==""){
						$.messageBox({level:'warn',message:"请选择组织机构！"});
						return;
					}
					searchIsssueByOrgId();
			}
		});
	$("#add").click(function(event){
		$("#issueDialog").createDialog({
			width:840,
			height:570,
			title:'新增事件处理信息',
			url:'${path}/fourTeamsIssueManage/dispatch.action?mode=add&keyId='+$("#currentOrgId").val(),
			buttons: {
		   		"保存" : function(event){
		   			$("#issueMaintainForm").submit();
		   		},
   				"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#delete").click(function(event){
		if (hasRowSelected()){
			$.confirm({
				title:"确认删除",
				message:"该事件处理信息删除后,将无法恢复,您确认删除该事件处理信息吗?",
				width:400,
				okFunc:function(){
					fourTeamsRemoveIssue(getSelectedData().encryptIdByIssueId);
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可删除的数据！"});
			return;
		}
	});

	$("#update").click(function(event){
		if (hasRowSelected()){
			fourTeamsEditIssue(getSelectedData().encryptIdByIssueStepId)
		}else{
			$.messageBox({level:'warn',message:"没有可修改的数据！"});
		}
	});

	$("#view").click(function(){
		viewIssue();
	});
	$("#refresh").click(function(){
		reloadIssue();
	});

	$("#search").click(function(event){
		var status = '';//<s:property value="@com.tianque.issue.state.IssueState@DEALING"/>
		$("#issueDialog").createDialog({
			width: 750,
			height: 400,
			title: "事项查询-请输入查询条件",
			url: "${path}/fourTeamsIssueManage/dispatch.action?mode=search&keyId="+$("#currentOrgId").val()+'&issue.status='+status+'&tag='+viewType.value+"&selectedIssueType=",
			buttons: {
		   		"查询" : function(event){
		   			if($("#searchIssueForm").valid()){
		   				searchIssue();
			        	$(this).dialog("close");
		   			}
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#normalIssue").click(function(event){
		if (hasRowSelected()){
			var selectedIssue = getSelectedData();
			var supervisionstate = selectedIssue.supervisionState;
			if(supervisionstate >= <s:property value="@com.tianque.issue.state.IssueState@NORMAL_SUPERVISE"/>){
				$.messageBox({level:'warn',message:"该事件已被普通督办或正处于更高级别的督办中！"});
				return;
			}
			var	selectedId = selectedIssue.issueStepId;
			fourTeamsSuperviseIssue(selectedIssue.encryptIdByIssueStepId,<s:property value="@com.tianque.issue.state.IssueOperate@NORMAL_SUPERVISE.code"/>);
		}else{
			$.messageBox({level:'warn',message:"没有可督办的数据！"});
			return;
		}
	});

	$("#yellowCardIssue").click(function(event){
		if (hasRowSelected()){
			var selectedIssue = getSelectedData();
			var supervisionstate = selectedIssue.supervisionState;
			if(supervisionstate >= <s:property value="@com.tianque.issue.state.IssueState@YELLOW_CARD_SUPERVISE"/>){
				$.messageBox({level:'warn',message:"该事件已被黄牌督办或正处于更高级别的督办中！"});
				return;
			}
			var	selectedId = selectedIssue.issueStepId;
			fourTeamsSuperviseIssue(selectedIssue.encryptIdByIssueStepId,<s:property value="@com.tianque.issue.state.IssueOperate@YELLOW_SUPERVISE.code"/>);
		}else{
			$.messageBox({level:'warn',message:"没有可督办的数据！"});
			return;
		}
	});

	$("#redCardIssue").click(function(event){
		if (hasRowSelected()){
			var selectedIssue = getSelectedData();
			var supervisionstate = selectedIssue.supervisionState;
			if(supervisionstate == <s:property value="@com.tianque.issue.state.IssueState@RED_CARD_SUPERVISE"/>){
				$.messageBox({level:'warn',message:"该事件已被红牌督办！"});
				return;
			}
			var	selectedId = selectedIssue.issueStepId;
			fourTeamsSuperviseIssue(selectedIssue.encryptIdByIssueStepId,<s:property value="@com.tianque.issue.state.IssueOperate@RED_SUPERVISE.code"/>);
		}else{
			$.messageBox({level:'warn',message:"没有可督办的数据！"});
			return;
		}
	});

	$("#cancleSupervise").click(function(event){
		if (hasRowSelected() ){
			var selectedIssue = getSelectedData();
			var supervisionstate = selectedIssue.supervisionState;
			if(supervisionstate == <s:property value="@com.tianque.issue.state.IssueState@NO_SUPERVISE"/>){
				$.messageBox({level:'warn',message:"该事件未被督办！"});
				return;
			}
			var	selectedId = selectedIssue.issueStepId;
			$.confirm({
				title:"系统提示",
				message:"是否确定要取消对该事件处理的督办!",
				width:400,
				okFunc:function(){
					fourTeamsCancelSupervise(selectedIssue.encryptIdByIssueStepId,<s:property value="@com.tianque.issue.state.IssueOperate@CANCEL_SUPERVISE.code"/>,'<pop:token ajax="true" />');
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可取消督办的数据！"});
			return;
		}
	});

	$("#command").click(function(event){
		if (hasRowSelected() && $("#command").isButtonEnabled()){
			var	selectedId = getSelectedId();
			var encryptIds=deleteOperatorByEncrypt("issue",selectedId,"encryptIdByIssueStepId");
			$("#issueDialog").createDialog({
				width: 600,
				height: 400,
				title: '领导批示',
				url:'${path}/fourTeamsIssueManage/dispatchDeal.action?dealCode=<s:property value="@com.tianque.issue.state.IssueOperate@INSTRUCT.code"/>&keyId='+encryptIds,
				buttons: {
			   		"确定" : function(event){
		   				$("#singleContentDealForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可作领导批示的数据！"});
			return;
		}
	});
	

	$("#historicalIssue").click(function(event){
		if (hasRowSelected()){
			var selectedIssue = getSelectedData();
			if('待处理' == selectedIssue.dealState){
				$.messageBox({level:'warn',message:"该事件还未受理，不能设为历史遗留！"});
				return;
			}
			$.confirm({
				title:"系统提示",
				message:"是否将该事件设置为历史遗留?",
				width:400,
				okFunc:function(){
					var	selectedId = selectedIssue.issueStepId;
					settingIssueHistorical(selectedId,<s:property value="@com.tianque.issue.state.IssueOperate@HISTORIC.code"/>,'<pop:token ajax="true" />');
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可作历史遗留的数据！"});
			return;
		}
	});

	//置顶操作
	$("#topIssue").click(function(event){
		var selectedIssue = getSelectedData();
		if (hasRowSelected()){
			var issueTag = '<s:property value="@com.tianque.issue.constants.IssueTag@DONE_ISSUE"/>';
			if(viewType.value=="need"){
				issueTag = '<s:property value="@com.tianque.issue.constants.IssueTag@NEEDDO_ISSUE"/>'
			}else if(viewType.value=="completed"){
				issueTag = '<s:property value="@com.tianque.issue.constants.IssueTag@COMPLETED_ISSUE"/>'
			}else if(viewType.value=="submit"){
				issueTag = '<s:property value="@com.tianque.issue.constants.IssueTag@SUBMIT_ISSUE"/>'
			}else if(viewType.value=="assign"){
				issueTag = '<s:property value="@com.tianque.issue.constants.IssueTag@ASSIGN_ISSUE"/>'
			}else if(viewType.value=="skip"){
				issueTag = '<s:property value="@com.tianque.issue.constants.IssueTag@SKIP_ISSUE"/>'
			}
			
			$.ajax({
				url:"/fourTeamsIssueManage/topNeedDoIssue.action",
				data:{
					"topIssue.issueId":selectedIssue.encryptIdByIssueId,
					"topIssue.issueTag":issueTag
				},
				success:function(responseData){
					if (responseData==true){
						$.messageBox({message:$("#topIssue").text()+"成功!"});
						reloadIssue();
					}else{
						$.messageBox({level:'error',message:$("#topIssue").text()+"失败!"});
					}
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可"+$(this).text()+"的事件！"});
			return;
		}
	});	

	//加急事件,取消加急事件
	$("#urgent").click(function(event){
		var dealCode = <s:property value="@com.tianque.issue.state.IssueOperate@URGENT.code"/>
		var cancelCode = <s:property value="@com.tianque.issue.state.IssueOperate@CANCEL_URGENT.code"/>
		var token = '<pop:token ajax="true" />'
		var selectedIssue = getSelectedData();
		if(selectedIssue.urgent=='1'){
			fourTeamsBindCancleUrgentById(selectedIssue.encryptIdByIssueStepId,cancelCode,token);
		}else{
			fourTeamsBindUrgentById(dealCode,selectedIssue.encryptIdByIssueStepId);
		}
	});

	//打印
	$("#printIssue").click(function(event){
		if(!hasRowSelected()){
			$.messageBox({level:'warn',message:"没有可作打印的数据！"});
			return ;
		}
		var selectedIssue = getSelectedData();
		$("#issueDialog").createDialog({
			width: 900,
			height: 600,
			title: '打印派单',
			url:'${path}/fourTeamsIssueManage/viewSubDetail.action?mode=print&keyId='+selectedIssue.encryptIdByIssueStepId,
			buttons: {
		   		"打印" : function(event){
					$("#issueAccordingPrint").printArea();
	   			},
	   			"导出word" : function(event){
					exportWord();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});

	$("#evaluate").click(function(event){
		if (hasRowSelected() && $("#evaluate").isButtonEnabled()){
			var selectedIssue = getSelectedData();
			$("#issueDialog").createDialog({
				width: 700,
				height: 500,
				title: "事件验证",
				url: "${path}/fourTeamsIssueManage/dispatchIssueEvaluate.action?issueEvaluate.issue.id="+selectedIssue.encryptIdByIssueId,
				buttons: {
			   		"验证" : function(event){
			   			$("#issueEvaluateForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可验证的数据！"});
			return;
		}
	});

	//转为工作台帐
	$("#convertToWorkingRecord").click(function(event){
		if (hasRowSelected() && $("#convertToWorkingRecord").isButtonEnabled()){
			var selectedIssue = getSelectedData();
			$("#issueDialog").createDialog({
				width: 800,
				height: 480,
				title: "转为台帐",
				url: "${path}/fourTeamsIssueManage/dispatch.action?mode=convertToWorkingRecord&keyId="+selectedIssue.encryptIdByIssueId,
				buttons: {
					"保存" : function(event){
		   				$("#workingRecordForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}else{
			$.messageBox({level:'warn',message:"没有可"+$(this).text()+"的事件！"});
			return;
		}
	});

	$("#viewProcess").click(function(){
		var data = $.extend($("#issueList").getPostData(),{"viewProcess":1});
		$("#viewProcessDialog").createDialog({
			width: document.documentElement.clientWidth,
			height: document.documentElement.clientHeight,
			title: "下辖"+viewType.name+"事件--动态列表",
			create:function(event,ui){
				$(event.target).prev().hide();
			},
			url: "${path}/fourTeamsIssueManage/findJurisdictionsForProcess.action?"+$.param(data)
		});
		$("#viewProcessDialog").css("overflow","hidden");
	});

	$("#publicltyCass").click(function(event){
		var selectedIssue = getSelectedData();
		if (!hasRowSelected()){
			$.messageBox({level:'warn',message:"没有可设为宣传案例的事件！"});
			return;
		}
		if(selectedIssue.publicltycass==1){
			$.messageBox({message:"该事件处理已经被设为宣传案例!",level:"warn"});
			return;
		}
		$.confirm({
			title:"系统提示",
			message:"是否要将该事件处理设置为宣传案例?",
			width:400,
			okFunc:publicltyCass
		});
	});

	$("#applyDelay").click(function (){
		if(!hasRowSelected()){
			$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
		var selectedIssue = getSelectedData();
		if(selectedIssue == null || '办理中' != selectedIssue.dealState){
			$.messageBox({level:'warn',message:"非处理中的数据，不能进行延期申请！"});
			return;
		}
		if('审核中' == selectedIssue.delayState){
			$.messageBox({level:'warn',message:"该事件正处于申请延期中，请等待领导审批！"});
			return;
		}
		$("#issueDialog").createDialog({
			width: 560,
			height: 230,
			title: "申请延期",
			url: "${path}/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsApplyDelayDlg.jsp?issueStepId="+selectedIssue.encryptIdByIssueStepId,
			buttons: {
		   		"申请" : function(event){
		   			$("#issueApplyDelayForm").submit();
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	
	$("#auditDelay").click(function (){
		if(!hasRowSelected()){
			$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
		var selectedIssue = getSelectedData();
		if(selectedIssue == null || selectedIssue.delayState=='' || typeof(selectedIssue.delayState)=='undefined'){
			$.messageBox({level:'warn',message:"该事件没有延期申请！"});
			return;
		}
		if('审核中' == selectedIssue.delayState){
			$("#issueDialog").createDialog({
				width: 780,
				height: 440,
				title: "申请延期",
				url: "${path}/issues/issueApplyDelay/dispatch.action?issueStepId="+selectedIssue.encryptIdByIssueStepId,
				buttons: {
					"保存" : function(event){
			   			$("#auditDelayForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		}
	});
	
	$("#delayDetails").click(function(){
		viewDelay();
	});
	
	$("#viewDelay").click(function (){
		viewDelay();
	});

	$("#readTop,#readBottom").click(function(event){
		var selectedIssue = getSelectedData();
		if(selectedIssue.dealState!=null && "待阅读" == selectedIssue.dealState){
			simpleDealIssue(selectedIssue.encryptIdByIssueStepId,<s:property value="@com.tianque.issue.state.IssueOperate@READ.code"/>);
		}else{
			$.messageBox({level:'warn',message:"该事件不可阅读，请进行其它操作！"});
		}
	});
	$("#conceptTop,#conceptBottom").click(function(event){
		var selectedIssue = getSelectedData();
		if(selectedIssue.dealState!=null && '待处理' == selectedIssue.dealState){
			simpleDealIssue(selectedIssue.encryptIdByIssueStepId,<s:property value="@com.tianque.issue.state.IssueOperate@CONCEPT.code"/>);
		}else{
			$.messageBox({level:'warn',message:"该事件不可受理，请进行其它操作！"});
		}
	});
	//由于该页面是动态的，使用click会重复绑定事件,所有使用以下方法
	$("#dealTop,#dealBottom").die().live("click",function(event){
		var selectedIssue = getSelectedData();
		if(selectedIssue.dealState!=null && '办理中' == selectedIssue.dealState){
			dealIssue(selectedIssue.encryptIdByIssueStepId);
		}else{
			$.messageBox({level:'warn',message:"该事件不可办理，请进行其它操作！"});
		}
	});
	$("#reportToTop,#reportToBottom").click(function(event){
		var selectedIssue = getSelectedData();
		if(selectedIssue.dealState!=null){
			simpleDealIssue(selectedIssue.encryptIdByIssueStepId,<s:property value="@com.tianque.issue.state.IssueOperate@REPORT_TO.code"/>);
		}else{
			$.messageBox({level:'warn',message:"该事件不可 上报指挥中心，请进行其它操作！"});
		}
	});

	$("#grade").click(function(){
		if(!hasRowSelected()){
			$.messageBox({level:'warn',message:"请选择可打分的数据！"});
			return;
		}
		var selectedIssue = getSelectedData();
		$.post('${path}/fourTeamsIssueManage/issueGradeIsVisabled.action?keyId=' + selectedIssue.encryptIdByIssueId, function (data) {
			if(isNaN(data)){
				$.messageBox({level:'warn',message: data});
				return;
			}
			$("#gradeDialog").createDialog({
				width: 700,
				height: 400,
				title: "给部门打分",
				url: '${path}/fourTeamsIssueManage/issueGrade.action?keyId=' + selectedIssue.encryptIdByIssueId,
				buttons: {
			   		"打分" : function(event){
			   			$("#gradeIssueForm").submit();
		   			},
			   		"关闭" : function(){
			        	$(this).dialog("close");
			   		}
				}
			});
		});
	});
	$("#gradeHistory").click(function(){
		if(!hasRowSelected()){
			$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
			return;
		}
		var selectedIssue = getSelectedData();
		$("#gradeDialog").createDialog({
			width: 800,
			height: 400,
			title: "打分详情",
			url: '${path}/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsIssueGradeHistory.jsp?keyId=' + selectedIssue.encryptIdByIssueId,
			buttons: {
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
});

function searchIssue(){
	setConditionImportant();
	var data=$("#searchIssueForm").serializeArray();
	var searchIssueVo={};
	for(i=0;i<data.length;i++){
		 if (searchIssueVo[data[i].name]) {
           searchIssueVo[data[i].name]=searchIssueVo[data[i].name]+","+data[i].value;
		} else {
            searchIssueVo[data[i].name] = data[i].value;
		}
	}
	var pageData = $.extend(
			{
			"searchIssueVo.sortField":"issueId",
			"searchIssueVo.order":"desc",
			"searchIssueVo.orgLevelInternalId":$("#currentOrgId").attr("orgLevelInternalId"),
			"searchIssueVo.functionalOrgType":$("#jurisdictionsFunctionalOrgType").val(),
			"searchIssueVo.leaderView":1,
			"viewType":$("#jurisdictionsViewType").val(),
			"type":$("#jurisdictionsIssueType").val(),
			"keyId":$("#jurisdictionsKeyId").val(),
			"orgLevel":$("#jurisdictionsOrgLevel").val(),
			"searchIssueVo.fourTeamsType":"<s:property value='@com.tianque.domain.property.FourTeamsType@PARTYMEMBERPIONEERSERVICETEAM'/>",
			"searchIssueVo.seachValue":$("#seachValue").val()
			},
			searchIssueVo);
	$("#issueList").setGridParam({
		url:"${path}/searchFourTeamsIssueByLevelManage/findIssueForView.action",
		datatype: "json",
		page : 1
	});
	$("#issueList").setPostData(pageData);
	$("#issueList").trigger("reloadGrid");
	$("#_statusTypeSelect").val('');
}
function searchIsssueByOrgId(stateType){
	var searchIssueVo={};
	var pageData = $.extend(
			{
			"searchIssueVo.sortField":"issueId",
			"searchIssueVo.order":"desc",
			"searchIssueVo.statusType": stateType,
			"searchIssueVo.orgLevelInternalId":$("#jurisdictionsOrgLevel").val(),
			"searchIssueVo.functionalOrgType":$("#jurisdictionsFunctionalOrgType").val(),
			"searchIssueVo.leaderView":1,
			"searchIssueVo.searchOrgId":$("#selectNodeId").val(),
			"searchIssueVo.targeOrgId":$("#jurisdictionsKeyId").val(),
			"viewType":$("#jurisdictionsViewType").val(),
			"type":$("#jurisdictionsIssueType").val()
			},
			searchIssueVo);
	$("#issueList").setGridParam({
		url:"${path}/searchFourTeamsIssueByLevelManage/findIssueForView.action",
		datatype: "json",
		page : 1
	});
	$("#issueList").setPostData(pageData);
	$("#issueList").trigger("reloadGrid");
}

function setConditionImportant(){
	//是否重大事件
	var conditionImportant = $("#conditionImportant").val();
	if($("#conditionImportant").val()==""){
		$("#conditionImportant").attr("disabled","disabled");
	}
}
function exportWord(){
	if(!hasRowSelected()){
		$.messageBox({level:'warn',message:"没有可作打印的数据！"});
		return ;
	}
	var selectedIssue = getSelectedData();
	this.location="${path}/fourTeamsIssueManage/viewSubDetail.action?mode=printWord&keyId="+selectedIssue.encryptIdByIssueStepId;
}

function publicltyCass(){
	var selectedIssue = getSelectedData();
	$.ajax({
		url:'${path}/fourTeamsIssueManage/publicltyCass.action',
		data:{
			"keyId":selectedIssue.encryptIdByIssueId
		},
		success:function(data){
			if (data.issueId){
				reloadIssue();
				$.messageBox({message:"该事件处理已设置为宣传案例!"});
			}else{
			 	$.messageBox({message:data});
			}
		}
	});
}

function dealIssue(issueStepId){
	if(issueStepId==null){
 		return;
	}
	var selectedIssue = getSelectedData();
	var issuesKeyId = selectedIssue.encryptIdByIssueId;
	$("#issueDialog").createDialog({
		width:720,
		height:550,
		title:'办理',
		url:'${path}/fourTeamsIssueManage/dispatchDeal.action?mode=deal&keyId='+issueStepId+'&issuesKeyId='+issuesKeyId,
		buttons: {
			"确定" : function(event){
				$("#issueDealForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}


function simpleDealIssue(issueStepId,dealType){
	if(issueStepId==null){
 		return;
	}
	var dealTitil = "受理";
	if(dealType == <s:property value='@com.tianque.issue.state.IssueOperate@READ.code'/>){
		dealTitil = "阅读";
	}else if(dealType == <s:property value='@com.tianque.issue.state.IssueOperate@REPORT_TO.code'/>){
		dealTitil = "上报指挥中心";
	}
	$("#issueDialog").createDialog({
		width:350,
		height:200,
		title:dealTitil,
		url:'${path}/fourTeamsIssueManage/dispatchDeal.action?dealCode='+dealType+'&keyId='+issueStepId,
		buttons: {
			"确定" : function(event){
				$("#issueDealForm").submit();
			},
			"关闭" : function(){
				$(this).dialog("close");
			}
		}
	});
}

function viewDelay() {
	if(!hasRowSelected()){
		$.messageBox({level:'warn',message:"请选择一条数据再进行操作！"});
		return;
	}
	var selectedIssue = getSelectedData();
	if(selectedIssue == null || '办理中' != selectedIssue.dealState){
		$.messageBox({level:'warn',message:"该事件没有延期申请！"});
		return;
	}
	$("#issueDialog").createDialog({
		width: 820,
		height: 460,
		title: "延期申请情况列表",
		url: "${path}/fourTeamsManage/fourTeamsIssueManage/issueManage/fourTeamsDelayList.jsp?issueStepId="+selectedIssue.encryptIdByIssueStepId,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function fourTeamsSuperviseIssue(keyId,typeCode){
	if (!isNullObject(keyId)){
		$("#issueDialog").createDialog({
	        width:550,
	        height:370,
	        title:'督办',
			url:'/fourTeamsIssueManage/dispatchDeal.action?dealCode='+typeCode+'&keyId='+keyId,
	        buttons: {
	            "确定" : function(event){
	                $("#superviseIssueForm").submit();
	            },
	            "关闭" : function(){
	                $(this).dialog("close");
	            }
	        }
	    });
	}
}

function fourTeamsEditIssue(keyId){
	$("#issueDialog").createDialog({
		width:840,
		height:570,
		title: '修改事件处理信息',
		url:'/fourTeamsIssueManage/dispatch.action?mode=editIssueForTab&keyId='+keyId,
		buttons: {
	   		"保存" : function(event){
				$("#issueMaintainForm").submit();
   			},
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function fourTeamsRemoveIssue(keyId){
	$.ajax({
		url:"/fourTeamsIssueManage/deleteIssue.action",
		data:{
			"keyId":keyId
		},
		success:function(responseData){
			if (responseData==true){
				$.messageBox({message:"已经成功删除该事件处理信息!"});
				reloadIssue();
				getMessageByUser();
			}else{
				$.messageBox({level:'error',message:"删除事件失败!"});
			}
		}
	});
}

//绑定取消加急事件
function fourTeamsBindCancleUrgentById(keyId,cancelCode,token){
	if (!isNullObject(keyId)){
		$.confirm({
			title:"系统提示",
			message:"是否确定要取消对该事件处理的加急!",
			width:400,
			okFunc:function(){
				$.ajax({
					url:"/fourTeamsIssueManage/dealIssue.action",
					data:{
						"keyId":keyId,
						"dealCode":cancelCode,
						"struts.token":token
					},
					success:function(data){
						if (data != null && data.issueStepId){
							$.messageBox({message:"已经成功取消该事件处理的加急!"});
							$("#urgent").find("span").html("加急");
							var urgenButton=$("#cancelUrgent").attr("id","urgent");
						}else{
							$.messageBox({message:data,level:"error"});
						}
						reloadIssue();
					}
				});
			}
		});
	}
}

//绑定加急事件
function fourTeamsBindUrgentById(dealCode,selectedId){
	if (isNullObject(selectedId)){
		$.messageBox({level:'warn',message:"没有可加急的事件！"});
	}else{
			$("#issueDialog").createDialog({
				width:600,
				height:400,
				title:'加急',
				url:'/fourTeamsIssueManage/dispatchDeal.action?dealCode='+dealCode+'&keyId='+selectedId,
				buttons: {
					"确定" : function(event){
						$("#singleContentDealForm").submit();
					  $("#urgent").find("span").html("取消加急");
					  var urgenButton=$("#urgent").attr("id","urgent");
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
			});
	}
}

function fourTeamsCancelSupervise(keyId,typeCode,token){
	if (!isNullObject(keyId)){
		$.ajax({
			url:'/fourTeamsIssueManage/dealIssue.action',
	        data:{
	            "dealCode":typeCode,
	            "keyId":keyId,
	            "struts.token":token
	        },
	        success:function(data){
	            if(data.issueStepId>0){
					$("#issueList").setRowData(keyId,data);
	                $.messageBox({message:"成功取消对该部门的督办!"});
	                reloadIssue();
	               // resetSuperviseButtonsState();
	            }else{
	                $.messageBox({message:data});
	            }
	        }
	    });
	}
	
}
</script>