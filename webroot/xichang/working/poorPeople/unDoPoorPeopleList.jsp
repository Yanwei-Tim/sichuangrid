<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="pop" uri="/PopGrid-taglib" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ include file="/includes/baseInclude.jsp"%>
<jsp:include page="/xichang/working/include/formatterAccount.jsp" />

<div class="content">
	<input type="hidden" id="module" value="${ module}">
	<input type="hidden" id="listType" value="${ listType}">
	<div class="ui-corner-all cf" id="nav">
		<s:if test="'childPoorPeopleManagement'.equals(module)">
			<input id="orgId" name="orgId" type="hidden" />
			<div class="areaChoose">
				<input type="text" id="orgSelector"  class="form-txt" style="width:138px"/>
		 	</div>
		 	<span class="lineBetween "></span>
		 	<div class="btnbanner btnbannerData">
		 		<div class="userState" id="fastSearchSelect">
					<select id="displayLevel" name="displayLevel" class="basic-input" >
						<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_ALLURISDICTION"/>"  selected="selected">全部</option>
						<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_SAMEGRADE"/>">仅显示本级</option>
						<option value="<s:property value="@com.tianque.domain.property.BasicOrgType@ORGSCOPE_DIRECTJURISDICTION"/>">直属下辖</option>
					</select>
				</div>
		 	</div>
	 	</s:if>
	 	<s:else>
	 		<div class="btnbanner btnbannerData">
				<div class="ui-widget autosearch">
					<input class="basic-input" type="text" value="请输入困难群众台账的编号" name="searchText" id="searchText" maxlength="18" class="searchtxt" style="width: 180px;"
						onblur="value=(this.value=='')?'请输入困难群众台账的编号':this.value;" onfocus="value=(this.value=='请输入困难群众台账的编号')?'':this.value;" />
					<button onclick="$('#searchText').val('请输入困难群众台账的编号');" class="ui-icon ui-icon-refresh searchbtnico"></button>
				</div>
			</div>
	 	</s:else>
		
		<a href="javascript:;" id="fastSearchButton"><span>搜索</span></a>
		<pop:JugePermissionTag ename="searchPoorPeople">
		    <a id="searchPoorPeople" href="javascript:void(0)"><span>高级搜索</span></a>
	    </pop:JugePermissionTag>
	    <span class="lineBetween "></span>
	    <s:if test="'unDo'.equals(listType)&&'poorPeopleManagement'.equals(module)">
			<pop:JugePermissionTag ename="addPoorPeople">
				<a id="addPoorPeople" href="javascript:;"><span>新增</span></a>
			</pop:JugePermissionTag>
		</s:if>
		<s:if test="'unDo'.equals(listType)&&'poorPeopleManagement'.equals(module)">
			<pop:JugePermissionTag ename="updatePoorPeople">
				<a id="updatePoorPeople" href="javascript:void(0)"><span>修改</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="deletePoorPeople">
				<a id="deletePoorPeople" href="javascript:void(0)"><span>删除</span></a>
			</pop:JugePermissionTag>
		</s:if>
		<s:else>
			 <s:if test="@com.tianque.core.util.ThreadVariable@getUser().isAdmin()" >
				<a id="updatePoorPeople" href="javascript:void(0)"><span>修改</span></a>
				<a id="deletePoorPeople" href="javascript:void(0)"><span>删除</span></a>
			</s:if>
		</s:else>
		<pop:JugePermissionTag ename="normalPoorPeople">
			<a id="normalPoorPeople" href="javascript:void(0)"><span>查看</span></a>
		</pop:JugePermissionTag>
		<s:if test="!'unDo'.equals(listType)&&'poorPeopleManagement'.equals(module)">
		<pop:JugePermissionTag ename="evaluatePoorPeople">
			<a id="evaluatePoorPeople" href="javascript:void(0)"><span>评价反馈</span></a>
		</pop:JugePermissionTag>
		</s:if>
		<s:if test="'unDo'.equals(listType)&&'poorPeopleManagement'.equals(module)">
			<pop:JugePermissionTag ename="maintainMember">
				<a id="maintainMember" href="javascript:void(0)"><span>维护家庭成员</span></a>
			</pop:JugePermissionTag>
			<pop:JugePermissionTag ename="recordsPoorPeople">
				<a id="recordsPoorPeople" href="javascript:void(0)"><span>情况记录</span></a>
			</pop:JugePermissionTag>
		</s:if>
		<a id="reload" href="javascript:void(0);" onclick="loadPoorPeopleData();$('#searchText').val('请输入困难群众台账的编号');"><span>刷新</span></a>
	</div>
	<div style="width: 100%;">
		<table id="poorPeopleList"></table>
		<div id="poorPeopleListPager"></div>
	</div>
	<div id="poorPeopleDialog" style="overflow: hidden"></div>
	<div id="poorPeopleMembersMainDialog" style="overflow: hidden"></div>
</div>
<script type="text/javascript">
<pop:formatterProperty name="createTableType" domainName="@com.tianque.domain.property.PropertyTypes@CREATE_TABLE_TYPE" />

function loadPoorPeopleData(pData) {
	var _poorPeopleList = $("#poorPeopleList");
	_poorPeopleList.setGridParam({
		url:"${path}/account/poorPeopleManage/findUndoPoorPeopleForList.action?module="+$("#module").val()+"&listType="+$("#listType").val(),
		datatype: "json",
		page:1
	});
	var pageData = null, 
	<s:if test="'poorPeopleManagement'.equals(module)">
		pageData=$.extend({"orgId":USER_ORG_ID},pData);
	</s:if>
	<s:else>
		pageData=$.extend({
				"orgId":$("#orgId").val(),
				"poorPeople.displayLevel":$("#displayLevel").val()
				
		},pData);
	</s:else>
	_poorPeopleList.setPostData(pageData == null ? {} : pageData);
	_poorPeopleList.trigger("reloadGrid");
}
var dialogWidth = 800, dialogHeight = 600, isNeedMaintainMember = false, userOrgId = "<s:property value='@com.tianque.core.util.ThreadVariable@getUser().getOrganization().getId()'/>";
$(function (){
	<s:if test="'childPoorPeopleManagement'.equals(module)">
		var tree=$("#orgSelector").treeSelect({
			inputName:"orgId",
			changeFun:function(node,e){
				loadPoorPeopleData({"orgId":node.id});
			}
		});
		if(isGrid()){
			$("#fastSearchSelect").hide();
		}
	</s:if>
	$("#maintainMember").click(function (){
		var selectedIds = $("#poorPeopleList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
			return;
		}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
			 return;
		}
		maintainMember(selectedId);
	});
	$("#searchPoorPeople").click(function (){
		$("#poorPeopleDialog").createDialog({
			width:550,
			height:240,
			title:'困难群众台账查询-请输入查询条件',
	 		url:'${path}/xichang/working/poorPeople/searchPoorPeopleDlg.jsp?orgId='+userOrgId,
			buttons: {
		   		"查询" : function(event){
					var formdata = $("#searchPoorPeopleForm").serialize();
					if (formdata != '') {
						formdata = formdata.replace(/=/g, '":"');
						formdata = formdata.replace(/&/g, '","');
						formdata += '"';
					}
					formdata = decodeURIComponent('{"' + formdata + '}').replace(',"poorPeople.isPartyMember":"全部"','');
					loadPoorPeopleData((new Function("return " + formdata))());
		        	$(this).dialog("close");
	   			},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	$("#poorPeopleList").jqGridFunction({
		datatype: "local",
	   	colModel:[
	        {name:"id",index:"id",hidden:true,sortable:false},
	        {name:"encryptId",index:'id',frozen:true,hidden:true},
	        {name:"organization.id",index:'organization.id',hidden:true,hidedlg:true,sortable:false,frozen:true},
	        {name:"earingWarn", index:'earingWarn',label:'预警',formatter:earingWarnFormatter,width:65,frozen:true,sortable:false,align:'center' },
	        {name:"serialNumber",sortable:true,align:'center',label:'编号',width:200},
      		{name:'organization.orgName',index:'orgId',label:'所属区域', width:350,hidden:false,sortable:true,align:'center'},
	        {name:'name',label:'有关人员',sortable:true,align:'center',width:100},
	        {name:'helpInfo',label:'情况描述',width:420},
	        {name:'createTableType', index:'createTableType',label:'建表类型', width:300,formatter:createTableTypeFormatter, sortable:true, align:'center', hidden:false},
			{name:'createDate', index:'createDate',label:'建表时间', width:200, sortable:true, align:'center', hidden:false},
			{name:'registrant', index:'registrant',label:'登记人', width:200, sortable:true, align:'center', hidden:false}
	        // {name:'lastLoginTime',label:'评价反馈',width:100,align:'center'}
	       // {name:'admin',label:'记录情况',align:'center',width:80}
		],
		multiselect: true,
		ondblClickRow: function (arg0){viewPoorPeope(arg0);}
	});
	<s:if test="!'childPoorPeopleManagement'.equals(module)">
		loadPoorPeopleData();
	</s:if>

	
	$("#addPoorPeople").click(function (){
		$("#poorPeopleDialog").createDialog({	
			width: dialogWidth,
			height: dialogHeight,
			title:'新增困难群众台账',
			url:'${path}/account/poorPeopleManage/toAddPoorPeople.action?mode=add',
			buttons: {
		   		"保存" : function(event){
		   			isNeedMaintainMember = false;
		   			$("#maintainForm").submit();
		   		},
		   		"保存并维护成员" : function(event){
		   			isNeedMaintainMember = true;
		   			$("#maintainForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	$("#updatePoorPeople").click(function (){
		var selectedIds = $("#poorPeopleList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
			return;
		}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
			 return;
		}
		var poorPeople =  $("#poorPeopleList").getRowData(selectedId);
		$("#poorPeopleDialog").createDialog({
			width: dialogWidth,
			height: dialogHeight,
			title:'修改困难群众台账',
			url:'${path}/xichang/working/flow/accountLogsTab.jsp?mode=edit&id='+poorPeople.encryptId+'&accountType=<s:property value="@com.tianque.xichang.working.flow.constant.AccountType@POORPEOPLE"/>',
			buttons: {
		   		"保存" : function(event){
		   			$("#maintainForm").submit();
		   		},
		   		"关闭" : function(){
		        	$(this).dialog("close");
		   		}
			}
		});
	});
	$("#deletePoorPeople").click(function (){
		var selectedIds = $("#poorPeopleList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null||''==selectedIds){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行操作！"});
			 return;
		}
		var selectedIds=deleteOperatorByEncrypt("poorPeople",selectedIds,"encryptId");
		$.confirm({
			title:"确认删除",
			message:"确定要删除吗?一经删除将无法恢复",
			okFunc: function() {
				$.ajax({
					url:'${path}/account/poorPeopleManage/deletePoorPeople.action',
					type:"post",
					data:{
						"ids":selectedIds
					},
					success:function(data){
					    $.messageBox({message:"已经成功删除该困难群众台账信息!"});
						$("#poorPeopleList").trigger("reloadGrid");
					}
				});
			}
		});
	});
	$("#normalPoorPeople").click(function (){
		var selectedIds = $("#poorPeopleList").jqGrid("getGridParam", "selarrrow");
		var selectedId = getSelectedIdLast();
		if(selectedId==null || selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行查看！"});
	 		return;
		}
		viewPoorPeope(selectedId);
	});
	$("#fastSearchButton").click(function (){
		if('请输入困难群众台账的编号' == $("#searchText").val()){
			$("#searchText").focus();
			return;
		}
		loadPoorPeopleData({"poorPeople.serialNumber": $("#searchText").val()});		
	});
	
	$("#recordsPoorPeople").click(function(){
		var selectedIds = $("#poorPeopleList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
			return;
		}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行修改！"});
			 return;
		}
		recordsOperator(selectedId);
	});
	
	$("#evaluatePoorPeople").click(function(){
		var isCanEvaluate=false;
		var selectedIds = $("#poorPeopleList").jqGrid("getGridParam", "selarrrow");
		if(selectedIds==null || selectedIds.length>1){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行评价反馈！"});
	 		return;
		}
		var selectedId = getSelectedIdLast();
		if(selectedId==null){
			$.messageBox({level:'warn',message:"请选择一条记录，再进行评价反馈！"});
			 return;
		}
		var rowDataOrgId = $("#poorPeopleList").getRowData(selectedId)["organization.id"];
		if(userOrgId!=rowDataOrgId){
			$.messageBox({level:'warn',message:"该条记录不是您添加，您不能评论！"});
		 	return;
		 }
		$.ajax({
			type:"POST",
			url:'${path}/account/evaluateFeedbacksManage/isCanEvaluatePeopleAspirationByIdAndAccountType.action',
			data:{
					"evaluateFeedbacks.accountType":'<s:property value="@com.tianque.xichang.working.flow.constant.AccountType@POORPEOPLE"/>',
					"evaluateFeedbacks.accountId":selectedId
			},
			success:function(data){
					if(data){
						$.messageBox({level:'warn',message:"本条记录已经被评论过不能重复评论！"});
	 						return;
					}else{
						evaluateDonePoorPeople(selectedId);
					}
				}		
		});
		
	});
	
	//切换本级及下辖功能
	$("#displayLevel").change(function(event){
		loadPoorPeopleData();
	});
	
});

function evaluateDonePoorPeople(selectedId,orgId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#poorPeopleList").getRowData(selectedId);
	var id = rowData.id;
	if(id==null){
		 return;
	}
	var rowDataOrgId=rowData["organization.id"];
	$.confirm({
		title:"确认评论",
		message:"确定要评论吗?一经评论将无法再次评论、修改、删除等",
		okFunc: function() {
			$("#poorPeopleDialog").createDialog({
				title:"添加困难群众反馈评论信息",
				width: dialogWidth,
				height: dialogHeight,
				url:'${path}/xichang/working/flow/accountLogsTab.jsp?id='+id+'&accountType=<s:property value="@com.tianque.xichang.working.flow.constant.AccountType@POORPEOPLE"/>&mode=view&isEvaluate=true&evaluateMode=add',
				buttons: {
					"保存" : function(){
						$("#maintainEvaluateFeedbacksForm").submit();
						//$(this).dialog("close");
					},
					"关闭" : function(){
						$(this).dialog("close");
					}
				}
				
			});
		}
	});
	
}

function recordsOperator(selectedId){
	if(selectedId==null){
 		return;
	}
	$("#poorPeopleDialog").createDialog({
		width:dialogWidth,
		height:600,
		title:"情况记录",
 		url:'${path}/xichang/working/flow/maintainAccountLogsForPoorPeopleDlg.jsp?mode=add&accountType=<s:property value="@com.tianque.xichang.working.flow.constant.AccountType@POORPEOPLE"/>&accountId='+selectedId,
		buttons: {
			"保存" : function(){
				$("#maintainAccountLogsForm").submit();
			},
		   "关闭" : function(){
		        $(this).dialog("close");
		   }
		}
	});
	
}

function viewPoorPeope(selectedId){
	if(selectedId==null){
 		return;
	}
	var rowData = $("#poorPeopleList").getRowData(selectedId);
	var id = rowData.encryptId;
	if(id==null){
		 return;
	}
	$("#poorPeopleDialog").createDialog({
		width: dialogWidth,
		height: dialogHeight,
		title:'查看困难群众台账',
		url:'${path}/account/evaluateFeedbacksManage/dispatchOperate.action?accountType=<s:property value="@com.tianque.xichang.working.flow.constant.AccountType@POORPEOPLE"/>&mode=view&evaluateMode=view&accountId='+id+'&id='+id,		
		//url:'${path}/xichang/working/flow/accountLogsTab.jsp?accountType=<s:property value="@com.tianque.xichang.working.flow.constant.AccountType@POORPEOPLE"/>&mode=view&id='+id,
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function maintainMember(id){
	$("#poorPeopleMembersMainDialog").createDialog({
		width: 920,
		height: 560,
		title:'困难群众台账-维护家庭成员',
		url:'${path}/account/poorPeopleMemberManage/poorPeopleMemberList.action?poorPeopleMembers.poorPeople.id=' + id+"&mode=edit",
		buttons: {
	   		"关闭" : function(){
	        	$(this).dialog("close");
	   		}
		}
	});
}

function getSelectedIdLast(){
	var selectedId;
	var selectedIds = $("#poorPeopleList").jqGrid("getGridParam", "selarrrow");
	for(var i=0;i<selectedIds.length;i++){
		selectedId = selectedIds[i];
	}
	return selectedId;
}
function getSelectedIds(){
	var selectedIds = $("#poorPeopleList").jqGrid("getGridParam", "selarrrow");
	return selectedIds;
}
</script>